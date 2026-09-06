public class _2_Optimal_O_n_AtMost_Difference {
    /*
    Interview : Given an integer array and K, find the number of subarrays containing exactly K odd numbers.

    Given : We need to count every contiguous subarray containing exactly K odd numbers.

            We only care whether a number is odd or even.
            Odd  → 1
            Even → 0

            Example: nums = [1,1,2,5,1]
                  becomes:  [1,1,0,1,1]

            Now the problem becomes finding the number of subarrays containing exactly K ones.
                                          orr
            find the number of non-empty binary subarrays whose sum is exactly equal to goal.
            Ex :[1, 1, 0, 1]

            Number of 1s = 3
            Sum of elements = 1 + 1 + 0 + 1 = 3

            Contiguous + Count + Exactly K Odd Numbers + SubArray = Variable Window
            subArray = window

    Brain : The simplest mental translation :

            Question says: Find the number of subarrays containing exactly K odd numbers.

            We need to check every possible contiguous subarray and count it
            whenever the number of odd elements inside that subarray is exactly K.

            Ex:
            nums = [1,1,2,5,1]
            k = 3

            [1,1,2,5]     → 3 odd numbers → Valid ✓
            [1,2,5,1]     → 3 odd numbers → Valid ✓

            So the answer is 2.

    Idea : Exact K is difficult to count directly using an at-most sliding window,
           so we convert the exact condition into the difference between two counts.

           Think of all subarrays in three groups:

                   Odd Count < K
                   Odd Count = K
                   Odd Count > K

           We want only:

                   Odd Count = K

           atMost(K) contains:

                   [Odd Count < K] + [Odd Count = K]

           atMost(K - 1) contains:

                   [Odd Count < K]

           Therefore:

                   atMost(K) - atMost(K - 1)

                   = [Odd Count < K] + [Odd Count = K]
                     - [Odd Count < K]

                   = [Odd Count = K]

           The subarrays having fewer than K odd numbers cancel each other,
           leaving only the subarrays containing exactly K odd numbers.

           Therefore:

                   ans = atMost(K) - atMost(K - 1)

           For atMost(K), convert every number into 1 when it is odd and 0
           when it is even, then maintain a window containing at most K ones.

           R keeps expanding the window and adds nums[R] % 2 into the sum.

           Whenever sum > K, move L forward and subtract nums[L] % 2
           until the window becomes valid again.

           Once [L...R] is valid, every subarray ending at R and starting
           anywhere from L through R also contains at most K odd numbers.

           Therefore, the number of valid subarrays ending at R is:

                   R - L + 1

           Add this count to the total.

           Time = O(2n - L,R movement) * 2 atMost calls = O(4n) = O(n)
           Space = O(1)
    */

    public static void main(String[] args) {
        int[] nums = {1,1,2,1,1};
        int k = 3;

        int ans = atMost(nums, k) - atMost(nums, k - 1);

        System.out.println("Answer = " + ans);

        /*
        Time = O(n)
        Reason : We call atMost two times, and each call moves both L and R
                 forward at most n positions through the entire array.

        Space = O(1)
        Reason : We only maintain pointers, sum, and count without storing
                 additional information proportional to the input size.
        */
        System.out.println("Time = O(n), Space = O(1)");
    }

    static int atMost(int[] nums, int k) {
        if(k < 0) {
            return 0;
        }

        int l = 0;
        int sum = 0;
        int count = 0;

        for(int r = 0; r < nums.length; r++) {
            sum += nums[r] % 2;

            while(sum > k) {
                sum -= nums[l] % 2;
                l++;
            }

            count += r - l + 1;
        }

        return count;
    }
}


/*
Dry Run :

nums = [1,1,2,1,1]
k = 3

ans = atMost(3) - atMost(2)

atMost(3):

R=0 → sum=1 → valid → count += 1 → count=1
R=1 → sum=2 → valid → count += 2 → count=3
R=2 → sum=2 → valid → count += 3 → count=6
R=3 → sum=3 → valid → count += 4 → count=10
R=4 → sum=4 → invalid →
              move L once → sum=3, L=1
              valid → count += 4 → count=14

atMost(3) = 14


atMost(2):

R=0 → sum=1 → valid → count += 1 → count=1
R=1 → sum=2 → valid → count += 2 → count=3
R=2 → sum=2 → valid → count += 3 → count=6
R=3 → sum=3 → invalid →
              move L → sum=2, L=1
              valid → count += 3 → count=9
R=4 → sum=3 → invalid →
              move L → sum=2, L=2
              valid → count += 3 → count=12

atMost(2) = 12


Therefore:

ans = atMost(3) - atMost(2)
    = 14 - 12
    = 2

Final Answer = 2

Valid subarrays containing exactly 3 odd numbers:

[1,1,2,5]
[1,2,5,1]
*/