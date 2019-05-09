# Number of Islands

[https://leetcode.com/problems/number-of-islands/](https://leetcode.com/problems/number-of-islands/)

## Description

Medium

Given a 2d grid map of `'1'`s (land) and `'0'`s (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

**Example 1:**

```
Input:
11110
11010
11000
00000

Output: 1
```

**Example 2:**

```
Input:
11000
11000
00100
00011

Output: 3
```

Accepted

343,603

Submissions

832,526

## Solution

### High-level Idea

- At each position/cell in the matrix, we can do a breadth-first search on its four neighboring cells
  - If the neighbor is water, continue
  - If the neighbor is an island, change it to water because it is connected to another island. E.g.,
    - The input 
      - **11**000
        **11**000
        00**1**00
        000**11**
      - is the same as:
      - **1**0000
        00000
        00**1**00
        0000**1**
- For BFS:
  - Start
    - the first cell, matrix\[0]\[0]
  - Data structure to store the visiting cells
    - FIFO queue
  - Expansion/generation rule
    - Expand the queue, if we see an island
      - check its four neighbors
      - change the neighbor to water if it is an island
      - add the neighbor to the queue
  - Terminate
    - When the queue becomes empty

### Code

```java
class Solution {
  public int numIslands(char[][] grid) {
    // Corner cases
    if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
      return -1;
    }
    int n = grid.length;
    int m = grid[0].length;
    int islands = 0;
    // Do a BFS on the grid for each cell
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        // Check the cell if it is an island
        if (grid[i][j] == '1') {
          // Mark its four (at most) neighbors to water
          markNeighbors(grid, i, j);
          islands++;
        }
      }
    }
    return islands;
  }
  
  private void markNeighbors(char[][] grid, int row, int col) {
    // Four directions: up, down, left, right
    int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // Use a Queue to store the visiting cells
    Queue<Cell> queue = new ArrayDeque<>();
    Cell first = new Cell(row, col);
    queue.offer(first);
    while (!queue.isEmpty()) {
      Cell curr = queue.poll();
      // Check all four neighbors
      for (int i = 0; i < dirs.length; i++) {
        int nextRow = row + dirs[i][0];
        int nextCol = col + dirs[i][1];
        if (!inBound(grid, nextRow, nextCol)) {
          continue;
        }
        Cell neighbor = new Cell(nextRow, nextCol);
        // Set the neighbor to water if it is an island to avoid re-visiting and re-calculation
        if (grid[nextRow][nextCol] == '1') {
          grid[nextRow][nextCol] = '0';
          // Offer the neighbor to the queue because it was an island and we need to
          // check until all of the neighbors are water
          queue.offer(neighbor);
        }
      }
    }
  }
  
  private boolean inBound(char[][] grid, int row, int col) {
    return row >= 0 && row < grid.length && 
           col >= 0 && col < grid[0].length;
  }
}

class Cell {
  int row;
  int col;
  
  Cell(int row, int col) {
    this.row = row;
    this.col = col;
  }
}
```

