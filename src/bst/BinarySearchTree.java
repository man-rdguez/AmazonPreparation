package bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTree {

   class Node {

      int value;
      Node left;
      Node right;

      Node( int value ) {
         this.value = value;
         left = null;
         right = null;
      }

   }

   private Node root;

   public Node getRoot() {
      return root;
   }

   public boolean insert( int value ) {

      Node newNode = new Node( value );

      if ( root == null ) {
         root = newNode;
         System.out.println( "root null, new value: " + root.value );
         return true;
      }

      Node currNode = root;

      while ( true ) {

         /*
         if ( currNode == null ) {
            currNode = newNode;
            System.out.println( "currNode null, new value: " + currNode.value );
            return true;
         }
         */

         if ( newNode.value == currNode.value ) {
            return false;
         }
         else if ( newNode.value < currNode.value ) {
            if ( currNode.left == null ) {
               currNode.left = newNode;
               return true;
            }
            currNode = currNode.left;
         }
         else {
            if ( currNode.right == null ) {
               currNode.right = newNode;
            }
            currNode = currNode.right;
         }

         if ( root.left != null ) {
            System.out.println("root.left.value: " + root.left.value);
         }
         if ( root.right != null ) {
            System.out.println("root.right.value: " + root.right.value);
         }
      }

   }

   public boolean contains( int value ) {

      Node currNode = root;

      while ( true) {

         if ( currNode == null ) {
            return false;
         }

         if ( value == currNode.value ) {
            return  true;
         }
         else if ( value < currNode.value ) {
            currNode = currNode.left;
         } else {
            currNode = currNode.right;
         }

      }

   }

   public void levelOrder( Node root ) {

      Node currNode = root;
      Queue<Node> queue = new LinkedList<>();
      List<Integer> values = new ArrayList<>();
      queue.add( currNode );

      while ( !queue.isEmpty() ) {
         currNode = queue.remove();
         values.add( currNode.value );

         if ( currNode.left != null ) {
            queue.add( currNode.left );
         }

         if ( currNode.right != null ) {
            queue.add( currNode.right );
         }

      }

      for ( int value : values ) {
         System.out.print( value + " " );
      }

   }

   public static void main( String[] args ) {
      BinarySearchTree tree = new BinarySearchTree();

      /*
         tree.insert(10);
         tree.insert(5);
         tree.insert(15);
         tree.insert(99);
         tree.insert(3);
         tree.insert(20);
      */

      tree.insert(1);
      tree.insert(2);
      tree.insert(5);
      tree.insert(3);
      tree.insert(6);
      tree.insert(4);

      /*
      System.out.println( tree.root.value );
      System.out.println( tree.getRoot().value );
      System.out.println( tree.getRoot().left.value );
      System.out.println( tree.getRoot().right.value );
      */

      System.out.println( "Value 17 exists: " + tree.contains( 17 ) );

      tree.levelOrder( tree.getRoot() );

   }

}
