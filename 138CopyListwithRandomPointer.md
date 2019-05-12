# Copy List with Random Pointer

[https://leetcode.com/problems/copy-list-with-random-pointer/](https://leetcode.com/problems/copy-list-with-random-pointer/)

## Description

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a [**deep copy**](https://en.wikipedia.org/wiki/Object_copying#Deep_copy) of the list.

 

**Example 1:**

**![img](https://discuss.leetcode.com/uploads/files/1470150906153-2yxeznm.png)**

```
Input:
{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}

Explanation:
Node 1's value is 1, both of its next and random pointer points to Node 2.
Node 2's value is 2, its next pointer points to null and its random pointer points to itself.
```

 

**Note:**

1. You must return the **copy of the given head** as a reference to the cloned list.

- Accepted
  - 239,359
- Submissions
  - 896,915

**Tags**

- Medium
- Hashtable
- Linked List

## Assumption

- The list should not be null or empty

## Solution

### High-level Idea

- Use a HashMap to store the nodes that have been generated already to avoid duplicate generations
  - <original node, generated node for copying>
- Each time when we copy a node, we check if its copy has been generated already
  - If it has been generated, copy it directly
  - Otherwise, generate a copy of it and copy this copy
- Do this for the node's next and random pointers

### Code

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/

class Solution {
  public Node copyRandomList(Node head) {
    // Write your solution here
    // Corner case check
    if (head == null) {
      return null;
    }
    // Use a HashMap to store the relationship between 
    // the original node and its generated copy
    // <original node, generated node for copying>
    Map<Node, Node> generated = new HashMap<>();
    // Initialize by generating and copying the head node
    Node result = new Node(head.val);
    generated.put(head, result);
    // The copy pointer points to position that the generated node should go to
    // The curr pointer points to the node that is being copied in the original list
    Node copy = result;
    Node curr = head;
    while (curr != null) {
      // Copy the node's next and random pointers
      if (curr.next != null) {
        if (!generated.containsKey(curr.next)) {
          generated.put(curr.next, new Node(curr.next.val));
        }
        copy.next = generated.get(curr.next);
      }
      if (curr.random != null) {
        if (!generated.containsKey(curr.random)) {
          generated.put(curr.random, new Node(curr.random.val));
        }
        copy.random = generated.get(curr.random);
      }
      copy = copy.next;
      curr = curr.next;
    }
    return result;
  }
}
```

### Complexity

- Time
  - Each one of the nodes gets copied exactly once
  - Total time is O(n)
- Space
  - The map costs O(n) and it is the only data structure used
  - Total space is O(n)