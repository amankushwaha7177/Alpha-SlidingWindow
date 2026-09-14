public class Main {
    static int findDuplicate(int[] a) {
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

        slow = 0;

        while(slow != fast) {
            slow = a[slow];
            fast = a[fast];
        }

        return slow;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 4, 2, 2};

        System.out.println("Duplicate = " + findDuplicate(a));

        /*
        Time = O(n)
        Reason : Slow and fast pointers move through the array a linear number of times.

        Space = O(1)
        Reason : Only slow and fast pointer variables are used without storing visited values.
        */
    }
}

/*
Logic Behind Duplicate Number :

Example:
a = {1, 3, 4, 2, 2}

value:  1  3  4  2  2
index:  0  1  2  3  4

Treat every array value as the next index.

Therefore:

0 → 1
1 → 3
3 → 2
2 → 4
4 → 2

So the movement becomes:

0 → 1 → 3 → 2 → 4
            ↑   ↓
            └───┘

The duplicate number is 2 because two different indexes point to 2:

3 → 2
4 → 2

Because both indexes point toward the same position, the movement
eventually enters a cycle containing the duplicate number.

Therefore:

Array
→ Treat value as next index
→ Duplicate creates a cycle
→ Use Slow & Fast Pointer to detect the cycle
→ Find the cycle starting point
→ Cycle starting point = Duplicate Number


Slow moves one step:

slow = a[slow]

Fast moves two steps:

fast = a[fast]
fast = a[fast]


After slow and fast meet inside the cycle:

slow = 0

Then move both one step:

slow = a[slow]
fast = a[fast]

They meet at the cycle start.

For this example:

0 → 1 → 3 → 2 → 4 → 2 → 4 → ...

Cycle Start = 2

Therefore:

Duplicate Number = 2


Mental Model:

Duplicate
    ↓
Two indexes point to the same value
    ↓
Movement enters a cycle
    ↓
Floyd Cycle Detection
    ↓
Find Cycle Start
    ↓
Cycle Start = Duplicate
*/