/*
3548. Equal Sum Grid Partition II
Solved
Hard
Topics
premium lock icon
Companies
Hint
You are given an m x n matrix grid of positive integers. Your task is to determine if it is possible to make either one horizontal or one vertical cut on the grid such that:

Each of the two resulting sections formed by the cut is non-empty.
The sum of elements in both sections is equal, or can be made equal by discounting at most one single cell in total (from either section).
If a cell is discounted, the rest of the section must remain connected.
Return true if such a partition exists; otherwise, return false.

Note: A section is connected if every cell in it can be reached from any other cell by moving up, down, left, or right through other cells in the section.

 

Example 1:

Input: grid = [[1,4],[2,3]]

Output: true

Explanation:



A horizontal cut after the first row gives sums 1 + 4 = 5 and 2 + 3 = 5, which are equal. Thus, the answer is true.
Example 2:

Input: grid = [[1,2],[3,4]]

Output: true

Explanation:



A vertical cut after the first column gives sums 1 + 3 = 4 and 2 + 4 = 6.
By discounting 2 from the right section (6 - 2 = 4), both sections have equal sums and remain connected. Thus, the answer is true.
Example 3:

Input: grid = [[1,2,4],[2,3,5]]

Output: false

Explanation:



A horizontal cut after the first row gives 1 + 2 + 4 = 7 and 2 + 3 + 5 = 10.
By discounting 3 from the bottom section (10 - 3 = 7), both sections have equal sums, but they do not remain connected as it splits the bottom section into two parts ([2] and [5]). Thus, the answer is false.
Example 4:

Input: grid = [[4,1,8],[3,2,6]]

Output: false

Explanation:

No valid cut exists, so the answer is false.

 

Constraints:

1 <= m == grid.length <= 105
1 <= n == grid[i].length <= 105
2 <= m * n <= 105
1 <= grid[i][j] <= 105 */

// Solution

import java.util.*;

class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int n = grid.length;
        int col = grid[0].length;

        long total = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<col;j++){
                total += grid[i][j];
            }
        }

        Map<Long,Integer> topFreq = new HashMap<>();
        Map<Long,Integer> bottomFreq = new HashMap<>();
        Map<Long,Integer> leftFreq = new HashMap<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<col;j++){
                long v = grid[i][j];
                bottomFreq.put(v, bottomFreq.getOrDefault(v,0)+1);
            }
        }

        long topSum = 0;

        for(int i=0;i<n-1;i++){
            for(int j=0;j<col;j++){
                long v = grid[i][j];
                topSum += v;

                topFreq.put(v, topFreq.getOrDefault(v,0)+1);

                bottomFreq.put(v, bottomFreq.get(v)-1);
                if(bottomFreq.get(v)==0) bottomFreq.remove(v);
            }

            long bottomSum = total - topSum;

            if(topSum == bottomSum) return true;

            long removetop = 2*topSum - total;
            if(check(removetop,0,i,0,col-1,topFreq,grid)) return true;

            long removebot = total - 2*topSum;
            if(check(removebot,i+1,n-1,0,col-1,bottomFreq,grid)) return true;
        }

        bottomFreq.clear();
        for(int i=0;i<n;i++){
            for(int j=0;j<col;j++){
                long v = grid[i][j];
                bottomFreq.put(v, bottomFreq.getOrDefault(v,0)+1);
            }
        }

        long leftSum = 0;

        for(int j=0;j<col-1;j++){
            for(int i=0;i<n;i++){
                long v = grid[i][j];
                leftSum += v;

                leftFreq.put(v, leftFreq.getOrDefault(v,0)+1);

                bottomFreq.put(v, bottomFreq.get(v)-1);
                if(bottomFreq.get(v)==0) bottomFreq.remove(v);
            }

            long rightSum = total - leftSum;

            if(leftSum == rightSum) return true;

            long removeleft = 2*leftSum - total;
            if(check(removeleft,0,n-1,0,j,leftFreq,grid)) return true;

            long removeright = total - 2*leftSum;
            if(check(removeright,0,n-1,j+1,col-1,bottomFreq,grid)) return true;
        }

        return false;
    }

    private boolean check(long val,int r1,int r2,int c1,int c2,Map<Long,Integer> mp,int[][] grid){
        if(val<=0 || !mp.containsKey(val)) return false;

        int h = r2-r1+1;
        int w = c2-c1+1;

        if(h==1 && w>1) return grid[r1][c1]==val || grid[r1][c2]==val;
        if(w==1 && h>1) return grid[r1][c1]==val || grid[r2][c1]==val;
        if(h==1 && w==1) return grid[r1][c1]==val;

        return true;
    }
}