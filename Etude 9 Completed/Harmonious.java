/* Oliver Westenra
 * Etude 9
 */
 
import java.util.*;
import java.lang.*;
 
public class Harmonious {
		
	//Stores the two numbers in a TreeMap. The first number is the key and 
	//the second number is the value associated with the first.
	private static Map<Long, Long> dict = new TreeMap<Long, Long>();
	
	//Finds the factors of n and sums them all. This is used to compare two numbers 
	//to find if they are harmonious or not
	public static long factors(long n){
		long sum = 0;
		long max = (long)Math.sqrt(n);
		
		for(long i = 2; i < max; i++){
			if(n%i == 0){
				sum += i;
				long d = n/i;
				if(d != i){
					sum += d;
				}
			}
		}
		return sum;
	}
	
	//Compares the sum of the factors of the first and second numbers to find if they 
	//equal the same. Returns true if they do, false if they don't.
	public static boolean compare(long first, long second){
		if(factors(second) == first){
			return true;
		}
		else{
			return false;
		}
	}
	
	/*
	  Runs through all the numbers from 1 to 2million and calls the factors method on
	  first and makes second equal that sum. This cuts down on how many iterations 
	  the program has to run through.
	*/
	public static void main(String[] args){
		long first = 1;
		long count = 0;

		while(first < 2000000){
			long second = factors(first);
			if(second > first && compare(first, second)){
				dict.put(Math.min(first, second), Math.max(first, second));

			}
			first++;
		}
		
		for(long keys : dict.keySet()){
			count++;
			long key = keys;
			long value = dict.get(keys);
			System.out.print(key + " " + value + "\n");
		}
		System.out.println("count = " + count);
	}
}