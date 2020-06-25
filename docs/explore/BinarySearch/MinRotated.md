# Find Minimum in Rotated Sorted Array

<https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/>

## Description

Medium

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., `[0,1,2,4,5,6,7]` might become `[4,5,6,7,0,1,2]`).

Find the minimum element.

You may assume no duplicate exists in the array.

**Example 1:**

```
Input: [3,4,5,1,2]
Output: 1
```

**Example 2:**

```
Input: [4,5,6,7,0,1,2]
Output: 0
```

Accepted

278,829

Submissions

650,334

## Solution

### High-level Idea

- It the array is not rotated
  - array[0] < array[end]
  - array[0] is the answer
- The min in a rotated sorted array is immediately following the max element
  - Case 1: [mid] > [mid + 1]
    - mid is the max
    - mid + 1 is the min
  - Case 2: [mid] < [mid - 1]
    - Same logic as the previous case
    - mid is the min
  - Case 3: [mid - 1], [mid], [mid + 1] are ascending
    - compare [mid] against [0]
      - if [mid] > [0]
        - the answer has to be on the right
      - if [mid] <= [0]
        - the answer has to be mid itself or to the left

### Code

```java
class Solution {

  public int findMin(int[] nums) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    // Case 0: if the array is not rotated, min is the first element
    int left = 0;
    int right = nums.length - 1;
    if (nums[left] <= nums[right]) {
      return nums[left];
    }
    // Do a binary search and look for the min
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] > nums[mid + 1]) {
        // Case 1: mid is the pivot point
        return nums[mid + 1];
      } else if (nums[mid] < nums[mid - 1]) {
        // Case 2: mid - 1 is the pivot point
        // mid - 1 will not be out of bounds because even if there are only two elements
        // 1. if they are not rotated: covered by case 0
        // 2. if they are rotated: covered by case 1
        return nums[mid];
      } else if (nums[mid] > nums[0]) {
        // Case 3: has to be on the right
        left = mid + 1;
      } else {
        // Case 4: has to be mid itself or to the left
        right = mid;
      }
    }
    return nums[left];
  }
}
```

_Alternative method using the so-called Template III_

```java
class Solution {

  public int findMin(int[] nums) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    // If the last element > the first element
    // The array is not rotated
    int start = 0;
    int end = nums.length - 1;
    if (nums[start] < nums[end]) {
      return nums[start];
    }
    // When the array has been rotated
    // Look for the rotation point/index
    // The element next to it is the smallest one
    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] > nums[mid + 1]) {
        return nums[mid + 1];
      } else if (nums[mid] < nums[mid - 1]) {
        return nums[mid];
      } else if (nums[mid] > nums[0]) {
        // Because the array is rotated
        // When the mid is greater than the start
        // It means the min is still to the right
        start = mid + 1;
      } else {
        end = mid - 1;
      }
    }
    return nums[start] < nums[end] ? nums[start] : nums[end];
  }
}
```

### Complexity

- Time
  - O(log(n))
- Space
  - O(1)
