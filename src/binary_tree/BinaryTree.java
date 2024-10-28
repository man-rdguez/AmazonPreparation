package binary_tree;

// Java program to create and to find the height of a binary tree by recursive way


// util package is imported to use classes like Queue and LinkedList
import java.util.*;



// A class named Node is created representing a single node of a binary tree
class Node{


   // The class Node has three class variables named key and left and right of int type and Node type respectively.


   // the key variable holds the actual value that is assigned to that node of the binary tree
   int key;

   // left and right variables that are of Node type will be used to store the left and right child nodes of the parent of the binary tree
   Node left, right;

   // a parameterized constructor is created to create and add data to the node at the same time.
   public Node(int item)
   {
      key = item;
      left = right = null;
   }

}
// end of node class definition

// A public class named BinaryTree is created having two constructors and methods to print the binary tree level-wise.

class BinaryTree{

   // A static variable named root_node is created that will represent the node of the binary tree
   static Node root_node;




   // A parametrized constructor of the BinaryTree class is written having the key as a parameter
   BinaryTree(int key)
   {

// here we are constructing a new node and assigning it to the root node
      root_node = new Node(key);
   }

   BinaryTree()
   {
      root_node = null;
   }



// a public static function named print tree is created to print all the nodes in the tree level-wise starting from the root node

   public static void printTree()
   {
      int h = height(root_node);
      int i;
      for (i=1; i<=h; i++){
         printCurrentLevel(root_node, i);
         System.out.println();
      }
   }


// a public static function named height is created to fund the height of the binary tree starting from the root node to the deepest leaf node that is present in the binary tree

   // the root node of the binary tree is passed as a parameter to the public static function named height
// the height function is called recursively until the node returned is NULL to find the height of the binary tree
   public static int height(Node root){

      // the root is null then the height of the tree will be Zero
      if (root == null)
         return 0;
      else
      {
         /* compute  height of each subtree */
         int lheight = height(root.left);
         int rheight = height(root.right);

         /* use the larger one */
         // height of both the sub trees is calcualted and which one is higher is used.
         if (lheight > rheight)
            return(lheight+1);
         else
            return(rheight+1);
      }
   }


// a Public static function named printCurrentLevel is created to print al the nodes that are present in that level
// this function is called repeatedly for each level of the binary tree to print all the nodes in that particular level

   public static void printCurrentLevel (Node root ,int level)
   {
      if (root == null)
         return;
      if (level == 1)
         System.out.print(root.key + " ");
      else if (level > 1)
      {
         printCurrentLevel(root.left, level-1);
         printCurrentLevel(root.right, level-1);
      }
   }


//the main function is created to create an object of the BinaryTree class and call the printTree method to level-wise print the nodes of the binary tree and the height method to find the height of the binary tree

   public static void main(String[] args){




      // first of all we have created an Object of the BinaryTree class that will represent the binary tree
      BinaryTree tree = new BinaryTree();


      // now a new node with the value as 150 is added as the root node to the Binary Tree
      tree.root_node = new Node(150);

      // now a new node with the value 250 is added as a left child to the root node
      tree.root_node.left = new Node(250);

      // now a new node with the value 270 is added as a right child to the root node
      tree.root_node.right = new Node(270);


      // now a new node with the value 320 is added as a left child to the left node of the previous level node
      tree.root_node.left.left = new Node(320);


      // now a new node with the value 350 is added as a right child to the right node of the previous level node
      tree.root_node.left.right = new Node(350);

    /*
               150
            /          \
       250         270
        /     \       /      \
      320 350  null  null

*/



      System.out.println("Printing the nodes of tree level wise :");
      System.out.println("Level order traversal : ");
      tree.printTree();

      // height of the binary tree is calculated bypassing the root as parameter to the height() function.
      int h = tree.height(tree.root_node);

      System.out.println("The height of the Binary tree is : " + h );

   }
}
// end of the BinaryTree class