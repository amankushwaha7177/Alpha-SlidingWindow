public class _1_BruteForce {
    public static void main(String[] args) {
        int[] arr = {2,5,1,7,10};
        int k = 14;

        int largestArrayLength = 0;
        for (int i =0; i<arr.length ; i++){
            int variableWindowSum = 0;            //----> Check All Windows possible from each index.
            for (int j=i;  j< arr.length ; j++){
                variableWindowSum+= arr[j];      // ----> Increase window size 1by1 each step

                if(variableWindowSum > k){       // ----> If sum becoming larger than k upcoming elements
                    break;                       //       will make it more larger so break here no need to check.
                }
                if(variableWindowSum <= k){      // ----> Valid sum : compare its length with previous length.
                    largestArrayLength = Math.max(largestArrayLength, j-i +1);
                }
            }
        }
        System.out.println(largestArrayLength);
    }
}
