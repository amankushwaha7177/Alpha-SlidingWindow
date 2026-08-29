public class _3_Longest_Substring_Without_Repeating_Character {
    /*
    Interview : Given a string, find the length of the longest substring
                 that contains no repeating characters.

    Given : Substring = contiguous characters.
            We need the longest valid window where every character is unique.

            Contiguous + Variable Size + No Repeating Character = Sliding Window

    Idea :  A. Normal : Generate all possible substrings and check whether
                    each substring contains duplicate characters.

               This takes more time because we repeatedly check characters.

               B. Optimal : Start a window from the beginning and keep increasing R.
                  If the new character is not repeated, window is valid.
                  If the new character is repeated, move L until duplicate is removed.
                  After every valid window, compare its length with the answer.

                  T = O(n)
    */

    public static void main(String[] args) {
        String str = "abcabcbb";

        /* Step 0 : Variables analogy for window :
                    a. 'l' and 'r' monitor the start and end of the window.
                    b. 'set' stores characters currently present inside the window.
                    c. 'ans' stores the maximum valid window length found so far. */

        int l = 0;
        int r = 0;

        int ans = 0;

        java.util.HashSet<Character> set = new java.util.HashSet<>();


        /* Step 1 : Keep moving R and add characters into the window.
                    If the character already exists, window becomes invalid.
                    So remove characters from LEFT until the duplicate is removed. */

        while(r < str.length()) {

            char current = str.charAt(r); // character by character.

            if(set.contains(current)){    // checks that character existence in Set.
                set.remove(str.charAt(l));
                l++;
            } else {
                set.add(current);

                ans = Math.max(ans, r-l+1);

                r++;
            }
        }

        System.out.println(ans);
    }
}

/*
str = "abcabcbb"

[ [a], b, c, a, b, c, b, b]       → window = "a",   length = 1
[ [a, b], c, a, b, c, b, b]       → window = "ab",  length = 2
[ [a, b, c], a, b, c, b, b]       → window = "abc", length = 3

Now 'a' comes again, so window becomes invalid.
Remove from LEFT until duplicate 'a' is removed.

[ a, [b, c, a], b, c, b, b]      → window = "bca", length = 3
[ a, [b, c, a], b, c, b, b]      → R moves to 'b' → duplicate

Shrink:

[ a, b, [c, a, b], c, b, b]      → window = "cab", length = 3

Then 'c' repeats, shrink again.

Maximum length = 3

============================================================
Remember:

a. R moves → character ENTERS the window.
b. L moves → character LEAVES the window.
c. Set → tells whether a character already exists in window.
d. If character is unique → add it and move R.
e. If character repeats → remove from L until valid.
f. ans → maximum valid window length.

============================================================
Complexity:

Time  = O(n)
Space = O(n)
============================================================
*/