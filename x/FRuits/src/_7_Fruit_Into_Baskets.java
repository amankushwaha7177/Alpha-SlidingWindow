public class _7_Fruit_Into_Baskets {
    /*
    Interview : Given an array of fruits type, find the maximum number of fruits type
                that can be collected from two baskets.
                1 basket can hold only 1 type of fruit.
                Also we can pickup fruits type contiguous only we can not skip. Once start pickinng up keep pickup.

    Given : Each basket can hold only 1 type of fruit.
            We have 2 baskets, so we can have at most 2 different fruit types.
            We need to find the largest subarray's length possible with at most 2 different fruit types.

            Contiguous + Size is not Fixed + At Most 2 Different Types + SubArray = Variable Window
            subArray = window

    Brain : The simplest mental translation :
            Question says: Pick fruits using 2 baskets.

            Sliding-window language:
            Find the longest contiguous window with at most 2 different numbers.
            Ex: [ [1, 2, 1], 2, 3, 1]   Fine
                [ [1, 2, 1, 2, 3], 1]   ❌ Incorrect.

    Idea :  A. Normal : Generate every possible subarray and check
                    how many different fruit types it contains.

               T = O(n²)

           B. Optimal : Keep a window using L and R.
                      Store fruit type and its frequency in HashMap.
                      If different fruit types become > 2,
                      shrink from LEFT until only 2 types remain.

               T = O(n)
    */

    public static void main(String[] args) {

        int[] fruits = {1, 2, 1, 2, 3};

        /* Step 0 : Variables analogy for window :
                    a. 'l' and 'r' monitor the start and end of the window.
                    b. 'map' stores fruit type → frequency inside the window.
                    c. 'ans' stores the maximum valid window length. */

        int l = 0;
        int r = 0;

        int ans = 0;

        java.util.HashMap<Integer, Integer> map = new java.util.HashMap<>();


        /* Step 1 : Move R one by one and add each fruit into the window.
                    If different fruit types become more than 2,
                    window becomes invalid and we shrink from LEFT. */

        while(r < fruits.length){

            map.put(fruits[r], map.getOrDefault(fruits[r], 0) + 1);

            /* More than 2 different fruit types means two baskets
               are not enough, so shrink from LEFT. */

            if(map.size() > 2){
                while(map.size() > 2){

                    map.put(fruits[l], map.get(fruits[l]) - 1);

                    if(map.get(fruits[l]) == 0){
                        map.remove(fruits[l]);
                    }

                    l++;
                }
            }

            /* Window is valid because it contains at most 2 types. */

            ans = Math.max(ans, r - l + 1);

            r++;
        }

        System.out.println(ans);
    }
}

/*
fruits = [1, 2, 1, 2, 3]

[ [1], 2, 1, 2, 3]       types = {1}       length = 1 ✓
[ [1, 2], 1, 2, 3]       types = {1,2}     length = 2 ✓
[ [1, 2, 1], 2, 3]       types = {1,2}     length = 3 ✓
[ [1, 2, 1, 2], 3]       types = {1,2}     length = 4 ✓

R adds 3 → types = {1,2,3} → ❌ Shrink Now

[ 1, [2, 1, 2, 3] ]      types = {1,2,3}   ❌
[ 1, 2, [1, 2, 3] ]      types = {1,2,3}   ❌
[ 1, 2, 1, [2, 3] ]      types = {2,3}     length = 2 ✓

Maximum length = 4

============================================================

Remember:

a. R moves → fruit ENTERS.
b. HashMap → stores fruit type and its frequency.
c. More than 2 types → window becomes invalid.
d. L moves → fruit LEAVES.
e. Frequency becomes 0 → remove fruit type from HashMap.
f. map.size() <= 2 → window is valid.
g. ans → maximum valid window length.

============================================================
Complexity:

Time = O(n)
Space = O(1)   // At most 3 fruit types temporarily exist.
============================================================
*/