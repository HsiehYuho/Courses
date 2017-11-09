# Uses python3
import sys
import numpy as np

def optimal_weight(W, w):
    # write your code here
    resMatrix = np.zeros((len(w)+1,W+1),int)    
    for i in range (0,len(w)+1,1):
    	for j in range (0,W+1,1):
    			if i == 0 or j == 0:
    				resMatrix[i][j] = 0
    			elif w[i-1]>j:
    				resMatrix[i][j] = resMatrix[i-1][j]
    			else:
    				resMatrix[i][j] = max(resMatrix[i-1][j-w[i-1]]+w[i-1], resMatrix[i-1][j])
#    print(resMatrix)
    return resMatrix[len(w)][W]
if __name__ == '__main__':
    input = sys.stdin.read()
    W, n, *w = list(map(int, input.split()))
    print(optimal_weight(W, w))
