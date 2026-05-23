/*
 */

// solution

class Solution {
    public boolean check(int[] nums) {
        int min = Integer.MAX_VALUE;

        int n = nums.length;

        for(int num : nums) {
            if(num < min) {
                min = num;
            }
        }

        for(int i = 0; i < n; i++) {
            if(nums[i] == min) {
                int idx = i;
                boolean flag = true;

                int count = 0;

                while(count < n - 1) {
                    if(nums[idx % n] >nums[(idx + 1) % n]) {
                        flag = false;
                        break;
                    }

                    count++;
                    idx++;
                }

                if(flag) {
                    return true;
                }
            }
        }

        return false;
    }
}