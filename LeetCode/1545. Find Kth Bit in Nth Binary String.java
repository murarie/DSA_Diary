/*
 */

// Solution

class Solution {
    public char findKthBit(int n, int k) {
        int pos = k;
        int inverse = 0;
        int size = 0;

        for(int i = 0; i<n; i++){
            size = size*2 + 1;
        }

        while(size != 1){
            if(pos < size/2 + 1){
                size /= 2;
            }
            else if(pos == size/2 + 1){
                int answer = (inverse+1)%2;
                return (char)(answer + '0');
            }
            else{
                pos = size - pos + 1;
                size /= 2;
                inverse += 1;
            }
        }

        int answer = inverse%2;
        return (char)(answer + '0');
    }
}