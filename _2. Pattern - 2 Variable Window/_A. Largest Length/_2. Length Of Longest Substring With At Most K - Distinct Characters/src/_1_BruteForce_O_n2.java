public class _1_BruteForce_O_n2 {
    /*
    Idea :  A. Normal : Generate every possible substring and count
                    how many different characters are present inside each substring.
                    If distinct characters <= K, compare its length with answer.
                    T = O(n²).

            B. Optimal : Keep a variable-size sliding window using L and R.
                       Store character → frequency inside HashMap.
                       If map.size() > K, shrink from LEFT until the window becomes valid.
                       T = O(2n) = O(n).
                       S = O(n).
    */

    public static void main(String[] args) {

        String str = "aaabbccd";
        int k = 2;

        /* Step 0 : variables analogy for brute force :
                    a. 'i' chooses the starting point of every possible substring.
                    b. 'j' chooses the ending point while extending the current substring.
                    c. 'map' stores character → frequency inside the current substring.
                    d. 'ans' stores the maximum valid substring length found so far. */

        int ans = 0;

        /*
         * Step 1 : Start every possible substring from each index and keep extending
         * the ending position toward the right while tracking distinct characters.
         */
        for(int i = 0; i < str.length(); i++) {

            java.util.HashMap<Character, Integer> map =
                    new java.util.HashMap<>();

            /*
             * Step 2 : Keep increasing J and add each character into the current substring.
             * The HashMap frequency allows us to know how many distinct characters currently exist.
             */
            for(int j = i; j < str.length(); j++) {

                char current = str.charAt(j);

                /* J enters the current character, so increase its frequency inside this substring. */
                map.put(current, map.getOrDefault(current, 0) + 1);

                /*
                 * If more than K distinct characters exist, this substring becomes invalid.
                 * Further extending this same substring cannot make its distinct count smaller,
                 * so we can stop and start generating substrings from the next starting index.
                 */
                if(map.size() > k) {
                    break;
                }

                /*
                 * The current substring contains at most K distinct characters,
                 * so compare its length against the maximum answer found previously.
                 */
                ans = Math.max(ans, j - i + 1);
            }
        }

        System.out.println(ans);
    }
}


/*
str = "aaabbccd"
k = 2


Step 1 : Start substring from index 0 and keep increasing J
------------------------------------------------------------

i = 0

[ [a], a, a, b, b, c, c, d ]       → map = {a→1}           → size = 1 → length = 1 ✓

[ [a, a], a, b, b, c, c, d ]       → map = {a→2}           → size = 1 → length = 2 ✓

[ [a, a, a], b, b, c, c, d ]       → map = {a→3}           → size = 1 → length = 3 ✓


J adds b:

[ [a, a, a, b], b, c, c, d ]       → map = {a→3,b→1}       → size = 2 → length = 4 ✓

J adds another b:

[ [a, a, a, b, b], c, c, d ]       → map = {a→3,b→2}       → size = 2 → length = 5 ✓


J adds c:

[ [a, a, a, b, b, c], c, d ]

map = {a→3,b→2,c→1}
size = 3

3 > 2 → INVALID ❌

Stop this starting position.


Step 2 : Start substring from index 1 and generate again
------------------------------------------------------------

i = 1

[ a, [a], a, b, b, c, c, d ]       → map = {a→1}           → length = 1 ✓

[ a, [a, a], b, b, c, c, d ]       → map = {a→2}           → length = 2 ✓

[ a, [a, a, b], b, c, c, d ]       → map = {a→2,b→1}       → length = 3 ✓

[ a, [a, a, b, b], c, c, d ]       → map = {a→2,b→2}       → length = 4 ✓

J adds c:

[ a, [a, a, b, b, c], c, d ]

map = {a→2,b→2,c→1}
size = 3

3 > 2 → INVALID ❌

Stop this starting position.


Step 3 : Start substring from index 2 and generate again
------------------------------------------------------------

i = 2

[ a, a, [a], b, b, c, c, d ]       → map = {a→1}           → length = 1 ✓

[ a, a, [a, b], b, c, c, d ]       → map = {a→1,b→1}       → length = 2 ✓

[ a, a, [a, b, b], c, c, d ]       → map = {a→1,b→2}       → length = 3 ✓

[ a, a, [a, b, b, c], c, d ]

map = {a→1,b→2,c→1}
size = 3

3 > 2 → INVALID ❌

Stop this starting position.


Step 4 : Continue the same brute-force process for remaining indexes
---------------------------------------------------------------------

i = 3:

[ a,a,a, [b] ]             → map = {b→1} → length = 1 ✓
[ a,a,a, [b,b] ]           → map = {b→2} → length = 2 ✓

J adds c:

[ a,a,a, [b,b,c] ]         → map = {b→2,c→1} → length = 3 ✓

J adds another c:

[ a,a,a, [b,b,c,c] ]       → map = {b→2,c→2} → length = 4 ✓

J adds d:

[ a,a,a, [b,b,c,c,d] ]

map = {b→2,c→2,d→1}
size = 3

3 > 2 → INVALID ❌


i = 4:

[ a,a,a,b, [b] ]           → map = {b→1} → length = 1 ✓
[ a,a,a,b, [b,c] ]         → map = {b→1,c→1} → length = 2 ✓
[ a,a,a,b, [b,c,c] ]       → map = {b→1,c→2} → length = 3 ✓
[ a,a,a,b, [b,c,c,d] ]

map = {b→1,c→2,d→1}
size = 3

3 > 2 → INVALID ❌


i = 5:

[ a,a,a,b,b, [c] ]         → map = {c→1} → length = 1 ✓
[ a,a,a,b,b, [c,c] ]       → map = {c→2} → length = 2 ✓

J adds d:

[ a,a,a,b,b, [c,c,d] ]

map = {c→2,d→1}
size = 2 → length = 3 ✓


i = 6:

[ a,a,a,b,b,c, [c] ]       → map = {c→1} → length = 1 ✓
[ a,a,a,b,b,c, [c,d] ]     → map = {c→1,d→1} → length = 2 ✓


i = 7:

[ a,a,a,b,b,c,c, [d] ]     → map = {d→1} → length = 1 ✓


Maximum length = 5

Maximum valid substring:

[ a, a, a, b, b ]

It contains exactly 2 distinct characters:
a and b.


============================================================
Remember:

a. Outer loop 'i' chooses every possible starting position for a substring.
b. Inner loop 'j' keeps extending the current substring toward the right.
c. HashMap → stores character → frequency inside the current substring.
d. map.size() → tells the number of DISTINCT characters currently present.
e. map.size() > K → current substring becomes invalid and cannot be extended further.
f. map.size() <= K → current substring is valid and its length can update ans.
g. j - i + 1 → gives the length of the current generated substring.
h. After an invalid substring, break and start again from the next starting position.
i. There is no sliding-window L movement because this is the brute-force approach.

============================================================
Important : This is the BRUTE FORCE pattern.

            Outer loop → chooses the starting position.

            Inner loop → keeps extending the ending position.

            HashMap → tracks character frequencies and distinct character count.

            map.size() <= K → valid substring.

            map.size() > K → invalid substring.

            Duplicate characters do NOT increase map.size()
            because map.size() counts distinct characters only.

            Example:

            "aaabb"

            map = {a→3, b→2}
            map.size() = 2

            Even though five characters exist,
            there are only two DISTINCT characters.


============================================================
Complexity:

Time = O(n²), Space = O(n)

        Outer loop chooses O(n) different starting positions.

        Inner loop can traverse O(n) characters
        for each starting position in the worst case.

        Therefore:

        O(n × n)
        = O(n²)

Space = O(n)

        HashMap can contain up to n distinct characters
        when the input string contains completely unique characters.
============================================================
*/