import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 1, 2, 2, 2, 3, 3};
        int k = 4;

        /*\
            Current sum
                ↓
            Calculate how far it is from k
                ↓
            Is this difference smaller than previous difference?
                ↓
            YES → update closestSum
                ↓
            sum < k → L++
            sum > k → R--
            sum == k → exact answer → cool ! break
         */

        /*
        1. Sort the array first so the two-pointer movement becomes possible.
        2. Fix arr[i], then use L and R to find the remaining two elements.
        3. Calculate sum = arr[i] + arr[l] + arr[r].
        4. Calculate difference = Math.abs(sum - k) to know how close the sum is.
        5. If difference is smaller than the previous difference, update the answer.
        6. If sum < k, we need a bigger sum, so move L++.
        7. If sum > k, we need a smaller sum, so move R--.
        8. If sum == k, the difference is zero, which is the closest possible
           answer, so we can immediately stop because no better answer exists
        */


        Arrays.sort(arr);

        int closestSum = 0;
        for( int i = 0; i< arr.length-2 ; i++) {
            // Elemenate duplacates in i also.
            if(i >0 && arr[i] == arr[i-1]){
                continue;
            }

            int l =i+1;
            int r=arr.length-1;

            while (l < r) {
                int a = arr[l];
                int b = arr[r];
                int sum = arr[i] + a + b;

                int currentSumClosest = Math.abs(sum - k);
                int previousSumClosest = Math.abs(closestSum - k);
                /*
                k = 4 | sum = 5
                difference = |5 - 4| = 1

                currentSumClosest = 1
                closestSum = 5
                 */
                if (currentSumClosest < previousSumClosest) {
                    closestSum = sum;   /* Store the actual triplet sum, not its difference from k. */
                }
                if (sum == k) {
                    break;
                    /*
                    System.out.println(arr[i] + " " + a + " " + b);
                    l++;
                    r--;

                    ----> Elemenate duplacates in l,r also.
                    while (arr[l] == arr[l - 1]) l++;
                    while (arr[r] == arr[r + 1]) r--;
                    */
                } else if (sum > k) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        System.out.println(closestSum);
    }
}
        /*
        T=  o(n3)
        S = o(1)
        */

/*
Normal 3Sum vs Closest 3Sum:

Normal 3Sum asks whether any triplet has exactly the target sum.

    sum == k
        ↓
    Triplet found

Closest 3Sum does not require the sum to equal k exactly.
Instead, we find the triplet whose sum has the smallest difference from k.

    difference = Math.abs(sum - k)

If the current difference is smaller than the previous difference,
we update closestSum with the current triplet's sum.

Example:
k = 5

sum = 8 → difference = |8 - 5| = 3
sum = 6 → difference = |6 - 5| = 1

Since 6 is closer to 5, closestSum becomes 6.

If sum == k, the difference is zero, which is the closest possible
answer, so we can immediately stop because no better answer exists.
*/

