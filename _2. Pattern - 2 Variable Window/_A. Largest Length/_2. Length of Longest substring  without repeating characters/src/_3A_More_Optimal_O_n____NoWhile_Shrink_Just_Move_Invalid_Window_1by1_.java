public class _3A_More_Optimal_O_n____NoWhile_Shrink_Just_Move_Invalid_Window_1by1_ {
    /*
    Idea :  B. Optimal : Keep a window using L and R.
                      Store each character's frequency in HashMap.
                      If current character repeats, window becomes invalid.
                      Move L only ONE step instead of repeatedly shrinking.
                      The window does not need to become valid immediately.
               T = O(n)
               S = O(n) = Map{In worst case all chars are unique so will exist in map}.
    */

    public static void main(String[] args) {

        String str = "abcabcbb";

        /* Step 0 : Variables analogy for window, where L and R monitor the current substring boundaries. */
        int l = 0;
        int r = 0;

        int ans = 0;

        java.util.HashMap<Character, Integer> map = new java.util.HashMap<>();

        /* Step 1 : Move R one by one and add every current character into the HashMap frequency. */
        while(r < str.length()) {

            char current = str.charAt(r);

            /* R enters current character, so increase its frequency inside the current sliding window. */
            map.put(current, map.getOrDefault(current, 0) + 1);

            /*
             * If current character frequency becomes greater than one, the current character is duplicated.
             * Since this is 3A NoWhile, move L only ONE position and continue with the next R.
             */
            if(map.get(current) > 1) {

                /* L removes exactly one character from the left side of the current sliding window. */
                char leftChar = str.charAt(l);

                map.put(leftChar, map.get(leftChar) - 1);

                /* Remove the character completely when its frequency becomes zero inside the window. */
                if(map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }

                l++;
            }

            /*
             * Update answer only when the current character is no longer duplicated after L moves.
             * This follows the same simple condition used by this particular 3A learning pattern.
             */
            if(map.get(current) == 1) {
                ans = Math.max(ans, r - l + 1);
            }

            r++;
        }

        System.out.println(ans);
    }
}

/*
str = "abcabcbb"


Step 1 : R keeps moving and every character enters the window
----------------------------------------------------------------

[ [a], b, c, a, b, c, b, b ] → map = {a→1} → length = 1 ✓
[ [a, b], c, a, b, c, b, b ] → map = {a→1,b→1} → length = 2 ✓
[ [ [a, b, c], a, b, c, b, b ] → map = {a→1,b→1,c→1} → length = 3 ✓


Step 2 : R adds 'a' again and current character becomes duplicated
----------------------------------------------------------------

[ [a, b, c, a], b, c, b, b ]

map = {a→2, b→1, c→1}
current = 'a'
'a' frequency = 2 → INVALID

3A NoWhile:
Move L only ONE position.

L removes 'a':

[ a, [b, c, a], b, c, b, b ]

map = {a→1, b→1, c→1}
current 'a' frequency = 1

length = 3
ans = 3


Step 3 : R adds 'b' again and current character becomes duplicated
----------------------------------------------------------------

[ a, [b, c, a, b], c, b, b ]

map = {a→1, b→2, c→1}
current = 'b'
'b' frequency = 2 → INVALID

L removes 'b':

[ a, b, [c, a, b], c, b, b ]

map = {a→1, b→1, c→1}
current 'b' frequency = 1

length = 3
ans = 3


Step 4 : R adds 'c' again and current character becomes duplicated
----------------------------------------------------------------

[ a, b, [c, a, b, c], b, b ]

map = {a→1, b→1, c→2}
current = 'c'
'c' frequency = 2 → INVALID

L removes 'c':

[ a, b, c, [a, b, c], b, b ]

map = {a→1, b→1, c→1}
current 'c' frequency = 1

length = 3
ans = 3


Step 5 : R adds 'b' again and current character becomes duplicated
----------------------------------------------------------------

[ a, b, c, [a, b, c, b], b ]

map = {a→1, b→2, c→1}
current = 'b'
'b' frequency = 2 → INVALID

L removes only 'a':

[ a, b, c, a, [b, c, b], b ]

map = {b→2, c→1}
current 'b' frequency = 2 → STILL INVALID

3A does NOT shrink again because there is NO inner while.

The next R movement continues from this state.


Step 6 : R adds final 'b' while the window is still invalid
----------------------------------------------------------------

Current window:

[ b, c, b ]

map = {b→2, c→1}
current = 'b'
'b' frequency = 2 → INVALID

R adds final 'b':

[ b, c, b, b ]

map = {b→3, c→1}
current = 'b'
'b' frequency = 3 → INVALID

L removes only one 'b':

[ b, [c, b, b] ]

map = {b→2, c→1}
current 'b' frequency = 2 → STILL INVALID

Again, there is NO inner while, so R continues.


Final Answer = 3


============================================================
Remember:

a. R moves → character ENTERS the current sliding window.
b. HashMap → stores character → FREQUENCY inside the current window.
c. Frequency > 1 → current character has become duplicated.
d. L moves → exactly ONE position when the current character is duplicated.
e. Frequency becomes 0 → completely remove that character from HashMap.
f. No inner while → L never repeatedly shrinks during the same R iteration.
g. Window can remain invalid → R continues moving from the current state.
h. This is the 3A NoWhile pattern, not the Pattern 2 HeavyWhile approach.

============================================================
Important : Pattern comparison:

            Pattern 2 → HeavyWhile
                       R → ENTERS → INVALID
                       L → L → L → ...
                       Continue until required condition becomes valid.

            Pattern 3A → NoWhile
                        R → ENTERS → INVALID
                        L → ONE step only
                        Window may remain INVALID
                        R → continues

            Pattern 3B → DirectJump
                        R → ENTERS → INVALID
                        L → jumps directly using stored index information.

============================================================
Complexity:

Time = O(n), Space = O(n)

R moves n times and L moves at most one position for every R iteration.
============================================================
*/