# Search in Rotated Sorted Array

<https://leetcode.com/problems/search-in-rotated-sorted-array/>

## Description

Medium

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., `[0,1,2,4,5,6,7]` might become `[4,5,6,7,0,1,2]`).

You are given a target value to search. If found in the array return its index, otherwise return `-1`.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of *O*(log *n*).

**Example 1:**

```
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
```

**Example 2:**

```
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
```

Accepted

406,552

Submissions

1,237,409

## Solution

### High-level Idea

- A relatively straightforward method can be
  - Find the pivot point (7 (index 3) in the above example)
  - Do two binary search processes in the two parts
  - By doing this, the time complexity will be O(3 * log(n))
- An improved method can be done in one pass
  - Each step in the binary search process
    - Find mid
    - if [left, mid] is sorted
      - if target falls between [left, mid], search there
      - otherwise, search [mid + 1, right]
    - otherwise
      - if target falls between [mid + 1, right], search there
      - otherwise, search [left, mid]

### Code

```java
class Solution {
  public int search(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    int left = 0;
    int right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) {
        return mid;
      }
      // Check if the lhs is sorted and determine the possible
      // range where the target could fall in
      if (nums[left] <= nums[mid]) {
        // Case 1: lhs is sorted and the target falls in there
        // Case 2: lhs is sorted, but the target does not fall in there
        if (nums[left] <= target && target < nums[mid]) {
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      } else if (nums[mid] < target && target <= nums[right]) {
        // Case 3: lhs is not sorted, which means rhs is sorted,
        //         and the target falls in there
        left = mid + 1;
      } else {
        // Case 4: rhs is sorted, but the target is not there
        right = mid - 1;
      }
    }
    return -1;
  }
}
```

### Complexity

- Time
  - One pass binary search
  - O(log(n))
- Space
  - Constant
  - O(1)