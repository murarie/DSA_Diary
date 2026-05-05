/*
61. Rotate List
Solved
Medium
Topics
premium lock icon
Companies
Given the head of a linked list, rotate the list to the right by k places.

 

Example 1:


Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]
Example 2:


Input: head = [0,1,2], k = 4
Output: [2,0,1]
 

Constraints:

The number of nodes in the list is in the range [0, 500].
-100 <= Node.val <= 100
0 <= k <= 2 * 109 */

// Solution
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0) return head;
        ListNode ttail=head;
        ListNode thead=head;
        ListNode ohead=head;
        int len=0;
        while(ttail!=null){
            ttail=ttail.next;
            len++;
        }
        k = k % len;
        if(k == 0) return head;
        ttail=head;
        for(int i=0;i<len-k-1;i++){
            ttail=ttail.next;
        }
        thead=ttail.next;
        ttail.next=null;
        ListNode newHead = thead;

        while(thead.next!=null){
            thead=thead.next;
        }
        thead.next=ohead;
        return newHead;
    }
}