/*
3336. Find the Number of Subsequences With Equal GCD
Solved
Hard
Topics
premium lock icon
Companies
Hint
You are given an integer array nums.

Your task is to find the number of pairs of non-empty subsequences (seq1, seq2) of nums that satisfy the following conditions:

The subsequences seq1 and seq2 are disjoint, meaning no index of nums is common between them.
The GCD of the elements of seq1 is equal to the GCD of the elements of seq2.
Return the total number of such pairs.

Since the answer may be very large, return it modulo 109 + 7.

 

Example 1:

Input: nums = [1,2,3,4]

Output: 10

Explanation:

The subsequence pairs which have the GCD of their elements equal to 1 are:

([1, 2, 3, 4], [1, 2, 3, 4])
([1, 2, 3, 4], [1, 2, 3, 4])
([1, 2, 3, 4], [1, 2, 3, 4])
([1, 2, 3, 4], [1, 2, 3, 4])
([1, 2, 3, 4], [1, 2, 3, 4])
([1, 2, 3, 4], [1, 2, 3, 4])
([1, 2, 3, 4], [1, 2, 3, 4])
([1, 2, 3, 4], [1, 2, 3, 4])
([1, 2, 3, 4], [1, 2, 3, 4])
([1, 2, 3, 4], [1, 2, 3, 4])
Example 2:

Input: nums = [10,20,30]

Output: 2

Explanation:

The subsequence pairs which have the GCD of their elements equal to 10 are:

([10, 20, 30], [10, 20, 30])
([10, 20, 30], [10, 20, 30])
Example 3:

Input: nums = [1,1,1,1]

Output: 50

 

Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 200 */

//  Solution

class Solution {
    public int subsequencePairCount(int[] nums) {
        final int MOD = 1_000_000_007;
        final int MAX = 200;

        int[][] dp = new int[MAX + 1][MAX + 1];
        dp[0][0] = 1;

        for (int x : nums) {
            int[][] next = new int[MAX + 1][MAX + 1];

            for (int g1 = 0; g1 <= MAX; g1++) {
                for (int g2 = 0; g2 <= MAX; g2++) {
                    if (dp[g1][g2] == 0) continue;

                    long ways = dp[g1][g2];

                    next[g1][g2] = (int)((next[g1][g2] + ways) % MOD);

                    int ng1 = (g1 == 0) ? x : gcd(g1, x);
                    next[ng1][g2] = (int)((next[ng1][g2] + ways) % MOD);

                    int ng2 = (g2 == 0) ? x : gcd(g2, x);
                    next[g1][ng2] = (int)((next[g1][ng2] + ways) % MOD);
                }
            }

            dp = next;
        }

        long ans = 0;

        for (int g = 1; g <= MAX; g++) {
            ans = (ans + dp[g][g]) % MOD;
        }

        return (int)ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}