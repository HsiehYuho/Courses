# Uses python3
import random

while True:
	a = []
	numberOfInput = random.randint(2,5)
	for i in range(0,numberOfInput,1):
		a = a.append(int(random.randint(0,20)))

	#n = int(input())
	#a = [int(x) for x in input().split()]
	#print(a)
	#print(n)
	assert(len(a) == numberOfInput)

	resultFast = 0
	resultSlow = 0
	maxNumberIndex = -1
	secondMaxnumberIndex = -1
	for i in range(0,numberOfInput,1):
		if maxNumberIndex == -1 or a[maxNumberIndex] < a[i]:
			secondMaxnumberIndex = maxNumberIndex
			maxNumberIndex = i
	resultFast = a[maxNumberIndex]*a[secondMaxnumberIndex]

	for i in range(0, numberOfInput):
	    for j in range(i+1, numberOfInput):
	        if a[i]*a[j] > resultSlow:
	            resultSlow = a[i]*a[j]
	if resultSlow != resultFast:
		print("Not correct" + "resultSlow: " + resultSlow + "resultFast" + resultFast)
		break
	else:
		print("Correct"+ "resultSlow: " + resultSlow + "resultFast" + resultFast)