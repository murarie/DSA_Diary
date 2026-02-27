/*
 */

// Solution

class Solution {
    public int minOperations(String s, int k) {
        int n = s.length(), zeroes = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') zeroes++;
        }

        int one = n - zeroes;
        int m = (zeroes + k - 1) / k;

        while (m <= n) {
            long total = (long)m * k;

            if ((total - zeroes) % 2 != 0) {
                m++;
                continue;
            }

            int max_zero = 0, max_one = 0;
            if (m % 2 == 1) {
                max_zero = m; 
                max_one = m - 1;   
            } else {
                max_zero = m - 1;   
                max_one = m;  
            }

            long L = Math.max(zeroes, total - (long)one * max_one);
            long R = Math.min((long)zeroes * max_zero, total);

            if (L <= R) {
                if (L % 2 == zeroes % 2 || L + 1 <= R) {
                    return m;
                }
            }

            m++;
        }

        return -1;
    }
}