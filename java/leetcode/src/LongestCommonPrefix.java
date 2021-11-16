public class LongestCommonPrefix {
// https://leetcode-cn.com/problems/longest-common-prefix/

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        for (int i=0; i<strs[0].length(); i++) {
             for (int j=1; j<strs.length; j++) {
                 if (i > strs[j].length()-1 || strs[j].charAt(i) != strs[0].charAt(i)) {
                     return strs[0].substring(0, i);
                 }
             }
        }
        return strs[0];
    }

    public static void main(String[] args) {
        String []arr = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(arr));

        String []arr2 = {"ab", "ab"};
        System.out.println(longestCommonPrefix(arr2));

    }

}


// 最近无聊的加班好多啊，烦啊烦啊烦。。。。。。。。。。。。。
