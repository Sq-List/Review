package wangyi.spring2018;

import java.util.Scanner;

/**
 * 被3整除
 */
public class BeDividedBy3 {
    public static int getNum(int x) {
        int num = (x / 3) * 2;
        num += (x % 3 == 2 ? 1 : 0);

        return num;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int l = in.nextInt();
            int r = in.nextInt();

            System.out.println(getNum(r) - getNum(l - 1));
        }
    }
}
