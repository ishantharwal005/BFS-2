// Time Complexity : O(n) where n is number of nodes in tree
// Space Complexity : O(n) for BFS and O(h) for DFS
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Had to revise basic syntax

// ## Problem 2: Cousins in binary tree (https://leetcode.com/problems/cousins-in-binary-tree/)
// Given the root of a binary tree with unique values and the values of two different nodes of the tree x and y, return true if the nodes corresponding to the values x and y in the tree are cousins, or false otherwise.
// Two nodes of a binary tree are cousins if they have the same depth with different parents.
// Note that in a binary tree, the root node is at the depth 0, and children of each depth k node are at the depth k + 1.
// Example 1:
// Input: root = [1,2,3,4], x = 4, y = 3
// Output: false

// Example 2:
// Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
// Output: true

// Example 3:
// Input: root = [1,2,3,null,4], x = 2, y = 3
// Output: false

// Constraints:
// The number of nodes in the tree is in the range [2, 100].
// 1 <= Node.val <= 100
// Each node has a unique value.
// x != y
// x and y are exist in the tree.

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
class Solution {
    
    public boolean isCousins(TreeNode root, int x, int y) {
        // Edge case: Empty tree
        if (root == null){
            return false;
        }

        boolean x_found = false; // Tracks if x is found at current level
        boolean y_found = false; // Tracks if y is found at current level

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root); // Starting BFS with root

        while (!q.isEmpty()){
            int size = q.size(); // Number of nodes at the current level
            for (int i = 0; i < size; i++){
                TreeNode curr = q.poll();

                // Checking if x and y are siblings (share the same parent)
                if (curr.left != null && curr.right != null){
                    if (curr.left.val == x && curr.right.val == y){
                        return false; // Not cousins if they're siblings
                    }
                    if (curr.left.val == y && curr.right.val == x){
                        return false;
                    }
                }

                // Checking if we found x or y at this level
                if (curr.val == x){
                    x_found = true;
                }
                if (curr.val == y){
                    y_found = true;
                }

                // Adding children to the queue for next level
                if (curr.left != null){
                    q.add(curr.left);
                }
                if (curr.right != null){
                    q.add(curr.right);
                }
            }

            // If x and y are found at the same level and are not siblings
            if (x_found == true && y_found == true){
                return true;
            }

            // If only one is found at this level, they can't be cousins
            if (x_found == true || y_found == true){
                return false;
            }
        }
        return false; // if neither x nor y were found
    }        
}


// DFS Approach:
// Time Complexity: O(n) | Space Complexity: O(h)
class Solution {
    int x_lvl, y_lvl; // levels of nodes x and y
    TreeNode x_parent, y_parent; // parents of nodes x and y
    public boolean isCousins(TreeNode root, int x, int y) {
        // Edge case: Empty tree
        if (root == null){
            return false;
        }
        
        // Initializing level and parent tracking
        x_lvl = -1;
        y_lvl = -1;

        // DFS to find level and parent of both x and y
        dfs(root, null, 0, x, y);

        // Cousins: same level, different parents
        return x_parent != y_parent && x_lvl == y_lvl;
    }

    private void dfs(TreeNode root, TreeNode parent, int level, int x, int y){
        // Base case: null node or both targets are already found
        if (root == null || (x_parent != null && y_parent != null)){
            return;
        }

        // if current node is x, store its level and parent
        if (root.val == x){
            x_parent = parent;
            x_lvl = level;
        }
        
        // if current node is y, store its level and parent
        if (root.val == y){
            y_parent = parent;
            y_lvl = level;
            
        }

        // Recursively calls for left and right subtrees
        dfs(root.left, root, level + 1, x, y);
        dfs(root.right, root, level + 1, x, y);
    }
}