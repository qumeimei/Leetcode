Given an array nums of integers, you can perform operations on the array.

In each operation, you pick any nums[i] and delete it to earn nums[i] points. After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.

You start with 0 points. Return the maximum number of points you can earn by applying such operations.

Example 1:
Input: nums = [3, 4, 2]
Output: 6
Explanation: 
Delete 4 to earn 4 points, consequently 3 is also deleted.
Then, delete 2 to earn 2 points. 6 total points are earned.
Example 2:
Input: nums = [2, 2, 3, 3, 3, 4]
Output: 9
Explanation: 
Delete 3 to earn 3 points, deleting both 2's and the 4.
Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
9 total points are earned.
Note:

The length of nums is at most 20000.
Each element nums[i] is an integer in the range [1, 10000].

Similar as House Robber I
class Solution {
    public int deleteAndEarn(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++){
            if (!map.containsKey(nums[i])){
                map.put(nums[i], 1);
            }else{
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }
        int n = map.size();
        int[] dp = new int[n + 1];
        int i = 2;
        dp[0] = 0;
        for (int key : map.keySet()){
            if (key == map.firstKey()){
                dp[1] = map.firstKey() * map.get(map.firstKey());
            }
            Integer lowerKey = map.lowerKey(key);
            if (lowerKey == key - 1){
                dp[i] = Math.max(dp[i-2] + map.get(key) * key, dp[i-1]);
            }else{
                dp[i] = dp[i-1] + map.get(key) * key;
            }
            i++;
        }
        return dp[n];
    }
}
