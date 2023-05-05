public class testListNode 
{
   public static void main(String [] args)
   {
      ListNode <String> head = new ListNode ("one", new ListNode ("two", new ListNode("three", null)));
      ListNode <String> none = null;
      print(head); 
      deleteNode(head,"one");
      System.out.println(head.getValue());
      print(head); 
   }
   public static void print(ListNode <String> head) {
      ListNode <String> t = head;
      while(t != null) {
         System.out.print(t.getValue());
         if(t.getNext() != null)
            System.out.print(", ");
         t = t.getNext();
      }
      System.out.println("");
   } 
   public static ListNode <String> deleteNode(ListNode<String> head, String value)
   {
      if(head == null)
         return null;
      else if(head.getValue().equals(value)) {
         head = head.getNext();
         return head;
      } else {
         ListNode <String> F = head;
         ListNode <String> S = head.getNext();
         while(S != null)
         {
            if(S.getValue().equals(value)) {
               F.setNext(S.getNext());
               return F;
            } else {
               F = S;
               S = S.getNext();
            }
         }
      }
      return head;
   }
} //DOESNT DELETE FIRST NODE CORRECTLY

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