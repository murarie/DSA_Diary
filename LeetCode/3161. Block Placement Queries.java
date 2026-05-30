/*
*/ 


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