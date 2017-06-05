import java.util.Scanner;

public class Ant1 {

    public static int x = 50, y = 50;
        
    public static int[] antPosition = new int[x];

    public static String[][] gridArray = new String[100][100];

    
    public static void main(String[] args){
        

        for(int i = 0; i<gridArray.length; i++){

            for(int j = 0; j<gridArray.length; j++){
                
                gridArray[i][j] = "w";
                
            }
        }

        Scanner scanner = new Scanner(System.in);
        //String dna = scanner.nextLine();
        String dna = "w ESWN bbbb";

        String[] parts = dna.split(" ");
        
        String cellState = parts[0];

        String directions = parts[1];

        String stateAfter = parts[2];
        
        String numMoves = parts[3];



        for(int i = 0;i<numMoves; i++){
            antMove(cellState, directions, stateAfter);
        }

        
    }

    public static void antMove(String cellState, String directions, String stateAfter){


        String[] directionParts = directions.split("[a-zA-Z]+");
        
        String direction1 = directionParts[0];
        String direction2 = directionParts[1];
        String direction3 = directionParts[2];
        String direction4 = directionParts[3];
        
        if(cellState=="w"){
            gridArray[antPosition[0]][antPosition[1]]="b";
            antPosition = antPosition[x+1][y];     
            
        }else{
           
            gridArray[antPosition[0]][antPosition[1]]="w";
            antPosition = antPosition[x-1][y];     
              
            
        }

        System.out.println(antPosition);

        System.out.println(cellState);
        System.out.println(directions);       
        System.out.println(stateAfter);
        
        
    }

  

   
    

}