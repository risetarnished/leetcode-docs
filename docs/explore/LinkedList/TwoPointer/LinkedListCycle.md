# Linked List Cycle

<https://leetcode.com/problems/linked-list-cycle/>

## Description

Easy

Given a linked list, determine if it has a cycle in it.

To represent a cycle in the given linked list, we use an integer `pos` which represents the position (0-indexed) in the linked list where tail connects to. If `pos` is `-1`, then there is no cycle in the linked list.

**Example 1:**

```
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the second node.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png)

**Example 2:**

```
Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the first node.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test2.png)

**Example 3:**

```
Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test3.png)

**Follow up:**

Can you solve it using _O(1)_ (i.e. constant) memory?

Accepted

397,245

Submissions

1,081,811

## Solution

### High-level Idea

- Use two pointers to track the positions and movement of two nodes
  - The _slow_ pointer moves **one step** each time
  - The _fast_ pointer moves **two steps** each time
- If there is no cycle, the fast pointer will stop at the end of the linked list
- If there is a cycle, the fast pointer will eventually meet with the slow pointer
- For each iteration, the fast pointer will move one extra step. If the length of the cycle is M, after M iterations, the fast pointer will definitely move one more cycle and catch up with the slow pointer

### Code

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *   int val;
 *   ListNode next;
 *   ListNode(int x) {
 *     val = x;
 *     next = null;
 *   }
 * }
 */
public class Solution {

  public boolean hasCycle(ListNode head) {
    // Sanity check
    if (head == null || head.next == null) {
      return false;
    }
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        return true;
      }
    }
    return false;
  }
}
```

### Complexity

- Time
  - One pass iteration over the entire linked list
  - O(n)
- Space
  - Constant
  - O(1)
