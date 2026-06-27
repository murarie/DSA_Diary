/*
3739. Count Subarrays With Majority Element II
Solved
Hard
Topics
premium lock icon
Companies
Hint
You are given an integer array nums and an integer target.

Return the number of subarrays of nums in which target is the majority element.

The majority element of a subarray is the element that appears strictly more than half of the times in that subarray.

 

Example 1:

Input: nums = [1,2,2,3], target = 2

Output: 5

Explanation:

Valid subarrays with target = 2 as the majority element:

nums[1..1] = [2]
nums[2..2] = [2]
nums[1..2] = [2,2]
nums[0..2] = [1,2,2]
nums[1..3] = [2,2,3]
So there are 5 such subarrays.

Example 2:

Input: nums = [1,1,1,1], target = 1

Output: 10

Explanation:

​​​​​​​All 10 subarrays have 1 as the majority element.

Example 3:

Input: nums = [1,2,3], target = 4

Output: 0

Explanation:

target = 4 does not appear in nums at all. Therefore, there cannot be any subarray where 4 is the majority element. Hence the answer is 0.

 

Constraints:

1 <= nums.length <= 10​​​​​​​5
1 <= nums[i] <= 10​​​​​​​9
1 <= target <= 109 */

// Solution

class Solution {

    static class Fenwick {
        int[] bit;

        Fenwick(int n) {
            bit = new int[n + 1];
        }

        void update(int idx) {
            while (idx < bit.length) {
                bit[idx]++;
                idx += idx & -idx;
            }
        }

        long query(int idx) {
            long sum = 0;
            while (idx > 0) {
                sum += bit[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {

        int n = nums.length;

        int[] pref = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + (nums[i] == target ? 1 : -1);
        }

        int[] values = pref.clone();
        java.util.Arrays.sort(values);

        int m = 0;
        for (int x : values) {
            if (m == 0 || values[m - 1] != x) {
                values[m++] = x;
            }
        }

        Fenwick ft = new Fenwick(m);

        long ans = 0;

        for (int x : pref) {

            int idx = java.util.Arrays.binarySearch(values, 0, m, x) + 1;

            ans += ft.query(idx - 1);

            ft.update(idx);
        }

        return ans;
    }
}