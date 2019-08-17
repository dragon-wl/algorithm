import org.junit.Test;

/**
 * @author wanglong
 * @brief
 * @date 2019-08-17 15:25
 */

/**
 * 867. 四键键盘
 * 中文English
 * 假设你有一个特殊的键盘，键盘上有如下键:
 *
 * 键1: (A): 在屏幕上打印一个'A'。
 * 键2: (Ctrl-A): 选择整个屏幕。
 * 键3: (Ctrl-C): 复制选择到缓冲区。
 * 键4: (Ctrl-V): 在屏幕上已有的内容后面追加打印缓冲区的内容。
 * 现在，你只能按键盘上N次(使用以上四个键)，找出你可以在屏幕上打印的“A”的最大数量
 *
 * Example
 * 样例 1:
 *
 * 输入: 3
 * 输出: 3
 * 解释: A, A, A
 * 样例 2:
 *
 * 输入: 7
 * 输出: 9
 * 解释: A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
 * Notice
 * 1 <= N <= 50
 * 答案将在32位有符号整数的范围内。
 * https://www.lintcode.com/problem/4-keys-keyboard/description
 */

public class KeyBoard4 {
    /**
     * @param N: an integer
     * @return: return an integer
     */
    public int maxA(int N) {
        if( N <= 0)
            return 0;
        int[] f = new int[N + 1];
        for(int i = 0; i <= N && i <4; i ++)
            f[i] = i;
        for(int i = 4; i <= N; i ++){
            f[i] = f[i - 1] + 1;
            for(int j = i-3; j > 0; j --){
                    f[i] = Math.max( f[i], f[j] * (i - j - 1));  //
            }
        }
        return f[N];
    }

    @Test
    public void test(){
        System.out.println(maxA(7)); //9
        System.out.println(maxA(2)); //2
    }
}


//九章题解
/**
 *
 * https://www.jiuzhang.com/solution/4-keys-keyboard/
 * 获得最优解的按键序列一定可以用以下两种子序列拼接而成:
 *
 * 数个连续的 A
 * Ctrl-A + Ctrl-C + 数个连续的 Ctrl-V
 * 设定状态: f[i] 表示i次按键可以获得的最多数量的A
 *
 * 我们的决策便是选用上面两种方案的哪一种, 枚举即可
 *
 * 状态转移方程:
 *
 * f[i] = max( f[i-1] + 1, f[j] * (i - j -1) )
 * 需要枚举j, 含义是连续按下了多少个 Ctrl-V
 *
 * 边界: f[i] = i, i <= 4
 *
 */