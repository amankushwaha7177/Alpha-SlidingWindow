public class _1B_Bruteforce {

    public static void main(String[] args) {
        int[] arr = {7, 2, 4, 5, 1};
        int k = 9;

        /* Bruteforce : 2 loops i,j
           i for marking a
           j for searching b
           To make Target.
        */
        for(int i = 0; i<arr.length; i++){
            for(int j=i+1 ; j<arr.length; j++){
                // No need to cover previous element because prev elem did cover thhis element already.
                // +1 to avoid repetation of same element.

                // if( i == j) continue;
                // element can't be same ( it will be given in Que.)---> But no need of this condition
                // j=i+1 did 2 good jobs.

                if(arr[i] + arr[j] == k){
                    System.out.println("Numbers ->" + arr[i] + " " + arr[j]);
                    System.out.println("Indexes ->" +   i + " " + j);
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
 */