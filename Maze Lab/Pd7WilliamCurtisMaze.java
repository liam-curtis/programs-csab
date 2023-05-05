/*********************************************************************************
NAME:
PERIOD: 
DATE SUBMITTED: 
DUE DATE: 
ASSIGNMENT:

PURPOSE OF THE LAB:


MISTAKES MADE:


NEW CONCEPTS LEARNED:
HOW I FEEL ABOUT THIS LAB: 
CREDITS: 

STUDENTS WHOM I HELPED: 
**********************************************************************************/

import java.util.*;
import java.io.*;
public class Pd7WilliamCurtisMaze
{
   public static void main(String[] args) throws FileNotFoundException
   {
      Scanner sc = new Scanner(System.in);
      char[][] retArr;
      Maze m ;
   
      System.out.println();
      
      for (;;)
      {
         System.out.print("\nEnter the maze's filename (file extension not needed): ");
         retArr = buildCharArr(sc.next());
         m = new Maze(retArr);
         System.out.println ("Maze: ");
         m.display();
         
         System.out.println("\nWhat do you want to do (choose 1, 2, or 3):");
         System.out.println("   1: Mark only the correct path.");
         System.out.println("   2: Mark only the correct path, and display the count of STEPs.");
         System.out.println("   3: Exit");
      
         m.solve(sc.nextInt());
         
         m.display();  
      } // for
   } // main
   
   // postcondition: take in a filename (without .txt), and return a char[][]
   public static char[][] buildCharArr(String fileName) throws FileNotFoundException
   {
      File f = new File(fileName);
      Scanner inFile = new Scanner (f);
      int rowSize = inFile.nextInt();
      int colSize = inFile.nextInt();
      inFile.nextLine();
      System.out.println("rowSize: " + rowSize);
      System.out.println("colSize: " + colSize);
      char[][] m = new char [rowSize][colSize];
      int r = 0;
      
      while(inFile.hasNext()) 
      {
         String s = inFile.nextLine();
         
         for(int c = 0; c < colSize; c++)
         {
            m[r][c] = s.charAt(c);
         }
         r++;
      }
      return m;
   }  // buildCharArr
   
   private static void transfer2DGridToFile (char [][] m) throws FileNotFoundException
   {
      System.out.print ("Enter the name of the output file? \nUse 'txt' as the file extension: ");
      Scanner input = new Scanner (System.in);
      String name = input.next();
      File outputFile = new File (name);
      if (outputFile.exists())
      {
         System.out.println (name + "already exists");
         System.exit(1);
      }
      
      PrintWriter pw = new PrintWriter (outputFile);  
      
      System.out.println ("Enter the dimensions (row and column) of the random maze (separated the numbers with a space): ");
      pw.println(input.next() + " " + input.next());
   
      // transfer the 2D grid to the .txt text file
      for (char [] row : m)
      {
         pw.println (row);
      } 
      pw.close();
   
   } // transfer2DGridToFile

}  // MazeDriver


class Maze
{
   //Constants
   private final char WALL = 'W';
   private final char DOT = '.';
   private final char START = 'S';
   private final char EXIT = 'E';
   private final char STEP = '*';
   //fields
   private char[][] maze;
   private int startRow, startCol;  // store where the start location is
   private boolean S_Exists=false, E_Exists=false;
   
   /** Initializes all the field of a Maze object: maze, startRow, startCol
   */
   public Maze(char[][] inCharArr)    
   {
      maze = inCharArr;
      for (int i = 0; i < maze.length; i++) {
         for (int j = 0; j < maze[0].length; j++) {
            if (maze[i][j] == 'S') {
               startRow = i;
               startCol = j;
               S_Exists = true;
            }
            if(maze[i][j] == EXIT) {
               E_Exists = true;
            }
         
         }
      } // finds location of 'S' in 2D array by looping through until correct character is found
   }  // Maze
   
   /**
   */
   public void display()
   {
      if(maze==null) 
         return;
      for(int a = 0; a<maze.length; a++)
      {
         for(int b = 0; b<maze[0].length; b++)
         {
            System.out.print(maze[a][b]);
         }
         System.out.println("");
      }
      System.out.println("");
   }  // display
   /**
   */
   public void solve(int n)
   {
      final int FIND_PATH = 1;
      final int FIND_PATH_AND_COUNT_PATH_LENGTH = 2;
      final int QUIT = 3;
   
      if(n == FIND_PATH)
      {
         if (!markTheCorrectPath(startRow, startCol))
            System.out.println ("No Path found!");
      }
      else if(n == FIND_PATH_AND_COUNT_PATH_LENGTH)
      {
         if (!markCorrectPathAndCountStars(startRow, startCol, 0))
            System.out.println ("No Path found!");
      
      }
      else if (n == QUIT)
      {
         System.out.println("Goodbye!\n"); 
         System.exit (0);
      }
        
      else System.out.println("Invalid choice\n");
   }  // solve
   
   /**
     precondition:
     postcondition:
    */
   /*  Recur until you find E, then mark the True path */
   private boolean markTheCorrectPath(int r, int c)
   {
      if( S_Exists && E_Exists && r < maze.length && r >= 0 && c < maze[0].length && c >= 0) {
         if(maze[r][c] == DOT) {
            maze[r][c] = 'o';
            if(markTheCorrectPath(r+1,c)||markTheCorrectPath(r,c+1)||markTheCorrectPath(r-1,c)||markTheCorrectPath(r,c-1)) {
               maze[r][c] = STEP;
               return true;
            } else {
               maze[r][c] = DOT;
            }
         }
         else if(maze[r][c] == START) {
            return markTheCorrectPath(r+1,c)||markTheCorrectPath(r,c+1)||markTheCorrectPath(r-1,c)||markTheCorrectPath(r,c-1);
         }
         else if(maze[r][c] == EXIT) {
            return true;
         }
      }
      return false;
   } // markTheCorrectPath

   
   /**
     precondition:
     postcondition:
    */
   private boolean markCorrectPathAndCountStars(int r, int c, int count)
   {
      if( S_Exists && E_Exists && r < maze.length && r >= 0 && c < maze[0].length && c >= 0) {
         if(maze[r][c] == DOT) {
            maze[r][c] = 'o';
            if(markCorrectPathAndCountStars(r+1,c,count+1)||markCorrectPathAndCountStars(r,c+1,count+1)||markCorrectPathAndCountStars(r-1,c,count+1)||markCorrectPathAndCountStars(r,c-1,count+1)) {
               maze[r][c] = STEP;
               return true;
            } else {
               maze[r][c] = DOT;
            }
         }
         else if(maze[r][c] == EXIT){
            System.out.println("Number of steps: "+(count+1));
            return true;
         }
         else if(maze[r][c] == START) {
            return markCorrectPathAndCountStars(r+1,c,count)||markCorrectPathAndCountStars(r,c+1,count)||markCorrectPathAndCountStars(r-1,c,count)||markCorrectPathAndCountStars(r,c-1,count);
         }
      }
      return false;
   } // markCorrectPathAndCountStars
}
