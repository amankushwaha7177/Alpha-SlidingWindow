import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 1, 2, 2, 2, 3, 3};
        int k = 4;

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

                // These 2 lines will extra added just to skip duplicate numbers.
                while( arr[l] == arr[l-1]) l++;
                while( arr[r] == arr[r+1]) r--;
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
