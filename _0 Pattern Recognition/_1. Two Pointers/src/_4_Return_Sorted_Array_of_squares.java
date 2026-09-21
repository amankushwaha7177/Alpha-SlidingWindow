public class _4_Return_Sorted_Array_of_squares {
}
/*
RECOGNITION HACK:

Array is already SORTED
        +
Need to SQUARE every element
        +
Need the SQUARES sorted
        ↓
TWO POINTERS


Why?

Negative numbers become positive after squaring.

Example:

    -7 → 49
    -4 → 16
    -2 → 4
     3 → 9
     5 → 25
     6 → 36

So the original sorted order is broken after squaring.

But the LARGEST square must come from one of the TWO ENDS.

    [-7, -4, -2, 3, 5, 6]
      ↑                 ↑
      L                 R

Compare:

    |-7| = 7
    | 6| = 6

Larger absolute value → larger square.

Therefore put the larger square at the END
of the result array and move that pointer.


Mental Rule:

    SORTED + SQUARES
        ↓
    Compare absolute values at L and R
        ↓
    Put larger square at the BACK
        ↓
    Move that pointer


Example:

    [-7, -4, -2, 3, 5, 6]

    |L| = 7
    |R| = 6

    49 > 36

    result[last] = 49
    L++


Then:

    [-4, -2, 3, 5, 6]

    |L| = 4
    |R| = 6

    36 > 16

    result[last] = 36
    R--


Continue until both pointers meet.


Recognition sentence:

    "The array is sorted, but an operation like SQUARE
    destroys the sorted order, while the largest result
    must still come from one of the two ends."

        ↓

    TWO POINTERS
*/