# Maximum Depth of Binary Tree

[https://leetcode.com/problems/maximum-depth-of-binary-tree/](https://leetcode.com/problems/maximum-depth-of-binary-tree/)

## Description

Easy

Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

**Note:** A leaf is a node with no children.

**Example:**

Given binary tree `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```

return its depth = 3.

Accepted

495,447

Submissions

822,993

## Assumption

- The input binary tree should not be null
- The input binary tree is not guaranteed to be complete or balanced

## Solution

### High-level Idea

- Do a recursion from the root and ask both left and right children for their subtree's max depth respectively
- Base case: reach a null ==> return 0 (level starts from 1 when we are at a leaf node)
- Recursive rule
  - Ask the left child for the max depth of its subtree
  - Ask the right child for the max depth of its subtree
  - Return the longer one + 1 (myself) to the parent

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
  public int maxDepth(TreeNode root) {
    // Base case
    if (root == null) {
      return 0;
    }
    int leftDepth = maxDepth(root.left);
    int rightDepth = maxDepth(root.right);
    return Math.max(leftDepth, rightDepth) + 1;
  }
}
```

### Complexity

- Time
  - If there are n nodes in the binary tree, we will need to check the depth of each node
  - Total time is O(n)
- Space
  - The number of recursion calls depends on the height of the binary tree
  - O(height)