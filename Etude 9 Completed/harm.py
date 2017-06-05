
from math import sqrt
from collections import OrderedDict

##
# Returns the sum of the factors of a number n.
#
# @param n The number to factor and sum.
# @return The sum of the factors of n.
##
def sumFactors(n, cache={}):
	if cache.get(n) is not None:
		return cache[n]
	cache[n] = sum(set(sum([[i, n//i] for i in range(2, int(sqrt(n)) + 1) if not n%i], [])))
	return cache[n]

##
# Checks to see if two numbers are harmonious to each other.
#
# @param first The first number to check.
# @param second The second number to check.
# @return True if the numbers are harmonious, false otherwise.
##
def harmonious(first, second):
	if sumFactors(second) == first:
		return True
	return False

##
# Main method
##
harmoniousNumbers = OrderedDict()
first = 1
while first < 2000000:
	second = sumFactors(first)
	if second > first and harmonious(first, second):
		harmoniousNumbers[min(first, second)] = max(first, second)
	first += 1

#Print out the pairs
for key, value in harmoniousNumbers.items():
    print(key, value)