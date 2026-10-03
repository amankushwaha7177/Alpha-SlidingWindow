public class _2_Why_Not_normal_pattern {
    /*
    Why can't we directly use:

        if(sum == goal)
            count++;

    Because this counts only one valid window for the current R.

    But multiple different subarrays can end at the same R and have the same
    exact sum, so count++ can miss some valid subarrays.

    Example:

    nums = [1,0,1,0,1]
    goal = 2

    At R = 4, the valid subarrays ending at R are:

        [0,1,0,1]  -> sum = 2
        [1,0,1]    -> sum = 2

    There are 2 valid subarrays, but:

        if(sum == goal)
            count++;

    adds only 1.

    Therefore, the normal exact-sum check cannot count all valid subarrays.

    --------------------------------------------------------------------------

    Why atMost() works:

    Instead of directly counting Sum = goal, count:

        atMost(goal)
        atMost(goal - 1)

    atMost(goal) counts:

        Sum < goal + Sum = goal

    atMost(goal - 1) counts:

        Sum < goal

    Subtracting them removes all smaller sums:

        atMost(goal) - atMost(goal - 1)

        = [Sum < goal + Sum = goal] - [Sum < goal]

        = Sum = goal

    Therefore:

        Exact Sum Count = atMost(goal) - atMost(goal - 1)

    --------------------------------------------------------------------------

    Why count += r - l + 1 inside atMost():

    Once the current window [L...R] has sum <= goal,
    every subarray ending at R and starting from L through R is also valid.

    Number of such subarrays:

        R - L + 1

    Example:

        L = 1
        R = 4

        Valid subarrays ending at R:

        [1...4]
        [2...4]
        [3...4]
        [4...4]

        Count = 4 - 1 + 1 = 4

    Therefore:

        count += r - l + 1

    is required instead of:

        count++

    --------------------------------------------------------------------------

    Important:

    This atMost technique works when the array contains non-negative values,
    because moving L forward cannot increase the current window sum.
    */

    public static void main(String[] args) {

        int[] nums = {1, 0, 1, 0, 1};
        int goal = 2;

        int ans = standard(nums, goal);

        System.out.println("Answer = " + ans);

        /*
        Time = O(n)
        Reason : atMost() is called twice and each call moves L and R
        forward at most n times overall.

        Space = O(1)
        Reason : Only pointers, sum, and count are maintained.
        */

        System.out.println("Time = O(n), Space = O(1)");
    }

    static int standard(int[] nums, int goal) {

        int l = 0;
        int sum = 0;
        int count = 0;

        for(int r = 0; r < nums.length; r++) {

            sum += nums[r];

            while(sum > goal) {
                sum -= nums[l];
                l++;
            }

            if( sum == goal)
            count +=  1;
        }

        return count;
    }
}