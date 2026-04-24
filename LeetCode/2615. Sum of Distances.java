/*
2615. Sum of Distances
Solved
Medium
Topics
premium lock icon
Companies
Hint
You are given a 0-indexed integer array nums. There exists an array arr of length nums.length, where arr[i] is the sum of |i - j| over all j such that nums[j] == nums[i] and j != i. If there is no such j, set arr[i] to be 0.

Return the array arr.

 

Example 1:

Input: nums = [1,3,1,1,2]
Output: [5,0,3,4,0]
Explanation: 
When i = 0, nums[0] == nums[2] and nums[0] == nums[3]. Therefore, arr[0] = |0 - 2| + |0 - 3| = 5. 
When i = 1, arr[1] = 0 because there is no other index with value 3.
When i = 2, nums[2] == nums[0] and nums[2] == nums[3]. Therefore, arr[2] = |2 - 0| + |2 - 3| = 3. 
When i = 3, nums[3] == nums[0] and nums[3] == nums[2]. Therefore, arr[3] = |3 - 0| + |3 - 2| = 4. 
When i = 4, arr[4] = 0 because there is no other index with value 2. 

Example 2:

Input: nums = [0,5,3]
Output: [0,0,0]
Explanation: Since each element in nums is distinct, arr[i] = 0 for all i.
 

Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 109
 

Note: This question is the same as 2121: Intervals Between Identical Elements. */

// Solution

import java.util.*;

class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] res = new long[n];

        HashMap<Integer, ArrayList<Integer>> mp = new HashMap<>();

        for (int i = 0; i < n; i++) {
            mp.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        for (ArrayList<Integer> arr : mp.values()) {
            int siz = arr.size();

            long[] pref = new long[siz];
            pref[0] = arr.get(0);

            for (int i = 1; i < siz; i++) {
                pref[i] = pref[i - 1] + arr.get(i);
            }

            for (int i = 0; i < siz; i++) {
                long left = 0, right = 0;

                if (i > 0) {
                    left = (long)i * arr.get(i) - pref[i - 1];
                }
                if (i < siz - 1) {
                    right = (pref[siz - 1] - pref[i]) - (long)(siz - i - 1) * arr.get(i);
                }

                res[arr.get(i)] = left + right;
            }
        }

        return res;
    }
}