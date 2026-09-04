import java.util.HashMap;

public class _1B_Bruteforce__O_n2 {
     /*
    Brain : Generate every possible substring using two loops and check whether
            the current substring contains at least one a, one b, and one c.

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

               Therefore:
               ans += n - j

               Ex : str = "abcab"
                j = 0 → "a"       ❌
                j = 1 → "ab"      ❌
                j = 2 → "abc"     ✓

                Now don't check:
                abc a
                abc ab
                abc abc
                We know all of them are valid because adding characters can never remove a, b, or c.

                 a b c  a b
                [0 1 2] 3 4    =>  5-2 =3 (abc, abca, abcab)
                     j
                Always make formulae from calculation

                So make formulae n - j
                = 5 - 2
                = 3

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


/*
/*
Dry Run : str = "abcabc"

i = 0 → "a" ❌ → "ab" ❌ → "abc" ✓
        n - j = 6 - 2 = 4
        ans = 4

i = 1 → "b" ❌ → "bc" ❌ → "bca" ✓
        n - j = 6 - 3 = 3
        ans = 7

i = 2 → "c" ❌ → "ca" ❌ → "cab" ✓
        n - j = 6 - 4 = 2
        ans = 9

i = 3 → "a" ❌ → "ab" ❌ → "abc" ✓
        n - j = 6 - 5 = 1
        ans = 10

i = 4 → "b" ❌ → "bc" ❌
i = 5 → "c" ❌

Final Answer = 10


============================================================

Main Logic :

Generate every substring using i and j, and once all three characters appear,
count all remaining endings using n - j and stop checking that starting position.

============================================================

Remember :

a. i → starting position.
b. j → ending position.
c. map → stores characters present in current substring.
d. map.size() == 3 → a, b, and c are present.
e. n - j → counts all valid endings from j through n-1.
f. break → remaining endings are already counted.

============================================================

*/