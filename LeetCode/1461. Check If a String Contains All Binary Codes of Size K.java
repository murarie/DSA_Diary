/*
1461. Check If a String Contains All Binary Codes of Size K
Solved
Medium
Topics
premium lock icon
Companies
Hint
Given a binary string s and an integer k, return true if every binary code of length k is a substring of s. Otherwise, return false.

 

Example 1:

Input: s = "00110110", k = 2
Output: true
Explanation: The binary codes of length 2 are "00", "01", "10" and "11". They can be all found as substrings at indices 0, 1, 3 and 2 respectively.
Example 2:

Input: s = "0110", k = 1
Output: true
Explanation: The binary codes of length 1 are "0" and "1", it is clear that both exist as a substring. 
Example 3:

Input: s = "0110", k = 2
Output: false
Explanation: The binary code "00" is of length 2 and does not exist in the array.
 

Constraints:

1 <= s.length <= 5 * 105
s[i] is either '0' or '1'.
1 <= k <= 20 */

// Solution

class Solution {
    class RollingHash {
        long hash, power;
        int len;

        RollingHash(String s, int len) {
            this.len = len;
            this.power = 1L << (len - 1);

            for (int i = 0; i < len; i++) {
                hash = (hash << 1) | (s.charAt(i) - '0');
            }
        }

        long next(char old_char, char new_char) {
            hash = ((hash - (old_char - '0') * power) << 1) | (new_char - '0');
            return hash;
        }
    }

    public boolean hasAllCodes(String s, int k) {
        int n = s.length();
        if (n - k + 1 < (1 << k)) return false;
        Set<Long> set = new HashSet<>();
        RollingHash rh = new RollingHash(s, k);
        set.add(rh.hash);

        for (int i = k; i <= n - 1; i++) {
            char old_symbol = s.charAt(i - k), new_symbol = s.charAt(i);
            long new_s = rh.next(old_symbol, new_symbol);
            set.add(new_s);
        }

        return set.size() == (1 << k);
    }
}