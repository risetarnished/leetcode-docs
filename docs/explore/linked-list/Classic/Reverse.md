# Reverse Linked List

<https://leetcode.com/problems/reverse-linked-list/>

## Description

`Easy`

Reverse a singly linked list.

**Example:**

```
Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
```

**Follow up:**

A linked list can be reversed either iteratively or recursively. Could you implement both?

Accepted

575,346

Submissions

1,058,274

## Solution

### Idea

#### Iteration

- Use a helper pointer to keep track of the previous node and the next node
  - current.next = previous
  - previous = current
  - current = next

#### Recursion

- When we are at a node current

  - ```
    current -> next -> ... -> end -> null
    ```

- Recursively reverse the next node of current

  - ```
    | next -> ... -> end -> null
    ```

  - **Base case**

    - The last/end node is reached: return the end node

  - will be reversed to

  - ```
    null <- next <- ... <- end
    ```

    - the returned reference node will be the head of the reversed list, which is the last node of the original list

- **Recursive rule**

  - the next node's next pointer to the current node and cut the current node's next pointer

_A good explanation:_

<https://www.educative.io/collection/page/5642554087309312/5679846214598656/70003>

### Code

#### Iteration

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

  public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    // curr has fallen out of the list
    // prev is pointing to the end node
    return prev;
  }
}
```

#### Recursion

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

  public ListNode reverseList(ListNode head) {
    // Base case: when we reach the end node
    if (head == null || head.next == null) {
      return head;
    }
    // Recursive rule
    // Reverse the section of the linked list starting from the next node
    // The return value will be the head of the reversed list (end node)
    ListNode reversedHead = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return reversedHead;
  }
}
```

### Complexity

#### Iteration

- Time
  - O(n) time to traverse the whole list
  - O(n)
- Space
  - Constant
  - O(1)

#### Recursion

- Time
  - Reversing every single node in the list
  - O(n)
- Space
  - n recursive calls ==> the recursion tree has n levels on the call stack
  - O(n)
