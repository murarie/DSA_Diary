/*
 */

// solution

class Solution {
    public int findMin(int[] nums) {
        int n= Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            if(nums[i]<n){
                n=nums[i];
            }
        }
        return n;
    }
}