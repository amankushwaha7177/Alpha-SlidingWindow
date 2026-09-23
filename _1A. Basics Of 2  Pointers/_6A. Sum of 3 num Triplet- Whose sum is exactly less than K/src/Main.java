import java.util.Arrays;

class Main {
    int countTriplets(int k, int arr[]) {
        // code here
        int count = 0;
        Arrays.sort(arr);
        for( int i =0; i<arr.length; i++){
            int l = i+1;
            int r = arr.length -1;

            if( i > 0 && arr[i] == arr[i-1]){
                continue;
            }

            while(l <  r){
                int sum = arr[i] + arr[l] + arr[r];

                if(sum < k){
                    count = count + r-l;
                    l++;
                    /*
                     Why count += r - l?

                     Example:

                     arr = [-2, 0, 1, 3, 4]
                     k = 5

                     i = 0 → arr[i] = -2
                     l = 1 → arr[l] = 0
                     r = 4 → arr[r] = 4

                     sum = -2 + 0 + 4
                         = 2

                     2 < 5 ✓

                     Now because the array is sorted:

                     arr[l]     = 0
                     arr[l + 1] = 1
                     arr[l + 2] = 3
                     arr[r]     = 4

                     If the largest value 4 works:

                         -2 + 0 + 4 = 2 < 5

                     Then every value between l and r also works: because they will give less sum definetly

                         -2 + 0 + 1 = -1  < 5 ✓
                         -2 + 0 + 3 =  1  < 5 ✓
                         -2 + 0 + 4 =  2  < 5 ✓

                     So we get 3 valid triplets:

                         [-2, 0, 1]
                         [-2, 0, 3]
                         [-2, 0, 4]

                     Number of valid triplets:

                         r - l
                         = 4 - 1
                         = 3

                     Therefore:

                         count += r - l;

                     Instead of checking all three triplets separately,
                     we count all of them at once.

                     Mental Rule:

                     sum < k
                         ↓
                     Largest value at r is valid
                         ↓
                     All values between l and r are also valid
                         ↓
                     Count = r - l
                         ↓
                     Move l forward
                 */
                }
                else if(sum  > k){
                    r--;
                }
                else{
                    l++;
                    // r--;

                    /*
                        Why not:

                            else {
                                l++;
                                r--;
                            }

                        Because when sum < k, we already know that
                        every position between l and r forms a valid triplet.

                        Example:

                        arr = [-2, 0, 1, 3, 4]
                        k = 5

                        i = 0 → -2
                        l = 1 → 0
                        r = 4 → 4

                        sum = -2 + 0 + 4
                            = 2

                        2 < 5 ✓

                        Therefore all these triplets are valid:

                            [-2, 0, 1] ✓
                            [-2, 0, 3] ✓
                            [-2, 0, 4] ✓

                        So:

                            count += r - l
                                   = 4 - 1
                                   = 3

                        Now we move only l:

                            l++

                        Why?

                        Because we already counted every valid triplet
                        using the current l, so we need a new l.

                        We do NOT move r because doing r-- would skip
                        possible valid triplets that we have not counted yet.


                        When sum > k:

                            r--

                        Because the sum is too large, so we need
                        a smaller value from the right side.


                        Mental Rule:

                        sum < k
                            ↓
                        Count all → count += r-l → l++

                        sum > k
                            ↓
                        Sum too large → r--

                        There is no need for:

                            l++;
                            r--;

                        because that would skip valid triplets.
                    */
                }
            }
        }

        return count;
    }
}