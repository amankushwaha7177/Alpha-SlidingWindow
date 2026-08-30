 class _3_More_Optimal_O_n {

    public static void main(String[] args) {

        int[] fruits = {3, 3, 3, 1, 2, 2};

        int l = 0;
        int r = 0;

        int ans = 0;

        java.util.HashMap<Integer, Integer> map = new java.util.HashMap<>();

        while(r < fruits.length){

            /* Store the latest index of current fruit. */
            map.put(fruits[r], r);

            /*
            More than 2 fruit types means the window is invalid.
            Directly move L after the LAST occurrence of fruits[L].
            */
            if(map.size() > 2){

                int leftFruit = fruits[l];

                l = map.get(leftFruit) + 1;

                map.remove(leftFruit);
            }

            /* Current window contains at most 2 fruit types. */
            ans = Math.max(ans, r - l + 1);

            r++;
        }

        System.out.println(ans);
    }
}