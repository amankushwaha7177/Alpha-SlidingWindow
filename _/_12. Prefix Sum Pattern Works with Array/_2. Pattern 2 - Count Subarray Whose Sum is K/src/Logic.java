public class Logic {
}
/*
Logic:

We need to count how many subarrays have sum exactly equal to K.

The main idea is:
Current Prefix - Previous Prefix = Subarray Sum

So if we need Subarray Sum = K:

Previous Prefix = Current Prefix - K

Example:

arr = [1, 2, 1, 2]
K = 3

Start with prefix = 0.

We store previous prefix sums inside a HashMap.

map = {0=1}

Why store 0 first?

Because before the array starts, the prefix sum is 0.
This allows us to find subarrays that start from index 0.


Step 1: Take 1.

        prefix = 1

        We need:

        requiredPrefix = prefix - K
                       = 1 - 3
                       = -2

        -2 does not exist in the map.

        So no valid subarray ends here.

        Store prefix 1.


Step 2:

Take 2.

prefix = 3

We need:

requiredPrefix = 3 - 3
               = 0

0 already exists in the map.

That means:

Current Prefix - Previous Prefix
3 - 0 = 3

So [1,2] has sum 3.

count = 1


Step 3:

Take 1.

prefix = 4

We need:

requiredPrefix = 4 - 3
               = 1

1 already exists in the map.

That means:

Current Prefix - Previous Prefix
4 - 1 = 3

So [2,1] has sum 3.

count = 2


Step 4:

Take 2.

prefix = 6

We need:

requiredPrefix = 6 - 3
               = 3

3 already exists in the map.

That means:

Current Prefix - Previous Prefix
6 - 3 = 3

So [1,2] has sum 3.

count = 3


Final Answer = 3


Mental Rule:

Current Prefix
      ↓
Current Prefix - K
      ↓
Search this value in HashMap
      ↓
Found → A subarray with sum K exists


The HashMap simply remembers previous prefix sums.
*/