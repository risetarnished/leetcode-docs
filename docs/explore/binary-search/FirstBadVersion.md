# First Bad Version

<https://leetcode.com/problems/first-bad-version/>

## Description

Easy

You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have `n` versions `[1, 2, ..., n]` and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API `bool isBadVersion(version)` which will return whether `version` is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

**Example:**

```
Given n = 5, and version = 4 is the first bad version.

call isBadVersion(3) -> false
call isBadVersion(5) -> true
call isBadVersion(4) -> true

Then 4 is the first bad version.
```

Accepted

219,430

Submissions

737,227

## Solution

### High-level Idea

- This is pretty much like the [First Occurrence of an Element in a Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)
- Start from version 1 and end at version n, test to see if the mid version is bad
  - If it is
    - The first bad version can be itself or an earlier version
  - If not
    - The first bad version has to be a later version
- Explanation from LeetCode
  - The only scenario left is where **_isBadVersion(mid) ⇒ true_**. This tells us that **_mid_** may or may not be the first bad version, but we can tell for sure that all versions after **_mid_** can be discarded. Therefore we set **_right_=_mid_** as the new search space of interval \[_left, mid_] (inclusive).
  - In our case, we indicate _left_ and _right_ as the boundary of our search space (both inclusive). This is why we initialize **_left = 1_** and **_right = n_**. How about the terminating condition? We could guess that _left_ and _right_ eventually both meet and it must be the first bad version, but how could you tell for sure?
  - The formal way is to [prove by induction](http://www.cs.cornell.edu/courses/cs211/2006sp/Lectures/L06-Induction/binary_search.html), which you can read up yourself if you are interested. Here is a helpful tip to quickly prove the correctness of your binary search algorithm during an interview. We just need to test an input of size 2. Check if it reduces the search space to a single element (which must be the answer) for both of the scenarios above. If not, your algorithm will never terminate.
- This problem can also be solved using the _so-called Template III_ in which we exit the loop when _left_ and _right_ are next to each other
  - Post-processing is required in this case to determine which one of these two elements is/are the answer

### Code

```java
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {

  public int firstBadVersion(int n) {
    // Corner case based on the assumption
    if (n <= 0) {
      return -1;
    }
    int left = 1;
    int right = n;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (isBadVersion(mid)) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    // We will eventually end up with one element left
    return left;
  }
}
```

### Complexity

- Time
  - Search range is narrowed down by 1/2 each time
  - O(log(n))
- Space
  - Constant
  - O(1)
