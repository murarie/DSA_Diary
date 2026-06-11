/*
3691. Maximum Total Subarray Value II
Solved
Hard
Topics
premium lock icon
Companies
Hint
You are given an integer array nums of length n and an integer k.

You must select exactly k distinct subarrays nums[l..r] of nums. Subarrays may overlap, but the exact same subarray (same l and r) cannot be chosen more than once.

The value of a subarray nums[l..r] is defined as: max(nums[l..r]) - min(nums[l..r]).

The total value is the sum of the values of all chosen subarrays.

Return the maximum possible total value you can achieve.

 

Example 1:

Input: nums = [1,3,2], k = 2

Output: 4

Explanation:

One optimal approach is:

Choose nums[0..1] = [1, 3]. The maximum is 3 and the minimum is 1, giving a value of 3 - 1 = 2.
Choose nums[0..2] = [1, 3, 2]. The maximum is still 3 and the minimum is still 1, so the value is also 3 - 1 = 2.
Adding these gives 2 + 2 = 4.

Example 2:

Input: nums = [4,2,5,1], k = 3

Output: 12

Explanation:

One optimal approach is:

Choose nums[0..3] = [4, 2, 5, 1]. The maximum is 5 and the minimum is 1, giving a value of 5 - 1 = 4.
Choose nums[1..3] = [2, 5, 1]. The maximum is 5 and the minimum is 1, so the value is also 4.
Choose nums[2..3] = [5, 1]. The maximum is 5 and the minimum is 1, so the value is again 4.
Adding these gives 4 + 4 + 4 = 12.

 

Constraints:

1 <= n == nums.length <= 5 * 10​​​​​​​4
0 <= nums[i] <= 109
1 <= k <= min(105, n * (n + 1) / 2)*/

// Solution

class Solution {
    static class Node {
        long val;
        int l;
        int r;

        Node(long val, int l, int r) {
            this.val = val;
            this.l = l;
            this.r = r;
        }
    }

    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;

        int[] lg = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            lg[i] = lg[i / 2] + 1;
        }

        int K = lg[n] + 1;

        int[][] mx = new int[K][n];
        int[][] mn = new int[K][n];

        for (int i = 0; i < n; i++) {
            mx[0][i] = nums[i];
            mn[0][i] = nums[i];
        }

        for (int j = 1; j < K; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                mx[j][i] = Math.max(
                    mx[j - 1][i],
                    mx[j - 1][i + (1 << (j - 1))]
                );

                mn[j][i] = Math.min(
                    mn[j - 1][i],
                    mn[j - 1][i + (1 << (j - 1))]
                );
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(b.val, a.val)
        );

        for (int l = 0; l < n; l++) {
            pq.offer(new Node(getValue(l, n - 1, mx, mn, lg), l, n - 1));
        }

        long ans = 0;

        while (k-- > 0) {
            Node cur = pq.poll();

            ans += cur.val;

            if (cur.r > cur.l) {
                pq.offer(new Node(
                    getValue(cur.l, cur.r - 1, mx, mn, lg),
                    cur.l,
                    cur.r - 1
                ));
            }
        }

        return ans;
    }

    private long getValue(
        int l,
        int r,
        int[][] mx,
        int[][] mn,
        int[] lg
    ) {
        int len = r - l + 1;
        int p = lg[len];

        int mxVal = Math.max(
            mx[p][l],
            mx[p][r - (1 << p) + 1]
        );

        int mnVal = Math.min(
            mn[p][l],
            mn[p][r - (1 << p) + 1]
        );

        return (long) mxVal - mnVal;
    }
}