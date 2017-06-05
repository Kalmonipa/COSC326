import java.io.*;
import java.lang.*;
import java.util.*;

public class work {
  
  // ArrayLists of the amount of peanuts or pretzels we can take based on the rules
  private static ArrayList<Integer> qPeanuts;
  private static ArrayList<Integer> qPretzels;
  // ArrayLists of the operators that are with each rule given. >, < or =
  // firstOp is the operator for the amount of peanuts you can take
  // secOp is the operator for the amount of pretzels you can take
  private static ArrayList<Character> firstOp;
  private static ArrayList<Character> secOp;
  // List of the winning moves that we find throughout the simulation
  public static ArrayList<String> winning = new ArrayList<String>();
  // Initial values of the peanut and pretzels at the start of the game
  private static int peanuts;
  private static int pretzels;
  // 2d array of the moves we can make. 
  // 0 == result hasn't been checked 
  // 1 == result has been checked and is a winner
  // 2 == result has been checked and is not a winner
  //private static int[][] moves;
  public static LinkedList<String> myMoves = new LinkedList<String>();
  public static ArrayList<String> theirMoves = new ArrayList<String>();
  // Stores the rules so that we don't make duplicates of the same rule
  private static boolean[][] rules;
  // Input being read in
  private static String line;
  // String of the winning moves that are 
  private static String winMove;
    
  
  /* Reads in the input and adds the contents of the first line to the 
   * the quantities array, then splits the array into the quanitities of
   * peanuts and pretzels available
   */
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    String currMove = "";
    
    theirMoves.add("0 1");
    theirMoves.add("1 0");
    
    while (scan.hasNextLine()) {
      line = scan.nextLine();
      boolean foundWinner = false;
      qPeanuts = new ArrayList<Integer>();
      qPretzels = new ArrayList<Integer>();
      firstOp = new ArrayList<Character>();
      secOp = new ArrayList<Character>();
      String[] quantities = line.split(" ");
      if(quantities.length != 2){ 
        break;
      }
      peanuts = Integer.parseInt(quantities[0]);
      pretzels = Integer.parseInt(quantities[1]);
      
      while (scan.hasNextLine()) {
        String[] str = scan.nextLine().split(" ");
        if (str.length != 2) break;
        firstOp.add(str[0].charAt(0));
        qPeanuts.add(Integer.parseInt(str[0].substring(1, str[0].length())));
        secOp.add(str[1].charAt(0));
        qPretzels.add(Integer.parseInt(str[1].substring(1, str[1].length())));
      }
      
      
      rules = new boolean[peanuts + 1][pretzels + 1];
      for (boolean[] row : rules) {
        for (boolean cell : row) {
          cell = false;
        }
      }
      
      createRules();
      
//      // It is always possible for a player to take just 1 peanut or
//      // 1 pretzel so these rules are added
//      qPeanuts.add(0);
//      qPretzels.add(1);
//      firstOp.add('=');
//      secOp.add('='); 
//      qPeanuts.add(1); 
//      qPretzels.add(0);
//      firstOp.add('=');
//      secOp.add('='); 

      System.out.println(theirMoves);
      System.out.println(myMoves);
      while(!myMoves.isEmpty()){
        currMove = myMoves.removeFirst();
        
        if(myTurn(currMove, peanuts, pretzels)){
          foundWinner = true;
          break;
        }
      }
      

      // Haven't found a winner after running through all the rules so we 
      // will print out 0 0
      if (!foundWinner) {
        System.out.println("0 0");
      }
      
      
      // Prints out the contents of winning, which should hopefully contain
      // a winning move
      else{
        System.out.println(currMove);
      }
    }
  }
    
    
  
  /* The program alternates between myTurn and theirTurn which changes the values
   * of peanuts and pretzels after each move.
   * 
   /* Function for my turn. If there are no peanuts or pretzels left then we lose.
   * Otherwise it tries the next move to see what that results in.
   */
  public static boolean myTurn(String currMove, int numPea, int numPret) {
    String[] moveSplit = currMove.split(" ");
    int part1 = Integer.parseInt(moveSplit[0]);
    int part2 = Integer.parseInt(moveSplit[1]);
    
    // No peanuts or pretzels left so we lose
    if (numPea == 0 && numPret == 0)
      return false;
    
    // If the current move means that there are no peanuts and pretzels left
    // then it is a winning move
    if (numPea - part1 == 0 && numPret - part2 == 0) {
      return true;
    }
    
    // If the current move means there are leftover peanuts and pretzels
    // we pass to the opponents turn to keep working through the game
    int p1 = numPea - part1;
    int p2 = numPret - part2;
    if (p1 >= 0 && p2 >= 0) {
      for(String move : theirMoves){
        boolean winningMove = theirTurn(currMove, p1, p2, move);
        if (winningMove == false) {
          return false;
        } 
      }
      return true;
    }
    else if(numPea - 1 >= 0){
      p1 = numPea - 1;
      for(String move : theirMoves){
        boolean winningMove = theirTurn(currMove, p1, numPret, move);
        if (winningMove == false) {
          return false;
        }      
      }
      return true;
    }
    else if(numPret - 1 >= 0){
      p2 = numPret - 1;
      for(String move : theirMoves){
        boolean winningMove = theirTurn(currMove, numPea, p2, move);
        if (winningMove == false) {
          return false;
        }
      }
      return true;
    }
    return false;
  }


      
  
  
  // Runs through the opponents turn and checks the moves
  public static boolean theirTurn(String currMove, int numPea, int numPret, String move) {
    String[] moveSplit = move.split(" ");
    int part1 = Integer.parseInt(moveSplit[0]);
    int part2 = Integer.parseInt(moveSplit[1]);
    
 // No peanuts or pretzels left so they lose, meaning we win
    if (numPea == 0 && numPret == 0)
      return true;
    
    // if the current move results in there being no more peanuts or pretzels
    // then they have a good move and we lose
    if (numPea - part1 == 0 && numPret - part2 == 0) {
      return false;
    }
    

 // Otherwise the move is not a winning move and the game continues
    return myTurn(currMove, numPea, numPret);
  }
  

  /*
   * Creates rules based on the rules and number of peanuts and pretzels
   */
  private static void createRules() {
    int count = 0;
    for (int i = 0; i < firstOp.size(); i++) {
      int p1 = qPeanuts.get(i);
      int p2 = qPretzels.get(i);
      //System.out.println(p1);
      //System.out.println(p2);
      if (firstOp.get(i) == '<' && secOp.get(i) == '<') {
       
        firstOp.remove(i);
        secOp.remove(i);
        i--;
        for (int j = 0; j < p1; j++) {
          for (int k = 0; k < p2; k++) {
            myMoves.add(j + " " + k);
            theirMoves.add(j + " " + k);
          }
        } 
      } 
      else if (firstOp.get(i) == '>' && secOp.get(i) == '<') {
       
        firstOp.remove(i);
        secOp.remove(i);
        i--;
        for (int j = p1 + 1; j <= peanuts; j++) {
          for (int k = 0; k < p2; k++) {
            myMoves.add(j + " " + k);
            theirMoves.add(j + " " + k);
          }
        }
      } 
      else if (firstOp.get(i) == '>' && secOp.get(i) == '>') {
        qPeanuts.remove(i);
        qPretzels.remove(i);
        firstOp.remove(i);
        secOp.remove(i);
        count++;
        for (int j = p1 + 1; j <= peanuts; j++) {
          for (int k = p2 + 1; k <= pretzels; k++) {
            myMoves.add(j + " " + k);
              theirMoves.add(j + " " + k);
          }
        }
      } 
      else if (firstOp.get(i) == '<' && secOp.get(i) == '>') {
      
        firstOp.remove(i);
        secOp.remove(i);
        i--;
        for (int j = 0; j < p1; j++) {
          for (int k = p2 + 1; k <= pretzels; k++) {
            myMoves.add(j + " " + k);
            theirMoves.add(j + " " + k);
          }
        }
      } 
      else if (firstOp.get(i) == '=' && secOp.get(i) == '<') {
        
        firstOp.remove(i);
        secOp.remove(i);
        i--;
        for (int j = 0; j < p2; j++) {
          myMoves.add(p1 + " " + j);
          theirMoves.add(p1 + " " + j);
        }
      } 
      else if (firstOp.get(i) == '<' && secOp.get(i) == '=') {
       
        firstOp.remove(i);
        secOp.remove(i);
        i--;
        for (int j = 0; j < p1; j++) {
          myMoves.add(j + " " + p2);
          theirMoves.add(j + " " + p2);
        }
      } 
      else if (firstOp.get(i) == '>' && secOp.get(i) == '=') {
        
        firstOp.remove(i);
        secOp.remove(i);
        i--;
        for (int j = p1 + 1; j <= peanuts; j++) {
          myMoves.add(j + " " + p2);
          theirMoves.add(j + " " + p2);
        }
      } 
      else if (firstOp.get(i) == '=' && secOp.get(i) == '>') {
        
        firstOp.remove(i);
        secOp.remove(i);
        i--;
        for (int j = p2 + 1; j <= pretzels; j++) {
          myMoves.add(p1 + " " + j);
          theirMoves.add(p1 + " " + j);
        }
      }
      else{
        //System.out.println("I am hee");
        firstOp.remove(i);
        secOp.remove(i);
        i--;
        myMoves.add(p1 + " " + p2);
        theirMoves.add(p1 + " " + p2);
      }
    }
  }
}