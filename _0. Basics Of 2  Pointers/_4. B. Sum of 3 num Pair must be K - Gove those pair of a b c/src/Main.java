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
                                          Than also skip all duplicates from both sides.

        3. There are 2 ways to pause the loop
           a. when pointers crosses each other ( l>r | r<l)
           b. or when both they meets
           Choose according to the question*/

        // 1.

        Arrays.sort(arr);

        for( int i = 0; i< arr.length-2 ; i++) {
            // Elemenate duplacates in i also.
            if(i >0 && arr[i] == arr[i-1]){
                continue;
            }

            int l =i+1;
            int r=arr.length-1;

            while (l < r) {
                int a = arr[l];
                int b = arr[r];
                int sum = arr[i] + a + b;
                if (sum == k) {
                    System.out.println(arr[i] + " " + a + " " + b);
                    l++;
                    r--;

                    // Elemenate duplacates in l,r also.
                    while (arr[l] == arr[l - 1]) l++;
                    while (arr[r] == arr[r + 1]) r--;
                } else if (sum > k) {
                    r--;
                } else {
                    l++;
                }
            }
        /*
        T=  o(n3)
        S = o(1)
        */
        }
    }
}
