/* Oliver Westenra  */

import java.lang.Math.*;
import java.util.*;


strictfp class Midpoint{
  
  public static int midpoint(int x, int y){
    
    if(x == y){
		return x;
	}
	else if(x == (y - y*2) || y == (x - x*2)){
		return 0;
	}
	else if(x==0){
		return y/2;
	}
	else if(y==0){
		return x/2;
	}
	else {
		int low = Math.min(x, y);
		int high = Math.max(x, y);
		int mid;
		if(low <= 0){
			if(high < 0){
				mid = (Math.abs(high) + low)/2 - Math.abs(high);
			}
			else {
				mid = (low + high)/2;
			}
		}
		else {
			mid = low + (high - low)/2;
		}
		return mid;
	}
  }

    
  
  public static void main(String[] args){
    
	Random ran = new Random();
	
	for(int i = 0; i < 2000000; i++){
		int x = ran.nextInt();
		int y = ran.nextInt();
			
		assert midpoint(x, x) == x;
		assert midpoint(x, -x) == 0;
		assert midpoint(x, 0) == x/2;
		assert midpoint(x, y) == midpoint(y, x);
		assert midpoint(x, y) >= Math.min(x, y);
		assert midpoint(x, y) <= Math.max(x, y);
	}
  }
  
}