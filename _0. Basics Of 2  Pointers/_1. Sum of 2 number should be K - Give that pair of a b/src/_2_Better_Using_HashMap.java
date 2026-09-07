import java.util.HashMap;

public class _2_Better_Using_HashMap {

    public static void main(String[] args) {
        int[] arr = {7, 2, 4, 5, 1};
        int k = 9;

        /*
        1. Using Hashmap we will first store all array elements as key
        2. As where our i will stand that will be a
           so b = k-1
           Now check this b in hashmap using containsKey() using o(1)*/

        // 1.
        HashMap<Integer, Integer> m = new HashMap<>();
        for(int i = 0; i<arr.length; i++){
            m.put(arr[i], i);
        }
        System.out.println((m.toString()));
        // {1=4, 2=1, 4=2, 5=3, 7=0}

        // 2.
        for(int i = 0; i<arr.length; i++){
            int a = arr[i];      // if a = 7
            int b = k - arr[i];  //    b = 9-7 = 2 | Now search for 2 as b
            /*
            for(int j=i ; j<arr.length; j++){
                if(b == arr[j]){
                    System.out.println(arr[i] + " " + arr[j]);
                }
            } */
            if(m.containsKey(b)){
                System.out.println(a + " "+b);
                // System.out.println(a + " "+b); ----> If require Indexes.
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