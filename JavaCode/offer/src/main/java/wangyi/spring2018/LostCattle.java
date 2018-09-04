package wangyi.spring2018;

import java.util.Scanner;

/**
 * 迷路的牛牛
 */
public class LostCattle {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] ans = {"N", "E", "S", "W"};
        while (in.hasNextInt()) {
            int n = in.nextInt();
            in.nextLine();
            String turn = in.nextLine();

            int now = 0;
            for (int i = 0; i < n; i++) {
                if (turn.charAt(i) == 'L') {
                    now += 3;
                } else {
                    now += 5;
                }
            }

            System.out.println(ans[now%4]);
        }
    }
}
