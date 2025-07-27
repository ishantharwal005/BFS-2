// Time Complexity : O(n) where n is number of nodes in tree
// Space Complexity : O(n) for BFS and O(h) for DFS
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Had to revise basic syntax

// ## Problem 1: Binary Tree Right Side View (https://leetcode.com/problems/binary-tree-right-side-view/)
// Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
// Example 1:
// Input: root = [1,2,3,null,5,null,4]
// Output: [1,3,4]

// Example 2:
// Input: root = [1,2,3,4,null,null,null,5]
// Output: [1,3,4,5]

// Example 3:
// Input: root = [1,null,3]
// Output: [1,3]

// Example 4:
// Input: root = []
// Output: []

// Constraints:
// The number of nodes in the tree is in the range [0, 100].
// -100 <= Node.val <= 100

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

// BFS Approach:
// Time Complexity: O(n) | Space Complexity: O(n)
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        // Edge case: if the tree is empty, returning empty list
        if (root == null){
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>(); // Final result list
        Queue<TreeNode> q = new LinkedList<>(); // Queue for level-order traversal
        q.add(root); // Starting BFS with root node

        while (!q.isEmpty()){
            int size = q.size(); // Number of nodes at the current level
            for (int i = 0; i < size; i++){
                TreeNode curr = q.poll(); // Remove front node from queue

                // Adding left and right children to the queue
                if (curr.left != null){
                    q.add(curr.left);
                }
                if (curr.right != null){
                    q.add(curr.right);
                }

                // Adding last node of this level to the result list
                if (i == size - 1){
                    result.add(curr.val); // Rightmost node at this level
                }
            }
        }
        return result;
    }
}

// DFS Approach
// Time Complexity: O(n) | Space Complexity: O(h)
class Solution {
    List<Integer> result; // stores the rightmost nodes at each level

    public List<Integer> rightSideView(TreeNode root) {
        // Edge case: if the tree is empty, returning empty list
        if (root == null){
            return new ArrayList<>();
        }
        result = new ArrayList<>();

        dfs(root, 0); // Starting DFS from root at level 0
        return result;
    }

    // DFS traversal prioritizing right children
    private void dfs(TreeNode root, int level){
        // Base case: null node
        if (root == null){
            return;
        }

        // If we are visiting this level for the first time, adding node's value
        // Because we visit right before left, the first node seen at this level is the rightmost one
        if (level == result.size()){
            result.add(root.val);
        }

        // Visiting right child first to ensure the rightmost nodes are prioritized
        dfs(root.right, level + 1);

        // Then visiting left child
        dfs(root.left, level + 1);
    }
}