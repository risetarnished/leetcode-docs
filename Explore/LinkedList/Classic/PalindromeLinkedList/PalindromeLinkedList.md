# Palindrome Linked List

<https://leetcode.com/problems/palindrome-linked-list/>

## Description

`Easy`

Given a singly linked list, determine if it is a palindrome.

**Example 1:**

```
Input: 1->2
Output: false
```

**Example 2:**

```
Input: 1->2->2->1
Output: true
```

**Follow up:**
Could you do it in O(n) time and O(1) space?

Accepted

253,706

Submissions

706,346

## Solution

### High-level Idea

- In order to check if a string is palindrome, we do
  - char[left++] == char[right--]
- But this is a singly linked list
- We can mimic this process if we
  - Find the **middle node** of the list
  - **Reverse** the second part
  - Traverse the first part and the reversed second part and see if each pair of nodes have the same key
  - Reverse the second part back to maintain the original data

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
  public boolean isPalindrome(ListNode head) {
		// Sanity check: no empty list
    // Empty list or a singleton list is a palindrome
    if (head == null || head.next == null) {
      return true;
    }
    // Step 1: find the middle node
    // If there are even number of nodes in the list
    // pick the first one of the two middle nodes
    // Such that size(first part) <= size(second part)
    ListNode mid = findMiddleNode(head);
    // Step 2: reverse the second part
    ListNode second = reverseList(mid.next);
    // Cut the two parts apart from each other
    mid.next = null;
    // Step 3: compare each node in the two parts
    ListNode first = head;
    while (first != null && second != null) {
      if (first.val != second.val) {
        return false;
      }
      first = first.next;
      second = second.next;
    }
    // size(first part) == size(second part) or size(second part) - 1
    // The one node gets left does not need to be checked
    // Step 4: restore the original data
    return true;
  }
  
  private ListNode findMiddleNode(ListNode head) {
    ListNode slow = head;
    ListNode fast = head.next;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }
  
  private ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    return prev;
  }
}
```

*The above method didn't restore the input linked list to its original state. The second part has been reversed and the two parts have been cut of from each other*

*The method below includes the operations to restore the data*

There is a java file in the directory, run it to make sure the data has been restored.

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
  public boolean isPalindrome(ListNode head) {
		// Sanity check: no empty list
    // Empty list or a singleton list is a palindrome
    if (head == null || head.next == null) {
      return true;
    }
    // Step 1: find the middle node
    // If there are even number of nodes in the list
    // pick the first one of the two middle nodes
    // Such that size(first part) <= size(second part)
    ListNode mid = findMiddleNode(head);
    // Step 2: reverse the second part
    ListNode reversedSecond = reverseList(mid.next);
    // Cut the two parts apart from each other
    mid.next = null;
    // Step 3: compare each node in the two parts
    ListNode firstCurr = head;
    ListNode secondCurr = reversedSecond;
    while (firstCurr != null && secondCurr != null) {
      if (firstCurr.val != secondCurr.val) {
        return false;
      }
      firstCurr = firstCurr.next;
      secondCurr = secondCurr.next;
    }
    // size(first part) == size(second part) or size(second part) - 1
    // The one node gets left does not need to be checked
    // Step 4: restore the original data
    ListNode second = reverseList(reversedSecond);
    mid.next = second;
    return true;
  }
  
  private ListNode findMiddleNode(ListNode head) {
    ListNode slow = head;
    ListNode fast = head.next;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }
  
  private ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    return prev;
  }
}
```

### Complexity

- Time
  - O(n) to find the middle node
  - O(n/2) to reverse the second part
  - O(n/2) to compare the two parts
  - O(n/2) to restore the second part
  - O(n)
- Space
  - Constant
  - O(1)