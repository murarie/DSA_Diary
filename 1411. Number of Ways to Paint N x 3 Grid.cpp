/*
Hard
Topics
premium lock icon
Companies
Hint
You have a grid of size n x 3 and you want to paint each cell of the grid with exactly one of the three colors: Red, Yellow, or Green while making sure that no two adjacent cells have the same color (i.e., no two cells that share vertical or horizontal sides have the same color).

Given n the number of rows of the grid, return the number of ways you can paint this grid. As the answer may grow large, the answer must be computed modulo 109 + 7.

 

Example 1:


Input: n = 1
Output: 12
Explanation: There are 12 possible way to paint the grid as shown.
Example 2:

Input: n = 5000
Output: 30228214
 

Constraints:

n == grid.length
1 <= n <= 5000 */

class Solution {
public:
    const int mod = 1e9 + 7;
    int numOfWays(int n) {
        vector<long long int> a(n,6),b(n,6);
        int tot = 0;
        for(int i=1;i<n;i++){
            a[i] = (2*a[i-1] + 2*b[i-1]) % mod;
            b[i] = (2*a[i-1] + 3*b[i-1]) % mod;
        }
        return (a[n-1] + b[n-1]) % mod;
    }
};
