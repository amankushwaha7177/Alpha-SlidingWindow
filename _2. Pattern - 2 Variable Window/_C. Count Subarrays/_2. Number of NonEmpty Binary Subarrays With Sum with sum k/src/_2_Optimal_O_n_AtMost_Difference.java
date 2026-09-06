public class _2_Optimal_O_n_AtMost_Difference {
    /*
    Idea :  Exact sum is difficult to count directly using an at-most sliding window,
            but we can represent exact sum using the difference between two at-most counts.

            Number of subarrays with sum exactly goal
            =
            Number of subarrays with sum at most goal
            -
            Number of subarrays with sum at most goal - 1

            Therefore:

            ans = atMost(goal) - atMost(goal - 1)

            For atMost(goal), maintain a window whose sum is <= goal.

            R expands the window and adds nums[R] into the current sum.

            Whenever sum becomes greater than goal, move L forward until the window
            becomes valid again because every element is non-negative in a binary array.

            Once the window [L...R] is valid, every subarray ending at R and starting
            anywhere from L through R also has a sum <= goal.

            Therefore, the number of valid subarrays ending at R is:

            R - L + 1

    Add this value to the count.

    Time = O(2n -l,r)*2 = o(4n)
    Space = O(1)
    */

    public static void main(String[] args) {
        int[] nums = {1,0,1,0,1};
        int goal = 2;

        int ans = atMost(nums, goal) - atMost(nums, goal - 1);

        System.out.println("Answer = " + ans);

        /*
        Time = O(n)
        Reason : We call atMost two times, and each call moves both L and R
                 forward at most n positions through the entire array.

        Space = O(1)
        Reason : We only maintain pointers, current sum, and count without
                 storing additional data proportional to the input size.
        */
        System.out.println("Time = O(n), Space = O(1)");
    }

    static int atMost(int[] nums, int goal) {
        if(goal < 0) {
            return 0;
        }

        int l = 0;
        int sum = 0;
        int count = 0;

        for(int r = 0; r < nums.length; r++) {
            sum += nums[r];

            while(sum > goal) {
                sum -= nums[l];
                l++;
            }

            count += r - l + 1;
        }

        return count;
    }
}