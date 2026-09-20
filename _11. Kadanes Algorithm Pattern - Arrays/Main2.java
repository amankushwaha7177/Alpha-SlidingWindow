public class Main2 {
}
/*
======================== DSA PATTERN IDENTIFICATION ========================

The first thing to check is:

    Are the numbers only +ve/0?
    OR
    Can the array contain +ve and -ve?

This is especially important for SUM-based problems.


1. SLIDING WINDOW

Best suited when:

    +ve numbers only
    +
    CONTIGUOUS subarray / substring
    +
    Longest / Shortest / Maximum / Minimum
    +
    Some window condition

Why does +ve help?

    Add arr[right]
        ↓
    Sum increases or stays same

    Remove arr[left]
        ↓
    Sum decreases or stays same

So we can control the window predictably.

Common examples:

    Longest Subarray with Sum <= K
    Minimum Size Subarray Sum
    Max Consecutive Ones
    Fruit Into Baskets
    Longest Substring Without Repeating Characters
    At Most K Distinct

Mental Rule:

    +ve only + window condition
        ↓
    Think Sliding Window


IMPORTANT:

    +ve + -ve
        ↓
    Normal sum-based Sliding Window is usually NOT reliable.

Example:

    arr = [2, -5, 4]

    Add -5
        ↓
    Sum decreases

    Remove -5
        ↓
    Sum increases

    Therefore the sum is not predictable.


2. PREFIX SUM

Think Prefix Sum when the problem is mainly about:

    SUBARRAY + SUM

Especially:

    Range Sum
    Subarray Sum = K
    Count Subarrays Sum = K
    Longest Subarray Sum = K
    Sum Divisible by K
    Equal 0s and 1s

Prefix Sum works with:

    +ve
    -ve
    0

Core idea:

    Current Prefix - Previous Prefix
            ↓
       Subarray Sum


For:

    Subarray Sum = K

We need:

    Current Prefix - Previous Prefix = K

Therefore:

    Previous Prefix = Current Prefix - K

Then:

    Search Current Prefix - K


For COUNT:

    map = {prefix → frequency}

    Example:

    map = {0=1}

    Why?

    We need to know HOW MANY previous prefixes exist.


For LONGEST:

    map = {prefix → first index}

    Example:

    map = {0=-1}

    Why?

    We need to know WHERE the earliest prefix occurred.


Mental Rule:

    Exact Sum / Range Sum / Prefix Relation
        ↓
    Think Prefix Sum


3. KADANE

Kadane is different.

It is mainly:

    CONTIGUOUS SUBARRAY
    +
    MAXIMUM SUM

It works with:

    +ve
    -ve
    0

The numbers being negative does NOT break Kadane.

At every element:

    Continue previous subarray
            OR
    Start a new subarray

For Maximum:

    currentSum = max(
        arr[i],
        currentSum + arr[i]
    )


Example:

    arr = [-2, 1, -3, 4, -1, 2, 1]

    "Find maximum sum contiguous subarray"

            ↓

        KADANE


For Minimum Subarray Sum:

    Use the same idea but choose the minimum.


For Maximum Product Subarray:

    Kadane-like approach is used,
    but we track both current maximum and minimum
    because multiplying by a negative can swap them.


4. THE MOST IMPORTANT +VE / -VE CHEAT SHEET


    +VE ONLY
        ↓
    Sliding Window can often work
    for sum-based window conditions.


    +VE + -VE
        ↓
    Normal sum-based Sliding Window usually fails.


    +VE + -VE
        ↓
    Prefix Sum can still work.


    +VE + -VE
        ↓
    Kadane still works for Maximum/Minimum
    contiguous subarray sum.


5. QUICK COMPARISON

    Problem                              Pattern

    Longest Subarray Sum <= K
    +ve only                             Sliding Window

    Longest Subarray Sum = K
    +ve / -ve                            Prefix Sum + HashMap

    Count Subarrays Sum = K
    +ve / -ve                            Prefix Sum + HashMap

    Count Subarrays Sum <= K
    +ve only                             Sliding Window

    Count Subarrays Sum <= K
    +ve / -ve                            Prefix + Fenwick Tree

    Range Sum L to R
    +ve / -ve                            Prefix Sum

    Maximum Subarray Sum
    +ve / -ve                            Kadane

    Minimum Subarray Sum
    +ve / -ve                            Kadane

    Longest Substring Without Repeating
    Any characters                       Sliding Window


6. THE BIGGEST INTERVIEW HACK

First ask:

    "Is this about a CONTIGUOUS subarray/substring?"

If NO:

    Do not immediately think Sliding Window or Kadane.

If YES:

    Ask what the problem wants.


    CONTIGUOUS
         +
    WINDOW CONDITION
         +
    Usually +ve
         ↓
    SLIDING WINDOW


    CONTIGUOUS
         +
    SUM RELATION
         ↓
    PREFIX SUM


    CONTIGUOUS
         +
    MAXIMUM/MINIMUM SUM
         ↓
    KADANE


7. ONE-LINE MEMORY TRICK

    Sliding Window
        ↓
    MANAGE A WINDOW


    Prefix Sum
        ↓
    SUBTRACT TWO PREFIXES


    Kadane
        ↓
    CONTINUE OR START FRESH


8. FINAL MENTAL FLOW

    Is it contiguous?
            ↓
           YES
            ↓
    Is it Maximum/Minimum SUM?
            ↓
           YES
            ↓
          KADANE


    Otherwise:

    Is it a SUM / PREFIX relationship?
            ↓
           YES
            ↓
        PREFIX SUM


    Otherwise:

    Is it a window condition?
            ↓
           YES
            ↓
    Check whether numbers are +ve.

        +ve only
            ↓
        Sliding Window

        +ve + -ve
            ↓
    Normal Sliding Window is usually
    not reliable for sum-based conditions.


The easiest sentence to remember:

    +ve + controllable window → Sliding Window

    Sum relationship → Prefix Sum

    Maximum/Minimum contiguous Sum → Kadane

    Negative numbers do NOT break Prefix Sum or Kadane,
    but they can break normal sum-based Sliding Window.
*/
