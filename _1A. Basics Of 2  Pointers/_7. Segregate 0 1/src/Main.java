public class Main {
    public static void main(String[] args) {
        int[] arr = {0, 1, 1, 0, 1, 0, 0, 1};

        segregate(arr);

        for(int num : arr) {
            System.out.print(num + " ");
        }
    }

    static void segregate(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while(left < right) {
            /*
            Keep Moving left forward while 0 is already in the correct position.
            */
            while(left < right && arr[left] == 0) {
                left++;
            }

            /*
            Keep Moving right backward while 1 is already in the correct position.
            */
            while(left < right && arr[right] == 1) {
                right--;
            }

            /*
            Now left points to 1 and right points to 0,
            so swap them to place both values correctly.
            */
            if(left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;

                left++;
                right--;
            }
        }
    }
}

/*
Recognition:

Array contains only:

    0 and 1

Requirement:

    Put all 0s on the left.
    Put all 1s on the right.

        ↓

    TWO POINTERS


Mental Rule:
  1. Keep going from left and right
  2. left  → find misplaced 1
     right → find misplaced 0

        Found both
            ↓
           Swap


Example:

    [0, 1, 1, 0, 1, 0]

     ↑              ↑
    left           right

    left finds 1.
    right finds 0.

    Swap:

    [0, 0, 1, 1, 1, 0]

    Continue until pointers meet.

Time = O(n)
Space = O(1)
*/