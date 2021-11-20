/* 剑指5：字符数组把空格替换成%20，要求时间复杂度O(n)
   思路：从后向前，两个指针，把前面的字符替换到后面，这样每个字符只移动一次，如果从前向后，后面的字符要移动空格个数次，是O(n2)
*/
public class ReplaceSpace {

    public static void replaceBlank(char[] charArr, int maxLength){
        int arrLength = 0;
        int blankCount = 0;
        while (charArr[arrLength] != '\u0000') {
            if (charArr[arrLength] == ' ') {
               ++blankCount;
            }
            ++arrLength;
        }

        if (blankCount == 0) return;
        int end2 = arrLength + 2*blankCount - 1;
        int end1 = arrLength - 1;
        System.out.println(end2);
        System.out.println(end1);
        if(end2 > maxLength) return;

        while (end1 != end2) {
            System.out.println(charArr[end1]);
            if (charArr[end1] == ' '){
                charArr[end2] = '0';
                charArr[end2 - 1] = '2';
                charArr[end2 - 2] = '%';
                end2 -= 3;
                end1 --;
            } else {
                charArr[end2] = charArr[end1];
                end1--;
                end2--;
            }
        }

    }

    public static void main(String[] args) {
        char [] arr = new char[50];
        String testStr = " We are   happy. ";
        for(int i = 0;i < testStr.length(); i++) {
            arr[i] = testStr.charAt(i);
        }
        replaceBlank(arr, 50);

        for (char c : arr) {
            System.out.print(c);
        }

    }
}
