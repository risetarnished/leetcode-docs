# Populating Next Right Pointers in Each Node

[https://leetcode.com/problems/populating-next-right-pointers-in-each-node/](https://leetcode.com/problems/populating-next-right-pointers-in-each-node/)

## Description

Medium

You are given a **perfect binary tree** where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

```
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
```

Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to `NULL`.

Initially, all next pointers are set to `NULL`.

**Example:**

![img](https://assets.leetcode.com/uploads/2019/02/14/116_sample.png)

```
Input: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}

Output: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next":null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}

Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B.
```

**Note:**

- You may only use constant extra space.
- Recursive approach is fine, implicit stack space does not count as extra space for this problem.

Accepted

240,185

Submissions

638,686

## Solution

### High-level Idea

- Because the input is given as a _perfect binary tree_, which means a **breadth-first search** or **level-order traversal** can easily group all nodes in the tree level by level
- All we need to do is to link each nodes in the same level
- Algorithm
  - Breadth-first search
- Data structure
  - FIFO queue
- Start
  - Root
- Expansion/Generation
  - Expand a node from the queue
  - Generate its two children
  - Connect the _previous_ node to the current node
- Termination
  - Terminate when the queue becomes empty

### Code

```java
/*
// Definition for a Node.
class Node {
  public int val;
  public Node left;
  public Node right;
  public Node next;

  public Node() {}

  public Node(int _val,Node _left,Node _right,Node _next) {
    val = _val;
    left = _left;
    right = _right;
    next = _next;
  }
};
*/
class Solution {

  public Node connect(Node root) {
    if (root == null) {
      return null;
    }
    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);
    // Use a helper node to keep track of the previously traversed node
    Node prev = null;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        Node curr = queue.poll();
        // Do not connect this node if it is the first node in the current level
        if (i != 0) {
          prev.next = curr;
        }
        if (curr.left != null) {
          queue.offer(curr.left);
        }
        if (curr.right != null) {
          queue.offer(curr.right);
        }
        prev = curr;
      }
    }
    return root;
  }
}
```

### Complexity

- Time
  - We did a level-order traversal of the entire tree using breadth-first search
  - O(n)
- Space
  - We used a FIFO queue to traverse the tree
  - O(n)
