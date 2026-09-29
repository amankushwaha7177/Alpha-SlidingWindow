public class DiffeBW_LongestLength__N__Count {

    /*
    DIFFERENCE BETWEEN LONGEST LENGTH AND COUNT

    1. EXACT SUM + LONGEST LENGTH

       → Directly find windows where sum == k.
       → When sum == k, calculate window length.
       → Keep the maximum length.

       Example:
       [0,0,5,1,2], k = 5

       [0,0,5] → sum = 5 → length = 3
       [0,5]   → sum = 5 → length = 2
       [5]     → sum = 5 → length = 1

       Answer = 3

       Why this works:
       → Longest only needs the largest valid window.
       → We compare the valid lengths and keep the maximum.
       → We do not need to count every valid window.

    2. EXACT SUM + COUNT

       → Counting requires every valid window.
       → In the same example, there are three valid subarrays.

       Example:
       [0,0,5], k = 5

       Valid subarrays ending at index 2:

       [0,0,5] → sum = 5
       [0,5]   → sum = 5
       [5]     → sum = 5

       Total = 3

       Directly checking sum == k is not enough for counting
       because the sliding window may keep only one valid window.

       So calculate:

          atMost(k) - atMost(k - 1)

       atMost(k):
       → Counts all subarrays having sum <= k.

       atMost(k - 1):
       → Counts all subarrays having sum <= k - 1.

       Subtracting them removes all sums smaller than k
       and leaves only the number of subarrays having sum exactly k.

       Example:

       atMost(5) - atMost(4)
       → Number of subarrays having sum exactly 5
       → 3

    MAIN DIFFERENCE:

       Exact Sum + Longest Length
       → sum == k
       → maximize window length
       → [0,0,5] gives answer 3

       Exact Sum + Count
       → atMost(k) - atMost(k - 1)
       → count every valid subarray
       → [0,0,5], [0,5], [5] gives answer 3

    WHY THE LOGIC IS DIFFERENT:

       LONGEST:
       → We only care about the largest valid length.
       → One valid window can be enough for each right pointer.

       COUNT:
       → We care about every valid subarray.
       → Multiple valid windows can end at the same right pointer.
       → Therefore, we cannot simply keep one window and count it.

    IMPORTANT:

       Both approaches can use sliding-window / two-pointer movement
       when the array contains positive or non-negative values.

       The difference is what we want from the valid windows:

       LONGEST → maximum length
       COUNT    → number of valid subarrays
    */
}