import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

   /*
    * Complete the 'isBalanced' function below.
    *
    * The function is expected to return a STRING.
    * The function accepts STRING s as parameter.
    */

   static String brackets = "({[)}]";
   static String openBrackets = "({[";

   public static String isBalanced(String s) {

      // Write your code here

      System.out.println( s );

      if ( !isEven(s) ) {
         return "NO";
      }

      if ( !areAllBrackets( s ) ) {
         return "NO";
      }

      Stack<Character> openBcketStack = new Stack();
      int i = 0;

      while( i < s.length() ) {

         if( isOpenBracket( s.charAt( i ) ) ) {
            openBcketStack.push( s.charAt( i ) );
         }
         else {
            if ( openBcketStack.isEmpty() ) {
               return "NO";
            }

            if ( correspOpenBcket( s.charAt( i ) ) != openBcketStack.pop() ) {
               return "NO";
            }

         }

         i++;
      }

      // Unmatch brackets left
      if ( !openBcketStack.isEmpty() ) {
         return "NO";
      }

      return "YES";
   }
   private static char correspOpenBcket( char closeBcket ) {
      switch( closeBcket ) {

         case ')':
            return '(';

         case '}':
            return '{';

         case ']':
            return '[';

         default:
            return 'X';
      }

   }

   private static boolean areAllBrackets( String s ) {

      int i = 0;

      while( i < s.length() ) {
         if ( !brackets.contains( String.valueOf( s.charAt(i) ) ) ) {
            return false;
         }
         i++;
      }

      return true;
   }

   private boolean isOpenCloseBracket( char character ) {
      if ( "({[)}]".contains( String.valueOf( character) ) ) {
         return true;
      }
      else {
         return false;
      }
   }

   private static boolean isEven( String s ) {
      return s.length() % 2 == 0;
   }

   private static boolean isOpenBracket( char bracket ) {
      if ( "({[".contains( String.valueOf(bracket) ) ) {
         return true;
      }
      else {
         return false;
      }
   }

}

public class Solution {
   public static void main(String[] args) throws IOException {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bufferedWriter =  new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      int t = Integer.parseInt(bufferedReader.readLine().trim());

      IntStream.range(0, t).forEach(tItr -> {
         try {
            String s = bufferedReader.readLine();

            String result = Result.isBalanced(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
         } catch (IOException ex) {
            throw new RuntimeException(ex);
         }
      });

      bufferedReader.close();
      bufferedWriter.close();
   }
}