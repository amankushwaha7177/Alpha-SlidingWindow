 class _3B_More_Optimal_O_n______Direct_Jump_Of_L_Using_Index_ByMap {

    /*
    Given : Each basket can hold only 1 fruit type.
            So the window can contain at most 2 different fruit types.

            Contiguous + Variable Size + At Most 2 Different Types
            + SubArray = Variable Window
            subArray = window

    Brain : The simplest mental translation :

            Question says:
            Pick fruits using 2 baskets.

            Sliding-window language:
            Find the longest contiguous window with at most 2 different fruit types.

            Ex:
            [ [1, 2, 1], 2, 3 ]       -> 2 types -> Fine
            [ [1, 2, 1, 2, 3] ]       -> 3 types -> Invalid

    Idea : A. Better :
               Store fruit type -> FREQUENCY in HashMap.
               If map.size() > 2, move L one by one.

           B. More Optimal :
               Store fruit type -> LATEST INDEX in HashMap.
               If map.size() > 2, directly jump L after the
               latest occurrence of the fruit currently at L.

               T = O(n)
               S = O(1)
                   // At most 3 fruit types temporarily exist.
    */

    public static void main(String[] args) {

        int[] fruits = {3, 3, 3, 1, 2, 2};

        int l = 0;
        int r = 0;

        int ans = 0;

        java.util.HashMap<Integer, Integer> map = new java.util.HashMap<>();

        while(r < fruits.length){

            /* Store CURRENT fruit -> its LATEST index. */
            map.put(fruits[r], r);

            /*
            If 3 fruit types exist, window becomes invalid.

            Instead of moving L one by one,
            directly jump L after the latest occurrence
            of the fruit currently present at L.
            */
            if(map.size() > 2){

                int leftFruit = fruits[l];

                /*
                Example:
                [3, 3, 3, 1, 2]
                 ^
                 L

                map = {3 -> 2, 1 -> 3, 2 -> 4}

                leftFruit = 3
                latest 3 = index 2

                Therefore:
                L = 2 + 1 = 3
                */

                l = map.get(leftFruit) + 1;

                /*
                Fruit at old L is now completely outside
                the new window, so remove its map entry.
                */
                map.remove(leftFruit);
            }

            /* Window now contains at most 2 fruit types. */
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

fruits = [3, 3, 3, 1, 2, 2]


R = 0

[ [3], 3, 3, 1, 2, 2]

map = {3 -> 0}
types = 1
length = 1
ans = 1


R = 1

[ [3, 3], 3, 1, 2, 2]

map = {3 -> 1}
types = 1
length = 2
ans = 2


R = 2

[ [3, 3, 3], 1, 2, 2]

map = {3 -> 2}
types = 1
length = 3
ans = 3


R = 3

[ [3, 3, 3, 1], 2, 2]

map = {3 -> 2, 1 -> 3}
types = 2
length = 4
ans = 4


R = 4

R adds 2:

[ [3, 3, 3, 1, 2], 2]

map = {3 -> 2, 1 -> 3, 2 -> 4}
types = 3 -> INVALID


L = 0
fruits[L] = 3

Latest index of 3 = 2

So directly:

L = latestIndex + 1
L = 2 + 1
L = 3


Now:

[ 3, 3, 3, [1, 2], 2]
          ^
          L

Remove 3 from map:

map = {1 -> 3, 2 -> 4}

types = 2 -> VALID

length = 4 - 3 + 1
       = 2

ans = max(4, 2)
    = 4


R = 5

R adds another 2:

[ 3, 3, 3, [1, 2, 2] ]

map = {1 -> 3, 2 -> 5}
types = 2 -> VALID

length = 5 - 3 + 1
       = 3

ans = max(4, 3)
    = 4


Maximum length = 4


============================================================
WHY DIRECT JUMP WORKS
============================================================

When 3 types exist:

[ 3, 3, 3, 1, 2 ]
  ^
  L

The fruit at L is 3.

HashMap stores:

3 -> 2

So:

L = map.get(3) + 1
  = 2 + 1
  = 3

Instead of:

L = 1
L = 2
L = 3

we directly jump:

L = 3

Now every 3 is outside the window:

[ 3, 3, 3, [1, 2] ]
          ^
          L

Only 1 and 2 remain.


============================================================
REMEMBER
============================================================

a. R moves -> fruit ENTERS.
b. HashMap -> stores fruit type -> LATEST INDEX.
c. map.size() <= 2 -> window is valid.
d. map.size() > 2 -> window becomes invalid.
e. fruits[L] -> tells which fruit type must be removed.
f. map.get(fruits[L]) -> gives its LATEST index.
g. L = latestIndex + 1 -> directly jumps L.
h. Remove that fruit type from HashMap.
i. ans -> maximum valid window length.


============================================================
COMPLEXITY
============================================================

Time = O(2n) = O(n)

        a. R traversal:
           R moves from 0 -> n-1 exactly once.
           So R traversal = O(n).

        b. L movement:
           Whenever map.size() > 2, L jumps directly.
           L only moves forward and never comes backward.
           Across the complete array, L moves at most n positions.

           So L movement = O(n).

        Therefore:

        O(n) + O(n)
        = O(2n)
        = O(n)


Space = O(1)

        At most 3 fruit types temporarily exist in HashMap.

============================================================
*/
