/*
 */

// Solution

class Solution {
    public boolean checkOnesSegment(String s) {
        int n = s.length(), i=0;
        boolean flg = false;
        while (i<n && s.charAt(i)=='1') i++;
        while (i<n && s.charAt(i)=='0') i++; 
        return i==n;
    }
}