public class _1_BruteForce_O_n2 {
    /*
    Idea :  A. Brute Force : Generate every possible substring using L and R.
                       For each substring, use a HashSet to check whether
                       all characters are unique.
                       If duplicate character is found, stop checking
                       the current substring.

               T = O(n²)
               Space = O(n)
    */

    public static void main(String[] args) {

        String str = "abcabcbb";

        int ans = 0;

        /*
        Step 1 : Start every possible substring from each index.
                 'l' decides where the substring starts.
        */
        for(int l = 0; l < str.length(); l++){

            java.util.HashSet<Character> set = new java.util.HashSet<>();

            /*
            Step 2 : Keep increasing R and add characters one by one.
                     If character already exists, current substring
                     contains duplicate character, so stop.
            */
            for(int r = l; r < str.length(); r++){

                char current = str.charAt(r);

                if(set.contains(current)){
                    break;
                }

                set.add(current);

                ans = Math.max(ans, r - l + 1);
            }
        }

        System.out.println(ans);
    }
}

/*
str = "abcabcbb"

All possible valid substrings:

[ [a], b, c, a, b, c, b, b]       → "a"       → length = 1
[ [a, b], c, a, b, c, b, b]       → "ab"      → length = 2
[ [a, b, c], a, b, c, b, b]       → "abc"     → length = 3
[ [a, b, c, a], b, c, b, b]       → duplicate 'a' → STOP

[ a, [b], c, a, b, c, b, b]       → "b"       → length = 1
[ a, [b, c], a, b, c, b, b]       → "bc"      → length = 2
[ a, [b, c, a], b, c, b, b]       → "bca"     → length = 3
[ a, [b, c, a, b], c, b, b]       → duplicate 'b' → STOP

[ a, b, [c], a, b, c, b, b]       → "c"       → length = 1
[ a, b, [c, a], b, c, b, b]       → "ca"      → length = 2
[ a, b, [c, a, b], c, b, b]       → "cab"     → length = 3
[ a, b, [c, a, b, c], b, b]       → duplicate 'c' → STOP

Maximum length = 3

============================================================

Remember:

a. L → chooses the starting point of substring.
b. R → keeps expanding the current substring.
c. HashSet → checks whether character already exists.
d. Duplicate found → STOP current substring.
e. ans → maximum valid substring length.

============================================================

Complexity:

Outer loop → O(n)
Inner loop → O(n)
HashSet operations → O(1) average

Time  = O(n²)
Space = O(n)
============================================================
*/