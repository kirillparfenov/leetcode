package dev.parfenov.leectode.medium;

// https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/

/**
 * Идея в следующем - у нас есть число n - это число, на которое нужно держать дистанцию относительно хвоста. <br>
 * По сути будет указатель, который назовем gap.<br>
 * Как только хвост увидел, что следующий элемент == null -> устанавливаем в gap элемент == gap.next.next (перепрыгиваем через элемент).<br>
 * В остальных случаях с каждой итерацией - передвигаем tail и gap, чтобы между ними было расстояние n.<br>
 */
public class Problem19_Remove_N_Element_From_End_List {
    public static void main(String[] args) {

        var node5 = new ListNode(5);
        var node4 = new ListNode(4, node5);
        var node3 = new ListNode(3, node4);
        var node2 = new ListNode(2, node3);
        var root = new ListNode(1, node2);

//        var root = new ListNode(1);

        var res = removeNthFromEnd(root, 1);
    }

    /*
     * Input: head = [1,2,3,4,5], n = 2
     * Output: [1,2,3,5]
     *
     * Input: head = [1], n = 1
     * Output: []
     * */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode gap = null;
        var tail = head;
        var counter = -1;

        while (tail != null) {
            counter++;

            if (counter == n)
                gap = head;
            else if (counter > n) {
                gap = gap.next;
            }

            if (tail.next == null) {
                if (gap == null)
                    return head.next; //случай, когда удаляется первый элемент в массиве
                else
                    gap.next = gap.next.next; //перекидываем ссылку через элемент
            }

            tail = tail.next; //передвигаем хвост
        }

        return head;
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
