/******
Name: William Curtis
Period: 7 
Name of the Lab: Mini Facebook
Purpose of the Program:
The program works/partially work/does not compile/has run time errors and where ...
What I learned (in bullet form):
How I feel about this lab:
What I wonder:
*****/

import java.util.*;

public class Pd7WilliamCurtisMicroFB
{  
   //driver
   public static void main(String [] args)
   {
      //instantiate a new facebook class
      MicroFB fb = new MicroFB();
      
      //nameOne and nameTwo represent the the names that the commands will execute
      String nameOne = "";
      String nameTwo = "";
      
      //line processing, instantiate a scanner and a exit variable
      Scanner console = new Scanner(System.in);
      boolean exit = false;
      
      //run a while loop that runs until program calls method X()
      while(!exit)
      {
         String line = console.nextLine(); //obtain the next line
         String command = line.substring(0,1).toUpperCase(); //obtain the next command, converting to upper case if necessary
       
         //filter through all possible methods (P F U L Q X)
         if(command.equals("P")) {
            if(lineValid(line, 1) != -1) { //check to see if line is properly formatted
               nameOne = parseLineSingle(line, 2);
               fb.P(nameOne);
            } else
               System.out.println("Please enter a valid command");
         } else if(command.equals("F")) {
            if(lineValid(line, 2) != -1) { //check to see if line is properly formatted
               String[] container = parseLineDouble(line, 2, lineValid(line, 2));
               nameOne = container[0];  
               nameTwo = container[1];
               fb.F(nameOne,nameTwo);
            } else
               System.out.println("Please enter a valid command");
         } else if(command.equals("U")) {
            if(lineValid(line, 2) != -1) { //check to see if line is properly formatted
               String[] container = parseLineDouble(line, 2, lineValid(line, 2));
               nameOne = container[0];  
               nameTwo = container[1];
               fb.U(nameOne,nameTwo);
            } else
               System.out.println("Please enter a valid command");
         } else if(command.equals("L")) {
            if(lineValid(line, 1) != -1) { //check to see if line is properly formatted
               nameOne = parseLineSingle(line, 2);
               fb.L(nameOne);
            } else
               System.out.println("Please enter a valid command");
         } else if(command.equals("Q")) {
            if(lineValid(line, 2) != -1) { //check to see if line is properly formatted
               String[] container = parseLineDouble(line, 2, lineValid(line, 2));
               nameOne = container[0];  
               nameTwo = container[1];
               fb.Q(nameOne,nameTwo);
            } else
               System.out.println("Please enter a valid command");
         } else if(command.equals("X")) {
            exit = true; //terminates the program
         } else {
            System.out.println("Please enter a valid command");
         }
      }
   } //while
   
   //preconditions: names have alphabetic letters only
   //postconditions: returns the line # of the string for parsing, -1 if invalid
   public static int lineValid(String line, int format) {
      if (format == 1 && line.length() > 2 && line.substring(1,2).equals(" ")) //check for single input
         return 2; //doesn't matter, will always start at two
      else if(format == 2 && line.length() > 5) { //check for double input
         int lengthBetween = 0; //length between spaces
         for(int i = 2; i < line.length(); i++) //loop through characters in line, starting after the command
         {
            if (line.substring(i,i+1).equals(" ") && lengthBetween > 0)
               return i;
            else
               lengthBetween++;
         }
      }
      return -1;
   } //lineValid
   
   //preconditions: command is in a valid format
   //postconditions: returns the name of the first friend
   public static String parseLineSingle(String line, int start) {
      return line.substring(start);
   } //parseLineSingle
   
   //preconditions: command is in a valid format
   //postconditions: returns the names in a String format in a 1D-array of strings
   public static String[] parseLineDouble(String line, int startFirst, int startSecond) {
      String[] values = new String[2];
      values[0] = line.substring(startFirst, startSecond);
      values[1] = line.substring(startSecond+1);
      return values;
   } //parseLineDouble
}

class MicroFB
{
   private Map <String, Person> nameToPerson;
   private Map <String, Boolean> namesFriends;
   
   //nameToPerson indexes each person object under their name, stores alphabetically
   //namesFriends contains a boolean signifying that the two names (in name1*name2 form) are friends
   //preconditions: none
   //postconditions: create a new instance of class MicroFB and instantiates two maps
   public MicroFB() {
      nameToPerson = new TreeMap <String, Person>();
      namesFriends = new HashMap <String, Boolean>();
   }
   
   //preconditions: none
   //postconditions: create a new instance of class Person with the according name, create a key name with value person
   public void P(String name) {
      Person p = new Person(name);
      nameToPerson.put(name, p);
   } //P
   
   //preconditions: nameOne and nameTwo are both already the names of Person in MicroFB
   //postconditions: connects nameOne and nameTwo, making them friends
   public void F(String nameOne, String nameTwo) {
      //check to see if names are the same, if so does nothing
      if(!nameOne.equals(nameTwo)) { 
         //add Person 2 as friend of Person 1, and vise versa
         nameToPerson.get(nameOne).addFriend(nameToPerson.get(nameTwo));
         nameToPerson.get(nameTwo).addFriend(nameToPerson.get(nameOne));
         //create the friends key and put key into namesFriends
         String key = friendsKey(nameOne, nameTwo);
         namesFriends.put(key, true);
      }
   } //F
   
   //preconditions: nameOne and nameTwo are both already the names of Person in MicroFB
   //postconditions: removes nameOne as friend of nameTwo, if they are not friends nothing happens
   public void U(String nameOne, String nameTwo) {
      //create the friends key
      String key = friendsKey(nameOne, nameTwo);
     
      //check to see if they are friends, if not do nothing
      if(namesFriends.containsKey(key) && namesFriends.get(key)) {
         //remove from direct friends list
         nameToPerson.get(nameOne).removeFriend(nameToPerson.get(nameTwo));
         nameToPerson.get(nameTwo).removeFriend(nameToPerson.get(nameOne));
         //set the key to false
         namesFriends.put(key, false);
      }
   } //U
   
   //preconditions: name is a Person in MicroFB
   //postconditions: prints the names of name's friends
   public void L(String name) {
      nameToPerson.get(name).printFriends();
   } //L
   
   //preconditions: nameOne and nameTwo are both already the names of Person in MicroFB
   //postconditions: checks whether nameOne and nameTwo are friends
   public void Q(String nameOne, String nameTwo) {
      //create the friends key
      String key = friendsKey(nameOne, nameTwo);
     
      //check to see if they are friends
      if(namesFriends.containsKey(key) && namesFriends.get(key)) 
         System.out.println("\tYes, they are friends");
      else
         System.out.println("\tNo, they are not friends");
   } //Q
   
   //preconditions: none
   //postconditions: returns a string that contains the friends key of the two names
   public String friendsKey(String nameOne, String nameTwo) {
      if(nameOne.compareTo(nameTwo) > 0) //nameOne > nameTwo
         return  nameTwo+"*"+nameOne;
      else //nameTwo > nameOne
         return nameOne+"*"+nameTwo;
   }//friendsKey
}

class Person
{
   private String name;
   private LinkedList <Person> friendsList;
   
   //preconditions: n is not the name of an existing person
   //postconditions: creates a new person with n name, instantiates friendsList
   public Person(String n) {
      name = n;
      friendsList = new LinkedList <Person> ();
   }
   
   //preconditions: none
   //postconditions: adds Person p to the friendsList variable of person
   public void addFriend(Person p) {
      friendsList.addFirst(p);
   }
   
   //preconditions: none
   //postconditions: remove Person p from the friendsList variable of person
   public void removeFriend(Person p) {
      friendsList.remove(p);
   }
   
   //preconditions: none
   //postconditions: prints the values of friendsList to console
   public void printFriends() {
      String print = "\tFriend(s): ";
      for(Person f : friendsList) //loop through all friends
         print += f.getName() + " ";
      System.out.println(print);
   }
   
   //preconditions: none
   //postconditions: returns the name of Person
   public String getName() {
      return name;
   }
}

/*****
Output:
  ----jGRASP exec: java Pd7WilliamCurtisMicroFB
 P Sam
 P Liza
 P Mark
 P Amy
 F Liza Amy
 F Liza Mark
 F Amy Sam
 L Amy
 	Friend(s): Sam Liza 
 L Sam
 	Friend(s): Amy 
 U Liza Amy
 L Amy
 	Friend(s): Sam 
 Q Liza Mark
 	Yes, they are friends
 P Edmund
 P Esther
 F Edmund Mark
 F Mark Esther
 L Mark
 	Friend(s): Esther Edmund Liza 
 X
 
  ----jGRASP: operation complete.
  *****/