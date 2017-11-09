# Uses python3
import sys

def get_majority_element(a, left, right):
    if left == right:
        return -1
    if left + 1 == right:
        return a[left]
    #write your code here
    midPoint = ((left + right - 1)//2+1)
#    print ("midPoint: " + repr(midPoint))
    candidate1 = get_majority_element(a,left,midPoint)
    candidate2 = get_majority_element(a,midPoint,right)
#    print("candidate2 " + repr(candidate2))
    rightCount = 0
    for i in range(left,right):
        if candidate1 == a[i]:
            rightCount = rightCount + 1
#    print("rightCount "+ repr(rightCount))         
#    print("candidate1 " + repr(candidate1))
#    print("left: " + repr(left))
#    print("right: " + repr(right))   
    if rightCount > ((right-left)//2):
        return candidate1
    leftCount = 0
#    print("leftCount "+ repr(leftCount))
#    print("candidate2 " + repr(candidate2))
    for i in range(left,right):
        if candidate2 == a[i]:
            leftCount  = leftCount  + 1
    if leftCount  > ((right-left)//2):
        return candidate2
    return -1
    
'''
    maxNumber = 0
    for i in range(len(a)):
        if a[i] > maxNumber:
            maxNumber = a[i]
#    print (maxNumber)
    count = []
    for i in range(0,maxNumber,1):
        count.append(0)
    for i in range(len(a)):
        count[a[i]-1] = count[a[i]-1] + 1
#    print (count)  
    for i in range(len(count)):
        if count[i] > int(len(a)/2):
            return 1
    return -1
'''
if __name__ == '__main__':
    input = sys.stdin.read()
    n, *a = list(map(int, input.split()))
    if get_majority_element(a, 0, n) != -1:
        print(1)
    else:
        print(0)
