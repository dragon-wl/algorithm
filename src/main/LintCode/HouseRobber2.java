/**
 * @author wanglong
 * @brief
 * @date 2019-08-16 11:58
 */

import org.junit.Test;

/**
 * 534. 打劫房屋 II
 * 中文English
 * 在上次打劫完一条街道之后，窃贼又发现了一个新的可以打劫的地方，但这次所有的房子围成了一个圈，这就意味着第一间房子和最后一间房子是挨着的。每个房子都存放着特定金额的钱。你面临的唯一约束条件是：相邻的房子装着相互联系的防盗系统，且 当相邻的两个房子同一天被打劫时，该系统会自动报警。
 *
 * 给定一个非负整数列表，表示每个房子中存放的钱， 算一算，如果今晚去打劫，在不触动报警装置的情况下, 你最多可以得到多少钱 。
 *
 * Example
 * 样例1
 *
 * 输入: nums = [3,6,4]
 * 输出: 6
 * 样例2
 *
 * 输入: nums = [2,3,2,3]
 * 输出: 6
 * Notice
 * 这题是House Robber的扩展，只不过是由直线变成了圈
 */


public class HouseRobber2 {
    /**
     * @param nums: An array of non-negative integers.
     * @return: The maximum amount of money you can rob tonight
     */
    public int houseRobber2(int[] nums) {
        // write your code here
        if(nums == null || nums.length ==0)
            return 0;
        if(nums.length == 1 )
            return nums[0];
        if(nums.length ==2)
            return nums[0]>nums[1]?nums[0]:nums[1];
        return Math.max(robberHelper(nums, 1, nums.length -1),
                robberHelper(nums,0,nums.length-2) );

    }

    private int robberHelper(int[] nums ,int start, int end){
        if(nums == null || end - start < 0)
            return 0;
        int [][] f = new int[nums.length][2];
        f[0][0] = nums[start];
        f[0][1] = 0;
        int i = 1;
        for( ;i <= end - start; i++){
            f[i][0] = f[i-1][1] + nums[start + i];
            f[i][1] = f[i-1][0]>f[i-1][1]?f[i-1][0]:f[i-1][1];
        }
        return f[i-1][0]>f[i-1][1]?f[i-1][0]:f[i-1][1];
    }


    @Test
    public void test()
    {
        int[] a = {3,6,4};
        System.out.println(houseRobber2(a));
    }
}




//动态规划
//考虑前若干个房子，记录抢最后一个房子或者不抢最后一个房子能抢到的最多的钱
//然后交叉更新
//九章题解：https://www.jiuzhang.com/solution/house-robber-ii/

//robberHelper(nums, 1, nums.length -1)可以找出不抢第一栋房子的最佳方案
//robberHelper(nums,0,nums.length-2) 可以找出不抢最后一栋房子的最佳方案
//两种综合总可以找出不同时抢首尾房子的最优解

//转移方程
//f(i,抢) = f(i-1, 不抢) + a(i)
//f(i,不抢) =  max{ f(i-1, 抢)，  f(i-1, 不抢)}

/**
 * 例子： 2 9 6  4  2  8  2  1  9
 * f抢：  2 9 8 13 11 21 13 22 30   -->抢该栋房子 = 不抢前一栋所得 + 该栋价值
 * 不抢： 0 2 9 9  13 11 21 21 22   --> 不抢该房子 = 抢/不抢前一栋中所得最大的
 */
