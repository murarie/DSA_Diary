/*
1888. Minimum Number of Flips to Make the Binary String Alternating
Solved
Medium
Topics
premium lock icon
Companies
Hint
You are given a binary string s. You are allowed to perform two types of operations on the string in any sequence:

Type-1: Remove the character at the start of the string s and append it to the end of the string.
Type-2: Pick any character in s and flip its value, i.e., if its value is '0' it becomes '1' and vice-versa.
Return the minimum number of type-2 operations you need to perform such that s becomes alternating.

The string is called alternating if no two adjacent characters are equal.

For example, the strings "010" and "1010" are alternating, while the string "0100" is not.
 

Example 1:

Input: s = "111000"
Output: 2
Explanation: Use the first operation two times to make s = "100011".
Then, use the second operation on the third and sixth elements to make s = "101010".
Example 2:

Input: s = "010"
Output: 0
Explanation: The string is already alternating.
Example 3:

Input: s = "1110"
Output: 1
Explanation: Use the second operation on the second element to make s = "1010".
 

Constraints:

1 <= s.length <= 105
s[i] is either '0' or '1'. */


// Solution
class Solution {
    public int minFlips(String s) {
        int n  = s.length();
        int res = Integer.MAX_VALUE;
        int flip1 = 0;
        int flip2 = 0;

        int i = 0;
        int j = 0;

        while( j < 2 * n ) {
            
            char expCharS1 = ( j % 2 == 0 ) ? '0' : '1';
            char expCharS2 = ( j % 2 == 0 ) ? '1' : '0';

            if( s.charAt(j % n) != expCharS1 ) {
                flip1 += 1;
            }

            if( s.charAt(j % n) != expCharS2 ) {
                flip2 += 1;
            }

            if( j - i + 1 > n ) {

                expCharS1 = ( i % 2 == 0 ) ? '0' : '1';
                expCharS2 = ( i % 2 == 0 ) ? '1' : '0';

                if( s.charAt(i % n) != expCharS1 ) {
                    flip1 -= 1;
                }

                if( s.charAt(i % n) != expCharS2 ) {
                    flip2 -= 1;
                }

                i += 1;
            }

            if( j - i + 1 == n ) {
                res = Math.min( res, ( Math.min( flip1, flip2 ) ) );
            }

            j += 1;

        }

        return res;

    }
}