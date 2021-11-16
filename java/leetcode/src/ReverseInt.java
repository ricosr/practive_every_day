public class ReverseInt {
// https://leetcode-cn.com/problems/reverse-integer/
    public static int reverse(int x) {
        int ret = 0;
        if (x == 0) {
            return x;
        }
        while(x != 0){
            // 主要是这一步，防止下一次就越界了
            if (ret > Integer.MAX_VALUE / 10 || ret < Integer.MIN_VALUE / 10 ) return 0;
            ret = x % 10 + ret * 10;
            x /= 10;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(reverse(-1463847412));
    }

}
