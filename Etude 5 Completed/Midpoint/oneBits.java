import java.lang.*;

public class oneBits{
  
  
  public static long highestOneBitPosition(int a) {
    long bits=0;
    while (a!=0) {
        ++bits;
        a>>=1;
    };
    return bits;
  }
  
  public static boolean addition_is_safe(int a, int b) {
    long a_bits=highestOneBitPosition(a), b_bits=highestOneBitPosition(b);
    System.out.println(a_bits);
    System.out.println(b_bits);
    return (a_bits<32 && b_bits<32);
  }
  
  public static boolean int_add_safe(int a, int b) { 
    if (a >= 0 && b >= 0) {
      System.out.println(Integer.MAX_VALUE - a >= b);
        return Integer.MAX_VALUE - a >= b; 
    }
    if (a < 0 && b < 0){
       System.out.println(Integer.MIN_VALUE - a >= b);
        return Integer.MIN_VALUE - a <= b;
    }
    return true;
}
  
  
  public static void main(String[] args){
    //addition_is_safe(500000, 3000000);
    int_add_safe(100, 100);
  }
}