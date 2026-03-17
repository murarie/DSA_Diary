/*
1727. Largest Submatrix With Rearrangements
Solved
Medium
Topics
premium lock icon
Companies
Hint
You are given a binary matrix matrix of size m x n, and you are allowed to rearrange the columns of the matrix in any order.

Return the area of the largest submatrix within matrix where every element of the submatrix is 1 after reordering the columns optimally.

 

Example 1:


Input: matrix = [[0,0,1],[1,1,1],[1,0,1]]
Output: 4
Explanation: You can rearrange the columns as shown above.
The largest submatrix of 1s, in bold, has an area of 4.
Example 2:


Input: matrix = [[1,0,1,0,1]]
Output: 3
Explanation: You can rearrange the columns as shown above.
The largest submatrix of 1s, in bold, has an area of 3.
Example 3:

Input: matrix = [[1,1,0],[1,0,1]]
Output: 2
Explanation: Notice that you must rearrange entire columns, and there is no way to make a submatrix of 1s larger than an area of 2.
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m * n <= 105
matrix[i][j] is either 0 or 1.
  */

// Solution

class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int max_area = 0;
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (matrix[i][j] == 1 && i > 0) {
        			matrix[i][j] = matrix[i - 1][j] + 1;
        		}
        	} 
        	
        	int[] heights = matrix[i].clone();
        	
        	Arrays.sort(heights);
        	
        	for (int j = n - 1; j >= 0; j--) {
        		if (heights[j] == 0) break;
        		
        		int width = n - j;
        		int height = heights[j];
        		
        		max_area = Math.max(max_area, width * height);
        	}
        }
        
       return max_area;
    }
}