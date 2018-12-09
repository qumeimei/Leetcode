In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

 

Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 

Note:

1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are english lowercase letters.

Method 1:
Time complexity: O(mnk)
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        for (int k = 0; k < words.length - 1; k++){
            String curr = words[k];
            String next = words[k+1];
            int i = 0;
            int j = 0;
            while (i < curr.length() || j < next.length()){
                char c = i < curr.length() ? curr.charAt(i) : '0';
                char n = j < next.length() ? next.charAt(j) : '0';
                if (c == n){
                    i++;
                    j++;
                }else{
                    int cInd = order.indexOf(c);
                    int nInd = order.indexOf(n);
                    if (nInd == -1){
                        return false;
                    }
                    if (cInd > nInd){
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }
}
