package Explore.BinarySearchTree.Introduction.BSTIterator;

import java.util.*;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

public class BSTIterator {
  // Use a stack to store the traversed nodes
  private Deque<TreeNode> stack;

  public BSTIterator(TreeNode root) {
    stack = new ArrayDeque<>();
    // From the root, do a "left traversal" and push all
    // left children along the path onto the stack
    TreeNode curr = root;
    while (curr != null) {
      stack.offerFirst(curr);
      curr = curr.left;
    }
  }

  /** @return the next smallest number */
  public int next() {
    if (stack.isEmpty()) {
      return Integer.MIN_VALUE;
    }
    // Pop the top element (which is the result) from the stack
    // and traverse to its right child
    TreeNode result = stack.pollFirst();
    TreeNode curr = result.right;
    while (curr != null) {
      stack.offerFirst(curr);
      curr = curr.left;
    }
    return result.val;
  }

  /** @return whether we have a next smallest number */
  public boolean hasNext() {
    return !stack.isEmpty();
  }

  public static void main(String[] args) {
    TreeNode root = buildTree(new Integer[] { 7, 3, 15, null, null, 9, 20 });
    BSTIterator iterator = new BSTIterator(root);
    System.out.println(iterator.next()); // return 3
    System.out.println(iterator.next()); // return 7
    System.out.println(iterator.hasNext()); // return true
    System.out.println(iterator.next()); // return 9
    System.out.println(iterator.hasNext()); // return true
    System.out.println(iterator.next()); // return 15
    System.out.println(iterator.hasNext()); // return true
    System.out.println(iterator.next()); // return 20
    System.out.println(iterator.hasNext()); // return false
  }

  private static TreeNode buildTree(Integer[] keys) {
    if (keys == null || keys.length == 0) {
      return null;
    }
    int keySize = keys.length;
    // Start from the first node
    // index represents the current key in the input used to build a node
    int index = 0;
    TreeNode root = buildNode(keys, index++);
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode curr = queue.poll();
        // Build the corresponding left and right children of this node
        TreeNode left = index >= keySize ? null : buildNode(keys, index++);
        TreeNode right = index >= keySize ? null : buildNode(keys, index++);
        curr.left = left;
        curr.right = right;
        // Add the non-null children to the queue
        if (left != null) {
          queue.offer(left);
        }
        if (right != null) {
          queue.offer(right);
        }
        // Stop if we have built all nodes
        if (index >= keySize) {
          break;
        }
      }
    }
    return root;
  }

  private static TreeNode buildNode(Integer[] keys, int index) {
    return keys[index] == null ? null : new TreeNode(keys[index]);
  }
}
/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
