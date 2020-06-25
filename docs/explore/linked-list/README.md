# Linked List

## Introduction

[https://leetcode.com/explore/learn/card/linked-list/](https://leetcode.com/explore/learn/card/linked-list/)

## Singly Linked List

Each node in a singly-linked list contains not only the value but also `a reference field` to link to the next node. By this way, the singly-linked list organizes all the nodes in a sequence.

![img](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/04/12/screen-shot-2018-04-12-at-152754.png)

- [Design a Singly Linked List](SinglyLinkedList/DesignLinkedList.md)

## Two-Pointer in Linked List

1. [Linked List Cycle](TwoPointer/LinkedListCycle.md)
2. [Linked List Cycle II](TwoPointer/LinkedListCycleII.md)
3. [Intersection of Two Linked Lists](TwoPointer/Intersection.md)
4. [Remove Nth Node From End of List](TwoPointer/RemoveNth.md)

## Classic Problems

1. [Reverse Linked List](Classic/Reverse.md)
2. [Remove Linked List Elements](Classic/RemoveElements.md)
3. [Palindrome Linked List](Classic/PalindromeLinkedList/PalindromeLinkedList.md)

## Doubly Linked List

- [Design a Doubly Linked List]()

## Summary

### Comparisons

Here we provide a comparison of `time complexity` between the linked list and other data structures including [array](https://leetcode.com/explore/learn/card/array-and-string/), queue and stack:

![img](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/04/29/screen-shot-2018-04-28-at-174531.png)

After this comparison, it is not difficult to come up with our conclusion:

> If you need to add or delete a node frequently, a linked list could be a good choice.
>
> If you need to access an element by index often, an array might be a better choice than a linked list.
