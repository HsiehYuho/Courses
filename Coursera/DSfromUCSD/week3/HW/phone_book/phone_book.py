# python3
import numpy as np
class Query:
    def __init__(self, query):
        self.type = query[0]
        self.number = int(query[1])
        if self.type == 'add':
            self.name = query[2]

def read_queries():
    n = int(input())
    return [Query(input().split()) for i in range(n)]

def write_responses(result):
    print('\n'.join(result))

def process_queries(queries):
	result = []
	contacts = np.empty(10000000, dtype = object )
	for i in range(0,len(queries),1):
		if queries[i].type == 'add':
			contacts[queries[i].number] = (queries[i].name)
		elif queries[i].type == 'del':
			if not contacts[queries[i].number]== None:
				contacts[queries[i].number] = None 
		else:
			response = 'not found'
			if not contacts[queries[i].number] == None:
				response = str(contacts[queries[i].number])
			result.append(response)
	return result

if __name__ == '__main__':
    write_responses(process_queries(read_queries()))

