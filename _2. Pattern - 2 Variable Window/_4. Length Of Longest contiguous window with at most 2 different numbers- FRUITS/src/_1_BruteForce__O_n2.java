class _1_BruteForce__O_n2 {
    /*
    Idea :  A. BruteForce : Generate every possible contiguous subarray.
                    For each starting index, keep adding fruits to the window.
                    If window contains more than 2 fruit types, stop.
                    Otherwise compare its length with the maximum answer.

                    [ [1], 2, 1, 2, 3]              → length = 1
                    [ [1, 2], 1, 2, 3]              → length = 2
                    [ [1, 2, 1], 2, 3]              → length = 3
                    [ [1, 2, 1, 2], 3]              → length = 4
                    [ [1, 2, 1, 2, 3] ]             → 3 types ❌ stop

                   T = O(n²)
                   Space = O(n)
    */

    public static void main(String[] args) {

        int[] fruits = {1, 2, 1, 2, 3};

        int ans = 0;

        /*
        Step 1 : Start from every index and create every possible
                 contiguous window from that starting point.
        */
        for(int i = 0; i < fruits.length; i++){

            java.util.HashSet<Integer> set = new java.util.HashSet<>();

            /*
            Add fruits one by one.
            Set stores different fruit types inside current window.
            */
            for(int j = i; j < fruits.length; j++){

                set.add(fruits[j]);

                /* More than 2 fruit types means two baskets are not enough. */
                if(set.size() > 2){
                    break;
                }

                /* Current window is valid, so update maximum length. */
                ans = Math.max(ans, j - i + 1);
            }
        }

        System.out.println(ans);
    }
}

/*
fruits = [1, 2, 1, 2, 3]

Start from index 0:
[ [1], 2, 1, 2, 3]       → types = {1}       → length = 1
[ [1, 2], 1, 2, 3]       → types = {1,2}     → length = 2
[ [1, 2, 1], 2, 3]       → types = {1,2}     → length = 3
[ [1, 2, 1, 2], 3]       → types = {1,2}     → length = 4
[ [1, 2, 1, 2, 3] ]       → types = {1,2,3}   → ❌ stop

Start from index 1:
[ 1, [2], 1, 2, 3]       → types = {2}       → length = 1
[ 1, [2,1], 2, 3]        → types = {2,1}     → length = 2
[ 1, [2,1,2], 3]          → types = {2,1}     → length = 3
[ 1, [2,1,2,3]]           → types = {2,1,3}   → ❌ stop

Start from index 2:
[ 1, 2, [1], 2, 3]       → types = {1}       → length = 1
[ 1, 2, [1,2], 3]        → types = {1,2}     → length = 2
[ 1, 2, [1,2,3]]          → types = {1,2,3}   → ❌ stop

Maximum length = 4

============================================================

Remember:
a. Start from every index.
b. Keep adding fruits to the current window.
c. Set stores different fruit types.
d. More than 2 types → stop this window.
e. Valid window → update ans.

Time = O(n²)
Space = O(n)
============================================================
*/