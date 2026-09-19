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
        because totalSum and minSum will be same.
        0 - which represents an empty subarray and is not allowed.
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
circularMax = totalSum - minSum
            =     14   - (-1)        [ 8, 3, 4 ]
            = 15


maxSum = 14

answer = 15
 */



/*
If all elements are negative: arr = {-3, -2, -5}
-----------------------------
        All elements are negative
                ↓
         definetly maxSum < 0
                ↓
        Circular calculation would select empty array in future in anyway
                ↓
        so Return normal Kadane maximum


totalSum = -10
minSum   = -10

circularMax = totalSum - minSum
            =    -10   - (-10)
            = 0

        We are effectively doing:

        [-3, -2, -5]
               ↓
        remove the entire array
               ↓
        nothing remains
               ↓
        sum = 0

        But an empty subarray is not allowed.


But 0 means choosing an empty subarray, which is not allowed.
ans = max(cicularMax , maxSUm)
    = (0, -2) = 0 -----------------> Incorrect

So we return maxSum directly.

maxSum = -2

answer = -2
*/


/*
Q. But 0 is bigger than -2 ? why not choosing 0 ?
A. Exactly — mathematically, 0 > -2, but 0 is not a valid subarray sum here
   because no sunbarray will give 0 if all elements are negative, sum will alsways -ve

   So if maxSum < 0
   return this maxSum

   Because Anyway further calcuation of curcular maxSum on all negative number will give 0.

    For: arr = {-3, -2, -5}
    The valid subarrays are:

    [-3]       = -3
    [-2]       = -2  ← maximum
    [-5]       = -5
    [-3,-2]    = -5
    [-2,-5]    = -7
    [-3,-2,-5] = -10

    So the correct answer is: maxSum = -2

        Therefore:

        if(maxSum < 0) {
            return maxSum;
        }

        means:

        If every element is negative, the best valid subarray is simply
        the largest single negative element.
*/



