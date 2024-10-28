package amazon_customer_reviews;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Result {
   /*
    * Complete the 'searchSuggestions' function below.
    *
    * The function is expected to return a 2D_STRING_ARRAY.
    * The function accepts following parameters:
    * 1. STRING_ARRAY repository
    * 2. STRING customerQuery
    */

   public static List<List<String>> searchSuggestions( List<String> repository, String customerQuery) {

      //List<String> sortedRepository = new ArrayList<>( repository );
      //repository.stream().map( word -> word.toLowerCase() );

      List<String> sortedRepository = new ArrayList<>( repository.stream().map(word -> word.toLowerCase() ).toList() );

      Collections.sort( sortedRepository );

      List<List<String>> suggestions = new ArrayList<>();

      for ( int i = 1; i < customerQuery.length(); i++ ) {

         List<String> suggestion = new ArrayList<>();

         for( String word : sortedRepository ) {

            String tempQry = customerQuery.substring( 0, i + 1 ).toLowerCase();

            System.out.println( tempQry);

            if ( word.startsWith( tempQry ) && suggestion.size() < 3 ) {
               suggestion.add( word );
            }

         }

         if ( suggestion.size() > 0  ) {
            suggestions.add( suggestion );
         }

      }

      return suggestions;
   }

}

public class Solution {

   public static void main(String[] args) {

      List<String> repository = new ArrayList<>( List.of( "mobile", "Mouse", "moneypot", "monitor", "moUsepad" ) );
      String customerQuery = "mouSe";


      List<List<String>> suggestions = Result.searchSuggestions( repository, customerQuery);

      for ( List<String> suggestion : suggestions ) {
         System.out.println( suggestion );
      }
      //System.out.println( Result.searchSuggestions( repository, customerQuery ) );
   }



}
