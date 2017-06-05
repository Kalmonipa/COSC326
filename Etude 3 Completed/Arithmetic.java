/*Oliver Westenra, Etude 3*/

import java.util.*;

public class Arithmetic{
  //Array of the integers used in the calculation
  private static ArrayList<Integer> numArray = new ArrayList<Integer>();

  //Backup array of numArray to use the numbers after they've been removed
  private static ArrayList<Integer> numArrayTemp = new ArrayList<Integer>();
  
  //Input coming from the file
  private static ArrayList<String> input = new ArrayList<String>(); 
  
  //Array of the sum that we need to get to
  //and the order that we have to use
  private static ArrayList<String> result = new ArrayList<String>();  
   
   
  //Adds the digits from the input to an array called numArray
  private static void addAndOrder(){
	String numString = input.get(0);
    String[] strArray = numString.split("\\s");
    for(int i = 0; i < strArray.length; i++){
      numArray.add(Integer.parseInt(strArray[i]));
	  numArrayTemp.add(Integer.parseInt(strArray[i]));
    }
    
	String resString = result.get(0);
    String[] resArray = resString.split("\\s");
	int obj = Integer.parseInt(resArray[0]);
	char n_or_l = resArray[1].charAt(0);
  
	calculate(obj, n_or_l, numArray, numArrayTemp);
	numArray.clear();
	numArrayTemp.clear();
	result.clear();
	input.clear();
  }
  
  
  /* Does the calculations in left to right order */
  public static void leftToRight(int num, int obj, ArrayList<Integer> numArray, ArrayList<Integer> numArrayTemp){
	int sum = 0;
	int numOps = 0;
	boolean isFirst = true;
	
	for(int i = 0; i < Math.pow(2, num); i++){
				
		//Gets the binary value of i up to 2^num
		String binValue = Integer.toBinaryString(i);
		//Pads the value with 0s until it has the length num
		while (binValue.length() < num){
			binValue = "0" + binValue;
		}
		char[] chars = binValue.toCharArray();
		char[] charArray = new char[num];
	
		for(int x = 0; x < chars.length; x++){
			if(chars[x] == '0'){
				charArray[x] = '+';
			}
			else {
				charArray[x] = '*';
			}
		}
				
		for(int y = 0; y < charArray.length; y++){
			char c = charArray[y];
			numOps++;
		
			if(isFirst == true){
				sum = numArray.get(0);
				numArray.remove(0);
			}
			
			if(c == '+'){
				sum += numArray.get(0);
			}
			else if(c == '*'){
				sum *= numArray.get(0);
			}
			numArray.remove(0);
			
			isFirst = false;
			
			if(sum == obj && numOps == num){
				int numOpsPrinted = 0;
				System.out.print("L ");
				String str = "";
				
				for(int len = 0; len < numArrayTemp.size(); len++){
					str += Integer.toString(numArrayTemp.get(len)) + " ";
					//System.out.print(numArrayTemp.get(len) + " ");
					if(numOpsPrinted == num){
						break;
					}
					str += charArray[numOpsPrinted] + " ";
					//System.out.print(charArray[numOpsPrinted] + " ");
					numOpsPrinted++;
				}
				System.out.print(str.trim());
				System.out.print("\n");
				return;
			}
			else if(numOps == num && sum != obj){
				isFirst = true;
				sum = 0;
				numOps = 0;
				for(int t = 0; t < numArrayTemp.size(); t++){
					numArray.add(t, numArrayTemp.get(t));
				}
			}
		}
	}
	System.out.println("L impossible");
  }
  
  
  /* Does the calculations based on normal order of operations (BEDMAS) */
  public static void normal(int num, int obj, ArrayList<Integer> numArray, ArrayList<Integer> numArrayTemp){
	  ArrayList<Character> charArray = new ArrayList<Character>();
	  ArrayList<Character> charArrayTemp = new ArrayList<Character>();
	  ArrayList<Character> ops = new ArrayList<Character>();
	  int sum = 0;
	  int numOps = 0;
	  boolean isFirst = true;

	  ops.add('+');
	  ops.add('*');
	  
	  for(int i = 0; i < Math.pow(2, num); i++){
		sum = 0;
		
		//Pads the binary value of num with zeroes		  
		String binValue = Integer.toBinaryString(i);
		while(binValue.length() < num){
			binValue = "0" + binValue;
		}
		  
		//Adds the binary value characs array
		char[] chars = binValue.toCharArray();
		ArrayList<Character> characs = new ArrayList<Character>();
		for(int c = 0; c < chars.length; c++){
			characs.add(chars[c]);
		}
		
		/* 0 == +, 1 == *. Adds the relevant operator to the ops array */
		for(int j = 0; j < characs.size(); j++){
			if(characs.get(j) == '0'){
				charArray.add(j, ops.get(0));
				charArrayTemp.add(j, ops.get(0));
			}else if(characs.get(j) == '1'){
				charArray.add(j, ops.get(1));
				charArrayTemp.add(j, ops.get(1));
			}
		}
		
		/* Does the multiplication calculations, takes the two numbers out of
		the array and adds the sum into that position */
		for(int x = 0; x < charArray.size(); x++){
			if(charArray.get(x) == '*'){
				int sumMulti = numArray.get(x) * numArray.get(x+1);
				numArray.remove(x);
				numArray.remove(x);
				numArray.add(x, sumMulti);
				charArray.remove(x);
				x -= 1;
			}
		}
		
		/* All the multiplications have been done so there are only additions
		left to do. This adds all the numbers in the array to sum */
		for(int n = 0; n < numArray.size(); n++){
			sum += numArray.get(n);
		}
		
		/*
			If sum != obj then resets the numArray to try different
			combinations of operators.
			If sum == obj then prints the combination of numbers and
			operators.
		*/
		if(sum != obj){
			numArray.clear();
			for(int a = 0; a < numArrayTemp.size(); a++){
				numArray.add(a, numArrayTemp.get(a));
			}
		}
		else{
			int printOp = 0;
			System.out.print("N ");
			String str = "";
			
			for(int v = 0; v < numArrayTemp.size(); v++){
				str += Integer.toString(numArrayTemp.get(v)) + " ";
				//System.out.print(numArrayTemp.get(v) + ".");
				if(printOp == num){
					break;
				}
				str += charArrayTemp.get(printOp) + " ";
				//System.out.print(charArrayTemp.get(printOp) + ".");
				printOp++;
			}
			System.out.print(str.trim());
			System.out.print("\n");
			return;
			}
		}
		System.out.println("N impossible");
	}
  
	/* Checks to see whether it needs to do normal order of operations (N) order
	or left to right (L). */
  public static void calculate(int obj, char n_or_l, ArrayList<Integer> numArray, ArrayList<Integer> numArrayTemp){
	int n = 0;
	
	n = numArray.size() - 1;
				
	if(numArray.size() == 1){
		System.out.println(n_or_l + " " + numArray.get(0));
		return;
	}
	
	if(n_or_l == 'L'){
		leftToRight(n, obj, numArray, numArrayTemp);
	}
	else if(n_or_l == 'N'){
		normal(n, obj, numArray, numArrayTemp);
	}
  } 
  
  
   public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    while(scanner.hasNextLine()){
		String line = scanner.nextLine();
		input.add(line);
		line = scanner.nextLine();
		result.add(line);
		addAndOrder();
	}
   }
 }