import java.util.HashMap;

public class _2B_More_Better_Using_HashMap__Good_If_Index_Is_required {

    public static void main(String[] args) {
        int[] arr = {7, 2, 4, 5, 1};
        int k = 9;

        /*
         basically using map we can do 2 things simultaneusly
         a. iteration of all elemnts.
         b. along with iteration keep putting elements in Map for o(1) lookup.
         c. so when we will stand on ith elements we can check all 0 to i-1 elemt
            using 0(1) lookup.

            Ex: Intution
                0, 1 , 2, 3, 4, 5, 6
                          |
               Suppose i is at 3
               a. when you reaching this than though map you can check all previous elements(0,1,2) in just o(1) not o(n) cool
               b. also when i reaches 4,5,6 than they will pair 3 using map.
                  So in short 3 is able to pair with everyone.

                */

        HashMap<Integer, Integer> m = new HashMap<>();
        for(int i = 0; i<arr.length; i++){
            int b = k- arr[i];
            if(m.containsKey(b)){
                System.out.println(arr[i] + " " + b);
                System.out.println( i  + " " + m.get(b));
                System.out.println("---------");
            }
            m.put(arr[i], i);
        }

        /*
        T= o(n * 1) = o(n) = 1 pass
           Assuming map taking o(1), it may also take logn but will not consider. That is diff kind of map.
        S = o(n)
        */
    }
}
/*
Op:
2 7
1 0
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

       but to give index, Its required have no option remain.
 */