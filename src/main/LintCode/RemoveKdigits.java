import org.junit.Test;

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

        if(num == null || num.length() ==0)
            return null;
        if(k >= num.length())
            return "0";
        if(k <= 0)
            return num;
        int[] diff = new int[num.length()];
        for(int i = 0; i < num.length(); i ++){
            if(i - k < 0){
                diff[i] = '0' - num.charAt(i);
            }else{
                diff[i] = num.charAt(i - k) - num.charAt(i); //>0 增加 ； <0 减少
            }
        }
        int index = 0;
        for(; index <= num.length() - 1; index ++){
            if(diff[index] > 0)
                break;
        }
        String str ="";
        for(int i = 0; i < num.length(); i ++){
            if(i >index || i < index - k + 1)
                str += num.charAt(i);
            else if(num.charAt(i) == '0')
                str = "";
        }
        return str;
    }


    @Test
    public void test(){
//        System.out.println(removeKdigits("10200",1)); //200
        System.out.println(removeKdigits("1432219",3)); //1219
    }
}

//wring answer
//错误理解题意，认为去掉的字符一定是连续的