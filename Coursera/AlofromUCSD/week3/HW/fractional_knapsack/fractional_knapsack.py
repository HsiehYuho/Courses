# Uses python3
import sys
def get_optimal_value(capacity, weights, values):
	value = 0.
	remainCapacity = capacity
	''' sort the value/weight at first to spare the time  ''' 
	wvValues = [float(x)/float(y) for x,y in zip(values,weights)] # values/weights
	# cannot In python 3.0 zip returns a zip object. 
	#You can get a list out of it by calling list(zip(a, b) and this is quite difficult
	wvMetrix = list(zip(wvValues,weights)) # [values/weights , weights]  
	''' then use greedy method to solve the problem  ''' 
	sortedWvmetrix = sorted(wvMetrix,key = lambda x:x[0], reverse = True)
	for i in range (0,len(wvValues),1):
		if remainCapacity == 0:
			break
		factor = min(remainCapacity,sortedWvmetrix[i][1])
		value = value + factor*sortedWvmetrix[i][0]
		remainCapacity = remainCapacity - factor	
	return value


if __name__ == "__main__":
	data = list(map(int, sys.stdin.read().split()))
#	print (data)
	n, capacity = data[0:2]
	values = data[2:(2 * n + 2):2]
	weights = data[3:(2 * n + 2):2]
	opt_value = get_optimal_value(capacity, weights, values)
	print("{:.10f}".format(opt_value))
