/*
1886. Determine Whether Matrix Can Be Obtained By Rotation
Solved
Easy
Topics
premium lock icon
Companies
Hint
Given two n x n binary matrices mat and target, return true if it is possible to make mat equal to target by rotating mat in 90-degree increments, or false otherwise.

 

Example 1:


Input: mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
Output: true
Explanation: We can rotate mat 90 degrees clockwise to make mat equal target.
Example 2:


Input: mat = [[0,1],[1,1]], target = [[1,0],[0,1]]
Output: false
Explanation: It is impossible to make mat equal to target by rotating mat.
Example 3:


Input: mat = [[0,0,0],[0,1,0],[1,1,1]], target = [[1,1,1],[0,1,0],[0,0,0]]
Output: true
Explanation: We can rotate mat 90 degrees clockwise two times to make mat equal target.
 

Constraints:

n == mat.length == target.length
n == mat[i].length == target[i].length
1 <= n <= 10
mat[i][j] and target[i][j] are either 0 or 1. */

// Solution

class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;

        for (int rot = 0; rot < 4; rot++) {
            if (is_same(mat, target)) {
                return true;
            }
            mat = rotate90(mat);
        }

        return false;
    }

    private boolean is_same(int[][] a, int[][] b) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] != b[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private int[][] rotate90(int[][] mat) {
        int n = mat.length;
        int[][] rotated = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][n - 1 - i] = mat[i][j];
            }
        }

        return rotated;
    }
}