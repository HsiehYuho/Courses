# Uses python3
import sys

def lcm_naive(a, b): 
	lcm = int (a * b // gcd_naive(a,b))    # floor division // VS classic division / 
#	print ("gcd : " + repr(gcd_naive(a,b)))
#	print ("a*b : " + repr(a * b))
	return lcm


''' bad algroithm 
	aResidual = int(a / gcd_naive(a,b))
	bResidual = int(b / gcd_naive(a,b))
	lcm = aResidual * bResidual * gcd_naive(a,b)
'''
	
'''
    for l in range(1, a*b + 1):
        if l % a == 0 and l % b == 0:
            return l

    return a*b
'''


def gcd_naive(a, b):
	current_gcd = 1
	while True:
		if a == 1 or b == 1:
			break
		elif a >= b:
			mutiple = int (a / int (b))
			a = a - mutiple * b
			if a == 0:
				current_gcd = b	
				break
			elif a == 1:
				break
		else:
			mutiple = int (b / int(a))
			b = b - mutiple * a
			if b == 0:
				current_gcd = a
				break
			elif b == 1:
				break
	return current_gcd


if __name__ == '__main__':
    input = sys.stdin.read()
    a, b = map(int, input.split())
    print(lcm_naive(a, b))

