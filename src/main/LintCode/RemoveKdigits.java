import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author wanglong
 * @brief
 * @date 2019-08-16 12:51
 */

public class RemoveKdigits {
    /**
     * @param num: a string
     * @param k: an integer
     * @return: return a string
     */
    public String removeKdigits(String num, int k) {
        // write your code here
        if(k <= 0)
            return num;
        if(num == null || k >= num.length())
            return "0";
        int len = num.length() - k;
        Deque<Character> minNum = new LinkedList<>();
        for(int i = 0; i < num.length(); i ++){
            if(minNum.isEmpty()){
                minNum.addLast(num.charAt(i));
                continue;
            }
            while(!minNum.isEmpty() && minNum.peekLast() - num.charAt(i) > 0 && k > 0){
                minNum.removeLast();
                k --;
            }
            if(minNum.isEmpty() || minNum.size() < len)
                minNum.addLast(num.charAt(i));
        }
        while(!minNum.isEmpty() && minNum.peekFirst() == '0')
            minNum.removeFirst();
        String str = "";
        while(!minNum.isEmpty()){
            str += minNum.getFirst();
            minNum.removeFirst();
        }
        return str;
    }



    @Test
    public void test(){
        System.out.println(removeKdigits("10200",1)); //200
//        System.out.println(removeKdigits("1432219",3)); //1219
//         System.out.println(removeKdigits("22222222222222222222222222222222222222",20)); //
    }
}

//wring answer
//错误理解题意，认为去掉的字符一定是连续的