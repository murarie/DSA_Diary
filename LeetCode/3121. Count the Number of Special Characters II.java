/*
3121. Count the Number of Special Characters II
Solved
Medium
Topics
premium lock icon
Companies
Hint
You are given a string word. A letter c is called special if it appears both in lowercase and uppercase in word, and every lowercase occurrence of c appears before the first uppercase occurrence of c.

Return the number of special letters in word.

 

Example 1:

Input: word = "aaAbcBC"

Output: 3

Explanation:

The special characters are 'a', 'b', and 'c'.

Example 2:

Input: word = "abc"

Output: 0

Explanation:

There are no special characters in word.

Example 3:

Input: word = "AbBCab"

Output: 0

Explanation:

There are no special characters in word.

 

Constraints:

1 <= word.length <= 2 * 105
word consists of only lowercase and uppercase English letters. */

// Solution

class Solution {
    public int numberOfSpecialChars(String word) {

        HashMap<Character,ArrayList<Integer>> mp = new HashMap<>();

        for(int i = 0; i < word.length(); i++) {

            char ch = word.charAt(i);

            mp.putIfAbsent(ch,new ArrayList<>());

            mp.get(ch).add(i);
        }

        int res = 0;

        for(char ch = 'a'; ch <= 'z'; ch++) {

            char upper = (char)(ch - 32);

            if(!mp.containsKey(ch) || !mp.containsKey(upper)) {
                continue;
            }

            int loweridx = mp.get(ch).get(mp.get(ch).size() - 1);
            int upperidx = mp.get(upper).get(0);

            if(loweridx < upperidx) {
                res++;
            }
        }

        return res;
    }
}