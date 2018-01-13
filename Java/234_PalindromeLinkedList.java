Given a singly linked list, determine if it is a palindrome.

Follow up:
Could you do it in O(n) time and O(1) space?

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null){
            return true;
        }
        ListNode cur = head;
        int len = 0;
        while (cur != null){
            len++;
            cur = cur.next;
        }
        cur = head;
        ListNode middleNode = findMiddle(cur);
        ListNode newHead2 = middleNode.next;
        cur = head;
        middleNode.next = null;
        ListNode newHead = reverse(cur);
        if (len %2 == 1){
            newHead = newHead.next;
        }
        while (newHead != null && newHead2 != null){
            if (newHead.val != newHead2.val){
                return false;
            }
            newHead = newHead.next;
            newHead2 = newHead2.next;
        }
        return true;
    }
    private ListNode findMiddle(ListNode head){
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    private ListNode reverse(ListNode head){
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null){
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }
}
