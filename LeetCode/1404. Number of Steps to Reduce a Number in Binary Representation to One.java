/*
 */

// Solution

class Solution {
    public int numSteps(String s) {
        int res = 0;
        StringBuilder sb = new StringBuilder(s);
        while(sb.length()>1){
            if(sb.charAt(sb.length()-1)=='0'){
                sb.deleteCharAt(sb.length()-1);
            }
            else{
                int r = sb.length()-1;
                while(r>=0){
                    if(sb.charAt(r)=='1'){
                        sb.setCharAt(r,'0');
                    }else{
                        sb.setCharAt(r,'1');
                        break;
                    }
                    r--;
                }
                if(r==-1){
                    sb.insert(0,'1');
                }
            }
            res++;
        }
        return res;
    }
}