# Validate Binary Search Tree

[https://leetcode.com/problems/validate-binary-search-tree/](https://leetcode.com/problems/validate-binary-search-tree/)

## Description

Medium

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

- The left subtree of a node contains only nodes with keys **less than** the node's key.
- The right subtree of a node contains only nodes with keys **greater than** the node's key.
- Both the left and right subtrees must also be binary search trees.

 

**Example 1:**

```
    2
   / \
  1   3

Input: [2,1,3]
Output: true
```

**Example 2:**

```
    5
   / \
  1   4
     / \
    3   6

Input: [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.
```

Accepted

394,972

Submissions

1,541,262

## Solution

### High-level Idea

- Always stick to the *definition* and *physical characteristics* of a BST
  - Every node in the left subtree is less than the root's key
  - Every node in the right subtree is greater than the root's key
- Do it recursively and at each level, for the root
  - Check if its left subtree is a valid BST
    - Every node should be less than the root's key
    - May need to specify a min such that the nodes in the subtree are limited in a valid range
  - Check if its right subtree is a valid BST
    - Every node should be greater than the root's key
    - May need to specify a max such that the nodes in the subtree are limited in a valid range
  - If both of the subtrees are valid BST's, the current tree is a valid BST. Thus, return true to the parent

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
  public boolean isValidBST(TreeNode root) {
		// A null is a BST by definition
    if (root == null) {
      return true;
    }
    // Given a valid range of the keys in the subtree, check if it is a valid BST
    return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }
  
  private boolean isBST(TreeNode root, int min, int max) {
    // Base case: null
    if (root == null) {
      return true;
    }
    // Illegal case
    if (root.val < min || root.val > max) {
      return false;
    }
    // Only if both of my subtrees are BST, will I be a BST
    return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
  }
}
```

*This method can't pass the test with input `[2147483647]`, which is quite lame. For that, we'll have to use the way given in [LeetCode's solution](https://leetcode.com/problems/validate-binary-search-tree/solution/)*

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
  public boolean isValidBST(TreeNode root) {
		// A null is a BST by definition
    if (root == null) {
      return true;
    }
    // Given a valid range of the keys in the subtree, check if it is a valid BST
    return isBST(root, null, null);
  }
  
  private boolean isBST(TreeNode root, Integer min, Integer max) {
    // Base case: null
    if (root == null) {
      return true;
    }
    // Illegal case
    if ((min != null && root.val <= min) || (max != null && root.val >= max)) {
      return false;
    }
    // Only if both of my subtrees are BST, will I be a BST
    return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
  }
}
```

### Complexity

- Time
  - We need to consider every single node once
  - O(n)
- Space
  - The depth of the recursion tree (# of recursive calls) depends on the height of the tree
  - O(height)
  - Worst case: O(n)