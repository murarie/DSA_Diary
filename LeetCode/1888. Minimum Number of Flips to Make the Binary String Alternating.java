/*
 */


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