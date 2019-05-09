# Binary Search Tree

## Overview

A `Binary Search Tree` is a special form of a binary tree. The value in each node must be `greater than` (or equal to) any values in its `left subtree` but `less than` (or equal to) any values in its `right subtree`.

We'll go through this definition more specifically in this chapter and provide you some exercise related to the binary search tree.

The goal of this card is to:

1. Understand the `properties` of a binary tree;
2. Be able to do `basic operations` in a binary search tree;
3. Understand the concept of a `height-balanced binary search tree`.

## Introduction to BST

### Definition of the Binary Search Tree

------

A `binary search tree` (BST), a special form of a binary tree, satisfies the binary search property:

1. The value in each node must be `greater than` (or equal to) any values stored in its left subtree.
2. The value in each node must be `less than` (or equal to) any values stored in its right subtree.
 

Here is an example of a BST.

![img](https://leetcode.com/explore/learn/card/introduction-to-data-structure-binary-search-tree/140/introduction-to-a-bst/Figures/binary_search_tree/BST_example.png)

 

We have exercises for you to `validate a BST` after this article. You can use the property we mentioned above to determine whether a binary tree is a BST or not. The recursive thinking we have introduced in the previous chapter might help you with this problem.

Like a normal binary tree, we can traverse a BST in preorder, inorder, postorder or level-order. However, it is noteworthy that `inorder traversal` in BST will be in `ascending order`. Therefore, the inorder traversal is the most frequent used traversal method of a BST.

We also have exercises for you to find the `inorder successor` in a BST after this article. Obviously, you can do the inorder traversal to find the inorder successor in a BST. But please try to apply the property of the BST we have learned to find out a better way to solve this problem.


### Problems

1. [Validate Binary Search Tree](Introduction/ValidateBST.md)
2. [Binary Search Tree Iterator](Introduction/BSTIterator/BSTIterator.md)
