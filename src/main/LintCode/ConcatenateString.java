/**
 * @author wanglong
 * @brief
 * @date 2019-08-16 20:29
 */


import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 702. 连接两个字符串中的不同字符
 * 中文English
 * 给出两个字符串, 你需要修改第一个字符串，将所有与第二个字符串中相同的字符删除, 并且第二个字符串中不同的字符与第一个字符串的不同字符连接
 *
 * Example
 * 样例 1:
 *
 * 输入 : s1 = "aacdb", s2 = "gafd"
 * 输出 : "cbgf"
 * 样例 2:
 *
 * 输入 : "abcs", s2 = "cxzca"
 * 输出 : "bsxz"
 * https://www.lintcode.com/problem/concatenated-string-with-uncommon-characters-of-two-strings/description
 */



public class ConcatenateString {

    /**
     * @param s1: the 1st string
     * @param s2: the 2nd string
     * @return: uncommon characters of given strings
     */
    public String concatenetedString(String s1, String s2) {
        // write your code here
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s1.length(); i ++){
            char key = s1.charAt(i);
            if(map.keySet().contains(key))
                map.put(key, map.get(key) + 1);
            else
                map.put(key, 1);
        }
        String str2left = "";
        for(int i = 0; i < s2.length(); i ++){
            char curChar = s2.charAt(i);
            if(map.keySet().contains(curChar))
                map.put(curChar,  - 1);
            else
                str2left += curChar;
        }

        String resStr = "";
        for(int i = 0; i < s1.length(); i ++){
            char curChar = s1.charAt(i);
            if(map.keySet().contains(curChar) && map.get(curChar) > 0){
                map.put(curChar, map.get(curChar) - 1);
                resStr += curChar;
            }
        }
        return resStr + str2left;
    }

    @Test
    public void test(){
        String s1 = "aacbd";
        String s2 = "gafd";
        System.out.println(concatenetedString(s1, s2)); //cbgf

        String s3 = "abcs";
        String s4 = "cxzca";
        System.out.println(concatenetedString(s3, s4)); //bsxz

        String s5 = "aacdb";
        String s6 ="";
        System.out.println(concatenetedString(s5, s6)); //aacdb

        //自定义测试
        String s7 = "aacdb";
        String s8 ="ggabd";
        System.out.println(concatenetedString(s7, s8)); //cgg

    }
}


//题意理解 与 测试用例理解
// 1.所有与第二个字符串中相同的字符删除
// 2.第二个字符串中与第一个相同的字符也要全部删除