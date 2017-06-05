/* Oliver Westenra, etude 2*/

import java.util.*;

public class g711{
  
  
  public static ArrayList<Long> factors = new ArrayList<Long>();
  public static ArrayList<Long> results = new ArrayList<Long>();
  public static int count = 0;
  
  //This method runs through all the elements in the factors array to find the numbers that 
  //add and multiply to equal n
  public static void solve(long n){
    boolean bool = true;
    String number = "";
    
    results.add(n);
    outerloop:
      for(int aInd = 1; aInd < factors.size(); aInd++){
      long a = factors.get(aInd);
      for(int bInd = aInd; bInd < factors.size(); bInd++){
        long b = factors.get(bInd);               
        for(int cInd = bInd; cInd < factors.size(); cInd++){
          long c = factors.get(cInd);
          long d = c;
          if(a + b + c + d < n){ 
            d = n - c - b - a;
          }
          long multiply =  a*b*c*d;
          long add = a+b+c+d;
          
          if(add == n && multiply == (n*1000000)){
            
            if(number == ""){
              
              int nWhole = (int)n/100;
              int nRem = (int)n%100;
              int aWhole = (int)a/100;
              int aRem = (int)a%100;
              int bWhole = (int)b/100;
              int bRem = (int)b%100;
              int cWhole = (int)c/100;
              int cRem = (int)c%100;
              int dWhole = (int)d/100;
              int dRem = (int)d%100;
              
              number = String.format("$%d.%02d = $%d.%02d + $%d.%02d + $%d.%02d + $%d.%02d"
                                       ,nWhole, nRem, aWhole, aRem, bWhole, bRem,
                                     cWhole, cRem, dWhole, dRem);
              count++;
              
            }
            
            else{
              number = "";
              count--;
              break outerloop;
            }                    
          }
        }
      }
    }
      if(!number.equals("")){
        System.out.println(number);
      }
      factors.clear();
  }
  
  //Finds the factors of n*10000000 which limits the number of elements that the program has to run through
  public static void factor(long n){
    long nMil = n * 1000000;
    for(long i = 2; i <= n; i++){
      if(nMil % i == 0){
        factors.add(i);
      }
    }
    
    solve(n);
  }
  
  //initialises the upper and lower limits of the program and calls the factors method to find the factors
  public static void main(String[] args){
    long min = 100, max = 999;
    for(long i = min; i < max; i++){
      factor(i);
      
    }
    System.out.println(count + " solutions");
  }
  
  
  
  
}
