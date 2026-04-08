/*
16. 3Sum Closest
Solved
Medium
Topics
premium lock icon
Companies
Given an integer array nums of length n and an integer target, find three integers at distinct indices in nums such that the sum is closest to target.

Return the sum of the three integers.

You may assume that each input would have exactly one solution.

 

Example 1:

Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
Example 2:

Input: nums = [0,0,0], target = 1
Output: 0
Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).
 

Constraints:

3 <= nums.length <= 500
-1000 <= nums[i] <= 1000
-104 <= target <= 104*/

// Solution

class Solution {
public:
    int threeSumClosest(vector<int>& nums, int target) {
        sort(nums.begin(),nums.end());
        int n = nums.size();
        int minDiff = INT_MAX, ans = 0;
        for(int i = 0 ; i < n ; i++){
            if( i > 0 && nums[i] == nums[i-1]) continue;
            int left = i + 1, right = n - 1;
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == target){
                    return sum;
                }
                int currDiff = abs(sum - target);
                if(currDiff < minDiff){
                    ans = sum;
                    minDiff = currDiff;
                }
                else if(sum > target){
                     right--;
                }
                else{
                    left++;
                }
            }
        }
        return ans;
    }
};