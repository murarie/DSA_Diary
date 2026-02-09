/*
 1382. Balance a Binary Search Tree
Medium
Topics
premium lock icon
Companies
Hint
Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.

A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.

 

Example 1:


Input: root = [1,null,2,null,3,null,4,null,null]
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.
Example 2:


Input: root = [2,1,3]
Output: [2,1,3]
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
1 <= Node.val <= 105 */

// Solution

class Solution {
    List<Integer> arr = new ArrayList<>();

    void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        arr.add(root.val);
        inorder(root.right);
    }

    TreeNode build(int left, int right) {
        if (left > right) return null;

        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(arr.get(mid));

        node.left = build(left, mid - 1);
        node.right = build(mid + 1, right);

        return node;
    }

    public TreeNode balanceBST(TreeNode root) {
        inorder(root);
        return build(0, arr.size() - 1);
    }
}