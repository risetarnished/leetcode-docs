# Remove Nth Node From End of List

<https://leetcode.com/problems/remove-nth-node-from-end-of-list/>

## Description

`Medium`

Given a linked list, remove the _n_-th node from the end of list and return its head.

**Example:**

```
Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
```

**Note:**

Given _n_ will always be valid.

**Follow up:**

Could you do this in one pass?

Accepted

386,857

Submissions

1,130,962

## Solution

### High-level Idea

- Because this is a singly linked list, we have to traverse the list once to get its total length
- Then, we can traverse (length - n) steps to find the target node and remove it

### Code

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *   int val;
 *   ListNode next;
 *   ListNode(int x) { val = x; }
 * }
 */
class Solution {

  public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null || n <= 0) {
      return head;
    }
    int length = getLength(head);
    // Use a dummy prev node to help us with the deletion
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;
    ListNode curr = head;
    for (int i = 0; i < length - n; i++) {
      prev = curr;
      curr = curr.next;
    }
    // curr points to the target to delete now
    prev.next = curr.next;
    curr.next = null;
    return dummy.next;
  }

  private int getLength(ListNode head) {
    int length = 0;
    while (head != null) {
      length++;
      head = head.next;
    }
    return length;
  }
}
```

### Complexity

- Time
  - O(n) to get the total length of the linked list
  - O(n) to get to the deletion point
  - Total time is O(n)
- Space
  - Constant
  - O(1)
