/**************************************************************************************
 Name: William Curtis
 Date: 2/27/23
 What I learned:
 How I feel about this lab:

 I am wondering (the what-if moment):
 Credits:
***************************************************************************************/
import java.util.*;
public class Pd7WilliamCurtisHeapPriorityQueue <E>
{
   private static final int DEFAULT_CAPACITY = 1024;
   private E [] items;      // use a 1-D array instead of ArrayList
   private int numItems;    // number of elements in items

  
   public static void main (String [] args)
   {
      // Create a HeapPriorQueue_shell object to test all the methods in this class
      // Need to make sure that the type parameter of HeapPriorityQueue_shell implements 
      // Comparable
      Pd7WilliamCurtisHeapPriorityQueue <Integer> pq = new Pd7WilliamCurtisHeapPriorityQueue <>();
                
      // your code goes here
   
   }
   
   public Pd7WilliamCurtisHeapPriorityQueue()
   {
       // your code goes here
      items = (E [])(new Object [] {0,5,12,20,32,52});  // a min-heap
      numItems = 5;
       /*for (E v: items)
          System.out.print (v + " "); */
   } 
   
   
   public Pd7WilliamCurtisHeapPriorityQueue (int initialCapacity)
   {
          // your code goes here
   }
   

    
   // precondition: none
   // postcondition: returns true if the heap is empty, false if not 
   public boolean isEmpty()
   {
      return items[1] == null; 
   }
   
   // precondition: none
   // postcondition: returns the root of heap 
   public E peek()
   {
      return items[1];  
   }
   
   // precondition:
   // postcondition:
   public E remove()
   {
      // your code goes here    
      return null;  
   }
   
   // precondition:
   // postcondition:   
   public boolean add(E obj)
   {
      // your code goes here    
      return false;  
     
   } // add
      
   // precondition: none
   // postcondition: displays the heap sideways
   public String toString ()
   {
      return null;
   }
   
   // precondition: none
   // postcondition: swaps the value of index with its smallest child (i*2),(i*2+1)
   private void reheapDown(int index)
   {
   }
   
   // precondition: none
   // postcondition: swaps the value of index with its greatest parent (i/2)
   private void reheapUp()
   {
           
         
   }
   
   // precondition: none
   // postcondition: doubles the size of the items array --> and therefore the heap
   private void doubleCapacity()
   {
      E [] temp = (E [])(new Object [2048]);
      for(int i = 0; i < items.length; i++) {
         temp[i] = items[i]; 
      }
      items = temp;      
   }
     
}  //HeapPriorityQueue_shell
