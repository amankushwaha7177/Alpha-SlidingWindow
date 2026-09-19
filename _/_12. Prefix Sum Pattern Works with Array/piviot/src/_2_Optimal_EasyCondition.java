public class _2_Optimal_EasyCondition {
    public static void main(String[] args) {
        int[] arr = {1, 7, 3, 6, 5, 6};

        int ans = pivotIndex(arr);

        System.out.println("Pivot Index = " + ans);
    }

    static int pivotIndex(int[] arr) {
        int sum = 0;

        /* First calculate the total sum of the complete array. */
        for(int num : arr) {
            sum += num;
        }

        /*
        Start from index 1 because index 0 has no elements on its left.
        Before checking index i, add arr[i - 1] to include everything on the left.
        */
        int left = 0;

        /*
        But What if the pivot is 0th index? Check that edge case separately.
        0----arr[0]----0   */
        int right = sum - arr[0] - 0;
        if(0 == right) {
            return 0;
        }

        for(int i = 1; i < arr.length; i++) {
            left += arr[i - 1];

            /*
            Remove the current element and the complete left sum from total sum
            to get the sum of all elements present on the right side.
            */
            right = sum - arr[i] - left;

            /* If both sides have equal sums, the current index is the pivot. */
            if(left == right) {
                return i;
            }
        }

        /* Return -1 when no index has equal left and right sums. */
        return -1;
    }
}

/*
Dry Run:

arr = [1, 7, 3, 6, 5, 6]

total sum = 28
left = 0


First check index 0:

        left = 0

        right = 28 - arr[0] - 0
              = 28 - 1 - 0
              = 27

        0 != 27
                ↓
        Move to index 1


Step 1 : i = 1

        left = 0 + arr[0]
             = 1

        right = 28 - arr[1] - left
              = 28 - 7 - 1
              = 20

        1 != 20
                ↓
        Move ahead


Step 2 : i = 2

        left = 1 + arr[1]
             = 1 + 7
             = 8

        right = 28 - arr[2] - left
              = 28 - 3 - 8
              = 17

        8 != 17
                ↓
        Move ahead


Step 3 : i = 3

        left = 8 + arr[2]
             = 8 + 3
             = 11

        right = 28 - arr[3] - left
              = 28 - 6 - 11
              = 11

        11 == 11 ✓
                ↓
        Pivot Index = 3
*/


/*
Edge Case:

arr = [2, 1, -1]

total sum = 2
left = 0


First check index 0:

        left = 0

        right = 2 - arr[0] - 0
              = 2 - 2 - 0
              = 0

        0 == 0 ✓
                ↓
        Pivot Index = 0
*/