/*
 */

// Solution

class Solution {
    public String mapWordWeights(String[] words, int[] weights){
        int n = weights.length;
        StringBuilder res = new StringBuilder("");
        for(String str : words){
            int sum = 0;
            for(char ch : str.toCharArray()){
                sum += weights[ch - 'a'];
            }
            res.append((char)(n - sum % 26 - 1 + 'a'));
        }
        return res.toString();
    }
}