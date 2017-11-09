
# Using python3
'''  
###naive algorithm### 
def calc_fib(n):
    if (n <= 1):
        return n

    return calc_fib(n - 1) + calc_fib(n - 2)

n = int(input())
print(calc_fib(n))
'''
###array algorithm###
def calc_fib_array(n):
	a = []
	a.append(1)
	a.append(1)
	if (n == 0):
		return 0
	if (n <=2 and n >0):
		return 1
	else:
		for i in range(2,n,1):
			a.append(a[i-1] + a[i-2])
		return a.pop()

n = int(input())
print(calc_fib_array(n))
