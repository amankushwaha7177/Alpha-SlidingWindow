public class _3B_More_Optimal_O_n___Direct_Jump_Of_L_Using_Index_ByMap {

    /*
           B. More Optimal :
               Store character → LATEST INDEX in HashMap.
               If map.size() > K, directly identify the character
               currently present at L and jump L after its latest occurrence.

               T = O(n)
               S = O(K) = O(n) in the general case.
    */

    public static void main(String[] args) {

        String str = "aaabbccd";
        int k = 2;

        int l = 0;
        int r = 0;

        int ans = 0;

        java.util.HashMap<Character, Integer> map = new java.util.HashMap<>();

        while(r < str.length()) {

            /* Store CURRENT character → its LATEST index because R has just entered this character. */
            map.put(str.charAt(r), r);

            /*
             * If more than K distinct characters exist, the current window becomes invalid.
             *
             * Instead of moving L one by one, directly jump L after the latest
             * occurrence of the character currently present at the LEFT boundary.
             */
            if(map.size() > k) {

                char leftChar = str.charAt(l);

                /*
                 * The character at L is the character that must completely leave
                 * the window, so find its latest occurrence and jump L after it.
                 */
                l = map.get(leftChar) + 1;

                /*
                 * The left character is now completely outside the new window,
                 * so remove its latest-index entry from the HashMap.
                 */
                map.remove(leftChar);
            }

            /* Window now contains at most K distinct characters, so compare its length with ans. */
            ans = Math.max(ans, r - l + 1);

            r++;
        }

        System.out.println(ans);
    }
}


/*
============================================================
DRY RUN
============================================================

str = "aaabbccd"
k = 2


R = 0 → 'a'

[ [a], a, a, b, b, c, c, d ]

map = {a → 0}
types = 1
length = 1
ans = 1


R = 1 → 'a'

[ [a, a], a, b, b, c, c, d ]

map = {a → 1}
types = 1
length = 2
ans = 2


R = 2 → 'a'

[ [a, a, a], b, b, c, c, d ]

map = {a → 2}
types = 1
length = 3
ans = 3


R = 3 → 'b'

[ [a, a, a, b], b, c, c, d ]

map = {a → 2, b → 3}
types = 2
length = 4
ans = 4


R = 4 → 'b'

[ [a, a, a, b, b], c, c, d ]

map = {a → 2, b → 4}
types = 2
length = 5
ans = 5


R = 5 → 'c'

R adds c:

[ [a, a, a, b, b, c], c, d ]

map = {a → 2, b → 4, c → 5}
types = 3 → INVALID ❌


L = 0

leftChar = 'a'

latest index of 'a' = 2

Instead of:

L → 1 → 2 → 3

Directly jump:

L = 2 + 1
L = 3


Remove 'a' from map:

map = {b → 4, c → 5}
types = 2 → VALID ✓


New window:

[ a, a, a, [b, b, c], c, d ]
          ↑
          L

length = 5 - 3 + 1
length = 3

ans = max(5, 3)
ans = 5


R = 6 → 'c'

[ a, a, a, [b, b, c, c], d ]

map = {b → 4, c → 6}
types = 2 → VALID ✓

length = 6 - 3 + 1
length = 4

ans = 5


R = 7 → 'd'

R adds d:

[ a, a, a, [b, b, c, c, d] ]

map = {b → 4, c → 6, d → 7}
types = 3 → INVALID ❌


L = 3

leftChar = 'b'

latest index of 'b' = 4

Directly jump:

L = 4 + 1
L = 5


Remove 'b' from map:

map = {c → 6, d → 7}
types = 2 → VALID ✓


New window:

[ a, a, a, b, b, [c, c, d] ]
                ↑
                L

length = 7 - 5 + 1
length = 3

ans = max(5, 3)
ans = 5


Maximum length = 5


============================================================
WHY DIRECT JUMP WORKS
============================================================

When R adds 'c':

[ a, a, a, b, b, c ]

map = {a → 2, b → 4, c → 5}

types = 3 → INVALID ❌


L is currently at index 0:

[ [a, a, a, b, b, c] ]
  ↑
  L

leftChar = 'a'

HashMap remembers:

a → 2

Therefore we know that the LAST occurrence of 'a'
inside the current window is at index 2.

So instead of:

L = 1
L = 2
L = 3

we directly jump:

L = 2 + 1
L = 3


Now:

[ a, a, a, [b, b, c] ]
          ↑
          L

All 'a' characters are outside the new window.

Only:

b + c

remain.

Therefore:

map.size() = 2 → VALID ✓


============================================================
Remember:

a. R moves → character ENTERS the current sliding window.
b. HashMap → stores character → LATEST INDEX.
c. map.size() → tells how many DISTINCT characters exist currently.
d. map.size() > K → window becomes INVALID.
e. str.charAt(l) → tells which character currently exists at LEFT.
f. map.get(leftChar) → gives that character's LATEST INDEX.
g. L = latestIndex + 1 → directly jumps L after that character.
h. Remove leftChar from HashMap because it is completely outside the new window.
i. map.size() <= K → window is valid and its length can update ans.
j. ans → stores the maximum valid window length found so far.

============================================================
Important : This is the 3B DIRECT-JUMP pattern.

            Pattern 2 → HeavyWhile

                       R → ENTERS
                       map.size() > K
                       ↓
                       INVALID
                       ↓
                       L → L → L → ...
                       ↓
                       VALID


            Pattern 3A → NoWhile

                        R → ENTERS
                        map.size() > K
                        ↓
                        INVALID
                        ↓
                        L → ONE STEP
                        ↓
                        R continues


            Pattern 3B → DirectJump

                        R → ENTERS
                        map.size() > K
                        ↓
                        INVALID
                        ↓
                        Find leftChar = str.charAt(L)
                        Find latest index using HashMap
                        ↓
                        L → JUMPS directly
                        ↓
                        VALID


============================================================
Complexity:

Time = O(n), Space = O(K)

R moves through every character once, and each character's latest index
is inserted or updated once while L only moves forward through the string.

For a general string, HashMap can contain up to K+1 distinct characters
temporarily before one character is removed from the window.
============================================================
*/