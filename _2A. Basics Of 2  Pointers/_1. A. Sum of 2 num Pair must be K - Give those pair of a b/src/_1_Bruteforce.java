public class _1_Bruteforce {

    public static void main(String[] args) {
        int[] arr = {7, 2, 4, 5, 1};
        int k = 9;

        /* Bruteforce : 2 loops i,j
           Pick one element, pair it with all remaining elements.
           if both makes sum == k ie. ans
        */
        for(int i = 0; i<arr.length; i++){
            for(int j=0 ; j<arr.length; j++){

                if( i == j) continue; // element can't be same ( it will be given in Que.)

                if(arr[i] + arr[j] == k){
                    System.out.println("Numbers ->" + arr[i] + " " + arr[j]);
                    System.out.println("Indexes ->" +   i + " " + j);
                    System.out.println("--------");
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
Numbers ->7 2
Indexes ->0 1
--------
Numbers ->2 7
Indexes ->1 0
--------
Numbers ->4 5
Indexes ->2 3
--------
Numbers ->5 4
Indexes ->3 2
--------
 */