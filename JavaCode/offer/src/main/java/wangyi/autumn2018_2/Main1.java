package wangyi.autumn2018_2;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String newStr = "";

        int ans;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length(); i++) {
            StringBuilder str1 = reverseString(str, 0, i);
            StringBuilder str2 = reverseString(str, i + 1, str.length() - 1);
            newStr = str1.append(str2).toString();

            char[] chars = newStr.toCharArray();

            ans = 1;
            for (int j = 1; j < chars.length; j++) {
                if (chars[j] != chars[j - 1]) {
                    ans++;
                } else {
                    max = max < ans ? ans : max;
                    ans = 1;
                }
            }
        }

        System.out.println(max);
    }

    public static StringBuilder reverseString(String str, int from, int to) {
        char[] chars = str.toCharArray();
        while (from < to) {
            char t = chars[from];
            chars[from++] = chars[to];
            chars[to--] = t;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(c);
        }
        return sb;
    }
}
