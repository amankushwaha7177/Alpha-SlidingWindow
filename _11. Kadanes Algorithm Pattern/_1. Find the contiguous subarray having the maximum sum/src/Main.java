public class Main {

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        int maxSum = findMaximumSubarraySum(arr);

        System.out.println("Maximum Subarray Sum = " + maxSum);

        /*
        Time = O(n)
        Reason : We traverse the array only once while maintaining the current and maximum sums.

        Space = O(1)
        Reason : Only currentSum and maxSum variables are used without storing the subarray.
        */
    }

    static int findMaximumSubarraySum(int[] arr) {
        int currentSum = arr[0];
        int maxSum = arr[0];

        for(int i = 1; i < arr.length; i++) {
            int withPrev = currentSum + arr[i];
            int withOutPrev = arr[i];

            currentSum = Math.max(withPrev, withOutPrev);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}

/*
Q. Given an integer array, find the contiguous subarray having the maximum sum.

Brain:

At every element, decide whether to continue the previous subarray
or throw away the previous sum and start a new subarray from current element.

currentSum = Math.max(withPrev, withOutPrev);

Then keep the best sum found so far:

maxSum = Math.max(maxSum, currentSum);


Example:

arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4}

Maximum Subarray:

[4, -1, 2, 1]

Sum:

4 + (-1) + 2 + 1 = 6

Answer = 6
*/