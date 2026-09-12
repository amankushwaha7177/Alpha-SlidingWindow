 class _3A_More_Optimal_O_n___NoWhile_Shrink_Just_Move_Invalid_Window_1by1 {
    /*
    Idea : A. Normal :
               Generate every possible subarray and check its fruit types.
               T = O(n²).

           B. Better :
               Keep a window using L and R.
               Store fruit type -> frequency in HashMap.
               If more than 2 types exist, move L ONE BY ONE
               until the window becomes valid.

               T = O(2n) = O(n).
    */

     public static void main(String[] args) {

         int[] fruits = {1, 1, 2, 1, 2, 3};

         int l = 0;
         int r = 0;

         int ans = 0;

         java.util.HashMap<Integer, Integer> map = new java.util.HashMap<>();

         while(r < fruits.length){

             /* R enters one fruit into the window. */
             map.put(fruits[r], map.getOrDefault(fruits[r], 0) + 1);

             /*
              * If 3 fruit types exist, window becomes invalid.
              *
              * IMPORTANT:
              * Move L only ONE step.
              * Do not use while here.
              */
             if(map.size() > 2){

                 /* Fruit at L leaves the window. */
                 map.put(fruits[l], map.get(fruits[l]) - 1);

                 /*
                  * If its frequency becomes 0,
                  * completely remove that fruit type.
                  */
                 if(map.get(fruits[l]) == 0){
                     map.remove(fruits[l]);
                 }

                 /* Move L only ONE position. */
                 l++;
             }

             /*
              * The window may still be invalid here.
              *
              * That is okay for this one-by-one approach.
              * On the next R iteration, we will check again
              * and move L one more step if required.
              */
             if(map.size() <= 2){
                 ans = Math.max(ans, r - l + 1);
             }

             r++;
         }

         System.out.println(ans);
     }
 }


 /*
fruits = [1, 1, 2, 1, 2, 3]

[ [1], 1, 2, 1, 2, 3]       → map = {1→1}           → length = 1 ✓
[ [1, 1], 2, 1, 2, 3]       → map = {1→2}           → length = 2 ✓
[ [1, 1, 2], 1, 2, 3]       → map = {1→2, 2→1}      → length = 3 ✓
[ [1, 1, 2, 1], 2, 3]       → map = {1→3, 2→1}      → length = 4 ✓
[ [1, 1, 2, 1, 2], 3]       → map = {1→3, 2→2}      → length = 5 ✓


R = 5 adds 3:
[ [1, 1, 2, 1, 2, 3] ]

map = {1→3, 2→2, 3→1}
size = 3 ❌

Since we are using ONE-BY-ONE shrink, we move L only once in this iteration.
update map = {1→2, 2→2, 3→1}
[ 1, [1, 2, 1, 2, 3] ]
      ↑
      L


size = 3 ❌
Window is STILL invalid.
IMPORTANT:
We do NOT update ans because map.size() > 2.

============================================================

Next iteration:

R = 6 → but R is now equal to arr.length.

So the loop ends.

Therefore, with THIS exact one-by-one approach:

Maximum length = 5
============================================================
*/





 /*
 fruits = [1, 1, 1, 2, 3, 4]

         [ [1], 1, 1, 2, 3, 4]
         → map = {1→1}
         → length = 1 ✓

         [ [1, 1], 1, 2, 3, 4]
         → map = {1→2}
         → length = 2 ✓

         [ [1, 1, 1], 2, 3, 4]
         → map = {1→3}
         → length = 3 ✓

         [ [1, 1, 1, 2], 3, 4]
         → map = {1→3, 2→1}
         → length = 4 ✓


         R adds 3:

         [ [1, 1, 1, 2, 3], 4]
         → map = {1→3, 2→1, 3→1}
         → size = 3 ❌

         Move L ONE step:

         [ 1, [1, 1, 2, 3], 4]
         → map = {1→2, 2→1, 3→1}
         → size = 3 ❌

         Still invalid.
         Do NOT update ans.


         ============================================================

         Next R iteration:

         R adds 4:

         [ 1, [1, 1, 2, 3, 4] ]
         → map = {1→2, 2→1, 3→1, 4→1}
         → size = 4 ❌

         Move L ONE step:

         [ 1, 1, [1, 2, 3, 4] ]
         → map = {1→1, 2→1, 3→1, 4→1}
         → size = 4 ❌

         Still invalid.
         Do NOT update ans.


         ============================================================

         R has now reached arr.length.

         Loop ends.

         Maximum length = 4

 */