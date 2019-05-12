# Find First and Last Position of Element in Sorted Array (aka. Search For a Range)

<https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/>

## Description

Medium

Given an array of integers `nums` sorted in ascending order, find the starting and ending position of a given `target` value.

Your algorithm's runtime complexity must be in the order of *O*(log *n*).

If the target is not found in the array, return `[-1, -1]`.

**Example 1:**

```
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
```

**Example 2:**

```
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
```

Accepted

294,420

Submissions

880,071

## Solution

### High-level Idea

- Look for the first occurrence of the target in the array
- Look for the last occurrence of the target in the array
- Binary search for both actions

### Code

```java
class Solution {
  public int[] searchRange(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
      return new int[0];
    }
    int[] result = new int[2];
    result[0] = firstOccurrence(nums, target);
    result[1] = lastOccurrence(nums, target);
    return result;
  }
  
  private int firstOccurrence(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    while (left + 1 < right) {
      int mid = left + (right - left) / 2;
      // When [mid] is strictly smaller than the target
      // the answer has to be to its right
      if (nums[mid] < target) {
        left = mid;
      } else {
        right = mid;
      }
    }
    if (nums[left] == target) {
      return left;
    } else if (nums[right] == target) {
      return right;
    }
    return -1;
  }
  
  private int lastOccurrence(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    while (left + 1 < right) {
      int mid = left + (right - left) / 2;
      // When [mid] is strictly larger than the target
      // the answer must be on the left
      if (nums[mid] > target) {
        right = mid;
      } else {
        left = mid;
      }
    }
    if (nums[right] == target) {
      return right;
    } else if (nums[left] == target) {
      return left;
    }
    return -1;
  }
}
```

### Complexity

- Time
  - It takes O(log(n)) to find the first occurrence of the element and another O(log(n)) to find its last occurrence
  - O(log(n))
- Space
  - Constant
  - O(1)