/*
3120. Count the Number of Special Characters I
Solved
Easy
Topics
premium lock icon
Companies
Hint
You are given a string word. A letter is called special if it appears both in lowercase and uppercase in word.

Return the number of special letters in word.

 

Example 1:

Input: word = "aaAbcBC"

Output: 3

Explanation:

The special characters in word are 'a', 'b', and 'c'.

Example 2:

Input: word = "abc"

Output: 0

Explanation:

No character in word appears in uppercase.

Example 3:

Input: word = "abBCab"

Output: 1

Explanation:

The only special character in word is 'b'.

 

Constraints:

1 <= word.length <= 50
word consists of only lowercase and uppercase English letters. */

// Solution

class Solution {
    public int numberOfSpecialChars(String word) {
        boolean[] lower = new boolean[26];
        boolean[] uppper = new boolean[26];
        for(char ch : word.toCharArray()){
            if(Character.isLowerCase(ch)){
                lower[ch - 'a'] = true;
            }
            else{
                uppper[ch - 'A'] = true;
            }
        }
        int cnt = 0;
        for(int i = 0; i < 26; i++){
            if(lower[i] && uppper[i]) cnt++;
        }
        return cnt;
    }
}