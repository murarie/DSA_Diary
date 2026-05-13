/*
 */

// Solution

class Solution {
    public int minMoves(int[] nums, int limit) {

        int n = nums.length;

        int[] diff = new int[2 * limit + 2];

        for(int i = 0; i < n / 2; i++) {

            int a = nums[i];
            int b = nums[n - i - 1];

            if(a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            int sum = a + b;

            int left = a + 1;
            int right = b + limit;

            diff[2] += 2;
            diff[2 * limit + 1] -= 2;

            diff[left] -= 1;
            diff[right + 1] += 1;

            diff[sum] -= 1;
            diff[sum + 1] += 1;
        }

        int res = Integer.MAX_VALUE;
        int curr = 0;

        for(int s = 2; s <= 2 * limit; s++) {
            curr += diff[s];
            res = Math.min(res, curr);
        }

        return res;
    }
}