public class DiffeBW_LongestLength__N__Count {

    /*
    DIFFERENCE BETWEEN LONGEST LENGTH AND COUNT

    1. EXACT SUM + LONGEST LENGTH
       → Directly find windows where sum == k.
       → When sum == k, calculate window length.
       → Keep the maximum length.

       Example:
       [1,2,3,8,6], k = 14

       [1,2,3,8] → sum = 14 → length = 4
       [8,6]     → sum = 14 → length = 2

       Answer = 4

    2. EXACT SUM + COUNT
       → Directly counting sum == k is difficult with an atMost window.
       → So calculate:

          atMost(k) - atMost(k - 1)

       atMost(k):
       → Counts all subarrays having sum <= k.

       atMost(k - 1):
       → Counts all subarrays having sum <= k - 1.

       Subtracting them removes all sums smaller than k
       and leaves only the number of subarrays having sum exactly k.

       Example:

       atMost(14) - atMost(13)
       → Number of subarrays having sum exactly 14

    MAIN DIFFERENCE:

       Exact Sum + Longest Length
       → sum == k
       → maximize window length

       Exact Sum + Count
       → atMost(k) - atMost(k - 1)
       → count exact-sum subarrays


    IMPORTANT:

       Both approaches can use sliding-window / two-pointer movement
       when the array contains positive or non-negative values.

       The difference is what we want from the valid windows:

       LONGEST → maximum length
       COUNT    → number of valid subarrays
    */
}