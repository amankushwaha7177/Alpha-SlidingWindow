public class _1_BruteForce {
    public static void main(String[] args) {
        int[] arr = {1, 7, 3, 6, 5, 6};

        int ans = pivotIndex(arr);

        System.out.println("Pivot Index = " + ans);
    }

    static int pivotIndex(int[] arr) {
        int n = arr.length;

        int[] prefix = new int[n];

        /* Store cumulative sum from index 0 up to every index. */
        prefix[0] = arr[0]; // prefix = [ 1, ...... ]
        /* calculating Prefix at index 1 is far easy to calculate as compare to 0 in loop.
           becuase left side will 0th element */

        for(int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }

        // prefix = [1, 8, 11, 17, 22, 28]
        int sum = prefix[n - 1];

        for(int i = 0; i < n; i++) {

            /* Prefix before i represents the complete left-side sum. */
            int left = (i == 0) ? 0 : prefix[i - 1];

            /* Total minus left and current element gives the right-side sum. */
            int right = sum - left - arr[i];

            if(left == right) {
                return i;
            }
        }

        return -1;
    }
}

/*        0  1  2    | 3 |    4  5
arr    = [1, 7, 3,   | 6 |  , 5, 6]

prefix = [1, 8, 11,    17   , 22, 28]

at index = 3
------------
left  = prefix[2] = 11
right = sum - left - currentElement
      = 28 - 11 - 6
      = 11

11 == 11
   ↓
Pivot Index = 3
 */