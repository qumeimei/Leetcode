Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

Note: The length of path between two nodes is represented by the number of edges between them.

Example 1:

Input:

              5
             / \
            4   5
           / \   \
          1   1   5
Output:

2
Example 2:

Input:

              1
             / \
            4   5
           / \   \
          4   4   5
Output:

2
Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.

Method:
https://leetcode.com/problems/longest-univalue-path/solution/
Let arrow_length(node) be the length of the longest arrow that extends from the node. 
That will be 1 + arrow_length(node.left) if node.left exists and has the same value as node. Similarly for the node.right case.

While we are computing arrow lengths, each candidate answer will be the sum of the arrows in both directions from that node. 
We record these candidate answers and return the best one.
    
Note that
For each node, there are 5 cases for the longest path as candidates
1. not pass node but on the left subtree
2. not pass node but on the right subtree
3. pass node and on the left subtree
4. pass node and on the right subtree
5. pass node and cross both left subtree and right subtree

We can use variable ans to track the longest path and cover cases 1, 2, 5; use recursion function return to cover cases 3, 4

Note that we are looking for path and graph, which means there are start node and end node. So in the function return, we have to 
use Math.max(leftMax, rightMax)

  

  Similar to 124. Binary Tree Maximum Path Sum
class Solution {
    int max = Integer.MIN_VALUE;
    public int longestUnivaluePath(TreeNode root) {
        if (root == null){
            return 0;
        }
        longestIncludeRoot(root); //return the longest path that pass root, note that this is not looking for graph
        return max;
    }
    private int longestIncludeRoot(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = longestIncludeRoot(root.left);
        int right = longestIncludeRoot(root.right);
        int leftIncludeRoot = 0; //number of longest path that pass through root
        int rightIncludeRoot = 0;
        if (root.left != null && root.val == root.left.val){
            leftIncludeRoot = left + 1;
        }
        if (root.right != null && root.val == root.right.val){
            rightIncludeRoot = right + 1;
        }
        max = Math.max(max, leftIncludeRoot + rightIncludeRoot);
        return Math.max(leftIncludeRoot, rightIncludeRoot);
    }
}

Time Complexity: O(N), where NN is the number of nodes in the tree. We process every node once.

Space Complexity: O(logN or H), where H is the height of the tree. Our recursive call stack could be up to H layers deep.


  
Remove global variable
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
    public int longestUnivaluePath(TreeNode root) {
        int[] max = new int[1];
        longestIncludeRoot(root, max);
        return max[0];
    }
    private int longestIncludeRoot(TreeNode root, int[] max){
        if (root == null){
            return 0;
        }
        int left = longestIncludeRoot(root.left, max);
        int right = longestIncludeRoot(root.right, max);
        int leftIncludeRoot = 0;
        int rightIncludeRoot = 0;
        if (root.left != null && root.val == root.left.val){
            leftIncludeRoot = left + 1;
        }
        if (root.right != null && root.val == root.right.val){
            rightIncludeRoot = right + 1;
        }
        max[0] = Math.max(max[0], leftIncludeRoot + rightIncludeRoot);
        return Math.max(leftIncludeRoot, rightIncludeRoot);
    }
}
