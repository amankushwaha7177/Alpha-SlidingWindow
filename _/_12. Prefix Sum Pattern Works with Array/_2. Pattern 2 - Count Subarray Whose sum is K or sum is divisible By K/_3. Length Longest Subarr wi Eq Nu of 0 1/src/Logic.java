public class Logic {
}

/*
Logic:

We need to find the length of the longest subarray having equal number of 0s and 1s.

The main trick is to convert:

0 → -1
1 → +1

Why?

Because every 0 and 1 can then cancel each other.

Example:

[0, 1, 0, 1]

Convert:

[-1, 1, -1, 1]

Sum:

-1 + 1 - 1 + 1 = 0

So:

Equal number of 0s and 1s
            ↓
       Sum becomes 0


Now the problem becomes:

"Find the longest subarray whose sum is 0."


Prefix Sum Idea:

If the same prefix sum appears at two different positions,
the elements between those positions must have sum 0.

Example:

arr = [0, 1, 0, 1]

Converted:

[-1, 1, -1, 1]

Prefix sums:

index 0 → -1
index 1 →  0
index 2 → -1
index 3 →  0


Prefix -1 appears again:

index 0 → -1
index 2 → -1

Difference:

2 - 0 = 2

So:

[1,0]

has equal 0s and 1s.

Prefix 0 also appears again:

index -1 → 0
index 1  → 0

Difference:

1 - (-1) = 2

So:

[0,1]

has equal 0s and 1s.


Why HashMap?

The HashMap stores:

prefix sum → first index where it appeared

We store the FIRST index only because we want the longest possible subarray.

If the same prefix appears again:

currentIndex - firstIndex

gives the length of the subarray having equal 0s and 1s.


Mental Rule:

0 → -1
1 → +1
      ↓
Equal 0s and 1s
      ↓
Sum becomes 0
      ↓
Same Prefix Sum
      ↓
Calculate currentIndex - firstIndex
      ↓
Keep the maximum length


The easiest sentence to remember:

"Convert 0 to -1, keep 1 as +1, and find the longest distance between equal prefix sums."
*/