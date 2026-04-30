/*
 */

// Solution

class Solution {
    int[][] grid;
    int n;
    int m;
    int k;
    int[][][] dp;
    public int maxPathScore(int[][] grid, int k) {
        this.grid=grid;
        this.n=grid.length;
        this.m=grid[0].length;
        this.k=k;
        dp=new int[n+1][m+1][k+1];
        for(int[][] i:dp){
            for(int[] j:i){
                Arrays.fill(j,-2);
            }
        }
        int ans=find(0, 0, 0);
        return ans==-1?-1:ans;
    }
    public int find(int i,int j,int c){
        if(i>=n || j>=m ) return -1;
        int n_c=c+(grid[i][j]==0?0:1);
        if(n_c>k) return -1;
        if(i==n-1 && j==m-1) return grid[i][j];
        
        if(dp[i][j][c]!=-2) return dp[i][j][c];
        
        int right=find(i,j+1,n_c);    
        int down=find(i+1,j,n_c);
        if (right==-1 && down ==-1) {
            return dp[i][j][n_c]=-1;
        }
        return dp[i][j][c]=grid[i][j]+Math.max(right,down);
    }
}