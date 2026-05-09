/*
3629. Minimum Jumps to Reach End via Prime Teleportation
Solved
Medium
Topics
premium lock icon
Companies
Hint
You are given an integer array nums of length n.

You start at index 0, and your goal is to reach index n - 1.

From any index i, you may perform one of the following operations:

Adjacent Step: Jump to index i + 1 or i - 1, if the index is within bounds.
Prime Teleportation: If nums[i] is a prime number p, you may instantly jump to any index j != i such that nums[j] % p == 0.
Return the minimum number of jumps required to reach index n - 1.

 

Example 1:

Input: nums = [1,2,4,6]

Output: 2

Explanation:

One optimal sequence of jumps is:

Start at index i = 0. Take an adjacent step to index 1.
At index i = 1, nums[1] = 2 is a prime number. Therefore, we teleport to index i = 3 as nums[3] = 6 is divisible by 2.
Thus, the answer is 2.

Example 2:

Input: nums = [2,3,4,7,9]

Output: 2

Explanation:

One optimal sequence of jumps is:

Start at index i = 0. Take an adjacent step to index i = 1.
At index i = 1, nums[1] = 3 is a prime number. Therefore, we teleport to index i = 4 since nums[4] = 9 is divisible by 3.
Thus, the answer is 2.

Example 3:

Input: nums = [4,6,5,8]

Output: 3

Explanation:

Since no teleportation is possible, we move through 0 → 1 → 2 → 3. Thus, the answer is 3.
 

Constraints:

1 <= n == nums.length <= 105
1 <= nums[i] <= 106 */

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