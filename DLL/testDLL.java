public class testDLL
{
   public static void main(String [] args)
   {
      DLNode <String> head = new DLNode("first", null, null);
      DLNode <String> mid = new DLNode("second", head, null);
      DLNode <String> tail = new DLNode("third", mid, null);
      mid.setNext(tail);
      head.setNext(mid);
      
      print(head);
      insert(tail,"Hello");
      print(head);
     
   }
   
   public static void print(DLNode <String> head)
   {
      System.out.print("[");
      while(head != null)
      {
         System.out.print(head.getValue());
         head = head.getNext();
         if(head != null)
            System.out.print(", ");
      }
      System.out.println("]");
   } 
   
   public static void insert(DLNode <String> t, String obj)
   {
      if(t == null) {
         DLNode <String> h = new DLNode(obj,null,null);
      } else {
         if(t.getPrev()==null) {
            t.setPrev(new DLNode(obj,null,t));
         } else {
            DLNode <String> insert = new DLNode(obj,t.getPrev(),t);
            t.getPrev().setNext(insert);
            t.setPrev(insert);
         }
      }
   }

}

class DLNode <E>
{
   private E value;
   private DLNode prev;
   private DLNode next;
         
   public DLNode(E arg, DLNode <E> p, DLNode <E> n)
   {
      value = arg;
      prev = p;
      next = n;
   }
   
   public void setValue(E arg)
   {
      value = arg;
   }
   public void setNext(DLNode <E> arg)
   {
      next = arg;
   }
   public void setPrev(DLNode <E> arg)
   {
      prev = arg;
   }
   public DLNode getNext()
   {
      return next;
   }
   public DLNode getPrev()
   {
      return prev;
   }
   public E getValue()
   {
      return value;
   }
}