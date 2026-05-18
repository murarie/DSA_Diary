/*
1306. Jump Game III
Solved
Medium
Topics
premium lock icon
Companies
Hint
Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach any index with value 0.

Notice that you can not jump outside of the array at any time.

 

Example 1:

Input: arr = [4,2,3,0,3,1,2], start = 5
Output: true
Explanation: 
All possible ways to reach at index 3 with value 0 are: 
index 5 -> index 4 -> index 1 -> index 3 
index 5 -> index 6 -> index 4 -> index 1 -> index 3 
Example 2:

Input: arr = [4,2,3,0,3,1,2], start = 0
Output: true 
Explanation: 
One possible way to reach at index 3 with value 0 is: 
index 0 -> index 4 -> index 1 -> index 3
Example 3:

Input: arr = [3,0,2,1,2], start = 2
Output: false
Explanation: There is no way to reach at index 1 with value 0.
 

Constraints:

1 <= arr.length <= 5 * 104
0 <= arr[i] < arr.length
0 <= start < arr.length */

// Solution

class Solution {
    public boolean canReach(int[] arr, int start) {
        int n=arr.length;
        int[]visited=new int[n];
        if(arr[start]==0)return true;
        return bfs(arr,start+arr[start],visited)||bfs(arr,start-arr[start],visited);
    }
    public static boolean bfs(int[]arr,int index,int[]dp){
        if(index<0||index>=arr.length)return false;
        if(dp[index]!=0)return false;
        dp[index]=1;
        if(arr[index]==0)return true;
        return bfs(arr,index+arr[index],dp)||bfs(arr,index-arr[index],dp);
    }
}