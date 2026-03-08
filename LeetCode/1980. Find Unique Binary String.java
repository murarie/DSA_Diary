/*
 */

// Solution

class Solution {
    public String findDifferentBinaryString(String[] nums) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            char c = nums[i].charAt(i);
            sb.append(c == '1' ? "0" : "1");
        }
        return sb.toString();
    }
}