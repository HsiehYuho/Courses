# python3

import sys

n, m = map(int, sys.stdin.readline().split())
lines = list(map(int, sys.stdin.readline().split())) #the original rows of the tables
rank = [1] * n # [1,1,1,....,1]
parent = list(range(0, n)) #[1,2,3,...,n]
ans = max(lines)
#print(lines)
def getParent(table):
    # find parent and compress path
    if table != parent[table]:
    	parent[table] = getParent(parent[table])
    return parent[table]

def merge(destination, source):
    global ans
    realDestination, realSource = getParent(destination), getParent(source)
    if realDestination == realSource:
        return ans
    else:
    	if rank[realDestination] > rank[realSource]:
    		parent[realSource] = realDestination
    		lines[realDestination] += lines[realSource]
    		if lines[realDestination]>ans:
    			ans = lines[realDestination]
    	elif rank[realDestination] < rank[realSource]:
    		parent[realDestination] = realSource
    		lines[realSource] += lines[realDestination]
    		if lines[realSource]>ans:
    			ans = lines[realSource]
    	else:
    		rank[realDestination] += 1
    		#print(repr(rank[realDestination]) + " " + repr(realDestination)+" " + repr(realSource))
    		parent[realSource] = realDestination
    		#print(repr(parent[realSource]) + " "+ repr(realDestination))
    		lines[realDestination] += lines[realSource]
    		if lines[realDestination] > ans:
    			ans= lines[realDestination]
    		#print(repr(lines[]))
    	return ans
    # merge two components
    # use union by rank heuristic 
    # update ans with the new maximum table size


for i in range(m):
    destination, source = map(int, sys.stdin.readline().split())
    print(merge(destination - 1, source - 1))  
#print(lines)
    
