# Remove Linked List Elements

<https://leetcode.com/problems/remove-linked-list-elements/>

## Description

`Easy`

Remove all elements from a linked list of integers that have value **val**.

**Example:**

```
Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5
```

Accepted

221,659

Submissions

620,677

## Solution

### High-level Idea

- This can be done in one pass
- Keep track of the current and previous node
- When the current node's key is the target value
  - link previous node -> next node
  - cut off current.next -> null
- To take care of the case where the head is such a target node
  - Use a dummy node
  - dummy -> head

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

  public ListNode removeElements(ListNode head, int val) {
    if (head == null) {
      return null;
    }
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;
    ListNode curr = head;
    while (curr != null) {
      if (curr.val == val) {
        prev.next = curr.next;
        // Cut current from the list and move the pointer down
        curr.next = null;
        curr = prev.next;
      } else {
        prev = curr;
        curr = curr.next;
      }
    }
    return dummy.next;
  }
}
```

### Complexity

- Time
  - One pass to check every single node in the linked list
  - O(n)
- Space
  - Constant
  - O(1)
