/*
1081. Smallest Subsequence of Distinct Characters
Solved
Medium
Topics
premium lock icon
Companies
Hint
Given a string s, return the lexicographically smallest subsequence of s that contains all the distinct characters of s exactly once.

 

Example 1:

Input: s = "bcabc"
Output: "abc"
Example 2:

Input: s = "cbacdcbc"
Output: "acdb"
 

Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters. */


// Solution

class Solution {
    public String smallestSubsequence(String s) {
        int[] lastIndex = new int[26];

        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        boolean[] seen = new boolean[26];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);
            int curr = ch - 'a';

            if (seen[curr])
                continue;

            while (sb.length() > 0 &&
                   sb.charAt(sb.length() - 1) > ch &&
                   lastIndex[sb.charAt(sb.length() - 1) - 'a'] > i) {

                seen[sb.charAt(sb.length() - 1) - 'a'] = false;
                sb.deleteCharAt(sb.length() - 1);
            }

            sb.append(ch);
            seen[curr] = true;
        }

        return sb.toString();
    }
}