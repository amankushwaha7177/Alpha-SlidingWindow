import java.util.HashMap;

public class _1A_BruteForce_O_n2 {
    /*
    Interview : Given a binary array and an integer goal, find the number of non-empty
                subarrays whose sum is exactly equal to goal.

    Given : We need to count every contiguous subarray whose sum equals the given goal.

    Brain : Binary Array + Contiguous + Count Subarrays + Exact Sum = Variable Window
            subArray = window

    Given : nums = [1,0,1,0,1]
            goal = 2

            We need to find how many contiguous subarrays have a sum exactly equal to goal.

            First identify what the problem is asking:

            1. Subarray :
               The elements must be contiguous, meaning we cannot skip elements.

            2. Binary Array :
               Every element is either 0 or 1, so the sum changes only when we encounter 1.

            3. Count :
               We need the total number of valid subarrays, not the longest or shortest one.

            4. Exact Sum :
               Every counted subarray must have sum exactly equal to goal.

            Example :

            [1,0,1] → sum = 2 → Valid
            [1,0,1,0] → sum = 2 → Valid
            [0,1,0,1] → sum = 2 → Valid

            Therefore, the important information is:

            Contiguous + Subarray + Count + Exact Sum + Binary Array

            The actual algorithm can then be selected based on these properties.

    Idea :
    A. Normal :
       Use two loops where i chooses the starting position and j chooses every
       possible ending position while continuously maintaining the current sum.

       Whenever sum == goal, increase the answer because the current subarray
       contains exactly the required sum.

       Time = O(n²)
       Space = O(1)
    */

    public static void main(String[] args) {
        int[] nums = {1,0,1,0,1};
        int goal = 2;
        int ans = 0;

        for(int i = 0; i < nums.length; i++) {
            int sum = 0;

            for(int j = i; j < nums.length; j++) {
                sum += nums[j];

                if(sum == goal) {
                    ans++;
                }
            }
        }

        System.out.println("Answer = " + ans);

        /*
        Time = O(n²)
        Reason : The outer loop chooses every starting position, while the inner
                 loop extends the subarray through every possible ending position.

        Space = O(1)
        Reason : We only maintain the current sum and answer without storing
                 additional data that grows with the input size.
        */
        System.out.println("Time = O(n²), Space = O(1)");
    }
}