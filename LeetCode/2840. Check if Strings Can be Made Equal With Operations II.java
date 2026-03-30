/*
2840. Check if Strings Can be Made Equal With Operations II
Solved
Medium
Topics
premium lock icon
Companies
Hint
You are given two strings s1 and s2, both of length n, consisting of lowercase English letters.

You can apply the following operation on any of the two strings any number of times:

Choose any two indices i and j such that i < j and the difference j - i is even, then swap the two characters at those indices in the string.
Return true if you can make the strings s1 and s2 equal, and false otherwise.

 

Example 1:

Input: s1 = "abcdba", s2 = "cabdab"
Output: true
Explanation: We can apply the following operations on s1:
- Choose the indices i = 0, j = 2. The resulting string is s1 = "cbadba".
- Choose the indices i = 2, j = 4. The resulting string is s1 = "cbbdaa".
- Choose the indices i = 1, j = 5. The resulting string is s1 = "cabdab" = s2.
Example 2:

Input: s1 = "abe", s2 = "bea"
Output: false
Explanation: It is not possible to make the two strings equal.
 

Constraints:

n == s1.length == s2.length
1 <= n <= 105
s1 and s2 consist only of lowercase English letters. */

// Solution

class Solution {
    private static final int ENG_CHARS_NUM = 'z' - 'a' + 1;
    private static final int EVEN = 0, ODD = 1;

    public boolean checkStrings(String s1, String s2) {

        int[] evenFreqDiff = computeFreqDiff(s1, s2, EVEN);

        if (!hasOnlyZeros(evenFreqDiff)) return false;

        int[] oddFreqDiff = computeFreqDiff(s1, s2, ODD);

        return hasOnlyZeros(oddFreqDiff);
    }

    private boolean hasOnlyZeros(int[] arr) {
        for (int elem : arr) {
            if (elem != 0) return false;
        }
        return true;
    }

    private int[] computeFreqDiff(String s1, String s2, int start) {
        int[] freq = new int[ENG_CHARS_NUM];
        for (int i = start; i < s1.length(); i += 2) {
            freq[s1.charAt(i) - 'a']++;
            freq[s2.charAt(i) - 'a']--;
        }
        return freq;
    }
}