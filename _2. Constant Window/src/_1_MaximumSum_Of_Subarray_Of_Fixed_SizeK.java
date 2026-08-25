/*
============================================================
File: _3_MaximumSumSubarrayOfSizeK.java
Topic: Maximum Sum Subarray of Size K

Problem:
Find the maximum sum among all contiguous subarrays
having exactly K elements.

Example:

arr = [2, 1, 5, 1, 3, 2]
k = 3

Windows:

[ [2, 1, 5], 1, 3, 2]  → 8
[ 2, [1, 5, 1], 3, 2]  → 7
[ 2, 1, [5, 1, 3], 2]  → 9
[ 2, 1, 5, [1, 3, 2] ] → 6

Answer = 9
============================================================
*/

public class _1_MaximumSum_Of_Subarray_Of_Fixed_SizeK {

    public static void main(String[] args) {

        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;

        /*
        ============================================================
        1️⃣ Initialize First Window
        ============================================================

        For a window of size K:

        left  = 0
        right = k - 1

        Here:

        left  = 0
        right = 2


        Pointer positions:

        index:   0   1   2   3   4   5
        arr:    [2   1   5   1   3   2]
                 ↑       ↑
               left    right
                 0       2


        Active window:

        [ [2, 1, 5], 1, 3, 2]
          ↑       ↑
        left    right
        ============================================================
        */

        int left = 0;
        int right = k - 1;

        int windowSum = 0;

        for (int i = left; i <= right; i++) {
            windowSum += arr[i];
        }

        int maxSum = windowSum;

        /*
        ============================================================
        2️⃣ First Window

        [ [2, 1, 5], 1, 3, 2]
          ↑       ↑
        left    right

        Sum:

        2 + 1 + 5 = 8

        maxSum = 8
        ============================================================
        */


        /*
        ============================================================
        3️⃣ Slide Window

        Current:

        [ [2, 1, 5], 1, 3, 2]
          ↑       ↑
        left    right
          0       2


        Move right:

        right = 3

        arr[3] = 1 ENTERS.


        Temporary:

        [ [2, 1, 5, 1], 3, 2]
          ↑           ↑
        left        right
          0           3


        Now remove the element at left:

        arr[0] = 2 LEAVES.


        Move left:

        left = 1


        New window:

        [ 2, [1, 5, 1], 3, 2]
             ↑       ↑
           left    right
             1       3


        New sum:

        8 - 2 + 1 = 7


        maxSum:

        max(8, 7) = 8
        ============================================================
        */


        while (right  < arr.length - 1) {

            right++;

            windowSum += arr[right];

            windowSum -= arr[left];

            left++;

            maxSum = Math.max(maxSum, windowSum);
        }


        /*
        ============================================================
        4️⃣ Understand Every Window

        WINDOW 1

        [ [2, 1, 5], 1, 3, 2]
          ↑       ↑
        left    right

        Sum = 8


        WINDOW 2

        [ 2, [1, 5, 1], 3, 2]
             ↑       ↑
           left    right

        2 LEAVES.
        1 ENTERS.

        Sum = 7


        WINDOW 3

        [ 2, 1, [5, 1, 3], 2]
                ↑       ↑
              left    right

        1 LEAVES.
        3 ENTERS.

        Sum = 9


        WINDOW 4

        [ 2, 1, 5, [1, 3, 2]]
                   ↑       ↑
                 left    right

        5 LEAVES.
        2 ENTERS.

        Sum = 6


        Maximum:

        max(8, 7, 9, 6)

        = 9
        ============================================================
        */


        System.out.println("Maximum Sum = " + maxSum);


        /*
        ============================================================
        5️⃣ Why Do We Add and Remove?

        Instead of calculating every window from scratch:


        Window 1:

        2 + 1 + 5 = 8


        Window 2:

        [ 2, [1, 5, 1], 3, 2]

        Previous sum = 8

        2 LEAVES
        1 ENTERS

        8 - 2 + 1 = 7


        Window 3:

        1 LEAVES
        3 ENTERS

        7 - 1 + 3 = 9


        Window 4:

        5 LEAVES
        2 ENTERS

        9 - 5 + 2 = 6


        Core formula:

        newSum = oldSum - leavingElement + enteringElement
        ============================================================
        */


        /*
        ============================================================
        6️⃣ Stopping Condition

        We continue while another element can enter:

        right + 1 < arr.length


        Last window:

        [ 2, 1, 5, [1, 3, 2]]
                   ↑       ↑
                 left    right
                   3       5


        right = 5

        arr.length = 6


        Next right:

        6

        Index 6 does not exist.

        Therefore the window stops.
        ============================================================
        */


        /*
        ============================================================
        7️⃣ Complete Algorithm

        Step 1:
        Set left = 0.

        Step 2:
        Set right = k - 1.

        Step 3:
        Calculate the first window sum.

        Step 4:
        Store it in maxSum.

        Step 5:
        Move right forward.

        Step 6:
        Add the entering element.

        Step 7:
        Remove the leaving element.

        Step 8:
        Move left forward.

        Step 9:
        Update maxSum.

        Step 10:
        Continue until right reaches the end.
        ============================================================
        */


        /*
        ============================================================
        8️⃣ Complexity

        First window:

        O(K)

        Remaining elements:

        O(N)

        Overall:

        O(N)

        Extra space:

        O(1)
        ============================================================
        */


        /*
        ============================================================
        ⭐ Interview Pattern

        Fixed Window of Size K:

        int left = 0;
        int right = k - 1;

        Calculate first window.

        int maxSum = windowSum;

        while (right + 1 < arr.length) {

            right++;

            windowSum += arr[right];

            windowSum -= arr[left];

            left++;

            maxSum = Math.max(maxSum, windowSum);
        }


        Remember:

        right moves → element ENTERS.

        left moves → element LEAVES.

        Window size remains K.

        Answer is updated after every window.
        ============================================================
        */
    }
}