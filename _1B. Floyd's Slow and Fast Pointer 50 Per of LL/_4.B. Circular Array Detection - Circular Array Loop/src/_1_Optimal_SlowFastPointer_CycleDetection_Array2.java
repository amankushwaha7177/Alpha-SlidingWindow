public class _1_Optimal_SlowFastPointer_CycleDetection_Array2 {
    static int findCycleStart(int[] a) {
        int slow = 0;
        int fast = 0;

        while(fast < a.length) {
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
