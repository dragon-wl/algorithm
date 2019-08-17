import org.junit.Test;

/**
 * @author wanglong
 * @brief
 * @date 2019-08-17 10:52
 */


/**
 * 给你一个链表以及一个k,将这个链表从头指针开始"每k个"翻转一下。
 * 链表元素个数不是k的倍数，最后剩余的不用翻转。
 * https://www.lintcode.com/problem/reverse-nodes-in-k-group/description
 */


public class ReserveKGroup {

    // Definition for ListNode
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * @param head: a ListNode
     * @param k: An integer
     * @return: a ListNode
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        while(head != null){
            head = reverse(head,k);
            if(head == null)
                break;
        }
        return dummy.next;
    }

    /**
     *  head  -> n1  -> n2  -> n3 ->... -> nk ->nk+1
     *  =>
     *  head -> nk -> nk-1 ->....-> n2 -> n1 -> nk+1
     *  return n1
     */

    private ListNode reverse(ListNode head, int k){
        ListNode nk = head;
        for(int i = 0; i < k; i ++){
            if(nk == null){
                return null;
            }
            nk = nk.next;
        }
        //保证最后一次nk = nk.next指向的真实nk不为空
        if(nk == null)
            return null;

        //一些固定的指针
        ListNode n1 = head.next; // k >= 1, 所以n1 != null
        ListNode nknext = nk.next; //可能为空


        //reverse
        ListNode curt = head.next;
        ListNode prev = nk.next;
        while(curt != nknext && curt != null){  //此处不可以写curt != nk.next。因为nk在循环过程中next指向发生了变化
            ListNode tmp = curt.next;
            curt.next = prev;
            prev = curt;
            curt = tmp;
        }

        //connect
        head.next = nk;
        return n1;

    }

    @Test
    public void test(){
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(5);
//        root.next.next.next.next.next = new ListNode(6);
        ListNode res = reverseKGroup(root,2);
        while(res != null){
            System.out.println(res.val);
            res = res.next;
        }

    }
}

//笔记
/**
 * 翻转链表可以使用的指针有 prev、curt、tmp
 * prev 指向当前节点的上一节点（也就是当前节点修改后应该指向的节点），如果是从头开始翻转，prev可以设置为null
 *
 * curt 当前节点，curt所处的范围在需要变更next指向的节点上，且节点不可以为空
 *
 * tmp 当前节点的下一节点，主要是在改变curt.next之前，先讲curt.next记为tmp，之后curt在向前移动时，可以直接curt = tmp
 *
 * 可以记住下面的固定句式
 *         ListNode curt = 需要变换next指向的节点-起点;
 *         ListNode prev = 起点的next的指向;
 *         while(curt需要满足的范围条件){  //如果此处的范围可能因为循环体内容发生变动，应避免出现死循环
 *             ListNode tmp = curt.next;
 *             curt.next = prev;
 *             prev = curt;
 *             curt = tmp;
 *         }
 */

//图解
/**
 * /**
 *      变换前
 *      *  head->   n1  -> n2  -> n3 ->... -> nk ->nk+1
 *                  ^      ^      ^           ^
 *                  |      |      |           |
                     需要调整next指针的节点结合 从n1到nk，除了n1之前其他节点都是将next节点指向prev
 *
 *      =>
 *      变换后
 *      *  head->   n1  <- n2  <- n3 <-... <- nk  nk+1-> nk+2
 *
 *      如果在while循环之前，prev初值为null，经过针对每个节点调整的while循环之后，结果如上图，此时是把完整的链表搞断了。
 *      所以还需要连接
 *
 *     =>
 *                ------------------------------
 *      连接      |                             |
 *      head->   n1  <- n2  <- n3 <-... <- nk  nk+1-> nk+2
 *        |                                /
 *        --------------------------------
 *      连接之后
 *      *  head -> nk -> nk-1 ->....-> n2 -> n1 -> nk+1
 *      *
 *
 *      此时再返回n1
 */

//九章题解：https://www.jiuzhang.com/solution/reverse-nodes-in-k-group/
