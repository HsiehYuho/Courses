# Uses python3
#import random

####Test region 
'''
while True:
	a = []
	numberOfInput = random.randint(2,1000)
	for i in range(0,numberOfInput,1):
		a.append(int(random.randint(0,99999)))
'''
numberOfInput = int(input())
a = [int(x) for x in input().split()]

assert(len(a) == numberOfInput)
resultFast = 0
#	resultSlow = 0
maxNumberIndex = -1
secondMaxnumberIndex = -1
for i in range(0,numberOfInput,1):
	if 	maxNumberIndex == -1 or a[maxNumberIndex] < a[i]:
		maxNumberIndex = i
for j in range(0,numberOfInput,1):
	if  j != maxNumberIndex and (secondMaxnumberIndex == -1 or a[j] > a[secondMaxnumberIndex]):
		secondMaxnumberIndex = j
resultFast = a[maxNumberIndex]*a[secondMaxnumberIndex]
print (resultFast)
'''
	for i in range(0, numberOfInput):
	    for j in range(i+1, numberOfInput):
	        if a[i]*a[j] > resultSlow:
	            resultSlow = a[i]*a[j]
	if resultSlow != resultFast:
		print(numberOfInput)
		print(a)
		print("Not correct " + "resultSlow: " + repr(resultSlow) + " resultFast: " + repr(resultFast))

		break
	else:
		print("Correct")
'''