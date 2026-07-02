/*
2812. Find the Safest Path in a Grid
Solved
Medium
Topics
premium lock icon
Companies
Hint
You are given a 0-indexed 2D matrix grid of size n x n, where (r, c) represents:

A cell containing a thief if grid[r][c] = 1
An empty cell if grid[r][c] = 0
You are initially positioned at cell (0, 0). In one move, you can move to any adjacent cell in the grid, including cells containing thieves.

The safeness factor of a path on the grid is defined as the minimum manhattan distance from any cell in the path to any thief in the grid.

Return the maximum safeness factor of all paths leading to cell (n - 1, n - 1).

An adjacent cell of cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) and (r - 1, c) if it exists.

The Manhattan distance between two cells (a, b) and (x, y) is equal to |a - x| + |b - y|, where |val| denotes the absolute value of val.

 

Example 1:


Input: grid = [[1,0,0],[0,0,0],[0,0,1]]
Output: 0
Explanation: All paths from (0, 0) to (n - 1, n - 1) go through the thieves in cells (0, 0) and (n - 1, n - 1).
Example 2:


Input: grid = [[0,0,1],[0,0,0],[0,0,0]]
Output: 2
Explanation: The path depicted in the picture above has a safeness factor of 2 since:
- The closest cell of the path to the thief at cell (0, 2) is cell (0, 0). The distance between them is | 0 - 0 | + | 0 - 2 | = 2.
It can be shown that there are no other paths with a higher safeness factor.
Example 3:


Input: grid = [[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]
Output: 2
Explanation: The path depicted in the picture above has a safeness factor of 2 since:
- The closest cell of the path to the thief at cell (0, 3) is cell (1, 2). The distance between them is | 0 - 1 | + | 3 - 2 | = 2.
- The closest cell of the path to the thief at cell (3, 0) is cell (3, 2). The distance between them is | 3 - 3 | + | 0 - 2 | = 2.
It can be shown that there are no other paths with a higher safeness factor.
 

Constraints:

1 <= grid.length == n <= 400
grid[i].length == n
grid[i][j] is either 0 or 1.
There is at least one thief in the grid. */

// Solution

class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {

        int n = grid.size();

        int[][] dist = new int[n][n];
        for(int[] row : dist)
            Arrays.fill(row, -1);

        Queue<int[]> queue = new LinkedList<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid.get(i).get(j)==1){
                    dist[i][j]=0;
                    queue.offer(new int[]{i,j});
                }
            }
        }

        int[] dx={-1,0,1,0};
        int[] dy={0,1,0,-1};

        while(!queue.isEmpty()){
            int[] cur=queue.poll();

            for(int k=0;k<4;k++){
                int nx=cur[0]+dx[k];
                int ny=cur[1]+dy[k];

                if(nx<0||ny<0||nx>=n||ny>=n||dist[nx][ny]!=-1)
                    continue;

                dist[nx][ny]=dist[cur[0]][cur[1]]+1;
                queue.offer(new int[]{nx,ny});
            }
        }

        int left=0;
        int right=2*n;
        int ans=0;

        while(left<=right){

            int mid=left+(right-left)/2;

            if(canReach(dist, mid, n)){
                ans=mid;
                left=mid+1;
            }else{
                right=mid-1;
            }
        }

        return ans;
    }

    private boolean canReach(int[][] dist,int limit,int n){

        if(dist[0][0]<limit || dist[n-1][n-1]<limit)
            return false;

        Queue<int[]> queue=new LinkedList<>();
        boolean[][] vis=new boolean[n][n];

        queue.offer(new int[]{0,0});
        vis[0][0]=true;

        int[] dx={-1,0,1,0};
        int[] dy={0,1,0,-1};

        while(!queue.isEmpty()){

            int[] cur=queue.poll();

            if(cur[0]==n-1 && cur[1]==n-1)
                return true;

            for(int k=0;k<4;k++){

                int nx=cur[0]+dx[k];
                int ny=cur[1]+dy[k];

                if(nx<0||ny<0||nx>=n||ny>=n)
                    continue;

                if(vis[nx][ny] || dist[nx][ny]<limit)
                    continue;

                vis[nx][ny]=true;
                queue.offer(new int[]{nx,ny});
            }
        }

        return false;
    }
}