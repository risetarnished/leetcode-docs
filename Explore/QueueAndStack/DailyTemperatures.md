# Daily Temperatures

<https://leetcode.com/problems/daily-temperatures/>

## Description

Medium

Given a list of daily temperatures `T`, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put `0` instead.

For example, given the list of temperatures `T = [73, 74, 75, 71, 69, 72, 76, 73]`, your output should be `[1, 1, 4, 2, 1, 1, 0, 0]`.

**Note:** The length of `temperatures` will be in the range `[1, 30000]`. Each temperature will be an integer in the range `[30, 100]`.

Accepted

62,359

Submissions

104,244

## Solution

### High-level Idea

- Because for any day, if we'd like to know how long it will take it to get warmer, we have to know the temperatures in the later days. So, we could iterate over the array in the reversed order, from end to start
- We can maintain a stack which keep track of indexes of the temperatures seen so far
- Therefore, when we check the current day's temperature, we compare it against the temperature of the stack's top until we see a warmer day
- Push the processed indexes to the stack

### Code

```java
class Solution {
  public int[] dailyTemperatures(int[] T) {
    if (T == null || T.length == 0) {
      return new int[] {};
    }
    int[] result = new int[T.length];
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = T.length - 1; i >= 0; i--) {
      // Look for the first warmer day
      while (!stack.isEmpty() && T[stack.peekFirst()] <= T[i]) {
        stack.pollFirst();
      }
      if (stack.isEmpty()) {
        // Case 1: stack becomes empty
        result[i] = 0;
        stack.offerFirst(i);
      } else {
        // Case 2: we have found the warmer day
        result[i] = stack.peekFirst() - i;
        stack.offerFirst(i);
      }
    }
    return result;
  }
}
```

### Complexity

- Time
  - One iteration over the array
  - O(n)
- Space
  - The stack can be as big as the array
  - O(n)