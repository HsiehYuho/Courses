# Uses python3
import sys

def get_change(m):
    #write your code here
#    print (m)
#    print (int (m/10))
#    print (int (m/5))
	coinNumber = 0 
	while m > 0 : 
   		if  m == 0 :
   			return coinNumber 
   		elif int(m/10) > 0:
   			coinNumber = coinNumber + int(m/10)
   			m = m - 10 * int(m/10)
   			continue
   		elif int(m/5) > 0:
   			coinNumber = coinNumber + int(m/5)
   			m = m - 5 * int(m/5)
   			continue
   		else:
   			coinNumber = coinNumber + m
   			m = 0
   			continue	
	return coinNumber

if __name__ == '__main__':
    m = int(sys.stdin.read())
    print(get_change(m))
