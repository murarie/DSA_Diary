/*
3753. Total Waviness of Numbers in Range II
Solved
Hard
Topics
premium lock icon
Companies
Hint
You are given two integers num1 and num2 representing an inclusive range [num1, num2].

The waviness of a number is defined as the total count of its peaks and valleys:

A digit is a peak if it is strictly greater than both of its immediate neighbors.
A digit is a valley if it is strictly less than both of its immediate neighbors.
The first and last digits of a number cannot be peaks or valleys.
Any number with fewer than 3 digits has a waviness of 0.
Return the total sum of waviness for all numbers in the range [num1, num2].
 

Example 1:

Input: num1 = 120, num2 = 130

Output: 3

Explanation:

In the range [120, 130]:

120: middle digit 2 is a peak, waviness = 1.
121: middle digit 2 is a peak, waviness = 1.
130: middle digit 3 is a peak, waviness = 1.
All other numbers in the range have a waviness of 0.
Thus, total waviness is 1 + 1 + 1 = 3.

Example 2:

Input: num1 = 198, num2 = 202

Output: 3

Explanation:

In the range [198, 202]:

198: middle digit 9 is a peak, waviness = 1.
201: middle digit 0 is a valley, waviness = 1.
202: middle digit 0 is a valley, waviness = 1.
All other numbers in the range have a waviness of 0.
Thus, total waviness is 1 + 1 + 1 = 3.

Example 3:

Input: num1 = 4848, num2 = 4848

Output: 2

Explanation:

Number 4848: the second digit 8 is a peak, and the third digit 4 is a valley, giving a waviness of 2.

 

Constraints:

1 <= num1 <= num2 <= 1015​​​​​​​ */

// Solution

class Solution {

    static class Node {
        long cnt;
        long wav;

        Node(long cnt, long wav) {
            this.cnt = cnt;
            this.wav = wav;
        }
    }

    String s;
    Node[][][][] dp = new Node[20][2][11][11];
    boolean[][][][] vis = new boolean[20][2][11][11];

    private Node dfs(int pos, int started, int last,
                     int secondLast, boolean tight) {

        if (pos == s.length()) {
            return new Node(1, 0);
        }

        if (!tight && vis[pos][started][last][secondLast]) {
            return dp[pos][started][last][secondLast];
        }

        int limit = tight ? s.charAt(pos) - '0' : 9;

        long totalCnt = 0;
        long totalWav = 0;

        for (int d = 0; d <= limit; d++) {
            boolean ntight = tight && (d == limit);

            if (started == 0 && d == 0) {
                Node nxt = dfs(pos + 1, 0, 10, 10, ntight);

                totalCnt += nxt.cnt;
                totalWav += nxt.wav;
            } else {
                long add = 0;

                if (started == 1 && secondLast != 10) {
                    if ((last > secondLast && last > d) ||
                        (last < secondLast && last < d)) {
                        add = 1;
                    }
                }

                int nSecondLast = (started == 1) ? last : 10;

                Node nxt = dfs(pos + 1, 1, d, nSecondLast, ntight);

                totalCnt += nxt.cnt;
                totalWav += nxt.wav + add * nxt.cnt;
            }
        }

        Node res = new Node(totalCnt, totalWav);

        if (!tight) {
            vis[pos][started][last][secondLast] = true;
            dp[pos][started][last][secondLast] = res;
        }

        return res;
    }

    private long solve(long n) {
        if (n < 0) return 0;

        s = Long.toString(n);

        vis = new boolean[20][2][11][11];
        dp = new Node[20][2][11][11];

        return dfs(0, 0, 10, 10, true).wav;
    }

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }
}