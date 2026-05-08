/*
 */

// solution

class Solution {
    public boolean[] sieve(){
        int n=1000000;
        boolean[] sv=new boolean[n+1];
        sv[0]=true;
        sv[1]=true;
        for(int i=2;i*i<=n;i++){
            if(!sv[i]){
                for(int j=i*i;j<=n;j+=i){
                    sv[j]=true;
                }
            }
        }
        return sv;
    }
    public int minJumps(int[] nums) {
        if(nums.length==1) return 0;
        boolean[] sv=sieve();
        int n=nums.length;
        Map<Integer,List<Integer>> map=new HashMap<>();
        int max_elem=-1;
        for(int i=0;i<n;i++){
            map.putIfAbsent(nums[i],new ArrayList<>());
            map.get(nums[i]).add(i);
            max_elem=Math.max(max_elem,nums[i]);
        }
        int dist[]=new int[n];
        Arrays.fill(dist,-1);
        Queue<Integer> q=new LinkedList<>();
        Set<Integer> set=new HashSet<>();
        q.add(0);
        dist[0]=0;
        while(!q.isEmpty()){
            int r=q.poll();
            set.add(r);
            List<Integer> nextJump=new ArrayList<>();
            if(r-1>=0) nextJump.add(r-1);
            if(r+1<n) nextJump.add(r+1);
            if(!sv[nums[r]]){
                int curr=nums[r];
                for(int i=curr;i<=max_elem;i+=curr){
                    if(map.containsKey(i)){
                        for(int v:map.get(i)) nextJump.add(v);
                        map.remove(i);
                    }
                }
            }
            for(int next:nextJump){
                if(dist[next]==-1){
                    dist[next]=dist[r]+1;
                    q.add(next);
                }
                if(next==n-1) return dist[next];
            }
        }
        return -1;
    }
}