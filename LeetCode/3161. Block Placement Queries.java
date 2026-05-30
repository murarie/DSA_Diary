/*
3161. Block Placement Queries
Solved
Hard
Topics
premium lock icon
Companies
Hint
There exists an infinite number line, with its origin at 0 and extending towards the positive x-axis.

You are given a 2D array queries, which contains two types of queries:

For a query of type 1, queries[i] = [1, x]. Build an obstacle at distance x from the origin. It is guaranteed that there is no obstacle at distance x when the query is asked.
For a query of type 2, queries[i] = [2, x, sz]. Check if it is possible to place a block of size sz anywhere in the range [0, x] on the line, such that the block entirely lies in the range [0, x]. A block cannot be placed if it intersects with any obstacle, but it may touch it. Note that you do not actually place the block. Queries are separate.
Return a boolean array results, where results[i] is true if you can place the block specified in the ith query of type 2, and false otherwise.

 

Example 1:

Input: queries = [[1,2],[2,3,3],[2,3,1],[2,2,2]]

Output: [false,true,true]

Explanation:



For query 0, place an obstacle at x = 2. A block of size at most 2 can be placed before x = 3.

Example 2:

Input: queries = [[1,7],[2,7,6],[1,2],[2,7,5],[2,7,6]]

Output: [true,true,false]

Explanation:



Place an obstacle at x = 7 for query 0. A block of size at most 7 can be placed before x = 7.
Place an obstacle at x = 2 for query 2. Now, a block of size at most 5 can be placed before x = 7, and a block of size at most 2 before x = 2.
 

Constraints:

1 <= queries.length <= 15 * 104
2 <= queries[i].length <= 3
1 <= queries[i][0] <= 2
1 <= x, sz <= min(5 * 104, 3 * queries.length)
The input is generated such that for queries of type 1, no obstacle exists at distance x when the query is asked.
The input is generated such that there is at least one query of type 2.*/ 


// Solution

class Solution {
    class SegmentTree{
        int[]tree;
        int n;
        SegmentTree(int n){
            this.n=n;
            tree=new int[4*n+1];
        }
        public void insert(int idx,int val){
             insert(1,0,n-1, idx, val);
        }
        private void insert(int node,int left,int right,int idx,int val){
            if(left==right){
                tree[node]=val;
                return;
            }
            int mid=left+(right-left)/2;
             if(idx<=mid){
                insert(2*node,left,mid,idx,val);
             }
             else{
                insert(2*node+1,mid+1,right,idx,val);
             }
             tree[node]=Math.max(tree[2*node],tree[2*node+1]);
        }
        public int query(int l,int r){
            return query(1,0,n-1, l, r);

            
        }
        private int query(int node ,int start,int end,int l,int r){
            if(r<start||end<l){
                return Integer.MIN_VALUE;
            }
            if(l<=start&&end<=r){
                return tree[node];
            }
            int mid=start+(end-start)/2;
            int left=query(2*node,start,mid,l,r);
            int right=query(2*node+1,mid+1,end,l,r);
            return Math.max(left,right);
        }
    }
    public List<Boolean> getResults(int[][] queries) {
        SegmentTree sg=new SegmentTree(50001);
        TreeSet<Integer>set=new TreeSet<>();
         List<Boolean> ans = new ArrayList<>();
        set.add(0);
        set.add(50001);
        for(int[]q:queries){
            if(q[0]==1){
                int x=q[1];
                int left=set.floor(x-1);
                int right=set.ceiling(x+1);
                set.add(x);
                sg.insert(x,x-left);
                sg.insert(right,right-x);
            }
            else{
                int x=q[1];
                int size=q[2];
                int prev=set.floor(x);
                int maxgap=sg.query(0,prev);
                int max=Math.max(maxgap,x-prev);
                ans.add(size<=max);
            }
        }
        return ans;
    }
}