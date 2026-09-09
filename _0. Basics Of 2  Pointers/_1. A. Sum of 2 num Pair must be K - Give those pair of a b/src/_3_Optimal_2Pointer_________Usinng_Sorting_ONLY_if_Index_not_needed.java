import java.util.Arrays;

public class _3_Optimal_2Pointer_________Usinng_Sorting_ONLY_if_Index_not_needed {

    public static void main(String[] args) {
        int[] arr = {7, 2, 4, 5, 1};
        int k = 9;

        // Do Sorting for applying 2 pointers
        // As we need sum so will move l++, r--
        // if we needed duplicate we move l++, r++
        // becuase its easy to apply these conditons on sorted arrays.
        // so i understood how important sorting is for 2 pointers.
        Arrays.sort(arr); // ---> o(nlogn)

        /*
        1. Use 2 pointer l,r to move
        2. if currentTarget < target ===> Increase | Move l++
           if currentTarget > target ===> Decrease | Move r--
           if currentTarget ==target ===> Move both l++ r--

        3. There are 2 ways to pause the loop
           a. when pointers crosses each other ( l>r | r<l)
           b. or when both they meets
           Choose according to the question*/

        // 1.
        int l =0;
        int r=arr.length-1;

        while( l < r ){
            int a = arr[l];
            int b = arr[r];
            int sum = a+b;
            if(sum == k){
                System.out.println(a + " " +b);
                l++;
                r--;
            }
            else if(sum > k){
                r--;
            }
            else{
                l++;
            }
        }
        /*
        T=  o(nlogn + n)
        S = o(1)
        */
    }
}
/*
Op:
        2 7
        4 5
 */
