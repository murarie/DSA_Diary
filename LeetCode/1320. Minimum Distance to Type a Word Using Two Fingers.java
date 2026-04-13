/*
1320. Minimum Distance to Type a Word Using Two Fingers
Solved
Hard
Topics
premium lock icon
Companies
Hint

You have a keyboard layout as shown above in the X-Y plane, where each English uppercase letter is located at some coordinate.

For example, the letter 'A' is located at coordinate (0, 0), the letter 'B' is located at coordinate (0, 1), the letter 'P' is located at coordinate (2, 3) and the letter 'Z' is located at coordinate (4, 1).
Given the string word, return the minimum total distance to type such string using only two fingers.

The distance between coordinates (x1, y1) and (x2, y2) is |x1 - x2| + |y1 - y2|.

Note that the initial positions of your two fingers are considered free so do not count towards your total distance, also your two fingers do not have to start at the first letter or the first two letters.

 

Example 1:

Input: word = "CAKE"
Output: 3
Explanation: Using two fingers, one optimal way to type "CAKE" is: 
Finger 1 on letter 'C' -> cost = 0 
Finger 1 on letter 'A' -> cost = Distance from letter 'C' to letter 'A' = 2 
Finger 2 on letter 'K' -> cost = 0 
Finger 2 on letter 'E' -> cost = Distance from letter 'K' to letter 'E' = 1 
Total distance = 3
Example 2:

Input: word = "HAPPY"
Output: 6
Explanation: Using two fingers, one optimal way to type "HAPPY" is:
Finger 1 on letter 'H' -> cost = 0
Finger 1 on letter 'A' -> cost = Distance from letter 'H' to letter 'A' = 2
Finger 2 on letter 'P' -> cost = 0
Finger 2 on letter 'P' -> cost = Distance from letter 'P' to letter 'P' = 0
Finger 1 on letter 'Y' -> cost = Distance from letter 'A' to letter 'Y' = 4
Total distance = 6
 

Constraints:

2 <= word.length <= 300
word consists of uppercase English letters. */

// Solution

class Solution {
    public int minimumDistance(String word) {
        int n = word.length();
        if (n <= 2) return 0;
        
        int[] dp = new int[27];
        for (int i = 0; i < 27; i++) dp[i] = Integer.MAX_VALUE;
        
        char[] w = word.toCharArray();
        dp[26] = dist(w[1], w[0]);
        dp[w[0] - 'A'] = 0;
        
        for (int i = 2; i < n; i++) {
            int delta = dist(w[i], w[i - 1]);
            int best = dp[26];
            for (int j = 0; j < 27; j++) {
                if (dp[j] < best) {
                    int cost = (j == 26 ? 0 : dist(w[i], (char)(j + 'A')));
                    best = Math.min(best, dp[j] + cost);
                }
                if (dp[j] < Integer.MAX_VALUE) dp[j] += delta;
            }
            dp[w[i - 1] - 'A'] = Math.min(dp[w[i - 1] - 'A'], best);
        }
        
        int res = Integer.MAX_VALUE;
        for (int v : dp) res = Math.min(res, v);

        return res;
    }
    
    private int dist(char a, char b) {
        int x1 = (a - 'A') / 6, y1 = (a - 'A') % 6;
        int x2 = (b - 'A') / 6, y2 = (b - 'A') % 6;

        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
