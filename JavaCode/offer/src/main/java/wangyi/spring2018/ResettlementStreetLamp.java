package wangyi.spring2018;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 安置路灯
 */
public class ResettlementStreetLamp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int t = in.nextInt();
            while (t-- > 0) {
                int n = in.nextInt();
                in.nextLine();

                int ans = 0;
                String line = in.nextLine();

                for (int i = 0; i < n; i++) {
                    for (; i < n && line.charAt(i) == 'X'; i++) {}
                    for (; i < n && line.charAt(i) == '.'; ans++, i += 3) {}
                }

                System.out.println(ans);
            }
        }
    }
}
