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
If all elements are negative:

arr = {-3, -2, -5}

totalSum = -10
minSum   = -10

circularMax = totalSum - minSum
            =    -10   - (-10)
            = 0

But 0 means choosing an empty subarray,
which is not allowed.

So we return maxSum directly.

maxSum = -2

answer = -2
*/


/*
Exactly — mathematically, 0 > -2, but 0 is not a valid subarray sum here
because getting 0 means we selected no elements.
Here in circular array if all elements are negative minSum will occupy whole array.
So when we will do total -minSum it will give 0.

For: arr = {-3, -2, -5}

The valid subarrays are:

[-3]       = -3
[-2]       = -2  ← maximum
[-5]       = -5
[-3,-2]    = -5
[-2,-5]    = -7
[-3,-2,-5] = -10

So the correct answer is: maxSum = -2


Why does totalSum - minSum give 0?

    totalSum = -10
    minSum   = -10

    totalSum - minSum
    = -10 - (-10)
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

Therefore:

if(maxSum < 0) {
    return maxSum;
}

means:

If every element is negative, the best valid subarray is simply
the largest single negative element.


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