# Oliver Westenra, etude 2

from math import sqrt

count = 0
min = 643
max = 645

factors_of_n = []
results = []

#Prints out all the combinations of $x.yz = a + b + c + d
def solve(n):
	global count
	print n
	print factors_of_n
	for a in factors_of_n:
		for b in factors_of_n:
			for c in factors_of_n:
				for d in factors_of_n:
					if(a < b and b < c and c < d):
						if(a + b + c + d == n):
							if(a * b * c * d == n * 1000000):
								print('$%d.%.2d = $%d.%.2d + $%d.%.2d + $%d.%.2d + $%d.%.2d' 
								% ((n/100), (n%100), (a/100), (a%100), (b/100), (b%100),
								(c/100), (c%100), (d/100), (d%100)))
								count += 1
								return True
					
						
#Finds the factors of n*1,000,000 that are < n to limit the number 
#of iterations it runs through
def factors(n):
	factors_of_n.append([i for i in solve(sqrt(n*1000000)) if n % i == 0 and i < n])
	solve(n)

#Main method
def g711():
	for n in range(min, max):
		factors(n)
		#if(len(factors_of_n) >= 4): #Ignores n with <4 factors
			#solve(n)
			
				
	print "%d solutions" % count
	
g711()