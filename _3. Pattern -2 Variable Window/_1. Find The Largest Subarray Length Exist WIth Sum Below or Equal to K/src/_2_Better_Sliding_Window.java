public class _2_Better_Sliding_Window {
    public static void main(String[] args) {
        int k = 14;
        int[] arr = {2,5,1,10,10};

        /* Step 1 : variables analogy for window */
        int l=0;
        int r =0;

        int windowSum= 0;
        int longestSubarrayLength =0;

        while(r < arr.length){
            windowSum+= arr[r];

            if(windowSum > k){     // --->always write terminatory code above main logic simple.
                while(windowSum > k){
                    windowSum-= arr[l];
                    l++;
                }
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

[ [2, 5, 1, 10], 10]       sum = 18     ❌ Shrink Now
[ 2, [5, 1, 10], 10]       sum = 16     ❌ Shrink Now
[ 2, 5, [1, 10], 10]       sum = 11   length = 2  ✓  now move r.
[ 2, 5, [1, 10, 10]]       ------> r is mover so it will definitely move at end of this iteration

[ 2, 5, [1, 10, 10]]       sum = 21     ❌ Shrink Now
[ 2, 5, 1, [10, 10]]       sum = 20     ❌ Shrink Now
[ 2, 5, 1, 10, [10]]       sum = 10   length = 1  ✓  now move r.

r = length => stop here.


Maximum length = 3

============================================================
*/