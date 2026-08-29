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
                  T = o(k).
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


Step 2 : Remove 1 card from left and add 1 card from right
--------
         Current selection:
         [ [1,  2,  3],  4,  5,  6,  1]
                ↑                    ↑
              remove                add

         [ [1,  2],  3,  4,  5,  6, [1] ]   lws = 3, rws = 1
           ←────→                         ←
         2 from left                  1 from right
         total = 3 + 1 = 4
         ans = max(6, 4) = 6


STEP 3 : Remove 1 more card from left and add 1 more from right
============================================================
         [ [1],  2,  3,  4,  5, [6,  1] ]   lws = 1, rws = 7
            ←                           ←
         1 from left                2 from right
         total = 1 + 7 = 8
         ans = max(6, 8) = 8


STEP 4 : Remove last card from left and add last card from right
============================================================
         [ 1,  2,  3,  4, [5,  6,  1] ]   lws = 0, rws = 12
                        ←───────→
                         3 from right
         total = 0 + 12 = 12
         ans = max(8, 12) = 12


STEP 5 : No more combinations
============================================================
         left has moved before index 0.
         We have checked:

         3 left + 0 right → 6
         2 left + 1 right → 4
         1 left + 2 right → 8
         0 left + 3 right → 12

         Maximum points = 12
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