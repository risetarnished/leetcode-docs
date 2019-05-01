# Binary Tree Preorder Traversal

https://leetcode.com/problems/binary-tree-preorder-traversal/[](https://leetcode.com/problems/binary-tree-preorder-traversal/)

## Description

Medium

Given a binary tree, return the *preorder* traversal of its nodes' values.

**Example:**

```
Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]
```

**Follow up:** Recursive solution is trivial, could you do it iteratively?

Accepted

328,417

Submissions

643,396

## Solution

### High-level Idea

- Recursive method skipped
- Iterative method
  - Use a stack to keep track of the previously traversed nodes
  - Preorder: try to go left first
    - So we need to check if the right child is available
    - If so, push it to the stack before checking the left child
  - Stop when the stack becomes empty (every node has been traversed)

### Code

```java
/**
* Definition for a binary tree node.
* public class TreeNode {
*     int val;
*     TreeNode left;
*     TreeNode right;
*     TreeNode(int x) { val = x; }
* }
*/
class Solution {
  public List<Integer> preorderTraversal(TreeNode root) {
    // return recursivelyPreorder(root);
    return iterativelyPreorder(root);
  }
  
  /**
   * Recursively traverse the binary tree in preorder fashion
   *
   * Time complexity
   *   - Every single node in the tree needs to be checked
   *   - O(n)
   * Space complexity
   *   - The recursion tree should have the same depth as the binary tree
   *   - Thus, space complexity is dependent on the height of the tree
   *   - O(height)
   */
  private List<Integer> recursivelyPreorder(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    List<Integer> left = recursivelyPreorder(root.left);
    List<Integer> right = recursivelyPreorder(root.right);
    result.add(root.val);
    result.addAll(left);
    result.addAll(right);
    return result;
  }
  
  /**
   * Iteratively traverse the binary tree in preorder fashion
   *
   * Time
   *   - Every single node in the tree is checked
   *   - O(n)
   * Space
   *   - A stack is used and its size could grow as large as
   *     the size of the tree (if the tree is a linked list)
   *   - O(height)
   */
  private List<Integer> iterativelyPreorder(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    Deque<TreeNode> stack = new ArrayDeque<>();
    stack.offerFirst(root);
    while (!stack.isEmpty()) {
      TreeNode curr = stack.pollFirst();
      // Traversing to the left before going right
      // So, push the right child first
      if (curr.right != null) {
        stack.offerFirst(curr.right);
      }
      if (curr.left != null) {
        stack.offerFirst(curr.left);
      }
      result.add(curr.val);
    }
    return result;
  }
}
```

### Complexity

- Recursion
  - Time
    - Every single node is traversed
    - O(n)
  - Space
    - The number of recursive calls (depth of the recursion tree/call stack) is dependent on the depth of the binary tree
      - worst case: O(n) when the binary tree is a linked list
    - O(height)
- Iteration
  - - Time
      - Every single node is traversed
      - O(n)
    - Space
      - The size of the stack is dependent on the depth of the binary tree
        - worst case: O(n) when the binary tree is a linked list
      - O(height)

