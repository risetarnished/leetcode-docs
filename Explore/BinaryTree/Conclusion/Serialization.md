# Serialize and Deserialize Binary Tree

[https://leetcode.com/problems/serialize-and-deserialize-binary-tree/](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/)

## Description

Hard

`Serialization` is the process of converting a data structure or object into `a sequence of bits` so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

**Example:** 

```
You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
```

**Clarification:** The above format is the same as [how LeetCode serializes a binary tree](https://leetcode.com/faq/#binary-tree). You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

**Note:** Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

Accepted

177,663

Submissions

438,618

## Solution

### High-level Idea

- Implement in the way in which LeetCode serializes (and maybe deserializes) a binary tree
- Serialization
  - Do a breadth-first search
    - Start
      - Root
    - Data structure
      - FIFO queue
    - Expansion/Generation
      - In each level, expand a node from the queue
        - Add its key to the result
        - Offer its children to the queue
          - If there is no child, offer a *null* 
    - Termination
      - Terminate when the queue becomes empty
- Deserialization
  - The input is a level-order traversal of the binary tree
  - We can follow the traversal order to build a tree
  - Also do a breadth-first search
    - Start from building a root using the first key from the input
      - offer the root to a FIFO queue
    - In each level in the queue
      - Expand a node from the queue
      - Build its left and right children
      - Connects them together
      - Offer the children to the queue
    - Terminate when the queue becomes empty or we have built all nodes

### Code

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *   int val;
 *   TreeNode left;
 *   TreeNode right;
 *   TreeNode(int x) { val = x; }
 * }
 */
public class Serialization {

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder("[");
    if (root == null) {
      sb.append(']');
      return sb.toString();
    }
    // We are adding null's to the queue to hold the positions
    // Therefore we cannot use ArrayDeque
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    // Use a boolean flag to tell us if the nodes in a level are all nulls
    boolean allNulls = false;
    while (!queue.isEmpty()) {
      // Break immediately if all nodes in this level are null
      if (allNulls) {
        break;
      }
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode curr = queue.poll();
        if (curr == null) {
          sb.append("null,");
          continue;
        }
        sb.append(curr.val);
        sb.append(',');
        queue.offer(curr.left);
        queue.offer(curr.right);
        // Update the flag
        if (i == 0) {
          allNulls = curr.left == null && curr.right == null;
        } else {
          allNulls = curr.left == null && curr.right == null && allNulls;
        }
      }
    }
    // Post-proccessing: remove the extra ',' at the end and add a final ']'
    sb.deleteCharAt(sb.length() - 1);
    sb.append(']');
    return sb.toString();
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    if (data == null || data.length() == 0) {
      return null;
    }
    // Parse the input string to an array of string
    String[] keys = data.replaceAll("\\[|\\]", "").split(",");
    // Validate the input to not be anything that looks like "" or "[]"
    if (keys.length == 0 || (keys.length == 1 && keys[0].length() == 0)) {
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

  private TreeNode buildNode(String[] keys, int index) {
    if (keys[index].equals("null")) {
      return null;
    }
    return new TreeNode(Integer.parseInt(keys[index]));
  }

  public static void main(String[] args) {
    Serialization Serialization = new Serialization();
    TreeNode root = Serialization.deserialize("[1,2,3,null,null,4,5]");
    System.out.println(Serialization.serialize(root));

    root = Serialization.deserialize("[]");
    System.out.println(Serialization.serialize(root));

    root = Serialization.deserialize("");
    System.out.println(Serialization.serialize(root));

    root = Serialization.deserialize("[3,2,4,1]");
    System.out.println(Serialization.serialize(root));
  }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

```

### Complexity

- Time
  - Both the serialization and deserialization take O(n) for the breadth-first search process
- Space
  - Both process cost O(width) where width is the max width of the tree for the FIFO queue

### TODO

- The current serialization method will print out trailing null's following the last node with a real key in the last level