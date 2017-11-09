# Uses python3
import sys

def get_fibonacci_huge_naive(n, m):
    if n <= 1:
        return n
    checkArray = [0,1,1]
    for i in range (3,n+1,1):
        checkArray.append((checkArray[i-1] + checkArray[i-2]) %m )
        if checkArray[i-2] == 0 :
            if checkArray[i-1] == 1 : 
                if checkArray[i] == 1 :
                    for i in range (0,3,1):
                        checkArray.pop()
                    break

    reminder = n % (len(checkArray))

#    print ("reminder : " +repr(reminder))
#    print ("length : " + repr(len(checkArray)))
#    print (checkArray)
    return calc_fib_array(reminder) % m



'''
    previous = 0
    current  = 1
    for _ in range(n - 1):
        previous, current = current, previous + current
    return current % m
'''

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



if __name__ == '__main__':
    input = sys.stdin.read();
    n, m = map(int, input.split())
    print(get_fibonacci_huge_naive(n, m))
