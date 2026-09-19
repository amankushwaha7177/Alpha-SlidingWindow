public class Main {
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
        for(int i = 1; i < arr.length; i++) {
            left += arr[i - 1];

            /*
            Remove the current element and the complete left sum from total sum
            to get the sum of all elements present on the right side.
            */
            int right = sum - arr[i] - left;

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
arr = [1, 7, 3, 6, 5, 6]

total sum = 28
left = 0


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