package wangyi.spring2018;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 牛牛的闹钟弄
 */
public class AlarmClock1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextInt()) {
            int n = in.nextInt();
            AlarmTime[] alarmTimes = new AlarmTime[n];

            for (int i = 0; i < n; i++) {
                int hour = in.nextInt();
                int minute = in.nextInt();

                alarmTimes[i] = new AlarmTime(hour, minute);
            }

            int x = in.nextInt();
            int startHour = in.nextInt();
            int startMinute = in.nextInt();
            AlarmTime startTime = new AlarmTime(startHour, startMinute);
            startTime.minus(x);
            System.out.println(startTime);

            Arrays.sort(alarmTimes);
            System.out.println(Arrays.toString(alarmTimes));

            System.out.println(alarmTimes[binarySearch(alarmTimes, startTime)]);
        }
    }

    // 最后一个≤key
    public static int binarySearch(AlarmTime[] alarmTimes, AlarmTime key) {
        int left = 0;
        int right = alarmTimes.length - 1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (alarmTimes[mid].compareTo(key) <= 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }
}

class AlarmTime implements Comparable<AlarmTime> {
    private int hour;
    private int minute;

    public AlarmTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void minus(int m) {
        if (m > minute) {
            hour = (--hour + 24) % 24;
            minute = (minute - m + 120) % 60;
        } else {
            minute -= m;
        }
    }

    @Override
    public String toString() {
        return this.hour + " " + this.minute;
    }

    @Override
    public int compareTo(AlarmTime o) {
        if (this.hour > o.getHour()) {
            return 1;
        } else if (this.hour == o.getHour()) {
            if (this.minute > o.getMinute()) {
                return 1;
            } else if (this.minute == o.getMinute()) {
                return 0;
            }
        }
        return -1;
    }
}
