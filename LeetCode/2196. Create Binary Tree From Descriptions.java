/*
 */


//solution

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        HashSet<Integer> h= new HashSet<>();
        HashMap<Integer,TreeNode> map= new HashMap<>();
        for(int [] i: descriptions){
            int c=i[1];
            int p=i[0];
            int l=i[2];
            h.add(c);
            map.putIfAbsent(p,new TreeNode(p));
            map.putIfAbsent(c,new TreeNode(c));
            TreeNode pa= map.get(p);
            TreeNode ch= map.get(c);
            if(l==1) pa.left=ch;
            else pa.right=ch;
        }
        for(int [] i: descriptions){
            if(!h.contains(i[0])){
                return map.get(i[0]);
            }
        }
        return null;
    }
}