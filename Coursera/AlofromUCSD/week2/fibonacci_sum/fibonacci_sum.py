# Uses python3
import sys

def fibonacci_sum(n):
    # write your code here
    m = (n) % 60
    fibArray = [1,1]
#    fibSum = [1,2]
    for i in range (2,m+2,1):
    	fibArray.append((fibArray[i-1]+fibArray[i-2])%10)
#    	fibSum.append((fibSum[i-1]+fibArray[i])%10)
#    print(fibArray)
    if fibArray[m+1]%10-1 == -1:
    	return 9 
    else: 
    	return fibArray[m+1]%10-1
    

if __name__ == '__main__':
    input = sys.stdin.read()
    n = int(input)
    print(fibonacci_sum(n))
