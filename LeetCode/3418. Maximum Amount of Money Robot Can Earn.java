/*
 */

// Solution

class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length, n = coins[0].length;

        int[][][] dp = new int[m][n][3];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }

        dp[0][0][0] = coins[0][0];
        if (coins[0][0] < 0) {
            dp[0][0][1] = 0;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                for (int used = 0; used < 3; used++) {
                    int best = Integer.MIN_VALUE;
                    
                    if (i > 0) {
                        if (dp[i-1][j][used] != Integer.MIN_VALUE) {
                            int val = dp[i-1][j][used] + coins[i][j];
                            best = Math.max(best, val);
                        }
                        if (used > 0 && coins[i][j] < 0) {
                            if (dp[i-1][j][used-1] != Integer.MIN_VALUE) {
                                int val = dp[i-1][j][used-1] + 0;
                                best = Math.max(best, val);
                            }
                        }
                    }
                    
                    if (j > 0) {
                        if (dp[i][j-1][used] != Integer.MIN_VALUE) {
                            int val = dp[i][j-1][used] + coins[i][j];
                            best = Math.max(best, val);
                        }
                        if (used > 0 && coins[i][j] < 0) {
                            if (dp[i][j-1][used-1] != Integer.MIN_VALUE) {
                                int val = dp[i][j-1][used-1] + 0;
                                best = Math.max(best, val);
                            }
                        }
                    }
                    
                    dp[i][j][used] = best;
                }
            }
        }

        return Math.max(dp[m-1][n-1][0], Math.max(dp[m-1][n-1][1], dp[m-1][n-1][2]));
    }
}