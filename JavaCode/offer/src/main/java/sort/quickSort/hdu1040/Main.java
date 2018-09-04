package sort.quickSort.hdu1040;

import sort.quickSort.QuickSort;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T;

        T = in.nextInt();
        QuickSort quickSort = new QuickSort();
        while (T-- > 0) {
            int n = in.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }

            quickSort.quickSort(arr, 0, n - 1);

            int i;
            for (i = 0; i < n - 1; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println(arr[i]);
        }
    }
}
