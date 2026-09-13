public class _3_Optimal_O_n__________same_just_dff_loop_condition {
    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;

        int left = 0;
        int right = k-1;

        int windowSum = 0;
        int i = left;
        while(i<=right){
            windowSum+= arr[i];
            i++;
        }
        System.out.println(windowSum);

        int finalAns = windowSum;
        while( right <= arr.length -1 ){
            left++;

            right++;
            if(right== arr.length) {
                break;
            }

            windowSum+= arr[right];

            finalAns =Math.max(finalAns, windowSum);
        }
        System.out.println(finalAns);


    }
}
