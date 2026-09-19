public class Main2 {
    public static void main(String[] args) {
        int[] arr = {8, -1, 3, 4};

        int ans = maxSubarraySumCircular(arr);

        System.out.println("Maximum Circular Subarray Sum = " + ans);
    }

    static int maxSubarraySumCircular(int[] arr) {
        int totalSum = 0;

        int currentMax = arr[0];
        int maxSum = arr[0];

        int currentMin = arr[0];
        int minSum = arr[0];

        for(int i = 0; i < arr.length; i++) {
            totalSum += arr[i];

            if(i == 0) {
                continue;
            }

            currentMax = Math.max(arr[i], currentMax + arr[i]);
            maxSum = Math.max(maxSum, currentMax);

            currentMin = Math.min(arr[i], currentMin + arr[i]);
            minSum = Math.min(minSum, currentMin);
        }

        /*
        If all elements are negative, totalSum - minSum becomes zero,
        which represents an empty subarray and is not allowed.
        */

        int circularMax = totalSum - minSum;

        if(circularMax == 0){ // means circularMax removed whole array and its {} empty array.
            return maxSum;
        }

        return Math.max(maxSum, circularMax);
    }
}

/*
        Mental Rule:

        All Negative
             ↓
        Circular formula gives 0
             ↓
          0 = Empty Subarray ❌
             ↓
        Return normal Kadane maximum

        So for {-3,-2,-5}:

        normalMax = -2
        circularMax = 0 ❌

        Answer = -2
*/