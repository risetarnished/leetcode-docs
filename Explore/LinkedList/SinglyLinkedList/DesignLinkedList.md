# Design Linked List

<https://leetcode.com/problems/design-linked-list/>

## Description

Easy

Design your implementation of the linked list. You can choose to use the singly linked list or the doubly linked list. A node in a singly linked list should have two attributes: `val` and `next`. `val` is the value of the current node, and `next` is a pointer/reference to the next node. If you want to use the doubly linked list, you will need one more attribute `prev` to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.

Implement these functions in your linked list class:

- get(index) : Get the value of the `index`-th node in the linked list. If the index is invalid, return `-1`.
- addAtHead(val) : Add a node of value `val` before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
- addAtTail(val) : Append a node of value `val` to the last element of the linked list.
- addAtIndex(index, val) : Add a node of value `val` before the `index`-th node in the linked list. If `index` equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
- deleteAtIndex(index) : Delete the `index`-th node in the linked list, if the index is valid.

**Example:**

```
MyLinkedList linkedList = new MyLinkedList();
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
linkedList.get(1);            // returns 2
linkedList.deleteAtIndex(1);  // now the linked list is 1->3
linkedList.get(1);            // returns 3
```

**Note:**

- All values will be in the range of `[1, 1000]`.
- The number of operations will be in the range of `[1, 1000]`.
- Please do not use the built-in LinkedList library.

Accepted

25,191

Submissions

111,689

## Solution

```java
class MyLinkedList {
  private class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
      this.val = val;
      next = null;
    }
  }

  // private variables to keep track of the list and its length
  private ListNode head;
  private int length;

  /** Initialize your data structure here. */
  public MyLinkedList() {
    head = null;
    length = 0;
  }

  /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
  public int get(int index) {
    if (head == null || index < 0 || index >= length) {
      return -1;
    }
    ListNode curr = head;
    while (curr != null) {
      if (index == 0) {
        return curr.val;
      }
      curr = curr.next;
      index--;
    }
    return -1;
  }

  /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
  public void addAtHead(int val) {
    ListNode newNode = new ListNode(val);
    newNode.next = head;
    head = newNode;
    length++;
  }

  /** Append a node of value val to the last element of the linked list. */
  public void addAtTail(int val) {
    ListNode newNode = new ListNode(val);
    if (head == null) {
      head = newNode;
      length++;
      return;
    }
    ListNode curr = head;
    while (curr.next != null) {
      curr = curr.next;
    }
    curr.next = newNode;
    length++;
  }

  /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
  public void addAtIndex(int index, int val) {
    // Corner cases and eager evaluations
    if (index > length) {
      return;
    } else if (index <= 0) {
      addAtHead(val);
      return;
    } else if (index == length) {
      addAtTail(val);
      return;
    }
    ListNode curr = head;
    // Because we are inserting the new node before the index
    // We need to stop one step ahead of reaching the index
    // And the index can be guaranteed to not be the head or the tail
    while (curr.next != null && index > 1) {
      curr = curr.next;
      index--;
    }
    ListNode newNode = new ListNode(val);
    newNode.next = curr.next;
    curr.next = newNode;
    length++;
  }

  /** Delete the index-th node in the linked list, if the index is valid. */
  public void deleteAtIndex(int index) {
    // Corner cases:
    if (index < 0 || index > length) {
      return;
    }
    if (index == 0) {
      head = head.next;
      return;
    }
    ListNode curr = head;
    // Deleting a node from a linked list: prev.next = curr.next
    // Therefore, we need to keep track of the previous node
    while (curr.next != null) {
      // Stop at one step ahead of reaching the index
      // Such that we can skip that node
      if (index == 1) {
        curr.next = curr.next.next;
        length--;
        break;
      }
      curr = curr.next;
      index--;
    }
  }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
```

