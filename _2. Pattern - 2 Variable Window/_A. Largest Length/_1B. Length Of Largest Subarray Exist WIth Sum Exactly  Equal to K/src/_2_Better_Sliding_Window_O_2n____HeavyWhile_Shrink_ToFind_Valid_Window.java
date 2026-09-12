 class _2_Better_Sliding_Window_O_2n____HeavyWhile_Shrink_ToFind_Valid_Window {
    public static void main(String[] args) {
        int k = 14;
        int[] arr = {2,5,1,10,10};     // -> 0 No valid subarray forming ws = 14
        // int[] arr = {2,5,4,10,10};  // -> 2  {4,10}  subarray forming ws = 14

        int l=0;
        int r =0;

        int ws= 0;
        int ans =0;

        while(r < arr.length){
            ws+= arr[r];

            if(ws > k){
                while(ws > k){
                    ws-= arr[l];
                    l++;
                }
            }

            if(ws == k){   // Excluded ws < k condition from atmost case.
                ans = Math.max(ans, r-l+1);

            }

            r++;
        }

        System.out.println(ans);
    }

}
