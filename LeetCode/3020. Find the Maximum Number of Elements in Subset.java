/*
 */

// Solution

class Solution {
    public int maximumLength(int[] nums) {
        HashMap<Long, Integer> freq = new HashMap<>();

        for (int x : nums) {
            freq.put((long) x, freq.getOrDefault((long) x, 0) + 1);
        }

        int ans = 1;

        if (freq.containsKey(1L)) {
            int cnt = freq.get(1L);

            ans = Math.max(ans, (cnt % 2 == 1) ? cnt : cnt - 1);
        }

        for (long start : freq.keySet()) {
            if (start == 1L) continue;

            long cur = start;
            int len = 0;

            while (freq.containsKey(cur)) {
                if (freq.get(cur) >= 2) {
                    len += 2;

                    cur = cur * cur;
                } else {
                    len++;
                    break;
                }
            }

            if ((len & 1) == 0) len--;

            ans = Math.max(ans, len);
        }

        return ans;
    }
}