// Solution

class Solution {
public:
    vector<string> generateParenthesis(int n) {
        vector<string> ans;
        string temp;
        fun(n,temp,ans,0,0);
        return ans;
    }
    void fun(int n, string &temp, vector<string> &ans, int ocnt, int ccnt)
    {
        if(temp.size()==2*n)
        {
            ans.push_back(temp);
            return;
        }
        if(ocnt<n)
        {
            temp+='(';
            fun(n,temp,ans,ocnt+1,ccnt);
            temp.pop_back();
        }
        if(ccnt<ocnt)
        {
            temp+=')';
            fun(n,temp,ans,ocnt,ccnt+1);
            temp.pop_back();
        }
    }
};