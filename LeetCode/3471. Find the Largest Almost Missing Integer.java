/*
 */

// Solution

class Solution {
      
    public int largestInteger(int[] nums, int k) {
        int[] cnt = new int[51];
        for (int i = 0; i <= nums.length - k; i++) {
            boolean[] b = new boolean[51];
            for (int j = i; j < i + k; j++) {
                if (!b[nums[j]]) {
                    cnt[nums[j]] += 1;
                    b[nums[j]] = true;
                }
            }
        }
        for (int i = cnt.length - 1; i >= 0; i--)
            if (cnt[i] == 1)
                return i;
        return -1;

    }
}