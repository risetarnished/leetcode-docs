# Linked List Cycle II

<https://leetcode.com/problems/linked-list-cycle-ii/>

## Description

Medium

Given a linked list, return the node where the cycle begins. If there is no cycle, return `null`.

To represent a cycle in the given linked list, we use an integer `pos` which represents the position (0-indexed) in the linked list where tail connects to. If `pos` is `-1`, then there is no cycle in the linked list.

**Note:** Do not modify the linked list.

**Example 1:**

```
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png)

**Example 2:**

```
Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test2.png)

**Example 3:**

```
Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test3.png)

**Follow up**:
Can you solve it without using extra space?

Accepted

211,124

Submissions

662,756

## Solution

### High-level Idea

- To determine if there is a cycle in the linked list is trivial: use fast-slow pointers

- To determine the position is somewhat difficult and non-generic

  - It should be kept to this problem only so it is a little boring

- Combine the two steps above:

  - Step 1: look for a cycle in the linked list
  - Step 2: look for the start of the cycle

- ![cyclenode](cyclenode.png)

  - ```
    To understand this solution, you just need to ask yourself these question.
        1.  Assume the distance from head to the start of the loop is x1
        2.  the distance from the start of the loop to the point fast and slow meet is x2
        3.  the distance from the point fast and slow meet to the start of the loop is x3
        4.  What is the distance fast moved? What is the distance slow moved? And their relationship?
            1.  d(fast) = x1 + x2 + x3 + x2
            2.  d(slow) = x1 + x2
            3.  d(fast) = 2 * d(slow) ⇒ x1 + x2 + x3 + x2 = 2 (x1 + x2)
            4.  Thus x1 = x3

                Finally got the idea.
    ```

### Code

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {

  public ListNode detectCycle(ListNode head) {
    if (head == null || head.next == null) {
      return null;
    }
    // Step 1: determine if there is a cycle
    ListNode slow = head;
    ListNode fast = head;
    boolean hasCycle = false;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        hasCycle = true;
        break;
      }
    }
    // Proceed only if there is a cycle
    if (!hasCycle) {
      return null;
    }
    // Step 2: look for the starting node of the cycle
    // a. Distance covered by the slow pointer:
    //    distance(head, start) + distance(start, meet)
    // b. Distance covered by the fast pointer:
    //    distance(head, start) + distance(start, meet) +
    //    distance(meet, start) + distance(start, meet)
    // Because b = 2 * a ==>
    // Therefore, distance(head, start) == distance(meet, start)
    // So, let slow moves one step at a time from the head
    //     let fast moves one step at a time from the meeting point
    // They will meet at the starting point of the cycle
    slow = head;
    while (slow != fast) {
      slow = slow.next;
      fast = fast.next;
    }
    return slow;
  }
}
```

### Complexity

- Time
  - O(n) to determine if there is a cycle
  - O(n) to find the start of the cycle
  - O(n)
- Space
  - Constant
  - O(1)
