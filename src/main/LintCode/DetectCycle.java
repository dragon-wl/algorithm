import org.junit.Test;

import java.util.LinkedList;

/**
 * @author wanglong
 * @brief
 * @date 2019-08-16 16:16
 */

/**
 * 103. 带环链表 II
 * 中文English
 * 给定一个链表，如果链表中存在环，则返回到链表中环的起始节点，如果没有环，返回null。
 *
 * Example
 * 样例 1:
 *
 * 输入：null,no cycle
 * 输出：no cycle
 * 解释：
 * 链表为空，所以没有环存在。
 * 样例 2:
 *
 * 输入：-21->10->4->5，tail connects to node index 1
 * 输出：10
 * 解释：
 * 最后一个节点5指向下标为1的节点，也就是10，所以环的入口为10。
 * Challenge
 * 不使用额外的空间
 * https://www.lintcode.com/problem/linked-list-cycle-ii/description
 */

public class DetectCycle {


//Definition for ListNode
  public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }

    /**
     * @param head: The first node of linked list.
     * @return: The node where the cycle begins. if there is no cycle, return null
     */
    public ListNode detectCycle(ListNode head) {
        // write your code here
        if (head == null || head.next == null)
            return null;
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null && slow != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    @Test
    public void test(){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        ListNode cross = new ListNode(4);
        head.next.next.next =cross;
        cross.next = new ListNode(5);
        cross.next.next = cross;
        System.out.println(detectCycle(head).val); //4

        System.out.println(detectCycle(null)); //null

        System.out.println(detectCycle(new ListNode(1))); //null

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        System.out.println(detectCycle(head2));  //null
    }
}


//九章题解：https://www.jiuzhang.com/solution/remove-k-digits/

//九章的解法是：while(fast != slow) 当fast或者slow遇到null时候返回null