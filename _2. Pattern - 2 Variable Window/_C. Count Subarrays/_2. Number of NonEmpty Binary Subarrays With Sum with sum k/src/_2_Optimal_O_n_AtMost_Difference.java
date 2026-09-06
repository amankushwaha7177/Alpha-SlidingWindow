public class _2_Optimal_O_n_AtMost_Difference {
/*
Idea :  Exact sum is difficult to count directly using an at-most sliding window,
        so first understand how all possible subarrays are divided according to their sum.

        Example :
        nums = [1,0,1,0,1]
        goal = 2

        Think of every possible subarray in three groups:    Sum < 2    |  Sum = 2   | Sum > 2
        We want only the middle group: Sum = 2

        1. atMost(goal) means sum <= 2
           mean, atMost(2) contains both groups: [Sum < 2] + [Sum = 2]
           So:
                 atMost(2) = [Sum < 2] + [Sum = 2]

        2. atMost(goal - 1) means sum <= 1
           mean, atMost(2) contains both groups: [Sum < 1] + [Sum = 1]

           so:
              Sum <= 1 is exactly the same as: Sum < 2

           So:
               atMost(1) = [Sum < 2]


        3. Now subtract both counts:
                atMost(2) - atMost(1)
                = ([Sum < 2] + [Sum = 2]) - [Sum < 2]
                = [Sum = 2]

           The subarrays having sum less than 2 are present in both counts,
           so they cancel each other during subtraction.

           The only subarrays remaining are those whose sum is exactly 2.

        Therefore:
        Number of subarrays with sum exactly goal  = Number of subarrays with sum <= goal  - Number of subarrays with sum <= goal - 1

        Therefore:  ans = atMost(goal) - atMost(goal - 1)


                                                      atMost(2)
                                                ┌───────────────────┐
                                                │ Sum < 2 │ Sum = 2 │
                                                └───────────────────┘
                                                ┌──────────┐
                                                │  Sum < 2 │  atMost(1)
                                                └──────────┘
                                                ─────────────────────
                                                          │
                                                          ▼
                                                       Sum = 2

        -------------------------------------------------------------------------------------------------------
        Now understand how atMost(goal) itself works:

        For atMost(goal), maintain a window whose sum is <= goal.

        R keeps expanding the window and adds nums[R] into the current sum.

        Whenever sum becomes greater than goal, move L forward until the
        window becomes valid again because every element is non-negative.

        Once [L...R] is valid, every subarray ending at R and starting anywhere
        from L through R will also have a sum <= goal.

        For example: nums = [1,0,1,0,1] , K=2
        [1,0,1] → sum = 2 → Valid
        [1,0,1,0] → sum = 2 → Valid
        [0,1,0,1] → sum = 2 → Valid

        Add this count to the total number of subarrays.

        This atMost function is called twice:

                atMost(goal)
                atMost(goal - 1)

        Finally:

                ans = atMost(goal) - atMost(goal - 1)

        Time = O(2n - L,R movement) * 2 atMost calls = O(4n) = O(n)
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