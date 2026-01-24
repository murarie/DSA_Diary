/*
3510. Minimum Pair Removal to Sort Array II
Solved
Hard
Topics
premium lock icon
Companies
Hint
Given an array nums, you can perform the following operation any number of times:

Select the adjacent pair with the minimum sum in nums. If multiple such pairs exist, choose the leftmost one.
Replace the pair with their sum.
Return the minimum number of operations needed to make the array non-decreasing.

An array is said to be non-decreasing if each element is greater than or equal to its previous element (if it exists).

 

Example 1:

Input: nums = [5,2,3,1]

Output: 2

Explanation:

The pair (3,1) has the minimum sum of 4. After replacement, nums = [5,2,4].
The pair (2,4) has the minimum sum of 6. After replacement, nums = [5,6].
The array nums became non-decreasing in two operations.

Example 2:

Input: nums = [1,2,2]

Output: 0

Explanation:

The array nums is already sorted.

 

Constraints:

1 <= nums.length <= 105
-109 <= nums[i] <= 109 */

// solution

class Solution {
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = nums[i];

        int sorted = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
            int c = Long.compare(a[0], b[0]);
            return c != 0 ? c : Long.compare(a[1], b[1]);
        });

        for (int i = 0; i < n - 1; i++) {
            if (arr[i + 1] >= arr[i]) sorted++;
            pq.offer(new long[]{arr[i] + arr[i + 1], i});
        }

        if (sorted == n - 1) return 0;

        int[] prev = new int[n], next = new int[n];
        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }

        int remaining = n;
        boolean[] removed = new boolean[n];

        while (remaining > 1) {
            long[] cur = pq.poll();
            if (cur == null) break;
            long sum = cur[0];
            int left = (int) cur[1];
            int right = next[left];

            if (right == n || removed[left] || removed[right] || arr[left] + arr[right] != sum)
                continue;

            int pre = prev[left];
            int nxt = next[right];

            if (arr[left] <= arr[right]) sorted--;
            if (pre != -1 && arr[pre] <= arr[left]) sorted--;
            if (nxt != n && arr[right] <= arr[nxt]) sorted--;

            arr[left] += arr[right];
            removed[right] = true;
            remaining--;

            if (pre == -1) {
                prev[left] = -1;
            } else {
                pq.offer(new long[]{arr[pre] + arr[left], pre});
                if (arr[pre] <= arr[left]) sorted++;
            }

            if (nxt == n) {
                next[left] = n;
            } else {
                prev[nxt] = left;
                next[left] = nxt;
                pq.offer(new long[]{arr[left] + arr[nxt], left});
                if (arr[left] <= arr[nxt]) sorted++;
            }

            if (sorted == remaining - 1) return n - remaining;
        }

        return n;
    }
}