public class _2_Optimal_O_n_________LastIndex_Approach {

    /*
    Interview : Given a string containing only a, b, and c, find the number of
                substrings that contain at least one a, one b, and one c.

    Given : We need to count every substring that contains all three characters.
            The substring must be contiguous, so we cannot skip any characters.

            Contiguous + Size is Not Fixed + Contains a,b,c + SubString = Variable Window
            subString = window

    Brain : The simplest mental translation :

            Question says:
            Count all substrings containing at least one a, one b, and one c.

            Sliding-window language:
            For every R, find how many valid starting positions can create
            a substring ending at the current R.

            Instead of physically shrinking L, store the latest index of
            a, b, and c and use the minimum latest index.

            Ex : "abcabc"

                 R
                 ↓
            [ a, b, c ]

            latest a = 0
            latest b = 1
            latest c = 2

            Minimum latest index = 0

            Therefore starting positions 0 through 0 are valid.
            Number of valid substrings = 0 + 1 = 1.

    Idea : A. Normal :

               Generate every possible substring and check whether it contains
               all three characters.

               T = O(n²)

           B. Optimal :

               Move R from left to right only once.

               Store the latest index of a, b, and c.

               Once all three characters are available, the minimum latest
               index tells us the maximum starting index boundary.

               Every starting position from 0 through that minimum index
               creates a valid substring ending at the current R.

               Therefore :

               ans += min(lastA, lastB, lastC) + 1

               T = O(n)
               S = O(1)
    */

    public static void main(String[] args) {

        String str = "abcabc";

        /* Step 0 : Variables analogy for the last-index approach :

                    a. 'r' monitors the current ending position of the substring.
                    b. 'lastA' stores the latest position where character a appeared.
                    c. 'lastB' stores the latest position where character b appeared.
                    d. 'lastC' stores the latest position where character c appeared.
                    e. 'ans' stores the total number of valid substrings found. */

        int lastA = -1;
        int lastB = -1;
        int lastC = -1;

        int ans = 0;


        /* Step 1 : Move R one by one and update the latest position of
                    whichever character currently enters the window. */

        for(int r = 0; r < str.length(); r++) {

            if(str.charAt(r) == 'a') {
                lastA = r;
            }
            else if(str.charAt(r) == 'b') {
                lastB = r;
            }
            else {
                lastC = r;
            }


            /* Step 2 : Find the minimum latest index among a, b, and c.

                        The minimum index becomes the boundary because every
                        starting position from 0 through this index contains
                        at least one occurrence of all three characters. */

            int minIndex = Math.min(lastA, Math.min(lastB, lastC));


            /* Step 3 : If all three characters have appeared, count every
                        valid substring ending at the current R. */

            if(minIndex != -1) {
                ans += minIndex + 1;
            }
        }


        System.out.println(ans);

        /*
        Complexity :

        Time = O(n)
        Reason : R moves from the first character to the last character exactly
                 once, and every iteration performs only constant-time operations.

        Space = O(1)
        Reason : We store only three integer variables for the latest positions
                 of a, b, and c, so memory remains constant regardless of n.
        */

        System.out.println("Time = O(n), Space = O(1)");
    }
}


/*
Example : str = "abcabc"

R = 0
[ a ]
lastA = 0
lastB = -1
lastC = -1

All three characters are not available.
ans = 0


R = 1
[ a, b ]
lastA = 0
lastB = 1
lastC = -1

All three characters are not available.
ans = 0


R = 2
[ a, b, c ]
lastA = 0
lastB = 1
lastC = 2

Minimum latest index = 0.

Starting positions :

[ [a, b, c], a, b, c ]   → abc ✓

Only starting index 0 is possible.

ans += 0 + 1
ans = 1


R = 3
[ a, b, c, a ]
lastA = 3
lastB = 1
lastC = 2

Minimum latest index = 1.

Starting positions :

[ [a, b, c, a], b, c ]   → abca ✓
[ a, [b, c, a], b, c ]   → bca  ✓

ans += 1 + 1
ans = 3


R = 4
[ a, b, c, a, b ]
lastA = 3
lastB = 4
lastC = 2

Minimum latest index = 2.

Starting positions :

0 → abcab ✓
1 → bcab  ✓
2 → cab   ✓

ans += 2 + 1
ans = 6


R = 5
[ a, b, c, a, b, c ]
lastA = 3
lastB = 4
lastC = 5

Minimum latest index = 3.

Starting positions :

0 → abcabc ✓
1 → bcabc  ✓
2 → cabc   ✓
3 → abc    ✓

ans += 3 + 1
ans = 10


============================================================

Remember :

a. R moves → current ending position enters.
b. lastA → latest position of character a.
c. lastB → latest position of character b.
d. lastC → latest position of character c.
e. Minimum latest index → boundary for valid starting positions.
f. minIndex + 1 → number of valid substrings ending at R.
g. ans → total number of valid substrings.

============================================================

Complexity :

Time = O(n)
        R traverses the complete string exactly once.
        Every iteration performs only constant-time operations.

Space = O(1)
         Only three latest indexes are stored.
         The number of stored variables does not increase with n.

============================================================
*/