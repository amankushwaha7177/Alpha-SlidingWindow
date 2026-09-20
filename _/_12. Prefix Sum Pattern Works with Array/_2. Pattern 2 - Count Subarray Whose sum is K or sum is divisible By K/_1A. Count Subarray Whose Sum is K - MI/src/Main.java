import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 2};
        int k = 3;

        int ans = countSubarrays(arr, k);

        System.out.println("Count = " + ans);
    }

    static int countSubarrays(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        /*
        The map stores previous prefix sums and their frequencies.
        We start with prefix sum 0 appearing once because nothing exists before index 0.
        */
        map.put(0, 1);

        int prefix = 0;
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            prefix += arr[i];

            /*
            Current Prefix - Previous Prefix = Subarray Sum.
            We want the subarray sum to equal K, so Previous Prefix = Current Prefix - K.

            Ex : [--------------------------- Subarray whose sum is 5------------------------------] = k(3)
                 [--- Subarray whose sum is 2---]          [-------- Subarray whose sum is 3-------]
                  If it exist It Exists                     Then It will definetly Exist.



            */
            int requiredPrefix = prefix - k;

            /*
            Every occurrence of the required previous prefix creates one valid subarray.
            */
            if (map.containsKey(requiredPrefix)) {
                count += map.get(requiredPrefix);
            }

            /*
            Store the current prefix so future elements can use it as a previous prefix.
            */
            map.put(prefix, map.getOrDefault(prefix, 0) + 1);
        }

        return count;
    }
}


/*
arr = [1, 2, 1, 2]
K = 3

Start:

prefix = 0
count = 0
map = {0=1}


Step 1: Take 1   |   arr = [1, 2, 1, 2]

        prefix = 1
        requiredPrefix = prefix - K
                       = 1 - 3
                       = -2

        -2 is not in map.
        => so No valid subarray till [ 1    that can form sum = 3

        Store prefix 1.
        map = {0=1, 1=1}
        count = 0


Step 2: Take 2

        prefix = 3
        requiredPrefix = prefix - K
                       = 3 - 3
                       = 0

        0 exists in map (Previous prefix = 0, Current prefix = 3 )
        means currentPrefix - prevPrefix = k
                          3 - 0 = 3

        So:
        [1,2] = 3 ✓

        count = 1

        Store prefix 3.
        map = {0=1, 1=1, 3=1}


Step 3: Take 1

        prefix = 4
        requiredPrefix = 4 - 3
                       = 1

        1 exists in map. ( Previous prefix = 1, Current prefix = 4 )
        means currentPrefix - prevPrefix = k
                          4 - 1 = 3

        Previous prefix 1 represents [1].
        Current  prefix 4 represents [1,2,1].

        Remove the previous [1]:

        [1,2,1]
         ↓
        remove [1]
         ↓
        [2,1]

        So:

        [2,1] = 3 ✓

        count = 2

        Store prefix 4.
        map = {0=1, 1=1, 3=1, 4=1}


Step 4: Take 2

        prefix = 6
        requiredPrefix = 6 - 3
                       = 3

        3 exists in map.( Previous prefix = 3, Current prefix = 6 )
        means currentPrefix - prevPrefix = k
                          6 - 3 = 3

        Previous prefix 3 represents [1,2]
        Current prefix 6 represents [1,2,1,2]

        Remove the previous [1,2]:

        [1,2,1,2]
           ↓
        remove [1,2]
           ↓
        [1,2]

        So:

        [1,2] = 3 ✓

        count = 3

        Store prefix 6.
        map = {0=1, 1=1, 3=1, 4=1, 6=1}


Final Answer:
count = 3


Mental Rule:

Current Prefix
      ↓
How much do I need to remove?
      ↓
Current Prefix - K
      ↓
Search this value in HashMap
      ↓
Found? Okay Cool this found value we have to remove
      ↓
Yes → A subarray with sum K exists.


The easiest sentence to remember:

"Find the old prefix that, when removed from my current prefix, leaves K."
*/
