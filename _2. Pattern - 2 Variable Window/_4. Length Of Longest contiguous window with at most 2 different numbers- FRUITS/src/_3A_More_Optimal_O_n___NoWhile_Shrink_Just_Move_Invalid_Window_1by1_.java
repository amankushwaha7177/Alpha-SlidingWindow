 class _3A_More_Optimal_O_n___NoWhile_Shrink_Just_Move_Invalid_Window_1by1_ {
    /*
     Brain : The simplest mental translation :

            Question says:
            Pick fruits using 2 baskets.

            Sliding-window language:
            Find the longest contiguous window with at most 2 different
            fruit types.

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