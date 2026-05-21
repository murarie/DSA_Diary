/*
 */

// Solution

class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = arr1.length;
        int m = arr2.length;
        for(int i = 0 ; i < n ; i++){
            int val = arr1[i];
            while(val > 0){
                map.put(val, 1);
                val /= 10;
            }
        }
        int max = 0;
        for(int i = 0 ; i < m ; i++){
            int val = arr2[i];
            while(val > 0){
                if(map.containsKey(val)){
                    max = Math.max(max, val);
                }
                val /= 10;
            }
        }
        int count = 0;
        while(max > 0){
            max /= 10;
            count++;
        }
        return count;
    }
}