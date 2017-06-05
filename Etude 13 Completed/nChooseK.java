public class nChooseK {
  
  
  public static void main(String[] args){
    
    System.out.println(choose(60L, 30L));  
    run(60L, 30L);
  }
  
  
  public static void run(long n, long k){
    
    for (int i = 0; i < n; i++) {
        int nCk = 1;
        for (int y = 0; y <= i; y++) {
            System.out.print(nCk + " ");
            nCk = nCk * (i-y) / (y+1);
        }
        System.out.println();
        System.out.println();
    }
  }

    
    
  public static long choose(long n, long k){
    if(k == 0) return 1;
    
    if(n != 0 && k > Long.MAX_VALUE / n){
      System.out.println("Broken");
    }
    
    return (n * choose(n - 1, k - 1)) / k;
  }
}
