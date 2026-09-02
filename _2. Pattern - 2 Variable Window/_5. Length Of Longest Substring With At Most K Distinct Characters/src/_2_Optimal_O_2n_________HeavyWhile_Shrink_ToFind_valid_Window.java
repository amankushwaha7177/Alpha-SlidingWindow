public class _2_Optimal_O_2n_________HeavyWhile_Shrink_ToFind_valid_Window {
    /*
    Idea :  A. Normal : Generate every possible subarray and check
                    how many different fruit types it contains.
                T = O(n²)

            B. Optimal : Keep a variable-size window using L and R.
                       Store fruit type → frequency inside HashMap.
                       If different fruit types become more than 2,
                       shrink from LEFT until only 2 types remain.
                T = O(2n) = O(n)
                S = O(3) = O(1) = At most 3 fruit types temporarily exist.
    */

    public static void main(String[] args) {
        int[] fruits = {1, 2, 1, 2, 3};

        /* Step 0 : variables analogy for window :
                    a. 'l' and 'r' monitor the start and end of the window.
                    b. 'map' stores fruit type → frequency inside the window.
                    c. 'ans' stores the maximum valid window length. */

        int l = 0;
        int r = 0;

        int ans = 0;

        java.util.HashMap<Integer, Integer> map = new java.util.HashMap<>();

        /*
         * Step 1 : Move R one by one and add each fruit into the window.
         * If different fruit types become more than 2, the window becomes invalid.
         */
        while(r < fruits.length) {

            map.put(fruits[r], map.getOrDefault(fruits[r], 0) + 1);

            /*
             * getOrDefault means: Give me the value for this key;
             * if the key does not exist, give me the provided default value.
             */

            /*
             * More than 2 different fruit types means two baskets are not enough,
             * so we must shrink from LEFT until the window becomes valid again.
             */
            if(map.size() > 2) {

                /*
                 * If the window is invalid, keep moving L until only two
                 * different fruit types remain inside the current window.
                 */
                while(map.size() > 2) {

                    /*
                     * The fruit at L leaves the window, so reduce its frequency
                     * because that particular fruit is no longer fully inside the window.
                     */
                    map.put(fruits[l], map.get(fruits[l]) - 1);

                    /*
                     * If frequency becomes zero, this fruit type no longer exists
                     * anywhere inside the current window, so remove it completely.
                     */
                    if(map.get(fruits[l]) == 0) {
                        map.remove(fruits[l]);
                    }

                    /*
                     * Above we reduced the frequency for the fruit at L,
                     * so only after that operation we move L forward by one position.
                     */
                    l++;
                }
            }

            /*
             * The window is now valid because it contains at most two fruit types,
             * so compare its current length with the maximum answer found so far.
             */
            ans = Math.max(ans, r - l + 1);

            r++;
        }

        System.out.println(ans);
    }
}

/*
fruits = [1, 2, 1, 2, 3]


Step 1 : R keeps moving and every fruit enters the window
------------------------------------------------------------

[ [1], 2, 1, 2, 3 ]       → map = {1→1}           → size = 1 → length = 1 ✓

[ [1, 2], 1, 2, 3 ]       → map = {1→1, 2→1}      → size = 2 → length = 2 ✓

[ [1, 2, 1], 2, 3 ]       → map = {1→2, 2→1}      → size = 2 → length = 3 ✓

[ [1, 2, 1, 2], 3 ]       → map = {1→2, 2→2}      → size = 2 → length = 4 ✓


Step 2 : R adds 3, so the number of fruit types becomes greater than 2
-----------------------------------------------------------------------

[ [1, 2, 1, 2, 3] ]

map = {1→2, 2→2, 3→1}
size = 3

3 > 2 → INVALID ❌

Two baskets cannot hold 3 different fruit types.


Step 3 : Shrink from LEFT until the window becomes valid
------------------------------------------------------------

Current:

[ [1, 2, 1, 2, 3] ]

map = {1→2, 2→2, 3→1}
size = 3 ❌


L removes first 1:

[ 1, [2, 1, 2, 3] ]

map = {1→1, 2→2, 3→1}
size = 3 ❌

Window is still invalid because three different fruit
types are still present.


L removes 2:

[ 1, 2, [1, 2, 3] ]

map = {1→1, 2→1, 3→1}
size = 3 ❌

Window is still invalid because three different fruit
types are still present.


L removes 1:

[ 1, 2, 1, [2, 3] ]

map = {1→0, 2→1, 3→1}

Frequency of 1 became zero, so remove 1 from HashMap.

map = {2→1, 3→1}
size = 2 ✓

Now the window is valid.


Current valid window:

[ 2, 3 ]

length = 5 - 3 + 1
length = 2

ans = max(4, 2)
ans = 4


Final Answer = 4

Maximum valid subarray:

[ 1, 2, 1, 2 ]

It contains only two different fruit types:
1 and 2.

============================================================
Remember:

a. R moves → fruit ENTERS the current sliding window.
b. HashMap → stores fruit type and its frequency inside the window.
c. map.size() → tells how many DISTINCT fruit types currently exist.
d. map.size() > 2 → window becomes INVALID because two baskets are insufficient.
e. L moves → fruit LEAVES from the left side of the window.
f. Frequency decreases → because one occurrence of the leftmost fruit leaves.
g. Frequency becomes 0 → completely remove that fruit type from HashMap.
h. map.size() <= 2 → window is VALID because two baskets are sufficient.
i. ans → stores the maximum valid window length found so far.

============================================================
Solid Interview Understanding :
============================================================

Q. Why don't we write if(map.size() <= 2) first?

A. if(map.size() <= 2) means the window is already valid,
   so we can directly calculate its length.

   But what if map.size() > 2?

   Then the window is INVALID.

   Therefore, first handle the invalid condition:

       if(map.size() > 2)

   Inside it, shrink the window:

       while(map.size() > 2)

   Once the invalid condition is removed, the window becomes valid.

   Then calculate:

       ans = Math.max(ans, r - l + 1);

   This is the main thinking pattern behind variable sliding windows.


============================================================
Important : This is the HEAVY-WHILE version.

            R → ENTERS fruit.

            map.size() > 2
            ↓
            Window becomes INVALID.

            L keeps moving ONE BY ONE:

            L → L → L → ...

            Each movement removes one fruit occurrence.

            When a fruit frequency becomes 0,
            remove that fruit type from HashMap.

            Continue shrinking until:

            map.size() <= 2

            Now:

            Window → VALID

            Then calculate the window length.


            Mental Rule:

            R → ENTERS
            condition violated → INVALID
            L → LEAVES repeatedly
            condition restored → VALID
            ans → calculate maximum valid window


============================================================
Complexity:

Time = O(2n) = O(n), Space = O(1)

R moves n times, while L also moves at most n times throughout the complete traversal.

Space remains O(1) because at most 3 fruit types can temporarily exist
inside the HashMap before the shrinking process removes one type.
============================================================
*/