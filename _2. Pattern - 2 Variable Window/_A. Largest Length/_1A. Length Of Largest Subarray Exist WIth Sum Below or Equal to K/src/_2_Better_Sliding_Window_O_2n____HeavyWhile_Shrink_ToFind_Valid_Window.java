public class _2_Better_Sliding_Window_O_2n____HeavyWhile_Shrink_ToFind_Valid_Window {
    public static void main(String[] args) {
        int k = 14;
        int[] arr = {2,5,1,10,10};

        /* Step 1 : variables analogy for window */
        int l=0;
        int r =0;

        int ws= 0;
        int ans =0;

        /* If Interviewer asks Subarray coordinates
        int x=0;
        int y=0;  */

        while(r < arr.length){
            ws+= arr[r];

            if(ws > k){     // --->always write terminatory code above main logic simple.
                while(ws > k){
                    ws-= arr[l];
                    l++;
                }
            }

            if(ws <= k){
                ans = Math.max(ans, r-l+1);

                /* if(r-l+1 > ans)
                {
                    x= l;
                    y=r;
                    ans = r-l+1;
                } */
            }

            r++;
        }

        System.out.println(ans);
        // System.out.println(x + " " + y);
    }

}
/*
arr = [2, 5, 1, 10, 10]
k = 14

[ [2], 5, 1, 10, 10]       sum = 2    length = 1 ✓
[ [2, 5], 1, 10, 10]       sum = 7    length = 2 ✓
[ [2, 5, 1], 10, 10]       sum = 8    length = 3 ✓

[ [2, 5, 1, 10], 10]       sum = 18     ❌ Shrink Now
[ 2, [5, 1, 10], 10]       sum = 16     ❌ Shrink Now
[ 2, 5, [1, 10], 10]       sum = 11   length = 2  ✓  now move r.
[ 2, 5, [1, 10, 10]]       ------> r is mover so it will definitely move at end of this iteration

[ 2, 5, [1, 10, 10]]       sum = 21     ❌ Shrink Now
[ 2, 5, 1, [10, 10]]       sum = 20     ❌ Shrink Now
[ 2, 5, 1, 10, [10]]       sum = 10   length = 1  ✓  now move r.

r = length => stop here.


Maximum length = 3

============================================================
*/