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