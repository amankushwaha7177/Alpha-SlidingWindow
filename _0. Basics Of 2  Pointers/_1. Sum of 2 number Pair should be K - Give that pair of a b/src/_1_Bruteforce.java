public class _1_Bruteforce {

    public static void main(String[] args) {
        int[] arr = {7, 2, 4, 5, 1};
        int k = 9;

        /* Bruteforce : 2 loops i,j
           i for marking a
           j for searching b
           To make Target.
        */
        for(int i = 0; i<arr.length; i++){
            int a = arr[i];      // if a = 7
            int b = k - arr[i];  //    b = 9-7 = 2 | Now search for 2 as b
            for(int j=0 ; j<arr.length; j++){
                if(b == arr[j]){
                    System.out.println(arr[i] + " " + arr[j]);
                }
            }
        }
        /*
        T= o(n2)
        S = o(1)
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