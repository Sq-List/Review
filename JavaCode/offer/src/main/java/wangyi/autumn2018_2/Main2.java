package wangyi.autumn2018_2;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        while (t-- != 0) {
            int n = in.nextInt();
            int k = in.nextInt();

            int ans;
            if (k == 1 || k == 0) {
                ans = 0;
            } else {
                if (2 * k <= n + 1) {
                    ans = k - 1;
                } else {
                    ans = n - k;
                }
            }

            System.out.println("0 " + ans);
        }
    }
}
