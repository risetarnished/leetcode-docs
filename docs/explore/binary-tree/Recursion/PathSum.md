# Path Sum

[https://leetcode.com/problems/path-sum/](https://leetcode.com/problems/path-sum/)

## Description

Easy

Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

**Note:** A leaf is a node with no children.

**Example:**

Given the below binary tree and `sum = 22`,

```
      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
```

return true, as there exist a root-to-leaf path `5->4->11->2` which sum is 22.

Accepted

306,983

Submissions

816,466

## Assumption

- The input tree is not null
- There may or may not be such a path in the tree

## Solution

### High-level Idea

- Should be easily solved with a _top-down_ recursion
- At each level, consider:
  - If this is a leaf node
    - root.val == target?
  - If there is a left/right child
    - update the remaining target sum and check if there exists such a path in the left/right subtree
- Base case
  - When the current node is a leaf node
- Recursive rule
  - Update the remaining target sum and go left/right
  - If **either** of the subtrees has such a root-to-leaf path, return true to the parent

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

  public boolean hasPathSum(TreeNode root, int sum) {
    // Corner case + Base case
    if (root == null) {
      return false;
    }
    if (root.left == null && root.right == null) {
      return root.val == sum;
    }
    return (
      hasPathSum(root.left, sum - root.val) ||
      hasPathSum(root.right, sum - root.val)
    );
  }
}
```

### Complexity

- Time
  - We need to traverse the whole tree once to get the root-to-leaf paths
  - In the worst case, total time is O(n)
- Space
  - The depth of the recursion tree depends on the height of the binary tree
  - O(height) or O(n) in the worst case
