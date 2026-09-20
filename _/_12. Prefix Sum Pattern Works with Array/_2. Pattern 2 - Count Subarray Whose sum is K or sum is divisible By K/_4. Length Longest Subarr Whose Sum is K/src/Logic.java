public class Logic {
}
/*
Q. Why do we use {0=-1} here and not {0=1}?
A. The reason is that these two HashMaps store different information.

A. For Count Subarrays Sum = K:
-------------------------------
        map = {0=1}

Here:   key   = prefix sum
        value = frequency

So:     0 → 1
means:  Prefix sum 0 has appeared 1 time.

We need frequency because we want to count how many previous prefixes can create a valid subarray.
Therefore: count += map.get(requiredPrefix);

Ex :
{ 0->3 }
If required prefix appeared 3 times, we add 3 in counting.


B. For Longest Subarray Sum = K:
--------------------------------

        map = {0=-1}

Here:

        key   = prefix sum
        value = first index

So:

        0 → -1

means:

        Prefix sum 0 first exists at index -1.

Why index -1?

Because index -1 represents the position immediately
before the array starts.

Example:

        arr = [10, 5, 2, 7, 1, 9]

Index:

        -1    0    1    2    3    4    5
              ↓
             10   5    2    7    1    9

Before taking any element:

        prefix = 0
        index = -1

Therefore:

        map.put(0, -1);


Why is -1 important?

At index 1:

        arr = [10, 5, 2, 7, 1, 9]

        prefix = 15
        k = 15

        requiredPrefix = prefix - k
                       = 15 - 15
                       = 0

The map contains:

        0 → -1

So:

        length = currentIndex - previousIndex
               = 1 - (-1)
               = 2

Therefore:

        arr[0...1]
        [10, 5]

        length = 2


What if we used {0=1}?

Then:

        length = 1 - 1
               = 0

That would be wrong.

Because in the Longest Subarray problem,
the value must represent an index, not a frequency.


Easy Difference:

        COUNT SUM = K

        map = {prefix → frequency}

        map = {0=1}

        We need to know:
        "How many previous prefixes exist?"


        LONGEST SUM = K

        map = {prefix → first index}

        map = {0=-1}

        We need to know:
        "Where was this prefix first found?"


Mental Rule:

        Count      → frequency  → {0=1}

        Longest    → first index → {0=-1}


The easiest sentence to remember:

        Count problem needs HOW MANY.

        Longest problem needs WHERE.

The -1 represents the position immediately before
index 0, allowing subarrays starting from index 0
to get their correct length.
*/