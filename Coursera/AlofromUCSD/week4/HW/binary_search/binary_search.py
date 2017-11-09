# Uses python3
import sys

def binary_search(a, x):
    left, right = 0, len(a)-1
   # write your code here
    while left < right:
#        print (left)
#        print (right)
        midPoint = int ((left+right)/2) 
        if a[midPoint] == x:
            return midPoint
        elif x < a[midPoint]:
            right = midPoint-1
#            print (left)
#            print (right)
        else:
            left = midPoint+1
#            print (left)
#            print (right)
        
        if (right-left) == 1 or (right-left)==0:  ##extremely important
            if (a[left] == x):
                return left
            elif (a[right] == x):
                return right
            else:
                return -1
                break
#        if (right-left) == 1:
#            if a[right]== x:
#                return right
#            else:
#                return -1
#                break

def linear_search(a, x):
    for i in range(len(a)):
        if a[i] == x:
            return i
    return -1

if __name__ == '__main__':
    input = sys.stdin.read()
    data = list(map(int, input.split()))
    n = data[0]
    m = data[n + 1]
    a = data[1 : n + 1]
    for x in data[n + 2:]:
        # replace with the call to binary_search when implemented
        print(binary_search(a, x), end = ' ')
