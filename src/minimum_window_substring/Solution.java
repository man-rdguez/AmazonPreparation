package minimum_window_substring;

import java.util.ArrayList;

public class Solution {

   public static void main(String[] args) {
      /*
      String s = "ADOBECODEBANC";
      String t = "ABC";

      String s = "a";
      String t = "a";
      */

      String s = "a";
      String t = "aa";

      System.out.println( minWinSubs(s, t) );
   }

   private static String minWinSubs( String s, String t ) {

      ArrayList<Character> charToFind = new ArrayList<Character>();
      StringBuilder currSubs = new StringBuilder();
      String minSubs = "";

      for ( int i = 0; i < s.length(); i++ ) {

         fillCharToFind( charToFind, t );
         currSubs.setLength(0);

         if ( charToFind.contains( s.charAt(i) ) ) {

            int j = i;

            while ( charToFind.isEmpty() == false && j < s.length()) {
               currSubs.append( s.charAt( j ) );

               if ( charToFind.contains( s.charAt(j) ) ) {
                  charToFind.remove( Character.valueOf( s.charAt(j) ) );
               }

               j++;
            }

            if ( charToFind.isEmpty() &&  ( currSubs.length() < minSubs.length() || minSubs.length() == 0 ) ) {
               minSubs = currSubs.toString();
            }

         }

      }

      return minSubs;
   }

   private static void fillCharToFind( ArrayList<Character> charToFind, String t ) {
      if ( charToFind.size() == t.length() ) {
         return;
      }

      charToFind.clear();

      for ( int i = 0; i < t.length(); i++ ) {
         charToFind.add( t.charAt(i) );
      }
   }

}
