public class _1_All { }
/*
================ PATTERN IDENTIFICATION HACK ================

STEP 1:
Is the problem asking about PAIRS / TRIPLETS?

    YES
      ↓
    Is the array SORTED or can I sort it?

      YES → TWO POINTERS

Examples:
    Two Sum in Sorted Array
    3Sum
    3Sum Closest
    Count Triplets <= K
    Container With Most Water


STEP 2:
Is it CONTIGUOUS?

    "subarray"
    "substring"
    "consecutive"

      ↓ YES

    Continue checking.


STEP 3:
Is it asking MAXIMUM / MINIMUM SUM?

      ↓ YES

    KADANE

Examples:
    Maximum Subarray Sum
    Minimum Subarray Sum
    Maximum Circular Subarray Sum
    Maximum Absolute Subarray Sum


STEP 4:
Is it asking about SUM RELATIONSHIPS?

    Sum = K
    Sum divisible by K
    Range Sum
    Zero Sum
    Equal 0s and 1s

      ↓ YES

    PREFIX SUM

    If counting/finding longest:
      ↓
    PREFIX SUM + HASHMAP


STEP 5:
Is it asking:

    Longest
    Shortest
    At Most K
    Exactly K
    K Distinct
    No Repeating
    Maximum/Minimum Window

      ↓ YES

    SLIDING WINDOW


STEP 6:
If Sliding Window involves SUM:

    Are numbers +ve / 0 only?

      YES
        ↓
      SLIDING WINDOW can usually work.


    Are numbers +ve and -ve?

      YES
        ↓
      Normal SUM-based Sliding Window
      is usually NOT reliable.

      Think PREFIX SUM or another
      appropriate data structure.


STEP 7:
Ask what the HashMap is supposed to remember.

    Need HOW MANY?
        ↓
    prefix → frequency

    Need WHERE?
        ↓
    prefix → first index


=============================================================

SUPER-FAST HACK:

    PAIR / TRIPLET
        ↓
    TWO POINTERS


    CONTIGUOUS + MAX/MIN SUM
        ↓
    KADANE


    SUBARRAY + SUM RELATION
        ↓
    PREFIX SUM


    LONGEST / SHORTEST / AT MOST K / DISTINCT
        ↓
    SLIDING WINDOW


    +VE ONLY + SUM CONDITION
        ↓
    SLIDING WINDOW


    +VE + -VE + EXACT SUM
        ↓
    PREFIX SUM + HASHMAP


=============================================================

ONE-LINE MEMORY:

    TWO POINTERS → PAIR / TRIPLET

    SLIDING WINDOW → MANAGE A WINDOW

    KADANE → CONTINUE OR START FRESH

    PREFIX SUM → SUBTRACT TWO PREFIXES

    HASHMAP → REMEMBER PREVIOUS INFORMATION
*/