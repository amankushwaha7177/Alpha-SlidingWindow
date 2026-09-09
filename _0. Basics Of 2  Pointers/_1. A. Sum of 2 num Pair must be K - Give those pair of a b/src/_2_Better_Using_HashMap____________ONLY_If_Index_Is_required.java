import java.util.HashMap;

public class _2_Better_Using_HashMap____________ONLY_If_Index_Is_required {

    public static void main(String[] args) {
        int[] arr = {7, 2, 4, 5, 1};
        int k = 9;

        /*
         Just pick one element and search what we can add on it to make sum =k
         Suppose 2+ b =9 -> b=7

         than search this b if its present in array. this pair is ans.

         Note : If we search again this element in array it will take o(n)
                so use hashMap which gives presence of key in o(1)*/


        HashMap<Integer, Integer> m = new HashMap<>();
        for(int i = 0; i<arr.length; i++){
            m.put(arr[i], i);
        }
        System.out.println((m.toString()));
        // {7-> 0, 2->1, 4-> 2, 5-> 3, 1-> 4 }

        for(int i = 0; i<arr.length; i++){
            int a = arr[i];      // if a = 7
            int b = k - arr[i];  //    b = 9-7 = 2 | Now search for 2 as b
            /*
            its costly ( oh no )
            for(int j=i ; j<arr.length; j++){
                if(b == arr[j]){
                    System.out.println(arr[i] + " " + arr[j]);
                }
            } */
            if(m.containsKey(b)){
                System.out.println(a + " "+b);
                System.out.println(i + " "+ m.get(b)); // ----> If require Indexes.
                System.out.println("---------");
            }
        }
        /*
        T= o(2n * 1) = o(2n) = 2 pass
           Assuming map taking o(1), it may also take logn(ordered map) but will not consider. That is diff kind of map.
        S = o(n)
        */
    }
}
/*
Op:
7 2
0 1
---------
2 7
1 0
---------
4 5
2 3
---------
5 4
3 2
---------
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