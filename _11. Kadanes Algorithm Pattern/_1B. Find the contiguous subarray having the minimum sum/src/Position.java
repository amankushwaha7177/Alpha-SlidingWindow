public class Position {

    public static void main(String[] args) {
        int[] arr = {-2, 3, -1, 2};

        int ans = findMinimumSubarraySum(arr);

        System.out.println("Minimum Subarray Sum = " + ans);
    }

    static int findMinimumSubarraySum(int[] arr) {
        int currentSum = arr[0];
        int ans = arr[0];

        int start = 0;
        int bestStart = 0;
        int bestEnd = 0;

        for(int i = 1; i < arr.length; i++) {
            int withPrev = currentSum + arr[i];
            int withOutPrev = arr[i];

            if(withPrev > withOutPrev) {
                currentSum = withOutPrev;
                start = i;  // if previous subarray is making bigger sum
                            // discarding previous subarray
                            // start new subarray.
            }
            else {         // if previous subarray is making smaller sum
                           // continue with it
                currentSum = withPrev;
            }

            if(currentSum < ans) {
                ans = currentSum;
                bestStart = start;  // update both real ends only when you get best answer.
                bestEnd = i;
            }
        }

        System.out.print("Minimum Subarray = [");

        for(int i = bestStart; i <= bestEnd; i++) {
            System.out.print(arr[i]);

            if(i < bestEnd) {
                System.out.print(", ");
            }
        }

        System.out.println("]");

        return ans;
    }
}

/*
Q. Given an integer array, find the contiguous subarray having the minimum sum
   and print the actual subarray along with its minimum sum.

Logic Behind Printing Subarray:

We already calculate the minimum sum using Kadane's Algorithm.

Now we additionally track the indexes of the subarray producing that minimum sum.
*/

/*
Example:

arr = {-2, 3, -1, 2}
start = 0
bestStart = 0
bestEnd = 0

Step 1:
i = 1

                3
                |
        ┌───────┴───────┐
        ▼               ▼
    Continue           Start Fresh
    -2 + 3 = 1             3
        |                   |
        └────────┬──────────┘
                 ▼
        Choose Minimum → 1 ✓
                       → Continue Previous Subarray [ -2, 3
                       → currentSum = 1

No new minimum:

bestStart = 0
bestEnd = 0


Step 2:
i = 2

               -1
                |
        ┌───────┴───────┐
        ▼               ▼
    Continue           Start Fresh
    1 + (-1) = 0          -1
        |                   |
        └────────┬──────────┘
                 ▼
        Choose Minimum → -1 ✓
                       → Start Fresh Subarray [ -1
                       → start = 2
                       → currentSum = -1

No new minimum:

bestStart = 0
bestEnd = 0


Step 3:
i = 3

                2
                |
        ┌───────┴───────┐
        ▼               ▼
    Continue           Start Fresh
    -1 + 2 = 1             2
        |                   |
        └────────┬──────────┘
                 ▼
        Choose Minimum → -1 ✓
                       → Start Fresh is not selected
                       → Continue Previous Subarray [ -1, 2 ]
                       → currentSum = 1

No new minimum:

bestStart = 0
bestEnd = 0


Therefore:

Minimum Subarray = [-2]
Minimum Sum = -2


Important:

start
→ Tracks where the CURRENT subarray starts.

bestStart
→ Remembers where the BEST subarray starts.

bestEnd
→ Remembers where the BEST subarray ends.

Kadane finds the minimum sum.

start, bestStart and bestEnd allow us to remember
which exact indexes produced that minimum sum.
*/