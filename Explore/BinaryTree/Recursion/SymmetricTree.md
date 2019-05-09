# Symmetric Tree

[https://leetcode.com/problems/symmetric-tree/](https://leetcode.com/problems/symmetric-tree/)

## Description

Easy

Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree `[1,2,2,3,4,4,3]` is symmetric:

```
    1
   / \
  2   2
 / \ / \
3  4 4  3
```

 

But the following `[1,2,2,null,3,null,3]` is not:

```
    1
   / \
  2   2
   \   \
   3    3
```

 

**Note:**
Bonus points if you could solve it both recursively and iteratively.

Accepted

391,045

Submissions

903,444

## Solution

### High-level Idea

- Recursion
  - Base case: null ==> true, it is symmetric
  - Recursive rule
    - Is my left subtree symmetric?
    - Is my right subtree symmetric?
    - If my left subtree is the same as my right subtree, I am symmetric
      - Both children are null ==> true
      - Either of them is not null ==> false
      - Their values are different ==> false
- Iteration
  - Do a breadth-first search
    - Start: the root
    - Data structure: stores the nodes of each level and allows us to compare the children of the leftmost node against the rightmost node
      - Add and remove from both end ==> deque
    - Expand + Generation:
      - Expand a node from the queue
      - Generate its two children
      - Check if its left and right children are the same
      - Offer the children to the queue
    - Terminate: when the queue becomes empty

### Code

#### Recursion

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
  public boolean isSymmetric(TreeNode root) {
		// Base case
    if (root == null) {
      return true;
    }
    return isSymmetricBinaryTree(root.left, root.right);
  }
  
  private boolean isSymmetricBinaryTree(TreeNode left, TreeNode right) {
    if (left == null && right == null) {
      return true;
    } else if (left == null || right == null) {
      return false;
    } else if (left.val != right.val) {
      return false;
    }
    return isSymmetricBinaryTree(left.left, right.right) &&
      		 isSymmetricBinaryTree(left.right, right.left);
  }
}
```

#### Iteration

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
  public boolean isSymmetric(TreeNode root) {
    // TODO(CL): Implement an iterative solution
  }
}
```

### Complexity

#### Recurion

- Time
  - There are n nodes in the tree and we need to consider each one of them
  - O(n)
- Space
  - O(height)