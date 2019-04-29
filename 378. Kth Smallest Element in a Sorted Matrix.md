# 378. Kth Smallest Element in a Sorted Matrix

[https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/)

## Description

Medium

Given a *n* x *n* matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

**Example:**

```
matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
```



**Note:** 
You may assume k is always valid, 1 ≤ k ≤ n2.

Accepted

104,476

Submissions

212,959

## Assumption

- The input matrix is not null or empty
- 1 <= k <= n * m

## Solution

### High-level Idea

- Use a min heap to store the elements processed so far such that we can get the smallest one in O(1) time (O(log(n)) in total because inserting the elements in to a heap is O(log(n)))
- Poll the smallest element and examine its two larger neighbors, the one to its left and below it, if they are still in bound
  - Offer the larger neighbors to the heap and continue
  - Use a HashSet to avoid adding the same cell for more than once
- Repeat the process for (k - 1) times such that the heap's top element then will be the result

### Code

```java
public class Solution {
  public int kthSmallest(int[][] matrix, int k) {
    // Corner cases
    if (matrix == null || matrix.length == 0 ||
        matrix[0] == null || matrix[0].length == 0) {
      return Integer.MIN_VALUE;
    }
    int rows = matrix.length;
    int cols = matrix[0].length;
    // Use a min heap to store the the elements being tested so far
    // such that we can easily get the smallest one in O(log(n)) time
    // When we poll the heap for the k-th time, we have the k-th smallest
    // element in the matrix
    PriorityQueue<Cell> minHeap = new PriorityQueue<>(k);
    // Use a HashSet to store the cells that have already been added to the heap
    Set<Cell> added = new HashSet<>();
    // Add the first cell into the heap and the set
    Cell first = new Cell(matrix[0][0], 0, 0);
    minHeap.offer(first);
    added.add(first);
    // For the smallest cell in the heap, check its two larger neighbors (left and down)
    // Add them into the heap
    // Do this for (k - 1) time and the heap's top is the result
    for (int i = 0; i < k - 1; i++) {
      Cell cell = minHeap.poll();
      if (cell.row + 1 < rows) {
        Cell next = new Cell(matrix[cell.row + 1][cell.col], cell.row + 1, cell.col);
        if (!added.contains(next)) {
          minHeap.offer(next);
          added.add(next);
        }
      }
      if (cell.col + 1 < cols) {
        Cell next = new Cell(matrix[cell.row][cell.col + 1], cell.row, cell.col + 1);
        if (!added.contains(next)) {
          minHeap.offer(next);
          added.add(next);
        }
      }
    }
    return minHeap.peek().val;
  }
}

class Cell implements Comparable<Cell> {
  int val;
  int row;
  int col;
  
  Cell(int val, int row, int col) {
    this.val = val;
    this.row = row;
    this.col = col;
  }
  
  @Override
  public int compareTo(Cell another) {
    if (this.val == another.val) {
      return 0;
    }
    return this.val < another.val ? -1 : 1;
  }
  
  /**
   * Override the hashCode() and equals() methods
   * because we need to use a hash map to avoid duplicates
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Cell)) {
      return false;
    }
    Cell another = (Cell) obj;
    return this.val == another.val &&
           this.row == another.row &&
           this.col == another.col;
  }
  
  @Override
  public int hashCode() {
    return 31 * 31 * 31 * this.val + 31 * 31 * this.row + 31 * this.col;
  }
}
```

### Complexity

- Time
  - Offer/poll a heap which has a size of n costs O(log(n))
  - There are n * m elements in the matrix
  - Total time is O(k * log(k))
- Space
  - The PriorityQueue takes up O(k)
  - The HashSet may take up to O(k)
  - Total space is O(k)

