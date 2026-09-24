public class _1_Optimal_SlowFastPointer_CycleDetection_Array {
    static int findCycleStart(int[] nums) {
        int slow = 0;
        int fast = 0;

        while ( fast <= nums.length-1){
            // if cycle does't exists fast will cross 0 to n-1 index
            // so fast <= nums.length-1 can pause loop in linear array.

            // our question already saying cycle is there so fast <= nums.length-1 never execute
            // so it will work like true alywas
            // or in best case directly write while(true) --> if cycle is given in Question.
            slow= nums[slow];
            fast = nums[fast];
            fast = nums[fast];


            if(slow == fast){
                slow = 0;

                while( slow != fast){
                    slow = nums[slow];
                    fast = nums[fast];
                }

                return slow;
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 4, 2, 2};
        System.out.println("Cycle Start = " + findCycleStart(a));
    }

}
