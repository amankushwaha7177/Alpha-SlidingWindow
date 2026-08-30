class _2_More_Optimal_O_n____NoWhile_Shrink_Just_Move_Invalid_Window_1by1_ {
    /* Interview : Given a binary array and K, find the maximum number of consecutive 1s
                   possible by flipping at most K zeros.

       Given : We can flip at most K zeros into 1s.
               So we need to find the largest subarray's length possible
               where the number of zeros <= K.

               Contiguous + Size is not Fixed + At Most K Zeros + SubArray = Variable Window
               subArray = window

        Brain : The simplest mental translation :
                Question says: We can flip at most K zeros into 1s.

                Sliding-window language:
                Find the longest contiguous window containing at most K zeros.

                Ex: k = 2
                [ [1, 1, 0, 1, 0], 1, 1]    → 2 zeros → Fine ✓
                [ [1, 1, 0, 1, 0, 0], 1]    → 3 zeros → ❌ Invalid

       Idea :  Previous optimal :
               T = o(2n) = (n for r moves + n for l moves to remove more invalid 0s).

               Optimal : Start a window from first element and keep increasing R.
                  Count zeros inside the current window.
                  If zeroCount > K, shrink from LEFT only once.
                  This compensates for the new element and keeps the window size
                  from growing beyond the maximum length found so far.
                  Then compare current window length with answer.
               T = o(n).
     */
    public static void main(String[] args) {
        int k = 2;
        int[] arr = {1,1,1,0,0,0,1,1,1,1,0};

        /* Step 0 : variables analogy for window :
                    a. 'l' and 'r' monitor the start and end points of window.
                    b. 'zeroCount' will store number of zeros inside current window.
                    c. 'ans' will store the maximum valid window length found so far. */

        /* Step 1 : Variable size subArray means : Window size can increase or decrease.
                    So start a window from index 0 and keep increasing R.
                    Count zeros and validate whether zeroCount is within K. */
        int l = 0;
        int r = 0;

        int zeroCount = 0;
        int ans = 0;


        /* Step 2 : We will move using R.
                  R enters one element at a time and we check whether it is 0.
                  If zeroCount becomes bigger than K, window becomes invalid.
                  Then shrink from LEFT only once to compensate for new element.
                  Finally compare current window length with answer. */
        while(r < arr.length){
            if(arr[r] == 0){
                zeroCount++;
            }

            if(zeroCount > k){
                if(arr[l] == 0){
                    zeroCount--;
                }
                l++;
            }

            ans = Math.max(ans, r-l+1);

            r++;
        }

        System.out.println(ans);
    }
}
/*
Step 1 : Start window and keep increasing R
-------  k = 2

         [ [1], 1, 1, 0, 0, 0, 1, 1, 1, 1, 0]       zeroCount = 0    length = 1 ✓
         [ [1, 1], 1, 0, 0, 0, 1, 1, 1, 1, 0]       zeroCount = 0    length = 2 ✓
         [ [1, 1, 1], 0, 0, 0, 1, 1, 1, 1, 0]       zeroCount = 0    length = 3 ✓


Step 2 : R adds 0, so zeroCount increases
============================================================

         [ [1, 1, 1, 0], 0, 0, 1, 1, 1, 1, 0]       zeroCount = 1    length = 4 ✓
         [ [1, 1, 1, 0, 0], 0, 1, 1, 1, 1, 0]       zeroCount = 2    length = 5 ✓

         R adds another 0 → zeroCount = 3 > K → INVALID ❌

         Shrink LEFT only once:

         [ 1, [1, 1, 0, 0, 0], 1, 1, 1, 1, 0]       zeroCount = 3    ❌
         [ 1, 1, [1, 0, 0, 0], 1, 1, 1, 1, 0]       zeroCount = 3    ❌

         L removes 1, so zeroCount remains 3.
         Current window size is reduced by 1 to compensate for R's new element.


Step 3 : Continue moving R
============================================================

         [ 1, 1, [1, 0, 0, 0, 1], 1, 1, 1, 0]       zeroCount = 3    ❌
         [ 1, 1, 1, [0, 0, 0, 1, 1], 1, 0]           zeroCount = 3    ❌
         [ 1, 1, 1, 0, [0, 0, 1, 1, 1], 0]            zeroCount = 2    ✓

         R keeps moving.
         L also keeps moving one position whenever zeroCount > K.
         We do not shrink repeatedly because our goal is MAXIMUM length.


Step 4 : Continue until R reaches the last element
============================================================

         R moves through the remaining elements one by one.
         Each time:

         R adds element → zeroCount may increase
         zeroCount > K → L moves once
         zeroCount <= K → current length is compared with ans

         The maximum valid window found is:

         [ 0, 0, 1, 1, 1, 1 ]                          → length = 6

         Maximum length = 6
============================================================
*/

/*
Remember:
a. right moves → element ENTERS.
b. 0 enters → zeroCount increases.
c. zeroCount > K → window becomes invalid.
d. left moves → one element LEAVES.
e. 0 leaves → zeroCount decreases.
f. We shrink only once → compensate for R's new element.
g. ans → maximum window length found.
*/