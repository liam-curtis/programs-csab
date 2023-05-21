/******
Name: William Curtis
Period: 7 
Name of the Lab: Mini Facebook Extension
Purpose of the Program:
The program works/partially work/does not compile/has run time errors and where ...
What I learned (in bullet form):
How I feel about this lab:
What I wonder:
*****/
import java.io.*;
import java.util.*;

public class Pd7WilliamCurtisMicroFBDBExtension 
{
   public static void main(String [] args) throws FileNotFoundException
   {
      //EXTENSION
      
      //instantiate a new facebook class
      MicroFB fb = new MicroFB();
      
      PrintStream output = new PrintStream(new File("Pd7WilliamCurtisPerson.txt")); //create new file with print stream
      Scanner console = new Scanner(System.in); //create scanner to ask how many names should be created
      
      System.out.println("How many names would you like to create?");
      int numberOfNames = console.nextInt();
      Map<String, Boolean> namesList = new HashMap<String, Boolean>();
      
      //create 100 random names
      for(int i = 0; i < numberOfNames; i++)
      {
         String name = randomName(namesList);
         namesList.put(name, true); //put the name is a list, so there will be no repeats (there are 26^5 combinations! that's 308,915,776!)
         output.println("P " + name); 
         System.out.println("P " + name); //print the names
         fb.P(name);
      }
      
      //ORIG
   
      //nameOne and nameTwo represent the the names that the commands will execute
      String nameOne = "";
      String nameTwo = "";
      
      //line processing, instantiate a scanner and a exit variable
      boolean exit = false;
      
      //run a while loop that runs until program calls method X()
      while(!exit)
      {
         String line = console.nextLine(); //obtain the next line
         if (line.isEmpty())
            continue; // Skip the iteration if the line is empty
         String command = line.substring(0,1).toUpperCase(); //obtain the next command, converting to upper case if necessary
       
         //filter through all possible methods (P F U L Q X)
         if(command.equals("P")) {
            if(lineValid(line, 1) != -1) { //check to see if line is properly formatted
               nameOne = parseLineSingle(line, 2);
               fb.P(nameOne);
            } else
               System.out.println("Please enter a valid command");
         } else if(command.equals("F")) {
            if(lineValid(line, 2) != -1) { //check to see if line is properly formatted
               String[] container = parseLineDouble(line, 2, lineValid(line, 2));
               nameOne = container[0];  
               nameTwo = container[1];
               fb.F(nameOne,nameTwo);
            } else
               System.out.println("Please enter a valid command");
         } else if(command.equals("U")) {
            if(lineValid(line, 2) != -1) { //check to see if line is properly formatted
               String[] container = parseLineDouble(line, 2, lineValid(line, 2));
               nameOne = container[0];  
               nameTwo = container[1];
               fb.U(nameOne,nameTwo);
            } else
               System.out.println("Please enter a valid command");
         } else if(command.equals("L")) {
            if(lineValid(line, 1) != -1) { //check to see if line is properly formatted
               nameOne = parseLineSingle(line, 2);
               fb.L(nameOne);
            } else
               System.out.println("Please enter a valid command");
         } else if(command.equals("Q")) {
            if(lineValid(line, 2) != -1) { //check to see if line is properly formatted
               String[] container = parseLineDouble(line, 2, lineValid(line, 2));
               nameOne = container[0];  
               nameTwo = container[1];
               fb.Q(nameOne,nameTwo);
            } else
               System.out.println("Please enter a valid command");
         } else if(command.equals("X")) {
            exit = true; //terminates the program
         } else {
            System.out.println("Please enter a valid command");
         }
      }
   } //while
   
   //preconditions: names have alphabetic letters only
   //postconditions: returns the line # of the string for parsing, -1 if invalid
   public static int lineValid(String line, int format) {
      if (format == 1 && line.length() > 2 && line.substring(1,2).equals(" ")) //check for single input
         return 2; //doesn't matter, will always start at two
      else if(format == 2 && line.length() > 5) { //check for double input
         int lengthBetween = 0; //length between spaces
         for(int i = 2; i < line.length(); i++) //loop through characters in line, starting after the command
         {
            if (line.substring(i,i+1).equals(" ") && lengthBetween > 0)
               return i;
            else
               lengthBetween++;
         }
      }
      return -1;
   } //lineValid
   
   //preconditions: command is in a valid format
   //postconditions: returns the name of the first friend
   public static String parseLineSingle(String line, int start) {
      return line.substring(start);
   } //parseLineSingle
   
   //preconditions: command is in a valid format
   //postconditions: returns the names in a String format in a 1D-array of strings
   public static String[] parseLineDouble(String line, int startFirst, int startSecond) {
      String[] values = new String[2];
      values[0] = line.substring(startFirst, startSecond);
      values[1] = line.substring(startSecond+1);
      return values;
   } //parseLineDouble
   
   
   public static String randomName(Map<String, Boolean> namesList) {
      String[] letters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
      String username = "";
      for(int i = 0; i < 5; i++)
         username += letters[(int)Math.floor(Math.random() * (25 - 0 + 1) + 0)];
      //check for duplicates
      if(namesList.containsKey(username)) 
      {  
         username = randomName(namesList); //if duplicate recur until a non-repeat is found
      }
      return username;
   }
}