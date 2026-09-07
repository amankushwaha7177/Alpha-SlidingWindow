import java.util.Arrays;

public class _2_Optimal_2Pointer {

    public static void main(String[] args) {
        int[] arr = {7, 2, 4, 5, 1};
        int k = 9;

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

        Arrays.sort(arr);

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
        T=  o(n/2)
        S = o(1)
        */
    }
}
/*
Op:
        2 7
        4 5
 */
