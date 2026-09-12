public class _2_Optimal_O_2n________HeavyWhile_Shrink_ToFind_valid_Window {
    /*
    Idea :  A. Normal : Generate every possible substring and check
                    how many different characters it contains.
                T = O(n²)

            B. Optimal : Keep a variable-size window using L and R.
                       Store character → frequency inside HashMap.
                       If different characters become more than K,
                       shrink from LEFT until exactly K characters remain.
                T = O(2n) = O(n)
                S = O(n) = HashMap can store different characters.
    */

    public static void main(String[] args) {
        String str = "aabbaccc";
        int k = 2;

        /* Step 0 : variables analogy for window :
                    a. 'l' and 'r' monitor the start and end of the substring.
                    b. 'map' stores character → frequency inside the window.
                    c. 'ans' stores the maximum valid substring length. */

        int l = 0;
        int r = 0;

        int ans = 0;

        java.util.HashMap<Character, Integer> map = new java.util.HashMap<>();

        /*
         * Step 1 : Move R one by one and add each character into the substring.
         * If different characters become more than K, the substring becomes invalid.
         */
        while(r < str.length()) {

            map.put(str.charAt(r), map.getOrDefault(str.charAt(r), 0) + 1);

            /*
             * getOrDefault means: Give me the value for this key;
             * if the key does not exist, give me the provided default value.
             */

            /*
             * More than K different characters means the substring is invalid,
             * so we must shrink from LEFT until exactly K characters remain.
             */
            if(map.size() > k) {

                /*
                 * If the substring is invalid, keep moving L until exactly K
                 * different characters remain inside the current substring.
                 */
                while(map.size() > k) {

                    /*
                     * The character at L leaves the substring, so reduce its frequency
                     * because one occurrence of that character is leaving the substring.
                     */
                    map.put(str.charAt(l), map.get(str.charAt(l)) - 1);

                    /*
                     * If frequency becomes zero, this character no longer exists
                     * anywhere inside the current substring, so remove it completely.
                     */
                    if(map.get(str.charAt(l)) == 0) {
                        map.remove(str.charAt(l));
                    }

                    /*
                     * Above we removed the character at L from the active substring,
                     * so only after that operation we move L forward one position.
                     */
                    l++;
                }
            }

            /*
             * The substring is valid only when it contains exactly K distinct characters,
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
str = "aabbaccc"
k = 2


Step 1 : R keeps moving and every character enters the substring
-----------------------------------------------------------------

[ [a], a, b, b, a, c, c, c ]       → map = {a→1}       → size = 1 → Not enough

[ [a,a], b, b, a, c, c, c ]       → map = {a→2}       → size = 1 → Not enough

[ [a,a,b], b, a, c, c, c ]       → map = {a→2, b→1} → size = 2 → VALID → length = 3

[ [a,a,b,b], a, c, c, c ]       → map = {a→2, b→2} → size = 2 → VALID → length = 4

[ [a,a,b,b,a], c, c, c ]       → map = {a→3, b→2} → size = 2 → VALID → length = 5


Step 2 : R adds c, so the number of distinct characters becomes greater than K
------------------------------------------------------------------------------

[ [a,a,b,b,a,c] ]

map = {a→3, b→2, c→1}
size = 3

3 > 2 → INVALID ❌


    Step 2B: Shrink from LEFT until exactly K distinct characters remain
    --------------------------------------------------------------------

    L removes first a:

    [ a, [a,b,b,a,c] ]

    map = {a→2, b→2, c→1}
    size = 3 ❌

    Substring is still invalid because three distinct characters remain.


    L removes second a:

    [ a,a, [b,b,a,c] ]

    map = {a→1, b→2, c→1}
    size = 3 ❌

    Substring is still invalid because three distinct characters remain.


    L removes first b:

    [ a,a,b, [b,a,c] ]

    map = {a→1, b→1, c→1}
    size = 3 ❌

    Substring is still invalid because three distinct characters remain.


    L removes second b:

    [ a,a,b,b, [a,c] ]

    map = {a→1, b→0, c→1}

    Frequency of b became zero, so remove b from HashMap.

    map = {a→1, c→1}
    size = 2 ✓

    Now the substring contains exactly K distinct characters.


    Current valid substring:

    [ a, c ]

    length = 6 - 4
    length = 2

    ans = max(5, 2)
    ans = 5


Final Answer = 5

Maximum valid substring:

"aabba"

It contains exactly two distinct characters:
a and b.


============================================================
Remember:

a. R moves → character ENTERS the current sliding window.
b. HashMap → stores character and its frequency inside the substring.
c. map.size() → tells how many DISTINCT characters currently exist.
d. map.size() < K → substring does not have enough distinct characters.
e. map.size() == K → substring is VALID and we calculate maximum length.
f. map.size() > K → substring becomes INVALID because too many distinct characters exist.
g. L moves → character LEAVES from the left side of the substring.
h. Frequency decreases → because one occurrence of the leftmost character leaves.
i. Frequency becomes 0 → completely remove that character from HashMap.
j. Continue shrinking until map.size() == K.
k. ans → stores the maximum valid substring length found so far.


============================================================
Solid Interview Understanding :
============================================================

Q. Why do we check map.size() == K for the answer?

A. The question asks for EXACTLY K distinct characters,
   so only a substring containing exactly K different characters is valid.

   If map.size() < K:

       Substring is not valid because it has fewer than K distinct characters.

   If map.size() == K:

       Substring is VALID because it has exactly K distinct characters.

   If map.size() > K:

       Substring is INVALID because it has more than K distinct characters.

   Therefore, first handle the invalid condition:

       if(map.size() > k)

   Inside it, shrink the substring:

       while(map.size() > k)

   Once the invalid condition is removed, the substring becomes:

       map.size() == k

   Then calculate:

       ans = Math.max(ans, r - l + 1);

   This is the main thinking pattern for EXACTLY K distinct substring problems.


============================================================
Important : This is the HEAVY-WHILE version.

            R → ENTERS character.

            map.size() > K
            ↓
            Substring becomes INVALID.

            L keeps moving ONE BY ONE:

            L → L → L → ...

            Each movement removes one character occurrence.

            When a character frequency becomes 0,
            remove that character from HashMap.

            Continue shrinking until:

            map.size() == K

            Now:

            Substring → VALID

            Then calculate the maximum substring length.


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

Space is O(n) because the HashMap can store up to n different characters
when the input string contains n unique characters.
============================================================
*/