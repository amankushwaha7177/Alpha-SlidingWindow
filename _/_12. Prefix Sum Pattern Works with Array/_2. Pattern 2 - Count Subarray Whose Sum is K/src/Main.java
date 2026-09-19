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
Logic:

arr = [1, 2, 1, 2]
k = 3

Start:
prefix = 0
map = {0=1}

The map stores previous prefix sums and their frequencies.


Step 1: Take 1
        prefix = 1

        requiredPrefix = prefix - k
                       = 1 - 3
                       = -2

        -2 is not present in map.

        count = 0

        Store prefix 1.

        map = {0=1, 1=1}


Step 2: Take 2
        prefix = 3

        requiredPrefix = prefix - k
                       = 3 - 3
                       = 0

        0 exists in the map.

        Why does this mean a subarray exists?

        Current Prefix - Previous Prefix = Subarray Sum

        3 - 0 = 3

        The previous prefix 0 was before [1,2].
        Removing that previous part leaves [1,2].

        Therefore [1,2] is a valid subarray.

        count = 1

        Store prefix 3.

        map = {0=1, 1=1, 3=1}


Step 3: Take 1
        prefix = 4

        requiredPrefix = prefix - k
                       = 4 - 3
                       = 1

        1 exists in the map.

        Why does this mean a subarray exists?

        Current Prefix - Previous Prefix = Subarray Sum

        4 - 1 = 3

        The previous prefix 1 represents the sum of [1].
        The current prefix 4 represents the sum of [1,2,1].

        Remove the previous part [1].

        Remaining part = [2,1]

        Sum of [2,1] = 3

        Therefore [2,1] is a valid subarray.

        count = 2

        Store prefix 4.

        map = {0=1, 1=1, 3=1, 4=1}


Step 4: Take 2
        prefix = 6

        requiredPrefix = prefix - k
                       = 6 - 3
                       = 3

        3 exists in the map.

        Why does this mean a subarray exists?

        Current Prefix - Previous Prefix = Subarray Sum

        6 - 3 = 3

        The previous prefix 3 represents the sum of [1,2].
        The current prefix 6 represents the sum of [1,2,1,2].

        Remove the previous part [1,2].

        Remaining part = [1,2]

        Sum of [1,2] = 3

        Therefore [1,2] is a valid subarray.

        count = 3

        Store prefix 6.

        map = {0=1, 1=1, 3=1, 4=1, 6=1}


Final Answer = 3


Mental Rule:

Current Prefix
      ↓
Current Prefix - K
      ↓
Search this value in the map
      ↓
Found
      ↓
Current Prefix - Previous Prefix = K
      ↓
A valid subarray exists

The HashMap simply remembers all previous prefix sums.
*/
