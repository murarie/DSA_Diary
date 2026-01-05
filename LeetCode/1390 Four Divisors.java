/*
Medium
Topics
premium lock icon
Companies
Hint
Given an integer array nums, return the sum of divisors of the integers in that array that have exactly four divisors. If there is no such integer in the array, return 0.

 

Example 1:

Input: nums = [21,4,7]
Output: 32
Explanation: 
21 has 4 divisors: 1, 3, 7, 21
4 has 3 divisors: 1, 2, 4
7 has 2 divisors: 1, 7
The answer is the sum of divisors of 21 only.
Example 2:

Input: nums = [21,21]
Output: 64
Example 3:

Input: nums = [1,2,3,4,5]
Output: 0
 

Constraints:

1 <= nums.length <= 104
1 <= nums[i] <= 105 */


//solution

class Solution {
    public int sumFourDivisors(int[] nums) {
        int total_sum = 0;
        
        for (int num : nums) {
            int sum = sum_if_four(num);
            total_sum += sum;
        }
        
        return total_sum;
    }

    private int sum_if_four(int n) {
        int d1 = 0, d2 = 0;

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                if (d1 == 0) {
                    d1 = i;
                    d2 = n / i;
                } else {
                    return 0;
                }
            }
        }

        if (d1 == 0) return 0;
        if (d1 == d2) return 0;

        return 1 + n + d1 + d2;
    }
}