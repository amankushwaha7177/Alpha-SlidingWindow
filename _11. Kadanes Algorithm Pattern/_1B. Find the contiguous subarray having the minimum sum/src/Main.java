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


Example:

arr = {-2, 3, -1, 2}

Step 1:
    currentSum = -2
    minSum = -2

Step 2: arr[i] = 3
    withPrev = -2 + 3 = 1
    withOutPrev = 3

    currentSum = min(1, 3) = 1
    minSum = min(-2, 1) = -2

Step 3: arr[i] = -1
    withPrev = 1 + (-1) = 0
    withOutPrev = -1

    currentSum = min(0, -1) = -1
    minSum = min(-2, -1) = -2

Step 4: arr[i] = 2
    withPrev = -1 + 2 = 1
    withOutPrev = 2

    currentSum = min(1, 2) = 1
    minSum = min(-2, 1) = -2

Minimum Subarray:

[-2]

Sum:

-2

Answer = -2

*/