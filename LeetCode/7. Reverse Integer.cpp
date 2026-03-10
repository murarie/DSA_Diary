/*
7. Reverse Integer
Solved
Medium
Topics
premium lock icon
Companies
Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

 

Example 1:

Input: x = 123
Output: 321
Example 2:

Input: x = -123
Output: -321
Example 3:

Input: x = 120
Output: 21
 

Constraints:

-231 <= x <= 231 - 1 */

// solution

class Solution {
public:
  
    int reverse(int x) {
         int revdig=0;
          
    while(x!=0){
        int lastdig= x%10;
        if(revdig<INT_MIN/10 || revdig>INT_MAX/10){
            return 0;
        }
        revdig=(revdig*10)+lastdig; 
        x=x/10;
         }
        return revdig; 
    }
      
};