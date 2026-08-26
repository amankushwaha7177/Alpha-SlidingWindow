public class _2_Better_Sliding_Window {
    public static void main(String[] args) {
        int k = 14;
//        int[] arr = {2,5,10,7,10};
         int[] arr = {2,5,1,10,10};

        int longestSubarrayLength =0;

        int l=0;
        int r =0;

        int windowSum= 0;
        while(r < arr.length){
            windowSum+= arr[r];

            if(windowSum <= k){
                longestSubarrayLength = Math.max(longestSubarrayLength, r-l+1);
            }

            if(windowSum > k){
                while(windowSum > k){
                    windowSum-= arr[l];
                    l++;
                }
            }
            r++;
        }

        System.out.println(longestSubarrayLength);
    }

}
