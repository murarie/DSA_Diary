/*
3600. Maximize Spanning Tree Stability with Upgrades
Solved
Hard
Topics
premium lock icon
Companies
Hint
You are given an integer n, representing n nodes numbered from 0 to n - 1 and a list of edges, where edges[i] = [ui, vi, si, musti]:

ui and vi indicates an undirected edge between nodes ui and vi.
si is the strength of the edge.
musti is an integer (0 or 1). If musti == 1, the edge must be included in the spanning tree. These edges cannot be upgraded.
You are also given an integer k, the maximum number of upgrades you can perform. Each upgrade doubles the strength of an edge, and each eligible edge (with musti == 0) can be upgraded at most once.

The stability of a spanning tree is defined as the minimum strength score among all edges included in it.

Return the maximum possible stability of any valid spanning tree. If it is impossible to connect all nodes, return -1.

Note: A spanning tree of a graph with n nodes is a subset of the edges that connects all nodes together (i.e. the graph is connected) without forming any cycles, and uses exactly n - 1 edges.

 

Example 1:

Input: n = 3, edges = [[0,1,2,1],[1,2,3,0]], k = 1

Output: 2

Explanation:

Edge [0,1] with strength = 2 must be included in the spanning tree.
Edge [1,2] is optional and can be upgraded from 3 to 6 using one upgrade.
The resulting spanning tree includes these two edges with strengths 2 and 6.
The minimum strength in the spanning tree is 2, which is the maximum possible stability.
Example 2:

Input: n = 3, edges = [[0,1,4,0],[1,2,3,0],[0,2,1,0]], k = 2

Output: 6

Explanation:

Since all edges are optional and up to k = 2 upgrades are allowed.
Upgrade edges [0,1] from 4 to 8 and [1,2] from 3 to 6.
The resulting spanning tree includes these two edges with strengths 8 and 6.
The minimum strength in the tree is 6, which is the maximum possible stability.
Example 3:

Input: n = 3, edges = [[0,1,1,1],[1,2,1,1],[2,0,1,1]], k = 0

Output: -1

Explanation:

All edges are mandatory and form a cycle, which violates the spanning tree property of acyclicity. Thus, the answer is -1.
 

Constraints:

2 <= n <= 105
1 <= edges.length <= 105
edges[i] = [ui, vi, si, musti]
0 <= ui, vi < n
ui != vi
1 <= si <= 105
musti is either 0 or 1.
0 <= k <= n
There are no duplicate edges.
 

Seen this question in a real interview before?
1/5
Yes
No
Accepted
41,084/65K
Acceptance Rate
63.2%
Topics
icon
Companies
Hint 1
Hint 2
Hint 3
Hint 4
Hint 5
Discussion (104) */

// Solution

class Solution {
    class Edge {
        int u, v, strength;
        boolean must;
        
        Edge(int u, int v, int strength, boolean must) {
            this.u = u;
            this.v = v;
            this.strength = strength;
            this.must = must;
        }
    }
    
    class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];  
        }

        void union(int x, int y) {
            int root_x = find(x), root_y = find(y);
            
            if (root_x == root_y) return;

            if (rank[root_x] < rank[root_y]) {
                parent[root_x] = root_y;
            } else if (rank[root_x] > rank[root_y]) {
                parent[root_y] = root_x;
            } else {
                parent[root_y] = root_x;
                rank[root_x]++;
            }
        }
    }

    public int maxStability(int n, int[][] edges, int k) {
        List<Edge> mandatory = new ArrayList<>();
        List<Edge> optional = new ArrayList<>();
        
        for (int[] e : edges) {	
            Edge edge = new Edge(e[0], e[1], e[2], e[3] == 1);
            if (edge.must) {
                mandatory.add(edge);
            } else {
                optional.add(edge);
            }
        }
    
        DSU dsu = new DSU(n);
        
        for (Edge e : mandatory) {
            if (dsu.find(e.u) == dsu.find(e.v)) {
                return -1;
            }
            dsu.union(e.u, e.v);
        }
        
        int[] parent_snap = new int[n];
        int[] rank_snap = new int[n];
        for (int i = 0; i < n; i++) {
            parent_snap[i] = dsu.parent[i];
            rank_snap[i] = dsu.rank[i];
        }
        
        int left = 1;
        int right = 0;
        for (int[] e : edges) {
            right = Math.max(right, e[2] * 2);
        }

        int answer = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (canAchieve(n, mandatory, optional, k, mid, parent_snap, rank_snap)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private boolean canAchieve(int n, List<Edge> mandatory, List<Edge> optional, 
                               int k, int minStability, int[] parent_snap, int[] rank_snap) {
        DSU dsu = new DSU(n);
        dsu.parent = parent_snap.clone();
        dsu.rank = rank_snap.clone();

        for (Edge e : mandatory) {
            if (e.strength < minStability) {
                return false;
            }
        }

        List<Edge> goodWithUpgrade = new ArrayList<>();
        List<Edge> goodWithoutUpgrade = new ArrayList<>();

        for (Edge e : optional) {
            if (e.strength >= minStability) {
                goodWithoutUpgrade.add(e);
            } else if (e.strength * 2 >= minStability) {
                goodWithUpgrade.add(e);
            }
        }

        for (Edge e : goodWithoutUpgrade) {
            if (dsu.find(e.u) != dsu.find(e.v)) {
                dsu.union(e.u, e.v);
            }
        }
        
        int used = 0;
        for (Edge e : goodWithUpgrade) {
            if (used >= k) break;
            
            if (dsu.find(e.u) != dsu.find(e.v)) {
                dsu.union(e.u, e.v);
                used++;
            }
        }
        
        int root = dsu.find(0);
        for (int i = 1; i < n; i++) {
            if (dsu.find(i) != root) return false;
        }
        
        return true;
    }
}