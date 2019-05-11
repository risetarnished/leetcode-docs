# Binary Search

[https://leetcode.com/problems/binary-search/](https://leetcode.com/problems/binary-search/)

## Description

Easy

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

Accepted

46,683

Submissions

98,922

## Solution

### High-level Idea

- This problem is straightforward: look for the index of the target number if it exists in the array

- Use the classic way of binary search
  - start/left <= end/right
  - check every element (last element when start == end)
  - decide whether this element is present in the while loop
  - no post-processing

### Code

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



### Complexity

- Time
  - The search range is narrowed down by 1/2 each time
  - O(log(n))

- Space
  - No additional spaces used
  - O(1)