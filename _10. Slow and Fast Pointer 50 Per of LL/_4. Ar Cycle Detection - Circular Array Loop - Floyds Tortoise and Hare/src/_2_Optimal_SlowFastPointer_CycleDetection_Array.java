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
        int[] a = {2, -1, 1, 2, 2};
        System.out.println("Cycle Start = " + findCycleStart(a));
    }

}

/*
   Time = O(n)
   Reason : Slow and fast pointers move through the array until they meet or leave the valid path.

   Space = O(1)
   Reason : Only slow and fast pointer variables are used without storing visited indexes.
 */