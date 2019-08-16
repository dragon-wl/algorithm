import org.junit.Test;

/**
 * @author wanglong
 * @brief
 * @date 2019-08-16 11:04
 */

/**
 * 给定单链表，将所有奇数节点连接在一起，然后将偶数节点连接在一起。 请注意，这里我们讨论的是节点编号，而不是节点中的值。
 *
 * Example
 * 样例1:
 *
 * 输入： 1->2->3->4->5->NULL
 * 输出： 1->3->5->2->4->NULL
 * 样例2:
 *
 * 输入： 2->1->null
 * 输出： 2->1->null
 * Notice
 * 奇数节点和偶数节点的相对位置应当不变。
 * 第一个节点应为奇数，第二个节点为偶数，以此类推……
 * https://www.lintcode.com/problem/odd-even-linked-list/description
 */


public class OddEvenList {


    //Definition for ListNode
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    /**
     * @param head: a singly linked list
     * @return: Modified linked list
     */
    public ListNode oddEvenList(ListNode head) {
        // write your code here
        if(head == null || head.next == null)
            return head;
        ListNode odd = head, even = head.next;
        ListNode evenHead = head.next;
        while(even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = even.next.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    @Test
    public void test(){
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next =new ListNode(4);
        root.next.next.next.next = new ListNode(5);
        ListNode head = oddEvenList(root);
        System.out.println(head);
    }
}

//九章题解：https://www.jiuzhang.com/solution/odd-even-linked-list/
//链表的基础操作，我们设置两个指针来分别存放奇节点和偶节点，最后将偶节点的表头接在奇节点的末尾即可