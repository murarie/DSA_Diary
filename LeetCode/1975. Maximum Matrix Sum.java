/*
Medium
Topics
premium lock icon
Companies
Hint
You are given an n x n integer matrix. You can do the following operation any number of times:

Choose any two adjacent elements of matrix and multiply each of them by -1.
Two elements are considered adjacent if and only if they share a border.

Your goal is to maximize the summation of the matrix's elements. Return the maximum sum of the matrix's elements using the operation mentioned above.

 

Example 1:


Input: matrix = [[1,-1],[-1,1]]
Output: 4
Explanation: We can follow the following steps to reach sum equals 4:
- Multiply the 2 elements in the first row by -1.
- Multiply the 2 elements in the first column by -1.
Example 2:


Input: matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
Output: 16
Explanation: We can follow the following step to reach sum equals 16:
- Multiply the 2 last elements in the second row by -1.
 

Constraints:

n == matrix.length == matrix[i].length
2 <= n <= 250
-105 <= matrix[i][j] <= 105 */

//solution

class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long sum = 0; 
        int negative_count = 0;
        int min_abs = Integer.MAX_VALUE; 

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int num = matrix[i][j];
                sum += Math.abs((long) num); 
                if (num < 0) {
                    negative_count++;
                }
                min_abs = Math.min(min_abs, Math.abs(num));
            }
        }

        if (negative_count % 2 == 0) {
            return sum;
        } else {
            return sum - 2 * min_abs;
        }
    }
}
