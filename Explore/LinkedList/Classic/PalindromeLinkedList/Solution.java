package Explore.LinkedList.Classic.PalindromeLinkedList;

class ListNode {
  int val;
  ListNode next;
  ListNode(int x) {
    val = x;
  }
}

public class Solution {
  public boolean isPalindrome(ListNode head) {
		// Sanity check: no empty list
    // Empty list or a singleton list is a palindrome
    if (head == null || head.next == null) {
      return true;
    }
    // Step 1: find the middle node
    // If there are even number of nodes in the list
    // pick the first one of the two middle nodes
    // Such that size(first part) <= size(second part)
    ListNode mid = findMiddleNode(head);
    // Step 2: reverse the second part
    ListNode reversedSecond = reverseList(mid.next);
    // Cut the two parts apart from each other
    mid.next = null;
    // Step 3: compare each node in the two parts
    ListNode firstCurr = head;
    ListNode secondCurr = reversedSecond;
    while (firstCurr != null && secondCurr != null) {
      if (firstCurr.val != secondCurr.val) {
        return false;
      }
      firstCurr = firstCurr.next;
      secondCurr = secondCurr.next;
    }
    // size(first part) == size(second part) or size(second part) - 1
    // The one node gets left does not need to be checked
    // Step 4: restore the original data
    ListNode second = reverseList(reversedSecond);
    mid.next = second;
    return true;
  }
  
  private ListNode findMiddleNode(ListNode head) {
    ListNode slow = head;
    ListNode fast = head.next;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }
  
  private ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    return prev;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(2);
    head.next.next.next = new ListNode(1);
    System.out.println(solution.isPalindrome(head));
  }
}
