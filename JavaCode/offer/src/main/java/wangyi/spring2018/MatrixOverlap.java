package wangyi.spring2018;

import java.util.Arrays;
import java.util.Scanner;

public class MatrixOverlap {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextInt()) {
            int n = in.nextInt();
            Matrix[] matrices = new Matrix[n];

            for (int i = 0; i < n; i++) {
                matrices[i] = new Matrix(in.nextInt());
            }

            for (int i = 0; i < n; i++) {
                matrices[i].setLeftDownY(in.nextInt());
            }

            for (int i = 0; i < n; i++) {
                matrices[i].setRightUpX(in.nextInt());
            }

            for (int i = 0; i < n; i++) {
                matrices[i].setRightUpY(in.nextInt());
            }

//            System.out.println(Arrays.toString(matrices));

            long ans = -1;
            Matrix[] dp = new Matrix[n];
            for (int i = 0; i < n; i++) {
                try {
                    dp[i] = (Matrix) matrices[i].clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }

                for (int j = 0; j < i; j++) {
                    Matrix tmp = dp[i].over(matrices[j]);
                    if (tmp != null && dp[i].getCover() < dp[j].getCover() + 1) {
                        tmp.setCover(dp[j].getCover() + 1);
                        dp[i] = tmp;
                    }
                }

                if (ans < dp[i].getCover()) {
                    ans = dp[i].getCover();
                }
            }

            System.out.println(ans);
        }
    }
}

class Matrix implements Cloneable {
    private long leftDownX;
    private long leftDownY;
    private long rightUpX;
    private long rightUpY;
    private long cover = 1;

    public Matrix(long leftDownX) {
        this.leftDownX = leftDownX;
        cover = 1;
    }

    public Matrix(long leftDownX, long leftDownY, long rightUpX, long rightUpY) {
        this(leftDownX, leftDownY, rightUpX, rightUpY, 1);
    }

    public Matrix(long leftDownX, long leftDownY, long rightUpX, long rightUpY, long cover) {
        this.leftDownX = leftDownX;
        this.leftDownY = leftDownY;
        this.rightUpX = rightUpX;
        this.rightUpY = rightUpY;
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "leftDownX=" + leftDownX +
                ", leftDownY=" + leftDownY +
                ", rightUpX=" + rightUpX +
                ", rightUpY=" + rightUpY +
                ", cover=" + cover +
                '}';
    }

    public void setLeftDownX(long leftDownX) {
        this.leftDownX = leftDownX;
    }

    public void setLeftDownY(long leftDownY) {
        this.leftDownY = leftDownY;
    }

    public void setRightUpX(long rightUpX) {
        this.rightUpX = rightUpX;
    }

    public void setRightUpY(long rightUpY) {
        this.rightUpY = rightUpY;
    }

    public long getLeftDownX() {
        return leftDownX;
    }

    public long getLeftDownY() {
        return leftDownY;
    }

    public long getRightUpX() {
        return rightUpX;
    }

    public long getRightUpY() {
        return rightUpY;
    }

    public long getCover() {
        return cover;
    }

    public void setCover(long cover) {
        this.cover = cover;
    }

    public Matrix over(Matrix matrix) {
        long cLeftDownX, cLeftDownY, cRightUpX, cRightUpY;
        cLeftDownX = Math.max(this.leftDownX, matrix.leftDownX);
        cLeftDownY = Math.max(this.leftDownY, matrix.leftDownY);
        cRightUpX = Math.min(this.rightUpX, matrix.rightUpX);
        cRightUpY = Math.min(this.rightUpY, matrix.rightUpY);
        if (cLeftDownX < cRightUpX && cLeftDownY < cRightUpY) {
            return new Matrix(cLeftDownX, cLeftDownY, cRightUpX, cRightUpY);
        }
        return null;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
