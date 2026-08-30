public class _1_Optimal_O_2n {
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


       Idea :  A. Normal : Try every possible subarray and count zeros in each window.
                    If zeros <= K, compare its length with answer.
                    T = o(n²).

               B. Optimal : Start a window from first element and keep increasing R.
                  Count zeros inside the current window.
                  If zeroCount > K, shrink from LEFT until the window becomes valid.
                  Then compare current valid window length with answer.

                  T = o(2n) = (n for r moves + n for l moves to remove more invalid 0s).
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


Step 4 : After shrinking, window becomes valid again
============================================================
         R adds final 0, so zeroCount becomes 3 > K.
         Shrink from LEFT until zeroCount becomes 2.

         [ 1, 1, 1, 0, [0, 0, 1, 1, 1, 1, 0] ]   zeroCount = 3    ❌
                        ↑
                        L
         [ 1, 1, 1, 0, 0, [ 0, 1, 1, 1, 1, 0]]   zeroCount = 2

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



/*
============================================================
Complexity:

Time = O(2n) = O(n)

        a. R traversal:
           R moves from 0 → n-1 exactly once.
           So R movement = O(n).

        b. L traversal:
           Whenever zeroCount > K, L moves forward.
           L also moves from 0 → n-1 at most once in total.
           So L movement = O(n).

        Example:
        arr = [1,1,1,0,0,0,1,1,1,1,0]
        k = 2

        When R reaches the 3rd zero:
        [ [1,1,1,0,0,0] ]  → zeroCount = 3 > 2 ❌
           l         r

        Now L starts moving forward:
        [ 1,[1,1,0,0,0] ]  → remove 1
          l            r

        [ 1,1,[1,0,0,0] ] → remove 1
        [ 1,1,1,[0,0,0] ] → remove 1
        [ 1,1,1,0,[0,0] ] → remove 0
                   l  r   -> zeroCount = 2 ✓

        Here L moved 4 positions during this single R iteration.

        This looks expensive because L is inside the while loop,
        but L never comes back to an earlier index.

        Worst case:
        R can move n times.
        L can also move n times in total.

        Therefore:

        R movement = O(n)
        L movement = O(n)

        Total:
        O(n) + O(n)
        = O(2n)
        = O(n)


Space = O(1)

        Only a few variables are used:
        l, r, zeroCount, ans.

        No extra data structure grows with n.
============================================================
*/