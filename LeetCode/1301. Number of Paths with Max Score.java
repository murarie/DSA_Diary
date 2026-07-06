/*
1301. Number of Paths with Max Score
Solved
Hard
Topics
premium lock icon
Companies
Hint
You are given a square board of characters. You can move on the board starting at the bottom right square marked with the character 'S'.

You need to reach the top left square marked with the character 'E'. The rest of the squares are labeled either with a numeric character 1, 2, ..., 9 or with an obstacle 'X'. In one move you can go up, left or up-left (diagonally) only if there is no obstacle there.

Return a list of two integers: the first integer is the maximum sum of numeric characters you can collect, and the second is the number of such paths that you can take to get that maximum sum, taken modulo 10^9 + 7.

In case there is no path, return [0, 0].

 

Example 1:

Input: board = ["E23","2X2","12S"]
Output: [7,1]
Example 2:

Input: board = ["E12","1X1","21S"]
Output: [4,2]
Example 3:

Input: board = ["E11","XXX","11S"]
Output: [0,0]
 

Constraints:

2 <= board.length == board[i].length <= 100 */

// Solution
class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        final int MOD = 1_000_000_007;
        int n = board.size();

        int[] nextScore = new int[n + 1];
        int[] nextWays = new int[n + 1];

        Arrays.fill(nextScore, -1);

        for (int i = n - 1; i >= 0; i--) {
            int[] currScore = new int[n + 1];
            int[] currWays = new int[n + 1];

            Arrays.fill(currScore, -1);

            for (int j = n - 1; j >= 0; j--) {
                char cell = board.get(i).charAt(j);

                if (cell == 'X') {
                    continue;
                }

                if (cell == 'S') {
                    currScore[j] = 0;
                    currWays[j] = 1;
                    continue;
                }

                int best = Math.max(
                    nextScore[j],
                    Math.max(currScore[j + 1], nextScore[j + 1])
                );

                if (best == -1) {
                    continue;
                }

                long ways = 0;

                if (nextScore[j] == best) {
                    ways += nextWays[j];
                }
                if (currScore[j + 1] == best) {
                    ways += currWays[j + 1];
                }
                if (nextScore[j + 1] == best) {
                    ways += nextWays[j + 1];
                }

                int value = (cell == 'E') ? 0 : cell - '0';

                currScore[j] = best + value;
                currWays[j] = (int) (ways % MOD);
            }

            nextScore = currScore;
            nextWays = currWays;
        }

        if (nextScore[0] == -1) {
            return new int[]{0, 0};
        }

        return new int[]{nextScore[0], nextWays[0]};
    }
}
