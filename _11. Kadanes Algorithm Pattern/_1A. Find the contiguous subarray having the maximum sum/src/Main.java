public class Main {

    public static void main(String[] args) {
        int[] arr = {-2, 3, -1, 2};

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

arr = {-2, 3, -1, 2}

Step 1:
    currentSum = -2
    maxSum = -2

Step 2: arr[i] = 3
    withPrev = -2 + 3 = 1
    withOutPrev = 3

    currentSum = max(1, 3) = 3
    maxSum = max(-2, 3) = 3

Step 3: arr[i] = -1
    withPrev = 3 + (-1) = 2
    withOutPrev = -1

    currentSum = max(2, -1) = 2
    maxSum = max(3, 2) = 3

Step 4: arr[i] = 2
    withPrev = 2 + 2 = 4
    withOutPrev = 2

    currentSum = max(4, 2) = 4
    maxSum = max(3, 4) = 4

Maximum Subarray:

[3, -1, 2]

Sum:

3 + (-1) + 2 = 4

Answer = 4*/