# Binary Search

[https://leetcode.com/explore/learn/card/binary-search/](https://leetcode.com/explore/learn/card/binary-search/)

## Introduction

In this card, we are going to help you understand the general concept of Binary Search.

### What is Binary Search?

Binary Search is one of the most fundamental and useful algorithms in Computer Science. It describes the process of searching for a specific value in an ordered collection.

> Terminology used in Binary Search:
>
> - Target - the value that you are searching for
> - Index - the current location that you are searching
> - Left, Right - the indicies from which we use to maintain our search Space
> - Mid - the index that we use to apply a condition to determine if we should search left or right

Other Binary Search Defintions:

([Wikipedia](https://en.wikipedia.org/wiki/Binary_search_algorithm))

## Background

The goal of this chapter is to explain the mechanics of how Binary Search works, different ways to identify Binary Search, and give a brief introduction to the 3 commonly used Binary Search Templates.

### How does it work?

------

In its simplest form, Binary Search operates on a contiguous sequence with a specified left and right index. This is called the Search Space. Binary Search maintains the left, right, and middle indicies of the search space and compares the search target or applies the search condition to the middle value of the collection; if the condition is unsatisfied or values unequal, the half in which the target cannot lie is eliminated and the search continues on the remaining half until it is successful. If the search ends with an empty half, the condition cannot be fulfilled and target is not found.

In the following chapters, we will review how to identify Binary Search problems, reasons why we use Binary Search, and the 3 different Binary Search templates that you might be previously unaware of. Since Binary Search is a common interview topic, we will also categorize practice problems to different templates so you can practice using each.

**Note:**

> Binary Search can take many alternate forms and might not always be as straight forward as searching for a specific value. Sometimes you will have to apply a specific condition or rule to determine which side (left or right) to search next.

We will provide examples in the coming chapters. First, could you try write a binary search algorithm yourself?

### Binary Search

[https://leetcode.com/explore/learn/card/binary-search/138/background/1038](https://leetcode.com/explore/learn/card/binary-search/138/background/1038)

#### Description

Given a **sorted** (in ascending order) integer array `nums` of `n` elements and a `target` value, write a function to search `target` in `nums`. If `target` exists, then return its index, otherwise return `-1`.


**Example 1:**

```
Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4
```

**Example 2:**

```
Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1
```

 

**Note:**

1. You may assume that all elements in `nums` are unique.
2. `n` will be in the range `[1, 10000]`.
3. The value of each element in `nums` will be in the range `[-9999, 9999]`.

#### Solution

##### High-level Idea

- This problem is straightforward: look for the index of the target number if it exists in the array
- Use the classic way of binary search
  - start/left <= end/right
  - check every element (last element when start == end)
  - decide whether this element is present in the while loop
  - no post-processing

##### Code

```java
class Solution {
  public int search(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    int start = 0;
    int end = nums.length - 1;
    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] > target) {
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }
    return -1;
  }
}
```

##### Complexity

- Time
  - The search range is narrowed down by 1/2 each time
  - O(log(n))
- Space
  - No additional spaces used
  - O(1)

### Identification and Template Introduction

------

#### **How do we identify Binary Search?**

------

As mentioned in earlier, Binary Search is an algorithm that *divides the search space in 2* after every comparison. Binary Search should be considered every time you need to search for an index or element in a collection. If the collection is unordered, we can always sort it first before applying Binary Search.

 

#### **3 Parts of a Successful Binary Search**

------

Binary Search is generally composed of 3 main sections:

1. **Pre-processing** - Sort if collection is unsorted.
2. **Binary Search** - Using a loop or recursion to divide search space in half after each comparison.
3. **Post-processing** - Determine viable candidates in the remaining space.

 

#### **3 Templates for Binary Search**

------

When we first learned Binary Search, we might struggle. We might study hundreds of Binary Search problems online and each time we looked at a developer's code, it seemed to be implemented slightly differently. Although each implementation divided the problem space in 1/2 at each step, one had numerous questions:

- Why was it implemented slightly differently?
- What was the developer thinking?
- Which way was easier?
- Which way is better?

After many failed attempts and lots of hair-pulling, we found 3 main templates for Binary Search. To prevent hair-pulling and to make it easier for new developers to learn and understand, we have provided them in the next chapter.

### Binary Search Template I

------

**Template #1:**

```java
int binarySearch(int[] nums, int target){
  if(nums == null || nums.length == 0)
    return -1;

  int left = 0, right = nums.length - 1;
  while(left <= right){
    // Prevent (left + right) overflow
    int mid = left + (right - left) / 2;
    if(nums[mid] == target){ return mid; }
    else if(nums[mid] < target) { left = mid + 1; }
    else { right = mid - 1; }
  }

  // End Condition: left > right
  return -1;
}
```



Template #1 is the most basic and elementary form of Binary Search. It is the standard Binary Search Template that most high schools or universities use when they first teach students computer science. Template #1 is used to search for an element or condition which can be determined by *accessing a single index* in the array.

 

**Key Attributes:**

------

- Most basic and elementary form of Binary Search
- Search Condition can be determined without comparing to the element's neighbors (or use specific elements around it)
- No post-processing required because at each step, you are checking to see if the element has been found. If you reach the end, then you know the element is not found

 

**Distinguishing Syntax:**

------

- Initial Condition:` left = 0, right = length-1`
- Termination: `left > right`
- Searching Left: `right = mid-1`
- Searching Right: `left = mid+1`

#### Sqrt(x)

[https://leetcode.com/explore/learn/card/binary-search/125/template-i/950/](https://leetcode.com/explore/learn/card/binary-search/125/template-i/950/)

Implement `int sqrt(int x)`.

Compute and return the square root of *x*, where *x* is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

**Example 1:**

```
Input: 4
Output: 2
```

**Example 2:**

```
Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.
```

##### Code

```java
class Solution {
  public int mySqrt(int x) {
    if (x < 0) {
      return -1;
    } else if (x <= 1) {
      return x;
    }
    int start = 2;
    int end = x;
    while (start <= end) {
      int mid = start + (end - start) / 2;
      // Do mid <=> x / mid instead of mid * mid <=> x
      // to avoid overflow problems
      if (mid == x / mid) {
        return mid;
      } else if (mid < x / mid) {
        start = mid + 1;
      } else {
        end = mid - 1;
      }
    }
    // Upon exiting the loop, start will point to the first element
    // whose ^2 is greater than x
    return start - 1;
  }
}
```

##### Complexity

- Time
  - Narrowing down the search range by 1/2 each time
  - O(log(n))
- Space
  - No additional spaces used
  - O(1)

