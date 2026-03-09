/*
*/

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