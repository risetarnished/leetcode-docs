# Binary Search Tree Iterator

[https://leetcode.com/problems/binary-search-tree-iterator/](https://leetcode.com/problems/binary-search-tree-iterator/)

## Description

Medium

Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling `next()` will return the next smallest number in the BST.

 

**Example:**

**![img](https://assets.leetcode.com/uploads/2018/12/25/bst-tree.png)**

```
BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false
```

 

**Note:**

- `next()` and `hasNext()` should run in average O(1) time and uses O(*h*) memory, where *h* is the height of the tree.
- You may assume that `next()` call will always be valid, that is, there will be at least a next smallest number in the BST when `next()` is called.

Accepted

200,765

Submissions

415,512



## Solution

### High-level Idea

- To achieve O(1) time for the `next()` and `hasNext()` methods, the next element(s) must be stored in some data structure before hand
- The in-order traversal of a BST yields the keys in ascending order. That is, the left most node is always the smallest element
  - We can use a stack to store the node we have traversed, and keep traversing to the left child
  - The size of the stack will be dependent on the height of the tree (always traversing to the left)
- When the `next()` method is called, we pop the top element from the stack
  - The current top element of the stack will be the parent of the previous top
  - Any node in the previous top node's right subtree will be greater than it but still less than the parent
  - Therefore, we need to traverse to its right subtree
    - Traverse all the way down its left children, as well

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
class BSTIterator {
  
  // Use a stack to store the traversed nodes
  private Deque<TreeNode> stack;

  public BSTIterator(TreeNode root) {
    stack = new ArrayDeque<>();
    // From the root, do a "left traversal" and push all
    // left children along the path onto the stack
    TreeNode curr = root;
    while (curr != null) {
      stack.offerFirst(curr);
      curr = curr.left;
    }
  }

  /** @return the next smallest number */
  public int next() {
		if (stack.isEmpty()) {
      return Integer.MIN_VALUE;
    }
    // Pop the top element (which is the result) from the stack
    // and traverse to its right child
    TreeNode result = stack.pollFirst();
    TreeNode curr = result.right;
    while (curr != null) {
      stack.offerFirst(curr);
      curr = curr.left;
    }
    return result.val;
  }

  /** @return whether we have a next smallest number */
  public boolean hasNext() {
		return !stack.isEmpty();
  }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
```

### Complexity

- Time
  - `next()`
    - Each time the `next()` method is called, we traverse down to the current smallest element's right and keep traversing down the right child's left subtree. But, on average, when we push k left children onto the stack, which takes O(k) time, another k pop() will only takes k * O(1). Therefore, the `next()` method takes O(1) on average.
  - `hasNext()`
    - The same as above
- Space
  - A *stack* is maintained to store the previous nodes visited.
  - At anytime, the stack only stores the left traversal nodes
  - Therefore, the size of the stack depends on the height of the tree
  - O(h)