package api.lec3;
public class Ex000 {
   public static void main(String[] args) {
       Object o = 1; getType(o);
       o = 1.2; getType(o);
   }

   public static void getType(Object o) {
      System.out.println(o.getClass().getName());
   }
}