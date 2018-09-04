package longestIncreasingSubsequence.HDU1160;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int index = 1;
        ArrayList<Mouse> mice = new ArrayList<>();
        while (in.hasNextInt()) {
            int w = in.nextInt();
            int s = in.nextInt();

            mice.add(new Mouse(index++, w, s));
        }

        Collections.sort(mice);

        int n = mice.size();
        int max = -1;
        int[] dp = new int[n];
        int[] pre = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            pre[i] = i;
            for (int j = 0; j < i; j++) {
                if (mice.get(j).getWeight() < mice.get(i).getWeight() && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    pre[i] = j;
                }
            }
            if (max < dp[i]) {
                max = dp[i];
            }
        }

        System.out.println(max);

        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            if (max == dp[i]) {
                while (pre[i] != i) {
                    stack.push(mice.get(i).getIndex());
                    i = pre[i];
                }
                stack.push(mice.get(i).getIndex());
                break;
            }
        }

        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}

class Mouse implements Comparable<Mouse> {
    private int index;
    private int weight;
    private int speed;

    public Mouse(int index, int weight, int speed) {
        this.index = index;
        this.weight = weight;
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Mouse{" +
                "index=" + index +
                ", weight=" + weight +
                ", speed=" + speed +
                '}';
    }

    public int getIndex() {
        return index;
    }

    public int getWeight() {
        return weight;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public int compareTo(Mouse o) {
        if (this.speed < o.speed) {
            return 1;
        } else if (this.speed == o.speed) {
            return 0;
        }
        return -1;
    }
}
