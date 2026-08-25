// File: _1_SlidingWindow_Concept.java
// Topic: Sliding Window - Core Concept
// Covers: window, contiguous elements, left/right, expansion,
//         sliding, entering/leaving elements, fixed and variable window.

public class _1_SlidingWindow_Concept {

    public static void main(String[] args) {

        /*
        ============================================================
        1️⃣ What is Sliding Window?
        ============================================================

        Sliding Window is a technique used to process a
        CONTIGUOUS part of an array or String.

        Example:

        arr = [2, 1, 5, 1, 3, 2]

        A window can be:

        [2, 1, 5]

        Then the window can slide:

        [1, 5, 1]

        Then:

        [5, 1, 3]

        Then:

        [1, 3, 2]

        The window moves from LEFT → RIGHT.
        ============================================================
        */


        /*
        ============================================================
        2️⃣ What Does Contiguous Mean?
        ============================================================

        Contiguous means elements are next to each other.

        Valid:

        [2, 1, 5]

        [1, 5, 1]

        [5, 1, 3]


        Not contiguous:

        [2, 5]

        2 is at index 0.
        5 is at index 2.

        Index 1 is between them, so they are not contiguous.
        ============================================================
        */


        /*
        ============================================================
        3️⃣ left and right Pointers
        ============================================================

        A Sliding Window normally uses two pointers.

        left  → first index of the window.

        right → last index of the window.


        Example:

        index   0  1  2  3  4  5
        arr     2 [1  5  1] 3  2
                  ↑     ↑
                left  right

        left  = 1
        right = 3

        Current window:

        [1, 5, 1]


        IMPORTANT:

        The arrows point to the actual elements
        represented by left and right.
        ============================================================
        */


        /*
        ============================================================
        4️⃣ Window = arr[left ... right]
        ============================================================

        The current window starts at left
        and ends at right.

        Example:

        left  = 1
        right = 3

        index   0  1  2  3  4  5
        arr     2 [1  5  1] 3  2
                  ↑     ↑
                left  right

        Therefore:

        window = arr[1 ... 3]

        window = [1, 5, 1]
        ============================================================
        */


        /*
        ============================================================
        5️⃣ Window Size
        ============================================================

        Window size is:

        right - left + 1


        Example:

        left  = 1
        right = 3

        Size:

        3 - 1 + 1
        = 3


        Visual:

        index   0  1  2  3  4  5
        arr     2 [1  5  1] 3  2
                  ↑     ↑
                left  right

        There are 3 elements:

        [1, 5, 1]
        ============================================================
        */


        /*
        ============================================================
        6️⃣ How Does the Window Expand?
        ============================================================

        right moves forward.

        A new element enters the window.


        Starting point:

        index   0  1  2  3  4  5
        arr    [2  1  5  1  3  2]
                 ↑
               left
               right

        left  = 0
        right = 0

        Window:

        [2]


        ------------------------------------------------------------

        right moves:

        right = 1

        index   0  1  2  3  4  5
        arr    [2  1  5  1  3  2]
                 ↑  ↑
               left right

        Window:

        [2, 1]

        1 ENTERS the window.


        ------------------------------------------------------------

        right moves again:

        right = 2

        index   0  1  2  3  4  5
        arr    [2  1  5  1  3  2]
                 ↑     ↑
               left  right

        Window:

        [2, 1, 5]

        5 ENTERS the window.


        Therefore:

        right moves →
        new elements ENTER.
        ============================================================
        */


        /*
        ============================================================
        7️⃣ Fixed Window of Size 3
        ============================================================

        Suppose:

        k = 3

        The window must contain exactly 3 elements.

        First complete window:

        index   0  1  2  3  4  5
        arr    [2  1  5] 1  3  2
                 ↑     ↑
               left  right

        left  = 0
        right = 2

        Window:

        [2, 1, 5]

        Size:

        2 - 0 + 1 = 3
        ============================================================
        */


        /*
        ============================================================
        8️⃣ How Does the Window Slide?
        ============================================================

        Current window:

        index   0  1  2  3  4  5
        arr    [2  1  5] 1  3  2
                 ↑     ↑
               left  right

        Window:

        [2, 1, 5]


        We want the next window:

        [1, 5, 1]


        STEP 1:
        Move right from index 2 → index 3.

        New element:

        arr[3] = 1

        1 ENTERS.


        Temporary state:

        index   0  1  2  3  4  5
        arr    [2  1  5  1] 3  2
                 ↑        ↑
               left     right

        Temporary window:

        [2, 1, 5, 1]

        It now contains 4 elements.


        STEP 2:
        Move left from index 0 → index 1.

        Element leaving:

        arr[0] = 2

        2 LEAVES.


        Final state:

        index   0  1  2  3  4  5
        arr     2 [1  5  1] 3  2
                   ↑     ↑
                 left  right

        New window:

        [1, 5, 1]


        One element entered:

        1


        One element left:

        2


        This is ONE WINDOW SLIDE.
        ============================================================
        */


        /*
        ============================================================
        9️⃣ Second Window Slide
        ============================================================

        Current window:

        index   0  1  2  3  4  5
        arr     2 [1  5  1] 3  2
                   ↑     ↑
                 left  right

        Window:

        [1, 5, 1]


        STEP 1:
        right moves from 3 → 4.

        arr[4] = 3

        3 ENTERS.


        Temporary:

        index   0  1  2  3  4  5
        arr     2 [1  5  1  3] 2
                   ↑        ↑
                 left     right


        STEP 2:
        left moves from 1 → 2.

        arr[1] = 1

        1 LEAVES.


        Final:

        index   0  1  2  3  4  5
        arr     2  1 [5  1  3] 2
                      ↑     ↑
                    left  right

        New window:

        [5, 1, 3]


        1 LEAVES.

        3 ENTERS.
        ============================================================
        */


        /*
        ============================================================
        🔟 Third Window Slide
        ============================================================

        Current:

        index   0  1  2  3  4  5
        arr     2  1 [5  1  3] 2
                      ↑     ↑
                    left  right

        Window:

        [5, 1, 3]


        right moves:

        right = 5

        arr[5] = 2

        2 ENTERS.


        Temporary:

        index   0  1  2  3  4  5
        arr     2  1 [5  1  3  2]
                      ↑        ↑
                    left     right


        Now left moves:

        left = 3

        arr[2] = 5

        5 LEAVES.


        Final:

        index   0  1  2  3  4  5
        arr     2  1  5 [1  3  2]
                         ↑     ↑
                       left  right

        New window:

        [1, 3, 2]


        5 LEAVES.

        2 ENTERS.
        ============================================================
        */


        /*
        ============================================================
        1️⃣1️⃣ Complete Sliding Visualization
        ============================================================

        Original array:

        [2, 1, 5, 1, 3, 2]


        WINDOW 1:

        [2, 1, 5] 1  3  2


        WINDOW 2:

         2 [1, 5, 1] 3  2

        2 LEAVES.
        1 ENTERS.


        WINDOW 3:

         2  1 [5, 1, 3] 2

        1 LEAVES.
        3 ENTERS.


        WINDOW 4:

         2  1  5 [1, 3, 2]

        5 LEAVES.
        2 ENTERS.


        Notice what happens:

        [2, 1, 5]
         ↓
        2 leaves
         ↓
        [1, 5, 1]
         ↓
        1 leaves
         ↓
        [5, 1, 3]
         ↓
        5 leaves
         ↓
        [1, 3, 2]


        At every slide:

        One element LEAVES from the LEFT.

        One new element ENTERS from the RIGHT.
        ============================================================
        */


        /*
        ============================================================
        1️⃣2️⃣ Fixed Window Rule
        ============================================================

        For a fixed-size window:

        right moves forward
        → new element enters

        left moves forward
        → old element leaves


        Example:

        Before:

        [A, B, C]


        Add D:

        [A, B, C, D]


        Remove A:

        [B, C, D]


        Therefore:

        A LEAVES.

        D ENTERS.

        New window:

        [B, C, D]
        ============================================================
        */


        /*
        ============================================================
        1️⃣3️⃣ Variable Window
        ============================================================

        A variable window does not have a fixed size.

        Its size depends on a condition.


        Example:

        "Find the longest subarray whose sum <= 7."


        The window can grow:

        [2]

        [2, 1]

        [2, 1, 5]


        If adding another element violates the condition,
        move left forward and remove elements.


        Example:

        [2, 1, 5, 1]


        Remove 2:

        [1, 5, 1]


        Therefore:

        right → expands the window.

        left → shrinks the window.
        ============================================================
        */


        /*
        ============================================================
        1️⃣4️⃣ Fixed vs Variable Window
        ============================================================

        FIXED WINDOW

        Window size is already given.

        Example:

        "Maximum sum of subarray of size K"


        VARIABLE WINDOW

        Window size changes according to a condition.

        Example:

        "Longest substring without repeating characters."
        ============================================================
        */


        /*
        ============================================================
        1️⃣5️⃣ Why Sliding Window is Faster
        ============================================================

        Example:

        arr = [2, 1, 5, 1, 3, 2]
        k = 3


        First window:

        [2, 1, 5]

        Suppose its sum is 8.


        Next window:

        [1, 5, 1]


        Instead of calculating:

        1 + 5 + 1

        again, reuse the previous result:

        previous sum
        - element that LEFT
        + element that ENTERED


        8 - 2 + 1 = 7


        This avoids repeated calculations.
        ============================================================
        */


        /*
        ============================================================
        1️⃣6️⃣ Complexity
        ============================================================

        Brute Force:

        O(n * k)


        Typical Sliding Window:

        O(n)


        Why?

        right moves only forward.

        left also moves only forward.

        Neither pointer moves backward.


        Extra space is commonly:

        O(1)

        when only variables such as left, right,
        sum, and count are required.
        ============================================================
        */


        /*
        ============================================================
        ⭐ 1️⃣7️⃣ Final Mental Model
        ============================================================

        Think of [ ] as a physical box moving across
        the array.

        Example:

        index   0  1  2  3  4  5
        arr    [2  1  5] 1  3  2
                 ↑     ↑
               left  right


        Slide right:

        index   0  1  2  3  4  5
        arr     2 [1  5  1] 3  2
                   ↑     ↑
                 left  right


        Slide right again:

        index   0  1  2  3  4  5
        arr     2  1 [5  1  3] 2
                      ↑     ↑
                    left  right


        Final slide:

        index   0  1  2  3  4  5
        arr     2  1  5 [1  3  2]
                         ↑     ↑
                       left  right


        Remember:

        left  = first index inside the window.

        right = last index inside the window.

        right moves → new element ENTERS.

        left moves → old element LEAVES.

        Window = arr[left ... right]

        Window size = right - left + 1

        Fixed Window → exact size K.

        Variable Window → size depends on a condition.
        ============================================================
        */
    }
}