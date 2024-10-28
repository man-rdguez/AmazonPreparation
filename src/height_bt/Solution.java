package height_bt;

import java.util.ArrayList;
import java.util.List;

public class Solution {

   static Node root = null;

   static class Node {
      int  data;
      Node left;
      Node right;

      Node( int data ) {
         this.data = data;
         left = null;
         right = null;
      }

   }

   private static void insertNodes( List<Integer> items, Node node ) {
      if ( items.isEmpty() ) {
         return;
      }

      root = new Node( items.get( 0 ) );

      for ( int i = 1; i < items.size(); i++ ) {
         insertNode( root, items.get( i ) );
      }
   }

   private static void insertNode( Node node, int data ) {

      if ( node == null ) {
         node = new Node( data );
         return;
      }

      if ( node.data == data ) {
         return;
      }

      if ( data > node.data ) {
         if ( node.right == null ) {
            node.right = new Node( data );
            return;
         } else {
            insertNode( node.right, data );
         }
      }

      if ( data < node.data ) {
         if ( node.left == null ) {
            node.left = new Node( data );
            return;
         } else {
            insertNode( node.left, data );
         }
      }

   }

   private static void printTree( Node node) {

      if ( node == null ) {
         return;
      }

      printTree( node.left );
      System.out.print( node.data + ", " );
      printTree( node.right );
   }

   public static int getHeight(Node root) {
      	// Write your code here.
      if ( root == null ) {
         return 0;
      }

      int leftHeight = getHeight( root.left );
      int rightHeight = getHeight( root.right );

      return Math.max( leftHeight, rightHeight ) + 1;
  }

   public static int height(Node root) {
      return getHeight( root ) - 1;
   }

   public static void main(String[] args) {

      List<Integer> items = new ArrayList<Integer>(List.of(3, 2, 1, 5, 4, 6, 7));
      System.out.println(items);

      insertNodes(items, root);

      printTree(root);

      System.out.println("\nTree height: " + height(root));

   }

}