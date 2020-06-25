# Valid Parentheses

<https://leetcode.com/problems/valid-parentheses/>

## Description

Easy

Given a string containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['`and `']'`, determine if the input string is valid.

An input string is valid if:

1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.

Note that an empty string is also considered valid.

**Example 1:**

```
Input: "()"
Output: true
```

**Example 2:**

```
Input: "()[]{}"
Output: true
```

**Example 3:**

```
Input: "(]"
Output: false
```

**Example 4:**

```
Input: "([)]"
Output: false
```

**Example 5:**

```
Input: "{[]}"
Output: true
```

Accepted

578,077

Submissions

1,588,313

## Solution

### High-level Idea

- Legal representations look like
  - ()
  - ()[]{}
  - ([]){}
  - ([]{})
  - ([{}])
  - _etc._
- Illegal representations look like
  - ()[
  - )[]
  - ([]{)}
  - _etc._
- Because we are constantly looking back to see if there are paired parentheses of the current incoming parenthesis, we should maintain a stack
- The stack will only keep track of the left parentheses/brackets added
  - If the incoming element is a left parenthesis, add it to the stack
  - If not, see if the right parenthesis pairs with the stack's top
    - If they do match, pop the matched left parenthesis from the stack
    - If not, the representation is illegal

### Code

```java
class Solution {

  public boolean isValid(String s) {
    if (s == null || s.length() == 0) {
      return true;
    }
    // Build a map for simpler matching <right, left>
    Map<Character, Character> map = buildMatchMap();
    Deque<Character> stack = new ArrayDeque<>();
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (isLeft(ch)) {
        // If the current element is a left, push it onto the stack
        stack.offerFirst(ch);
      } else if (!match(ch, stack, map)) {
        // Otherwise, it is a right. If it doesn't match the previous left
        // This representation is illegal
        return false;
      } else {
        // It is a right, but it does match the previous left
        // Pop the left from the stack and move on
        stack.pollFirst();
      }
    }
    return stack.isEmpty();
  }

  private Map<Character, Character> buildMatchMap() {
    Map<Character, Character> map = new HashMap<>();
    map.put(')', '(');
    map.put(']', '[');
    map.put('}', '{');
    return map;
  }

  private boolean isLeft(char ch) {
    return ch == '(' || ch == '[' || ch == '{';
  }

  private boolean match(
    char right,
    Deque<Character> stack,
    Map<Character, Character> map
  ) {
    return !stack.isEmpty() && stack.peekFirst() == map.get(right);
  }
}
```

### Complexity

- Time
  - One iteration over the input string costs O(n)
  - `offerFirst()`, `pollFirst()`, `peekFirst()` operations all cost O(1)
  - The matching process involves hash table operation and takes O(1) on average
  - Total time is O(n)
- Space
  - A stack and a map is used
  - O(n)
