public class exhaustiveSearch
{
   public static void main (String [] args)
   {
      System.out.println(findPath(0,0,5,5));
   }
   public static int findPath(int startX, int startY, int endX, int endY)
   {
      return helper(startX,startY,endX,endY,"");
   }
   private static int helper(int startX, int startY, int endX, int endY, String path){
      if(startX == endX && startY == endY) {
         return 1;
      } else if(startX <= endX && startY <= endY) {
      
         helper(startX+1,startY+1,endX,endY,path+"Ne");
         helper(startX,startY+1,endX,endY,path+"N");
         helper(startX+1,startY,endX,endY,path+"E");
         
         return 1;
      } 
   }
}