//CircularLinkedList.java
public class Pd7WilliamCurtisTestCircular
{
   public static void main (String [] args)
   {
      ListNode <Integer> list = new ListNode <> (1, new ListNode <> (2, new ListNode <> (3,null)));
      CircularLinkedList circle = new CircularLinkedList(list);  
      System.out.println(circle);
      circle.insertLast(20);
      System.out.println(circle);    
      circle.insertFirst(100);  
      System.out.println(circle);
      System.out.println(circle.deleteLast());
      System.out.println(circle);
      System.out.println(circle.deleteFirst());
      System.out.println(circle);
   } // main

} // TestCircular

/*  CircularLinkedList <E> class
*/
class CircularLinkedList<E>
{
   private ListNode <E> head;  // head points to a circular linked list
  
   // make 'head' a copy of 'list'
   public CircularLinkedList (ListNode <E> list)
   {
      //creates a pointer that loops to end of the linked list, then points the last node to the starting node
      head = copy(list);
      ListNode <E> temp = head;
      
      while(temp.getNext() != null)
      {
         temp = temp.getNext();
      }
      
      temp.setNext(list);
   }
   
   // helper method
   private ListNode <E> copy (ListNode <E> list)
   {
      if (list == null) 
         return null; 
      else if (list.getNext() == null) 
         return new ListNode <> (list.getValue(), null); 
      else
         return new ListNode <> (list.getValue(), copy(list.getNext())); 
   }

   public void insertFirst (E obj)
   {
      ListNode <E> newHead = new ListNode <> (obj, head);
      ListNode <E> temp = head;
      while(temp.getNext().getValue() != head.getValue())
      {
         temp = temp.getNext();
      }
      head = newHead;
      temp.setNext(head);
   }


   public void insertLast (E obj)
   {
      ListNode <E> temp = head;
      
      while(temp.getNext().getValue() != head.getValue())
      {
         temp = temp.getNext();
      }
      temp.setNext(new ListNode(obj,head));
   }


   // post: return the first node of the CLL
   public  ListNode <E> getFirst()
   {
      if(head == null)
      {
         return null;
      } else {
         return head;
      }
   }


   // post: return the last node of the DLL
   public  ListNode <E> getLast()
   {
      ListNode <E> temp = head;
      while(temp.getNext().getValue() != head.getValue())
      {
         temp = temp.getNext();
      }
      return temp;
   }

   // post: return the deleted node’s value
   public  E deleteLast()
   {
      ListNode <E> temp = head;
      while(temp.getNext().getNext().getValue() != head.getValue())
      {
         temp = temp.getNext();
      }
      E val = temp.getNext().getValue();
      temp.setNext(head);
      return val;
   }

   // post:  return the deleted node’s value
   public E deleteFirst()
   {
      ListNode <E> temp = head;
      while(temp.getNext().getValue() != head.getValue())
      {
         temp = temp.getNext();
      }
      temp.setNext(head.getNext());
      E val = head.getValue();
      head = head.getNext();
      return val;
   }

   public String toString()
   {
      if(head != null)
      {
         ListNode <E> temp = head.getNext();
         String print = head.getValue() + ", ";
         while(temp.getValue() != head.getValue())
         {
            print += temp.getValue() + ", ";
            temp = temp.getNext();
         }
         return print;
      } else {
         return "Empty list";
      }
   }
}
/*  ListNode <E> class
*/
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












