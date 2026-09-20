public class Logic {
}
/*
Logic:

We need to count subarrays whose sum is divisible by K.

Example:

arr = [4, 5, 0, -2, -3, 1]
K = 5


Step 1: Understand the Main Trick

For the normal Sum = K problem, we searched:

Previous Prefix = Current Prefix - K

But here we do not need the sum to be exactly K.

We need:

Subarray Sum % K = 0

The important rule is:

If two prefix sums have the SAME remainder when divided by K,
their difference will always be divisible by K.


Example:

Current Prefix = 7
Previous Prefix = 2
K = 5

7 % 5 = 2
2 % 5 = 2

Same remainder.

Therefore:

7 - 2 = 5

And:

5 % 5 = 0

So the subarray between these two prefixes has a sum divisible by 5.


Step 2: What Does HashMap Store?

The HashMap stores:

remainder → frequency

We start with:

map = {0=1}

This means:

Before the array starts,
prefix sum = 0,
and its remainder is also 0.

This is necessary for subarrays starting from index 0.

 */