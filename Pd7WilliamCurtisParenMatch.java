/***********************************************************************
Name: William Curtis      
Period: 7 
Date: 1/11/23
What I Learned:            
Credit (person who helped me): None
Student(s) whom I helped (to what extent): None
************************************************************************/   
import java.util.*;
public class Pd7WilliamCurtisParenMatch
{
   public static final String left  = "([{<";
   public static final String right = ")]}>";
   
   public static void main(String[] args)
   {
   
      System.out.println("Enter an expression with grouping symbols,");
      System.out.println("such as (2+3)-[5*(6+1)]IndexMals");
      Scanner keyboard = new Scanner(System.in);
      String s = keyboard.next();  
      while(!s.equals("-1"))
      {
         boolean flag = check(s);
         if(flag)
            System.out.println(s + " is good.");
         else
            System.out.println("No, no, no.  Bad.  " + s);
         System.out.println();
         s = keyboard.next();
      }
   }
   // precondition:
   // postcondition: 
   public static boolean check(String s)
   {
      Stack <String> bin = new Stack<>();
      
      for(int t = 0; t < s.length(); t++) {
      
         if(left.contains(s.substring(t,t+1))) //checks for left bracket
            bin.push(s.substring(t,t+1));//adds to stack if left bracket is present
            
         if(right.contains(s.substring(t,t+1))) { //check for right bracket
         
            int pos = right.indexOf(s.substring(t,t+1));
            
            while(!left.substring(pos,pos+1).equals(bin.peek())) {
               bin.pop();
            }
         }
         
      }//NEED TO COUNTER EMPTY STACK EXCEPTION and method is wrong:(
      
      return bin.isEmpty();
   }
}
// Program outputs should include all the test cases described in the lab specifications
