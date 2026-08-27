public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
/*
Problem:
Find the length of the longest contiguous subarray
whose sum is less than or equal to K.

Example:
arr = [2, 1, 5, 1, 3, 2]
K = 7

Longest valid subarray:

[2, 1, 5] → sum = 8 ❌
[1, 5, 1] → sum = 7 ✓
[5, 1, 3] → sum = 9 ❌
[1, 3, 2] → sum = 6 ✓

Answer = 3

IMPORTANT:
This simple sliding-window approach works when the
array contains NON-NEGATIVE numbers.
============================================================
*/