package binaryIndexedTree;

public class BinaryIndexedTree {
    public int lowbit(int x) {
        return x & -x;
    }

    // 单点更新，val为增加的部分
    public void update(int[] c, int i, int val) {
        while (i < c.length) {
            c[i] += val;
            i += lowbit(i);
        }
    }

    // 区间[1, i]的和
    public int sum(int[] c, int i) {
        int rec = 0;
        while (i > 0) {
            rec += c[i];
            i -= lowbit(i);
        }
        return rec;
    }
}
