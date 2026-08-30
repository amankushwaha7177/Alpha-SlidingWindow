public class _5_Max_Consecutive_Ones_III {
    /* Interview : Given a binary array and K, find the maximum number of consecutive 1s
                   possible by flipping at most K zeros.
       Given : We can flip at most K zeros into 1s, so our window can contain maximum K zeros.
               Contiguous + Variable Size + At Most K Zeros = Variable Window
               subArray=window
       Idea :  A. Normal : Try every possible subarray and count zeros in each window.
                    If zeros <= K, compare its length with answer.
                    T = o(n²).

               B. Optimal : Start a window from first element and keep increasing R.
                  Count zeros inside the current window.
                  If zeroCount > K, shrink from LEFT until the window becomes valid.
                  Then compare current valid window length with answer.
                  T = o(n).
     */
    public static void main(String[] args) {
        int k = 2;
        int[] arr = {1,1,1,0,0,0,1,1,1,1,0};

        /* Step 0 : variables analogy for window :
                    a. 2 for window -l,r to monitor start and end points of window.
                    b. 'zeroCount' will store number of zeros inside current window.
                    c. 'ans' will store maximum valid window length found so far. */

        /* Step 1 : Variable size subArray means : Window size can increase or decrease.
                    So start a window from index 0 and keep increasing R.
                    Count zeros and validate whether zeroCount is within K. */
        int l = 0;
        int r = 0;

        int zeroCount = 0;
        int ans = 0;


        /* Step 2 : We will move using r.
                  R enters one element at a time and we check whether it is 0.
                  If zeroCount becomes bigger than K, window becomes invalid.
                  Then shrink from LEFT until zeroCount becomes valid again. */
        while(r < arr.length){
            if(arr[r] == 0){
                zeroCount++;
            }

            if(zeroCount > k){
                while(zeroCount > k){
                    if(arr[l] == 0){
                        zeroCount--;
                    }
                    l++;
                }
            }

            if(zeroCount <= k){
                ans = Math.max(ans, r-l+1);
            }

            r++;
        }

        System.out.println(ans);
    }
}
/*
Step 1 : Start window and keep increasing R
-------  k = 2

         [ [1], 1, 1, 0, 0, 0, 1, 1, 1, 1, 0]    zeroCount = 0    length = 1
         [ [1, 1], 1, 0, 0, 0, 1, 1, 1, 1, 0]    zeroCount = 0    length = 2
         [ [1, 1, 1], 0, 0, 0, 1, 1, 1, 1, 0]    zeroCount = 0    length = 3


Step 2 : R adds 0, so zeroCount increases
--------
         [ [1, 1, 1, 0], 0, 0, 1, 1, 1, 1, 0]    zeroCount = 1    length = 4
         [ [1, 1, 1, 0, 0], 0, 1, 1, 1, 1, 0]    zeroCount = 2    length = 5

         R adds another 0, so zeroCount becomes 3 > K.
         Window becomes invalid, so shrink from LEFT.

         [ 1, [1, 1, 0, 0, 0], 1, 1, 1, 1, 0]    zeroCount = 3    ❌
         [ 1, 1, [1, 0, 0, 0], 1, 1, 1, 1, 0]    zeroCount = 3    ❌
         [ 1, 1, 1, [0, 0, 0], 1, 1, 1, 1, 0]    zeroCount = 3    ❌
         [ 1, 1, 1, 0, [0, 0, 1], 1, 1, 1, 0]    zeroCount = 2    ✓


Step 3 : Continue moving R after window becomes valid
============================================================

         [ 1, 1, 1, 0, [0, 0, 1], 1, 1, 1, 0]    zeroCount = 2    length = 3

         [ 1, 1, 1, 0, [0, 0, 1, 1], 1, 1, 0]    zeroCount = 2    length = 4
         [ 1, 1, 1, 0, [0, 0, 1, 1, 1], 1, 0]    zeroCount = 2    length = 5
         [ 1, 1, 1, 0, [0, 0, 1, 1, 1, 1], 0]    zeroCount = 2    length = 6

         R adds final 0, so zeroCount becomes 3 > K.
         Shrink from LEFT until zeroCount becomes 2.

Step 4 : After shrinking, window becomes valid again
============================================================

         [ 1, 1, 1, 0, [0, 0, 1, 1, 1, 1, 0] ]
                    ↑
                    L

         zeroCount = 2 ✓
         length = 7 - 3 + 1 = 4
         ans = max(6, 4) = 6

         Maximum length = 6
*/

/*
Remember:
a. right moves → element ENTERS.
b. 0 enters → zeroCount increases.
c. zeroCount > K → window becomes invalid.
d. left moves → element LEAVES.
e. 0 leaves → zeroCount decreases.
f. zeroCount <= K → window is valid.
g. ans → maximum valid window length.
*/