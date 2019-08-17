import org.junit.Test;

/**
 * @author wanglong
 * @brief
 * @date 2019-08-17 13:36
 */


/**
 * 843. 数字翻转
 * 中文English
 * 给定一个01构成的数组。你可以翻转1变成0或者反转0变成1。
 *
 * 请问最少反转多少次可以使得数组满足以下规则：
 *
 * 1的后面可以是1或者0，而0的后面必须是0。
 *
 * Example
 * 样例 1:
 *
 * 输入: [1,0,0,1,1,1]
 * 输出: 2
 * 解释: 把两个0翻转成1。
 * 样例 2:
 *
 * 输入: [1,0,1,0,1,0]
 * 输出: 2
 * 解释: 把第二个1和第三个1都翻转成0。
 * Notice
 * 数组长度 n <= 100000。
 * https://www.lintcode.com/problem/digital-flip/description
 */
public class FlipDigit {
    /**
     * @param nums: the array
     * @return: the minimum times to flip digit
     */
    public int flipDigit(int[] nums) {
        // Write your code here
        int minFlip = 0;
        if(nums == null || nums.length == 0)
            return 0;
        int zeroNum = 0;
        for(int i = 0; i < nums.length; i ++){
            if(nums[i] == 0)
                zeroNum ++;
        }

        minFlip = zeroNum;
        int zeroSum = 0, oneSum = 0;
        for(int i = nums.length -1; i >= 0; i --){
            if(nums[i] == 0)
                zeroSum ++;
            else
                oneSum ++;
            if(nums[i] == 0){
                int tmpFlip = oneSum + zeroNum - zeroSum;
                minFlip = minFlip < tmpFlip ? minFlip : tmpFlip;
            }
        }
        return minFlip;
    }


    @Test
    public void test(){
        int [] a1 = {1,0,0,1,0,0,0,1};
        System.out.println(flipDigit(a1));

        int [] a2 = {1,0,1,0,1,0};
        System.out.println(flipDigit(a2));
    }
}

//笔记
/**
 * 此题目我没有用动态规划，只是枚举0和1的分界点
 * 从实例出发开始找规律
 *
 * 有一种极端情况，全部0的都翻转，则翻转树木cnt = 0的总数
 *
 *
 *
 * （要知道：如果某位置的0不翻转为1，则该0之后的所有1全部要变为0。）
 *
 * 如果翻转部分0，则一定是优先翻转位于前面的0。从某个0开始，后面的0可以保持不变
 *
 * 那么遍历讨论，从哪个位置的0开始保持后面的0全部不变，前面的0全部翻转
 *
 *  1  0  1  1  1  0  1  0  0  1  1  1
 *                       |假设此处的0不翻转
 *                       则翻转的总数 = 从后往前统计到当前位置的1的数目 + 从前往后统计到当前位置之前0的树木
 *                                  = 从后往前统计到当前位置的1的数目 + 所有零数目 -  从后往前统计到当前位置的零数目
 *
 */