import java.util.Scanner;

public class etude1_ver2 {
  
  
  public static String antDirection = "N";
  
  public static String[][] gridArray = new String[200][200];
  
  //Initialises the grid of cells
  public static void main(String[] args){
    
    for(int i = 0; i<gridArray.length; i++){
      
      for(int j = 0; j<gridArray.length; j++){
        
        gridArray[i][j] = "w";
        
      }
    }
    
	//Starts the ant in the centre of the grid
    int antPositionX = 100;
    int antPositionY = 100;
    
    Scanner scanner = new Scanner(System.in);
    //String dna = scanner.nextLine();
    
        
    
    antMove(antDirection, antPositionX, antPositionY);
    
	//IT PRINTS OUT THE antPosition BECAUSE THESE TWO LINES ARE BELOW THE 
	//antMove() CALL. IF YOU MOVE THESE TWO TO ABOVE THE antMove() THEN IT
	//PRINTS IT OUT AT THE TOP. I DONT KNOW WHY IT PRINTS OUT THE ORIGINAL
	//POSITION THOUGH...
    //System.out.println(antPositionX+","+antPositionY);
    //System.out.println(antDirection);
    //DO WE NEED THESE LINES? ^
    
    
  }
  
  public static void antMove(String antDirection, int antPositionX, int antPositionY){
    
    
    //String[] directionParts = directions.split("[a-zA-Z]+");
    
    //String direction1 = directionParts[0];
    //String direction2 = directionParts[1];
    //String direction3 = directionParts[2];
    //String direction4 = directionParts[3];
    
    String dna = "w ESWN bbbb 14000";
    
    String[] parts = dna.split(" ");
    
    String cellState = parts[0];
    
    String directions = parts[1];
    
    String stateAfter = parts[2];
    
    int numMoves = Integer.parseInt(parts[3]);
    
    for(int i = 0;i<numMoves; i++){
      
	  //if the cell is white
      if(gridArray[antPositionX][antPositionY]=="w" && antDirection == "N"){
        antDirection = "E";
        gridArray[antPositionX][antPositionY]="b";  
        
        antPositionX=antPositionX + 1;   
        System.out.println(gridArray[antPositionX][antPositionY]);
        System.out.println(antPositionX+","+antPositionY);
        
      }else if(gridArray[antPositionX][antPositionY]=="w" && antDirection == "E"){
        antDirection = "S";
        gridArray[antPositionX][antPositionY]="b";
        //antPositionY--;   
        
        antPositionY=antPositionY - 1; 
        System.out.println(gridArray[antPositionX][antPositionY]);
        System.out.println(antPositionX+","+antPositionY);
        
      }else if(gridArray[antPositionX][antPositionY]=="w" && antDirection == "S"){
        antDirection = "W";
        gridArray[antPositionX][antPositionY]="b";
        //antPositionX--;     
        
        antPositionX=antPositionX - 1; 
        System.out.println(gridArray[antPositionX][antPositionY]);
        System.out.println(antPositionX+","+antPositionY);
        
      }else if(gridArray[antPositionX][antPositionY]=="w" && antDirection == "W"){
        antDirection = "N";
        gridArray[antPositionX][antPositionY]="b";
        //antPositionY++;   
        antPositionY=antPositionY + 1; 
        System.out.println(gridArray[antPositionX][antPositionY]);
        System.out.println(antPositionX+","+antPositionY);
        
        // if the cell is black
        
      }else if(gridArray[antPositionX][antPositionY]=="b" && antDirection == "N"){
        antDirection = "W";
        gridArray[antPositionX][antPositionY]="w";
        // antPositionX--;   
        antPositionX=antPositionX - 1; 
        System.out.println(gridArray[antPositionX][antPositionY]);
        System.out.println(antPositionX+","+antPositionY);
        
      }else if(gridArray[antPositionX][antPositionY]=="b" && antDirection == "E"){
        antDirection = "N";
        gridArray[antPositionX][antPositionY]="w";
        //antPositionY++;  
        antPositionY=antPositionY + 1; 
        System.out.println(gridArray[antPositionX][antPositionY]);
        System.out.println(antPositionX+","+antPositionY);
        
      }else if(gridArray[antPositionX][antPositionY]=="b" && antDirection == "S"){
        antDirection = "E";
        gridArray[antPositionX][antPositionY]="w";
        // antPositionX++;    
        antPositionX=antPositionX + 1; 
        System.out.println(gridArray[antPositionX][antPositionY]);
        System.out.println(antPositionX+","+antPositionY);
        
      }else if(gridArray[antPositionX][antPositionY]=="b" && antDirection == "W"){
        antDirection = "S";
        gridArray[antPositionX][antPositionY]="w";
        // antPositionY--;  
        antPositionY=antPositionY - 1; 
        System.out.println(gridArray[antPositionX][antPositionY]);
        System.out.println(antPositionX+","+antPositionY);
        
      }
    }
    
    
    
    
  }
  
  
  
  
  
  
}