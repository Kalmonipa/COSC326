import java.util.*;

public class etude1_ver4 {
  
  
  public static String antDirection = "N";
  
  public static Hashtable<String, String> hashTable = new Hashtable<String, String>();
  
  //public static int gridSize = 200;
  //public static String[][] gridArray = new String[gridSize][gridSize];
  
  public static ArrayList<ArrayList> ruleList = new ArrayList<ArrayList>();
  
  public static int numMoves;
  
  
  //Initialises the grid of cells
  public static void main(String[] args){
    
    
    //Starts the ant in the centre of the grid
    int antPositionX = 0;
    int antPositionY = 0;
    
    Scanner scanner = new Scanner(System.in);
    
    while(scanner.hasNext()){
      
      String dna = scanner.nextLine();
      arrayBuild(dna);
      
      if(scanner.hasNextInt()){
        numMoves = scanner.nextInt();
        break;
      }
    }
    
    String initialState = ruleList.get(0).get(0).toString();
    for(int i = 0; i<hashTable.size(); i++){
      
      for(int j = 0; j<hashTable.size(); j++){
        
        gridArray[i][j] = initialState;
        
      }
    }

    antMove(antDirection, antPositionX, antPositionY);
    
  }
  
  public static void antMove(String antDirection, int antPositionX, int antPositionY){
    
    //While loop to make the ant move the specified number of times
    int count = 0;
    while(count < numMoves){
      
      //Runs through the rules until it finds the one that it needs
      for(int i = 0;i<ruleList.size();i++){
        
        
        //Checks to see which rule it needs to follow to make the next move
        if(ruleList.get(i).get(0).equals(gridArray[antPositionX][antPositionY])){
          
          if(antDirection.equals("N") && count<numMoves){
            antDirection = ruleList.get(i).get(1).toString();
            char ch = antDirection.charAt(0);
            antDirection = Character.toString(ch);           
            String stateChange = ruleList.get(i).get(2).toString();
            
            
            if(antDirection.equals("N")){
              gridArray[antPositionX][antPositionY] = Character.toString(stateChange.charAt(0));
              antPositionY=antPositionY + 1;
            }if(antDirection.equals("E")){
              gridArray[antPositionX][antPositionY] = Character.toString(stateChange.charAt(1));
              antPositionX=antPositionX + 1; 
            }if(antDirection.equals("S")){
              gridArray[antPositionX][antPositionY] = Character.toString(stateChange.charAt(2));
              antPositionY=antPositionY - 1; 
            }if(antDirection.equals("W")){
              gridArray[antPositionX][antPositionY] = Character.toString(stateChange.charAt(3));
              antPositionX=antPositionX - 1; 
            }
            
            System.out.println(ruleList.get(i));
            System.out.println("#" +" "+ (antPositionX-(gridSize+1)/2)+" "+(antPositionY-(gridSize+1)/2));
            if(count!=numMoves-1){           
            System.out.println();
            }

            count++;
            
            
          }else if(antDirection.equals("E")&&count<numMoves){
            antDirection = ruleList.get(i).get(1).toString();
            char ch = antDirection.charAt(1);
            antDirection = Character.toString(ch);
            String stateChange = ruleList.get(i).get(2).toString();
            
            if(antDirection.equals("N")){
              gridArray[antPositionX][antPositionY] = Character.toString(stateChange.charAt(0));
              antPositionY=antPositionY + 1;
            }if(antDirection.equals("E")){
              gridArray[antPositionX][antPositionY] = Character.toString(stateChange.charAt(1));
              antPositionX=antPositionX + 1; 
            }if(antDirection.equals("S")){
              gridArray[antPositionX][antPositionY] = Character.toString(stateChange.charAt(2));
              antPositionY=antPositionY - 1; 
            }if(antDirection.equals("W")){
              gridArray[antPositionX][antPositionY] = Character.toString(stateChange.charAt(3));
              antPositionX=antPositionX - 1; 
            }
     
            System.out.println(ruleList.get(i));
            System.out.println("#" +" "+ (antPositionX-(gridSize+1)/2)+" "+(antPositionY-(gridSize+1)/2));
            if(count!=numMoves-1){
            System.out.println();
            }

            count++;
            
            
          }else if(antDirection.equals("S")&&count<numMoves){
            antDirection = ruleList.get(i).get(1).toString();
            char ch = antDirection.charAt(2);
            antDirection = Character.toString(ch);
            String stateChange = ruleList.get(i).get(2).toString();
            
            if(antDirection.equals("N")){
              gridArray[antPositionX][antPositionY] = Character.toString(stateChange.charAt(0));
              antPositionY=antPositionY + 1;
            }if(antDirection.equals("E")){
              gridArray[antPositionX][antPositionY] = Character.toString(stateChange.charAt(1));
              antPositionX=antPositionX + 1; 
            }if(antDirection.equals("S")){
              gridArray[antPositionX][antPositionY] = Character.toString(stateChange.charAt(2));
              antPositionY=antPositionY - 1; 
            }if(antDirection.equals("W")){
              gridArray[antPositionX][antPositionY] = Character.toString(stateChange.charAt(3));
              antPositionX=antPositionX - 1; 
            }
            
            System.out.println(ruleList.get(i));
            System.out.println("#" +" "+ (antPositionX-(gridSize+1)/2)+" "+(antPositionY-(gridSize+1)/2));
            if(count!=numMoves-1){          
            System.out.println();
            }

            count++;
            
            
          }else if(antDirection.equals("W")&&count<numMoves){
            antDirection = ruleList.get(i).get(1).toString();
            char ch = antDirection.charAt(3);
            antDirection = Character.toString(ch);
            //System.out.println("Ant is facing: " + antDirection);
            String stateChange = ruleList.get(i).get(2).toString();
            
            if(antDirection.equals("N")){
              gridArray[antPositionX][antPositionY] = Character.toString(stateChange.charAt(0));
              antPositionY=antPositionY + 1;
            }if(antDirection.equals("E")){
              gridArray[antPositionX][antPositionY] = Character.toString(stateChange.charAt(1));
              antPositionX=antPositionX + 1; 
            }if(antDirection.equals("S")){
              gridArray[antPositionX][antPositionY] = Character.toString(stateChange.charAt(2));
              antPositionY=antPositionY - 1; 
            }if(antDirection.equals("W")){
              gridArray[antPositionX][antPositionY] = Character.toString(stateChange.charAt(3));
              antPositionX=antPositionX - 1; 
            }
            
            System.out.println(ruleList.get(i));
            System.out.println("#" +" "+ (antPositionX-(gridSize+1)/2)+" "+(antPositionY-(gridSize+1)/2));
            if(count!=numMoves-1){           
            System.out.println();
            }

            count++;           
          } 
        }
      }
    }   
  }
  
  
  //Method that adds all the dna rules into arraylists
  public static void arrayBuild(String dna){
    
    ArrayList<String> ruleSplit = new ArrayList<String>();
    String[] parts = new String[3];
    parts[0] = dna.substring(0, 1) ;
    parts[1] = dna.substring(2, 6);
    parts[2] = dna.substring(7);
    
    
      ruleSplit.add(new String(parts[0]));
      ruleSplit.add(new String(parts[1]));
      ruleSplit.add(new String(parts[2]));
      
      ruleList.add(ruleSplit);
    
    
  }
  
  
  
  
  
  
}