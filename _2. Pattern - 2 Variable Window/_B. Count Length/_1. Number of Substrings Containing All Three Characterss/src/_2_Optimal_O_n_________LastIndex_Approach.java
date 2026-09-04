public class _2_Optimal_O_n_________LastIndex_Approach {

    /*
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


            Ex : "aabbca"

                              R
              0  1  2   3  4  5
            [ a, a, b, |b, c, a| ]

            latest a = 5
            latest b = 3
            latest c = 4

            Minimum latest index = 3

            Therefore starting positions 3 to 0 are valid.
            Number of valid substrings = 3 + 1 = 4.


            Ex: "aabbccca"
                                    R
              0  1  2   3  4  5  6  7
            [ a, a, b, |b, c, c, c, a| ]    --> yes |b c c c a| is valid window currently.

            latest a = 7
            latest b = 3
            latest c = 6

            Minimum latest index = 3

            Therefore starting positions 3 to 0 are valid.
            Number of valid substrings = 3 + 1 = 4. (    |b, c, c, c, a|
                                                        b|b, c, c, c, a|
                                                       ab|b, c, c, c, a|
                                                      aab|b, c, c, c, a|  )



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

    /*
            Core Understanding:

            R keeps moving forward and checks each new character in the string.

            Whenever R makes the current substring contain all three characters a, b, and c,
            we do not move L through every possible position to count each valid substring.

            Instead, we find minIndex, which represents the last valid starting position L.

            Therefore, every starting position from 0 through minIndex creates a valid substring.

            So we count all valid starting positions directly using:

            ans = ans + minIndex + 1

            Then R moves forward again and discovers the next possible ending position.
            */

    /*
     Que.   Why +1?
     Ans.   Example 1 : minIndex = 0
            String = "abc"
            lastA = 0
            lastB = 1
            lastC = 2

            minIndex = min(0, 1, 2) = 0

            Valid starting positions:  0 → "abc"

            Only 1 valid starting position exists.

            Count = minIndex + 1   ( 1 because index starts with 0 not 1 so to count )
                  = 0 + 1
                  = 1


            Example 2 : minIndex = 1
            String = "abca"
            lastA = 3
            lastB = 1
            lastC = 2

            minIndex = min(3, 1, 2) = 1

            Valid starting positions:
            L = 0 → "abca"
            L = 1 → "bca"

            There are 2 valid starting positions.

            Count = minIndex + 1
                  = 1 + 1
                  = 2


            Example 3 : minIndex = 2

            String = "abcab"

            lastA = 3
            lastB = 4
            lastC = 2

            minIndex = min(3, 4, 2) = 2          [ in simple 2 means string starts with 0,1,2 so i+1]

            Valid starting positions:
            L = 0 → "abcab"
            L = 1 → "bcab"
            L = 2 → "cab"

            There are 3 valid starting positions.

            Count = minIndex + 1
                  = 2 + 1
                  = 3


            Example 4 : minIndex = 3
                         3
            String = "abcabc"

            Suppose the latest positions are:

            lastA = 5
            lastB = 4
            lastC = 3

            minIndex = min(5, 4, 3) = 3

            Valid starting positions:
            L = 0
            L = 1
            L = 2
            L = 3

            There are 4 valid starting positions.

            Count = minIndex + 1
                  = 3 + 1
                  = 4


            Main Rule:

            minIndex tells us the LAST valid starting position L.

            Therefore, every starting position from 0 through minIndex is also valid.

            Positions = 0, 1, 2, ..., minIndex

            So the total number of positions is always:

            Count = minIndex + 1

            The +1 is required because position 0 is also included.
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

            int l = Math.min(lastA, Math.min(lastB, lastC));


            /* Step 3 : If all three characters have appeared, count every
                        valid substring ending at the current R. */

            if(l != -1) {
                ans += l + 1;
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

Complexity :

Time = O(n)
        R traverses the complete string exactly once.
        Every iteration performs only constant-time operations.

Space = O(1)
         Only three latest indexes are stored.
         The number of stored variables does not increase with n.

============================================================
*/