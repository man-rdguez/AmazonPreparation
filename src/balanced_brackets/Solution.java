package balanced_brackets;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.util.Stack;

class Result {

   public static String isBalanced(String s) {
      // Write your code here
      if (s.length() % 2 > 0) {
         return "NO";
      }

      Stack<Character> openBrackets = new Stack();

      for (char c : s.toCharArray()) {

         if (c == '(' || c == '{' || c == '[') {
            openBrackets.push(c);
         } else {
            if (openBrackets.isEmpty()) {
               return "NO";
            }

            char lastOpenBracket = openBrackets.pop();

            if (lastOpenBracket == '{' && c != '}') {
               return "NO";
            } else if (lastOpenBracket == '(' && c != ')') {
               return "NO";
            } else if (lastOpenBracket == '[' && c != ']') {
               return "NO";
            }
         }
      }

      if (openBrackets.isEmpty()) {
         return "YES";
      }
      else {
         return "NO";
      }

   }
}

public class Solution {

   public static void main(String[] args) throws IOException {

      String brackets = "{{}(";
      System.out.println( "isBalanced: " + Result.isBalanced( brackets ) );
   }

}
