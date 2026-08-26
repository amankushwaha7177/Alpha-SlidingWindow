/*
============================================================
File: _5_LargestSubarrayWithSumAtMostK.java
Topic: Largest Subarray With Sum <= K

Problem:
Find the length of the longest contiguous subarray
whose sum is less than or equal to K.

Example:

arr = [2, 1, 5, 1, 3, 2]
K = 7

Longest valid subarray:

[2, 1, 5] → sum = 8 ❌
[1, 5, 1] → sum = 7 ✓
[5, 1, 3] → sum = 9 ❌
[1, 3, 2] → sum = 6 ✓

Answer = 3

IMPORTANT:
This simple sliding-window approach works when the
array contains NON-NEGATIVE numbers.
============================================================
*/

public class _5_LargestSubarrayWithSumAtMostK {

    public static void main(String[] args) {

        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 7;

        int left = 0;
        int right = 0;

        int windowSum = 0;
        int maxLength = 0;


        /*
        ============================================================
        1️⃣ START

        left and right both start at index 0.

        Pointer diagram:

        index:   0   1   2   3   4   5
        arr:     2   1   5   1   3   2
                 ↑
               L/R

        left  = 0
        right = 0

        Window:

        [ [2], 1, 5, 1, 3, 2]
        ============================================================
        */


        while (right < arr.length) {

            /*
            ========================================================
            2️⃣ RIGHT ENTERS

        Current right points to the element entering
        the window.

        Add that element to windowSum.
            ========================================================
            */

            windowSum += arr[right];


            /*
            ========================================================
            3️⃣ CHECK SUM

        If:

        windowSum <= K

        the current window is valid.

        If:

        windowSum > K

        the current window is invalid.

        We then move LEFT until the window becomes valid.
            ========================================================
            */

            while (windowSum > k) {

                windowSum -= arr[left];

                left++;
            }


            /*
            ========================================================
            4️⃣ CURRENT WINDOW

        After the inner while loop:

        windowSum <= K

        Therefore the current window is valid.

        Current length:

        right - left + 1

        Example:

        index:   0   1   2   3   4   5
        arr:     2   1   5   1   3   2
                 ↑           ↑
                 L           R

        left  = 0
        right = 3

        Current window:

        [2, 1, 5, 1]

        Sum = 9

        Since 9 > 7, LEFT moves until valid.

        After removing 2:

        index:   0   1   2   3   4   5
        arr:     2   1   5   1   3   2
                     ↑       ↑
                     L       R

        left  = 1
        right = 3

        Window:

        [2, [1, 5, 1], 3, 2]

        Sum = 7

        Length:

        3 - 1 + 1 = 3
            ========================================================
            */

            int currentLength = right - left + 1;

            maxLength = Math.max(maxLength, currentLength);


            /*
            ========================================================
            5️⃣ MOVE RIGHT

        right moves to the next index.

        The next iteration will add that new element.

        right++;
            ========================================================
            */

            right++;
        }


        System.out.println("Maximum Length = " + maxLength);


        /*
        ============================================================
        6️⃣ COMPLETE WINDOW MOVEMENT

        We use the following format to show the active window:

        STEP 1

        [ [2], 1, 5, 1, 3, 2]

        sum = 2
        length = 1


        STEP 2

        [ [2, 1], 5, 1, 3, 2]

        sum = 3
        length = 2


        STEP 3

        [ [2, 1, 5], 1, 3, 2]

        sum = 8

        8 > 7

        Remove 2.

        [ 2, [1, 5], 1, 3, 2]

        sum = 6
        length = 2


        STEP 4

        [ 2, [1, 5, 1], 3, 2]

        sum = 7
        length = 3


        STEP 5

        [ 2, [1, 5, 1, 3], 2]

        sum = 10

        10 > 7

        Remove 1:

        sum = 9

        Still > 7.

        Remove 5:

        sum = 4

        New window:

        [ 2, 1, [5, 1, 3], 2]

        length = 3


        STEP 6

        [ 2, 1, [5, 1, 3, 2]]

        sum = 11

        11 > 7

        Remove 5:

        sum = 6

        New valid window:

        [ 2, 1, [5, 1, 3, 2]]

        length = 3


        Final answer:

        3
        ============================================================
        */


        /*
        ============================================================
        ⭐ CORE VARIABLE-SIZE SLIDING WINDOW

        int left = 0;
        int right = 0;

        while (right < arr.length) {

            windowSum += arr[right];

            while (windowSum > k) {

                windowSum -= arr[left];

                left++;
            }

            maxLength = Math.max(
                maxLength,
                right - left + 1
            );

            right++;
        }


        Mental flow:

        RIGHT ENTERS
             ↓
        ADD TO SUM
             ↓
        SUM > K ?
             ↓
        YES → LEFT LEAVES
             ↓
        WINDOW BECOMES VALID
             ↓
        CALCULATE LENGTH
             ↓
        RIGHT++
        ============================================================
        */


        /*
        ============================================================
        ⭐ DIFFERENCE FROM FIXED-SIZE WINDOW

        Fixed-size window:

        Window size is always K.

        Example:

        [ [2, 1, 5], 1, 3, 2]
        [ 2, [1, 5, 1], 3, 2]
        [ 2, 1, [5, 1, 3], 2]


        Variable-size window:

        Window size changes depending on the condition.

        Example:

        [ [2], 1, 5, 1, 3, 2]

        [ [2, 1], 5, 1, 3, 2]

        [ 2, [1, 5], 1, 3, 2]

        [ 2, [1, 5, 1], 3, 2]

        RIGHT expands the window.

        LEFT shrinks the window when the condition breaks.
        ============================================================
        */


        /*
        ============================================================
        ⚠️ IMPORTANT INTERVIEW CONDITION

        This simple sliding-window solution assumes
        all elements are NON-NEGATIVE.

        Why?

        When LEFT moves forward:

        windowSum decreases.

        Therefore, once the sum becomes too large,
        removing elements from the left can make
        the window valid again.

        With negative numbers, this simple behavior
        is not guaranteed.
        ============================================================
        */


        /*
        ============================================================
        COMPLEXITY

        right moves from left to right once.

        left also moves from left to right at most once.

        Time  = O(N)

        Space = O(1)
        ============================================================
        */
    }
}