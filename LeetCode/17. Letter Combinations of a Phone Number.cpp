/*
17. Letter Combinations of a Phone Number
Solved
Medium
Topics
premium lock icon
Companies
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.


 

Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:

Input: digits = "2"
Output: ["a","b","c"]
 

Constraints:

1 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].*/

// Solution

class Solution {
public:
    vector<string> letterCombinations(string digits) 
    {
        vector<string>ans;
        vector<string> temp = {".",".","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        string curr="";
        fun(0,temp,ans,digits,curr);
        return ans;
    }
    void fun(int idx,vector<string>&temp,vector<string>&ans,string &digits,string &curr)
    {
        if(idx==digits.size())
        {
            ans.push_back(curr);
            return;
        }
        int x=digits[idx]-'0';
        for(int i=0;i<temp[x].size();i++)
        {
            curr+=temp[x][i];
            fun(idx+1,temp,ans,digits,curr);
            curr.pop_back();
        }
    }
};