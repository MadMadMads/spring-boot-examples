package lambda;

import java.util.stream.Stream;

/**
 * @author: Luo
 * @description:
 * @time: 2020/11/4 22:26
 * Modified By:
 * lambda遍历链表
 */
public class ListNode {
    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(90);
        node1.next = new ListNode(200);
        node1.next.next = new ListNode(300);
        node1.next.next.next = new ListNode(400);
        Stream.iterate(node1,ListNode::getNext).limit(4).forEach( n -> {
            System.out.println(n.val);
        });
    }
}
