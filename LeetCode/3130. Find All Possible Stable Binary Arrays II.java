/*
3130. Find All Possible Stable Binary Arrays II
Solved
Hard
Topics
premium lock icon
Companies
Hint
You are given 3 positive integers zero, one, and limit.

A binary array arr is called stable if:

The number of occurrences of 0 in arr is exactly zero.
The number of occurrences of 1 in arr is exactly one.
Each subarray of arr with a size greater than limit must contain both 0 and 1.
Return the total number of stable binary arrays.

Since the answer may be very large, return it modulo 109 + 7.

 

Example 1:

Input: zero = 1, one = 1, limit = 2

Output: 2

Explanation:

The two possible stable binary arrays are [1,0] and [0,1].

Example 2:

Input: zero = 1, one = 2, limit = 1

Output: 1

Explanation:

The only possible stable binary array is [1,0,1].

Example 3:

Input: zero = 3, one = 3, limit = 2

Output: 14

Explanation:

All the possible stable binary arrays are [0,0,1,0,1,1], [0,0,1,1,0,1], [0,1,0,0,1,1], [0,1,0,1,0,1], [0,1,0,1,1,0], [0,1,1,0,0,1], [0,1,1,0,1,0], [1,0,0,1,0,1], [1,0,0,1,1,0], [1,0,1,0,0,1], [1,0,1,0,1,0], [1,0,1,1,0,0], [1,1,0,0,1,0], and [1,1,0,1,0,0].

 

Constraints:

1 <= zero, one, limit <= 1000 */

// Solution

class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        int MOD = (int)1e9 + 7;
        
        int[][] dp0 = new int[one + 1][zero + 1];
        int[][] dp1 = new int[one + 1][zero + 1];
        
        for (int i = 1; i <= Math.min(one, limit); i++) {
            dp1[i][0] = 1;
        }
        
        for (int j = 1; j <= Math.min(zero, limit); j++) {
            dp0[0][j] = 1;
        }
        
        for (int o = 1; o <= one; o++) {
            for (int z = 1; z <= zero; z++) {
                if (o == 0 && z == 0) continue;

                dp0[o][z] = dp1[o][z - 1];
                dp0[o][z] = (dp0[o][z] + dp0[o][z - 1]) % MOD;
                if (z - limit - 1 >= 0) {
                    dp0[o][z] = (dp0[o][z] - dp1[o][z - limit - 1] + MOD) % MOD;
                }

                dp1[o][z] = dp0[o - 1][z]; 
                dp1[o][z] = (dp1[o][z] + dp1[o - 1][z]) % MOD; 
                if (o - limit - 1 >= 0) {
                    dp1[o][z] = (dp1[o][z] - dp0[o - limit - 1][z] + MOD) % MOD;
                }
            }
        }
        
        return (dp0[one][zero] + dp1[one][zero]) % MOD;
    }
}