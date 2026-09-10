public class _1_Optimal_O_n {
    /* Interview : Given an array and fixed k, find the maximum sum of any contiguous subarray of size k.
       Given : SubArrays size is fixed K, we need to find which subarray sum is max among all.
               Contiguous + Fixed Size + SubArray = Constant window
               subArray=window
       Idea :  A. Normal :In normal way what we can do we will move k size window 1 by 1 step.
                    [ [2, 1, 5, 1, 3], 2, 6, 4, 2]  → 12  ( sum found through loop k = l to r )
                    [ 2, [1, 5, 1, 3, 2], 6, 4, 2]  → 12         "    "
                    [ 2, 1, [5, 1, 3, 2, 6], 4, 2]  → 17         "    "
                    [ 2, 1, 5, [1, 3, 2, 6, 4], 2]  → 16         "    "
                    [ 2, 1, 5, 1, [3, 2, 6, 4, 2]]  → 17         "    "
                   T = n(number of window move) * k(k loop for each window) =o(nk).

               B. Optimal : But there is no need to do looping to find sum of contiguous windows again and again.
                  Hack : just find a sum of first window.
                         when you move the window just remove last element from sum and add right element.
                         this will be sum of new window easy.
                   T = k(First window sum) + n(for each window move)* 1(sum by add/remove) = o(n).
     */
    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;
        /* Step 0 : variables analogy for window :
                    a. 2 for windows -l,r to monitor end points of window.
                    b. 'ws' will store sum of current window, to find next window sum we will remove+add elements from it.
                    c. Then we need to monitor maximum sum among all, so will use 'ans' which we will keep updating. */

        /* Step 1 : Fixed K=3 size subArray means : Window size is fixed : In Each iteration it will not change.
                    So First create a window with size k from index 0.
                    and Find sum of the first window. */
        int left = 0;
        int right = k-1; // 2  // 3rd Element index = 2 | 8th Element index = 7th | Kth element index = k-1
        /*  [ [2,  1,  5],  1,  3,  2  ]
               ↑       ↑
             left    right  */
        int windowSum = 0;
        int i = left;
        while(i<=right){
            windowSum+= arr[i];
            i++;
        }
        System.out.println(windowSum);  // [2,  1,  5] => 8


        /*Step 2: We will move using r. also will pause movement using r.
                  Now Without Changing size of window, slide window 1 by 1 and find windowSum for all windows.
                  biggest sum among all windowSum will be our ANSWER.
                  But How -> a. simple Remove 1 element from left side from sum and move on and
                             b. add 1 element from right side
                             Update answer only when you get bigger sum*/
        int finalAns = windowSum;
        while( right < arr.length -1 /* At last write this : We are already reaching last element in 2nd last itr.*/){
            // Core formula: newSum = oldSum - leavingElement + enteringElement
            // Here we are moving to next window and finding its sum in same iteration
            // Thats why in 2nd last iteration we covered last element too.
            windowSum-= arr[left]; // remove element from 1st then move on
            left++;

            right++; // move on first then add it on sum
            windowSum+= arr[right];

            finalAns =Math.max(finalAns, windowSum);
        }
        System.out.println(finalAns);


    }
}
/*
Step 1 : Create First window and finding its sum
-------  k = 3

         [2   1   5]  1   3   2    windowSum = 2 + 1 + 5 = 8
          ↑       ↑
         left    right
          0       2                finalAns = 8


Step 2 : Slide Window 1 by 1 and find all sum and biggest will be finalAns amonng all.
--------
        Current window:
        [2   1   5]  1   3   2
        2  [1   5   1]   3   2       a. windowSum = 8 - 2(l) + 1(r) = 7
                                     b. -----> finalAns = max(8, 7) = 8
        New window:
        2  [1   5   1]   3   2

STEP 3: SLIDE WINDOW
============================================================
        Current window:
        [ 2, [1,  5, 1], 3,  2]
        [ 2,  1, [5, 1,  3], 2]    a. windowSum = 7 - 1 + 3 = 9
                                   b. -----> finalAns = max(8, 9) = 9

STEP 4: SLIDE WINDOW
============================================================
        Current window:
        [ 2, 1, [5, 1, 3], 2]
        [ 2, 1, 5, [1, 3, 2]]    a. windowSum = 9 - 5 + 2 = 6
                                 b. ------> finalAns = max(9, 6) =9

STEP 5: SLIDE WINDOW
============================================================
        r is reached last element, so we can not move further also there is no need
        so when r cheched last elemnt we need to stop.
*/

/*
Remember:
a. right moves → element ENTERS.
b. left moves → element LEAVES.
c. Window size remains K.
d. finalAnswer is updated after every window if it finds greater answer.
 */