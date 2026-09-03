import java.util.HashMap;

public class _1B_Bruteforce_O_n2 {
    /*
    Brain : Generate every possible starting position using i and extend the
            substring using j until all three required characters are present.

            Once a substring contains a, b, and c, every longer substring
            having the same starting position will also contain all three.

            Therefore, instead of checking every remaining ending position,
            directly count all remaining valid substrings using n - j.

    Idea : A. Normal :

               Generate every possible substring and check whether it contains
               all three characters using three boolean variables.

               T = O(n²)
               S = O(1)

           B. Better Brute Force :

               Generate substrings using i and j.

               Once a, b, and c are found, all substrings from the current
               j position until the final index are automatically valid.

               Therefore:
               ans += n - j

               Ex : str = "abcabc"
                j = 0 → "a"       ❌
                j = 1 → "ab"      ❌
                j = 2 → "abc"     ✓

                Now don't check:
                abc a
                abc ab
                abc abc
                We know all of them are valid because adding characters can never remove a, b, or c.

                So n - j
                = 6 - 2
                = 4

               Then break because we do not need to check those substrings individually.

               T = O(n²)
               S = O(1)
    */

    public static void main(String[] args) {

        String str = "abcabc";

        /* Step 0 : Variables analogy for better brute force :

                    a. 'i' selects the starting position of the substring.
                    b. 'j' extends the substring toward the right.
                    c. 'hasA' tracks whether character a exists in the substring.
                    d. 'hasB' tracks whether character b exists in the substring.
                    e. 'hasC' tracks whether character c exists in the substring.
                    f. 'ans' stores the total number of valid substrings. */

        int ans = 0;

        int n = str.length();


        /* Step 1 : Select every possible starting position using the outer loop. */
        for(int i = 0; i < str.length(); i++) {

            HashMap<Character, Integer> map = new HashMap<>();

            for(int j = i; j < str.length(); j++) {

                char current = str.charAt(j);

                map.put(current, map.getOrDefault(current, 0) + 1);

                /*
                Once the substring contains all three characters, every longer
                substring starting from the same i will also contain all three.
                */

                if(map.size() == 3) {
                    ans += n - j;
                    break;
                }
            }
        }
        System.out.println(ans);

        /*
        Complexity :

        Time = O(n²)
        Reason : In the worst case, for every starting position i, the inner
                 loop may still move through many positions before finding all three.

        Space = O(1)
        Reason : Only three boolean variables are used to track character presence,
                 so the extra memory remains constant regardless of input length.
        */

        System.out.println("Time = O(n²), Space = O(1)");
    }
}


/*
Example : str = "abcabc"

i = 0

j = 0
[a] → a ✓, b ✗, c ✗
       → Invalid

j = 1
[ab] → a ✓, b ✓, c ✗
       → Invalid

j = 2
[abc] → a ✓, b ✓, c ✓
        → Valid

Now:

n - j
= 6 - 2
= 4

We directly count:

[abc]     ✓
[abca]    ✓
[abcab]   ✓
[abcabc]  ✓

ans = 4

Then break because every longer substring with the same starting position
has already been counted using n - j.


i = 1

j = 1
[b] → Invalid

j = 2
[bc] → Invalid

j = 3
[bca] → Valid

n - j
= 6 - 3
= 3

Count:

[bca]    ✓
[bcab]   ✓
[bcabc]  ✓

ans = 7


Continue the same process for every starting position.

Final Answer = 10


============================================================

Remember :

a. i → fixes the starting position.
b. j → moves toward the ending position.
c. hasA → checks whether a exists.
d. hasB → checks whether b exists.
e. hasC → checks whether c exists.
f. All three true → current substring is valid.
g. n - j → counts all remaining valid endings.
h. break → no need to individually check those remaining endings.

============================================================

Important Counting Logic :

Current substring = [ i ........ j ........ n-1 ]

If [ i ........ j ] already contains a, b, and c,

then these are all valid:

[ i ........ j ]
[ i ........ j+1 ]
[ i ........ j+2 ]
...
[ i ........ n-1 ]

Number of valid substrings:

n - j

============================================================

Complexity :

Time = O(n²)
        There are still two nested loops in the worst case.

Space = O(1)
         Only three boolean variables are maintained.

============================================================
*/