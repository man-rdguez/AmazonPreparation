package shortest_sub_segment;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {

   private static void loadWords( ArrayList<String> words, ArrayList<String> wordsToFind ){
      if ( wordsToFind.size() == words.size() ){
         return;
      }

      if ( wordsToFind.size() > 0 ) {
         wordsToFind.clear();
      }

      wordsToFind.addAll( words );
      Collections.sort( wordsToFind );
   }

   private static String getText( BufferedReader br ) throws IOException {
      String string = br.readLine();

      String alphabet = "abcdefghijklmnopqrstuvwxyz";
      StringBuilder text = new StringBuilder();

      for ( int i = 0; i < string.length(); i++ ) {
         if ( alphabet.contains( String.valueOf( string.toLowerCase().charAt(i) ) )
               || string.charAt(i) == ' '
         ) {
            text.append( string.charAt( i ) );
         }
      }

      return text.toString();
   }

   public static ArrayList<String> getWords( BufferedReader br ) throws IOException {

      ArrayList<String> words = new ArrayList<>();

         int numOfWords = Integer.parseInt(br.readLine());
         StringBuilder word = new StringBuilder();

         for (int i = 0; i < numOfWords; i++) {
            word.append( br.readLine() );

            if ( !words.contains( word.toString() ) ) {
               words.add( word.toString() );
            }

            word.setLength(0);
         }

      return words;
   }

   public static void main(String[] args) throws IOException {

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String text = getText( br );
      ArrayList<String> words = getWords( br );
      br.close();

      ArrayList<String> wordsToFind = new ArrayList<>();

      int shortestLength = 0;
      int shortestSubSegBeg = 0;
      int shortestSubSegEnd = 0;
      boolean noMoreSubSegs = false;


      int i = 0;
      int beg = 0;
      int end = text.length();
      int nextWordPos = 0;

      while ( i < text.length() && noMoreSubSegs == false ) {

         loadWords(words, wordsToFind );

         if ( text.charAt(i) == ' ' ) {
            i++;
         }
         else {
            beg = i;

            while ( i < text.length() && text.charAt(i) != ' ' ) {
               i++;
            }

            end = i;
            nextWordPos = i + 1;
         }

         if ( wordsToFind.contains( text.substring(beg, end).toLowerCase() ) ) {

            int subSegmentBeg = beg;
            int subSegmentEnd = 0;
            int currentLength = 0;

            while ( wordsToFind.size() > 0 && i < text.length() ) {
               subSegmentEnd = end;
               currentLength++;

               if ( wordsToFind.contains( text.substring(beg, end).toLowerCase() ) ) {
                  wordsToFind.remove( text.substring(beg, end).toLowerCase() );
               }

               if ( text.charAt(i) == ' ' ) {
                  i++;
               }
               else {
                  beg = i;

                  while ( i < text.length() && text.charAt(i) != ' ' ) {
                     i++;
                  }

                  end = i;
               }

            }

            if ( wordsToFind.isEmpty() ) {
               if ( currentLength < shortestLength || shortestLength == 0 ) {
                  shortestLength = currentLength;
                  shortestSubSegBeg = subSegmentBeg;
                  shortestSubSegEnd = subSegmentEnd;
               }

               i = nextWordPos;
            }
            else {
               noMoreSubSegs = true;
            }

         }

      }

      BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      if ( shortestSubSegBeg != shortestSubSegEnd ) {
         System.out.println( text.substring( shortestSubSegBeg, shortestSubSegEnd ) );
         bw.write( text.substring( shortestSubSegBeg, shortestSubSegEnd ) );
      } else {
         bw.write("NO SUBSEGMENT FOUND");
      }

      bw.close();

   }

}
