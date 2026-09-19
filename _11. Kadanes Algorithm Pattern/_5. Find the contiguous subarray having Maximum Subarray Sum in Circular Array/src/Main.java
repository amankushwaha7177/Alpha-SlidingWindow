public class Main {
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
        if(maxSum < 0) {
            return maxSum;
        }

        int circularMax = totalSum - minSum;

        return Math.max(maxSum, circularMax);
    }
}

/*
Normal Maximum:
maxSum = Kadane Maximum

Circular Maximum:
circularMax = totalSum - Kadane minSum

Final:
answer = max(maxSum, circularMax)
 */


/*
[8, -1, 3, 4]

totalSum = 14
minSum   = -1

circularMax = 14 - (-1)
            = 15

maxSum = 14

answer = 15
 */