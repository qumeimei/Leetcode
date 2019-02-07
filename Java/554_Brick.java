There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. The bricks have the same height but different width. You want to draw a vertical line from the top to the bottom and cross the least bricks.

The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each brick in this row from left to right.

If your line go through the edge of a brick, then the brick is not considered as crossed. You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.

You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.

Example:
Input: 
[[1,2,2,1],
 [3,1,2],
 [1,3,2],
 [2,4],
 [3,1,2],
 [1,3,1,1]]
Output: 2
Explanation: 


class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (List<Integer> list : wall){
            int preSum = 0;
            for (int i = 0; i < list.size() - 1; i++){
                preSum += list.get(i);
                map.put(preSum, map.getOrDefault(preSum, 0) + 1);
                max = Math.max(max, map.get(preSum));
            }
        }
        return wall.size() - max;
    }
}

class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int res = wall.size();
        for (List<Integer> list : wall){
            sum = 0;
            for(int i = 0; i < list.size() - 1; i++){
                sum += list.get(i);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
                res = Math.min(res, wall.size() - map.get(sum));
            }
        }
        return res;
    }
}
