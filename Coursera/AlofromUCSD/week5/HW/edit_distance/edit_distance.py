# Uses python3
import numpy as np

def edit_distance(s, t):
    #write your code here
	list_s = list(s)
	list_t = list(t)
	length_s = len(s)
	length_t = len(t)
	stMatrix = np.zeros((length_s+1,length_t+1),int)
	for i in range(length_s+1):
		stMatrix[i][0] = i
	for j in range(length_t+1):
		stMatrix[0][j] = j
	for i in range(1,length_s+1,1):
		for j in range(1,length_t+1,1):
			if list_s[i-1] == list_t[j-1]:
				stMatrix[i][j] = stMatrix[i-1][j-1]
			else :
				stMatrix[i][j] = min(stMatrix[i-1][j]+1,stMatrix[i][j-1]+1,stMatrix[i-1][j-1]+1)
#	print (stMatrix)
	return stMatrix[length_s][length_t]

if __name__ == "__main__":
	print(edit_distance(input(), input()))
