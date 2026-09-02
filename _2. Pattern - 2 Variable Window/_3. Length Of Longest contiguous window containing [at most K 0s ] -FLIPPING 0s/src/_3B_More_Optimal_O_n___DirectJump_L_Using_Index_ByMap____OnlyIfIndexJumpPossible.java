public class _3B_More_Optimal_O_n___DirectJump_L_Using_Index_ByMap____OnlyIfIndexJumpPossible {
    /*
       Idea :  A. Normal : Try every possible subarray and count zeros in each window.
                    If zeroCount <= K, compare its length with the maximum answer.
                    T = O(n²).

               B. Previous optimal : Start a window from first element and keep increasing R.
                  Count zeros inside the current window.
                  If zeroCount > K, shrink from LEFT until the window becomes valid.
                  T = O(2n) = O(n).

               C. Direct Jump : Store the index of every zero inside a Queue.
                  When zeroCount becomes greater than K, remove the oldest zero index
                  and directly move L to the position immediately after that zero.
                  This avoids moving L one position at a time through unnecessary 1s.
                  T = O(n)
                  S = O(K)
     */

    public static void main(String[] args) {
        int k = 2;
        int[] arr = {1,1,1,0,0,0,1,1,1,1,0};

        /* Step 0 : variables analogy for window, where L and R monitor the current window boundaries. */
        int l = 0;
        int r = 0;

        int zeroCount = 0;
        int ans = 0;

        java.util.Queue<Integer> zeroIndex = new java.util.LinkedList<>();

        /* Step 1 : Move R one element at a time and remember every zero's exact position. */
        while(r < arr.length) {

            /* R enters the current element, so remember its index whenever that element is zero. */
            if(arr[r] == 0) {
                zeroCount++;
                zeroIndex.offer(r);
            }

            /*
             * Step 2 : When more than K zeros exist, the oldest zero must leave the window.
             * Instead of moving L one-by-one, directly jump L after that oldest zero.
             */
            if(zeroCount > k) {

                int oldestZeroIndex = zeroIndex.poll();

                /*
                 * L jumps directly after the oldest zero because that zero
                 * is the first zero that must leave to bring the count back within K.
                 */
                l = oldestZeroIndex + 1;

                zeroCount--;
            }

            /* Step 3 : The window now contains at most K zeros, so compare its length with ans. */
            ans = Math.max(ans, r - l + 1);

            r++;
        }

        System.out.println(ans);
    }
}

/*
arr = [1,1,1,0,0,0,1,1,1,1,0]
k = 2


Step 1 : R keeps moving and zero indexes are remembered
------------------------------------------------------------

R = 0 → 1
Window = [ [1] ]
zeroCount = 0
zeroIndex = [ ]
length = 1


R = 1 → 1
Window = [ [1,1] ]
zeroCount = 0
zeroIndex = [ ]
length = 2


R = 2 → 1
Window = [ [1,1,1] ]
zeroCount = 0
zeroIndex = [ ]
length = 3


Step 2 : R adds the first zero
------------------------------------------------------------

R = 3 → 0

Window = [ [1,1,1,0] ]

zeroCount = 1
zeroIndex = [3]

length = 4


R = 4 → 0

Window = [ [1,1,1,0,0] ]

zeroCount = 2
zeroIndex = [3,4]

length = 5


Step 3 : R adds the third zero
------------------------------------------------------------

R = 5 → 0

Window = [ [1,1,1,0,0,0] ]

zeroCount = 3 > K
Window becomes INVALID ❌

zeroIndex = [3,4,5]

The oldest zero is at index 3.

Instead of moving L:

L → 0 → 1 → 2 → 3 → 4

We directly jump:

L = oldestZeroIndex + 1
L = 3 + 1
L = 4

Remove zero index 3 from Queue:

zeroIndex = [4,5]

zeroCount = 2 ✓

New window:

[ 1,1,1,0, [0,0] ]

length = 5 - 4 + 1
length = 2


Step 4 : R continues moving after the direct jump
------------------------------------------------------------

R = 6 → 1

Window = [ 1,1,1,0, [0,0,1] ]

zeroCount = 2
zeroIndex = [4,5]

length = 3


R = 7 → 1

Window = [ 1,1,1,0, [0,0,1,1] ]

zeroCount = 2
length = 4


R = 8 → 1

Window = [ 1,1,1,0, [0,0,1,1,1] ]

zeroCount = 2
length = 5


R = 9 → 1

Window = [ 1,1,1,0, [0,0,1,1,1,1] ]

zeroCount = 2
length = 6

ans = 6


Step 5 : R adds final zero
------------------------------------------------------------

R = 10 → 0

Window = [ 1,1,1,0,0,0,1,1,1,1,0 ]

zeroCount = 3 > K
Window becomes INVALID ❌

zeroIndex = [4,5,10]

Oldest zero = index 4

Directly jump L:

L = 4 + 1
L = 5

Remove index 4 from Queue:

zeroIndex = [5,10]

zeroCount = 2 ✓

New window:

[ 1,1,1,0, [0,1,1,1,1,0] ]

length = 10 - 5 + 1
length = 6

ans = max(6,6)
ans = 6


Final Answer = 6


============================================================
Remember:

a. R moves → element ENTERS the current window.
b. 0 enters → zeroCount increases and its index is remembered.
c. zeroCount > K → window becomes INVALID.
d. Queue → remembers zero indexes from LEFT to RIGHT.
e. Queue front → gives the oldest zero currently inside the window.
f. Remove oldest zero → because it is the first zero that must leave.
g. L jumps → directly to oldestZeroIndex + 1 instead of moving one-by-one.
h. zeroCount decreases → because exactly one zero has left the window.
i. R continues → after L has jumped to the correct position.
j. ans → maximum valid window length found so far.

============================================================
Important : This is the 3B DIRECT-JUMP pattern.

            Pattern 2 → HeavyWhile

            R → ENTERS
            Window → INVALID
            L → L → L → L
            Continue until window becomes VALID


            Pattern 3A → NoWhile

            R → ENTERS
            Window → INVALID
            L → ONE STEP
            Continue R


            Pattern 3B → DirectJump

            R → ENTERS
            Window → INVALID
            Find the exact element that must leave
            L → JUMP directly after that element
            Continue R

============================================================
Complexity:

Time = O(n), Space = O(K)

R visits every element exactly once, and every zero index enters
and leaves the Queue only once during the complete traversal.

Therefore:

R movement = O(n)
Queue insertion = O(n)
Queue removal = O(n)

Total = O(n)

Space = O(K)

The Queue stores at most K+1 zero indexes temporarily,
because one extra zero triggers the direct jump and is then removed.
============================================================
*/