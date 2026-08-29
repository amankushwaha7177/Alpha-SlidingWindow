public class Optimal_O_2k {
    /* Interview : Given an array of cards and fixed k, find the maximum points by picking exactly k cards from either left or right.
       Given : We can pick cards only from the two ends, so selected cards can be a combination of left cards + right cards.
               Fixed K cards + Pick from ends = Card Selection Window
       Idea :  A. Normal : Try every possible combination of cards from left and right.
                    [ [1, 2, 3], 4, 5, 6, 1]  → 6   ( 3 from left + 0 from right )
                    [ [1, 2], 3, 4, 5, 6, [1] ]  → 4   ( 2 from left + 1 from right )
                    [ [1], 2, 3, 4, 5, [6, 1] ]  → 8   ( 1 from left + 2 from right )
                    [ 1, 2, 3, 4, [5, 6, 1] ]  → 12   ( 0 from left + 3 from right )
                   We check all possible K card combinations and find maximum.
                   T = o(k).

               B. Optimal : First take all K cards from left and find their sum.
                  Then remove 1 card from left and add 1 card from right.
                  Again remove 1 card from left and add 1 card from right.
                  This way we check every possible left + right combination without extra loops.
                  T = o(2k).

                   [ [1, 2, 3], 4, 5, 6, 1]       → LEFT = 6,  RIGHT = 0,  Total = 6
                   [ [1, 2], 3, 4, 5, 6, [1] ]    → LEFT = 3,  RIGHT = 1,  Total = 4
                   [ [1], 2, 3, 4, 5, [6, 1] ]    → LEFT = 1,  RIGHT = 7,  Total = 8
                   [ 1, 2, 3, 4, [5, 6, 1] ]      → LEFT = 0,  RIGHT = 12, Total = 12

                   Time = First K cards sum → O(k)  +  Replace all K (from left) with right one by one → O(k)
                   Time =  O(2k)
     */
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 1};
        int k = 3;

        /* Step 0 : variables analogy for card selection :
                    a. 'lws' stores sum of currently selected cards from left.
                    b. 'rws' stores sum of currently selected cards from right.
                    c. 'ans' stores maximum points found among all combinations. */

        /* Step 1 : Initially take K cards from left and find their sum.
                    k = 3 means first select 3 cards from left.
                    [ [1, 2, 3], 4, 5, 6, 1]
                       ←─────→
                       K cards
                    lws = 1 + 2 + 3 = 6 */
        int lws = 0;
        int rws = 0;

        for(int i = 0; i <= k-1; i++){
            lws += arr[i];
        }

        int ans = lws;

        /* Step 2 : Now replace left cards with right cards one by one.
                    We will remove one card from left selection and add one card from right.
                    This gives every possible combination of left + right cards.
                    right starts from last index because cards are picked from the right end. */
        int right = arr.length - 1;

        for(int left = k-1; left >= 0; left--){
            lws -= arr[left];       // remove 1 card from left selection
            rws += arr[right];      // add 1 card from right selection
            right--;

            ans = Math.max(ans, lws + rws);
        }

        System.out.println(ans);
    }
}
/*
Step 1 : Initially take K cards from left
-------  k = 3

         [ [1,  2,  3],  4,  5,  6,  1]    lws = 6
            ←───────→
              K = 3                         ans = 6


[ [1, 2, 3], 4, 5, 6, 1]       → LEFT = 6,  RIGHT = 0,  Total = 6
[ [1, 2], 3, 4, 5, 6, [1] ]    → LEFT = 3,  RIGHT = 1,  Total = 4
[ [1], 2, 3, 4, 5, [6, 1] ]    → LEFT = 1,  RIGHT = 7,  Total = 8 , update ans
[ 1, 2, 3, 4, [5, 6, 1] ]      → LEFT = 0,  RIGHT = 12, Total = 12, update ans

ans = 12
 */
/*
Remember:
a. First take K cards from LEFT.
b. Then remove 1 LEFT card and add 1 RIGHT card.
c. Repeat until all K cards are taken from RIGHT.
d. lws → LEFT selected cards sum.
e. rws → RIGHT selected cards sum.
f. ans → maximum points among all combinations.
*/