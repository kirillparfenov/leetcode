package dev.parfenov.leectode.medium;

// https://leetcode.com/problems/add-two-numbers/description/
public class Problem2_Add_two_numbers {
    public static void main(String[] args) {

//        var ln3 = new ListNode(3);
//        var ln4 = new ListNode(4, ln3);
//        var ln2 = new ListNode(2, ln4);
//
//        var ln4_2 = new ListNode(4);
//        var ln6 = new ListNode(6, ln4_2);
//        var ln5 = new ListNode(5, ln6);

//        var res = addTwoNumbers(ln2, ln5);

//        var l9_1 = new ListNode(9);
//        var l9_2 = new ListNode(9, l9_1);
//        var l9_3 = new ListNode(9, l9_2);
//        var l9_4 = new ListNode(9, l9_3);
//        var l9_5 = new ListNode(9, l9_4);
//        var l9_6 = new ListNode(9, l9_5);
//        var l9_7 = new ListNode(9, l9_6);
//
//        var l9_2_1 = new ListNode(9);
//        var l9_2_2 = new ListNode(9, l9_2_1);
//        var l9_2_3 = new ListNode(9, l9_2_2);
//        var l9_2_4 = new ListNode(9, l9_2_3);
//
//        var res = addTwoNumbers(l9_7, l9_2_4);

        var l0_1 = new ListNode(0);
        var l0_2 = new ListNode(0);

        var res = addTwoNumbers(l0_1, l0_2);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        var calculation = l1.val + l2.val;
        var val = calculation % 10;
        var keep = calculation / 10;

        var root = new ListNode(val);
        var tail = root;

        while (keep != 0 || l1.next != null || l2.next != null) {
            calculation = 0;
            calculation += keep;

            if (l1.next != null) {
                calculation += l1.next.val;
                l1 = l1.next;
            }

            if (l2.next != null) {
                calculation += l2.next.val;
                l2 = l2.next;
            }

            val = calculation % 10;
            keep = calculation / 10;

            tail.next = new ListNode(val);
            tail = tail.next;
        }

        return root;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
