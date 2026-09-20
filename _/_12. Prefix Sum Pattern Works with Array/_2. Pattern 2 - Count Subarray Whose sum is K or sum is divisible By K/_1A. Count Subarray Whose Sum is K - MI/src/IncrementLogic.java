public class IncrementLogic {
}

/*
Why count += map.get(requiredPrefix) and not count += 1?

Because the same required prefix can appear multiple times,
and every occurrence creates one different valid subarray.

Example:

arr = [2, -2, 2, -2]
k = 0

Start:

prefix = 0
count = 0
map = {0=1} -> {0=1} means: "Before the array starts, prefix sum is 0." t does NOT mean an empty subarray is counted.


Step 1: Take 2                                           | [2, -2, 2, -2]      {0=1}

        prefix = 2
        requiredPrefix = prefix - k
                       = 2 - 0
                       = 2

        2 is not in map.
        so no change in count ( still count = 0 )
        No valid subarray yet.

        Store prefix 2.
        map = {0=1, 2=1}


Step 2: Take -2                                            | [2, -2, 2, -2]   {0=1, 2=1}

        prefix = 0
        requiredPrefix = prefix - k
                       = 0 - 0
                       = 0

        0 exists once.
        count = count + map.get(0)
        count =    0  + 1
              = 1
        The valid subarray is: [2,-2] = 0 ✓

        Store prefix 0.
        map = {0=2, 2=1}


Step 3: Take 2                                            | [2, -2, 2, -2]    {0=2, 2=1}

        prefix = 2
        requiredPrefix = prefix - k
                       = 2 - 0
                       = 2

        2 exists once.
        count  = count + map.get(2)
               = 1 + 1
               = 2
        The valid subarray is: [-2, 2 ] = 0 ✓

        Store prefix 2.
        map = {0=2, 2=2}


Step 4: Take -2                                            | [2, -2, 2, -2]    {0=2, 2=2}

        prefix = 0
        requiredPrefix = prefix - k
                       = 0 - 0
                       = 0

        0 exists twice.
        count  = count + map.get(0)
               = 2 + 2
               = 4

        Why 2 new subarrays?

        Previous prefix at index -1 → [2,-2,2,-2] = 0 ✓
        Previous prefix at index  1 → [2,-2]       = 0 ✓

        Store prefix 0.

        map = {0=3, 2=2}


Final Answer: count = 4


Now the important point:

At Step 4:

        requiredPrefix = 0
        map.get(0) = 2

        count += 2

Why?

Because prefix 0 appeared at TWO previous positions.

Each previous position can create a different valid subarray.

So:

        count += 1
        ↓
        Counts only one previous occurrence.

        count += map.get(requiredPrefix)
        ↓
        Counts ALL previous occurrences.


Mental Rule:

Required prefix appears 1 time → +1
Required prefix appears 2 times → +2
Required prefix appears 3 times → +3

The HashMap stores frequency because every previous occurrence
of the required prefix creates one different valid subarray.
*/