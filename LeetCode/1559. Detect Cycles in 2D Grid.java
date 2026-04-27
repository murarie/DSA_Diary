/*
1559. Detect Cycles in 2D Grid
Solved
Medium
Topics
premium lock icon
Companies
Hint
Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.

A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.

Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.

Return true if any cycle of the same value exists in grid, otherwise, return false.

 

Example 1:



Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
Output: true
Explanation: There are two valid cycles shown in different colors in the image below:

Example 2:



Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
Output: true
Explanation: There is only one valid cycle highlighted in the image below:

Example 3:



Input: grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
Output: false
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 500
grid consists only of lowercase English letters. */

// Solution

class Solution {
    private final int[] dir = {-1, 0, 1, 0, -1};
    private boolean cycle(char[][] g, char unvisited, char visited, int i, int j, int prev_i, int prev_j) {
        g[i][j] = visited;
        for (int d = 0; d < 4; d++) {
            int x = i + dir[d], y = j + dir[d + 1];
            if (x >= 0 && x < g.length && y >= 0 && y < g[0].length && !(x == prev_i && y == prev_j)) {
                if (g[x][y] == visited) return true;
                if (g[x][y] == unvisited && cycle(g, unvisited, visited, x, y, i, j)) return true;
            }
        }
        return false;
    }
    public boolean containsCycle(char[][] g) {
        for (int i = 0; i < g.length; i++)
            for (int j = 0; j < g[0].length; j++)
                if (g[i][j] >= 'a' && g[i][j] <= 'z')
                    if (cycle(g, g[i][j], (char) (g[i][j] - 'a' + 'A'), i, j, -1, -1)) return true;
        return false;
    }
}