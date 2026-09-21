public class _2_TwoPointers {
}
/*
Two Pointer + Duplicate Hack:

First ask:

    Is it PAIR / TRIPLET?
        +
    Is the array SORTED or can I sort it?

        ↓

    TWO POINTERS


Then ask:

    Do I need UNIQUE pairs/triplets?

        YES
          ↓
    Skip duplicate values after using them.



Important:

    TWO POINTERS
        ↓
    is the main pattern.

    UNIQUE
        ↓
    is an additional constraint.

So:

    Pair/Triplet + Sorted
        → Two Pointers

    Pair/Triplet + Sorted + Unique
        → Two Pointers + Skip Duplicates


Mental Rule:

    Pattern tells you HOW to solve.

    Duplicate requirement tells you
    HOW to avoid repeated answers.
*/