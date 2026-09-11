public class _2_Optimal_O_2n____HeavyWhile_Shrink_ToFind_Valid_Window {
    /*
           B. Optimal : Keep a window using L and R.
                      Store each character's frequency in HashMap.
                      If current character repeats, window becomes invalid.
                      Shrink from LEFT until the repeated character is removed.
               T = O(2n) = O(n)
               S = O(n) = Map{In worst case all chars are unique so will exist in map}.
    */

    public static void main(String[] args) {

        String str = "abcabcbb";

        /* Step 0 : Variables analogy for window :
                    a. 'l' and 'r' monitor the start and end of the window.
                    b. 'map' stores character → frequency inside window.
                    c. 'ans' stores the maximum valid window length. */

        int l = 0;
        int r = 0;

        int ans = 0;

        java.util.HashMap<Character, Integer> map = new java.util.HashMap<>();


        /* Step 1 : Move R one by one and add current character into window.
                    If character repeats, shrink from LEFT until
                    the current window contains unique characters again. */

        while(r < str.length()) {

            char current = str.charAt(r);

            /* R enters current character, so increase its frequency. */
            map.put(current, map.getOrDefault(current, 0) + 1);

            /*
             * If any character frequency becomes greater than 1,
             * window becomes invalid because a character is repeated.
             */
            if(map.get(current) > 1){

                /*
                 * Shrink from LEFT until current character's
                 * duplicate occurrence is removed.
                 */
                while(map.get(current) > 1){

                    map.put(str.charAt(l), map.get(str.charAt(l)) - 1);

                    if(map.get(str.charAt(l)) == 0){
                        map.remove(str.charAt(l));
                    }

                    l++;
                }
            }

            /* Window is now valid, so update maximum length. */
            ans = Math.max(ans, r - l + 1);

            r++;
        }

        System.out.println(ans);
    }
}

/*
str = "abcabcbb"


Step 1 : R keeps moving and every character enters the window
------- 

[ [a], b, c, a, b, c, b, b]       → map = {a→1}              → length = 1 ✓
[ [a, b], c, a, b, c, b, b]       → map = {a→1, b→1}         → length = 2 ✓
[ [a, b, c], a, b, c, b, b]        → map = {a→1,b→1,c→1}      → length = 3 ✓


Step 2 : R adds 'a' again → duplicate appears
------------------------------------------------------------

[ [a, b, c, a], b, c, b, b]
    map = {a→2, b→1, c→1}
    a frequency = 2 ❌

    Shrink from LEFT:

[ a, [b, c, a], b, c, b, b]
    → remove a
    → map = {a→1, b→1, c→1}
    → length = 3 ✓


Step 3 : R adds 'b' again → duplicate appears
------------------------------------------------------------

[ a, [b, c, a, b], c, b, b]
    map = {a→1, b→2, c→1}
    b frequency = 2 ❌

    Shrink from LEFT:

[ a, b, [c, a, b], c, b, b]
    → remove b
    → map = {a→1, b→1, c→1}
    → length = 3 ✓


Step 4 : R adds 'c' again → duplicate appears
------------------------------------------------------------

[ a, b, [c, a, b, c], b, b]
    map = {a→1, b→1, c→2}
    c frequency = 2 ❌

    Shrink from LEFT:

[ a, b, c, [a, b, c], b, b]
    → remove c
    → map = {a→1, b→1, c→1}
    → length = 3 ✓


Step 5 : R adds 'b' again → duplicate appears
------------------------------------------------------------

[ a, b, c, [a, b, c, b], b]
    map = {a→1, b→2, c→1}
    b frequency = 2 ❌

    Shrink from LEFT:

[ a, b, c, a, b, [c, b], b]
    → remove a
    → map = {a→0, b→2, c→1}
    → remove a from map

    Still b is repeated:

[ a, b, c, a, [b, c, b], b]
    → remove b
    → map = {b→1, c→1}
    → length = 2 ✓


Step 6 : R adds final 'b' → duplicate appears
------------------------------------------------------------

[ a, b, c, a, b, [c, b, b]]
    map = {b→2, c→1}
    b frequency = 2 ❌

    Shrink from LEFT:

[ a, b, c, a, b, c, [b] ]
    → remove c
    → map = {b→2}

    Still b is repeated:

[ a, b, c, a, b, c, [b] ]
    → remove b
    → map = {b→1}

    Now window is valid ✓
    length = 1


Maximum length = 3

============================================================
Remember:

a. R moves → character ENTERS.
b. HashMap → stores character → FREQUENCY.
c. Frequency > 1 → window becomes invalid.
d. L moves → character LEAVES.
e. Frequency becomes 0 → remove character from HashMap.
f. Keep shrinking until every character is unique.
g. ans → maximum valid window length.

============================================================
*/

/*
Important : This is the HEAVY-WHILE version.

            R can make the window invalid.
            Then L keeps moving ONE BY ONE inside
            the inner while until the window becomes valid.

            Unlike the latest-index approach,
            we do not jump L directly.

            Example:

            [ a, b, c, a ]
              ↑       ↑
              L       R

            a repeats.

            Move L:
            [ a, [b, c, a] ]
                 ↑
                 L

            Now duplicate is removed and window is valid.

            Mental Rule:

            R → ENTERS
            duplicate → INVALID
            L → LEAVES one by one
            duplicate removed → VALID
*/

/*
============================================================
Complexity:

Time = O(2n) = O(n)

        R moves from 0 → n-1 exactly once.

        L also moves only forward.
        Whenever a duplicate appears, L moves inside
        the inner while until the window becomes valid.

        Example:
        str = "abcabcbb"

        R → moves through every character once = O(n)
        L → moves forward at most n times       = O(n)

        Therefore:

        O(n) + O(n)
        = O(2n)
        = O(n)


Space = O(n)

        HashMap stores character → frequency.

        Worst case:
        all characters are unique.

        str = "abcdef..."

        map = {a→1, b→1, c→1, d→1, ...}

        Therefore:
        Space = O(n)
============================================================
*/