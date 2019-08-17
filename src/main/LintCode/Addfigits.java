import org.junit.Test;

/**
 * @author wanglong
 * @brief
 * @date 2019-08-17 16:21
 */

/**
 * 给出一个非负整数 num，反复的将所有位上的数字相加，直到得到一个一位的整数。
 *
 * Example
 * 例1:
 *
 * 输入:
 * num=38
 * 输出:
 * 2
 * 解释:
 * 过程如下： 3 + 8 = 11, 1 + 1 = 2. 因为 2 只有一个数字，返回 2.
 *
 * 例2:
 *
 * 输入:
 * num=9
 * 输出:
 * 9
 * 解释:
 * 9<10,返回 9.
 */

public class Addfigits {
    /**
     * @param num: a non-negative integer
     * @return: one digit
     */
    public int addDigits(int num) {
        // write your code here
        while(num >= 10){
            int sum = 0;
            //计算各位之和
            while(num > 0){
                sum += num % 10;
                num = num / 10;
            }
            //判断各位之和是否已经小于10
            num = sum;
        }
        return num;
    }

    @Test
    public void test(){
        System.out.println(addDigits(39));
    }
}


//笔记
/**
 *
 * 记住这个结构：
 *
 *      while(num > 0){
 *          sum += num % 10;
 *          num = num / 10;
 *      }
 *
 *    统计所有数位之和，余数相加，商继续
 *
 */
