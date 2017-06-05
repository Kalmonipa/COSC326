#Oliver Westenra, Etude 2

def s711():	

	#Runs through all possible numbers that could add and multiply to find 711		
	for a in range(1, 711):
		for b in range(1, 711):
			for c in range(1, 711):
				d = 711 - a - b - c
				if(a < b and b < c and c < d):
					if(a * b * c * d == 711000000 and a + b + c + d == 711):
						print("$" + str(a)[0] + "." + str(a)[1:] + 
						" $" + str(b)[0] + "." + str(b)[1:] + 
						" $" + str(c)[0] + "." + str(c)[1:] +
						" $" + str(d)[0] + "." + str(d)[1:])
						return
	
s711()
