/*****************************************************************************************************************
NAME: William Curtis  
PERIOD: 7 
DUE DATE: 2/14/23

PURPOSE: Learn how a Binary Search Tree (BST) operates and learn how to write methods that work on a BST.   

WHAT I LEARNED: I learned that helper methods can be very very helpful when dealing with recursive methods, and
that I need to use them sometimes due to the need for a parameter.

HOW I FEEL ABOUT THIS LAB: I feel as if this lab has allowed me a new way of thinking, since before this I have always 
dealt with linear structures, and now dealing with a non linear structure has allowed me a new way of thinking.
            
CREDITS (BE SPECIFIC: FRIENDS, PEERS, ONLINE WEBSITE): None

****************************************************************************************************************/
import java.util.Scanner;
	/****************************************************************
	Practice with a Binary Search Tree. Uses TreeNode.
    Prompt the user for an input string.  Build a Binary Search Tree 
	using Comparables.  Display it as a sideways tree (take the code 
	from the Tree Lab).  Prompt the user for a target and search the tree 
	for it.  Display the tree's minimum and maximum values.  Print 
	the data in order from smallest to largest.
	*****************************************************************/
public class Pd7WilliamCurtisBinarySearchTree
{
   public static void main(String[] args)
   {   
      BinarySearchTree <String> t = new BinarySearchTree <> ();
      
      // build the tree
      Scanner sc = new Scanner(System.in);
      System.out.print("Input string: ");
      String s = sc.next();               // "MAENIRAC";  "AMERICAN";   "AACEIMNR"
      for(int k = 0; k < s.length(); k++)
         t.insert ("" + s.charAt (k));
         
         
      // get the root of the newly created BinarySearchTree 
      TreeNode <String> root = t.getRoot();
      
      // call the display sideway method
      t.display();    
      
      // test the find method
      sc = new Scanner(System.in);
      System.out.print("Input target: ");
      String target =  sc.next();             //"I"
      
      boolean itemFound = t.find(target);
      if(itemFound)
         System.out.println("found: " + target);
      else
         System.out.println(target +" not found.");
        
      // test the min and max methods
      System.out.println("Min = " + t.min());
      System.out.println("Max = " + t.max());	
   
      // inorder traversal display the values from smallest to largest
      System.out.println("\nIn Order: ");
      t.smallToLarge();     
   }
}
class BinarySearchTree <E extends Comparable>
{ 
   private TreeNode <E> root;
    
   public TreeNode <E> getRoot()
   {
      return root;
   }

   //precondition: none
   //postcondition: inserts the value s into the binary tree iteravely w/ helper method
   public TreeNode <E> insert(E s)
   {  	
      iterativeInsert(s);
      return this.root;
   }

   //precondition: none
   //postcondition: inserts the value s into the binary tree iteravely
   private TreeNode <E> iterativeInsert(E s)
   {
      //if empty then just returns a one node tree
      if(root == null) {
         root = new TreeNode <E> (s, null, null);
         return root;
      }
   
      TreeNode <E> p = null; //parent node
      TreeNode <E> q = root; //current node
      TreeNode <E> node = new TreeNode <E> (s, null, null); //newly created node to be inserted
      
      //check is used to hold the comapreTo value, which is uncallable 
      //after q becomes null, this is then called when finally inserting
      //the method
      int check = 0;
      
      //pushes q to bottom of tree and p to one above q
      while(q != null)  {
         p = q;
         check = s.compareTo(q.getValue());
         if(0 >= check) {
            q = q.getLeft();
         } else {
            q = q.getRight();
         }
      }    
   
      //connects the last node p pointer to new node depending on value
      if(0 >= check)           
         p.setLeft(node);
      else          
         p.setRight(node);
      
      //returns new node         
      return node;
        
   } // insert
   
   /*
   //recursive implmentation of insert (not used in the program)
   public TreeNode<E> insert (TreeNode <E> t, Comparable item)
   {  
      if(t == null)
         return new TreeNode<E> (item, null, null);
      else if(item.compareTo(t.getValue())
         return insert(t.getRight());
      else 
         return insert(t.getLeft());
      return new TreeNode <E> (item, null, null);
   }
   */

   //precondition: none
    //postcondition: display the tree sideway
   public void display ()
   {
      display (root, 0);
   }
   
   // display helper method (same pre and post conditions)
   private void display(TreeNode <E> t, int level)
   {
      if (t == null) 
         return;
      display(t.getRight(), 1 + level);
   
      int count = 0;
      while(count < level) {
         System.out.print("  ");
         count++;
         //this system adds 4 spaces before every "level"
         //this ensures each level is evenly spaced
         //while loop loops through all values on given level
      }
      
      System.out.println(t.getValue());
      display(t.getLeft(), 1 + level);
      
      //starts at the right tree because it would be displayed at the top
      // when displayed sideways, and then moves down to the left recursively
   } //display
 
   //precondition: none
   //postcondition: finds the value x if in root, with a helper method
   public  boolean find(E x)
   {
      return find (root, x);
   }
   
   //precondition: none
   //postcondition: finds the value x if in root, with a helper method, does recursively
   private boolean find (TreeNode <E> t, E x)
   {
      if(t == null)
         return false;
         
      if(t.getValue().compareTo(x) > 0)
         return find(t.getLeft(),x);
      else if (t.getValue().compareTo(x) < 0)
         return find(t.getRight(),x);
      
      return true;
   }
   
   /*
   //iterative implemtation of find (not used in the program)
   public boolean find(TreeNode <E> t, Comparable item)
   {
      TreeNode <E> p = t;
      while(p != null)
      {
         if(p.equals(item))
            return true;
         else if(p.compareTo(target > 0))
            p = p.getLeft();
         else
            p = p.getRight();
      }
      return false;
   }
   */
   
   //precondition: none
   //postcondition: iteravely returns the min value in a binary tree w/ a helper method
   public  E min() {
      return min(root);
   }
   
   //precondition: none
   //postcondition: iteravely returns the min value in a binary tree
   private E min(TreeNode <E> t)
   {
      if(t == null)
         return null;
         
      while(t.getLeft() != null)
         t = t.getLeft();
         
      return t.getValue();  
   }
 
    //precondition: none
    //postcondition: recursively returns the maximum value in a binary tree w/ a helper method
   public  E max()              
   {
      return max(root);
   }
   
   //precondition: none
   //postcondition: recursively returns the maximum value in a binary tree
   private E max(TreeNode <E> t)
   {
      if(t == null)
         return null;
      else if(t.getRight() == null)
         return t.getValue();
      else  
         return max(t.getRight());
   }
   
   //precondition: none
   //postcondition: does inorder traversal to print the method in order
   public  void smallToLarge()  
   {
      smallToLarge(root);
   }
   
   //precondition: none
   //postcondition: does inorder traversal to print the method in order
   private void smallToLarge(TreeNode<E> t)
   {
      if(t != null) {
         smallToLarge(t.getLeft());
         System.out.print(t.getValue() + " ");
         smallToLarge(t.getRight());
      }
   }
}  // BinarySearchTree

 /* TreeNode class for the AP Exams */

class TreeNode <E>
{
   private E value; 
   private TreeNode left, right;
   
   public TreeNode(E initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(E initValue, TreeNode <E> initLeft, TreeNode <E> initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public E getValue()
   { 
      return value; 
   }
   
   public TreeNode <E> getLeft() 
   { 
      return left; 
   }
   
   public TreeNode <E> getRight() 
   { 
      return right; 
   }
   
   public void setValue(E theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode <E> theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode <E> theNewRight)
   { 
      right = theNewRight;
   }
}
/*
Output:

     ----jGRASP exec: java Pd7WilliamCurtisBinarySearchTree
 Input string: american
     r
       n
   m
       i
     e
       c
 a
   a
 Input target: x
 x not found.
 Min = a
 Max = r
 
 In Order: 
 a a c e i m n r 
  ----jGRASP: operation complete.
  
     ----jGRASP exec: java Pd7WilliamCurtisBinarySearchTree
 Input string: MAENIRAC
     R
   N
 M
       I
     E
       C
   A
     A
 Input target: I
 found: I
 Min = A
 Max = R
 
 In Order: 
 A A C E I M N R 
  ----jGRASP: operation complete.
  */