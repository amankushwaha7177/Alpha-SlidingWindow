public class _2_Optimal_O_2n________HeavyWhile_Shrink_ToFind_valid_Window {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 1, 2, 3};

        int k = 2;

        int l = 0;
        int r = 0;

        int ans = 0;

        java.util.HashMap<Integer, Integer> map = new java.util.HashMap<>();

        while(r < arr.length) {

            map.put(arr[r], map.getOrDefault(arr[r], 0) + 1);

            if(map.size() > k) {
                while(map.size() > k) {

                    map.put(arr[l], map.get(arr[l]) - 1);

                    if(map.get(arr[l]) == 0) {
                        map.remove(arr[l]);
                    }

                    l++;
                }
            }

            /* Excluded ws < k condition from atmost case.
             * The window is valid only when it contains exactly K distinct numbers,
             * so compare its current length with the maximum answer found so far.
             */
            if(map.size() == k) {
                ans = Math.max(ans, r - l + 1);
            }

            r++;
        }

        System.out.println(ans);
    }
}

/*
arr = [1, 1, 2, 1, 2, 3]
k = 2


Step 1 : R keeps moving and every number enters the window
------------------------------------------------------------

[ [1], 1, 2, 1, 2, 3 ]       → map = {1→1}       → size = 1 → Not enough

[ [1, 1], 2, 1, 2, 3 ]       → map = {1→2}       → size = 1 → Not enough

[ [1, 1, 2], 1, 2, 3 ]       → map = {1→2, 2→1} → size = 2 → VALID → length = 3

[ [1, 1, 2, 1], 2, 3 ]       → map = {1→3, 2→1} → size = 2 → VALID → length = 4

[ [1, 1, 2, 1, 2], 3 ]       → map = {1→3, 2→2} → size = 2 → VALID → length = 5


Step 2 : R adds 3, so the number of distinct values becomes greater than K
-----------------------------------------------------------------------

[ [1, 1, 2, 1, 2, 3] ]

map = {1→3, 2→2, 3→1}
size = 3

3 > 2 → INVALID ❌


Step 3 : Shrink from LEFT until exactly K distinct numbers remain
-----------------------------------------------------------------------

Current:

[ [1, 1, 2, 1, 2, 3] ]

map = {1→3, 2→2, 3→1}
size = 3 ❌


L removes first 1:

[ 1, [1, 2, 1, 2, 3] ]

map = {1→2, 2→2, 3→1}
size = 3 ❌

Window is still invalid because three distinct numbers remain.


L removes second 1:

[ 1, 1, [2, 1, 2, 3] ]

map = {1→1, 2→2, 3→1}
size = 3 ❌

Window is still invalid because three distinct numbers remain.


L removes 2:

[ 1, 1, 2, [1, 2, 3] ]

map = {1→1, 2→1, 3→1}
size = 3 ❌

Window is still invalid because three distinct numbers remain.


L removes 1:

[ 1, 1, 2, 1, [2, 3] ]

map = {1→0, 2→1, 3→1}

Frequency of 1 became zero, so remove 1 from HashMap.

map = {2→1, 3→1}
size = 2 ✓

Now the window contains exactly K distinct numbers.


Current valid window:

[ 2, 3 ]

length = 6 - 4
length = 2

ans = max(5, 2)
ans = 5


Final Answer = 5

Maximum valid subarray:

[ 1, 1, 2, 1, 2 ]

It contains exactly two distinct numbers:
1 and 2.


============================================================
Remember:

a. R moves → number ENTERS the current sliding window.
b. HashMap → stores number and its frequency inside the window.
c. map.size() → tells how many DISTINCT numbers currently exist.
d. map.size() < K → window does not have enough distinct numbers.
e. map.size() == K → window is VALID and we calculate maximum length.
f. map.size() > K → window becomes INVALID because too many distinct numbers exist.
g. L moves → number LEAVES from the left side of the window.
h. Frequency decreases → because one occurrence of the leftmost number leaves.
i. Frequency becomes 0 → completely remove that number from HashMap.
j. Continue shrinking until map.size() == K.
k. ans → stores the maximum valid subarray length found so far.


============================================================
Solid Interview Understanding :
============================================================

Q. Why do we check map.size() == K for the answer?

A. The question asks for EXACTLY K distinct numbers,
   so only a window containing exactly K different numbers is valid.

   If map.size() < K:

       Window is not valid because it has fewer than K distinct numbers.

   If map.size() == K:

       Window is VALID because it has exactly K distinct numbers.

   If map.size() > K:

       Window is INVALID because it has more than K distinct numbers.

   Therefore, first handle the invalid condition:

       if(map.size() > k)

   Inside it, shrink the window:

       while(map.size() > k)

   Once the invalid condition is removed, the window becomes:

       map.size() == k

   Then calculate:

       ans = Math.max(ans, r - l + 1);

   This is the main thinking pattern for EXACTLY K distinct subarray problems.


============================================================
Important : This is the HEAVY-WHILE version.

            R → ENTERS number.

            map.size() > K
            ↓
            Window becomes INVALID.

            L keeps moving ONE BY ONE:

            L → L → L → ...

            Each movement removes one number occurrence.

            When a number frequency becomes 0,
            remove that number from HashMap.

            Continue shrinking until:

            map.size() == K

            Now:

            Window → VALID

            Then calculate the maximum window length.


            Mental Rule:

            R → ENTERS
            map.size() > K → INVALID
            L → LEAVES repeatedly
            map.size() == K → VALID
            ans → calculate maximum


============================================================
Complexity:

Time = O(2n) = O(n), Space = O(n)

R moves n times, while L also moves at most n times throughout the complete traversal.

Space is O(n) because the HashMap can store up to n different numbers
when the input array contains n unique numbers.
============================================================
*/