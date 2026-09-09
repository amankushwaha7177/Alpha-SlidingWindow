public class _1B_Bruteforce {

    public static void main(String[] args) {
        int[] arr = {7, 2, 4, 5, 1};
        int k = 9;


        /* Bruteforce : 2 loops i,j
           Pick one element, pair it with all future elements.
           no need to pair with past element because past element is already made pair with this current one.
        */
        for(int i = 0; i<arr.length; i++){
            for(int j=i+1 ; j<arr.length; j++){
                // +1 to avoid repetation of same element.

                if(arr[i] + arr[j] == k){
                    System.out.println("Numbers ->" + arr[i] + " " + arr[j]);
                    System.out.println("Indexes ->" +   i + " " + j);
                    System.out.println("--------");
                }
            }
        }
        /*
        T= o(n2) Slightly better than previous.
        S = o(1)
        */
    }
}
/*
Op:
Numbers ->7 2
Indexes ->0 1
--------
Numbers ->4 5
Indexes ->2 3
--------
 */