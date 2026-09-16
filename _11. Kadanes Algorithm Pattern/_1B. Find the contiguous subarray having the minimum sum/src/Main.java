public class Main {

    public static void main(String[] args) {
        int[] arr = {-2, 3, -1, 2};

        int minSum = findMinimumSubarraySum(arr);

        System.out.println("Minimum Subarray Sum = " + minSum);

        /*
        Time = O(n)
        Reason : We traverse the array only once while maintaining the current and minimum sums.

        Space = O(1)
        Reason : Only currentSum and minSum variables are used without storing the subarray.
        */
    }

    static int findMinimumSubarraySum(int[] arr) {
        int currentSum = arr[0];
        int minSum = arr[0];

        for(int i = 1; i < arr.length; i++) {
            int withPrev = currentSum + arr[i];
            int withOutPrev = arr[i];

            currentSum = Math.min(withPrev, withOutPrev);
            minSum = Math.min(minSum, currentSum);
        }

        return minSum;
    }
}

/*
Q. Given an integer array, find the contiguous subarray having the minimum sum.

Brain:

At every element, decide whether to continue the previous subarray
or throw away the previous sum and start a new subarray from current element.

currentSum = Math.min(withPrev, withOutPrev);

Then keep the smallest sum found so far:

minSum = Math.min(minSum, currentSum);

*/


/*
Intuition : Every element gives us TWO arrows.

Example:
arr = {-2, 3, -1, 2}

For every arr[i], we ask:

                    arr[i]
                       |
          ┌────────────┴────────────┐
          │                         │
          ▼                         ▼
  Continue Previous subarray     Start Fresh subarray

   withPrev                        withOutPrev
   currentSum + arr[i]               arr[i]

We simply choose the smaller arrow.

Step 1 : i = 1
                3
                |
        ┌───────┴───────┐
        ▼               ▼
    Continue          Start
    -2 + 3 = 1        3

    Choose Minimum → 1 ✓  -> currentSum = 1
                             ans = no update -2


Step 2 : i = 2
               -1
                |
        ┌───────┴───────┐
        ▼               ▼
    Continue          Start
    1 + (-1) = 0      -1

    Choose Minimum → -1 ✓  -> currentSum = -1
                               ans = -2


Step 3 : i = 3
                2
                |
        ┌───────┴───────┐
        ▼               ▼
    Continue          Start
    -1 + 2 = 1         2

    Choose Minimum → 1 ✓  -> currentSum = 1
                              ans = -2


Minimum Subarray:

[-2]

Sum:

-2

Answer = -2
*/