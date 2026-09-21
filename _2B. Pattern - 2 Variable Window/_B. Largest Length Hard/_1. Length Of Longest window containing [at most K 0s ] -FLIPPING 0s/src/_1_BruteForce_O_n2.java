public class _1_BruteForce_O_n2 {
    /*
       Idea :  A. Normal : Try every possible subarray and count zeros inside each generated window.
                    If zeroCount <= K, compare the current subarray length with the maximum answer.
                    If zeroCount > K, the current subarray becomes invalid and cannot be considered further.
                    Start again from the next index and generate another possible subarray.
                    T = O(n²).

               B. Optimal : Start a variable-size sliding window from index 0 and keep increasing R.
                  Count zeros inside the current window while R keeps moving forward.
                  If zeroCount > K, shrink from LEFT until the window becomes valid again.
                  Then compare the current valid window length with the maximum answer.
                  T = O(2n) = O(n).
     */

    public static void main(String[] args) {
        int k = 2;
        int[] arr = {1,1,1,0,0,0,1,1,1,1,0};

        /* Step 0 : variables analogy for brute force, where every starting index creates a new subarray. */
        int ans = 0;

        /*
         * Step 1 : Start every possible subarray from each index and keep extending
         * the right boundary while counting how many zeros are present inside that subarray.
         */
        for(int i = 0; i < arr.length; i++) {

            int zeroCount = 0;

            /*
             * Step 2 : Extend the current subarray one element at a time toward the right.
             * Every zero entering this subarray increases zeroCount by exactly one.
             */
            for(int j = i; j < arr.length; j++) {

                if(arr[j] == 0) {
                    zeroCount++;
                }

                /*
                 * If zeroCount becomes greater than K, this subarray is invalid.
                 * Since every further element only extends this same subarray, stop checking it.
                 */
                if(zeroCount > k) {
                    break;
                }

                /*
                 * The current subarray contains at most K zeros, so compare its length
                 * with the maximum valid subarray length found during all previous iterations.
                 */
                ans = Math.max(ans, j - i + 1);
            }
        }

        System.out.println(ans);
    }
}

/*
arr = [1,1,1,0,0,0,1,1,1,1,0]
k = 2


Step 1 : Start subarrays from index 0 and keep increasing R
------------------------------------------------------------

i = 0

[ [1], 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 ] → zeroCount = 0 → length = 1 ✓
[ [1, 1], 1, 0, 0, 0, 1, 1, 1, 1, 0 ] → zeroCount = 0 → length = 2 ✓
[ [1, 1, 1], 0, 0, 0, 1, 1, 1, 1, 0 ] → zeroCount = 0 → length = 3 ✓
[ [1, 1, 1, 0], 0, 0, 1, 1, 1, 1, 0 ] → zeroCount = 1 → length = 4 ✓
[ [1, 1, 1, 0, 0], 0, 1, 1, 1, 1, 0 ] → zeroCount = 2 → length = 5 ✓

R adds the third zero:

[ [1, 1, 1, 0, 0, 0], 1, 1, 1, 1, 0 ] → zeroCount = 3 → INVALID ❌

Stop this starting position because this subarray already contains more than K zeros.


Step 2 : Start again from index 1 and generate another set of subarrays
---------------------------------------------------------------------

i = 1

[ 1, [1], 1, 0, 0, 0, 1, 1, 1, 1, 0 ] → zeroCount = 0 → length = 1 ✓
[ 1, [1, 1], 0, 0, 0, 1, 1, 1, 1, 0 ] → zeroCount = 0 → length = 2 ✓
[ 1, [1, 1, 1], 0, 0, 0, 1, 1, 1, 1, 0 ] → zeroCount = 1 → length = 3 ✓
[ 1, [1, 1, 1, 0], 0, 0, 1, 1, 1, 1, 0 ] → zeroCount = 2 → length = 4 ✓

R reaches the third zero:

[ 1, [1, 1, 1, 0, 0, 0], 1, 1, 1, 1, 0 ] → zeroCount = 3 → INVALID ❌

Stop this starting position.


Step 3 : Start again from index 2 and continue generating subarrays
-------------------------------------------------------------------

i = 2

[ 1, 1, [1], 0, 0, 0, 1, 1, 1, 1, 0 ] → zeroCount = 0 → length = 1 ✓
[ 1, 1, [1, 0], 0, 0, 1, 1, 1, 1, 0 ] → zeroCount = 1 → length = 2 ✓
[ 1, 1, [1, 0, 0], 0, 1, 1, 1, 1, 0 ] → zeroCount = 2 → length = 3 ✓

R reaches the third zero:

[ 1, 1, [1, 0, 0, 0], 1, 1, 1, 1, 0 ] → zeroCount = 3 → INVALID ❌

Stop this starting position.


Step 4 : Continue the same brute-force process for remaining starting positions
-------------------------------------------------------------------------------

i = 3:

[ 1,1,1, [0] ] → zeroCount = 1 → length = 1 ✓
[ 1,1,1, [0,0] ] → zeroCount = 2 → length = 2 ✓
[ 1,1,1, [0,0,0] ] → zeroCount = 3 → INVALID ❌

i = 4:

[ 1,1,1,0, [0] ] → zeroCount = 1 → length = 1 ✓
[ 1,1,1,0, [0,0] ] → zeroCount = 2 → length = 2 ✓

i = 5:

[ 1,1,1,0,0, [0] ] → zeroCount = 1 → length = 1 ✓
[ 1,1,1,0,0, [0,1] ] → zeroCount = 1 → length = 2 ✓
[ 1,1,1,0,0, [0,1,1] ] → zeroCount = 1 → length = 3 ✓
[ 1,1,1,0,0, [0,1,1,1] ] → zeroCount = 1 → length = 4 ✓
[ 1,1,1,0,0, [0,1,1,1,1] ] → zeroCount = 1 → length = 5 ✓
[ 1,1,1,0,0, [0,1,1,1,1,0] ] → zeroCount = 2 → length = 6 ✓

This gives the maximum answer.

Maximum length = 6


============================================================
Remember:

a. Brute force means generating subarrays from every possible starting index.
b. Outer loop 'i' chooses where the current subarray starts from the array.
c. Inner loop 'j' keeps extending the current subarray toward the right.
d. 0 enters → zeroCount increases because the current subarray contains another zero.
e. zeroCount > K → current subarray becomes invalid and further extension is stopped.
f. zeroCount <= K → current subarray is valid and its length can update ans.
g. j - i + 1 → gives the length of the current generated subarray.
h. There is no L and R sliding-window movement because this is the brute-force approach.
i. Every new starting index creates a completely new subarray-generation process.

============================================================
Important : This is the BRUTE FORCE pattern.

            Outer loop → chooses the starting index.

            Inner loop → keeps extending the ending index.

            zeroCount → counts zeros inside the current subarray.

            zeroCount <= K → valid subarray.

            zeroCount > K → invalid subarray, so stop extending.

            Then:
            Start again from the next starting index.

            Unlike the HeavyWhile sliding-window approach,
            L does not move forward to repair the same window.

============================================================
Complexity:

Time = O(n²), Space = O(1)

        Outer loop:
        i moves from 0 → n-1, giving O(n) starting positions.

        Inner loop:
        For every starting position, j can traverse
        many remaining elements before reaching the end
        or before finding more than K zeros.

        Therefore, in the worst case:

        O(n) × O(n)
        = O(n²)

Space = O(1)

        Only zeroCount and ans are maintained.
        No extra data structure grows with the input size.
============================================================
*/