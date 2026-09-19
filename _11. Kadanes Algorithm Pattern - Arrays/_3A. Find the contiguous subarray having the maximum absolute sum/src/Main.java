public class Main {
    public static void main(String[] args) {
        int[] arr = {1, -3, 2, 3, -4};

        int ans = maxAbsoluteSum(arr);

        System.out.println("Maximum Absolute Subarray Sum = " + ans);
    }

    static int maxAbsoluteSum(int[] arr) {
        int maxSum = arr[0];
        int minSum = arr[0];

        int currentMax = arr[0];
        int currentMin = arr[0];

        for(int i = 1; i < arr.length; i++) {

            currentMax = Math.max(arr[i], currentMax + arr[i]);
            maxSum = Math.max(maxSum, currentMax);

            currentMin = Math.min(arr[i], currentMin + arr[i]);
            minSum = Math.min(minSum, currentMin);
        }

        return Math.max(Math.abs(maxSum), Math.abs(minSum));
    }
}

/*
Maximum Absolute Sum of Any Subarray :
-------------------------------------
The goal is to find the maximum absolute value of the sum of any contiguous subarray.

For every subarray sum, we need to consider both:
a. Positive maximum sum
b. Negative minimum sum

Because:
absoluteSum = |subarraySum|

Ex : minSum =-25 = 25
     maxSum = 12 = 12   ==> Here 25 will be answer

So we run Kadane twice:
a. Find maximum subarray sum.
b. Find minimum subarray sum.
   Return max(abs(maxSum), abs(minSum)).
 */


/*
Intuition : Every element gives us TWO arrows.

Example:
arr = {1, -3, 2, 3, -4}

For every arr[i], we ask:

                    arr[i]
                       |
          ┌────────────┴────────────┐
          │                         │
          ▼                         ▼
  Continue Previous subarray     Start Fresh subarray

   withPrev                        withOutPrev
   currentSum + arr[i]               arr[i]

For Maximum Sum:
Choose the larger arrow.

For Minimum Sum:
Choose the smaller arrow.

We need BOTH because the largest absolute sum
can come from either a very positive sum or a very negative sum.


Step 1 : i = 1

               -3
                |
        ┌───────┴───────┐
        ▼               ▼
    Continue           Start
    1 + (-3) = -2        -3
        |                   |
        └────────┬──────────┘
                 ▼
        Choose Maximum → -2 ✓
                       → Continue [1, -3]
                       → currentMax = -2
                       → maxSum = 1

        Choose Minimum → -3 ✓
                       → New Subarray starts at [ -3
                       → currentMin = -3
                       → minSum = -3


Step 2 : i = 2

                2
                |
        ┌───────┴───────┐
        ▼               ▼
    Continue           Start
    -2 + 2 = 0            2
        |                   |
        └────────┬──────────┘
                 ▼
        Choose Maximum → 2 ✓
                       → New Subarray starts at [ 2
                       → currentMax = 2
                       → maxSum = 2

        Choose Minimum → 0 ✓
                       → Continue [-3, 2]
                       → currentMin = 0
                       → minSum = -3


Step 3 : i = 3

                3
                |
        ┌───────┴───────┐
        ▼               ▼
    Continue           Start
    2 + 3 = 5             3
        |                   |
        └────────┬──────────┘
                 ▼
        Choose Maximum → 5 ✓
                       → Continue [2, 3]
                       → currentMax = 5
                       → maxSum = 5

        Choose Minimum → 3 ✓
                       → Start Fresh [3]
                       → currentMin = 3
                       → minSum = -3


Step 4 : i = 4

               -4
                |
        ┌───────┴───────┐
        ▼               ▼
    Continue           Start
    5 + (-4) = 1         -4
        |                   |
        └────────┬──────────┘
                 ▼
        Choose Maximum → 1 ✓
                       → Continue [2, 3, -4]
                       → currentMax = 1
                       → maxSum = 5

        Choose Minimum → -4 ✓
                       → New Subarray starts at [ -4
                       → currentMin = -4
                       → minSum = -4


Final:

maxSum = 5
minSum = -4

|maxSum| = |5| = 5
|minSum| = |-4| = 4

Maximum Absolute Sum = 5
*/


/*
Mental Rule:

Every element has TWO choices:

        Continue Previous subarray
                OR
          Start Fresh subarray


For Maximum Sum:

currentMax = Math.max(withPrev, withOutPrev)


For Minimum Sum:

currentMin = Math.min(withPrev, withOutPrev)


For Maximum Absolute Sum:

1. Find Maximum Subarray Sum.
2. Find Minimum Subarray Sum.
3. Take absolute value of both.
4. Choose the larger absolute value.

Maximum Absolute Sum
        =
max(|maximumSum|, |minimumSum|)


Kadane = At every element, choose the better arrow.
*/