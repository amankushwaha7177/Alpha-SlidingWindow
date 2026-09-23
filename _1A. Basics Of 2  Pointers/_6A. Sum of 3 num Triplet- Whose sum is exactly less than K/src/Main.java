import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        int k = 5;
        int[] arr = {-2, 0, 1, 3, 4};

        int count = countTriplets(k, arr);

        System.out.println("Count = " + count);
    }

    static int countTriplets(int k, int arr[]) {
        int count = 0;

        Arrays.sort(arr);

        /*
            We fix i as the first element and use l and r
            to find all possible pairs after i.

            Since the array is sorted, two pointers help us
            count multiple valid triplets without checking each
            possible triplet separately.
        */
        for(int i = 0; i < arr.length - 2; i++) {
            int l = i + 1;
            int r = arr.length - 1;

            /*
                The array contains distinct integers, so there
                is no need to skip duplicate values of i.
            */
            while(l < r) {
                int sum = arr[i] + arr[l] + arr[r];

                /*
                    If sum < k, then the largest value arr[r]
                    already produces a valid triplet.

                    Because the array is sorted, every value
                    between l and r is smaller than or equal to arr[r],
                    so every triplet from l through r is also valid.

                    Example:

                    arr = [-2, 0, 1, 3, 4]
                    k = 5

                    i = 0 → -2
                    l = 1 → 0
                    r = 4 → 4

                    sum = -2 + 0 + 4
                        = 2

                    2 < 5 ✓

                    Therefore all these triplets are valid:

                    [-2, 0, 1] ✓
                    [-2, 0, 3] ✓
                    [-2, 0, 4] ✓

                    Number of valid triplets:

                    r - l
                    = 4 - 1
                    = 3

                    So:

                    count += r - l;

                    We count all these triplets together instead
                    of checking each possible right value separately.

                    After counting them, we move l forward because
                    every possible triplet using the current l
                    has already been counted.

                    We do not move r because doing r-- would skip
                    possible combinations that have not been counted.
                */
                if(sum < k) {
                    count += r - l;
                    l++;
                }

                /*
                    If sum >= k, the current triplet is not valid
                    because the condition requires sum strictly less
                    than k.

                    This includes both:

                    sum > k
                    sum == k

                    Since the array is sorted, moving r backward
                    gives us a smaller value and therefore reduces
                    the current sum.

                    So we move r toward the left.
                */
                else {
                    r--;
                }
            }
        }

        return count;
    }
}