package shortest_sub_segment;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.joining;

public class Solution {

   private static void loadWords( ArrayList<String> words, ArrayList<String> wordsToFind, int numOfWords ){
      if ( wordsToFind.size() == words.size() ){
         return;
      }

      if ( wordsToFind.size() > 0 ) {
         wordsToFind.clear();
      }

      wordsToFind.addAll( words );
   }

   private static String[] phraseWords( String text ) {
      StringBuilder newText = new StringBuilder();
      String alphabet = "abcdefghijklmnopqrstuvwxyz";

      for ( int i = 0; i < text.length(); i++ ) {
         if ( alphabet.contains( String.valueOf( text.toLowerCase().charAt(i) ) )
               || text.charAt(i) == ' '
         ) {
            newText.append( text.charAt( i ) );
         }
      }

      return newText.toString().split( "\\s" );
   }

   public static void main(String[] args) throws IOException {

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      ArrayList<String> words = new ArrayList<>();
      ArrayList<String> wordsToFind = new ArrayList<>();

      String text = "";
      int numOfWords = 0;

      BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
      text = br.readLine();
      //System.out.println("text: " + text);
      numOfWords = Integer.parseInt(br.readLine());
      //System.out.println("numOfWords: " + numOfWords);
      StringBuilder word = new StringBuilder();

      for (int i = 0; i < numOfWords; i++) {
         word.append( br.readLine() );

         if ( !words.contains( word.toString() ) ) {
            words.add( word.toString() );
         }

         word.setLength(0);
      }

      System.out.println( "words:\n" + words.toString() );
      System.out.println( "words size: " + words.size() );

      String[] phraseWords = phraseWords(text);

      int shortestLength = 0;
      String shortestSubSegment = "";
      StringBuilder subSegment = new StringBuilder();

      outer:
      for (int i = 0; i < phraseWords.length - numOfWords + 1; i++) {

         loadWords(words, wordsToFind, numOfWords);

         if (wordsToFind.contains(phraseWords[i].toLowerCase())) {

            int j = i;
            int currentLength = 0;
            subSegment.setLength(0);

            while (wordsToFind.size() > 0 && j < phraseWords.length) {
               subSegment.append(phraseWords[j] + " ");
               currentLength++;

               if (wordsToFind.contains(phraseWords[j].toLowerCase())) {
                  wordsToFind.remove(phraseWords[j].toLowerCase());
               }

               j++;
            }
            /*
            System.out.println( "currentLength: " + currentLength );
            System.out.println( "subSegment: " + subSegment.toString() );
            System.out.println( "wordsToFind: " + wordsToFind );
            System.out.println( "shortestSubSegment: " + shortestSubSegment );
            System.out.println( "shortestLength: " + shortestLength );
            */

            if (wordsToFind.isEmpty() ) {
               if (currentLength < shortestLength || shortestLength == 0) {
                  shortestLength = currentLength;
                  shortestSubSegment = subSegment.toString().trim();
               }
            }
            else {
               break outer;
            }

         }

      }

      if (shortestSubSegment.length() > 0) {
         System.out.print(shortestSubSegment);
         bw.write(shortestSubSegment);
      } else {
         bw.write("NO SUBSEGMENT FOUND");
      }

      br.close();
      bw.close();

   }

}
