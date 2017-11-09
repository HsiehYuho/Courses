# Uses python3
import sys

def optimal(n):
	accountList = [0,0,1,1]
	sequence = [n]
	if n == 1:
		return [1]
	if n == 2:
		return [1,2]
	if n == 3:
		return [1,3]
	for i in range (4,n+1,1):
		if i % 6 == 0:
			accountList.append(min(accountList[i-1]+1,accountList[i//2]+1,accountList[i//3]+1))
		elif i % 3 == 0:
			accountList.append(min(accountList[i-1]+1,accountList[i//3]+1))
		elif i % 2 == 0:
			accountList.append(min(accountList[i-1]+1,accountList[i//2]+1))
		else:
			accountList.append(accountList[i-1]+1)
	m = n
	while m != 1:
		if m % 6 == 0:
			if accountList[m//3] == accountList[m]-1:
				sequence.append(m//3)
				m = m//3
			elif accountList [m//2] == accountList[m]-1:
				sequence.append(m//2)
				m = m//2
			else:
				sequence.append(m-1)
				m = m-1
		elif m % 3 == 0:
			if accountList[m//3] == accountList[m]-1:
				sequence.append(m//3)
				m = m//3
			else:
				sequence.append(m-1)
				m = m-1
		elif m %2 == 0:
			if accountList [m//2] == accountList[m]-1:
				sequence.append(m//2)
				m = m//2
			else:
				sequence.append(m-1)
				m = m-1
		else:
			sequence.append(m-1)
			m = m-1
	return reversed(sequence)
def optimal_sequence(n):
    sequence = []
    while n >= 1:
        sequence.append(n)
        if n % 3 == 0:
            n = n // 3
        elif n % 2 == 0:
            n = n // 2
        else:
            n = n - 1
    return reversed(sequence)

input = sys.stdin.read()
n = int(input)
sequence = list(optimal(n))
print(len(sequence) - 1)
for x in sequence:
    print(x, end=' ')
