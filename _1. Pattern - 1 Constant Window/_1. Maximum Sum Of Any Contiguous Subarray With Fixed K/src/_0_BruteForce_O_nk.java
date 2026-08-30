public class _0_BruteForce_O_nk {
    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;

        int left = 0;
        int right = k-1;

        int i =0;
        int windowSum = 0;
        while(i<=right){
            windowSum+=arr[i];
            i++;
        }

        int finalAns = windowSum;
        while(right< arr.length-1){
            left++;
            right++;

            i =left;
            windowSum = 0;
            while(i<=right){
                windowSum+=arr[i];
                i++;
            }
            finalAns = Math.max(finalAns, windowSum);
        }

        System.out.println(finalAns);
    }
}
