import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* 组合电话号码对应英文字母：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
思想：回溯法，遍历每个数字时，得到它的字符集，每次用字符集的一个字母一直向下，到底后回来这个位置，换一个字母继续，类似建树的过程或者深度优先遍历
 */


public class LetterCombinations {
    public static List<String> letterCombinations(String digits) {
        List<String> retList = new ArrayList<String>();
        if (digits == null || digits.length() == 0) return retList;

        HashMap<Character, String> letters = new HashMap<Character, String>(){{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        StringBuffer tmpStr = new StringBuffer();
        buildCombination(retList, digits, letters, 0, tmpStr);
        return retList;
    }

    /**
     * 回溯法组件字符串list
     * @param retList 结果List
     * @param digits  输入的测试数字，如 “123”
     * @param letters 数字对应字符集的map
     * @param index   当前处理数字的下标
     * @param tmpStr  储存临时字符串结果，这玩意能加能删就好体现它的作用
     */
    public static void buildCombination(List<String> retList, String digits, HashMap<Character, String> letters, int index, StringBuffer tmpStr) {
        if (index == digits.length()) {
            retList.add(tmpStr.toString());    // 长度等于数字长度，则不会再增加，记录结果
        } else {
            char number = digits.charAt(index);
            String charSeries = letters.get(number);
            for (int i=0; i < charSeries.length(); i++) {   // 比如，abc，遍历abc，依次传入a、b、c
                tmpStr.append(charSeries.charAt(i));
                buildCombination(retList, digits, letters, index+1, tmpStr);
                tmpStr.deleteCharAt(index);    // 比如abc，上一个传的是a，index这个位置要先删除a，换成b，再和后面的组合
            }
        }
    }

    public static void main(String[] args) {
        List<String> ret = letterCombinations("23");
        for (String item: ret) {
            System.out.println(item);
        }
        /*
            ad
            ae
            af
            bd
            be
            bf
            cd
            ce
            cf
         */
    }
}

// 还是官方厉害。。。。。。。。。。。。。。。。看书的时间总是很少




