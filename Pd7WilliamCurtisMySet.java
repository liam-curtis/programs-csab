/*
Name: William Curtis
Period: 7
Date: 3/24/2023

Name of Program: MySet
Purpose: implement a set with a variety of methods.
What I learned: I learned how to implement a set, as well as use
different methods that interact with the set datatype.
*/

import java.util.*;

public class Pd7WilliamCurtisMySet
{
   //driver class: tests with type integer
   public static void main (String [] args)
   {
      MySet<Integer> set =  new MySet<Integer>();
      
      set.getSet().add(10);
      set.getSet().add(20);
      set.getSet().add(30);
      set.getSet().add(40);
      set.getSet().add(20); //duplicate value (doesn't add!)
      set.getSet().add(50);
      
      MySet<Integer> set2 =  new MySet<Integer>();
      
      set2.getSet().add(10);
      set2.getSet().add(20);
      set2.getSet().add(40);
      set2.getSet().add(80);
      
      MySet<Integer> set3 =  new MySet<Integer>();
      
      //print the values of set
      System.out.println("set = " + set.getSet());
      System.out.println("set2 = " + set2.getSet());
      System.out.println("set3 = " + set3.getSet());
     
      //print the values of union, intersect, and difference
      System.out.println("union = " + set.union(set2.getSet()));
      System.out.println("intersect = " + set.intersection(set2.getSet()));
      System.out.println("difference = " + set.difference(set2.getSet()));
      
      //remove 80 from set2 to test subset
      set2.getSet().remove(80);
      System.out.println("Removed 80 from set2 to test subset");
      
      //test last 2 methods   
      System.out.println("set is a subset of set2: " + set.subset(set2.getSet()) + "\nset is a superset of set2: " + set.superset(set2.getSet()));
   }
}

class MySet <E>
{
   private Set <E> set;
   
   //default constructor
   public MySet()
   {
      set = new HashSet<E>();
   }
   
   //get method for MySet variable set
   public Set <E> getSet()
   {
      return set;
   }
   
   //returns a set containing all of the values in set and s
   Set <E> union(Set <E> s)
   {
      //initiate hashset union
      Set <E> union = new HashSet<E>();
      
      //iterate through set and s, adding all values to set union
      for(E item: set)
         union.add(item);
      for(E item: s)
         union.add(item);
      
      return union;
   }
   
   //returns a set containing the values the appear in BOTH set and s
   Set <E> intersection(Set <E> s)
   {
      //initialize hashset intersect
      Set<E> intersect = new HashSet<E>();
      
      //loops through set, and adds the values that appear in both
      for (E item : set) {
         if(s.contains(item))
            intersect.add(item);
      }
   
      return intersect;
   }
   
   //returns a set that contains values that appear in set but do not 
   //appear in s
   Set <E> difference(Set <E> s)
   {
      //initializes hashset difference
      Set <E> difference = new HashSet<E>();
      
      //loop through set, and add the values contained in set that don't 
      //appear in s to set difference
      for (E item : set) {
         if(!s.contains(item))
            difference.add(item);
      }
      
      return difference;   
   }
   
   
   /*
   subset definition: 
   
   s1 = {1,2,3}
   s2 = {1,2}
   
   s2 is a subset of s1, because all of s2's values are within s1.
   s2 wouldn't be a subset of s1 if had a 4, because 4 isn't in s1.
   if s2 = {1,2,3} then it would be considered a inproper subset, 
   because all of the values are the same s1's values.
   */
   
   //returns true if set is a subset of s, returns false otherwise
   boolean subset(Set <E> s)
   {
      //if set is the same size or bigger than s, set isn't a proper subset
      if(set.size() >= s.size())
         return false;
      
      //if a value in set isn't in s, then set isn't a proper subset
      for(E item : set)  {
         if(!s.contains(item))
            return false;
      }
      
      return true;
   }
   
   //returns true if s is a subset of set, returns false otherwise
   boolean superset(Set <E> s)
   {
      //if s is the same size or bigger than set, s isn't a proper subset
      if(s.size() >= set.size())
         return false;
      
      //if a value in s isn't in set, then s isn't a proper subset
      for(E item : s)  {
         if(!set.contains(item))
            return false;
      }
      
      return true;
   }
}

/*
  ----jGRASP exec: java Pd7WilliamCurtisMySet
 set = [50, 20, 40, 10, 30]
 set2 = [80, 20, 40, 10]
 set3 = []
 union = [80, 50, 20, 40, 10, 30]
 intersect = [20, 40, 10]
 difference = [50, 30]
 Removed 80 from set2 to test subset
 set is a subset of set2: false
 set is a superset of set2: true
 
  ----jGRASP: operation complete.
*/
