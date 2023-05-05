/*
Name: William Curtis     
Period: 7            
Assignment: DCLL Lab

Purpose of the Lab: Construct the DCLL class, which utilizes a "dummy node" that makes it easier to insert and delete nodes, as it is a 
permenant empty node as the first node.

How do I approach the lab (my problem solving approach): My approach to this lab was to accomplish the to string method first, as it would 
allow me to see that I have the right format, after that I wrote out all of the code but did not compile, and only after writing all of the
code I compiled (and fixed any mistakes I made) then ran the code.

Successes: I was able to have a limited amount of compile time errors, which meant that I was able to go from coding everything to running the 
program very quickly.

Mistakes made (major compile-time errors and runtime errors):
1)
When changing the file name, I encountered an error where the name of the object being created in the driver class didn't match up
with the newly formatted filename, which was exasperated by the fact that I had the old filename inside the same folder, which allowed
the class to access it, so it made any changes I made to my code redundant as it called the empty methods.
2)
The second mistake that I made was not creating a temp pointer variable in my code, which sometimes resulted in the head moving, which is not 
as it created a new start to the list (the list is circular)

What I Learned: I learned how to construct a DCLL, and I better understand how to use a DLL's commands, and the importance of a dummy node and how 
much that can simplify things in your code.
 
How I feel about this lab: I feel like this lab is very similar to the mini-lab, with many of the methods doing the same thing, I do think that this lab
was a bit easier, as the dummy node really simplified things in the methods.

Where/Who I got help from (what help did you get?): None

Students I helped: None
*/
public class Pd7WilliamCurtisDCLL <E>
{
   private int size;
   //dummy node--very useful--simplifies the code
   private DLNode <E> head = new DLNode <E> ();    
   
   // pre: List is provided
   // post: return size
   public int size()
   {
      DLNode <E> t = head.getNext();
      int count = 0;
      while(t != head)
      {
         t = t.getNext();
         count++;
      }
      return count;
   }
   
   //precondition: list is provided
   //postcondition: adds obj at the end of list returns true
   public boolean add(E obj)
   {
      DLNode n = new DLNode(obj,head.getPrev(),head);
      head.getPrev().setNext(n);
      head.setPrev(n);
      return true;
   } // add
   
   //precondition: list is provided
   //postcondition: adds obj at specified index
   public void add(int index, E obj)
   {
      DLNode <E> temp = head;
      for(int i = 1; i < index; i++)
      {
         temp = temp.getNext();
      }
      DLNode <E> n = new DLNode(obj,temp,temp.getNext());
      temp.setNext(n);
      temp.getNext().getNext().setPrev(n);
   } // add
   
   //precondition: list is provided
   //postcondition: gets value at specified index
   public E get(int index)
   {
      DLNode <E> temp = head;
      for(int i = 0; i < index; i++)
      {
         temp = temp.getNext();
      }
      return temp.getValue();
   } // get
   
   //precondition: list is provided
   //postcondition: sets list to obj at specified node
   public void set(int index, E obj)
   {
      DLNode <E> temp = head;
      for(int i = 0; i < index; i++)
      {
         temp = temp.getNext();
      }
      temp.setValue(obj);
   } // set
   
   //precondition: list is provided
   //postcondition: removes the node from position index. decrements size. return the object at position index.
   public E remove(int index)
   {
      DLNode <E> temp = head;
      for(int i = 0; i < index; i++)
      {
         temp = temp.getNext();
      }
      temp.getNext().setPrev(temp.getPrev());
      temp.getPrev().setNext(temp.getNext());
      size--;
      return temp.getValue();
   } // remove
  
   //precondition: list is provided
   //postcondition: inserts obj at front of list; increases size
   public void addFirst(E obj)
   {
      head.setNext(new DLNode(obj,head,head.getNext()));
      head.getNext().getNext().setPrev(head.getNext());
      size++;
   } // addFirst
   
   //precondition: list is provided
   //postcondition: appends obj to end of list; increases size
   public void addLast(E obj)
   {
      DLNode n = new DLNode(obj,head.getPrev(),head);
      head.getPrev().setNext(n);
      head.setPrev(n);
      size++;
   
   } // addLast
      
   // precondition: List is provided
   // post: return first value
   public E getFirst()
   {
      return head.getNext().getValue();
   
   } // getFirst
     
   // precondition: List is provided
   // postcondition: return last value 
   public E getLast()
   {
      return head.getPrev().getValue();
   } // getLast
      
  // precondition: List is provided
  //  postcondition: remore first value and return that value decreases size
   public E removeFirst()
   {
      DLNode <E> temp = head.getNext();
      temp.getNext().setPrev(head);
      head.setNext(temp.getNext());
      size--;
      return temp.getValue(); 
   } // removeFirst
       
   // precondition: List is provided
   // postcondition: Remove last value and return that value decreases size
   public E removeLast()
   {
      DLNode <E> temp = head.getPrev();
      temp.getPrev().setNext(head);
      head.setPrev(temp.getPrev());
      size--;
      return temp.getValue(); 
   } // removeLast
      
  //precondition: List is provided
  //postcondition: Print out the list provided 
   public String toString()
   {
      DLNode <E> t = head.getNext();
      String list = "[";
      while(t != head)
      {
         list += t.getValue();
         if(t.getNext() != head)
            list += ", ";
            
         t = t.getNext();
      }
      return list + "]";
   } // toString

   public static void main(String args[])
   {
      Pd7WilliamCurtisDCLL <String> list = new Pd7WilliamCurtisDCLL <String> ();	
   
      list.addLast("Apple");
      list.addLast("Banana");
      list.addLast("Cucumber");
      list.add("Dumpling");
      list.add("Escargot");
      System.out.println(list);
      System.out.println("Size: " + list.size());
      
      Object obj = list.remove(3);
      System.out.println(list);
      
      System.out.println("Size: " +list.size());
      System.out.println("Removed "+ obj);
      
      System.out.print("Add at 3:   ");
      list.add(3,"Cheese");
      System.out.println(list);
      
      System.out.println("Get values at 1 and first: " + list.get(1)+" and " +
                          list.getFirst());
      System.out.println("No change: " +list);
      
      System.out.println( list.removeLast() + " is now removed!"); 
      System.out.println(list);
      
      System.out.print("Add first:  ");
      list.addFirst("Anchovie");
      System.out.println(list);
      System.out.println("Size: " + list.size());
      
      System.out.print("Set the second:  ");
      list.set(2, "Bread");
      System.out.println(list);
   } // main
}  // DCLL


class DLNode <E>
{
   private E value;
   private DLNode prev;
   private DLNode next;
   public DLNode(E arg, DLNode <E> p, DLNode <E> n)
   {
      value=arg;
      prev=p;
      next=n;
   }
   public DLNode()
   {
      value=null;
      next=this;
      prev=this;
   }
   public void setValue(E arg)
   {
      value=arg;
   }
   public void setNext(DLNode <E> arg)
   {
      next=arg;
   }
   public void setPrev(DLNode <E> arg)
   {
      prev=arg;
   }
   public DLNode <E> getNext()
   {
      return next;
   }
   public DLNode <E> getPrev()
   {
      return prev;
   }
   public E getValue()
   {
      return value;
   }
}  // DLNode
   
/*
 [Apple, Banana, Cucumber, Dumpling, Escargot]
 Size: 5
 [Apple, Banana, Dumpling, Escargot]
 Size: 4
 Removed Cucumber
 Add at 3:   [Apple, Banana, Cheese, Dumpling, Escargot]
 Get values at 1 and first: Apple and Apple
 No change: [Apple, Banana, Cheese, Dumpling, Escargot]
 Escargot is now removed!
 [Apple, Banana, Cheese, Dumpling]
 Add first:  [Anchovie, Apple, Banana, Cheese, Dumpling]
 Size: 5
 Set the second:  [Anchovie, Bread, Banana, Cheese, Dumpling]
 
  ----jGRASP: operation complete
  
  My output:
   ----jGRASP exec: java Pd7WilliamCurtisDCLL
 [Apple, Banana, Cucumber, Dumpling, Escargot]
 Size: 5
 [Apple, Banana, Dumpling, Escargot]
 Size: 4
 Removed Cucumber
 Add at 3:   [Apple, Banana, Cheese, Dumpling, Escargot]
 Get values at 1 and first: Apple and Apple
 No change: [Apple, Banana, Cheese, Dumpling, Escargot]
 Escargot is now removed!
 [Apple, Banana, Cheese, Dumpling]
 Add first:  [Anchovie, Apple, Banana, Cheese, Dumpling]
 Size: 5
 Set the second:  [Anchovie, Bread, Banana, Cheese, Dumpling]
 
  ----jGRASP: operation complete
  */