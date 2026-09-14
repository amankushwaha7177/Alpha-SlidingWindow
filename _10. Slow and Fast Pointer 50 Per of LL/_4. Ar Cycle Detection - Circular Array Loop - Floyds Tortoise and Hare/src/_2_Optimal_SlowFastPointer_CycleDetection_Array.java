public class _2_Optimal_SlowFastPointer_CycleDetection_Array {
    static int findCycleStart(int[] a) {
        int slow = 0;
        int fast = 0;

        while(true) {
            slow = a[slow];

            fast = a[fast];
            fast = a[fast];

            if(slow == fast) {
                break;
            }
        }

        /* After the first meeting, reset slow to the starting index. */
        slow = 0;

        /* Move both one step until they meet at the starting point of the cycle. */
        while(slow != fast) {
            slow = a[slow];
            fast = a[fast];
        }

        return slow;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 4, 2, 2};
        System.out.println("Cycle Start = " + findCycleStart(a));
    }

}

/*
   Time = O(n)
   Reason : Slow and fast pointers move through the array until they meet or leave the valid path.

   Space = O(1)
   Reason : Only slow and fast pointer variables are used without storing visited indexes.
 */

/*
Dry Run :

a = {1, 3, 4, 2, 2}

value:  1   3   4   2   2
index:  0   1   2   3   4

Movement means:

i   v
0 → 1
1 → 3
3 → 2
2 → 4
4 → 2

So:
0 → 1
    3
    2
    4
    2

0 → 1 → 3 → 2 → 4
            ↑   ↓
            └───┘


First Meeting:

Initial:
slow = 0
fast = 0

value:  1   3   4   2   2
index:  0   1   2   3   4


Step 1:
    slow: arr[0] → 1
    fast: arr[0] → 1
          arr[1] → 3

    slow = 1
    fast = 3

Step 2:
    slow: arr[1] → 3
    fast: arr[3] → 2
          arr[2] → 4

    slow = 3
    fast = 4

Step 3:
    slow: 3 → 2
    fast: 4 → 2 → 4

    slow = 2
    fast = 4


Step 4:
    slow: 2 → 4
    fast: 4 → 2 → 4

    slow = 4
    fast = 4

slow == fast
Cycle Found.

First Meeting Point = 4

============================================================

Now find the Cycle Start:

Reset only slow:

slow = 0
fast = 4

Now both move one step at a time.


Step 1:

slow: 0 → 1
fast: 4 → 2

slow = 1
fast = 2


Step 2:

slow: 1 → 3
fast: 2 → 4

slow = 3
fast = 4


Step 3:

slow: 3 → 2
fast: 4 → 2

slow = 2
fast = 2

They meet again at 2.

Therefore:

Cycle Start = 2


Visual:

0 → 1 → 3 → 2 → 4
            ↑   ↓
            └───┘

First Meeting:
slow = fast = 4

Reset:
slow = 0
fast = 4

Move both one step:

slow: 0 → 1 → 3 → 2
fast:  4 → 2 → 4 → 2

Second Meeting:
slow = fast = 2

Therefore:
2 is the starting point of the cycle.
*/