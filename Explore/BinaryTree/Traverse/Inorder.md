# Binary Tree Inorder Traversal

https://leetcode.com/problems/binary-tree-inorder-traversal/[](https://leetcode.com/problems/binary-tree-inorder-traversal/)

## Description

Medium

Given a binary tree, return the *inorder* traversal of its nodes' values.

**Example:**

```
Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
```

**Follow up:** Recursive solution is trivial, could you do it iteratively?

Accepted

446,174

Submissions

793,933

## Solution

### High-level Idea

- Recursive method
  - Trivial, so it is not the focus
  - Base case: reaching a null ==> return an empty list
  - Recursive rule:
    - At each level, ask the two children for their subtree's in-order traversal
    - Add the results in the order of: *left subtree + root.key + right subtree*
    - Return the result to my parent
- Iterative method
  - Use a stack to keep track of the nodes that have been checked but have a left child available
  - Use a current node pointer to keep track of the previously traversed node
    - Such that we could have a reference to the previous node when we come back from the stack
  - Keep traversing to the left and push the current node to the stack
  - When reaching a null, the current node of interest is the stack's top
  - Traverse until the stack or the current node becomes empty

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
  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    // Use a stack to keep track of the nodes that have been traversed
    // and have a left child available
    Deque<TreeNode> stack = new LinkedList<>();
    // Use a current node pointer to keep track of the previously traversed node
    TreeNode current = root;
    // Traverse until current or stack becomes empty
    while (current != null || !stack.isEmpty()) {
      // Keep traversing to the left and keep track of the node that have been traversed
      while (current != null) {
        stack.offerFirst(current);
        current = current.left;
      }
      current = stack.pollFirst();
      result.add(current.val);
      current = current.right;
    }
    return result;
  }
}
```

### Complexity

- Time
  - Every single node is traversed
  - O(n)
- Space
  - A stack is used
  - In the worst case, when the tree is a linked list (each node has only a left child), it costs O(n)