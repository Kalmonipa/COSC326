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
  private static int[][] moves;
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
      moves = new int[peanuts + 1][pretzels + 1];
      for (boolean[] row : rules) {
        for (boolean cell : row) {
          cell = false;
        }
      }
      for (int[] row : moves) {
        for (int cell : row) {
          cell = 0;
        }
      }
      createRules();
      
      // It is always possible for a player to take just 1 peanut or
      // 1 pretzel so these rules are added
      qPeanuts.add(0);
      qPretzels.add(1);
      firstOp.add('=');
      secOp.add('='); 
      qPeanuts.add(1);
      qPretzels.add(0);
      firstOp.add('=');
      secOp.add('='); 
      
      // If the next move results in there being no peanuts or pretzels left
      // then we have found a winning move and the game ends
      for (int i = 0; i < qPeanuts.size(); i++) {
        if (peanuts - qPeanuts.get(i) == 0 && pretzels - qPretzels.get(i) == 0) {
          winning.add(qPeanuts.get(i) + " " + qPretzels.get(i));
          foundWinner = true;
          break;
        }
      }
      // If we haven't found a winner straight off the bat then we run through the rules
      // to try and find a winner
      if (!foundWinner) {
        for (int i = 0; i < qPeanuts.size(); i++) {
          int numPea = peanuts - qPeanuts.get(i);
          int numPret = pretzels - qPretzels.get(i);
          if (numPea >= 0 && numPret >= 0) {
            if (theirTurn(numPea, numPret, 1)) {
              winning.add(qPeanuts.get(i) + " " + qPretzels.get(i));
              foundWinner = true;
              break;
            }
          }
        }
      }
  
   // Haven't found a winner after running through all the rules so we 
   // will print out 0 0
      if (!foundWinner) {
        winning.add("0 0");
      }
      
      }
   // Prints out the contents of winning, which should hopefully contain
   // a winning move
   for(String move : winning){
     System.out.println(move);
   }
    }

  /* The program alternates between myTurn and theirTurn which changes the values
   * of peanuts and pretzels after each move.
   
  /* Function for my turn. If there are no peanuts or pretzels left then we lose.
   * Otherwise it tries the next move to see what that results in.
   */
  public static boolean myTurn(int numPea, int numPret, int numLoops) {
 // No peanuts or pretzels left so we lose
    if (numPea == 0 && numPret == 0)
      return false;
  
    // This result has already been checked and it is a winning move
    if (moves[numPea][numPret] == 1) {
      return true;
 
 // This move has already been checked and it's a losing move
    } else if (moves[numPea][numPret] == 2) {
      return false;
    }
 // If the current move means that there are no peanuts and pretzels left
 // then it is a winning move
    for (int i = 0; i < qPeanuts.size(); i++) {
      if (numPea - qPeanuts.get(i) == 0 && numPret - qPretzels.get(i) == 0) {
        moves[numPea][numPret] = 1;
        return true;
      }
    }
 // If the current move means there are leftover peanuts and pretzels
 // we pass to the opponents turn to keep working through the game
    for (int i = 0; i < qPeanuts.size(); i++) {
      int p1 = numPea - qPeanuts.get(i);
      int p2 = numPret - qPretzels.get(i);
      if (p1 >= 0 && p2 >= 0) {
        if (theirTurn(p1, p2, numLoops + 1)) {
          return true;
        }
      }
    }
 // If we haven't found a result then the move must be a bad one.
    moves[numPea][numPret] = 2;
    return false;
    
  }
  
  // Runs through the opponents turn and checks the moves
  public static boolean theirTurn(int numPea, int numPret, int numLoops) {
 // No peanuts or pretzels left so they lose, meaning we win
    if (numPea == 0 && numPret == 0)
      return true;
    // The move is has already been checked so they are going to win
    if (moves[numPea][numPret] == 1) {
      return false;
 // The move has already been checked and is a losing move so we win
    } else if (moves[numPea][numPret] == 2) {
      return true;
    }
 // if the current move results in there being no more peanuts or pretzels
 // then they have a good move and we lose
    for (int i = 0; i < qPeanuts.size(); i++) {
      if (numPea - qPeanuts.get(i) == 0 && numPret - qPretzels.get(i) == 0) {
        moves[numPea][numPret] = 1;
        return false;
      }
    }
 // If the current move results there still being peanuts and pretzels
 // it becomes my turn and the game continues
    for (int i = 0; i < qPeanuts.size(); i++) {
      int p1 = numPea - qPeanuts.get(i);
      int p2 = numPret - qPretzels.get(i);
      if (p1 >= 0 && p2 >= 0) {
        if (myTurn(p1, p2, numLoops + 1) == false) {
          moves[numPea][numPret] = 1;
          return false;
        }
      }
    }
 // Otherwise the move is not a winning move and the game continues
    moves[numPea][numPret] = 2;
    return true;
  }
  

  /*
   * Creates rules based on the rules and number of peanuts and pretzels
   */
  private static void createRules() {
    int count = 0;
    for (int i = 0; i < firstOp.size(); i++) {
      int p1 = qPeanuts.get(i);
      int p2 = qPretzels.get(i);
      if (firstOp.get(i) == '<' && secOp.get(i) == '<') {
        qPeanuts.remove(i);
        qPretzels.remove(i);
        firstOp.remove(i);
        secOp.remove(i);
        i--;
        for (int j = 0; j < p1; j++) {
          for (int k = 0; k < p2; k++) {
            if (rules[j][k] == false) {
              qPeanuts.add(j);
              qPretzels.add(k);
              rules[j][k] = true;
            }
          }
        } 
      } 
      else if (firstOp.get(i) == '>' && secOp.get(i) == '<') {
        qPeanuts.remove(i);
        qPretzels.remove(i);
        firstOp.remove(i);
        secOp.remove(i);
        i--;
        for (int j = p1 + 1; j <= peanuts; j++) {
          for (int k = 0; k < p2; k++) {
            if (rules[j][k] == false) {
              qPeanuts.add(j);
              qPretzels.add(k);
              rules[j][k] = true;
            }
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
            if (rules[j][k] == false) {
              qPeanuts.add(j);
              qPretzels.add(k);
              rules[j][k] = true;
            }
          }
        }
      } 
      else if (firstOp.get(i) == '<' && secOp.get(i) == '>') {
        qPeanuts.remove(i);
        qPretzels.remove(i);
        firstOp.remove(i);
        secOp.remove(i);
        i--;
        for (int j = 0; j < p1; j++) {
          for (int k = p2 + 1; k <= pretzels; k++) {
            if (rules[j][k] == false) {
              qPeanuts.add(j);
              qPretzels.add(k);
              rules[j][k] = true;
            }
          }
        }
      } 
      else if (firstOp.get(i) == '=' && secOp.get(i) == '<') {
        qPeanuts.remove(i);
        qPretzels.remove(i);
        firstOp.remove(i);
        secOp.remove(i);
        i--;
        for (int j = 0; j < p2; j++) {
          if (rules[p1][j] == false) {
            qPeanuts.add(p1);
            qPretzels.add(j);
            rules[p1][j] = true;
          }
        }
      } 
      else if (firstOp.get(i) == '<' && secOp.get(i) == '=') {
        qPeanuts.remove(i);
        qPretzels.remove(i);
        firstOp.remove(i);
        secOp.remove(i);
        i--;
        for (int j = 0; j < p1; j++) {
          if (rules[j][p2] == false) {
            qPeanuts.add(j);
            qPretzels.add(p2);
            rules[j][p2] = true;
          }
        }
      } 
      else if (firstOp.get(i) == '>' && secOp.get(i) == '=') {
        qPeanuts.remove(i);
        qPretzels.remove(i);
        firstOp.remove(i);
        secOp.remove(i);
        i--;
        for (int j = p1 + 1; j <= peanuts; j++) {
          if (rules[j][p2] == false) {
            qPeanuts.add(j);
            qPretzels.add(p2);
            rules[j][p2] = true;
          }
        }
      } 
      else if (firstOp.get(i) == '=' && secOp.get(i) == '>') {
        qPeanuts.remove(i);
        qPretzels.remove(i);
        firstOp.remove(i);
        secOp.remove(i);
        i--;
        for (int j = p2 + 1; j <= pretzels; j++) {
          if (rules[p1][j] == false) {
            qPeanuts.add(p1);
            qPretzels.add(j);
            rules[p1][j] = true;
          }
        }
      }
    }
  }
}