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
        T= o(n * 1) = o(n)
        S = o(n)
        */
    }
}
/*
Op:

        7 2
        2 7
        4 5
        5 4
 */


/*
Q. Problem with Hashmap | Interview
A. Always try to avoid use of space.
   Ex. { 1=4}         For 1 size Map  = 2 Integer Required
       { 1=4, 3=7...} For 50 size Map = 100 Integer Required

                     1 Integer = 4 Bytes
                     = So 50 size Map = 100 * 4 Bytes

       Its too much space wastage for CPU Memory.
 */