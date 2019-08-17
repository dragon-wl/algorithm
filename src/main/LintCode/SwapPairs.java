import org.junit.Test;

import java.util.List;

/**
 * @author wanglong
 * @brief
 * @date 2019-08-17 16:34
 */

/**
 * 451. 两两交换链表中的节点
 * 中文English
 * 给一个链表，两两交换其中的节点，然后返回交换后的链表。
 *
 * Example
 * 样例 1：
 *
 * 输入：1->2->3->4->null
 * 输出：2->1->4->3->null
 * 样例 2：
 *
 * 输入：5->null
 * 输出：5->null
 * Challenge
 * 你的算法只能使用常数的额外空间，并且不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * K组翻转的简化版本
 * https://www.lintcode.com/problem/swap-nodes-in-pairs/description
 */

public class SwapPairs {


     // Definition for ListNode
     public class ListNode {
          int val;
          ListNode next;
          ListNode(int x) {
              val = x;
              next = null;
          }
      }


    /**
     * @param head: a ListNode
     * @return: a ListNode
     */
    public ListNode swapPairs(ListNode head) {
        // write your code here
        if(head == null)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        // dummy/head -> n0 -> n1
        while(head != null){
            head = reverse(head,2);
        }
        return dummy.next;
    }

    private ListNode reverse( ListNode head, int k){
        if(head == null || head.next == null)
            return null;
        ListNode kNode = head;
        for(int i = 0; i < k; i ++){
            if(kNode == null)
                return null;
            kNode = kNode.next;
        }
        if(kNode == null)
            return null;
        ListNode n1 = head.next;
        ListNode nkNext = kNode.next;

        //reverse
        ListNode prev = nkNext;
        ListNode curt = n1;
        while(curt != nkNext){
            ListNode tmp = curt.next;
            curt.next = prev;
            prev = curt;
            curt = tmp;
        }
        //       _________________
        //      |                 \
        //head  n1 <- n2 <- kNode nkNext

        //connect
        head.next = kNode;
        return n1;
    }



    @Test
    public void test(){
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);

        ListNode res = swapPairs(node);
        while(res != null){
            System.out.println(res.val);
            res = res.next;
        }


    }
}

//K组翻转简化版本
//升级版本：k组翻转链表 https://www.lintcode.com/problem/reverse-nodes-in-k-group/

/**
 *
 * 此题目的九章解法：
 *
 * public ListNode swapPairs(ListNode head) {
 *         ListNode dummy = new ListNode(0);
 *         dummy.next = head;
 *
 *         head = dummy;
 *         while (head.next != null && head.next.next != null) {
 *             ListNode n1 = head.next, n2 = head.next.next;
 *             // head->n1->n2->...
 *             // => head->n2->n1->...
 *             head.next = n2;
 *             n1.next = n2.next;
 *             n2.next = n1;
 *
 *             // move to next pair
 *             head = n1;
 *         }
 *
 *         return dummy.next;
 *     }
 *
 *     https://www.jiuzhang.com/solution/swap-nodes-in-pairs/
 *
 * */