/*
 */

// solution

class Solution {
    public int minJumps(int[] arr) {

        int n = arr.length;

        if (n == 1) return 0;

        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        Queue<Integer> queue = new LinkedList<>();

        boolean[] visited = new boolean[n];

        queue.offer(0);
        visited[0] = true;

        int steps = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            while (size-- > 0) {

                int idx = queue.poll();

                if (idx == n - 1) {
                    return steps;
                }

                if (idx - 1 >= 0 && !visited[idx - 1]) {
                    visited[idx - 1] = true;
                    queue.offer(idx - 1);
                }

                if (idx + 1 < n && !visited[idx + 1]) {
                    visited[idx + 1] = true;
                    queue.offer(idx + 1);
                }

                for (int nextIdx : map.get(arr[idx])) {

                    if (!visited[nextIdx]) {
                        visited[nextIdx] = true;
                        queue.offer(nextIdx);
                    }
                }

                map.get(arr[idx]).clear();
            }

            steps++;
        }

        return -1;
    }
}