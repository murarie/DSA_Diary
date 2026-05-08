/*
3660. Jump Game IX
Solved
Medium
Topics
premium lock icon
Companies
Hint
You are given an integer array nums.

From any index i, you can jump to another index j under the following rules:

Jump to index j where j > i is allowed only if nums[j] < nums[i].
Jump to index j where j < i is allowed only if nums[j] > nums[i].
For each index i, find the maximum value in nums that can be reached by following any sequence of valid jumps starting at i.

Return an array ans where ans[i] is the maximum value reachable starting from index i.

 

Example 1:

Input: nums = [2,1,3]

Output: [2,2,3]

Explanation:

For i = 0: No jump increases the value.
For i = 1: Jump to j = 0 as nums[j] = 2 is greater than nums[i].
For i = 2: Since nums[2] = 3 is the maximum value in nums, no jump increases the value.
Thus, ans = [2, 2, 3].

Example 2:

Input: nums = [2,3,1]

Output: [3,3,3]

Explanation:

For i = 0: Jump forward to j = 2 as nums[j] = 1 is less than nums[i] = 2, then from i = 2 jump to j = 1 as nums[j] = 3 is greater than nums[2].
For i = 1: Since nums[1] = 3 is the maximum value in nums, no jump increases the value.
For i = 2: Jump to j = 1 as nums[j] = 3 is greater than nums[2] = 1.
Thus, ans = [3, 3, 3].

 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109 */

// Solution

class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;

        int[] suffixMin = new int[n + 1];
        suffixMin[n] = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }

        int[] ans = new int[n];
        int l = 0;

        while (l < n) {
            int r = l;
            int componentMax = nums[l];

            while (r + 1 < n && componentMax > suffixMin[r + 1]) {
                r++;
                componentMax = Math.max(componentMax, nums[r]);
            }

            for (int i = l; i <= r; i++) {
                ans[i] = componentMax;
            }

            l = r + 1;
        }

        return ans;
    }
}