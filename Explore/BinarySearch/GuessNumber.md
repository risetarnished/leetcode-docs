# Guess Number Higher or Lower

<https://leetcode.com/problems/guess-number-higher-or-lower/>

## Description

Easy

We are playing the Guess Game. The game is as follows:

I pick a number from **1** to **n**. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number is higher or lower.

You call a pre-defined API `guess(int num)` which returns 3 possible results (`-1`, `1`, or `0`):

```
-1 : My number is lower
 1 : My number is higher
 0 : Congrats! You got it!
```

**Example :**

```
Input: n = 10, pick = 6
Output: 6
```

Accepted

105,358

Submissions

268,099

## Solution

### Code

```java
/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

public class Solution extends GuessGame {
  public int guessNumber(int n) {
    // Corner case: guessing range [1, n]
    if (n <= 0) {
      return -1;
    }
    int start = 1;
    while (start <= n) {
      int mid = start + (n - start) / 2;
      if (guess(mid) == 0) {
        return mid;
      } else if (guess(mid) == -1) {
        n = mid - 1;
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
  - This is just classic binary search
  - The search range is narrowed down by 1/2 each round
  - O(log(n))
- Space
  - Constant space
  - O(1)