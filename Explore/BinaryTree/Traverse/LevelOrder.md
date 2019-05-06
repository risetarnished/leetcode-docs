# Binary Tree Level Order Traversal

[https://leetcode.com/problems/binary-tree-level-order-traversal/](https://leetcode.com/problems/binary-tree-level-order-traversal/)

## Description

Given a binary tree, return the *level order* traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```



return its level order traversal as:

```
[
  [3],
  [9,20],
  [15,7]
]
```



Accepted

365,340

Submissions

760,006

## Solution

### High-level Idea

This is what is called *level-order traversal of binary tree*

- Do a *Breadth-First Search*
- Data structure: FIFO queue
  - Use a FIFO queue to keep track of the nodes that are about to be visited
- Basic flow of a breadth-first search process:
  - Starting point: The root of the tree
  - Definition 1: Expand a node *s*
  - Definition 2: Generate the children of *s*
  - Data structure: Maintain a FIFO queue, put all generated nodes into the queue
  - Termination condition: do a loop until the queue becomes empty
- Keep a record of the queue's size before expanding the nodes each time such that we can iterate only the number of nodes in this level

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
class Solution {
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    // Use a FIFO queue to keep track of the nodes to be traversed
    Queue<TreeNode> queue = new LinkedList<>();
    // Start from the root of the tree
    queue.offer(root);
    // Terminate when the queue becomes empty
    while (!queue.isEmpty()) {
      // Only check the nodes in the current level
      List<Integer> level = new ArrayList<>();
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        // Expand a node, add it to the current level
        TreeNode curr = queue.poll();
        level.add(curr.val);
        // Generate its two children
        if (curr.left != null) {
          queue.offer(curr.left);
        }
        if (curr.right != null) {
          queue.offer(curr.right);
        }
      }
      // Add the current level's nodes to the result
      result.add(level);
    }
    return result;
  }
}
```

### Complexity

- Time
  - Every single node in the tree is traversed
  - O(n)
- Space
  - A FIFO queue is used and its size could be as large as the tree
  - O(n)