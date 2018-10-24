Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces 
in s to construct a sentence where each word is a valid dictionary word. You may assume the dictionary
does not contain duplicate words.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].

DP = recursion + memorization
Method 1:  DP, the same as Methodd 2
class Solution {
    Map<String, List<String>> map = new HashMap<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0){
            return result;
        }
        if (map.containsKey(s)){
            return map.get(s);
        }
        if (wordDict.contains(s)){//must have, like initialization
            result.add(s);
        }
        for (int i = 1; i < s.length(); i++){
            String sub = s.substring(i);
            if (wordDict.contains(sub)){
                List<String> temp = wordBreak(s.substring(0, i), wordDict);
                if (temp.size() != 0){
                    for (String str : temp){
                        result.add(str +" " + sub);
                    }
                }
            }
        }
        map.put(s, result);
        return result;
    }
}

Method 2: DP
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<String, List<String>> map = new HashMap<>();
        return dp(map, s, wordDict);
    }
    private List<String> dp(Map<String, List<String>> map, String s, List<String> wordDict){
        if (map.containsKey(s)){
            return map.get(s);
        }
        List<String> result = new ArrayList<>();
        if  (wordDict.contains(s)){
            result.add(s);
        }
        for (int i = 1; i < s.length(); i++){
            String sub = s.substring(0, i);
            if (wordDict.contains(sub)){
                List<String> temp = dp(map, s.substring(i), wordDict);
                if (temp.size() != 0){
                    for (String str : temp){
                        result.add(sub + " " + str);
                    }
                }
            }
        }
        map.put(s, result);
        return result;
    }
}


Method 3: Backtracking
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (String st : wordDict){
            set.add(st);
            min = Math.min(min, st.length());
            max = Math.max(max, st.length());
        }
        backtrack(res, new ArrayList<String>(), s, set, min, max, 0);
        return res;
    }
    private void backtrack(List<String> res, List<String> item, String s, Set<String> set, int min, int max, int pos){
        if (pos == s.length()){
            String str = String.join(" ", item);
            res.add(str);
            return;
        }
     //   System.out.println(pos);
        for (int i = pos; i <= s.length(); i++){
            if (i - pos < min){
                continue;
            }
            if (i - pos > max){
                break;
            }
            String sub = s.substring(pos, i);
      //      System.out.println(sub);
            if (set.contains(sub)){
                item.add(sub);
                backtrack(res, item, s, set, min, max, i);
                item.remove(item.size() - 1);
            }
        }
    }
    
}
