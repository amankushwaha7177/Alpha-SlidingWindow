public class Position {

    public static void main(String[] args) {
        int[] arr = {-2, 3, -1, 2};

        int ans = findMaximumSubarraySum(arr);

        System.out.println("Maximum Subarray Sum = " + ans);
    }

    static int findMaximumSubarraySum(int[] arr) {
        int currentSum = arr[0];
        int ans = arr[0];

        int start = 0;
        int bestStart = 0;
        int bestEnd = 0;

        for(int i = 1; i < arr.length; i++) {
            int withPrev = currentSum + arr[i];
            int withOutPrev = arr[i];

            if(withOutPrev > withPrev) {
                currentSum = withOutPrev;
                start = i; // new start only when you start a new subarray after
                           // discarding previous subarray
            } else {
                currentSum = withPrev;
            }

            if(currentSum > ans) {
                ans = currentSum;
                bestStart = start;  // update both real ends when you get best answer.
                bestEnd = i;
            }
        }

        System.out.print("Maximum Subarray = [");

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
Q. Given an integer array, find the contiguous subarray having the maximum sum
   and print the actual subarray along with its maximum sum.

Logic Behind Printing Subarray:

We already calculate the maximum sum using Kadane's Algorithm.

Now we additionally track the indexes of the subarray producing that maximum sum.
*/

/*
Example:

arr = {-2, 3, -1, 2}
start =0
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
        Choose Maximum → 3 ✓
                       → New Subarray starts at [ 3
                       → start = 1
                       → currentSum = 3

Since 3 is the maximum sum so far:

bestStart = 1
bestEnd = 1


Step 2:
i = 2

               -1
                |
        ┌───────┴───────┐
        ▼               ▼
    Continue           Start Fresh
    3 + (-1) = 2          -1
        |                   |
        └────────┬──────────┘
                 ▼
        Choose Maximum → 2 ✓
                       → Continue Previous Subarray [ 3, -1
                       → currentSum = 2

No new maximum:

bestStart = 1
bestEnd = 1


Step 3:
i = 3

                2
                |
        ┌───────┴───────┐
        ▼               ▼
    Continue           Start Fresh
    2 + 2 = 4              2
        |                   |
        └────────┬──────────┘
                 ▼
        Choose Maximum → 4 ✓
                       → Continue Previous Subarray [ 3, -1, 2 ]
                       → currentSum = 4

Since 4 is the maximum sum:

bestStart = start = 1
bestEnd = i = 3


Therefore:

Maximum Subarray = [3, -1, 2]
Maximum Sum = 4


Important:

start
→ Tracks where the CURRENT subarray starts.

bestStart
→ Remembers where the BEST subarray starts.

bestEnd
→ Remembers where the BEST subarray ends.

Kadane finds the maximum sum.

start, bestStart and bestEnd allow us to remember
which exact indexes produced that maximum sum.
*/