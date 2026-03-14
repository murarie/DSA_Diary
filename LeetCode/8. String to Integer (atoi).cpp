/*
*/

// Solution

class Solution {
public:
    int myAtoi(string s) {
        int max_int = 2147483647, min_int = -2147483648;
        int i = 0, n = s.length(), sign = 1, result = 0;
        
        while (i < n && s[i] == ' ') i++;
        
        if (i < n && (s[i] == '-' || s[i] == '+')) {
            sign = (s[i] == '-') ? -1 : 1;
            i++;
        }
        
        while (i < n && isdigit(s[i])) {
            int digit = s[i] - '0';
            if (result > (max_int - digit) / 10) {
                return sign == 1 ? max_int : min_int;
            }
            result = result * 10 + digit;
            i++;
        }
        
        return sign * result;
    }
};