# Uses python3
import sys

def fibonacci_partial_sum_naive(from_, to):
    m = (from_- 1 + 1) % 60  # m = the ith of the array , not the index of the array, once -1, then become the index of array 
    n = (to - 1 + 2) % 60
    fibArray = [1,1]
    for i in range (2,60,1):
        fibArray.append((fibArray[i-1]+fibArray[i-2])%10)
    answer = ((fibArray[n]) - (fibArray[m]))
#    print (fibArray)
#    print (repr(n) + " " + repr (m))
#    print (repr(fibArray[n]) + " " + repr(fibArray[m]))
    if answer < 0:
        answer += 10
    return answer 


if __name__ == '__main__':
    input = sys.stdin.read();
    from_, to = map(int, input.split())
    print(fibonacci_partial_sum_naive(from_, to))
