# Find K Closest Elements

<https://leetcode.com/problems/find-k-closest-elements/>

## Description

Medium

Given a sorted array, two integers `k` and `x`, find the `k` closest elements to `x` in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

**Example 1:**

```
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]
```

**Example 2:**

```
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]
```

**Note:**

1. The value k is positive and will always be smaller than the length of the sorted array.
2. Length of the given array is positive and will not exceed 104
3. Absolute value of elements in the array and x will not exceed 104

---

**UPDATE (2017/9/19):**
The _arr_ parameter had been changed to an **array of integers** (instead of a list of integers). **Please reload the code definition to get the latest changes**.

Accepted

53,268

Submissions

140,579

## Solution

### High-level Idea

- Look for either the first or last occurrence of the target
- Start from that index, check its neighbors to the left and right
- Pick the closer one and move that pointer respectively
- Do it k times to get the k closest elements result
- To make sure the output is in ascending order, I used LinkedList to store the result such that I can add from both ends
  - This is not particularly a good practice because I wasn't implementing a List object with LinkedList

### Code

```java
class Solution {

  public List<Integer> findClosestElements(int[] arr, int k, int x) {
    LinkedList<Integer> result = new LinkedList<>();
    if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
      return result;
    }
    // Narrow the search range to the two closest elements
    int left = 0;
    int right = arr.length;
    while (left + 1 < right) {
      int mid = left + (right - left) / 2;
      if (arr[mid] > x) {
        right = mid;
      } else {
        left = mid;
      }
    }
    // Start from the two pointers, take the closest k elements
    for (int i = 0; i < k; i++) {
      // We can take the left element when:
      // 1. right is out of bound
      // 2. left element is closer
      if (
        right >= arr.length ||
        (left >= 0 && Math.abs(arr[left] - x) <= Math.abs(arr[right] - x))
      ) {
        result.addFirst(arr[left--]);
      } else {
        result.addLast(arr[right++]);
      }
    }
    return result;
  }
}
```

### Complexity

- Time
  - Looking for the starting positions
    - O(log(n))
  - Looking for the k closest
    - O(k)
  - Total time
    - O(log(n) + k)
- Space
  - O(1)
