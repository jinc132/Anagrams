
import java.util.*;

public class CountLetter {
   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
      System.out.print("phrase: ");
      System.out.println("Count: " + counter(console.next()));
   }
   
   private static int counter(String phrase) {
      int count = 0;
      for(int i = 0; i < phrase.length(); i++) {
         if(phrase.charAt(i) != ' ') {
            count++;
         }
      }
      return count;
   }
}