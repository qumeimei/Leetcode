Given two rectangles, find if the given two rectangles overlap or not.

 Notice

l1: Top Left coordinate of first rectangle.
r1: Bottom Right coordinate of first rectangle.
l2: Top Left coordinate of second rectangle.
r2: Bottom Right coordinate of second rectangle.

l1 != r2 and l2 != r2

Have you met this question in a real interview? Yes
Example
Given l1 = [0, 8], r1 = [8, 0], l2 = [6, 6], r2 = [10, 0], return true

Given l1 = [0, 8], r1 = [8, 0], l2 = [9, 6], r2 = [10, 0], return `false

思路:
• 最基础的想法分类讨论
• 假设一个固定,另一个从左往右移,那么在一个维度上分别是:
• 不重叠 重叠重叠重叠 不重叠 • 两个维度上都重叠矩形才重叠
• 分类讨论有点麻烦,有更简单的方法吗?
• 正着想麻烦就反着来
• 考虑下不重叠的时候是什么情况? • 要么上下、要么左右

public class Solution {
    /**
     * @param l1 top-left coordinate of first rectangle
     * @param r1 bottom-right coordinate of first rectangle
     * @param l2 top-left coordinate of second rectangle
     * @param r2 bottom-right coordinate of second rectangle
     * @return true if they are overlap or false
     */
    public boolean doOverlap(Point l1, Point r1, Point l2, Point r2) {
      if (l1.x > r2.x || l2.x > r1.x){
          return false;
      }
      if (l1.y < r2.y || l2.y < r1.y){
          return false;
      }
      return true;
        
    }
}

class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return rec1[2] > rec2[0] && rec1[3] > rec2[1] && rec1[0] < rec2[2] && rec1[1] < rec2[3];
    }
}
