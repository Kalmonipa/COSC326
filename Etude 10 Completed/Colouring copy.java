/* NEED TO:
 * Initialize all regions to 0;
 * Check each regions colour to its neighbours to make sure they !=
 */


import java.io.*;
import java.util.*;
import java.lang.*;

public class Colouring{
  
  private static Map<Integer, ArrayList<Integer>> graph;
  private static ArrayList<Integer> regionColours;
  private static ArrayList<Integer> regions;
  private static ArrayList<Integer> adjList;
  public static ArrayList<Integer> visitedRegions = new ArrayList<Integer>();
  public static int currColour = 0;
  public static int[] colours = {0, 1, 2, 3};
  
  
  /* Reads in the map from input, returning each regions neighbours to the neighbours array
   */
  public static ArrayList<Integer> readMap(String input){
    
    adjList = new ArrayList<Integer>();
    
    Scanner scan = new Scanner(input);
    
    while(scan.hasNext()){
      adjList.add(scan.nextInt());
    }   
    return adjList;
  }
  
  
  /* public static ArrayList<Integer> getNeighbours(int region){
 
 return adjList; 
  } */
  
 public static void dfs(ArrayList<Integer> regionColours){
  for(int region : regionColours){
   if(regionColours.get(region) == 0){
     if(!visitedRegions.contains(region)){
       visitedRegions.add(region);
     }
    DFS_visit(region);
   }
  }
 }

 public static ArrayList<Integer> DFS_visit(int region){
   
   if(visitedRegions.size() == regionColours.size()){
     return regionColours;
   }
   
   if(!visitedRegions.contains(region)){
     visitedRegions.add(region);
   }
   
   //System.out.println("Visited Regions: " + visitedRegions);
   
   for(int neigh : graph.get(region)){
     if(!adjList.contains(neigh)){
       adjList.add(neigh);
     }
   }
   //System.out.println("AdjList: " + adjList);
   
   for(int neigh : adjList){
     if(regionColours.get(neigh) == 0 && !visitedRegions.contains(neigh)){
       //System.out.println(neigh);
       DFS_visit(neigh);
     }
   }
   regionColours.set(region, currColour);
   currColour++;
   if(currColour >= colours.length){
     currColour = 0;
   }
   return regionColours;
 }
  
  
  public static void main(String[] args){
    graph = new HashMap<Integer, ArrayList<Integer>>();
    regionColours = new ArrayList<Integer>();
    Scanner in = new Scanner(System.in);
    //Read file and add regions and their neighbours to graph
    while(in.hasNextLine()){
      int region = in.nextInt();
      graph.put(region, readMap(in.nextLine()));
    }
    //Assign each regions colour to 0 and make a list of vertices
    for(int region : graph.keySet()){
      regionColours.add(0);
    }
    //System.out.println(regionColours);
    dfs(regionColours);
    System.out.println(regionColours);
    in.close();
  }
}
    