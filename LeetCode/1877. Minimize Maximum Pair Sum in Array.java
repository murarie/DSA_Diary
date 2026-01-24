/*
 */

// solution

class Solution {
    public int minPairSum(int[] nums) {
        int mxval=0;
        for(int n:nums) mxval=Math.max(mxval,n);
        int[] freq=new int[mxval+1];
        for(int n:nums) freq[n]++;
        int si=1,ei=mxval,mxsum=0;
        while(si<=ei) {
            while(si<=ei&&freq[si]==0) si++;
            while(si<=ei&&freq[ei]==0) ei--;
            if (si<=ei){
                mxsum=Math.max(mxsum,si+ei);
                freq[si]--;freq[ei]--;
            }
        }
        return mxsum;
    }
}