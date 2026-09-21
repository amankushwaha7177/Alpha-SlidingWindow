class _3A_More_Optimal_O_n___NoWhile_Shrink_Just_Move_Invalid_Window_1by1_ {
    /*
    Interview : Given a string and K, find the length of the longest substring
                containing at most K distinct characters.

    Given : We need a contiguous substring containing at most K different characters.
            If the window contains more than K distinct characters, it becomes invalid.

            Contiguous + Variable Size + At Most K Distinct Characters + SubString
            = Variable Window

            subString = window

     Brain : The simplest mental translation :
             Question says: Find longest substring with at most K distinct characters.

             Sliding-window language:
             Find the longest contiguous window containing at most K different characters.

             Ex : k = 2
             [ [a, a, b, b], c, c, d]       → 2 distinct characters → Fine ✓
             [ [a, a, b, b, c], c, d]       → 3 distinct characters → ❌ Invalid

     Idea :  A. Normal :
                Generate every possible substring and count distinct characters.
                If distinct characters <= K, compare its length with answer.
                T = O(n²).

            B. Previous optimal :
               Start a variable window and keep increasing R.
               Store character → frequency inside HashMap.
               If map.size() > K, shrink from LEFT until window becomes valid.
               T = O(2n) = O(n).

            C. More Optimal :
               Start a variable window and keep increasing R.
               Store character → frequency inside HashMap.
               If map.size() > K, move L only ONE position.
               This compensates for R's new element and keeps window size controlled.
               The window may remain invalid, but R continues moving forward.
               T = O(n).
    */

    public static void main(String[] args) {

        String str = "aaabbccd";
        int k = 2;

        /* Step 0 : variables analogy for window :
                    a. 'l' and 'r' monitor the start and end points of window.
                    b. 'map' stores character → frequency inside current window.
                    c. 'ans' stores the maximum valid window length found so far. */

        int l = 0;
        int r = 0;

        int ans = 0;

        java.util.HashMap<Character, Integer> map = new java.util.HashMap<>();

        /*
         * Step 1 : Variable size substring means the window can increase or decrease.
         * Start from index 0 and keep increasing R while tracking distinct characters.
         */
        while(r < str.length()) {

            /*
             * R enters one character at a time, so increase its frequency inside the current window.
             */
            map.put(str.charAt(r), map.getOrDefault(str.charAt(r), 0) + 1);

            /*
             * If more than K distinct characters exist, the current window becomes invalid.
             * Since this is the 3A NoWhile pattern, move L only ONE position.
             */
            if(map.size() > k) {

                /*
                 * The character at L leaves the window, so decrease its frequency by exactly one.
                 */
                map.put(str.charAt(l), map.get(str.charAt(l)) - 1);

                /*
                 * If its frequency becomes zero, completely remove that character from the HashMap.
                 */
                if(map.get(str.charAt(l)) == 0) {
                    map.remove(str.charAt(l));
                }

                /*
                 * Move L only one position instead of repeatedly shrinking inside a while loop.
                 */
                l++;
            }

            /*
             * The window may still be invalid after moving L only once.
             * Update the answer only when the current window contains at most K distinct characters.
             */
            if(map.size() <= k) {
                ans = Math.max(ans, r - l + 1);
            }

            r++;
        }

        System.out.println(ans);
    }
}


/*
str = "aaabbccd"
k = 2


Step 1 : Start window and keep increasing R
-------  k = 2

         [ [a], a, a, b, b, c, c, d ]       → map = {a→1}           → length = 1 ✓
         [ [a, a], a, b, b, c, c, d ]       → map = {a→2}           → length = 2 ✓
         [ [a, a, a], b, b, c, c, d ]       → map = {a→3}           → length = 3 ✓

         R adds b:

         [ [a, a, a, b], b, c, c, d ]       → map = {a→3,b→1}       → size = 2 ✓
         length = 4


         R adds another b:

         [ [a, a, a, b, b], c, c, d ]       → map = {a→3,b→2}       → size = 2 ✓
         length = 5


Step 2 : R adds c, so distinct character count becomes greater than K
---------------------------------------------------------------------

         [ [a, a, a, b, b, c], c, d ]

         map = {a→3, b→2, c→1}
         map.size() = 3
         K = 2

         3 > 2 → INVALID ❌

         3A NoWhile says:
         Move L only ONE position.

         L removes 'a':

         [ a, [a, a, b, b, c], c, d ]

         map = {a→2, b→2, c→1}
         map.size() = 3
         Window is STILL INVALID ❌

         IMPORTANT:
         We do NOT shrink again because there is NO inner while.

         Do NOT update ans.


Step 3 : R continues moving while L also moves one position
------------------------------------------------------------

         R adds c:

         [ a, [a, a, b, b, c, c], d ]

         map = {a→2, b→2, c→2}
         map.size() = 3
         Window is INVALID ❌

         Move L only ONE position:

         [ a, a, [a, b, b, c, c], d ]

         map = {a→1, b→2, c→2}
         map.size() = 3
         Window is STILL INVALID ❌

         Do NOT update ans.


Step 4 : R adds d while the window is still invalid
------------------------------------------------------------

         [ a, a, a, [b, b, c, c, d] ]

         map = {a→1, b→2, c→2, d→1}
         map.size() = 4
         Window is INVALID ❌

         Move L only ONE position:

         [ a, a, a, b, [b, c, c, d] ]

         map = {a→0, b→2, c→2, d→1}

         Remove a because its frequency becomes zero.

         map = {b→2, c→2, d→1}
         map.size() = 3
         Window is STILL INVALID ❌

         Do NOT update ans.


Final Answer = 5

Maximum valid substring found:

[ a, a, a, b, b ]

Length = 5

============================================================
Remember:

a. right moves → character ENTERS the current sliding window.
b. HashMap → stores character → FREQUENCY inside the current window.
c. map.size() → gives the number of DISTINCT characters currently present.
d. map.size() > K → window becomes invalid because too many distinct characters exist.
e. left moves → exactly ONE position whenever the window becomes invalid.
f. Character frequency decreases → because the leftmost character leaves the window.
g. Frequency becomes 0 → completely remove that character from the HashMap.
h. map.size() <= K → current window is valid and can update ans.
i. No inner while → L moves only one position during each R iteration.
j. Window may remain invalid → R continues moving without repeatedly shrinking L.
k. ans → stores the maximum valid window length found throughout the traversal.

============================================================
Important : This is the 3A NO-WHILE pattern.

            R → ENTERS one character.

            map.size() > K
            ↓
            Window becomes INVALID.

            L → moves only ONE position.

            After L moves:
            Window may STILL be INVALID.

            We do NOT use while.

            R → continues moving.

            This keeps the window size from continuously
            increasing while we search for the maximum length.

            Pattern 2 → HeavyWhile

                       R → ENTERS
                       Window → INVALID
                       L → L → L → ...
                       Window → VALID
                       Then update answer


            Pattern 3A → NoWhile

                        R → ENTERS
                        Window → INVALID
                        L → ONE step
                        Window may remain INVALID
                        R → continues


            Pattern 3B → DirectJump

                        R → ENTERS
                        Window → INVALID
                        L → jumps directly
                        using stored character/index information.

============================================================
Complexity:

Time = O(n), Space = O(n)

R moves from 0 → n-1 exactly once, and L moves at most once for every R movement.

The HashMap can contain at most all unique characters from the input string.
============================================================
*/