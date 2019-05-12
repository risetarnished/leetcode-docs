# Top K Frequent Elements

[https://leetcode.com/problems/top-k-frequent-elements/](https://leetcode.com/problems/top-k-frequent-elements/)

## Description

Given a non-empty array of integers, return the **k** most frequent elements.

**Example 1:**

```
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
```

**Example 2:**

```
Input: nums = [1], k = 1
Output: [1]
```

**Note:**

- You may assume *k* is always valid, 1 ≤ *k* ≤ number of unique elements.
- Your algorithm's time complexity **must be** better than O(*n* log *n*), where *n* is the array's size.

**Data:**

- Accepted
  - 195,558
- Submissions
  - 359,421 

## Assumption

- The input consists of integers only and should not be null or empty
- 1 <= k <= # of unique elements

## Solution

### High-level Idea

- Use a HashMap to represent the relationship between <element, frequency>, aka a frequency map
- Use a min heap of size k to store the largest k map entries based on their frequency
- Poll everything out of the heap and it is the answer

### Code

```java
class Solution {
  public List<Integer> topKFrequent(int[] nums, int k) {
    // Corner cases check
    List<Integer> result = new ArrayList<>();
    if (nums == null || nums.length == 0 || k <= 0) {
      return result;
    }
    // An entry represents <element, frequency>
    Map<Integer, Integer> frequency = getFrequencyMap(nums);
    // The min heap stores the current most frequent k elements
    PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
        k, new Comparator<Map.Entry<Integer, Integer>>() {
          @Override
          public int compare(Map.Entry<Integer, Integer> one,
                             Map.Entry<Integer, Integer> two) {
            if (one.getValue() == two.getValue()) {
              return 0;
            }
            return one.getValue() < two.getValue() ? -1 : 1;
          }
        }
    );
    int step = 0;
    for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
      if (step < k) {
        minHeap.offer(entry);
        step++;
      } else if (entry.getValue() > minHeap.peek().getValue()) {
        minHeap.poll();
        minHeap.offer(entry);
      }
    }
    while (!minHeap.isEmpty()) {
      result.add(minHeap.poll().getKey());
    }
    Collections.reverse(result);
    return result;
  }
  
  private Map<Integer, Integer> getFrequencyMap(int[] nums) {
    Map<Integer, Integer> frequency = new HashMap<>(nums.length);
    for (int num : nums) {
      frequency.put(num, frequency.getOrDefault(num, 0) + 1);
    }
    return frequency;
  }
}
```

### Complexity

- Time
  - O(n) to get the frequency map
  - Each offer/poll operation on the heap with a size of k costs O(log(k))
  - So, generating the heap costs O(n log(k))
  - Getting the result from the heap costs O(k log(k))
  - Reversing the result costs O(n)
    - Can be avoided if implemented using LinkedList instead of ArrayList
  - Total time is O(n log(k))
- Space
  - Frequency map costs O(n)
  - MinHeap costs O(k)
  - Total space is O(n + k)

### Follow Up

_Similar problem:_

[692. Top K Frequent Words](https://leetcode.com/problems/top-k-frequent-words/)