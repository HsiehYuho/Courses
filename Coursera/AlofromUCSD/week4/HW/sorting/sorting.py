# Uses python3
import sys
import random

def partition3(a, l, r): # l,r are index of a, not over arrange
    #write your code here
#	print (a[l:r+1])
	pivot = a[l]
	pivotIndex = l	
	j = l
	for i in range(l + 1 , r + 1):
		if a[i] == pivot:
			pivotIndex += 1
			j += 1
			a.insert(pivotIndex,int(a.pop(i)))
#			print ("if a[" + repr(i) + "] == pivot = " + repr(pivot) + "then change to " + repr (a[l:r+1]) + " pivotIndex = " + repr(pivotIndex))
			continue
		if a[i] < pivot:
			j += 1
			a[j] , a[i] = a[i] , a[j]
#			print ("if a[" + repr(i) + "] < pivot = " + repr(pivot) + "then change to " + repr (a[l:r+1]))
	pivotArray = a[l:pivotIndex+1]
#	print ("pivotArray = " + repr(pivotArray))
#	if len(pivotArray) != (l-r+1):
	a[l:pivotIndex+1] = ()
	j = j - pivotIndex 
	a[l+j:l+j] = pivotArray #this is the place that waste me 3 hrs, it should be l+j instead of j....
#	else:
#		return (l,r)
#	print (a[l:r+1])
#	print ("l = " + repr(l) + "m1 = " + repr(l+j) + " m2= " + repr(l+j+len(pivotArray)-1))
	return (l+j,l+j+len(pivotArray)-1) # another 2 hours

def partition2(a, l, r):
    x = a[l]
    j = l
    for i in range(l + 1, r + 1):
        if a[i] <= x:
            j += 1
            a[i], a[j] = a[j], a[i]
    a[l], a[j] = a[j], a[l]
    return j


def randomized_quick_sort(a, l, r):
#    print ( "l = " + repr(l) + "r = "+ repr(r))
#    print (a)
	if l > r:
	    return
	k = random.randint(l, r)
#    print ("random k = " + repr(k))
	a[l], a[k] = a[k], a[l]
    

 	#use partition3
	(m1,m2) = partition3(a, l, r)
#    print ("m1 = " + repr(m1) + " m2 = " + repr(m2) + " l = " + repr(l) + "r = " +repr(r))
#	if m1 > l :
	randomized_quick_sort(a, l, m1-1);
#	if m2 < r:
	randomized_quick_sort(a, m2+1, r);


if __name__ == '__main__':
    input = sys.stdin.read()
    n, *a = list(map(int, input.split()))
#	n = random.randint(1,100)
#	a = []
#	for i in range(0,n,1):
#		a.append(random.randint(1,100))
#	b = sorted(a)
    randomized_quick_sort(a, 0, n - 1)
#	if a == b :
#		print (1)
#	else:
#		print (n)
#		print (a)
#		print (b)
    for x in a:
        print(x, end=' ')
