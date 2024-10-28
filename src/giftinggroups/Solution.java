package giftinggroups;

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
    * Complete the 'countGroups' function below.
    *
    * The function is expected to return an INTEGER.
    * The function accepts STRING_ARRAY related as parameter.
    */

   public static int countGroups(List<String> related) {
      // Write your code here

      for (int i = 0; i < related.size(); i++) {
         System.out.println( i + " -> " + related.get(i) );
      }

      List<List<Character>> groups = new ArrayList<List<Character>>();

      for (int i = 0; i < related.size(); i++) {

         for (int j = 0; j < related.size(); j++) {

            if (related.get(i).charAt(j) == '1') {

               // already in a group
               boolean hasAGroup = false;

               for ( int k = 0; k < groups.size(); k++ ) {
                  if ( groups.get(k).contains( (char) i ) ||
                       groups.get(k).contains( (char) j )
                  ) {
                     hasAGroup = true;
                     if ( !groups.get(k).contains( (char) i ) ) {
                        groups.get(k).add( (char) i  );
                     }
                     if ( !groups.get(k).contains( (char) j ) ) {
                        groups.get(k).add( (char) j );
                     }
                  }
               }

               if ( hasAGroup == false ) {
                  List<Character> group = new ArrayList<>();
                  group.add( (char) i );

                  if ( i != j ) {
                     group.add( (char) j );
                  }

                  groups.add( group );
               }

            }

         }

      }

      System.out.println( "groups.size(): " + groups.size() );
      return groups.size();
   }

}

public class Solution {
   public static void main(String[] args) throws IOException {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      int relatedCount = Integer.parseInt(bufferedReader.readLine().trim());

      List<String> related = IntStream.range(0, relatedCount).mapToObj(i -> {
               try {
                  return bufferedReader.readLine();
               } catch (IOException ex) {
                  throw new RuntimeException(ex);
               }
            })
            .collect(toList());

      int result = Result.countGroups(related);

      bufferedWriter.write(String.valueOf(result));
      bufferedWriter.newLine();

      bufferedReader.close();
      bufferedWriter.close();
   }
}
