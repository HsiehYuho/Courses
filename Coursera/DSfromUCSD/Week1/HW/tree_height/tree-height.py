# python3

import sys, threading
sys.setrecursionlimit(10**7) # max depth of recursion
threading.stack_size(2**27)  # new thread will get stack of such size

def findMax(inputList,dicList):
	if len(inputList) == 0:
		return 0
	else:
		maxNumber = 0
		for i in range (0,len(inputList),1):
			number = 1 + findMax(dicList[inputList[i]],dicList)
			if number > maxNumber:
				maxNumber = number
		return maxNumber

class TreeHeight:
        def read(self):
                self.n = int(sys.stdin.readline())
                self.parent = list(map(int, sys.stdin.readline().split()))

        def compute_height(self):
                # Replace this code with a faster implementation
                root = -1
                dic = {i: [] for i in range(0,self.n,1)}
                for i in range(self.n):
                	if self.parent[i] == -1 :
                		root = i
                	else:
                		dic[self.parent[i]].append(i)

                # print ('root : ' + repr(root))
               	# print (dic)
               	maxHeight = 1 + findMax(dic[root],dic)
               	# index = [root]              	
               	# maxHeight = 1           		
               	# print ('maxHeight' + repr(maxHeight))
                return maxHeight

                '''
                maxHeight = 0
                for vertex in range(self.n):
                        height = 0
                        i = vertex
                        while i != -1:
                                height += 1
                                i = self.parent[i]
                        maxHeight = max(maxHeight, height);
                return maxHeight;
				'''



def main():
  tree = TreeHeight()
  tree.read()
  print(tree.compute_height())

threading.Thread(target=main).start()
