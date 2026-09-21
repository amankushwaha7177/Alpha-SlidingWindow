import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {2,2,1,0,0,0,1,2,2,1,1,1};

        int low =0;
        int mid =0;
        int high = arr.length-1;

        while(mid <= high){
            if(arr[mid] == 0){
                int x = arr[low];
                arr[low] = arr[mid];
                arr[mid]= x;

                mid++;
                low++;
            } else if (arr[mid] == 1) {
                mid++;
            }
            else {
                int x = arr[high];
                arr[high] = arr[mid];
                arr[mid] = x;
                high--;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}

/*
Time = O(n)
Reason : Each pointer moves only in one direction, so the total number of
         pointer movements remains proportional to the array length.

Space = O(1)
Reason : Only three pointers and a temporary variable are used, requiring
         constant extra space.
*/