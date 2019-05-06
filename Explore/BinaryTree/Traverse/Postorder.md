# Binary Tree Postorder Traversal

[https://leetcode.com/problems/binary-tree-postorder-traversal/](https://leetcode.com/problems/binary-tree-postorder-traversal/)

## Description

Hard

Given a binary tree, return the *postorder* traversal of its nodes' values.

**Example:**

```
Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [3,2,1]
```

**Follow up:** Recursive solution is trivial, could you do it iteratively?

Accepted

252,950

Submissions

526,862

## Solution

### High-level Idea

- Like the [Binary Tree Inorder Traversal](Binary Tree Inorder Traversal.md) problem, use a stack to keep track of the previously visited nodes because we need to come back along the path
- Use a helper node to keep track of the previously visited node
- Check 3 conditions
  - If we are coming down from a parent
    - Try to go left before right because this is *post-order*
    - If neither of the children is available
      - Update result
  - If we are coming back from the left child
    - Left subtree traversal has just finished, try to go right
    - If no right child available
      - Update result
  - If we are coming back from the right child
    - Both left and right subtrees traversals have finished
    - Update result and poll the stack

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
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    // Use a stack to keep track of the node that we will come back later
    Deque<TreeNode> stack = new ArrayDeque<>();
    // Use a helper node to keep track of the previously visted node
    TreeNode prev = null;
    stack.offerFirst(root);
    while (!stack.isEmpty()) {
      // Hold on to the stack's top because we may come back
      TreeNode curr = stack.peekFirst();
      if (prev == null || curr == prev.left || curr == prev.right) {
        if (curr.left != null) {
          stack.offerFirst(curr.left);
        } else if (curr.right != null) {
          stack.offerFirst(curr.right);
        } else {
          // Neither left or right child available
          // This is the node that needs to go into the result
          curr = stack.pollFirst();
          result.add(curr.val);
        }
      } else if (prev == curr.left) {
        if (curr.right != null) {
          stack.offerFirst(curr.right);
        } else {
          curr = stack.pollFirst();
          result.add(curr.val);
        }
      } else if (prev == curr.right) {
        curr = stack.pollFirst();
        result.add(curr.val);
      }
      // Update the previously visited node to current
      prev = curr;
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