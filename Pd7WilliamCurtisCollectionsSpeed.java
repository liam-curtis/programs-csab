//  name: William Curtis date: 12/12/22    
import java.util.*;

public class Pd7WilliamCurtisCollectionsSpeed
{
   public static int N = 10000;
   
   public static void main(String[] args)
   {
      ArrayList<Integer> al = new ArrayList<Integer>();
      LinkedList<Integer> ll = new LinkedList<Integer>();
      makeValues(al, ll);
      
      System.out.println("get each for ArrayList = " + timeGetEach(al));
      System.out.println("get each for LinkedList = " + timeGetEach(ll));
      System.out.println("\nadd at 0 for ArrayList = " + timeAddFirst(al));
      System.out.println("add at 0 for LinkedList = " + timeAddFirst(ll));
      System.out.println("\nadd at list.size() for ArrayList = " + timeAddLast(al));
      System.out.println("add at list.size() for LinkedList = " + timeAddLast(ll));
      System.out.println("addLast for LinkedList = " + timeAddLastLL(ll));
   }
   public static void makeValues(ArrayList<Integer> al, LinkedList<Integer> ll)
   {
      for( int i = 0; i < N; i++ )
      {
         al.add(i);              		//"autoboxing" converts int to Integer; 
         ll.add(i);							//    introduced in Java 1.5
      }
   }
   	/* get N items by searching for each one. */
   public static double timeGetEach(List<Integer> list)
   {
      double start = System.nanoTime();
      for( int i = 0; i < N; i++ )
      {
         int index = list.get(i);
      }
      return (System.nanoTime() - start)/1E6;
   }
   /* add 10000 new objects at position 0  */
   public static double timeAddFirst(List<Integer> list)
   {
      double start = System.nanoTime();
      for(int i = 0; i < N; i++)
      {
         list.add(0,i);
      }//adds at pos 0 10k times --> al takes longer because needs to shift
      return (System.nanoTime() - start)/1E6;
   }
   /* add 10000 new objects at position list.size() */
   public static double timeAddLast(List<Integer> list)
   {
      double start = System.nanoTime();
      for(int i = 0; i < N; i++)
      {
         list.add(list.size(),i);
      } //adds at last 10K times
      return (System.nanoTime() - start)/1E6;
   }
   /* add 10000 new objects at the end. 
      Uses the LinkedList method addLast(). 
		You must cast List list into a LinkedList.     */
   public static double timeAddLastLL(List<Integer> list)
   {
      double start = System.nanoTime();
      LinkedList <Integer> temp = (LinkedList<Integer>)list; //typecasted list
      for(int i = 0; i < N; i++)
      {
         temp.addLast(i);
      } //adds at last 10K times
      return (System.nanoTime() - start)/1E6;
   }
}
