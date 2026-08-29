/*
============================================================
File: _2_Maximum_Points_You_Can_Obtain_From_Cards.java
Topic: Maximum Points You Can Obtain From Cards

Problem:
There is an array of cards and we need to pick exactly K cards.

We can pick cards only from either:
a. Left side
b. Right side

Find the maximum points we can obtain.

Example:

arr = [1, 2, 3, 4, 5, 6, 1]
k = 3

We need to pick exactly 3 cards.

Possible combinations:

3 from left:
[1, 2, 3] = 6

2 from left + 1 from right:
[1, 2] + [1] = 4

1 from left + 2 from right:
[1] + [6, 1] = 8

0 from left + 3 from right:
[5, 6, 1] = 12

Answer = 12
============================================================
*/

public class _2_Maximum_Points_You_Can_Obtain_From_Cards {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 1};
        int k = 3;


        /*
        Step 0 : Variables analogy

        leftSum  → stores points taken from LEFT.
        rightSum → stores points taken from RIGHT.
        finalAns → stores maximum points found so far.

        We will initially take all K cards from the left.
        Then gradually replace left cards with right cards.
        */


        int leftSum = 0;
        int rightSum = 0;

        int finalAns = 0;


        /*
        Step 1 : Initially take K cards from LEFT.

        k = 3

        [ [1, 2, 3], 4, 5, 6, 1]
           ↑     ↑
          left  k-1

        leftSum = 1 + 2 + 3
                = 6

        finalAns = 6
        */

        for (int i = 0; i <= k-1; i++) {
            leftSum += arr[i];
        }

        finalAns = leftSum;


        /*
        Step 2 : Start replacing LEFT cards with RIGHT cards.

        We already selected:

        [ [1, 2, 3], 4, 5, 6, 1]
           ← LEFT

        Now remove one card from LEFT
        and add one card from RIGHT.

        Right pointer starts from last index.
        */

        int right = arr.length - 1;


        /*
        Step 3 : Move from K-1 towards 0.

        Why?

        We need to remove the selected left cards
        one by one from RIGHT to LEFT.

        At the same time, RIGHT side cards
        enter one by one.
        */

        for (int i = k - 1; i >= 0; i--) {

            /*
            Remove one card from the LEFT selection.

            i = 2

            Remove arr[2] = 3

            [ [1, 2, 3], 4, 5, 6, 1]
                 ← remove 3
            */

            leftSum -= arr[i];


            /*
            Add one card from the RIGHT.

            right = 6

            arr[6] = 1 enters.

            [ [1, 2], 3, 4, 5, 6, [1]]
              LEFT                         RIGHT

            Total selected cards = 2 from left + 1 from right.
            */

            rightSum += arr[right];

            right--;


            /*
            Now calculate total points.

            leftSum  = points from LEFT
            rightSum = points from RIGHT

            Compare with previous maximum.
            */

            finalAns = Math.max(
                    finalAns,
                    leftSum + rightSum
            );
        }


        System.out.println(finalAns);
    }
}


/*
============================================================
WINDOW / CARD MOVEMENT
============================================================

arr = [1, 2, 3, 4, 5, 6, 1]
k = 3


STEP 1 : Take all K cards from LEFT
------------------------------------------------------------

[ [1, 2, 3], 4, 5, 6, 1]       sum = 6
   ←──────→
   3 cards

finalAns = 6


STEP 2 : Remove 1 LEFT card and add 1 RIGHT card
------------------------------------------------------------

[ [1, 2], 3, 4, 5, 6, [1]]     sum = 4

LEFT  = 1 + 2 = 3
RIGHT = 1
TOTAL = 4

finalAns = max(6, 4) = 6


STEP 3 : Remove 1 more LEFT card
         and add 1 more RIGHT card
------------------------------------------------------------

[ [1], 2, 3, 4, 5, [6, 1]]     sum = 8

LEFT  = 1
RIGHT = 6 + 1 = 7
TOTAL = 8

finalAns = max(6, 8) = 8


STEP 4 : Remove the last LEFT card
         and add 1 more RIGHT card
------------------------------------------------------------

[ 1, 2, 3, 4, [5, 6, 1]]       sum = 12

LEFT  = 0
RIGHT = 5 + 6 + 1 = 12
TOTAL = 12

finalAns = max(8, 12) = 12


FINAL ANSWER = 12


============================================================
IMPORTANT IDEA
============================================================

We always pick exactly K cards.

The number of cards selected from each side changes:

LEFT     RIGHT

3          0
2          1
1          2
0          3


So we are checking every possible combination
of K cards from the two ends.

============================================================
CORE FORMULA
============================================================

Initially:

leftSum = sum of first K cards

Then every iteration:

leftSum  -= left card
rightSum += right card

currentSum = leftSum + rightSum

finalAns = max(finalAns, currentSum)


Important:

LEFT pointer moves ←
RIGHT pointer moves ←

Both are moving towards the middle.

============================================================
COMPLEXITY
============================================================

First K elements:

O(K)

Then K combinations:

O(K)

Total:

O(K) + O(K)
= O(K)

Space:

O(1)

*/