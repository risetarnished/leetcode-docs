# Sqrt(x)

<https://leetcode.com/problems/sqrtx/>

## Description

Easy

Implement `int sqrt(int x)`.

Compute and return the square root of _x_, where _x_ is guaranteed to be a non-negative integer.

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

Accepted

360,380

Submissions

1,154,983

## Solution

### Code

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

### Complexity

- Time
  - Narrowing down the search range by 1/2 each time
  - O(log(n))
- Space
  - No additional spaces used
  - O(1)
