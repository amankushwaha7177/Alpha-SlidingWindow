import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 1};

        int ans = findMaxLength(arr);

        System.out.println("Maximum Length = " + ans);
    }

    static int findMaxLength(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();

        /*
        Store prefix sum 0 at index -1 because the array starts after index -1.
        */
        map.put(0, -1);

        int prefix = 0;
        int maxLength = 0;

        for(int i = 0; i < arr.length; i++) {

            /*
            Convert 0 into -1 and keep 1 as +1.
            */
            if(arr[i] == 0) {
                prefix -= 1;
            } else {
                prefix += 1;
            }

            /*
            Same prefix means the subarray between them has sum 0.
            Sum 0 means equal number of 0s and 1s.
            */
            if(map.containsKey(prefix)) {
                int length = i - map.get(prefix);
                maxLength = Math.max(maxLength, length);
            } else {
                /*
                Store only the first occurrence because it gives the longest length.
                */
                map.put(prefix, i);
            }
        }

        return maxLength;
    }
}

/*
Dry Run:

arr = [0, 1, 0, 1]

Start:

map = {0=-1}
prefix = 0
maxLength = 0


Step 1: i = 0, arr[i] = 0

        0 → -1

        prefix = -1

        -1 is not in map.

        Store:
        map = {0=-1, -1=0}


Step 2: i = 1, arr[i] = 1

        1 → +1

        prefix = 0

        0 already exists at index -1.

        length = i - firstIndex
               = 1 - (-1)
               = 2

        Subarray:
        [0,1]

        maxLength = 2


Step 3: i = 2, arr[i] = 0

        0 → -1

        prefix = -1

        -1 already exists at index 0.

        length = 2 - 0
               = 2

        Subarray:
        [1,0]

        maxLength = 2


Step 4: i = 3, arr[i] = 1

        1 → +1

        prefix = 0

        0 already exists at index -1.

        length = 3 - (-1)
               = 4

        Subarray:
        [0,1,0,1]

        maxLength = 4


Final Answer = 4
*/