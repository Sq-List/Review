package leetCode.addTwoNumbers;

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        int up = 0;
        int sum = l1.val + l2.val + up;
        up = sum / 10;
        sum = sum % 10;

        ListNode ans = new ListNode(sum);
        ListNode sAns = ans;

        l1 = l1.next;
        l2 = l2.next;

        while (l1 != null && l2 != null) {
            sum = l1.val + l2.val + up;
            up = sum / 10;
            sum = sum % 10;
            ans.next = new ListNode(sum);

            ans = ans.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        if (l1 != null) {
            ans.next = l1;
        } else if (l2 != null) {
            ans.next = l2;
        }

        while (up == 1) {
            if (ans.next != null) {
                ans = ans.next;
                ans.val += 1;
                up = ans.val / 10;
                ans.val %= 10;
            } else {
                ans.next = new ListNode(1);
                up = 0;
            }
        }

        return sAns;
    }

    public static void main(String[] args) {
        ListNode ans = null;
        ListNode sAns = ans;

        ans = new ListNode(1);
        ans = ans.next;
        ans = new ListNode(2);
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
