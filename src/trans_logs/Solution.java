package trans_logs;

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
    * Complete the 'processLogs' function below.
    *
    * The function is expected to return a STRING_ARRAY.
    * The function accepts following parameters:
    *  1. STRING_ARRAY logs
    *  2. INTEGER threshold
    */

   public static List<String> processLogs(List<String> logs, int threshold) {
      // Write your code here

      HashMap<String,Integer> trans_count = new HashMap<>();

      for ( String transaction : logs ) {

         String[] fields = transaction.split( "\\s" );

         if ( trans_count.containsKey(fields[0]) ) {
            trans_count.put(fields[0], trans_count.get(fields[0]) + 1 );
         }
         else {
            trans_count.put(fields[0], 1 );
         }

         if ( !fields[0].equals( fields[1] ) ) {
            if ( trans_count.containsKey(fields[1]) ) {
               trans_count.put(fields[1], trans_count.get(fields[1]) + 1 );
            }
            else {
               trans_count.put(fields[1], 1 );
            }
         }

      }

      List<Integer> users = new ArrayList<>(
         trans_count.keySet()
         .stream()
         .filter( key -> trans_count.get(key) >= threshold )
         .map( key -> Integer.valueOf( key ) ).
         toList()
      );

      System.out.println( users );

      Collections.sort( users );

      return users.stream().map( user -> Integer.toString( user ) ).toList();
   }

}

public class Solution {
   public static void main(String[] args) throws IOException {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      int logsCount = Integer.parseInt(bufferedReader.readLine().trim());

      List<String> logs = IntStream.range(0, logsCount).mapToObj(i -> {
               try {
                  return bufferedReader.readLine();
               } catch (IOException ex) {
                  throw new RuntimeException(ex);
               }
            })
            .collect(toList());

      int threshold = Integer.parseInt(bufferedReader.readLine().trim());

      List<String> result = Result.processLogs(logs, threshold);

      bufferedWriter.write(
            result.stream()
                  .collect(joining("\n"))
                  + "\n"
      );

      bufferedReader.close();
      bufferedWriter.close();
   }
}
