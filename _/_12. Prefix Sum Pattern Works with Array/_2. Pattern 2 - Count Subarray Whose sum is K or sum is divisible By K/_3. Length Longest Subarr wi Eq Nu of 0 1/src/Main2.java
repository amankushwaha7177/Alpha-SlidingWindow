import java.util.HashMap;

public class Main2 {
    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 1};

        int ans = findMaxLength(arr);

        System.out.println("Maximum Length = " + ans);
    }

    static int findMaxLength(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();

        /*
        Store difference 0 at index -1 because before the array,
        count of 0s and 1s is equal, with both counts equal to zero.
        */
        map.put(0, -1);

        int count0 = 0;
        int count1 = 0;
        int maxLength = 0;

        for(int i = 0; i < arr.length; i++) {

            if(arr[i] == 0) {
                count0++;
            } else {
                count1++;
            }

            /*
            Difference tells us how many more 1s or 0s we currently have.
            */
            int difference = count1 - count0;

            /*
            Same difference means the number of 0s and 1s between
            those two positions is equal.
            */
            if(map.containsKey(difference)) {
                int length = i - map.get(difference);
                maxLength = Math.max(maxLength, length);
            } else {
                /*
                Store only the first occurrence because it gives
                the longest possible subarray later.
                */
                map.put(difference, i);
            }
        }

        return maxLength;
    }
}

/*
Logic:

We need the longest subarray having equal number of 0s and 1s.

Instead of converting:

0 → -1
1 → +1

we directly count:

count0 = number of 0s
count1 = number of 1s

Then calculate:

difference = count1 - count0


Example:

arr = [0, 1, 0, 1]

Start:

count0 = 0
count1 = 0
difference = 0

map = {0=-1}


Step 1: Take 0

        count0 = 1
        count1 = 0

        difference = count1 - count0
                   = 0 - 1
                   = -1

        -1 is not in map.

        Store:
        map = {0=-1, -1=0}


Step 2: Take 1

        count0 = 1
        count1 = 1

        difference = 1 - 1
                   = 0

        0 already exists at index -1.

        length = 1 - (-1)
               = 2

        Subarray:
        [0,1]

        count0 = 1
        count1 = 1

        Equal 0s and 1s ✓

        maxLength = 2


Step 3: Take 0

        count0 = 2
        count1 = 1

        difference = 1 - 2
                   = -1

        -1 already exists at index 0.

        length = 2 - 0
               = 2

        Subarray:
        [1,0]

        count0 = 1
        count1 = 1

        Equal 0s and 1s ✓

        maxLength = 2


Step 4: Take 1

        count0 = 2
        count1 = 2

        difference = 2 - 2
                   = 0

        0 already exists at index -1.

        length = 3 - (-1)
               = 4

        Subarray:
        [0,1,0,1]

        count0 = 2
        count1 = 2

        Equal 0s and 1s ✓

        maxLength = 4


Final Answer = 4


Mental Rule:

Count 0s
   +
Count 1s
   ↓
difference = count1 - count0
   ↓
Same difference
   ↓
Equal number of 0s and 1s
   ↓
Calculate the length


The easiest sentence to remember:

"Store the first index of every count difference, then use the same difference to find the longest balanced subarray."
*/

/*
Time = O(n)
Space = O(n)
*/