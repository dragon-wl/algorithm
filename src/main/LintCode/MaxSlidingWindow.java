import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.*;

/**
 * @author wanglong
 * @brief
 * @date 2019-08-16 13:38
 */

/**
 * 362. 滑动窗口的最大值
 * 中文English
 * 给出一个可能包含重复的整数数组，和一个大小为 k 的滑动窗口, 从左到右在数组中滑动这个窗口，找到数组中每个窗口内的最大值。
 *
 * Example
 * 样例 1:
 * 输入:
 * [1,2,7,7,8]
 * 3
 * 输出:
 * [7,7,8]
 * 样例 2:
 *
 * 输入:
 * [1,2,3,1,2,3]
 * 5
 * 输出:
 * [3,3]
 * Challenge
 * O(n)时间，O(k)的额外空间
 * https://www.lintcode.com/problem/sliding-window-maximum/description
 */
public class MaxSlidingWindow {
    /**
     * @param nums: A list of integers.
     * @param k: An integer
     * @return: The maximum number inside the window at each moving.
     */
    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        if(k <= 0)
            return result;
        LinkedList<Integer> windowQueue = new LinkedList<Integer>();
        windowQueue.clear();
        for(int i = 0; i < nums.length; i++){
            if(windowQueue.isEmpty())
                windowQueue.addLast(i);
            while(!windowQueue.isEmpty() && nums[windowQueue.getLast()] < nums[i]) //去除队尾较小值
                windowQueue.removeLast();
            while(!windowQueue.isEmpty() && i - windowQueue.getFirst() >= k) //去掉对头超范围值
                windowQueue.removeFirst();
            windowQueue.addLast(i);//将序号加入到队尾
            if(i+1 >= k)
                result.add(nums[windowQueue.getFirst()]);
        }
        return result;
    }

    @Test
    public void test(){
        int[] a = {3,9,2,6,5,4,1,6};
        List<Integer> m = maxSlidingWindow(a,3);
        for(int i = 0; i < m.size(); i ++)
            System.out.println(m.get(i));
    }
}


/**
 *  JAVA中双端队列的使用
 *
 *  Deque<T> deque = new LinkedList<T>()
 *
 *  deque.addLast()  deque.removeLast()  deque.getLast()
 *
 *  deque.addFirst()  deque.removeFirst() deque.removeLast()
 */


/**
 *  队列/堆栈 在get 和remove的时候一定要判断是否是Empty
 */


//此题目中几个取等号的地方要小心
//i - windowQueue.getFirst() >= k 时就要弹出队头元素
// i+1 >= k 就要开始输出元素啦