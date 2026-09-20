import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        int[] arr = {4, 5, 0, -2, -3, 1};
        int k = 5;

        int ans = countSubarrays(arr, k);

        System.out.println("Count = " + ans);
    }

    static int countSubarrays(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        /*
        The map stores previous prefix remainders and their frequencies.
        We start with remainder 0 appearing once because before the array starts, sum is 0.
        */
        map.put(0, 1);

        int prefix = 0;
        int count = 0;

        for(int i = 0; i < arr.length; i++) {
            prefix += arr[i];

            /*
            If two prefix sums have the same remainder after division by K,
            their difference is always divisible by K.

            Example:

            Current Prefix = 7
            Previous Prefix = 2
            K = 5

            7 % 5 = 2
            2 % 5 = 2

            Same remainder means:

            7 - 2 = 5

            And 5 % 5 = 0.

            Therefore, the subarray between these two prefix sums
            has a sum that is divisible by K.
            */

            int remainder = prefix % k;

            /*
            Java can produce a negative remainder for negative prefix sums.
            Convert it into the normal positive range [0, K-1].
            */
            if(remainder < 0) {
                remainder += k;
            }

            /*
            Every previous occurrence of the same remainder creates
            one subarray whose sum is divisible by K.
            */
            if(map.containsKey(remainder)) {
                count += map.get(remainder);
            }

            /*
            Store the current remainder so future prefix sums can use it.
            */
            map.put(remainder, map.getOrDefault(remainder, 0) + 1);
        }

        return count;
    }
}


/*
Complete Dry Run

arr = [4, 5, 0, -2, -3, 1]
K = 5

Start:
prefix = 0
count = 0
map = {0=1}


Step 1: Take 4

        prefix = 4
        remainder = 4 % 5
                 = 4

        4 is not in map.
        No valid subarray yet.

        Store remainder 4.
        map = {0=1, 4=1}
        count = 0


Step 2: Take 5

        prefix = 9
        remainder = 9 % 5
                 = 4

        4 already exists.

        Previous remainder = 4
        Current remainder = 4

        Same remainder means their difference is divisible by 5.

        9 - 4 = 5

        So:

        [5] = 5 ✓

        count = 1

        Store remainder 4 again.

        map = {0=1, 4=2}


Step 3: Take 0

prefix = 9

remainder = 9 % 5
         = 4

4 already exists twice.

So we get 2 new valid subarrays.

They are:

[5,0] = 5 ✓
[0]   = 0 ✓

count = 3

Store remainder 4 again.

map = {0=1, 4=3}


Step 4: Take -2

prefix = 7

remainder = 7 % 5
         = 2

2 is not in map.

No new subarray.

Store remainder 2.

map = {0=1, 4=3, 2=1}
count = 3


Step 5: Take -3

prefix = 4

remainder = 4 % 5
         = 4

4 already exists three times.

So 3 new valid subarrays are found.

count = 6

Store remainder 4.

map = {0=1, 4=4, 2=1}


Step 6: Take 1

prefix = 5

remainder = 5 % 5
         = 0

0 already exists.

So:

5 - 0 = 5

The complete subarray:

[4,5,0,-2,-3,1] = 5 ✓

count = 7

Store remainder 0.

map = {0=2, 4=4, 2=1}


Final Answer:

count = 7


Mental Rule:

Current Prefix
      ↓
Current Prefix % K
      ↓
Search the SAME remainder in HashMap
      ↓
Same remainder found
      ↓
Their difference is divisible by K
      ↓
A valid subarray exists.


The easiest sentence to remember:

"Same prefix remainder means the difference between them is divisible by K."
*/


/*
Time = O(n)
Space = O(k)
*/