# Oliver Westenra, etude 2

from math import sqrt

count = 0
min = 100
max = 999

factors = []
results = []

#Prints out all the combinations of $x.yz = a + b + c + d
def solve(n):
	global count
	
	for a in factors:
		for b in factors:
			for c in factors:
				d = c
				if(a + b + c + d < n):
					d = n - c - b - a
				if(a < b and b < c and c < d):
					if(a + b + c + d == n and a * b * c * d == n * 1000000):
						print('$%d.%.2d = $%d.%.2d + $%d.%.2d + $%d.%.2d + $%d.%.2d' 
						% ((n/100), (n%100), (a/100), (a%100), (b/100), (b%100),
						(c/100), (c%100), (d/100), (d%100)))
						count += 1
						return True
	del factors[:]				
						
#Finds the factors of n*1,000,000 that are < n to limit the number 
#of iterations it runs through
#IT runs out of memeory here. ASK DEMONSTRATOR FOR HELP
def factor(n):
	for x in range(1, n):
		if (n*1000000) % x == 0:
			factors.append(x)
	
	solve(n)
	

#Main method
def g711():
	for n in range(min, max):
		factor(n)
		
				
	print "%d solutions" % count
	
g711()