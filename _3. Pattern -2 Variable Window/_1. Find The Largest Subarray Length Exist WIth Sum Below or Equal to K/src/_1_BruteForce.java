public class _1_BruteForce {
    public static void main(String[] args) {
        int[] arr = {2,5,1,7,10};
        int k = 14;

        int largestArrayLength = 0;
        for (int i =0; i<arr.length ; i++){
            int variableWindowSum = 0;
            for (int j=i;  j< arr.length ; j++){
                variableWindowSum+= arr[j];

                if(variableWindowSum > k){
                    break;
                }
                if(variableWindowSum <= k){
                    largestArrayLength = Math.max(largestArrayLength, j-i +1);
                }
            }
        }
        System.out.println(largestArrayLength);
    }
}
