class _2_Optimal_O_n_HeavyWhile_Shrink_ToFind_Minimum_Valid_Window {

    /*
    Interview : Given an array of positive integers and a target, find the minimum
                length of a contiguous subarray whose sum is greater than or equal
                to the given target.

       Given : We need to find the smallest subarray's length possible where
               the subarray sum is >= target.

               Contiguous + Size is not Fixed + Sum >= Target + SubArray = Variable Window
               subArray = window

       Brain : The simplest mental translation :

               Question says: Find the minimum length window whose sum >= target.

               Sliding-window language:
               Keep increasing R until windowSum >= target.
               Once the window becomes valid, shrink from LEFT as much as possible
               while keeping windowSum >= target because we want minimum length.

               Ex:
               target = 4

               [1,2,4] → sum = 7 >= 4 → Valid ✓
               Shrink:
               [2,4] → sum = 6 >= 4 → Valid ✓
               [4] → sum = 4 >= 4 → Valid ✓
               [] → sum = 0 < 4 → Invalid ❌

               Therefore minimum valid window = [4]
               Answer = 1

       Idea : Start a window from the first element and keep increasing R.
              Add every element entering the window into windowSum.

              When windowSum >= target, the window becomes valid.
              Now use while to repeatedly remove elements from LEFT and update
              the minimum length because a smaller valid window may still exist.

              T = O(2n) = O(n)
              S = O(1)
    */

    public static void main(String[] args) {

        int target = 4;
        int[] arr = {1, 2, 4, 4};

        /*
        Step 0 : Variables analogy for window :
                a. 'l' and 'r' monitor the start and end points of window.
                b. 'windowSum' stores the sum of elements inside current window.
                c. 'ans' stores the minimum valid window length found so far.
        */

        int l = 0;
        int r = 0;

        int windowSum = 0;
        int ans = Integer.MAX_VALUE;

        /*
        Step 1 : Keep increasing R and add every new element into windowSum.
                When windowSum becomes >= target, the window is valid and
                repeatedly shrink from LEFT to find the minimum valid length.
        */

        while(r < arr.length) {

            windowSum += arr[r];

            while(windowSum >= target) {

                ans = Math.min(ans, r - l + 1);

                windowSum -= arr[l];
                l++;
            }

            r++;
        }

        System.out.println(ans == Integer.MAX_VALUE ? 0 : ans);

        /*
        Time = O(2n) = O(n)
        Reason : R moves through every element once, while L also moves forward
                 at most n times across the complete execution.

        Space = O(1)
        Reason : Only pointers, the running sum, and the answer variable are used.
        */
    }
}

/*
Dry Run:

arr = [1,2,4,4], target = 4

R=0 → sum=1 <4 → Invalid
window = [1]

R=1 → sum=3 <4 → Invalid
window = [1,2]

R=2 → sum=7 >=4 → Valid
window = [1,2,4], length=3 → ans=3

Remove 1 → sum=6 >=4 → Valid
window = [2,4], length=2 → ans=2

Remove 2 → sum=4 >=4 → Valid
window = [4], length=1 → ans=1

Remove 4 → sum=0 <4 → Stop shrinking

R=3 → add 4
sum=4 >=4 → Valid
window = [4], length=1 → ans=1

Remove 4 → sum=0 <4 → Stop shrinking

Final answer = 1
*/



/*
Que. Previously we were using inner while loop to make invalid window valid again here doing opposite
     when its becoming valid we are finding result and then making invalid
Ans. Exactly. You understood the key difference correctly.

The while loop is doing the opposite job depending on the problem's goal:

1. Previous problems — while makes INVALID → VALID
            Example: Maximum length with at most K zeros

            if(zeroCount > k){
                while(zeroCount > k){
                    // Remove from left
                    l++;
                }
            }

            Here:
            Invalid window → shrink → make it valid → find maximum length

            R grows → window becomes INVALID
                          ↓
                     while shrink
                          ↓
                     VALID window
                          ↓
                    calculate maximum



2. Current problem — while makes VALID → INVALID
        Example: Minimum length with sum >= target

        while(windowSum >= target){
            ans = Math.min(ans, r - l + 1);

            windowSum -= arr[l];
            l++;
        }

        Here:
        Valid window → find result → shrink → eventually make it invalid

        R grows → window becomes VALID
                      ↓
               calculate minimum
                      ↓
                 while shrink
                      ↓
               still VALID → smaller
                      ↓
               still VALID → smaller
                      ↓
               becomes INVALID → STOP


🧠 The main difference :
------------------------
The while loop does the opposite job depending on whether we need maximum or minimum length.
Maximum length → INVALID → shrink → VALID → calculate maximum.
Minimum length → VALID → calculate minimum → shrink → INVALID.
Maximum means we fix the invalid window, while minimum means we destroy the valid window.
*/