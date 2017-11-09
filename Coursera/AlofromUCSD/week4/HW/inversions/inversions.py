# Uses python3
import sys

def get_number_of_inversions(a, b, left, right): # 0 ~ right - 1
    number_of_inversions = 0
    if right - left <= 1:
        return number_of_inversions
    ave = (left + right) // 2
    number_of_inversions += get_number_of_inversions(a, b, left, ave)
    number_of_inversions += get_number_of_inversions(a, b, ave, right)
    #write your code here
    leftArray = []
    rightArray = []
#    print("a[l:r]: " + repr(a[left:right]) + " l: " + repr(left) + " r: " + repr(right))
    for i in range (left,ave,1):
        leftArray.append(a[i])
    for i in range (ave,right,1):
        rightArray.append(a[i])
#    print ("left: "+ repr(leftArray))
#    print ("right: "+ repr(rightArray))
    i = left
    while len(leftArray)!= 0 or len(rightArray) != 0:
        if len(rightArray) == 0:
            a[i] = leftArray[0]
            leftArray.pop(0)
            i += 1
        elif len(leftArray) == 0:
            a[i] = rightArray[0]
            rightArray.pop(0)
            i += 1
        elif leftArray[0] > rightArray[0]:
            number_of_inversions += len(leftArray)
            a[i] = rightArray[0]
            rightArray.pop(0)
            i += 1
        else:
            a[i] = leftArray[0]
            leftArray.pop(0)
            i += 1
            
#    print("a = " + repr(a))
#    print("# = " + repr(number_of_inversions))
    return number_of_inversions


if __name__ == '__main__':
    input = sys.stdin.read()
    n, *a = list(map(int, input.split()))
    b = n * [0]
    print(get_number_of_inversions(a, b, 0, len(a)))
