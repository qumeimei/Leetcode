Given an array of integers A with even length, return true if and only if it is possible to reorder it such 
that A[2 * i + 1] = 2 * A[2 * i] for every 0 <= i < len(A) / 2.

 

Example 1:

Input: [3,1,3,6]
Output: false
Example 2:

Input: [2,1,2,6]
Output: false
Example 3:

Input: [4,-2,2,-4]
Output: true
Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].
Example 4:

Input: [1,2,4,16,8,4]
Output: false
 

Note:

0 <= A.length <= 30000
A.length is even
-100000 <= A[i] <= 100000

Method 1:
Time complexity: O(nlogn)
 
Key: need to use treemap to ensure the logic is in ascending or descending order, otherwise we don't know to check key/2 or key*2
 
 need sorted array and hashmap, so choose treemap
 
class Solution {
    public boolean canReorderDoubled(int[] A) {
        if (A.length == 0){
            return true;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i : A){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (int key : map.keySet()){
            if (map.get(key) == 0){
                continue;
            }
            if (key < 0){
                if (map.get(key/2) == null || map.get(key/2) < map.get(key)){
                    return false;
                }else{
                    map.put(key/2, map.get(key/2) - map.get(key));
                }
            }else if (key > 0){
                if (map.get(key*2) == null || map.get(key*2) < map.get(key)){
                    return false;
                }else{
                    map.put(key*2, map.get(key*2) - map.get(key));
                }
            }else{
                if (map.get(key) % 2 != 0){
                    return false;
                }
            }
        }
        return true;
    }
}
