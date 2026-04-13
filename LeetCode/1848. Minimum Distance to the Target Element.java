/*
 */

// Solution

class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int n = nums.length;

        for (int dist = 0; dist < n; dist++) {

            int left = start - dist;
            if (left >= 0 && nums[left] == target) {
                return dist;
            }

            int right = start + dist;
            if (right < n && nums[right] == target) {
                return dist;
            }
        }

        return -1;
    }
}