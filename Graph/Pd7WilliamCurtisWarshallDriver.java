//uses AdjMat
import java.util.*;
import java.io.*;
public class Pd7WilliamCurtisWarshallDriver
{
   public static void main(String[] args)throws FileNotFoundException
   {
      Scanner kb = new Scanner(System.in);
      System.out.print("Warshall's Algorithm! Enter file of names: "); 
   											                    //cities
      String fileNames = kb.next()+".txt";
      Scanner sc = new Scanner(new File(fileNames));
      int size = sc.nextInt();
      AdjMat g = new AdjMat(size);
      g.readNames(fileNames);
      System.out.print("Enter file of the matrix: "); //citymatrix
      String fileGrid = kb.next()+".txt";
      g.readGrid(fileGrid);
      System.out.println("Adjacency Matrix");
      System.out.println(g);
      System.out.println("Number of Edges: " + g.edgeCount());
      System.out.println();
   
      g.allPairsReachability();    //runs Warshall's algorithm
      g.displayVertices();
      System.out.println("Reachability Matrix");
      System.out.println(g);
      System.out.println("Number of Edges: " + g.edgeCount());
   
      while(true)
      {
         System.out.print("\nIs it reachable?  Enter name of start city (-1 to exit): ");
         String from = kb.next().trim();
         if(from.equals("-1"))
            break;
         System.out.print("                Enter name of end city: "); 
         String to = kb.next().trim();  
         System.out.println( g.isEdge(from, to) );
      }
      
      //Extension
      System.out.println("\n================== EXTENSION =================="); 
      System.out.println("List of every city's reachable cities: ");
      for(String city : g.getVertices().keySet() )
         System.out.println(city + "--> " + g.getReachables(city) );
         
      while(true)
      {
         System.out.print("\nList the reachable cities from: ");
         String from = kb.next();
         if(from.equals("-1"))
            break;
         System.out.println(g.getReachables(from));
      }
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