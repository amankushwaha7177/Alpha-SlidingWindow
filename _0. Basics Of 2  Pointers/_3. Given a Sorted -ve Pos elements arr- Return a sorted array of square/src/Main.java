import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {-4, -3, 0, 1, 2};
        int l= 0;
        int r=arr.length-1;

        int[] ans = new int[arr.length];
        int k =arr.length-1;

        while(l <= r){
            int leftSqr = arr[l] * arr[l];
            int rightSqr = arr[r] * arr[r];

            if(leftSqr > rightSqr){
                ans[k] = leftSqr;
                k--;
                l++;
            } else if ( rightSqr > leftSqr) {
                ans[k] = rightSqr;
                k--;
                r--;
            }
            else{
                ans[k] = leftSqr;
                k--;
                l++;
            }
        }
        System.out.println(Arrays.toString(ans));
    }
}

// [0, 1, 4, 9, 16]

/*
Why can we not put the smaller square into ans from left to right?

Our comparison correctly finds the smaller square between the two boundary
elements, but that does not mean it is the smallest square in the whole window.

Example:
arr = {-4, -3, 0, 1, 2}

Squares:
16, 9, 0, 1, 4

L points to -4 → square = 16
R points to  2 → square = 4

Our comparison finds 4 as the smaller boundary square, so we would put 4
into ans[0].
{ 4, _________ } x
However, the actual smallest square is 0, which is inside
the window and is not currently at either L or R.

Therefore, comparing L and R can guarantee the largest remaining square,
because the largest square must come from one of the two boundaries.
{ ___________ 16 }

But it cannot guarantee the smallest remaining square, because the smallest
square can exist anywhere inside the remaining window.

So the standard approach finds the largest square first and fills ans
from right to left, producing the final sorted array in ascending order.

if(sq2 < sq1){
            ans[k] = sq2;
            k++;
            r--;
}
else if(sq1 < sq2){
            ans[k] = sq1;
            k++;
            l++;
}

 */