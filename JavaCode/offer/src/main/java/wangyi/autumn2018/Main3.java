package wangyi.autumn2018;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] a = new int[n];

        a[0] = in.nextInt();
        for (int i = 1; i < n; i++) {
            a[i] = in.nextInt() + a[i - 1];
        }

        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            System.out.println(binarySearch(a, in.nextInt()) + 1);
        }
    }

    public static int binarySearch(int[] a, int key) {
        int left = 0;
        int right = a.length - 1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (a[mid] >= key) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
