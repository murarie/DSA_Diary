/*
 */

// Solution

class Solution {
    public int largestAltitude(int[] gain) {
        int n = gain.length;
        int[] alt = new int[n + 1];

        for (int i = 0; i < n; i++) 
        {
            alt[i + 1] = alt[i] + gain[i];
        }

        int max = 0;
        for (int x : alt) 
        {
            max = Math.max(max, x);
        }

        return max;
    }
}