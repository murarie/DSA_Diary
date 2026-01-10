/*
712. Minimum ASCII Delete Sum for Two Strings
Solved
Medium
Topics
premium lock icon
Companies
Hint
Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two strings equal.

 

Example 1:

Input: s1 = "sea", s2 = "eat"
Output: 231
Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
Deleting "t" from "eat" adds 116 to the sum.
At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
Example 2:

Input: s1 = "delete", s2 = "leet"
Output: 403
Explanation: Deleting "dee" from "delete" to turn the string into "let",
adds 100[d] + 101[e] + 101[e] to the sum.
Deleting "e" from "leet" adds 101[e] to the sum.
At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
 

Constraints:

1 <= s1.length, s2.length <= 1000
s1 and s2 consist of lowercase English letters. */

// solution

class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int total_sum = 0, n1 = s1.length(), n2 = s2.length();
        for (int i = 0; i < n1; i++) total_sum += s1.charAt(i);
        for (int i = 0; i < n2; i++) total_sum += s2.charAt(i);
        
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    int ascii_value = (int)s1.charAt(i - 1);
                    dp[i][j] = dp[i - 1][j - 1] + ascii_value;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return total_sum - 2 * dp[n1][n2];
    }
}