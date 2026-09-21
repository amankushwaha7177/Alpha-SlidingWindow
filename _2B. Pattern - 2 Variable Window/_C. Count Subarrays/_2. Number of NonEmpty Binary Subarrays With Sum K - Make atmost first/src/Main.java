/*
Q.  Let's use exactly the situation in your image.
    nums = [1, 0, 0, 1, 1, 0]
    goal = 2
    The confusing part is “when sum == goal, should I move L or R?”

A.  For the AtMost sliding window, the rule is very simple:
    If the window is valid (sum <= goal), move R. Only move L when the window becomes invalid (sum > goal).

        Suppose we are here:

           L              R
           ↓              ↓
        [  1,   0,   0,   1,   1,   0 ]
           └──────────────┘

        window = [1,0,0,1]
        sum = 2

        Now:

        sum = 2
        goal = 2

        sum <= goal
        2 <= 2

        So the window is valid.

        What do we do?

        Do NOT move L.

        We count all valid subarrays ending at R:

        [L........................R]
              [0,0,1]
                 [0,1]
                    [1]

        Actually, with L = 0, the valid starts depend on the zeros,
        but the key atMost formula is:

        count += r - l + 1;

        Then:

        R → move forward
        L → stay

        So:

           L                   R
           ↓                   ↓
        [  1,   0,   0,   1,   1,   0 ]
        Now R reaches the next 1:

        Now:
        sum = 3
        goal = 2
        3 > 2

        Now and only now do we move L.
        while(sum > goal) {
            sum -= nums[l];
            l++;
        }

        Remove the 1 at L:

                L              R
                ↓              ↓
        [  1,   0,   0,   1,   1,   0 ]
        window = [0, 0, 1, 1]
        sum = 2

        Valid again.

        So the movement rule is:

        R moves → EVERY iteration
        L moves → ONLY when sum > goal


The dilemma in one picture:

                         R moves forward
                              ↓
                     ┌────────────────┐
                     │                │
                     ▼                │
                sum <= goal ?         │
                  /        \           │
                YES         NO         │
                 │           │         │
                 │           ▼         │
                 │        Move L       │
                 │        until        │
                 │        sum <= goal  │
                 │           │         │
                 └───────────┴─────────┘
                             │
                             ▼
                       count +=
                       r - l + 1


The most important thing:

Don't think:

        sum == goal → move L

Think:

        sum <= goal → VALID → count → move R

        sum > goal  → INVALID → move L → make valid → count → move R


That's why your atMost() is:

        for(int r = 0; r < nums.length; r++) {
            sum += nums[r];

            while(sum > goal) {
                sum -= nums[l];
                l++;
            }

            count += r - l + 1;
        }

        sum == goal is not a special movement condition. It is simply a valid window,
        so L stays and R continues.
*/


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
/*
Why not simply do ans = ans + 1 when the current window is valid?

Suppose:

    nums = [1,0,0,1]
    goal = 2

At this point:

    L = 0
    R = 3

    Current window:
    [1,0,0,1]

    sum = 2

Yes, the current window itself is one valid subarray.

But we are calculating AT MOST goal, not just checking whether
the current [L...R] window itself is valid.

Because sum <= goal, all smaller windows ending at the same R
and starting between L and R are also valid.

They are:

    [1,0,0,1]
      [0,0,1]
        [0,1]
          [1]

So there are 4 valid subarrays ending at R.

Therefore:

    ans += 1        ❌ counts only [1,0,0,1]

    ans += R-L+1    ✅ counts all 4 valid subarrays

    R-L+1
    = 3-0+1
    = 4

Therefore:

    count += r-l+1;

The important difference is:

    ans + 1
    → counts only the current [L...R] window.

    ans + (R-L+1)
    → counts every valid subarray ending at R.

This is why atMost() uses:

    count += r-l+1;

rather than:

    count++;
*/