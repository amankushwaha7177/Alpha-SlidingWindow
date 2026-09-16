public class Main {

    public static void main(String[] args) {
        int[] arr = {-2, 3, -1, 2};

        int ans = findMaximumSubarraySum(arr);

        System.out.println("Maximum Subarray Sum = " + ans);

        /*
        Time = O(n)
        Reason : We traverse the array only once while maintaining the current and maximum sums.

        Space = O(1)
        Reason : Only currentSum and ans variables are used without storing the subarray.
        */
    }

    static int findMaximumSubarraySum(int[] arr) {
        int currentSum = arr[0];
        int ans = arr[0];

        for(int i = 1; i < arr.length; i++) {
            int withPrev = currentSum + arr[i];
            int withOutPrev = arr[i];

            currentSum = Math.max(withPrev, withOutPrev);
            ans = Math.max(ans, currentSum);
        }

        return ans;
    }
}

/*
Q. Given an integer array, find the contiguous subarray having the maximum sum.

Brain:

At every element, decide whether to continue the previous subarray
or throw away the previous sum and start a new subarray from current element.

currentSum = Math.max(withPrev, withOutPrev);

Then keep the best sum found so far:

ans = Math.max(ans, currentSum);

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

We simply choose the better arrow.

Step 1 : i = 1
                3
                |
        ┌───────┴───────┐
        ▼               ▼
    Continue               Start
    -2 + 3 = 1             3                  Choose Maximum → 3 ✓  -> currentSum = 3
                                              ans = 3

Step 2 : i = 2
               -1
                |
        ┌───────┴───────┐
        ▼               ▼
    Continue               Start
    3 + (-1) = 2           -1                Choose Maximum → 2 ✓  -> currentSum = 2
                                             ans = no update 3

Step 3 : i = 3
                2
                |
        ┌───────┴───────┐
        ▼               ▼
    Continue               Start
    2 + 2 = 4              2                Choose Maximum → 4 ✓  -> currentSum = 4
                                            ans = 4


*/

/*
Mental Rule:

Every element has TWO choices:

        Continue Previous sunarray
                OR
          Start Fresh subarray


For Maximum Sum:   currentSum = Math.max(withPrev, withOutPrev)
For Minimum Sum:   currentSum = Math.min(withPrev, withOutPrev)

Kadane = At every element, choose the better arrow.
*/