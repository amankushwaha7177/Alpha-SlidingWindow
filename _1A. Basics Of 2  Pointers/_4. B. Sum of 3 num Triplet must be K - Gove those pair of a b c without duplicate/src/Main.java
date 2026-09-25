import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 1, 2, 2, 2, 3, 3};
        int k = 4;

        /*
        1. Use 2 pointer l,r to move
        2. if currentTarget < target ===> Increase | Move l++
           if currentTarget > target ===> Decrease | Move r--
           if currentTarget ==target ===> Move both l++ r--
                                          Than also skip all duplicates from both sides.

        3. There are 2 ways to pause the loop
           a. when pointers crosses each other ( l>r | r<l)
           b. or when both they meets
           Choose according to the question*/

        Arrays.sort(arr);

        for( int i = 0; i<= arr.length-3 ; i++) {
            // Elemenate duplacates in i also. ----------------> Usefull in 3Sum to skip i duplicate
            if(i >0 && arr[i] == arr[i-1]){
                continue;
            }
            /*          0   1  2  3  4
                arr = [-1, -1, 0, 1, 2]

                i = 0
                    arr[i] = -1

                    Now It can find: [-1, 0, 1]

                i = 1
                    arr[i] = -1 again
                          ↓
                    Same starting value as i = 0
                          ↓
                    Now It can find:
                    [-1, 0, 1] again ❌ But it should not.

                    So skip i = 1.

                i = 2
                    arr[i] = 0

                    This is a NEW starting value.
                    So we process i = 2.


                Therefore:

                if(i > 0 && arr[i] == arr[i - 1])
                    continue;

                i = 1 is skipped because:

                    arr[1] == arr[0]
                    -1    == -1 ✓

                But i = 2 is NOT skipped because:

                    arr[2] == arr[1]
                     0    == -1 ✗
            */

            int l =i+1;
            int r=arr.length-1;

            while (l < r) {
                int a = arr[l];
                int b = arr[r];
                int sum = arr[i] + a + b;
                if (sum == k) {
                    System.out.println(arr[i] + " " + a + " " + b);
                    l++;
                    r--;

                    // Elemenate duplacates in l,r also. --------------> Usefull in 2Sum to skip l,r duplicates
                    while (l < r && arr[l] == arr[l - 1]) l++;
                    while (l < 5 && arr[r] == arr[r + 1]) r--;
                    /*
                            0   1   2   3   4   5   6
                    arr = [-1,  0,  0,  1,  1,  2,  2]

                    After finding a valid triplet:

                    l = 1
                        arr[l] = 0

                         Now It can find: [-1, 0, 1]

                    l = 2
                        arr[l] = 0 again
                              ↓
                        Same left value as l = 1
                              ↓
                        Now It can find::
                        [-1, 0, 1] again ❌

                        So skip l = 2.

                    Therefore:

                    while(l < r && arr[l] == arr[l - 1])
                        l++;

                    l = 2 is skipped because:

                        arr[2] == arr[1]
                         0    ==  0 ✓


                    Now for r:

                    r = 4
                        arr[r] = 1

                        Find: [-1, 0, 1]

                    r = 3
                        arr[r] = 1 again
                              ↓
                        Same right value as r = 4
                              ↓
                        It can find:
                        [-1, 0, 1] again ❌

                        So skip r = 3.

                    Therefore:

                    while(l < r && arr[r] == arr[r + 1])
                        r--;

                    r = 3 is skipped because:

                        arr[3] == arr[4]
                         1    ==  1 ✓


                    Important:

                    l duplicate → move l forward
                    r duplicate → move r backward

                    Both are skipped because they can create
                    the same triplet again.
                */
                } else if (sum > k) {
                    r--;
                } else {
                    l++;
                }
            }
        /*
        T=  o(n2)
        S = o(1)
        */
        }
    }
}
