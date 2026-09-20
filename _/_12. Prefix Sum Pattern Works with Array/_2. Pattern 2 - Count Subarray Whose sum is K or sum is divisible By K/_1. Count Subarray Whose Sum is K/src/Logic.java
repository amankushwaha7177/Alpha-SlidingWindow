public class Logic {
}

/*
Logic:

We need to count subarrays whose sum is exactly K.

Example:
arr = [1, 2, 1, 2]
K = 3


Step 1: Understand Prefix Sum

        Prefix Sum means the total sum from index 0 up to the current index.

        arr = [1, 2, 1, 2]

        prefix values:

        After 1       → 1
        After 1,2     → 3
        After 1,2,1   → 4
        After 1,2,1,2 → 6


Step 2: Understand the Main Trick

        Suppose current prefix = 4.

        We need a subarray whose sum is K = 3.

        Ask:

        "What previous sum should I remove from 4 so that 3 remains?"

        4 - ? = 3

        So:

        ? = 1

        Therefore, we search the HashMap for prefix = 1.


Step 3: Why Does This Work?

        The array is:

        [1, 2, 1]

        Current prefix = 4

        The previous prefix = 1 means:

        [1]

        So if we remove the first [1]:

        [1, 2, 1]
           ↓
        remove [1]
           ↓
        [2, 1]

        And:

        4 - 1 = 3

        Therefore [2,1] is a valid subarray.


Step 4: General Formula

        Current Prefix - Previous Prefix = Subarray Sum

        We need:

        Subarray Sum = K

        Therefore:

        Current Prefix - Previous Prefix = K

        So:

        Previous Prefix = Current Prefix - K


Step 5: Why Do We Need HashMap?

        The HashMap simply remembers every prefix sum that we have already seen.

        We start with:

        map = {0=1}

        The 0 is important because before the array starts,
        the prefix sum is 0.

        The value 1 means that prefix sum 0 has appeared once.

*/
