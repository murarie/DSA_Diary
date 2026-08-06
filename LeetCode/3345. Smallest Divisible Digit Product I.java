/*
 */

// Solution

class Solution {

    private int digitProduct(int n) {

        int prod = 1;

        while (n != 0) {
            prod *= (n % 10);
            n /= 10;
        }

        return prod;
    }

    public int smallestNumber(int n, int t) {

        for (int i = 0; i < 11; i++) {

            int prod = digitProduct(n + i);

            if (prod % t == 0) {
                return n + i;
            }
        }

        return 0;
    }
}