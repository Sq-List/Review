package wangyi.spring2018;

import java.util.Arrays;
import java.util.Scanner;

public class AlarmClock {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextInt()) {
            int n = in.nextInt();
            int[] mins = new int[n];

            for (int i = 0; i < n; i++) {
                int hour = in.nextInt();
                int minute = in.nextInt();

                mins[i] = hour * 60 + minute;
            }

            int x = in.nextInt();
            int startHour = in.nextInt();
            int startMinute = in.nextInt() + startHour * 60;

            int lastMinute = startMinute - x;

            Arrays.sort(mins);
            int index = binarySearch(mins, lastMinute);

            System.out.println(mins[index] / 60 + " " + mins[index] % 60);
        }
    }

    public static int binarySearch(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] <= key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }
}
