package wangyi.spring2018;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 牛牛找工作
 */
public class FindWork {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N, M;

        while (in.hasNextInt()) {
            N = in.nextInt();
            M = in.nextInt();
            int[][] arr = new int[N][2];

            TreeMap<Integer, Integer> work = new TreeMap<>();

            for (int i = 0; i < N; i++) {
                arr[i][0] = in.nextInt();
                arr[i][1] = in.nextInt();
            }

            Arrays.sort(arr, ((o1, o2) -> o1[0] - o2[0]));

            work.put(arr[0][0], arr[0][1]);
            for (int i = 1; i < N; i++) {
                arr[i][1] = Math.max(arr[i][1], arr[i - 1][1]);
                work.put(arr[i][0], arr[i][1]);
            }

            for (int i = 0; i < M; i++) {
                int a = in.nextInt();
                Integer index = work.floorKey(a);
                if (index != null) {
                    System.out.println(work.get(index));
                } else {
                    System.out.println(0);
                }
            }
        }
    }
}
