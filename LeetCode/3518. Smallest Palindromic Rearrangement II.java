/*
3518. Smallest Palindromic Rearrangement II
Solved
Hard
Topics
premium lock icon
Companies
Hint
You are given a palindromic string s and an integer k.

Return the k-th lexicographically smallest palindromic permutation of s. If there are fewer than k distinct palindromic permutations, return an empty string.

Note: Different rearrangements that yield the same palindromic string are considered identical and are counted once.

 

Example 1:

Input: s = "abba", k = 2

Output: "baab"

Explanation:

The two distinct palindromic rearrangements of "abba" are "abba" and "baab".
Lexicographically, "abba" comes before "baab". Since k = 2, the output is "baab".
Example 2:

Input: s = "aa", k = 2

Output: ""

Explanation:

There is only one palindromic rearrangement: "aa".
The output is an empty string since k = 2 exceeds the number of possible rearrangements.
Example 3:

Input: s = "bacab", k = 1

Output: "abcba"

Explanation:

The two distinct palindromic rearrangements of "bacab" are "abcba" and "bacab".
Lexicographically, "abcba" comes before "bacab". Since k = 1, the output is "abcba".
 

Constraints:

1 <= s.length <= 104
s consists of lowercase English letters.
s is guaranteed to be palindromic.
1 <= k <= 106 */

// Solution

class Solution {
    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        
        int[] half = new int[26];
        StringBuilder mid = new StringBuilder();
        int m = 0;
        
        for (int i = 0; i < 26; ++i) {
            if (freq[i] % 2 != 0) {
                mid.append((char) (i + 'a'));
            }
            half[i] = freq[i] / 2;
            m += half[i];
        }
        
        if (getWays(half, k) < k) {
            return "";
        }
        
        StringBuilder firstHalf = new StringBuilder();
        for (int i = 0; i < m; ++i) {
            for (int c = 0; c < 26; ++c) {
                if (half[c] > 0) {
                    half[c]--;
                    long ways = getWays(half, k);
                    
                    if (ways >= k) {
                        firstHalf.append((char) (c + 'a'));
                        break;
                    } else {
                        k -= ways;
                        half[c]++;
                    }
                }
            }
        }
        
        StringBuilder res = new StringBuilder(firstHalf);
        res.append(mid);
        res.append(firstHalf.reverse());
        return res.toString();
    }
    
    private long getWays(int[] f, long targetK) {
        long ways = 1;
        int currLen = 0;
        for (int count : f) {
            if (count > 0) {
                currLen += count;
                long n = currLen;
                long r = count;
                
                if (r > n - r) r = n - r;
                long curNCr = 1;
                
                for (int i = 1; i <= r; ++i) {
                    curNCr = curNCr * (n - i + 1) / i;
                    if (curNCr > targetK) {
                        curNCr = targetK + 1;
                        break;
                    }
                }
                ways *= curNCr;
                if (ways > targetK) return targetK + 1;
            }
        }
        return ways;
    }
}