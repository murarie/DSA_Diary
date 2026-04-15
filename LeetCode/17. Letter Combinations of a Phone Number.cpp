/*
*/

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