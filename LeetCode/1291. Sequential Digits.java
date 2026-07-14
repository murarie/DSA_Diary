/*
1291. Sequential Digits
Solved
Medium
Topics
premium lock icon
Companies
Hint
An integer has sequential digits if and only if each digit in the number is one more than the previous digit.

Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.

 

Example 1:

Input: low = 100, high = 300
Output: [123,234]
Example 2:

Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]
 

Constraints:

10 <= low <= high <= 10^9 */

// Solution

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> ans = new ArrayList<>(); 

        String str = "123456789";
        for(int i = 0; i < 9; i++) {
            int right = i + 1; 
            while(right <= str.length()) {
                String s = str.substring(i, right); 
                int num = Integer.parseInt(s); 
                if(num >= low && num <= high) ans.add(num); 
                if(num > high) break; 
                right++; 
            }
        } 
        Collections.sort(ans); 
        return ans; 
    }
}