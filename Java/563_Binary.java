Given a binary tree, return the tilt of the whole tree.

The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of 
all right subtree node values. Null node has tilt 0.

The tilt of the whole tree is defined as the sum of all nodes' tilt.

Example:
Input: 
         1
       /   \
      2     3
Output: 1
Explanation: 
Tilt of node 2 : 0
Tilt of node 3 : 0
Tilt of node 1 : |2-3| = 1
Tilt of binary tree : 0 + 0 + 1 = 1
Note:

The sum of node values in any subtree won't exceed the range of 32-bit integer.
All the tilt values won't exceed the range of 32-bit integer.


Method 1:
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int tilt = 0;
    public int findTilt(TreeNode root) {
        sum(root);
        return tilt;
    }
    private int sum(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = sum(root.left);
        int right = sum(root.right);
        tilt += Math.abs(left - right);
        return left + right + root.val;
    }
}

Method 2: without global variable
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int findTilt(TreeNode root) {
        int[] tilt = new int[1];
        sum(root, tilt);
        return tilt[0];
    }
    private int sum(TreeNode root, int[] tilt){
        if (root == null){
            return 0;
        }
        int left = sum(root.left, tilt);
        int right = sum(root.right, tilt);
        tilt[0] += Math.abs(left - right);
        return left + right + root.val;
    }
}


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    class Pair {
        int sum;
        int tilt;
        public Pair (int sum, int tilt){
            this.sum = sum;
            this.tilt = tilt;
        }
    }
    public int findTilt(TreeNode root) {
        if (root == null){
            return 0;
        }
        Pair p = dfs(root);
        return p.tilt;
    }
    private Pair dfs(TreeNode root){
        if (root == null){
            return new Pair(0, 0);
        }
        Pair left = dfs(root.left);
        Pair right = dfs(root.right);
        int tilt = Math.abs(left.sum - right.sum) + left.tilt + right.tilt;
        int sum = root.val + left.sum + right.sum;
        return new Pair(sum, tilt);
    }
}
