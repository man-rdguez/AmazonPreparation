package height_binary_tree;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.stream;

class Node {
   int value;
   Node left;
   Node right;

   public Node(int value) {
      this.value = value;
   }
}

class Result {

   static Node root = null;
   static int max = 0;
   static int currentMax = 0;

   public static Node createBinaryTree(List<Integer> values ) {

      for ( int i = 0; i < values.size(); i++ ) {
         insertNode( values.get(i) );
      }

      return root;
   }

   public static void insertNode( int value ) {

      Node newNode = new Node(value);

      if ( root == null ) {
         root = newNode;
         return;
      }

      Node currentNode = root;

      insertNode( newNode, currentNode );

   }

   public static void insertNode( Node newNode, Node currentNode ) {

      if ( newNode.value == currentNode.value ) {
         return;
      }

      if ( newNode.value < currentNode.value ) {
         if ( currentNode.left == null ) {
            currentNode.left = newNode;
            return;
         }
         else {
            currentNode = currentNode.left;
            insertNode( newNode, currentNode );
         }

      }
      else {
         if ( currentNode.right == null ) {
            currentNode.right = newNode;
            return;
         }
         else {
            currentNode = currentNode.right;
            insertNode( newNode, currentNode );
         }
      }

   }

   public static void printBinaryTree(Node node) {
      if ( node == null ) {
         return;
      }
      else {
         printBinaryTree(node.left);
         System.out.println( node.value );
         printBinaryTree(node.right);
      }

   }

    public static int returnBackHeight( Node node ) {

      root = node;

      if ( node == null ) {
         return 0;
      }
      else {
         return calculateHeight( node );
      }

    }

   private static int calculateHeight( Node node ) {

      if ( node == null ) {
         return 0;
      }

      int leftHeight = calculateHeight(node.left);
      int rightHeight = calculateHeight(node.right);

      if ( node == root ) {
         return Math.max(leftHeight, rightHeight);
      }

      return Math.max(leftHeight, rightHeight) + 1;
   }

   public static int returnHeight( Node root ) {
      getHeight( root );

      return max;
   }

   public static void getHeight(Node node) {

      if ( node == null ) {

         currentMax--;

         if (currentMax > max ) {
            max = currentMax;
         }

         System.out.println( "currentMax in if before= " + currentMax );

         System.out.println( "currentMax in if = " + currentMax );
         System.out.println( "Max in if = " + max );
         return;
      }
      else {
         currentMax++;
         getHeight(node.left);
         System.out.println( "currentMax in else = " + currentMax );
         currentMax++;
         getHeight(node.right);
         currentMax--;
      }

   }

   public static int height(Node root) {
      countLeft( root );
      countRight( root );

      if ( max == 1 ) {
         max = 0;
      }

      return max;
   }

   public static void countLeft( Node node ) {
      if ( node == null ) {
         if (currentMax > max ) {
            max = currentMax;
         }

         currentMax = 0;

         return;
      }
      else {
         countLeft(node.left);
         currentMax++;
      }

   }

   public static void countRight( Node node ) {
      if ( node == null ) {
         if (currentMax > max ) {
            max = currentMax;
         }

         currentMax = 0;

         return;
      }
      else {
         countRight(node.right);
         currentMax++;
      }

   }

}

public class Solution {

   public static void main(String[] args) {
      /*
      Enter your code here.
      Read input from STDIN.
      Print output to STDOUT.
      Your class should be named Solution.
      */

      /*

      for ( String value : args ) {
         System.out.println( value);
      }

      int nodes = Integer.parseInt(args[0]);
      //System.out.println( "Number of nodes: " + nodes );

      List<Integer> values =  Arrays.stream( args[1].split( "\\s+" ) ).map( value -> Integer.parseInt( value ) ).toList();

       */

      /*
      From terminal:
      manuel@thinkpad:~/IdeaProjects/AmazonPreparation/out/production/AmazonPreparation$ java -cp . height_binary_tree.Solution < height_binary_tree/args.txt
      3

      cases:
       7
3 5 2 1 4 6 7
R=3

1
15
R=0

5
3 1 7 5 4
R=3

15
1 3 2 5 4 6 7 9 8 11 13 12 10 15 14
R=9
       */

      Scanner scanner = new Scanner(System.in);
      int nodes = Integer.parseInt( scanner.nextLine() );

      List<Integer> values =  Arrays.stream( scanner.nextLine().split( "\\s+" ) ).map( value -> Integer.parseInt( value ) ).toList();

      //System.out.println( "values: " + values );

      Node root = Result.createBinaryTree( values );

      Result.printBinaryTree( root );

      //System.out.println( "Tree height: " + Result.height( root ) );
      //System.out.println( Result.height( root ) );
      //System.out.println( "returnHeight: " + Result.returnHeight( root ) );
      System.out.println( "calcHeight: " + Result.returnBackHeight( root ) );
   }

}