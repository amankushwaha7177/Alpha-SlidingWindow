/*
arr = [2, 1, 5, 1, 3, 2]
k = 3

Windows:

[ [2, 1, 5], 1, 3, 2]  → 8
[ 2, [1, 5, 1], 3, 2]  → 7
[ 2, 1, [5, 1, 3], 2]  → 9
[ 2, 1, 5, [1, 3, 2] ] → 6

Answer = 9

Simple na why calculating all sum individually just deduct from left and add from right and move on.
That is the benifit of 2 pointer approach.
============================================================
*/

public class Main {
    public static void main(String[] args) {
        /*
        1. Core formula:
        newSum = oldSum - leavingElement + enteringElement

        2. Stopping Condition : right + 1 < arr.length  or   right  < arr.length -1
                                We continue while another element can enter:

        ============================================================
        8️⃣ Complexity

        First window: O(K)
        Remaining elements: O(N)
        Overall: O(N)
        Extra space: O(1)
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