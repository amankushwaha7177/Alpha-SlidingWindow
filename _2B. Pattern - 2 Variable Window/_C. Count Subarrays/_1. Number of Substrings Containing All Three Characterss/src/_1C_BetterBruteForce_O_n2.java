import java.util.HashMap;

public class _1C_BetterBruteForce_O_n2 {

    /*
    Brain : Same just if Interviewer ask dont use hashmap
            Than use only 3 boolean variable to track all 3 characters presence.
            and the the same validation job that we done using Hashmap to check

            Ie. Instead of checking size of map check all truthy vars.
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
        for(int i = 0; i < n; i++) {

            boolean hasA = false;
            boolean hasB = false;
            boolean hasC = false;


            /* Step 2 : Extend the current substring using J until all
                        three required characters become available. */

            for(int j = i; j < n; j++) {

                char current = str.charAt(j);


                /* Step 3 : Mark the current character as present because
                            frequency is unnecessary for this requirement. */

                if(current == 'a') {
                    hasA = true;
                }
                else if(current == 'b') {
                    hasB = true;
                }
                else {
                    hasC = true;
                }


                /* Step 4 : Once all three characters are present, every
                            substring extending from this J to the final index
                            will also contain all three required characters. */

                if(hasA && hasB && hasC) {

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
= 6- 2
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
*/