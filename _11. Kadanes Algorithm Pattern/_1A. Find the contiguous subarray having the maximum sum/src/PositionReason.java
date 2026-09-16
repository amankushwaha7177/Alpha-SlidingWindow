public class PositionReason {
}
/*
Q. not understood need of 2 starts
A. Ah, I see the confusion. You don't need two “starts” for Kadane itself.
    One start is enough to know where the current subarray begins.
    The second start (bestStart) is only needed because the best subarray may have been found earlier and we need to remember it until the end.

/*
Why do we need start and bestStart?

start:
→ Stores where the CURRENT subarray starts.

bestStart:
→ Stores where the BEST subarray starts.

bestEnd:
→ Stores where the BEST subarray ends.


Example:

arr = {-2, 3, -1, 2, -10, 5}

Current Subarray:
start ---------------- i
        [Current]


Best Subarray:
bestStart -------- bestEnd
        [Best]


When we start a new subarray:

start = i

This only changes the starting point of the CURRENT subarray.


When we find a new maximum:

bestStart = start
bestEnd = i

This saves the starting and ending indexes of the BEST subarray.


Important:

start can change later when Kadane starts a new subarray.

But bestStart and bestEnd must remember the previous
best subarray even after the current subarray changes.
 */

/*
arr = {-2, 3, -1, 2, -10, 5}


Step 1 : i = 1

                3
                |
        ┌───────┴───────┐
        ▼               ▼
    Continue           Start Fresh
    -2 + 3 = 1             3
        |                   |
        └────────┬──────────┘
                 ▼
        Choose Maximum → 3 ✓

currentSum = 3
start = 1

3 > ans
→ New Best

bestStart = 1
bestEnd = 1


Step 2 : i = 2

               -1
                |
        ┌───────┴───────┐
        ▼               ▼
    Continue           Start Fresh
    3 + (-1) = 2          -1
        |                   |
        └────────┬──────────┘
                 ▼
        Choose Maximum → 2 ✓

currentSum = 2
start = 1

2 < ans
→ No New Best

bestStart = 1
bestEnd = 1


Step 3 : i = 3

                2
                |
        ┌───────┴───────┐
        ▼               ▼
    Continue           Start Fresh
    2 + 2 = 4              2
        |                   |
        └────────┬──────────┘
                 ▼
        Choose Maximum → 4 ✓

currentSum = 4
start = 1

4 > ans
→ New Best

bestStart = start = 1
bestEnd = i = 3

Best Subarray:
[3, -1, 2]
 ↑         ↑
bestStart bestEnd


Step 4 : i = 4

               -10
                |
        ┌───────┴────────┐
        ▼                ▼
    Continue           Start Fresh
    4 + (-10) = -6        -10
        |                  |
        └────────┬─────────┘
                 ▼
        Choose Maximum → -6 ✓

currentSum = -6
start = 1

-6 < ans
→ No New Best

Best Subarray is still:
[3, -1, 2]

bestStart = 1
bestEnd = 3


Important:

start
→ Current Subarray Start

bestStart
→ Best Subarray Start

bestEnd
→ Best Subarray End

start can change when we choose Start Fresh,
but bestStart and bestEnd only change when we find a New Best.
*/