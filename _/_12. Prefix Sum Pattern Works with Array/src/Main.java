public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

/*
Prefix Sum:

The idea is to store the sum of all elements from the beginning
up to the current index.

Example:

arr = [2, 5, 1, 3]

index:       0   1   2   3
arr:         2   5   1   3
prefix:      2   7   8  11


Now we can find any subarray sum quickly:

sum(l...r) = prefix[r] - prefix[l - 1]

Example:

arr = [2, 5, 1, 3]

Find sum of [5, 1, 3]:

prefix[3] - prefix[0]
= 11 - 2
= 9


Problem With Sliding Window:

Sliding Window works well when the array has a predictable
relationship between window movement and the sum.

For positive numbers:

Adding an element
      ↓
Sum increases

Removing an element
      ↓
Sum decreases


But with negative numbers:

arr = [2, -5, 4, 3]

Adding an element may increase OR decrease the sum.

Removing an element may increase OR decrease the sum.

Therefore, conditions like:

sum > target → shrink
sum < target → expand

are no longer reliable.


Example:

arr = [2, -5, 4, 3]

Current sum = 2

Add -5:
2 + (-5) = -3

The window became smaller in sum even though
we expanded the window.

This breaks the normal Sliding Window logic.


Mental Rule:

Positive Numbers
      ↓
Sum is predictable
      ↓
Sliding Window can work


Negative Numbers
      ↓
Sum is not predictable
      ↓
Sliding Window may fail
      ↓
Prefix Sum + HashMap can help


Prefix Sum does not depend on the sum being
monotonically increasing or decreasing.
*/