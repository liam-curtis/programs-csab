/**
* Name: William Curtis              
* Period: 7
* 
* Purpose of the Program: Enrich our understanding of binary trees and how to operate and traverse them
* Date Submitted: 2/6/23
* 
* What I learned: I learned to treat binary trees in a similar fashion to linked lists, in a recursive manner.
  I also learned to take a break if a concept or method is making me hit a roadblock, which allows me to clear my 
  mind and come back with fresh perspectives
* 
* How I feel about this lab: I feel good about this lab, and I think binary trees are a great way to represent data, and am 
interested how we can use them in real life applications.
* 	
* What I wonder: I wonder how (as I said before) we can use binary trees in real life, and I also wonder if there were more effiecent ways
to write this code
* Credits: educative.io/answers/how-to-use-the-tostring-in-java
           https://www.geeksforgeeks.org/compare-two-strings-in-java/
           ^how to use tostring (for min and max methods)
* Students whom I helped (to what extent): None 
*/
import java.util.*;                     //for the queue interface
public class Pd7WilliamCurtisTreeLab       
{
   public static void main(String[] args)
   {
      String s = "XCOMPUTERSCIENCE";
   		
      TreeNode  root = new TreeNode  ("" + s.charAt(1), null, null);
         
      for(int index = 2; index < s.length(); index++)
         insert(root, "" + s.charAt(index), index, (int)(1 + Math.log(index) / Math.log(2)));
      
      // NOTE: The following 3 lines are supposed to show you how insert works. You
      //             uncomment them and see how the tree looks like with these 3 additional nodes
      insert(root, "A", 17, 5); 
      insert(root, "B", 18, 5); 
      insert(root, "C", 37, 6); //B's right child
      
      // display the tree sideway; see the sample output at the end of this file
      display(root, 0);
      
      System.out.print("\nPreorder: ");
      preorderTraverse(root);
   
      System.out.print("\nInorder: ");
      inorderTraverse(root);
   
      System.out.print("\nPostorder: ");
      postorderTraverse(root);
      
      System.out.println("\n\nNodes = " + countNodes(root));
      System.out.println("Leaves = " + countLeaves(root));
      System.out.println("Grandparents = " + countGrandParentNodes(root));  // count the number grandparent nodes
      System.out.println("Only childs = " + countSingleChildNodes(root));   // count the number of nodes that has only 1 child
         
      //System.out.println("\nDepth = " + numOfLevels(root));                    
      System.out.println("Height = " + height(root));
   
      System.out.println("Min = " + min(root));
      System.out.println("Max = " + max(root));	
      
      // level by level display of the nodes (starts from left to right for nodes on the same level)
      System.out.println("\nLevel-by-level display of the tree: ");
      displayLevelOrder(root);     
      
   } // end of main
   
   
   // insert a new node in the tree based on the node's level
   public static void insert(TreeNode t, String s, int pos, int level)
   {
      TreeNode p = t;
      for(int k = level - 2; k > 0; k--)
         if((pos & (1 << k)) == 0) 
            p = p.getLeft();      // What does this do? Answer this question first.  What does '&' do? 
         else                     // Google it!!!!  We did not learn this in APCS A!  :
            p = p.getRight();     // What does this do? Answer this question first. 
            
       //& is the bitwise and operator of java, which means it compares the corresponding bits of each operand,
       //if both bits are 1 the corresponding bit is set to 1.
       //the << takes the value on the left (1) and shifts the bits to the left by 'k' positions
            
      if((pos & 1) == 0)
         p.setLeft(new TreeNode(s, null, null));
      else
         p.setRight(new TreeNode(s, null, null));
   } // end of insert
      
    //precondition: none
    //postcondition: display the tree sideway   
   public static void display(TreeNode  t, int level)
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
   }  
   
   //precondition: none
   //postcondition: prints out the tree in a preorder fashion
   public static void preorderTraverse(TreeNode  t)
   {
      if(t != null)  {
         System.out.print(t.getValue() + " ");
         preorderTraverse(t.getLeft());
         preorderTraverse(t.getRight());
      }// C L R
   }
   
   //precondition: none
   //postcondition: prints out the tree in a inorder fashion
   public static void inorderTraverse(TreeNode t)
   {
      if(t != null)  {
         inorderTraverse(t.getLeft());
         System.out.print(t.getValue() + " ");
         inorderTraverse(t.getRight());
      }// L C R    
   }
   
   //precondition: none
   //postcondition: prints out the tree in a postorder fashion
   public static void postorderTraverse(TreeNode  t)
   {
      if(t != null)  {
         postorderTraverse(t.getLeft());
         postorderTraverse(t.getRight());
         System.out.print(t.getValue() + " ");
      }// L R C  
   }
   
   //precondition: none
   //postcondition: returns the total amount of nodes in binary tree t
   public static int countNodes(TreeNode  t)
   {
      if(t == null)
         return 0;
      else {
         //recursively goes down left and right side of tree and increments every
         //time a new node is reached
         return 1 + countNodes(t.getLeft()) + countNodes(t.getRight());
      }
   }
    
   //precondition: none
   //postcondition: returns the number of leaves (node with no children) in binary tree t 
   public static int countLeaves(TreeNode  t)
   {
      if(t != null && t.getRight() == null && t.getLeft() == null) {
         return 1; 
         //if isn't null and has no children returns 1
      } else if(t != null) {
         return countLeaves(t.getLeft()) + countLeaves(t.getRight());
         //continues to check lower if t isn't null
      } else {
         return 0;
      }
   }
   
   //precondition: none
   //postcondition: returns the number of grandparents in a binary tree
   public static int countGrandParentNodes(TreeNode  t)
   {
      if(t == null)
         return 0;
      if(t.getRight() != null) {//inital test to see if has right child
         if(t.getRight().getLeft() != null || t.getRight().getRight() != null)//if that child has a child then increments by 1
            return 1 + countGrandParentNodes(t.getLeft()) + countGrandParentNodes(t.getRight());
      } else if(t.getLeft() != null) {//inital test to see if has left child
         if(t.getLeft().getLeft() != null || t.getLeft().getRight() != null)//if that child has a child then increments by 1
            return 1 + countGrandParentNodes(t.getLeft()) + countGrandParentNodes(t.getRight());
      }
      return countGrandParentNodes(t.getLeft()) + countGrandParentNodes(t.getRight());
   }
   
   //precondition: none
   //postcondition: returns the # of nodes with only one child
   public static int countSingleChildNodes(TreeNode  t)
   {
      if(t == null) 
         return 0;
      else if(t.getLeft() == null && t.getRight() != null)
         return 1 + countSingleChildNodes(t.getRight()) ;
      else if (t.getLeft() != null && t.getRight() == null)
         return 1 + countSingleChildNodes(t.getLeft());
      else
         return countSingleChildNodes(t.getLeft()) + countSingleChildNodes(t.getRight());
      //checks to see if parent node has only one child, and if so goes to that child and 
      //increments, otherwise returns 0 OR goes deeper into both children (but doesn't increment)
   }
   

    //precondition: none
   //postcondition: returns the height of tree t  
   public static int height(TreeNode t)
   {
      if(t == null)
         return -1;
      else {
         int left = height(t.getLeft());
         int right = height(t.getRight());
         
         if (left > right)
            return left + 1;
         else
            return right + 1; 
      }
      //goes recursively down each left and right subtree until 
      //biggest height is found (by comparing heights of left and 
      //right subtree)
   }
    
    //precondition: original t is not null
   //postcondition: returns the smallest value in tree t
   public static  Object min(TreeNode  t)
   {
      if (t == null)//base case 
         return "Z";
      
      //creation of 3 Strings that will be compared 
      //while going recursively through the tree 
      String m = t.getValue().toString();
      String left = min(t.getLeft()).toString();
      String right = min(t.getRight()).toString();
   
      if (left.compareTo(right) < 0) {
         if (left.compareTo(m) > 0)
            return m;
         else
            return left;
      } else {
         if (right.compareTo(m) > 0)
            return m;
         else
            return right;
      } //compares the two values such that the smallest is always returned
      
   }
    
    //precondition: original t is not null
   //postcondition: returns the largest value in tree t
   public static Object max(TreeNode  t)
   {
      if (t == null)//base case
         return "";
      
      //creation of 3 Strings that will be compared 
      //while going recursively through the tree 
      String m = t.getValue().toString();
      String left = max(t.getLeft()).toString();
      String right = max(t.getRight()).toString();
   
      if (left.compareTo(right) > 0) {
         if (left.compareTo(m) < 0) 
            return m;
         else 
            return left;
      } else {
         if (right.compareTo(m) < 0) 
            return m;
         else 
            return right;
      } //oppisite logic of min
   }
   
   //precondition: none 
   //postcondition: displays the level by level order of binary tree
   public static void displayLevelOrder(TreeNode t)
   {
      Queue <TreeNode> level = new LinkedList <TreeNode> (); //creates a queue 
      level.add(t); //adds root to queue
      
      while(!level.isEmpty()) {
         TreeNode i = level.remove(); //removes bottom thing from queue 
         System.out.print(i.getValue() + " ");
         if(i.getLeft() != null)
            level.add(i.getLeft());//adds left branch to queue
         if(i.getRight() != null)
            level.add(i.getRight());//adds right branch to queue
      }
   }
   
   public static boolean desc(TreeNode t, TreeNode d)
   {
      if(t == null)
         return false;
      if(d.getValue().equals(t.getValue()))
         return true;
      else
         return desc(t.getLeft(),d) || desc(t.getRight(),d);
   
   }
}  // end of TreeLab_shell



class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}


   /***************************************************
	  Sample output: (not mine)
      ----jGRASP exec: java Lab01
    
    			E
    		E
    			C
    	M
    			N
    		T
    			E
    C
    			I
    		U
    			C
    	O
    			S
    					C
    				B
    		P
    				A
    			R
    
    Preorder: C O P R A S B C U C I M T E N E C E 
    Inorder: R A P B C S O C U I C E T N M C E E 
    Postorder: A R C B S P C I U O E N T C E E M C 
    
    Nodes = 18
    Leaves = 8
    Grandparents = 5
    Only childs = 3
    
    Height = 5
    Min = A
    Max = U
    
    Level-by-level: 
    COMPUTERSCIENCEABC
    
    My output: 
    
        ----jGRASP exec: java Pd7WilliamCurtisTreeLab
       E
     E
       C
   M
       N
     T
       E
 C
       I
     U
       C
   O
       S
           C
         B
     P
         A
       R
 
 Preorder: C O P R A S B C U C I M T E N E C E 
 Inorder: R A P B C S O C U I C E T N M C E E 
 Postorder: A R C B S P C I U O E N T C E E M C 
 
 Nodes = 18
 Leaves = 8
 Grandparents = 5
 Only childs = 3
 Height = 5
 Min = A
 Max = U
 
 Level-by-level display of the tree: 
 C O M P U T E R S C I E N C E A B C 
  ----jGRASP: operation complete.
        *******************************************************/