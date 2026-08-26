public class _1_Maximum_Sum_Of____Fixed_k_Size_Subarray {
    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;


        /* Step 1 : Fixed K=3 size subArray means : Winidow size is fixed : In Each iteration it will not change.
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


        /*Step 2: Now Without Changing size of window, slide window 1 by 1 and find windowSum for all windows.
                  biggest sum among all windowSum will be our ANSWER.
                  But How -> a. simple Remove 1 element from left side from sum and move on and
                             b. add 1 element from right side
                             Update answer only when you get bigger sum
                  Note : TO Pause sliding we will use right Pointer and specific condition*/
        int finalAns = windowSum;
        while( right < arr.length -1 /* At last write this : We are already reaching last element in 2nd last itr.*/){
            // Core formula:
            // newSum = oldSum - leavingElement + enteringElement
            // Here not only we are finding new sum but also moving to next window in same iteration
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

         First window contains exactly 3 elements.
         index:    0   1   2   3   4   5
         arr:     [2   1   5]  1   3   2    windowSum = 2 + 1 + 5
                   ↑       ↑
                 left    right
                   0       2

        finalAns = 8

Step 2 : Slide Window 1 by 1 and find all sum and biggest will be finalAns amonng all.
--------
        Current pointers:
        index:   0   1   2   3   4   5
        arr:    [2   1   5]  1   3   2
                 ↑       ↑
               left    right
                 0       2

        r < l-1 ✓

        Remove arr[left]: windowSum = 8 - 2 = 6
        Move left:  left = 1
        Move right: right = 3
        Add arr[right]: windowSum = 6 + 1 = 7           windowSum = 7
                                                        finalAns = max(8, 7) = 8

        New window:
        index:   0   1   2   3    4   5
        arr:     2  [1   5   1]   3   2
                     ↑       ↑
                   left     right
                     1       3

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
*/

/*
Remember:
a. right moves → element ENTERS.
b. left moves → element LEAVES.
c. Window size remains K.
d. finalAnswer is updated after every window if it finds greater answer.
 */