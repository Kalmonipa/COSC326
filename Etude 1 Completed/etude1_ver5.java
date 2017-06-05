/*  
	Josh Tell, Oliver Westenra
	Etude 1, COSC326
*/

import java.util.*;
import java.awt.*;

/* An ant moves around a board based on the current state of the tile it is on,
 * and sets of rules given and a number of moves. These are entered using stdin.
 */ 

public class etude1_ver5 {
  
  //Stores the DNA of the ant given in stdin
  private static ArrayList<String> DNA = new ArrayList<String>();
  
  //Stores the states of all the tiles that the ant has passed over
  private static LinkedHashMap<String,Character> stateStore = new LinkedHashMap<String,Character>();
  
  private static ArrayList<Character> stateList = new ArrayList<Character>();
  private static ArrayList<String> directionList = new ArrayList<String>();
  private static ArrayList<String> nextState = new ArrayList<String>();
  
  //Initialises the number of moves that the ant will make
  private static int numMoves;
  
  //Initialises the grid of cells
  public static void main(String[] args){
    
    Scanner scanner = new Scanner(System.in);
    while(scanner.hasNextLine()){
      String line = scanner.nextLine();
      while(line.trim().isEmpty()){
        line = scanner.nextLine();
      }
	  
      //Checks if the next line is an integer. This means that the rules have stopped being given 
	  //and the number of moves to be made has been specified
      if(isInteger(line)){
        numMoves = Integer.parseInt(line);
        arrayBuilder();
        antMove(numMoves);
      }
      else{
        DNA.add(line);
        
      }
    }   
  }
  
  // checks to see if the line is integer
  public static boolean isInteger(String s) {
    try {
      Integer.parseInt(s);
    } catch(NumberFormatException e) {
      return false;
    } catch(NullPointerException e) {
      return false;
    }
    return true;
  }
  
  //Moves the ant based on the ruleset given and the tile that it is currently on
  private static void antMove(int steps){
    
    char state = stateList.get(0);
    int direction = 2;
    char antDirection = 'N';
    String states = "";
    char newState = ' ';
    String key = "";
    int count = 0;
    int antPositionX = 0;
    int antPositionY = 0;
    
    //Loops through the ant movement until it has made the specified number of moves
    while(count < steps){
      key = antPositionX + " " + antPositionY;
      if(stateStore.get(key) != null){
        state = stateStore.get(key);
      } else {
        state = stateList.get(0);
      }
      
      for(int i = 0; i < stateList.size(); i++){
        if(state == stateList.get(i)){
          String compass = directionList.get(i);
          states = nextState.get(i);
          antDirection = compass.charAt(direction);
          newState = states.charAt(direction);
        }
      }
      
	  //Checks the direction options and applies the appropriate movement based on the ruleset
      if(antDirection==('N')){
        stateStore.put(key, newState);
        antPositionY=antPositionY + 1;
        direction = 0;
        count++;
      }
      else if(antDirection==('E')){
        stateStore.put(key, newState); 
        antPositionX=antPositionX + 1; 
        direction = 1;
        count++;
      }
      else if(antDirection==('S')){
        stateStore.put(key, newState); 
        antPositionY=antPositionY - 1; 
        direction = 2;
        count++;
      }
      else if(antDirection==('W')){
        stateStore.put(key, newState); 
        antPositionX=antPositionX - 1; 
        direction = 3;
        count++;
      }
      
    }
    for(String s : DNA){
      System.out.println(s);
    }
    System.out.println(count + "\n#" + " " + antPositionX + " " + antPositionY);
    DNA.clear();
    stateList.clear();
    directionList.clear();
    nextState.clear();
    stateStore.clear();
  }
  
  //Method that adds all the dna rules into arraylists
  public static void arrayBuilder(){
    
    for(String str: DNA){
      stateList.add(str.charAt(0));
      directionList.add(str.substring(2,6));
      nextState.add(str.substring(7,11));
    }  
  }
}