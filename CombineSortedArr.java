/* 剑指5扩展题：合并两个有序数组，要求时间复杂度O(n)
   思路：类似5题思路
*/
public class CombineSortedArr {

    public static void combine(int[] arrA, int[] arrB) {
        int lengthA = 0;
        int lengthB = 0;
        while (arrA[lengthA] != 0) {
            lengthA ++;
        }
        while (arrB[lengthB] != 0) {
            lengthB ++;
        }

        int newLengthA = lengthA + lengthB;
        int ptrA = lengthA - 1;
        int ptrNewA = newLengthA - 1;
        int ptrB = lengthB - 1;

        while (ptrB >= 0 || ptrA >= 0) {
            if (ptrB < 0){
                arrA[ptrNewA] = arrA[ptrA];
                ptrA --;
                ptrNewA --;
                continue;
            }
            if (ptrA < 0){
                arrA[ptrNewA] = arrA[ptrB];
                ptrB --;
                ptrNewA --;
                continue;
            }
            if (arrB[ptrB] > arrA[ptrA]) {
                arrA[ptrNewA] = arrB[ptrB];
                ptrB --;
                ptrNewA --;
            } else if (arrB[ptrB] < arrA[ptrA]) {
                arrA[ptrNewA] = arrA[ptrA];
                ptrA --;
                ptrNewA --;
            } else {
                arrA[ptrNewA] = arrA[ptrA];
                arrA[ptrNewA - 1] = arrB[ptrB];
                ptrA --;
                ptrB --;
                ptrNewA -= 2;
            }

        }
    }


    public static void main(String[] args) {
        int[] a = new int[100];
        for (int i = 1; i <= 7; i++) {
            a[i-1] = i;
        }
        int[] b = new int[10];
        b[0] = 1;
        b[1] = 3;
        b[2] = 5;
        b[3] = 7;
        b[4] = 9;

        combine(a, b);

        for (int item: a) {
            if (item != 0) System.out.print(item);   // 112334556779
        }

    }
}
