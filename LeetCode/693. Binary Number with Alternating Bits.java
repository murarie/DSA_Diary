/*
 */

// Solution

class Solution {
    public boolean hasAlternatingBits(int n) {
        int curr = n & 1;
        while (n > 0) {
            if (curr == (n & 1)) curr = 1 - curr;
            else return false;
            n = n >> 1;
        }
        return true;
    }
}