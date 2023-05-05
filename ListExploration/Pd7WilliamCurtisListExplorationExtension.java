/*********************************************************************************
NAME: William Curtis 
PERIOD: 7
DATE SUBMITTED: 11/14/22
DUE DATE: 11/16/22
ASSIGNMENT: List Exploration Extension

PURPOSE OF THE LAB: 
Write various methods that deal with Linked Lists and discover ways to manipulate 
the data within a Linked List.

MISTAKES MADE:
- While looping through a linked list I used the val.setNext(val.getNext) to loop
  instead of doing val = val.getNext(), which resulted in a run time error where
  the loop was never exited.
- Multiple times I returned a pointer that I manipulated in the method instead of 
  the original pointer (usually head). This results in an incomplete list as the 
  new pointer does not count the rest of the list.


NEW CONCEPTS LEARNED:
- I learned how to use for-loops and while loops to loop through a linked list, and
  how to use recursive methods to build linked lists.
- I learned how to test for empty lists at the beginning of my methods, and what to 
  return if there is a null value.

HOW I FEEL ABOUT THIS LAB:
- I feel good about this lab, and that my understanding of linked lists is thorough
  without much doubt.
- If I continue to rewrite these methods until I can do them perfect every time I 
  feel as if I will perform well on the assessment.

CREDITS: https://www.geeksforgeeks.org/applications-advantages-and-disadvantages-of-linked-list/?ref=lbp

STUDENTS WHOM I HELPED: none
*/
import java.util.*;
public class Pd7WilliamCurtisListExplorationExtension
{
   public static void main(String[] args)
   {
      
      ListNode <String> head = new ListNode <>("hello", null);
      
      head = new ListNode <>("foo", head);
      head = new ListNode <>("boo", head);
      head = new ListNode <>("nonsense", head);
      head = new ListNode <>("computer",
         	 new ListNode <>("science",
         	 new ListNode <>("java",
         	 new ListNode <>("coffee", head))));
   
      print(head);
      
         
      /**** uncomment the code below for ListExploration extension  ****/
      
      System.out.println("First = " + first(head));
      System.out.println("Second = " + second(head));
      ListNode  <String> p = pointerToLast(head);
      System.out.println("Pointer to Last = " + p.getValue()+ " at " + p);
      
      ListNode  <String> c = copyOfLast(head);
      System.out.println("Copy of Last =    " + c.getValue()+ " at " + c);
   	 	
      Scanner in = new Scanner(System.in);
      System.out.print("Insert what? ");
      String x = in.next();
      head = insertFirst(head, x);
      head = insertLast(head, x);
      print(head);
   }

   //  precondition: a <String> type ListNode is entered as a parameter
   //  postcondition:  prints the linked list out in a [ __, __, __] format. If list is null [] is printed
   public static void print(ListNode  <String> head)
   {
      System.out.print("[");
      while(head != null)
      {
         System.out.print(head.getValue());
         head = head.getNext();
         if(head != null)
            System.out.print(", ");
      }
      System.out.println("]");
   } // print
            
   /*EXERCISE METHODS*/
   
   //precondition: none
   //postcondition: returns a new node that is a copy of the argument node, returns null if node is empty
   public static ListNode <String> copyNode(ListNode <String> arg)
   {
      if(arg == null)
         return null;
      else
         return new ListNode <String> (arg.getValue(),arg.getNext());
   }//copyNode
   
    //precondition: none 
    //postcondition: returns a new list that is a copy of the original list, returns null if list is empty
   public static ListNode <String> copyList(ListNode <String> arg)
   {		
      if (arg == null) 
         return null; //if there is no node, returns null
      else if (arg.getNext() == null) 
         return new ListNode <> (arg.getValue(), null); //base case, where it is the last node in the list
      else
         return new ListNode <> (arg.getValue(), copyList(arg.getNext())); //returns a new node with the following node in the line
   }//copyList
   
   //precondition: none
   //postcondition: returns a copy of each node in original list except the first node, returns null if node is empty
   public static ListNode <String> rest (ListNode <String> h)
   {
      if(h == null || h.getNext() == null) {
         return null; //checks if either the first or second node are null
      } else {
         ListNode <String> newList = new ListNode (h.getNext().getValue(), null); //copy of second node in original list
         for(ListNode temp = h.getNext(); temp.getNext() != null; temp = temp.getNext()) {
            //loops through a temp linked list with same value as h until end of list is reached
            newList.setNext(new ListNode (temp.getNext().getValue(), null)); 
         }
         return newList;
      }       
   }//rest, uses unsafe operations, but not sure how to fix or what is the problem
      
   /* LAB METHODS */
   
   //precondition: none
   //postcondition: returns the value of the first node, or null if the list is empty 
   public static String first(ListNode <String> head)
   {
      if(head.getValue() == null)
         return null;
      else
         return head.getValue(); //if the head is not null returns value of first node, otherwise null
   }//first
      
      //precondition: none
      //postcondition: returns the value of the second node, or null if the list is empty or if there is only one node.
   public static String second(ListNode <String>  head) 
   {
      ListNode <String> sec = rest(head); //uses the exercise method rest to create copy of head (minus first node)
      if(sec.getValue() == null)          //does same operations as first method with newly created list
         return null;
      else
         return sec.getValue();
   }//second
   
   //precondition: none
   //postcondition: returns a reference to the last node in the list, or null if the list is empty
   public static ListNode <String> pointerToLast (ListNode <String>  head) 
   {
      if(head.getValue() == null) {
         return null; //returns null if list is empty
      } else {
         while(head.getNext() != null) {
            head = head.getNext(); //loops through the head value until end of list is reached
         }
         return head; //returns pointer head, which is now at the end of the list
      }
   }//pointerToLast

   //precondition:
   //postcondition: returns a copy of the last node 
   public static ListNode <String> copyOfLast (ListNode <String> head) 
   {
      if(head.getValue() == null)
         return null;
      else if(head.getNext() == null) 
         return copyNode(head); //calls copyOfLast exercise method to create copy of the last node
      else
         return copyOfLast(head.getNext()); //moves through list by recursively calling itself
   }//copyOfLast

//precondition: none
//postcondition: inserts a new node infront of the first node, and returns the list with the new node as the starting point
   public static ListNode <String> insertFirst(ListNode <String> head,String arg) 
   {
      if(head.getValue() == null)
         return new ListNode <> (arg, null); //if list is empty returns a new node that is not linked to any other node
      else
         return new ListNode <> (arg, head); //otherwise returns a new node that is connected to the head of the list
   }//insertFirst                          

//precondition: none
//postcondition: inserts a new node at the end of the list, while returning the same list and head
   public static ListNode <String> insertLast(ListNode <String> head, String arg) 
   {
      if(head.getValue() == null){
         return new ListNode <> (arg, null); //if list is empty a new node is created that is not linked to anything
      } else {
         ListNode <String> temp = head; //new pointer that points to start of list
         while(temp.getNext() != null)
            temp = temp.getNext(); 
         temp.setNext(new ListNode <> (arg, null)); //appends arg to end of list
         return head; //returns original untamperered head of list
      }  
   }//insertLast
} // ListExplorationExtension_shell


class ListNode <E> 
{    
   private E value;    
   private ListNode <E>  next; 
   
   public ListNode  (E  initValue, ListNode <E> initNext)    
   { 
      value = initValue; 
      next = initNext; 
   }  
   public E getValue()  
   { 
      return value; 
   }    
  
   public ListNode <E> getNext() 
   { 
      return next;  
   } 
   
   public void setValue(E theNewValue)
   { 
      value = theNewValue;
   }
   
   public void setNext(ListNode  <E> theNewNext)
   { 
      next = theNewNext; 
   }
 
}  // end of ListNode

/*
Program output

  ----jGRASP exec: java Pd7WilliamCurtisListExplorationExtension
 [computer, science, java, coffee, nonsense, boo, foo, hello]
 First = computer
 Second = science
 Pointer to Last = hello at ListNode@77459877
 Copy of Last =    hello at ListNode@5b2133b1
 Insert what? what?
 [what?, computer, science, java, coffee, nonsense, boo, foo, hello, what?]
 
  ----jGRASP: operation complete.   
  
 ----jGRASP exec: java Pd7WilliamCurtisListExplorationExtension
 [computer, science, java, coffee, nonsense, boo, foo, hello]
 First = computer
 Second = science
 Pointer to Last = hello at ListNode@77459877
 Copy of Last =    hello at ListNode@5b2133b1
 Insert what? mrlau
 [mrlau, computer, science, java, coffee, nonsense, boo, foo, hello, mrlau]
 
  ----jGRASP: operation complete.
  
*/