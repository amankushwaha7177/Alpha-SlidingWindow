import java.util.HashMap;

public class _2_Optimal_O_n_AtMost_Difference {
    /*
    Interview : Given an integer array and K, find the number of subarrays
                containing exactly K different integers.

    Given : We need to count every contiguous subarray containing exactly K different integers.

            Example:
            nums = [1,2,1,2,3]
            k = 2

            Contiguous + Count + Exactly K Different Integers + SubArray = Variable Window
            subArray = window

    Brain : The simplest mental translation :

            Question says: Find the number of subarrays containing exactly K different integers.

            We need to check every possible contiguous subarray and count it
            whenever the number of different integers inside that subarray is exactly K.

            Ex:
            nums = [1,2,1,2,3]
            k = 2

            [1,2]       → 2 different integers → Valid ✓
            [2,1]       → 2 different integers → Valid ✓
            [1,2,1]     → 2 different integers → Valid ✓
            [2,1,2]     → 2 different integers → Valid ✓
            [1,2,1,2]   → 2 different integers → Valid ✓

            So the answer is 7.

    Idea : Exactly K different integers is difficult to count directly using
           an at-most sliding window, so we convert it into two at-most counts.

           Think of all subarrays in three groups:

                   Different Count < K
                   Different Count = K
                   Different Count > K

           We want only:

                   Different Count = K

           atMost(K) contains:

                   [Different Count < K] + [Different Count = K]

           atMost(K - 1) contains:

                   [Different Count < K]

           Therefore:

                   atMost(K) - atMost(K - 1)

                   = [Different Count = K]

           The subarrays having fewer than K different integers cancel each other,
           leaving only the subarrays containing exactly K different integers.

           Therefore:

                   ans = atMost(K) - atMost(K - 1)

           For atMost(K), maintain a window containing at most K different integers.

           R expands the window and adds nums[R] into the HashMap.

           Whenever map.size() > K, move L forward and decrease the frequency
           of nums[L] until the window becomes valid again.

           Once [L...R] is valid, every subarray ending at R and starting anywhere
           from L through R also contains at most K different integers.

           Therefore:

                   count += R - L + 1

           Time = O(2n - L,R movement) * 2 atMost calls = O(4n) = O(n)
           Space = O(n)
    */

    public static void main(String[] args) {
        int[] nums = {1,2,1,2,3};
        int k = 2;

        int ans = atMost(nums, k) - atMost(nums, k - 1);

        System.out.println("Answer = " + ans);

        /*
        Time = O(n)
        Reason : We call atMost two times, and each call moves both L and R
                 forward at most n positions through the entire array.

        Space = O(n)
        Reason : The HashMap stores frequencies of different integers present
                 inside the current window and can grow with input size.
        */
        System.out.println("Time = O(n), Space = O(n)");
    }

    static int atMost(int[] nums, int k) {
        if(k < 0) {
            return 0;
        }

        int l = 0;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int r = 0; r < nums.length; r++) {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);

            while(map.size() > k) {
                map.put(nums[l], map.get(nums[l]) - 1);

                if(map.get(nums[l]) == 0) {
                    map.remove(nums[l]);
                }

                l++;
            }

            count += r - l + 1;
        }

        return count;
    }
}