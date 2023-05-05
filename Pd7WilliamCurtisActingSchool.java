/*
Name: William Curtis
Period: 7
Date: 4/10/23

What I learned: I learned how to reverse a map, as well as print one out. 
I also better understand why we need to use a for-each loop on unordered
data structures.
*/

import java.util.*;

public class Pd7WilliamCurtisActingSchool
{
   public static void main(String [] args)
   {
      Map <String,String> sGrades = new TreeMap <String,String> ();
      
      sGrades.put("Doe","A+");
      sGrades.put("John","C");
      sGrades.put("William","F");
      sGrades.put("Sunny","A+");
      sGrades.put("Bob","B");
   
      //print out the map
      for(String key : sGrades.keySet())
      {
         System.out.println(key +" (" + sGrades.get(key) + ")");
      }
      
      //make a reversed map
      Map<String,ArrayList<String>> reverse = new TreeMap<String,ArrayList<String>>();
      
      //iterate through all of the keys in sGrades (original map)
      for(String key : sGrades.keySet())
      {
         if(!reverse.containsKey(sGrades.get(key)))   { //if key from sGrades isn't in the reversed map
            ArrayList <String> temp = new ArrayList <String> (); //create a temporary ArrayList that's empty
            temp.add(key);
            reverse.put(sGrades.get(key), temp); //adds temp to reverse (should be empty)
         } else { //otherwise
            ArrayList<String> temp = reverse.get(sGrades.get(key)); //create a temporary ArrayList with all of the current vals from reverse
            temp.add(key);
            reverse.put(sGrades.get(key), temp); //replaces the ArrayList in reverse with temp
         }
      }
      
      //print out the reversed map
      for(String key : reverse.keySet())
      {
         System.out.println(key +": " + reverse.get(key));
      }
   }
}

/*

  ----jGRASP exec: java Pd7WilliamCurtisActingSchool
 Bob (B)
 Doe (A+)
 John (C)
 Sunny (A+)
 William (F)
 A+: [Doe, Sunny]
 B: [Bob]
 C: [John]
 F: [William]
 
  ----jGRASP: operation complete.

*/




