# Lowest Common Ancestor of a Binary Tree

[https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)

## Description

Medium

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the [definition of LCA on Wikipedia](https://en.wikipedia.org/wiki/Lowest_common_ancestor): “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow **a node to be a descendant of itself**).”

Given the following binary tree: root = [3,5,1,6,2,0,8,null,null,7,4]

![img](https://assets.leetcode.com/uploads/2018/12/14/binarytree.png)

**Example 1:**

```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
```

**Example 2:**

```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
```

**Note:**

- All of the nodes' values will be unique.
- p and q are different and both values will exist in the binary tree.

Accepted

270,600

Submissions

734,184

## Assumption

- There are no duplicate keys in the binary tree
- The two nodes are guaranteed to be in the binary tree

## Solution

### High-level Idea

- Do a **bottom-up** recursion
- At each node, consider
  - Whether I am one of the target nodes
    - If yes
      - Since the two nodes are guaranteed to be present in the tree, the other target node should either be somewhere else or in my subtrees.
      - In either case, return myself to the parent.
    - Otherwise
      - Ask my left/right child to look for the target nodes in their subtrees
  - If _neither_ of the target nodes sits in my subtrees
    - Return null to the parent
  - If _either_ node is found in my subtrees
    - Return the found node to the parent
  - If _both_ nodes are found in my subtrees
    - Return their LCA to my parent (all the way up to the root)

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

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // Base case
    if (root == null || root == p || root == q) {
      return root;
    }
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    if (left != null && right != null) {
      return root;
    }
    return left != null ? left : right;
  }
}
```

### Complexity

- Time
  - One and exactly one traversal of the entire tree
  - O(n)
- Space
  - The depth of the recursion tree depends on the height of the binary tree
  - O(height)
