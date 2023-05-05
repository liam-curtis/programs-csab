// Name: William Curtis                SHELL
// Date: 4/30/23
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 *              Graph1 WarshallDriver,
 *          and Graph2 FloydDriver
 */

interface AdjacencyMatrix
{
   void addEdge(int source, int target);
   void removeEdge(int source, int target);
   boolean isEdge(int from, int to);
   String toString();   //returns the grid as a String
   int edgeCount();
   List<Integer> getNeighbors(int source);
   public List<String> getReachables(String from);  //Warshall extension
}

interface Warshall      //User-friendly functionality
{
   boolean isEdge(String from, String to);
   Map<String, Integer> getVertices();     
   void readNames(String fileName) throws FileNotFoundException;
   void readGrid(String fileName) throws FileNotFoundException;
   void displayVertices();
   void allPairsReachability();  // Warshall's Algorithm
}

interface Floyd
{
   int getCost(int from, int to);
   int getCost(String from, String to);
   void allPairsWeighted(); 
}

class AdjMat implements AdjacencyMatrix, Warshall //Floyd 
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   // name maps to index (for Warshall & Floyd)
   /*for Warshall's Extension*/  ArrayList<String> nameList = null;  //reverses the map, index-->name
	  
   /*  enter your code here  */  
   //precondition: size is a positive integer
   //postcondition: initializes a 2D array that will represent an Adjacency Matrix
   // rows and columns will be the same as size
   public AdjMat (int size) {
      grid = new int[size][size];
      vertices = new TreeMap <String,Integer>();
      nameList = new ArrayList<String>();
   }
   //precondition: source and target are within the bounds of grid, grid is initialized
   //postcondition: adds an edge between the source node and target node
   public void addEdge(int source, int target) {
      grid[source][target] = 1;
   }
   //precondition: source and target are within the bounds of grid, grid is initialized
   //postcondition: removes an edge between the source node and target node
   public void removeEdge(int source, int target) {
      grid[source][target] = 0;
   }
  //precondition: source and target are within the bounds of grid, grid is initialized
  //postcondition: returns true if there is an edge between from node and to node, otherwise returns false
   public boolean isEdge(int from, int to){
      return grid[from][to] == 1;
   }
   //precondition: none
   //postcondition: returns the grid as a String
   public String toString() {
      String g = "";
      //loop through 2D array, add value at each index and add a space , creating a new line for each row
      for(int i = 0; i < grid.length; i++)
      {
         for(int j = 0; j < grid.length; j++)
            g += grid[i][j] + " ";
         g+="\n";
      }
      return g;
   }  
   //precondition: the grid is initialized
   //postcondition: returns the number of edges in the grid
   public int edgeCount() { 
      int c = 0;
      //loop through 2D array, increment c for each edge found
      for(int i = 0; i < grid.length; i++)
      {
         for(int j = 0; j < grid.length; j++)
         {
            if(grid[i][j] == 1)
               c++;
         }
      }
      return c;
   }
   //precondition: grid is initialized, and source is within bounds of grid
   //postcondition: returns the neighbors of source
   public List<Integer> getNeighbors(int source) {
      List <Integer> neighbors = new ArrayList <Integer> (); //initialize list of neighbors
      //loop through the column, and if column is an edge add to neighbors
      for(int c = 0; c < grid.length; c++)
      {
         if(grid[source][c] == 1)
            neighbors.add(c);
      }
      return neighbors; //return list of neighbors
   }
   
   
   //Warshall 
   //precondition: fileName is formatted correctly
   //postcondition: reads the names of cities from fileName using fileIO
   //maps the name of the city to the index of citymatrix, also builds nameList
   public void readNames(String fileName) throws FileNotFoundException
   {
      Scanner sc = new Scanner(new File(fileName)); //initialize scanner for fileName
      int citiesNum = sc.nextInt();
      sc.nextLine();
      for(int i = 0; i < citiesNum; i++)
      {
         String city = sc.nextLine().toUpperCase();
         vertices.put(city,i); //put each city into vertices map
         nameList.add(i, city);
      }
   }
   //precondition: fileName is formatted correctly
   //postcondition: reads the vertices of cities from fileName using fileIO
   //moves fileName to grid
   public void readGrid(String fileName) throws FileNotFoundException
   {
      Scanner sc = new Scanner(new File(fileName)); //initialize scanner for fileName
      int citiesNum = sc.nextInt(); //get number of cities 
      for(int i = 0; i < citiesNum; i++) //loop through each row
      {
         for(int j = 0; j < citiesNum; j++) //loop through each column of each row
            grid[i][j] = sc.nextInt();
      }
   }
   //precondition: vertices is initialized and has cities in it                                         
   //postcondition: displays the vertices by index
   public void displayVertices()
   {
      for(String city : vertices.keySet()) //loop through vertices with for-each loop, use keySet
         System.out.println(vertices.get(city) + "-" + city);
   }
   //precondition: adjacency matrix is initialized
   //postcondition: returns a reachability matrix that shows where a node is able to travel to
   public void allPairsReachability()
   {
      int n = grid.length; // n is the number of nodes
      
      for(int v = 0; v < n; v++) // intermediary vertices
      {
         for(int i = 0; i < n; i++) 
         {
            for(int j = 0; j < n; j++)
            {
               if(grid[i][v] + grid[v][j] == 2) //if the start and end are both 1, then the intermediary should be 1
                  grid[i][j] = 1;
            }   
         }
      }
   }
   //precondition: from and to are cities in the vertices map, otherwise will return a null pointer, 
   // as the vertices.get(__) will return null
   //postcondition: returns true if from and to are connected by an edge. otherwise returns false
   public boolean isEdge(String from, String to)
   {
      //changed so this method is not sensetive to changes in capitalization
      from = from.toUpperCase();
      to = to.toUpperCase();
      return grid[vertices.get(from)][vertices.get(to)] == 1;  
   }
   //precondition: none
   //postcondition: returns the vertices map
   public Map<String, Integer> getVertices()
   {
      return vertices;
   }
   //Warshall Extension
   //precondition: grid, nameList are initialized. from is in grid
   //postcondition: returns a list of cities that are reachable from city from
   public List<String> getReachables(String from)
   {
      ArrayList <String> toCities = new ArrayList <String> (); //initialize list of reachable cities from city from
      for(int i = 0; i < grid.length; i++) //loops through the column
      {
         if(grid[vertices.get(from.toUpperCase())][i] == 1)
            toCities.add(nameList.get(i)); //adds the city by accessing the nameList
      }
      return toCities;
   }
   
} // AdjMat

public class Pd7WilliamCurtisAdjMat_shell
{
   public static void main (String [] args)
   {
   
   }
}

/******************************************
Output:
  ----jGRASP exec: java Pd7WilliamCurtisWarshallDriver
 Warshall's Algorithm! Enter file of names: cities
 Enter file of the matrix: citymatrix
 Adjacency Matrix
 0 0 0 0 0 0 0 1 
 0 0 0 1 0 0 0 0 
 0 0 0 0 0 1 0 1 
 0 0 0 0 0 1 0 1 
 1 0 0 0 0 0 0 0 
 0 1 0 1 0 0 0 0 
 0 0 0 0 0 1 1 0 
 1 0 0 0 1 0 0 0 
 
 Number of Edges: 13
 
 0-PENDLETON
 1-PENSACOLA
 2-PEORIA
 3-PHOENIX
 4-PIERRE
 5-PITTSBURGH
 6-PRINCETON
 7-PUEBLO
 Reachability Matrix
 1 0 0 0 1 0 0 1 
 1 1 0 1 1 1 0 1 
 1 1 0 1 1 1 0 1 
 1 1 0 1 1 1 0 1 
 1 0 0 0 1 0 0 1 
 1 1 0 1 1 1 0 1 
 1 1 0 1 1 1 1 1 
 1 0 0 0 1 0 0 1 
 
 Number of Edges: 40
 
 Is it reachable?  Enter name of start city (-1 to exit): pueblo
                 Enter name of end city: pensacola
 false
 
 Is it reachable?  Enter name of start city (-1 to exit): princeton
                 Enter name of end city: pendleton
 true
 
 Is it reachable?  Enter name of start city (-1 to exit): -1
 
 ================== EXTENSION ==================
 List of every city's reachable cities: 
 PENDLETON--> [PENDLETON, PIERRE, PUEBLO]
 PENSACOLA--> [PENDLETON, PENSACOLA, PHOENIX, PIERRE, PITTSBURGH, PUEBLO]
 PEORIA--> [PENDLETON, PENSACOLA, PHOENIX, PIERRE, PITTSBURGH, PUEBLO]
 PHOENIX--> [PENDLETON, PENSACOLA, PHOENIX, PIERRE, PITTSBURGH, PUEBLO]
 PIERRE--> [PENDLETON, PIERRE, PUEBLO]
 PITTSBURGH--> [PENDLETON, PENSACOLA, PHOENIX, PIERRE, PITTSBURGH, PUEBLO]
 PRINCETON--> [PENDLETON, PENSACOLA, PHOENIX, PIERRE, PITTSBURGH, PRINCETON, PUEBLO]
 PUEBLO--> [PENDLETON, PIERRE, PUEBLO]
 
 List the reachable cities from: pueblo
 [PENDLETON, PIERRE, PUEBLO]
 
 List the reachable cities from: pIerre
 [PENDLETON, PIERRE, PUEBLO]
 
 List the reachable cities from: phoenix
 [PENDLETON, PENSACOLA, PHOENIX, PIERRE, PITTSBURGH, PUEBLO]
 
 List the reachable cities from: -1
 
  ----jGRASP: operation complete.
 **************************************************/