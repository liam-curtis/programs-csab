/*****************************************************************************************************************
NAME: William Curtis
PERIOD: 7
DUE DATE: 3/16/23 

PURPOSE: The purpose of the lab is to construct hashtables with three different ways: linear probe, rehashing, and chaining.

WHAT I LEARNED: I learned how to deal with various methods of implementing hashtables, as well as the pitfalls of certain 
implemenatations. I also (re?)learned how to find the GCF of two numbers.
         
CREDITS (BE SPECIFIC: FRIENDS, PEERS, ONLINE WEBSITE): https://www.programiz.com/java-programming/examples/hcf-gcd
help with greatest common factor (relatively prime)

****************************************************************************************************************/
/***********************************************************************************
   Assignment:  This hashing program results in collisions.
                You are to implement three different collision schemes: 
                linear probing, relative prime probing (use the first relatively prime 
                number of the length of the hash table as the step increase), and 
                chaining.  Then implement a search algorithm that is appropriate
                for each collision scheme.
 ***********************************************************************************/
import java.util.*;
import javax.swing.*;

public class Pd7WilliamCurtisHashing
{
   public static void main(String[] args)
   {
      int arrayLength = Integer.parseInt(JOptionPane.showInputDialog(
                         "Hashing!\n"+
                         "Enter the size of the array:  ")); // enter 20
       
      int numItems = Integer.parseInt(JOptionPane.showInputDialog(
                         "Add n items:  "));                 // enter 15
     
      int scheme = Integer.parseInt(JOptionPane.showInputDialog(
           "The Load Factor is " + (double)numItems/arrayLength +
           "\nWhich collision scheme?\n"+
           "1. Linear Probing\n" +
           "2. Relatively Prime Probing\n"+
           "3. Chaining"));
      Hashtable table = null;
      switch( scheme )
      {
         case 1:   
            table = new HashtableLinearProbe(arrayLength);
            break;
         case 2: // rehash using the first relatively prime of arrayLength
            table = new HashtableRehash(arrayLength); 
            break;
         case 3:  
            table = new HashtableChaining(arrayLength);
            break;
         default:  System.exit(0);    
      }
      for(int i = 0; i < numItems; i++)
         table.add("Item" + i);
      String action = JOptionPane.showInputDialog(
                       "Search for:  Item0" + " to "+ "Item"+(numItems-1));
      int itemNumber = 0;
      if (action != null)
      {
         itemNumber = Integer.parseInt(action);
         while( itemNumber != -1 )
         {
            String key = "Item" + itemNumber;
            int index = table.indexOf(key); 
            if( index >= 0)    //found it
               System.out.println(key + " found  at index " + index);
            else
               System.out.println(key + " not found!");
            action = JOptionPane.showInputDialog(
                       "Search for:  Item0" + " to "+ "Item"+(numItems-1));
            if (action != null)
               itemNumber = Integer.parseInt(action); 
            else
               itemNumber = -1;                         
         } 
      }
      System.out.println ("Goodbye!");
      System.exit(0);
   }
}

interface Hashtable
{
   void add(Object obj);
   int indexOf(Object obj);
}


class HashtableLinearProbe implements Hashtable
{
   private Object[] array;
   
   public HashtableLinearProbe(int size)
   {
      array = new Object[size];                   
   }
   
   //preconditions: obj is a object
   //postconditions: adds obj to the hashTable at first available spot
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if (array[index] == null)  //empty
      {
         System.out.println(obj + "\t" + code + "\t" + index);
         array[index] = obj; //inserts value into hashtable
      }
      else    //collision
      {
         System.out.println(obj + "\t" + code + "\tCollision at "+ index);
         index = linearProbe(index);
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
   }  
   
   //preconditions: none
   //postconditions: probes throught the values one at a time, if array is full then may not work
   public int linearProbe(int index)
   {
      //checks if the index is at the end of array, if so then goes back to index 0
      //adds 1 to the index otherwise, because it moves one space
      while(array[index] != null) {
         index++;
         if(index > array.length-1)
            index = 0;
      }
      return index;
   }
   
   //preconditions: none
   //postconditions: returns the first instance of the object
   public int indexOf(Object obj)     
   {
      int index = Math.abs(obj.hashCode() % array.length);
      while(array[index] != null) //if null then value is not in the array
      {
         if(obj.equals(array[index]))  //found it
         {
            return index;
         }
         else    //search for it in a linear probe manner
         {
            index++;
            if(array.length-1 < index) {
               index = 0;
            }
               
            System.out.println("Looking at index " + index);
         }
      } // while
      //not found
      return -1; 
   } // indexOf
} // HashtableLinearProbe


class HashtableRehash implements Hashtable
{
   private Object[] array;
   private int constant = 2;
   
   //constructor
   public HashtableRehash(int size)
   {
      array = new Object[size];
       
      //finds the first relative prime number in relation to size
      for (int i = constant; i < size; i++)
      {
         if (greatestCommonFactor(size, i) == 1)   {
            constant = i;
            break; //if found don't look for others -> looking for first 
         }
      }
      
      System.out.println("constant: " + constant);                      
   }
   
   //precondition: a & b are valid integers
   //postcondition: returns the greatest common factor of a & b
   public int greatestCommonFactor(int a, int b)
   {
      //greatest common factor, init 1
      int gcf = 1;
      
      for (int i = gcf; i <= a && i <= b; ++i) {
         if (a % i == 0 && b % i == 0)
            gcf = i;
      }
      return gcf;
   }
   
   //preconditions: obj is a valid object
   //postconditions: inserts the obj into first available index
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if(array[index] == null)  //empty
      {
         //insert it
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
      else //collision
      {
         System.out.println(obj + "\t" + code + "\tCollision at "+ index);
         index = rehash(index);
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
   }  
   
   //preconditions: valid index
   //postconditions: changes the hash value depending on the constant (first relative prime #)
   public int rehash(int index)
   {
      int i = 1;
      while(array[(index + (i * constant)) % array.length] != null) {
         index = (index +  i * constant) % array.length;           
      }
      return index;
   }
   
   //preconditions: obj is a valid object
   //postconditions: returns the index of the first instance of obj by rehashing
   public  int indexOf(Object obj)
   {
      int index = Math.abs(obj.hashCode() % array.length);
      while(array[index] != null)
      {
         if(array[index].equals(obj))  //found it
         {
            return index;
         }
         else //search for it in a rehashing manner
         {
            index = (index + constant) % array.length;
            System.out.println("Looking at index " + index);
         }
      }
      return -1; //not found
   }
} // HashTableRehash


class HashtableChaining implements Hashtable
{
   private LinkedList[] array;
   
   public HashtableChaining(int size)
   {
      array = new LinkedList[size];                                                  
   }
   
   //preconditions: obj is a valid object
   //postconditions: adds the obj to the hashtable in a chaining manner, with a linked list representing every index
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      
      if (array[index] == null)
      {
         //empty
         System.out.println(obj + "\t" + code + "\t" + index);
         array[index] = new LinkedList<>();
         array[index].add(obj);
      }
      else   
      {
         //collision
         System.out.println(obj + "\t" + code + "\tCollision at "+ index);
         array[index].add(obj);
         System.out.println(obj + "\t" + code + "\t" + index);
      }
   }  
   
   //preconditions: none
   //postconditions: returns the first instance of the object
   public int indexOf(Object obj)
   {
      int index = Math.abs(obj.hashCode() % array.length);
      //if empty then value is not in the array
      if( !array[index].isEmpty() ) {
         //loop through the entire array, looking for the index, and the i value
         int i = 0;
         while(i < array[index].size()) {
            //found it
            if(array[index].get(i).equals(obj)) 
               return index;
            i++;
         }
      }
      return -1; //not found
   } // indexOf
} // HashtableChaining