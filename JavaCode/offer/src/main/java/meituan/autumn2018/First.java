package meituan.autumn2018;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class First {
    private static HashMap<Integer, LinkedList<Integer>> map;
    private static Integer ans;
    private static Integer max;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextInt()) {
            int n = in.nextInt();
            map = new HashMap<>(n << 1);

            for (int i = 0; i < n - 1; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                if (x > y) {
                    int tmp = x;
                    x = y;
                    y = tmp;
                }

                if (map.containsKey(x)) {
                    map.get(x).add(y);
                } else {
                    LinkedList<Integer> tmpList = new LinkedList<>();
                    tmpList.add(y);

                    map.put(x, tmpList);
                }
            }

            ans = 0;
            max = Integer.MIN_VALUE;
            search(1, 0);

            System.out.println(ans - max);
        }
    }

    public static void search(int root, int level) {
        if (map.containsKey(root)) {
            LinkedList<Integer> child = map.get(root);
            if (child != null) {
                ans += child.size() * 2;
                Iterator<Integer> integerIterator = child.iterator();
                while (integerIterator.hasNext()) {
                    search(integerIterator.next(), level + 1);
                }
            }
        } else {
            max = max > level ? max : level;
        }
    }
}
