import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 1, 2, 2, 2, 3, 3};
        int k = 4;

        /*
        1. Sort the array so that two-pointer movement becomes possible.
        2. Fix arr[i], then use L and R to find valid pairs after i.
        3. If sum <= k, every element between L and R can form a valid triplet
           with arr[i] and arr[l], so count all of them using r - l.
        4. After counting them, move L++ to find the next possible triplets.
        5. If sum > k, the sum is too large, so move R-- to decrease the sum.
        */

        Arrays.sort(arr);

        int cnt =0;
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
                if (sum == k) {
                    cnt = cnt + r-l;
                    l++; // Yes if sum is valid at l,r directly move l.
                    // System.out.println(arr[i] + " " + a + " " + b);

                    // r--;

                } else if (sum > k) {
                    r--;
                } else { // if sum < k
                    cnt = cnt + r-l;
                    l++;
                }
            }
        /*
        T=  o(n2)
        S = o(1)
        */
        }
        System.out.println(cnt);
    }
}

/*
Dry Run:

arr = [1,1,1,1,2,2,2,3,3], k = 4

i = 0 → arr[i] = 1
------------------
    l = 1, r = 8
    sum = 1+1+3 = 5 > 4 → r--

    l = 1, r = 7
    sum = 1+1+3 = 5 > 4 → r--

    l = 1, r = 6
    sum = 1+1+2 = 4 <= 4
    count += r-l = 6-1 = 5
    cnt = 5
    l++


    l = 2, r = 6
    sum = 1+1+2 = 4 <= 4
    count += 6-2 = 4
    cnt = 9
    l++


    l = 3, r = 6
    sum = 1+1+2 = 4 <= 4
    count += 6-3 = 3
    cnt = 12
    l++


    l = 4, r = 6
    sum = 1+2+2 = 5 > 4 → r--

    l = 4, r = 5
    sum = 1+2+2 = 5 > 4 → r--

    l = 4, r = 4 → stop

i = 1,2,3 → duplicate 1 → continue
i = 4 → arr[i] = 2 → all sums > 4 → no count
i = 5,6 → duplicate 2 → continue
i = 7 → only one element remains → stop

Final cnt = 12

Key:
sum <= k → count r-l triplets → l++
sum > k  → r--
*/