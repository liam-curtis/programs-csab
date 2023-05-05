//*********************************************************************************************************************************
// Name: William Curtis
// Period: 7                                          
// Date: 4/10/2023
// What I learned: I learned multiple things on this lab.
//    1. I learned how to reverse a map that has a list (ArrayList, Set, etc), as opposed to a with a singluar value.
//    2. I refreshed my knowledge on fileIO, as well as try-catch blocks.
//    3. The ordered property of TreeSet / TreeMap , and the trade offs of runtime and alphabetical order.
// How I feel about this lab: I feel good about this lab, and I'm happy that I created a more fool proof lab, where there
// cannot be inputmismatch errors due to a a try-catch block.
// What I wonder: I wonder how this data structure is not more popular, does it have a bad space complexity? There are not
// many pitfalls to this data structure, and I wonder what the practical applications of it are.
//***********************************************************************************************************************************

import java.io.*;
import java.util.*;
public class Pd7WilliamCurtisDictionary
{
   private static PrintStream ps;
   public static void main(String[] args) throws Exception
   {
      /***************************************************
                        PART I
       **************************************************/
      try
      {
         // funnel all System.out.print() results to the output file "Pd6EdundLauDictionaryOutputI.txt");
         ps = new PrintStream(new FileOutputStream("Pd7WilliamCurtisDictionaryOutputI.txt"));
         System.setOut(ps);
      }
      catch(Exception e)
      {
      } //catch
    
   
      Map<String, Set<String>> eng2spn = new TreeMap<String, Set<String>>();
      Scanner infile = new Scanner(new File("spanglish.txt"));
      while(infile.hasNext())
      {
         add(eng2spn, infile.next(), infile.next());
      }
      
      infile.close();
      
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);
      
      Map<String, Set<String>> spn2eng = reverse(eng2spn);
      System.out.println("\nSPANISH TO ENGLISH");
      display(spn2eng);
      
      ps.close();    // close the output file
      
      
      /***************************************************
        Part II
       **************************************************/
   
      // The two maps are still in the memory. Part II can interact with the user and add
      // new word(s) to both maps
      // For this part of the program, display all outputs onto the console. See sample outputs below.
      // After the user is done, write the two maps to a text file.
   
      // To reset the System.out to the console
      System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
            
      //creating the console interface where the user chooses options to follow
      
      //create a new scanner that grabs text from the console
      Scanner console = new Scanner(System.in);
      int num = -1;
      
      //while loop for the program to run
      while(num != 4)
      {
         //grab the next task for the user
         System.out.print("Menu options: translate from:\n(1) English to Spanish\n(2) Spanish to English\n(3) Add a new tranlation: (a) from English->Spanish (b) from Spanish->English\n(4) Exit\n");
         //try-catch block to make sure the number entered is an integer
         try {
            num = console.nextInt();
         } catch (Exception e) {
            num = -1;
            console.nextLine();
         }
         
         //different options
         //option 1 & 2 use the searchMap method to display the relevant definition (or apologizes if not in the dictionary).
         //option 3 uses the add method to add a new word to a dictionary.
         if(num == 1)
         {
            System.out.println("You have selected the English to Spanish dictionary. What word would you like to search for (type in all lowercase)?");
            String word = console.next();
            searchMap(eng2spn, word);
         } else if(num == 2) {
            System.out.println("You have selected the Spanish to English dictionary. What word would you like to search for (type in all lowercase)?");
            String word = console.next();
            searchMap(spn2eng, word);
         } else if(num == 3) {
         
            //gathers the translations
            System.out.println("What English word would you like to add today?");
            String wordEng = console.next().toLowerCase(); //converts to lowercase
            System.out.println("What is the Spanish translation?");
            String wordSpn = console.next().toLowerCase();
            
            //adds the translation to relevent dictionaries
            add(eng2spn, wordEng, wordSpn);
            add(spn2eng, wordSpn, wordEng);
            
         } else if(num != 4) { //if not 1-3 and not 4 then user must enter a vaild number
            System.out.println("Please enter an integer 1-4!\n");
         }
         //otherwise number is 4, then the program exits
      }
      
      //adds the updated maps to second text file
      try
      {
         // funnel all System.out.print() results to the output file "Pd6EdundLauDictionaryOutputI.txt");
         ps = new PrintStream(new FileOutputStream("Pd7WilliamCurtisDictionaryOutputII.txt"));
         System.setOut(ps);
      }
      catch(Exception e)
      {
      } //catch   
      
      //print the dictionaries
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);
      System.out.println("\nSPANISH TO ENGLISH");
      display(spn2eng);
   
   } // main
   
   //PART 2 METHOD
   
   //precondition: m and key are valid maps and strings, respectively
   //postcondition: prints out the key + definitions, or prints it isn't in the dicitonary if not valid
   public static void searchMap(Map<String, Set<String>> m, String key)
   {
      if(m.containsKey(key))
         System.out.println(m.get(key)+"\n");
      else
         System.out.println("Sorry, this word is currently not in the dictionary.\n"); 
   }
   
   //PART 1 METHODS
   
   // Precondition: none
   // Postcondition: display the contents of  a dictionary on the screeb
   public static void display(Map<String, Set<String>> m)
   {
      //loop through the keyset using a for-each loop
      for(String key : m.keySet())
         System.out.println("\t" + key + " " + m.get(key));
   } // display
   
   // precondition: none
   // postcondition: insert a new pair to the English to Spanish Dictionary
   public static void add(Map<String, Set<String>> engToSpnDictionary, String word, String translation)
   {
      
      if(!engToSpnDictionary.containsKey(word)) //check to see if the key isn't already inside the map
      {
         Set <String> temp = new TreeSet <String>(); //create a new TreeSet
         temp.add(translation);//add translation
         engToSpnDictionary.put(word,temp); //add translation to dictionary 
      } else {
         Set <String> temp = engToSpnDictionary.get(word); //get the existing TreeSet
         temp.add(translation);
         engToSpnDictionary.put(word,temp);
      }
   } // add
   
   // precondition: none
   // postcondition: returns a Spanish to English dictionary
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> engToSpnDictionary)
   {
      //make a reversed map
      Map<String,Set<String>> reverse = new TreeMap<String,Set<String>>();
      
      //iterate through all of the keys in engToSpnDictionary (original map)
      for(String key : engToSpnDictionary.keySet())
      {
         //iterate through each TreeSet in engToSpnDictionary
         for(String translation : engToSpnDictionary.get(key))
         {
            if(!reverse.containsKey(translation)) //if reverse doesn't contain translation as a key
            {
               Set <String> temp = new TreeSet <String> ();
               temp.add(key);
               reverse.put(translation, temp);
            } else { //otherwise adds to existing TreeSet
               Set <String> temp = reverse.get(translation);
               temp.add(key);
               reverse.put(translation, temp);
            }
         }
      }
      return reverse;
   } // reverse
   
}// Dictionary2022

/*
Output:

  ----jGRASP exec: java Pd7WilliamCurtisDictionary
 Menu options: translate from:
 (1) English to Spanish
 (2) Spanish to English
 (3) Add a new tranlation: (a) from English->Spanish (b) from Spanish->English
 (4) Exit
 1
 You have selected the English to Spanish dictionary. What word would you like to search for (type in all lowercase)?
 father
 [padre]
 
 Menu options: translate from:
 (1) English to Spanish
 (2) Spanish to English
 (3) Add a new tranlation: (a) from English->Spanish (b) from Spanish->English
 (4) Exit
 2
 You have selected the Spanish to English dictionary. What word would you like to search for (type in all lowercase)?
 fiesta
 [celebration, feast, holiday, party]
 
 Menu options: translate from:
 (1) English to Spanish
 (2) Spanish to English
 (3) Add a new tranlation: (a) from English->Spanish (b) from Spanish->English
 (4) Exit
 1
 You have selected the English to Spanish dictionary. What word would you like to search for (type in all lowercase)?
 beautiful
 Sorry, this word is currently not in the dictionary.
 
 Menu options: translate from:
 (1) English to Spanish
 (2) Spanish to English
 (3) Add a new tranlation: (a) from English->Spanish (b) from Spanish->English
 (4) Exit
 3
 What English word would you like to add today?
 beautiful
 What is the Spanish translation?
 bella
 Menu options: translate from:
 (1) English to Spanish
 (2) Spanish to English
 (3) Add a new tranlation: (a) from English->Spanish (b) from Spanish->English
 (4) Exit
 3
 What English word would you like to add today?
 hand
 What is the Spanish translation?
 manecilla
 Menu options: translate from:
 (1) English to Spanish
 (2) Spanish to English
 (3) Add a new tranlation: (a) from English->Spanish (b) from Spanish->English
 (4) Exit
 2
 You have selected the Spanish to English dictionary. What word would you like to search for (type in all lowercase)?
 manecilla
 [hand]
 
 Menu options: translate from:
 (1) English to Spanish
 (2) Spanish to English
 (3) Add a new tranlation: (a) from English->Spanish (b) from Spanish->English
 (4) Exit
 2
 You have selected the Spanish to English dictionary. What word would you like to search for (type in all lowercase)?
 bella
 [beautiful]
 
 Menu options: translate from:
 (1) English to Spanish
 (2) Spanish to English
 (3) Add a new tranlation: (a) from English->Spanish (b) from Spanish->English
 (4) Exit
 1
 You have selected the English to Spanish dictionary. What word would you like to search for (type in all lowercase)?
 beautiful
 [bella]
 
 Menu options: translate from:
 (1) English to Spanish
 (2) Spanish to English
 (3) Add a new tranlation: (a) from English->Spanish (b) from Spanish->English
 (4) Exit
 4
 
  ----jGRASP: operation complete.
 
 File contents after:
 
 Pd7WilliamCurtisDictionaryOutputI
 
ENGLISH TO SPANISH
	banana [banana]
	celebration [fiesta]
	computer [computadora, ordenador]
	double [doblar, doble, duplicar]
	father [padre]
	feast [fiesta]
	good [bueno]
	hand [mano]
	hello [hola]
	holiday [fiesta, vacaciones]
	party [fiesta]
	plaza [plaza]
	priest [padre]
	program [programa, programar]
	sleep [dormir]
	son [hijo]
	sun [sol]
	vacation [vacaciones]

SPANISH TO ENGLISH
	banana [banana]
	bueno [good]
	computadora [computer]
	doblar [double]
	doble [double]
	dormir [sleep]
	duplicar [double]
	fiesta [celebration, feast, holiday, party]
	hijo [son]
	hola [hello]
	mano [hand]
	ordenador [computer]
	padre [father, priest]
	plaza [plaza]
	programa [program]
	programar [program]
	sol [sun]
	vacaciones [holiday, vacation]

Pd7WilliamCurtisDictionaryOutputII <- may not be the same as actual file due to testing after doing output

ENGLISH TO SPANISH
	banana [banana]
	beautiful [bella]
	celebration [fiesta]
	computer [computadora, ordenador]
	double [doblar, doble, duplicar]
	father [padre]
	feast [fiesta]
	good [bueno]
	hand [manecilla, mano]
	hello [hola]
	holiday [fiesta, vacaciones]
	party [fiesta]
	plaza [plaza]
	priest [padre]
	program [programa, programar]
	sleep [dormir]
	son [hijo]
	sun [sol]
	vacation [vacaciones]

SPANISH TO ENGLISH
	banana [banana]
	bella [beautiful]
	bueno [good]
	computadora [computer]
	doblar [double]
	doble [double]
	dormir [sleep]
	duplicar [double]
	fiesta [celebration, feast, holiday, party]
	hijo [son]
	hola [hello]
	manecilla [hand]
	mano [hand]
	ordenador [computer]
	padre [father, priest]
	plaza [plaza]
	programa [program]
	programar [program]
	sol [sun]
	vacaciones [holiday, vacation]   
*/
