/*
HOW TO RECOGNIZE 0, 1, 2 / DUTCH NATIONAL FLAG?

Look for these clues:

    1. Array contains ONLY 3 categories/values.

    Example:

        [0, 1, 2, 2, 0, 1, 1, 2]

    2. The question asks you to SORT / GROUP / PARTITION them.

        0s together
        1s together
        2s together

    3. Usually it asks for IN-PLACE sorting.

        No extra array
        O(1) extra space

            ↓

        DUTCH NATIONAL FLAG


Why three pointers?

    low
      ↓
    [0s] [1s] [unknown] [2s]
           ↑              ↑
          mid            high


Pointer meaning:

    low  → position where next 0 should go

    mid  → current element we are checking

    high → position where next 2 should go


The movement rule:

    arr[mid] == 0
        ↓
    swap(low, mid)
    low++
    mid++

    arr[mid] == 1
        ↓
    mid++

    arr[mid] == 2
        ↓
    swap(mid, high)
    high--

    Do NOT increase mid after swapping with high,
    because the new value at mid has not been checked yet.


Quick Recognition Hack:

    ONLY 3 values/categories
          +
    SORT / GROUP / PARTITION
          +
    IN-PLACE
          ↓
    DUTCH NATIONAL FLAG


Example:

    Sort [0,1,2,1,0,2]

        ↓

    0s → first
    1s → middle
    2s → last

        ↓

    Dutch National Flag


Important:

    Do NOT think:

        "Three values → always Dutch Flag."

    Think:

        "Three categories + partition/group them
         in-place → Dutch National Flag."


Generalized version:

    0,1,2
      ↓
    3 categories

    Red, White, Blue
      ↓
    Same pattern

    Negative, Zero, Positive
      ↓
    Can use the same partition idea,
    depending on the exact requirement.


Mental Rule:

    2 categories
        → Two Pointers can often partition.

    3 categories
        → Three Pointers / Dutch National Flag.

    0, 1, 2 specifically
        → Classic Dutch National Flag.
*/