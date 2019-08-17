import org.junit.Test;

import java.awt.*;

/**
 * @author wanglong
 * @brief
 * @date 2019-08-17 13:14
 */

/**
 * 给一个<Point>的List p，求满足p[i].x+p[j].x和p[i].y+p[j].y（i < j)都能被2整除的(i,j)对数。
 *
 * Example
 * 样例1
 *
 * 输入: p = [[1,2],[3,4],[5,6]]
 * 输出: 3
 * 解释:
 * p[0],p[1],p[2]两两组合，他们x与y之和都能被2整除。
 * 样例2
 *
 * 输入: p = [[0,3],[1,1],[3,4],[5,6]]
 * 输出: 1
 * 解释:
 * 只有p[2]和p[3]组合，他们的x与y之和都能被2整除。
 * Notice
 * 输入的list长度len <= 10000。
 *
 * https://www.lintcode.com/problem/number-pair-statistics/description
 */


public class PairNums {
    /**
     * @param p: the point List
     * @return: the numbers of pairs which meet the requirements
     */
    public int pairNumbers(Point[] p) {
        // Write your code here
        int cnt = 0;
        if(p == null || p.length < 2)
            return 0;
        for(int i = 0; i < p.length; i ++)
            for( int j =i+1; j < p.length; j ++){
                int x = p[i].x + p[j].x;
                int y = p[i].y + p[j].y;
                if(x % 2 ==0  && y % 2 ==0)
                    cnt ++;
            }
        return cnt;
    }


    @Test
    public void test(){
        Point[] p = {new Point(1,2), new Point(3,4), new Point(5,6)};
        System.out.println(pairNumbers(p)); //6
    }
}