/*
 */

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