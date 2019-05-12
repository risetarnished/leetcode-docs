# Find Peak Element

<https://leetcode.com/problems/find-peak-element/>

## Description

Medium

A peak element is an element that is greater than its neighbors.

Given an input array `nums`, where `nums[i] ≠ nums[i+1]`, find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that `nums[-1] = nums[n] = -∞`.

**Example 1:**

```
Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
```

**Example 2:**

```
Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5 
Explanation: Your function can return either index number 1 where the peak element is 2, 
             or index number 5 where the peak element is 6.
```

**Note:**

Your solution should be in logarithmic complexity.

Accepted

233,760

Submissions

567,997

## Solution

### High-level Idea

- Do binary search to narrow down the search range by 1/2 each time
- Method 1: so-called Template II
  - Compare the mid to mid + 1 
    - Because even when there are only two elements in the array
      - mid will be the first element
      - mid + 1 will not be out of bounds
      - However, if we compare to mid - 1, it will
    - if [mid] <= [mid + 1]
      - The peak element has to be on the right
      - It could be [mid + 1], but never [mid] itself
    - If [mid] > [mid + 1]
      - The peak element has to be on the left
      - It could be [mid] itself
  - We can stop when there is only one element left and that is the result
- Method 2: so-called Template III
  - Compare mid against its two neighbors

### Method 1

#### Code

```java
class Solution {
  public int findPeakElement(int[] nums) {
    if (nums == null || nums.length == 0) {
      return Integer.MIN_VALUE;
    }
    int left = 0;
    int right = nums.length - 1;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] > nums[mid + 1]) {
        right =  mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }
}
```

#### Complexity

- Time
  - O(log(n))
- Space
  - O(1)

### Method 2

#### Code

```java
class Solution {
  public int findPeakElement(int[] nums) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    int start = 0;
    int end = nums.length - 1;
    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
        // Case 1: mid is a peak
        // No out of bounds would happen because the array is guaranteed to have
        // at least three elements if we could enter the loop
        return mid;
      } else if (nums[mid] < nums[mid - 1]) {
        // mid - 1 > mid: look left
        end = mid;
      } else {
        // mid + 1 > mid: look right
        start = mid;
      }
    }
    // Return the larger element of the two
    return nums[start] >= nums[end] ? start : end;
  }
}
```

#### Complexity

- Time
  - O(log(n))
- Space
  - O(1)