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
Example:

arr = {-2, 3, -1, 2, 1, -10, 5}


Step 1:
i = 1

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
                       → New Subarray starts at [ 3
                       → start = 1
                       → currentSum = 3

Since 3 is the maximum sum:
update ans and main poiters

bestStart = 1
bestEnd = 1


Step 2:
i = 2

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
                       → Continue Previous Subarray [ 3, -1
                       → currentSum = 2

No new maximum:

bestStart = 1
bestEnd = 1


Step 3:
i = 3

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
                       → Continue Previous Subarray [ 3, -1, 2
                       → currentSum = 4

Since 4 is the maximum sum:

bestStart = start = 1
bestEnd = i = 3


Step 4:
i = 4

                1
                |
        ┌───────┴───────┐
        ▼               ▼
    Continue           Start Fresh
    4 + 1 = 5              1
        |                   |
        └────────┬──────────┘
                 ▼
        Choose Maximum → 5 ✓
                       → Continue Previous Subarray [ 3, -1, 2, 1
                       → currentSum = 5

Since 5 is the maximum sum:

bestStart = start = 1
bestEnd = i = 4


Step 5:
i = 5

              -10
                |
        ┌───────┴────────┐
        ▼                ▼
    Continue           Start Fresh
    5 + (-10) = -5        -10
        |                  |
        └────────┬─────────┘
                 ▼
        Choose Maximum → -5 ✓
                       → Continue Previous Subarray
                       → currentSum = -5

No new maximum:

bestStart = 1
bestEnd = 4


Step 6:
i = 6

                5
                |
        ┌───────┴───────┐
        ▼               ▼
    Continue           Start Fresh
    -5 + 5 = 0             5
        |                   |
        └────────┬──────────┘
                 ▼
        Choose Maximum → 5 ✓
                       → Start Fresh
                       → start = 6
                       → currentSum = 5

5 is equal to the previous maximum 5:

bestStart = 1
bestEnd = 4


Therefore:

Maximum Subarray = [3, -1, 2, 1]
Maximum Sum = 5


Important:

At Step 6:

Continue → -5 + 5 = 0
Start Fresh → 5

So we choose Start Fresh.

However, the new sum 5 is equal to the previous best 5,
so the best subarray is not replaced because the condition is >.

Therefore:

Current Subarray:
[5]
start = 6

Best Subarray:
[3, -1, 2, 1]
bestStart = 1
bestEnd = 4
*/