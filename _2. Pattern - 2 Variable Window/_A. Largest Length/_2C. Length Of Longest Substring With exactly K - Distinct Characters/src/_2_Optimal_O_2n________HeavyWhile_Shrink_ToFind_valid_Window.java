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


        int l = 0;
        int r = 0;

        int ans = 0;

        java.util.HashMap<Character, Integer> map = new java.util.HashMap<>();

        while(r < str.length()) {

            map.put(str.charAt(r), map.getOrDefault(str.charAt(r), 0) + 1);

            if(map.size() > k) {

                while(map.size() > k) {

                    map.put(str.charAt(l), map.get(str.charAt(l)) - 1);

                    if(map.get(str.charAt(l)) == 0) {
                        map.remove(str.charAt(l));
                    }
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

*/