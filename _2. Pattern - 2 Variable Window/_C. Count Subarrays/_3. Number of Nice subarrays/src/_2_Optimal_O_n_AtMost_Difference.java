public class _2_Optimal_O_n_AtMost_Difference {
    /*
    Interview : Given an integer array and K, find the number of subarrays
                containing exactly K odd numbers.

    Given : We need to count every contiguous subarray containing exactly K odd numbers.

            We only care whether a number is odd or even.
            Odd  → 1
            Even → 0

            Example: nums = [1,1,2,1,1]
                  becomes:  [1,1,0,1,1]

            Now the problem becomes finding the number of subarrays
            containing exactly K ones.

            Contiguous + Count + Exactly K Odd Numbers + SubArray = Variable Window
            subArray = window

    Brain : The simplest mental translation :

            Question says: Find the number of subarrays containing exactly K odd numbers.

            We need to check every possible contiguous subarray and count it
            whenever the number of odd elements inside that subarray is exactly K.

            Ex:
            nums = [1,1,2,1,1]
            k = 3

            [1,1,2,1]     → 3 odd numbers → Valid ✓
            [1,2,1,1]     → 3 odd numbers → Valid ✓

            So the answer is 2.

    Idea :  Exact K is difficult to count directly using an at-most sliding window,
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

            For atMost(K), maintain a window containing at most K odd numbers.

            R keeps expanding the window and increases oddCount whenever nums[R] is odd.

            Whenever oddCount > K, move L forward until the window becomes valid again.

            Once [L...R] is valid, every subarray ending at R and starting anywhere
            from L through R also contains at most K odd numbers.

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
        Reason : We only maintain pointers, oddCount, and count without storing
                 additional information proportional to the input size.
        */
        System.out.println("Time = O(n), Space = O(1)");
    }

    static int atMost(int[] nums, int k) {
        if(k < 0) {
            return 0;
        }

        int l = 0;
        int oddCount = 0;
        int count = 0;

        for(int r = 0; r < nums.length; r++) {
            if(nums[r] % 2 != 0) {
                oddCount++;
            }

            while(oddCount > k) {
                if(nums[l] % 2 != 0) {
                    oddCount--;
                }
                l++;
            }

            count += r - l + 1;
        }

        return count;
    }
}