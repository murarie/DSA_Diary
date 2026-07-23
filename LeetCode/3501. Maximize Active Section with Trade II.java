/*
3501. Maximize Active Section with Trade II
Solved
Hard
Topics
premium lock icon
Companies
Hint
You are given a binary string s of length n, where:

'1' represents an active section.
'0' represents an inactive section.
You can perform at most one trade to maximize the number of active sections in s. In a trade, you:

Convert a contiguous block of '1's that is surrounded by '0's to all '0's.
Afterward, convert a contiguous block of '0's that is surrounded by '1's to all '1's.
Additionally, you are given a 2D array queries, where queries[i] = [li, ri] represents a substring s[li...ri].

For each query, determine the maximum possible number of active sections in s after making the optimal trade on the substring s[li...ri].

Return an array answer, where answer[i] is the result for queries[i].

Note

For each query, treat s[li...ri] as if it is augmented with a '1' at both ends, forming t = '1' + s[li...ri] + '1'. The augmented '1's do not contribute to the final count.
The queries are independent of each other.
 

Example 1:

Input: s = "01", queries = [[0,1]]

Output: [1]

Explanation:

Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 1.

Example 2:

Input: s = "0100", queries = [[0,3],[0,2],[1,3],[2,3]]

Output: [4,3,1,1]

Explanation:

Query [0, 3] → Substring "0100" → Augmented to "101001"
Choose "0100", convert "0100" → "0000" → "1111".
The final string without augmentation is "1111". The maximum number of active sections is 4.

Query [0, 2] → Substring "010" → Augmented to "10101"
Choose "010", convert "010" → "000" → "111".
The final string without augmentation is "1110". The maximum number of active sections is 3.

Query [1, 3] → Substring "100" → Augmented to "11001"
Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 1.

Query [2, 3] → Substring "00" → Augmented to "1001"
Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 1.

Example 3:

Input: s = "1000100", queries = [[1,5],[0,6],[0,4]]

Output: [6,7,2]

Explanation:

Query [1, 5] → Substring "00010" → Augmented to "1000101"
Choose "00010", convert "00010" → "00000" → "11111".
The final string without augmentation is "1111110". The maximum number of active sections is 6.

Query [0, 6] → Substring "1000100" → Augmented to "110001001"
Choose "000100", convert "000100" → "000000" → "111111".
The final string without augmentation is "1111111". The maximum number of active sections is 7.

Query [0, 4] → Substring "10001" → Augmented to "1100011"
Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 2.

Example 4:

Input: s = "01010", queries = [[0,3],[1,4],[1,3]]

Output: [4,4,2]

Explanation:

Query [0, 3] → Substring "0101" → Augmented to "101011"
Choose "010", convert "010" → "000" → "111".
The final string without augmentation is "11110". The maximum number of active sections is 4.

Query [1, 4] → Substring "1010" → Augmented to "110101"
Choose "010", convert "010" → "000" → "111".
The final string without augmentation is "01111". The maximum number of active sections is 4.

Query [1, 3] → Substring "101" → Augmented to "11011"
Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 2.

 

Constraints:

1 <= n == s.length <= 105
1 <= queries.length <= 105
s[i] is either '0' or '1'.
queries[i] = [li, ri]
0 <= li <= ri < n */

// Solution

class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int totalOnes = 0;
        
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                totalOnes++;
            }
        }
        
        List<Integer> typeList = new ArrayList<>();
        List<Integer> startList = new ArrayList<>();
        List<Integer> endList = new ArrayList<>();
        
        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }
            typeList.add(s.charAt(i) - '0');
            startList.add(i);
            endList.add(j - 1);
            i = j;
        }
        
        int N = typeList.size();
        int[] type = new int[N];
        int[] start = new int[N];
        int[] endIdx = new int[N];
        for (int i = 0; i < N; i++) {
            type[i] = typeList.get(i);
            start[i] = startList.get(i);
            endIdx[i] = endList.get(i);
        }
        
        int[] posToSeg = new int[n];
        for (int i = 0; i < N; i++) {
            for (int j = start[i]; j <= endIdx[i]; j++) {
                posToSeg[j] = i;
            }
        }
        
        int[] ans = new int[N];
        for (int i = 1; i < N - 1; i++) {
            if (type[i] == 1) {
                ans[i] = (endIdx[i - 1] - start[i - 1] + 1) + (endIdx[i + 1] - start[i + 1] + 1);
            }
        }
        
        int[] logTable = new int[N + 1];
        for (int i = 2; i <= N; i++) {
            logTable[i] = logTable[i / 2] + 1;
        }
        
        int K = logTable[N] + 1;
        int[][] st = new int[K][N];
        for (int i = 0; i < N; i++) {
            st[0][i] = ans[i];
        }
        
        for (int j = 1; j < K; j++) {
            for (int i = 0; i + (1 << j) <= N; i++) {
                st[j][i] = Math.max(st[j - 1][i], st[j - 1][i + (1 << (j - 1))]);
            }
        }
        
        List<Integer> res = new ArrayList<>();
        
        for (int[] q : queries) {
            int L = q[0];
            int R = q[1];
            
            int segL = posToSeg[L];
            int segR = posToSeg[R];
            
            if (segR - segL < 2) {
                res.add(totalOnes);
                continue;
            }
            
            int maxGain = 0;
            maxGain = Math.max(maxGain, evaluateEdge(segL + 1, L, R, segL, segR, type, start, endIdx));
            maxGain = Math.max(maxGain, evaluateEdge(segR - 1, L, R, segL, segR, type, start, endIdx));
            
            if (segL + 2 <= segR - 2) {
                int L_idx = segL + 2;
                int R_idx = segR - 2;
                int j = logTable[R_idx - L_idx + 1];
                int rmqVal = Math.max(st[j][L_idx], st[j][R_idx - (1 << j) + 1]);
                maxGain = Math.max(maxGain, rmqVal);
            }
            
            res.add(totalOnes + maxGain);
        }
        
        return res;
    }
    
    private int evaluateEdge(int i, int L, int R, int segL, int segR, int[] type, int[] start, int[] endIdx) {
        if (i <= segL || i >= segR) return 0;
        if (type[i] == 0) return 0;
        
        int leftLen = 0;
        if (i - 1 == segL) leftLen = Math.max(0, endIdx[i - 1] - L + 1);
        else leftLen = endIdx[i - 1] - start[i - 1] + 1;
        
        int rightLen = 0;
        if (i + 1 == segR) rightLen = Math.max(0, R - start[i + 1] + 1);
        else rightLen = endIdx[i + 1] - start[i + 1] + 1;
        
        return leftLen + rightLen;
    }
}