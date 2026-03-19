/*
3212. Count Submatrices With Equal Frequency of X and Y
Solved
Medium
Topics
premium lock icon
Companies
Hint
Given a 2D character matrix grid, where grid[i][j] is either 'X', 'Y', or '.', return the number of submatrices that contain:

grid[0][0]
an equal frequency of 'X' and 'Y'.
at least one 'X'.
 

Example 1:

Input: grid = [["X","Y","."],["Y",".","."]]

Output: 3

Explanation:



Example 2:

Input: grid = [["X","X"],["X","Y"]]

Output: 0

Explanation:

No submatrix has an equal frequency of 'X' and 'Y'.

Example 3:

Input: grid = [[".","."],[".","."]]

Output: 0

Explanation:

No submatrix has at least one 'X'.

 

Constraints:

1 <= grid.length, grid[i].length <= 1000
grid[i][j] is either 'X', 'Y', or '.'. */

// Solution

class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] diff = new int[m+1][n+1];
        boolean[][] has_x = new boolean[m+1][n+1]; 
        
        int count = 0;
        
        for (int i = 1; i <= m; i++) {  
            for (int j = 1; j <= n; j++) { 
                int val = 0;
                if (grid[i-1][j-1] == 'X') {
                    val = 1;
                } else if (grid[i-1][j-1] == 'Y') {
                    val = -1;
                }
                
                diff[i][j] = diff[i-1][j] + diff[i][j-1] - diff[i-1][j-1] + val;
                
                has_x[i][j] = has_x[i-1][j] || has_x[i][j-1] 
                           || has_x[i-1][j-1] || (grid[i-1][j-1] == 'X');
                
                if (has_x[i][j] && diff[i][j] == 0) count++;
            }
        }
        
        return count;
    }
}