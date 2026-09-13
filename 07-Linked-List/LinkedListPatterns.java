// Linked List pattern — reference implementations (Java)
// Compile: javac LinkedListPatterns.java   Run: java LinkedListPatterns
public class LinkedListPatterns {

    static class ListNode {
        int val; ListNode next;
        ListNode(int v) { val = v; }
    }

    // 1) Reverse in place — three pointers. O(n) / O(1).
    static ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;   // save the rest
            curr.next = prev;            // flip the arrow
            prev = curr;                 // advance prev
            curr = next;                 // advance curr
        }
        return prev;                     // new head
    }

    // 2) Cycle detection — Floyd's fast & slow. O(n) / O(1).
    static boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    // 3) Merge two sorted lists — dummy node + splice. O(n+m) / O(1).
    static ListNode mergeTwoLists(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0), tail = dummy;
        while (a != null && b != null) {
            if (a.val <= b.val) { tail.next = a; a = a.next; }
            else { tail.next = b; b = b.next; }
            tail = tail.next;
        }
        tail.next = (a != null) ? a : b;
        return dummy.next;
    }

    // 4) Remove nth node from end — fast leads by n, uses dummy. O(n) / O(1).
    static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;
        for (int i = 0; i < n; i++) fast = fast.next;   // lead by n
        while (fast.next != null) { fast = fast.next; slow = slow.next; }
        slow.next = slow.next.next;                     // unlink
        return dummy.next;
    }

    // helpers
    static ListNode build(int... v) {
        ListNode dummy = new ListNode(0), t = dummy;
        for (int x : v) { t.next = new ListNode(x); t = t.next; }
        return dummy.next;
    }
    static String show(ListNode h) {
        StringBuilder sb = new StringBuilder();
        while (h != null) { sb.append(h.val); if (h.next != null) sb.append("->"); h = h.next; }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(show(reverse(build(1,2,3,4))));               // 4->3->2->1
        System.out.println(show(mergeTwoLists(build(1,3,5), build(2,4,6)))); // 1->2->3->4->5->6
        System.out.println(show(removeNthFromEnd(build(1,2,3,4,5), 2)));  // 1->2->3->5
        System.out.println(hasCycle(build(1,2,3)));                      // false
    }
}
