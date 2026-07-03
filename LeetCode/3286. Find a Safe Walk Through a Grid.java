/*
3286. Find a Safe Walk Through a Grid
Solved
Medium
Topics
premium lock icon
Companies
Hint
You are given an m x n binary matrix grid and an integer health.

You start on the upper-left corner (0, 0) and would like to get to the lower-right corner (m - 1, n - 1).

You can move up, down, left, or right from one cell to another adjacent cell as long as your health remains positive.

Cells (i, j) with grid[i][j] = 1 are considered unsafe and reduce your health by 1.

Return true if you can reach the final cell with a health value of 1 or more, and false otherwise.

 

Example 1:

Input: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]], health = 1

Output: true

Explanation:

The final cell can be reached safely by walking along the gray cells below.


Example 2:

Input: grid = [[0,1,1,0,0,0],[1,0,1,0,0,0],[0,1,1,1,0,1],[0,0,1,0,1,0]], health = 3

Output: false

Explanation:

A minimum of 4 health points is needed to reach the final cell safely.


Example 3:

Input: grid = [[1,1,1],[1,0,1],[1,1,1]], health = 5

Output: true

Explanation:

The final cell can be reached safely by walking along the gray cells below.



Any path that does not go through the cell (1, 1) is unsafe since your health will drop to 0 when reaching the final cell.

 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
2 <= m * n
1 <= health <= m + n
grid[i][j] is either 0 or 1. */

// Solution


class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            java.util.Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        java.util.ArrayDeque<int[]> dq = new java.util.ArrayDeque<>();

        dist[0][0] = grid.get(0).get(0);
        dq.offerFirst(new int[]{0, 0});

        int[] dir = {-1, 0, 1, 0, -1};

        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            int x = cur[0];
            int y = cur[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k];
                int ny = y + dir[k + 1];

                if (nx < 0 || ny < 0 || nx >= m || ny >= n)
                    continue;

                int newCost = dist[x][y] + grid.get(nx).get(ny);

                if (newCost < dist[nx][ny]) {
                    dist[nx][ny] = newCost;

                    if (grid.get(nx).get(ny) == 0)
                        dq.offerFirst(new int[]{nx, ny});
                    else
                        dq.offerLast(new int[]{nx, ny});
                }
            }
        }

        return dist[m - 1][n - 1] < health;
    }
}