# Intersection of Two Linked Lists

<https://leetcode.com/problems/intersection-of-two-linked-lists/>

## Description

Easy

Write a program to find the node at which the intersection of two singly linked lists begins.

For example, the following two linked lists:

![img](https://assets.leetcode.com/uploads/2018/12/13/160_statement.png)

begin to intersect at node c1.

 

**Example 1:**

![img](https://assets.leetcode.com/uploads/2018/12/13/160_example_1.png)

```
Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
Output: Reference of the node with value = 8
Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,0,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
```

 

**Example 2:**

![img](https://assets.leetcode.com/uploads/2018/12/13/160_example_2.png)

```
Input: intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
Output: Reference of the node with value = 2
Input Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [0,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.
```

 

**Example 3:**

![img](https://assets.leetcode.com/uploads/2018/12/13/160_example_3.png)

```
Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
Output: null
Input Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
Explanation: The two lists do not intersect, so return null.
```

 

**Notes:**

- If the two linked lists have no intersection at all, return `null`.
- The linked lists must retain their original structure after the function returns.
- You may assume there are no cycles anywhere in the entire linked structure.
- Your code should preferably run in O(n) time and use only O(1) memory.

Accepted

298,833

Submissions

889,753

## Solution

### High-level Idea

- Use two pointers, one for each linked list, let them start traversing from the same length
  - Calculate the difference in the lengths of the two linked lists, say k
  - Traverse the longer one k steps until the length left for the longer list == the length of the shorter list
- Traverse both pointers down at the same pace
  - If the same node is reached, it is the answer

### Code

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *   int val;
 *   ListNode next;
 *   ListNode(int x) {
 *     val = x;
 *     next = null;
 *   }
 * }
 */
public class Solution {
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		// Sanity check: if either or both lists are empty
    if (headA == null || headB == null) {
      return null;
    }
    // Step 1: calculate the lengths of the two linked lists
    int lengthA = getLength(headA);
    int lengthB = getLength(headB);
    // Step 2: let the two linked list traverse from a position where
    //         they are left with the same remaining length
    ListNode longer = lengthA - lengthB >= 0 ? headA : headB;
    ListNode shorter = longer == headA ? headB : headA;
    // Move the longer pointer down by the steps difference
    for (int i = 0; i < Math.abs(lengthA - lengthB) && longer != null; i++) {
      longer = longer.next;
    }
    // Step 3: traverse both lists until we find a common node or reach the end
    while (shorter != null && longer != null) {
      if (shorter == longer) {
        return shorter;
      }
      shorter = shorter.next;
      longer = longer.next;
    }
    return null;
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
  - Getting lengths of the lists takes O(n)
  - Traversing down and look for the common node takes another O(n)
  - O(n)
- Space
  - Constant space
  - O(1)