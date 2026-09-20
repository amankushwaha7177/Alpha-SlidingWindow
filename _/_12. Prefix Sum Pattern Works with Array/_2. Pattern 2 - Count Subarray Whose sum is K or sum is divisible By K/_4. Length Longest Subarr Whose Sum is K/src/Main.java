import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        int[] arr = {10, 5, 2, 7, 1, 9};
        int k = 15;

        int ans = longestSubarray(arr, k);

        System.out.println("Maximum Length = " + ans);
    }

    static int longestSubarray(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        /*
        Store prefix sum 0 at index -1 because the array starts after index -1.
        */
        map.put(0, -1);

        int prefix = 0;
        int maxLength = 0;

        for(int i = 0; i < arr.length; i++) {
            prefix += arr[i];

            /*
            Current Prefix - Previous Prefix = Subarray Sum.
            We need the subarray sum to equal K.
            Therefore Previous Prefix = Current Prefix - K.
            */
            int requiredPrefix = prefix - k;

            /*
            If required prefix exists, calculate the current subarray length.
            */
            if(map.containsKey(requiredPrefix)) {
                int length = i - map.get(requiredPrefix);
                maxLength = Math.max(maxLength, length);
            }

            /*
            Store only the first occurrence because it gives the longest length.
            */
            if(!map.containsKey(prefix)) {
                map.put(prefix, i);
            }
        }

        return maxLength;
    }
}

/*
Logic:

arr = [10, 5, 2, 7, 1, 9]
K = 15

Start:

prefix = 0
maxLength = 0
map = {0=-1}


Step 1: Take 10                               | [10, 5, 2, 7, 1, 9]   {0=-1, 10=0}

        prefix = 10
        requiredPrefix = 10 - 15
                       = -5

        -5 is not in map.

        Store:
        map = {0=-1, 10=0}


Step 2: Take 5

        prefix = 15

        requiredPrefix = 15 - 15
                       = 0

        0 exists at index -1.

        length = currentIndex - firstIndex
               = 1 - (-1)
               = 2

        Subarray:
        [10,5] = 15 ✓

        maxLength = 2

        Store:
        map = {0=-1, 10=0, 15=1}


Step 3: Take 2

        prefix = 17

        requiredPrefix = 17 - 15
                       = 2

        2 is not in map.

        Store:
        map = {0=-1, 10=0, 15=1, 17=2}


Step 4: Take 7

        prefix = 24

        requiredPrefix = 24 - 15
                       = 9

        9 is not in map.

        Store:
        map = {0=-1, 10=0, 15=1, 17=2, 24=3}


Step 5: Take 1

        prefix = 25

        requiredPrefix = 25 - 15
                       = 10

        10 exists at index 0.

        length = 4 - 0
               = 4

        Subarray:
        [5,2,7,1] = 15 ✓

        maxLength = 4

        Store:
        map = {0=-1, 10=0, 15=1, 17=2, 24=3, 25=4}


Step 6: Take 9

        prefix = 34

        requiredPrefix = 34 - 15
                       = 19

        19 is not in map.

        No new valid subarray.

        maxLength = 4


Final Answer = 4


Mental Rule:

Current Prefix
      ↓
Current Prefix - K
      ↓
Search required prefix in HashMap
      ↓
Found
      ↓
Current Index - First Index
      ↓
Maximum Length


Important:

For COUNT problems:
Store frequency.

For LONGEST LENGTH problems:
Store first index.

The easiest sentence to remember:

"Find the required previous prefix and use its first index to get the longest length."
*/

/*
Time = O(n)
Space = O(n)
*/