/***********************************************************************************************************************************************
 * Name: William Curtis
 * Period: 7
 * Name of the Lab: AreaFill
 * Purpose of the Program: Purpose of this program is when given a table of values and given a specific coordinate, is to take the coordinate 
                           and replace the character at the given coordinate with a *, this will then spread up, down, left and right
                           to neighboring coordinates with the same character as the original coordinate until there are no more coordinates 
                           to be filled.
 * Due Date: 10/2/22
 * Date Submitted: 10/2/22
 * What I learned: 
 * 1. I learned that you can sometimes include an if statement outlining required conditions (such as out of bounds of an array) to make the 
      programming more simmple/concise.
 * 2. I also learned that the base case doesn't always have to make something happen, and sometimes when the base case is met nothing will
      happen.
 * How I feel about this lab: 
 * I feel that this lab was pretty simple, and once I found the base case the only problem that stumped me for some time was how to deal with
      was when the fill method reached the bounds/edges of the table.
 * What I wonder: 
 * One thing that I wonder is how we will deal with having the trace back in the maze lab, as it will add complexity to the problem of recursion.
 * Student(s) who helped me (to what extent): none
 * Student(s) whom I helped (to what extent): none
 *************************************************************************************************************************************************/

   
import java.util.Scanner;
import java.io.*;
public class Pd7WilliamCurtisAreaFill
{
   public static char[][] grid = null;
   
   public static void main(String[] args) throws FileNotFoundException
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Filename: ");
      String filename = sc.next();
      grid = read(filename + ".txt");
      display(grid);
      System.out.print("\nEnter ROW COL to fill from: ");
      int row = sc.nextInt();
      int col = sc.nextInt(); 
      fill(grid, row, col, grid[row][col]);
      display(grid);
      sc.close();
   }
   public static char[][] read(String filename)throws FileNotFoundException
   {
      Scanner sc = new Scanner(new File(filename));
      char[][] board = new char[sc.nextInt()][sc.nextInt()];
      for(int i = 0; i < board.length; i++) {
         String row = sc.next();
         board[i] = row.toCharArray();
      }
      return board;
   }
   
   public static void display(char[][] g)
   {              
      for(int i = 0; i < g.length; i++) {
         for(int j = 0; j < g[i].length; j++) {
            System.out.print(g[i][j]);
         }
         System.out.println();
      }
   }
   
          /**
        * pre: method called in main method
        * post: modifies char[][] g and replaces characters of the 
        * index indicated on the grid with a *
        * @param g
        * @param r
        * @param c
        * @param ch
        */

   public static void fill(char[][] g, int r, int c, char ch) //recursive method 
   {  
      if ((r >= 0 && r < g.length)&&(c >= 0 && c < g[0].length))  {
         if (g[r][c] != ch)  {
         }  else  {
            g[r][c] = '*';
            fill(g,r,c-1,ch);
            fill(g,r,c+1,ch);
            fill(g,r-1,c,ch);
            fill(g,r+1,c,ch);
         }
      }
   }// fill

}