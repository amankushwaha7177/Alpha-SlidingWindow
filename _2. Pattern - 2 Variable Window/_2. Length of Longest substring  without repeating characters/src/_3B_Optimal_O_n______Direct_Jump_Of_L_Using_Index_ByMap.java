public class _3B_Optimal_O_n______Direct_Jump_Of_L_Using_Index_ByMap {
    /*
    Interview : Given a string, find the length of the longest substring
                without repeating characters.
    Given : Substring = contiguous characters.
            We need the longest window where every character is unique.
            Contiguous + Variable Size + Unique Characters = Sliding Window

    Brain : The simplest mental translation :
            Question says: Find longest substring without repeating characters.
            Sliding-window language:
            Find the longest contiguous window with all unique characters.
            Ex: [ [a, b, c], a, b, c]       → 3 unique characters → Fine
                [ [a, b, c, a], b, c]       → 'a' repeats → ❌ Invalid

    Idea :  A. Normal : Generate all possible substrings and check each
                    substring whether it contains duplicate characters.
               T = O(n²)

           B. Optimal : Keep a window using L and R.
                      Store each character's latest index in HashMap.
                      If current character is already inside the window,
                      directly move L after its previous occurrence.
               T = O(n)
               S = o(n) =Map{In worst case all chars are unique so will exist in map}.
    */

    public static void main(String[] args) {

        String str = "abcabcbb";

        /* Step 0 : Variables analogy for window :
                    a. 'l' and 'r' monitor the start and end of the window.
                    b. 'map' stores character → latest index.
                    c. 'ans' stores the maximum valid window length. */

        int l = 0;
        int r = 0;

        int ans = 0;

        java.util.HashMap<Character, Integer> map = new java.util.HashMap<>();


        /* Step 1 : Move R one by one and check whether current character
                    already exists inside the current window. */

        while(r < str.length()) {

            char current = str.charAt(r);

            /* If character already exists, move L directly after
               its previous occurrence instead of moving one by one. */

            if(map.containsKey(current)) {
                l = Math.max(l, map.get(current) + 1);
            }

            /* Store/update the latest index of current character. */

            map.put(current, r);

            /* Current window is now valid, so calculate its length. */

            ans = Math.max(ans, r - l + 1);

            r++;
        }

        System.out.println(ans);
    }
}

/*
str = "abcabcbb"

[ [a], b, c, a, b, c, b, b]       → "a"       store/update → HashMap = {a=0}            | length = 1
[ [a, b], c, a, b, c, b, b]       → "ab"      store/update → HashMap = {a=0, b=1}       | length = 2
[ [a, b, c], a, b, c, b, b]       → "abc"     store/update → HashMap = {a=0, b=1, c=2}  | length = 3


[ [a, b, c, a], b, c, b, b]
----------->  'a' repeats at index 3. | because HashMap says: a → 0
----------->   Move L directly to = indexOf(a) + 1
                                  = 0 + 1 = 1
[ a, [b, c, a], b, c, b, b]       → "bca"  →  store/update → HashMap = {a=3, b=1, c=2}  | length = 3


[ a, [b, c, a, b], c, b, b]
----------->  'b' repeats at index 4. | because HashMap says: b → 1
----------->   Move L directly to = indexOf(b) + 1
                                  = 1 + 1 = 2
[ a, b, [c, a, b], c, b, b]       → "cab"  →  store/update → HashMap = {a=3, b=4, c=2}  | length = 3



[ a, b, [c, a, b, c], b, b]
'c' repeats at index 5. HashMap says: c → 2 Move L directly:
[ a, b, c, [a, b, c], b, b]       → "abc"    →  store/update → HashMap = {a=3, b=4, c=5} | length = 3


[ a, b, c, [a, b, c, b], b]
'b' repeats at index 6. HashMap says: b → 4 Move L directly:
[ a, b, c, a, b, [c, b], b]       → "cb"    →   store/update → HashMap = {a=3, b=6, c=5}  | length = 2


[ a, b, c, a, b, [c, b, b]]
'b' repeats at index 7. HashMap says: b → 6 Move L directly:
[ a, b, c, a, b, c, b, [b] ]       → "c"     →   store/update → HashMap = {a=3, b=7, c=5}   | length = 1

Maximum length = 3

============================================================
Remember:

a. R moves → character ENTERS.
b. HashMap → stores character's LATEST index.
c. Character repeats → L jumps to previousIndex + 1.
d. Update HashMap with the character's NEW index.
e. ans → maximum valid window length.

============================================================
*/
















/*
Important : L should never move backward.
            HashMap remembers old character positions.

            Example: str = "abba"

            index:   0   1   2   3
                     a   b   b   a

            At r = 2:
            a  b  [b]
                   ↑
                   L = 2

            Now r = 3:
            a  b  [b    a]
            ↑      ↑    ↑
          old a    L    r current a

            HashMap says: HashMap{a → 0}

            If:
            L = map.get(current) + 1
              = 0 + 1
            L = 1  ❌  (L moved backward)

            So:
            L = Math.max(L, map.get(current) + 1)
            L = Math.max(2, 1)
            L = 2  ✓

Mental Rule:
HashMap → OLD position
L       → CURRENT window boundary

Therefore:
L = max(L, oldPosition + 1)
*/












/*
============================================================
Complexity:

Time = O(n)

        R moves from 0 → n-1, so R traversal = O(n).

        When a character repeats:
        L jumps directly using HashMap.

        Example:
        str = "abcabcbb"

        At r = 3, 'a' repeats.
        HashMap says: a → 0

        L = Math.max(L, 0 + 1)
          = 1

        We DO NOT move L one by one.
        So there is no extra O(n) traversal for L.

        Therefore:
        R traversal = O(n)
        L jumps     = O(1) per iteration
        HashMap     = O(1) average operation

        Total = O(n)


Space = O(n)

        HashMap stores characters and their latest indexes.

        In the worst case, all characters are unique:

        str = "abcdef..."

        HashMap = {a→0, b→1, c→2, d→3, ...}

        So HashMap can store up to n characters.

        Therefore:
        Space = O(n)
============================================================
*/