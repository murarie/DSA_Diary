/*
3546. Equal Sum Grid Partition I
Solved
Medium
Topics
premium lock icon
Companies
Hint
You are given an m x n matrix grid of positive integers. Your task is to determine if it is possible to make either one horizontal or one vertical cut on the grid such that:

Each of the two resulting sections formed by the cut is non-empty.
The sum of the elements in both sections is equal.
Return true if such a partition exists; otherwise return false.

 

Example 1:

Input: grid = [[1,4],[2,3]]

Output: true

Explanation:



A horizontal cut between row 0 and row 1 results in two non-empty sections, each with a sum of 5. Thus, the answer is true.

Example 2:

Input: grid = [[1,3],[2,4]]

Output: false

Explanation:

No horizontal or vertical cut results in two non-empty sections with equal sums. Thus, the answer is false.

 

Constraints:

1 <= m == grid.length <= 105
1 <= n == grid[i].length <= 105
2 <= m * n <= 105
1 <= grid[i][j] <= 105 */

// Solution

class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        long[] row_sum = new long[m], col_sum = new long[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                row_sum[i] += val; col_sum[j] += val;
            }
        }

        long[] row_prefix = new long[m + 1];
        for (int i = 0; i < m; i++) {
            row_prefix[i + 1] = row_prefix[i] + row_sum[i];
        }

        long[] col_prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            col_prefix[i + 1] = col_prefix[i] + col_sum[i];
        }

        long total_sum = row_prefix[m];

        if (total_sum % 2 != 0) {
            return false;
        }

        long target = total_sum / 2;

        for (int i = 1; i < m; i++) {
            if (row_prefix[i] == target) {
                return true;
            }
        }

        for (int i = 1; i < n; i++) {
            if (col_prefix[i] == target) {
                return true;
            }
        }
        
        return false;
    }
}