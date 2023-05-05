/***********************************************************************
Name: William Curtis
Period: 7
Date: 1/12/23
What I Learned: I learned how to use stacks to hold an existing value, and 
to combine the values inside the stack when parsing through a string, which
is helpful because it allows me to operate on the string as it is being parsed.        
Credit (person who helped me): None
Student(s) whom I helped (to what extent): None
************************************************************************/    
import java.util.*;
public class Pd7WilliamCurtisPostfix
{
   public static void main(String[] args)
   {
      System.out.println("Enter a valid postfix expression (single digits only),");
      System.out.println("such as 35*1+");
      Scanner keyboard = new Scanner(System.in);
      String s = keyboard.next();  
      while(!s.equals("-1"))
      { 
         System.out.println(s + "  --->  " + eval(s) + "\n");
      // //        System.out.println((s = "354*+7*") + " = " + eval(s) + "\n");
      // //          System.out.println((s = "82-") + " = " + eval(s) + "\n");
      // //          System.out.println((s = "82/") + " = " + eval(s) + "\n");
         s = keyboard.next();
      }
   }
   //precondition: x is not empty, is in post-fix style, and only contains 
   //single digit integers and operators.
   //postcondition: Returns the evaluated equation of the postfix expression.
   public static int eval(String x) 
   {
      Stack <Integer> bin = new Stack <> (); //initializes a stack of integers
      for(int t = 0; t < x.length(); t++) //loops through whole string x
      {
         if(!isOperator(x.charAt(t)))//if isn't operator add the number to the stack
            bin.push(Integer.parseInt(x.substring(t,t+1)));
         else //if operator combines two top int's in bin, and evaluates with current operator,
              //then adds result back to the stack.
            bin.push(eval(bin.pop(),bin.pop(),x.charAt(t)));
      }
      return bin.pop();
   }
   //precondition: a, b, ch are not empty.
   //postcondition: Returns the value of a (the operator ch) b together.
   public static int eval(int a, int b, char ch)
   {
      if(ch == '/')
         return b / a; 
      else if(ch == '+')
         return b + a;
      else if(ch == '*')
         return b * a;
      else
         return b - a;             
   } //returns an equation with the a and b operands, and checks which operator 
     // to use with if statments.
     
   //precondition: ch is not empty.
   //postcondition: Returns if the character is an operator.
   public static boolean isOperator(char ch)
   {
      return ch == '/' || ch == '+' || ch == '-' || ch == '*';      
   }
}
/*
Output:

----jGRASP exec: java Pd7WilliamCurtisPostfix
 Enter a valid postfix expression (single digits only),
 such as 35*1+
 345*+
 345*+  --->  23
 
 34*5+
 34*5+  --->  17
 
 45+53*-
 45+53*-  --->  -6
 
 34+56+*
 34+56+*  --->  77
 
 345+*2-5/
 345+*2-5/  --->  5
 
 812*+93/-
 812*+93/-  --->  7
 
 -1
 
  ----jGRASP: operation complete.

*/
