public class _3_Optimal_Sliding_Window_O_n {
    /* Given : We need to find largest subarray's length possible where its sum <= k.
               Contiguous + Size is not Fixed Size For window + SubArray = variable window
       Idea :  a. subArray=window
               b. Before calculating subarray length we need to first validate its sum range.
                  So first calculate sum of every subarray.
                  If subarray sum is in range then only we will consider its length.

               BruteForce : Simple Create all possible contiguous subarrays and find their sum 1 by 1.
                            If sum is in range => Update maxLengthAns
                            If sum crossed range => break no need to check, adding more element will make sum larger.
               Better : start a window from first element and keep it increasing.
                        in each step validate its sum if sum is in range compare its length with answer.
                        if sum is bigger keep shrinking the window until valid sum is find then only move right.
               Optimal: if sum is bigger we need to shrink,
                        But no need to shrink heavily it will make window size so small and we will increase again.
                        But Idea is to find max length of subarray so no need to go below the maximum length already found..
                        =>So, Shrink once to compensate for the new element, then continue moving R to find a larger valid window.

                                                SUM > K
                                                   ↓
                                                Shrink once to compensate 1 R movement
                                                   ↓
                                                Move R
                                                   ↓
                                                Try to find a larger window

                        Ex : You already found maxLength = 3
                        Than if you shrink below 3 then you will again come to 3
                        like  3-->  |2|  -----> 3 Unnecessary logic we need bigger length so why going back to 2.
               */
    public static void main(String[] args) {
        int k = 14;
        int[] arr = {2,5,1,10,10};

        /* Step 1 : variables analogy for window :
                    a. 2 for windows -l,r to monitor end points of window.
                    b. 'ws' will store sum of current window, to find next window sum we will remove+add elements from it.
                    c. Then we need to monitor max length, so will use 'ans' which we will keep updating. */
        int l=0;
        int r =0;

        int windowSum= 0;
        int longestSubarrayLength =0;

        while(r < arr.length){
            windowSum+= arr[r];

            if(windowSum > k){
                windowSum-= arr[l];
                l++;
            }

            if(windowSum <= k){
                longestSubarrayLength = Math.max(longestSubarrayLength, r-l+1);
            }

            r++;
        }

        System.out.println(longestSubarrayLength);
    }

}


/*
arr = [2, 5, 1, 10, 10]
k = 14

[ [2], 5, 1, 10, 10]       sum = 2    length = 1 ✓
[ [2, 5], 1, 10, 10]       sum = 7    length = 2 ✓
[ [2, 5, 1], 10, 10]       sum = 8    length = 3 ✓

[ [2, 5, 1, 10], 10]       sum = 18 ❌  shrink First
[ 2, [5, 1, 10], 10]       sum = 16 ❌  length = 3   Now move on

[ 2, [5, 1, 10, 10]]       sum = 26 ❌  shrink First
[ 2, 5, [1, 10, 10]]       sum = 21 ❌  length = 3   Now move on

[ 2, 5, 1, [10, 10]] r    Invalid r :  r = arr.length → loop stops

Maximum length = 3

============================================================
*/