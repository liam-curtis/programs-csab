/*****************************************************************************************************************
NAME: William Curtis     
PERIOD: 7 
DUE DATE: 12/16/22

ASSIGNMENT: 
Iterator Lab

PURPOSE: 
The purpose of this lab is to manipulate an array into an ArrayList, then use for-each loops and an iterator 
to further manipulate the ArrayList.

WHAT I LEARNED:   
I learned that a for-each loop is internally represented by an iterator, and an iterator also functions similar to 
a cursor. I also learned that the iterator is a powerful tool that can be used with multiple data structures that 
java has. 
            
CREDITS: 
The Notes.
****************************************************************************************************************/

import java.io.*;
import java.util.*;
public class Pd7WilliamCurtisIteratorLab
{
   public static void main(String[] args)
   {
      System.out.println("Iterator Lab\n");
      int[] rawNumbers = {-9, 4, 2, 5, -10, 6, -4, 24, 20, -28};
      for(int n : rawNumbers )
         System.out.print(n + " ");    
      ArrayList<Integer> numbers = createNumbers(rawNumbers);
      System.out.println("ArrayList: "+ numbers);      //Implicit Iterator!
      System.out.println("Count negative numbers: " + countNeg(numbers));
      System.out.println("Average: " + average(numbers));
      System.out.println("Replace negative numbers: " + replaceNeg(numbers));
      System.out.println("Delete zeros: " + deleteZero(numbers));
      String[] rawMovies = {"High_Noon", "High_Noon", "Star_Wars", "Tron", "Mary_Poppins", 
               "Dr_No", "Dr_No", "Mary_Poppins", "High_Noon", "Tron"};
      ArrayList<String> movies = createMovies(rawMovies);
      System.out.println("Movies: " + movies);
      System.out.println("Movies: " +  removeDupes(movies));
   }
   // pre: an array of just int values 
   // post: return an ArrayList containing all the values
   public static ArrayList<Integer> createNumbers(int[] rawNumbers) 
   {
      ArrayList<Integer> list = new ArrayList<>();
      for(int num : rawNumbers)
      {
         list.add(num); 
      } //for-each loop that loops through entire rawNumbers, and adds each one to list, which is an ArrayList
      return list;
   }//createNumbers
   
   // pre: an array of just Strings  
   // post: return an ArrayList containing all the Strings
   public static ArrayList<String> createMovies(String[] rawWords) 
   {
      ArrayList<String> list = new ArrayList<>();
      for(String movie : rawWords)
      {
         list.add(movie);
      } //for-each loop that loops through entire rawWords, and adds each one to list, which is an ArrayLis
      return list;
   }//createMovies
   
   // pre: ArrayList a is not empty and contains only Integer objects
   // post: return the number of negative values in the ArrayList a
   public static int countNeg(ArrayList<Integer> a)
   {
      int count = 0;
      for(Integer num : a)
      {
         if(num < 0)
            count++;
      }//for-each loop through the ArrayList, iterating a counter for every negative number
      return count;         
   }//countNeg
   
   // pre: ArrayList a is not empty and contains only Integer objects
   // post: return the average of all values in the ArrayList a
   public static double average(ArrayList<Integer> a)
   {
      int sum = 0;
      for(Integer num : a)
      {
         sum += num;
      } //for-each loop through the ArrayList, finding the sum of a ArrayList
      return sum / a.size(); //returns sum divided by # of terms     
   }//average
   
  	// pre: ArrayList a is not empty and contains only Integer objects
   // post: replaces all negative values with 0 
   public static ArrayList<Integer> replaceNeg(ArrayList<Integer> a)
   {
      ListIterator <Integer> iter = a.listIterator();//initializes a list iterator
      while(iter.hasNext())
      {
         Integer num = iter.next();
         if(num<0) //checks if num is negative, and if so replaces the negative number with 0
            iter.set(0);
      }
      return a;     
   }//replaceNeg
   
   // pre: ArrayList a is not empty and contains only Integer objects
   // post: deletes all zeros in the ArrayList a
   public static ArrayList<Integer> deleteZero(ArrayList<Integer> a)
   {
      Iterator <Integer> iter = a.iterator();//initializes an iterator
      while(iter.hasNext())
      {
         Integer num = iter.next();
         if(num==0) //checks if num is 0, and if removes it from the ArrayList
            iter.remove();
      }
      return a;      
   }//deleteZero
   
   // pre: ArrayList a is not empty and contains only String objects
   // post: return ArrayList without duplicate movie titles
	// strategy: start with an empty array and add movies as needed
   public static ArrayList<String> removeDupes(ArrayList<String> a)
   {
      ArrayList<String> origMovies = new ArrayList<String>();
      for (String movie : a) {
         if (!origMovies.contains(movie)) //if movie is not in new list, it is added
            origMovies.add(movie);
      }
      return origMovies;
   }//removeDupes
  
}
/*
Output
 ----jGRASP exec: java Pd7WilliamCurtisIteratorLab
 Iterator Lab
 
 -9 4 2 5 -10 6 -4 24 20 -28 ArrayList: [-9, 4, 2, 5, -10, 6, -4, 24, 20, -28]
 Count negative numbers: 4
 Average: 1.0
 Replace negative numbers: [0, 4, 2, 5, 0, 6, 0, 24, 20, 0]
 Delete zeros: [4, 2, 5, 6, 24, 20]
 Movies: [High_Noon, High_Noon, Star_Wars, Tron, Mary_Poppins, Dr_No, Dr_No, Mary_Poppins, High_Noon, Tron]
 Movies: [High_Noon, Star_Wars, Tron, Mary_Poppins, Dr_No]
 
  ----jGRASP: operation complete.
*/
