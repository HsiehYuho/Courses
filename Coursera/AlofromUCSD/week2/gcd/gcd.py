# Uses python3
import sys

def gcd_naive(a, b):
	current_gcd = 1
	while True:
		if a == 1 or b == 1:
			break
		elif a >= b:
			a = a - b
			if a == 0:
				current_gcd = b	
				break
			elif a == 1:
				break
		else:
			b = b - a
			if b == 0:
				current_gcd = a
				break
			elif b == 1:
				break
	return current_gcd
"""
    for d in range(2, min(a, b) + 1):
        if a % d == 0 and b % d == 0:
            if d > current_gcd:
                current_gcd = d
"""	
if __name__ == "__main__":
    input = sys.stdin.read()
    a, b = map(int, input.split())
    print(gcd_naive(a, b))
