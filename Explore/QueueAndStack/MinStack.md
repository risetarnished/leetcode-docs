# Min Stack

[https://leetcode.com/problems/min-stack/](https://leetcode.com/problems/min-stack/)

## Description

Easy

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

- push(x) -- Push element x onto stack.
- pop() -- Removes the element on top of the stack.
- top() -- Get the top element.
- getMin() -- Retrieve the minimum element in the stack.



**Example:**

```
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
```



Accepted

294,171

Submissions

800,726

## Solution

### High-level Idea

- A direct but naive solution
  - When pushing a new element onto the stack, check the element against the minStack's top, push whichever is the smaller one on to the minStack
  - The drawback of this method is that the size of the minStack will be the same as the main stack. Moreover, even if we only push the element onto the minStack when it is smaller than the current min, in the case where there are a lot of duplicate elements, it still takes up a lot of space
- An improved method
  - Only push the new element onto the minStack when it is smaller than the minStack's top
    - Remember the size of the stack when pushing it onto the minStack
  - If popping the stack makes the stack's size smaller than the size of it when the current min was pushed onto the minStack, also pop the minStack

### Code

#### Direct method

```java
class MinStack {
  
  private Deque<Integer> stack;
  private Deque<Integer> minStack;

  /** initialize your data structure here. */
  public MinStack() {
    stack = new ArrayDeque<>();
    minStack = new ArrayDeque<>();
  }

  public void push(int x) {
		stack.offerFirst(x);
    // Push the new element onto the minStack if it is smaller
    if (minStack.isEmpty() || x < minStack.peekFirst()) {
      minStack.offerFirst(x);
    } else {
      minStack.offerFirst(minStack.peekFirst());
    }
  }

  public void pop() {
		if (stack.isEmpty() || minStack.isEmpty()) {
      return;
    }
    // Also pop the minStack
    stack.pollFirst();
    minStack.pollFirst();
  }

  public int top() {
		return stack.isEmpty() ? Integer.MIN_VALUE : stack.peekFirst();
  }

  public int getMin() {
		return minStack.isEmpty() ? Integer.MIN_VALUE : minStack.peekFirst();
  }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
```

#### Improved method

```java
class MinStack {
  
  private Deque<Integer> stack;
  private Deque<Element> minStack;
  
  /** 
   * A helper class to represent a pair which is 
   * <element, size of stack when it gets pushed>
   */
  private class Element {
    int val;
    int size;
    private Element(int val, int size) {
      this.val = val;
      this.size = size;
    }
  }

  /** initialize your data structure here. */
  public MinStack() {
    stack = new ArrayDeque<>();
    minStack = new ArrayDeque<>();
  }

  public void push(int x) {
		stack.offerFirst(x);
    // Push the new element onto the minStack if it is smaller
    if (minStack.isEmpty() || x < minStack.peekFirst().val) {
      minStack.offerFirst(new Element(x, stack.size()));
    }
  }

  public void pop() {
		if (stack.isEmpty() || minStack.isEmpty()) {
      return;
    }
    stack.pollFirst();
    // If the stack's size becomes shorter than it was when the 
    // min was pushed, we also need to pop the minStack
    if (stack.size() < minStack.peekFirst().size) {
      minStack.pollFirst();
    }
  }

  public int top() {
		return stack.isEmpty() ? Integer.MIN_VALUE : stack.peekFirst();
  }

  public int getMin() {
		return minStack.isEmpty() ? Integer.MIN_VALUE : minStack.peekFirst().val;
  }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
```



### Complexity

#### Direct method

```
Runtime: 46 ms, faster than 91.66% of Java online submissions for Min Stack.
Memory Usage: 37.9 MB, less than 96.22% of Java online submissions for Min Stack.
```



- Time
  - Each `push()` and `pop()` call takes O(1) time
- Space
  - If n elements get pushed onto the stack, there are n elements pushed onto the minStack, as well
  - O(2n)

#### Improved method

```
Runtime: 46 ms, faster than 91.66% of Java online submissions for Min Stack.
Memory Usage: 38.2 MB, less than 93.28% of Java online submissions for Min Stack.
```

- Time
  - Each `push()` and `pop()` call takes O(1) time
- Space
  - The minStack will take less space than the stack
  - O(< 2n)