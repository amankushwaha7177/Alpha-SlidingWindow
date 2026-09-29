import java.util.HashMap;

public class _2A_Optimal_O_2n_HeavyWhile_Shrink_ToFind_Valid_Window {
    /*
    Idea :
    Expand the window using R and maintain frequency of every character inside it.
    Track the maximum frequency of the current window and calculate required numOfMinorityChar.
    If numOfMinorityChar exceed k, keep moving L and recalculate maxFreqInWindow until valid.
    
    R → keeps expanding the window
    L → keeps shrinking inside while until valid
    maxFreqInWindow → exact maximum frequency in current window
    numOfMinorityChar → windowLength - maxFreqInWindow
    */

    /*
    High Twist :
    ============
    Here we calculate the exact maxFreqInWindow of the current window after every
    L movement instead of keeping the historical maximum frequency from earlier windows.

    This makes the Heavy While logic easier to understand because maxFreqInWindow always
    represents the actual highest character frequency inside the current window.

    After removing a character from the left, that character's frequency may decrease
    and another character may become the new maximum frequency inside the current window.

    Therefore, we recalculate maxFreqInWindow by traversing the Map after every L movement.

    Since this problem contains only 26 uppercase English characters, this traversal
    is at most 26 operations and therefore can be considered O(1).

    Example :

    Current Window = "AABAB"
    Map = { A → 3, B → 2 }
    maxFreqInWindow = 3

    Remove left A :

    Current Window = "ABAB"
    Map = { A → 2, B → 2 }
    maxFreqInWindow = 2

    Therefore:

    numOfMinorityChar = windowLength - maxFreqInWindow
                 = 4 - 2
                 = 2

    This exact current-window maxFreqInWindow makes the Heavy While logic very clear.
    */

    public static void main(String[] args) {
        String str = "AABABBA";
        int k = 1;

        int ans = 0;
        int l = 0;
        int maxFreqInWindow = 0;

        HashMap<Character, Integer> map = new HashMap<>();

        for(int r = 0; r < str.length(); r++) {
            char current = str.charAt(r); // Get the character entering the window.

            map.put(current, map.getOrDefault(current, 0) + 1); // Increase its frequency.

            maxFreqInWindow = Math.max(maxFreqInWindow, map.get(current)); // Update maximum frequency.

            int windowLength = r - l + 1; // Calculate current window length.
            int numOfMinorityChar = windowLength - maxFreqInWindow; // Calculate required numOfMinorityChar.

            while(numOfMinorityChar > k) {
                char left = str.charAt(l); // Get the character leaving the window.

                map.put(left, map.get(left) - 1); // Decrease its frequency.

                l++; // Move left boundary forward by one position.

                maxFreqInWindow = 0; // Reset maxFreqInWindow before finding current maximum.

                for(int frequency : map.values()) { // ---> We can eliminate it.
                    maxFreqInWindow = Math.max(maxFreqInWindow, frequency); // Find exact current maximum frequency.
                }

                windowLength = r - l + 1; // Recalculate current window length.
                numOfMinorityChar = windowLength - maxFreqInWindow; // Recalculate required numOfMinorityChar.
            }

            /* Yaha Tak ana matlab numOfMinorityChar<k or numOfMinorityChar=k*/
            ans = Math.max(ans, r - l + 1); // Update answer using the valid window.
        }

        System.out.println("Answer = " + ans);

        /*
        Time = O(n × 26) = O(n)
        Reason : R moves n times, L moves at most n times, and Map traversal has at most 26 characters.

        Space = O(26) = O(1)
        Reason : The HashMap can contain only 26 uppercase English alphabet characters.
        */

        System.out.println("Time = O(n), Space = O(1)");
    }
}


/*
Dry Run Example :

str = "AABABBA"
k = 1
===================

l = 0
maxFreqInWindow = 0

a. r = 0 → "A"       => Map: { A → 1 }

    maxFreqInWindow = {0, 1} = 1    |    windowLength = 1
    numOfMinorityChar = 1 - 1 = 0
    Valid  → ans = 1


b. r = 1 → "AA"      => Map: { A → 2 }

    maxFreqInWindow = {1, 2} = 2    |   windowLength = 2
    numOfMinorityChar = 2 - 2 = 0
    Valid → ans = 2


c. r = 2 → "AAB"     => Map: { A → 2, B → 1 }

    maxFreqInWindow = {2, 1} = 2    |   windowLength = 3
    numOfMinorityChar = 3 - 2 = 1
    Valid → ans = 3


d. r = 3 → "AABA"    => Map: { A → 3, B → 1 }

    maxFreqInWindow = {2, 3} = 3    |    windowLength = 4
    numOfMinorityChar = 4 - 3 = 1
    Valid → ans = 4


e. r = 4 → "AABAB"   => Map: { A → 3, B → 2 }

    maxFreqInWindow = {3, 2} = 3    |   windowLength = 5
    numOfMinorityChar = 5 - 3 = 2
    Invalid → Enter while loop because numOfMinorityChar > k

        while :  Remove str[l] = A
                 Map: { A → 2, B → 2 }
                 l = 1
                 Now → "ABAB"

                 maxFreqInWindow = {2, 2} = 2   |  windowLength = 4
                 numOfMinorityChar = 4 - 2 = 2
                 Still Invalid → Enter while again

        while : Remove str[l] = B
                 Map: { A → 2, B → 1 }
                 l = 2
                 Now → "BAB"

                 maxFreqInWindow = {2, 1} = 2    | windowLength = 3
                 numOfMinorityChar = 3 - 2 = 1
                 Valid → Exit while

    Coming out of loop means numOfMinorityChar <= k
    ans remains 4


f. r = 5 → "BABB"   => Map: { A → 1, B → 3 }

    maxFreqInWindow = {2, 3} = 3      |    windowLength = 4
    numOfMinorityChar = 4 - 3 = 1
    Valid → ans = 4


g. r = 6 → "BABBA"  => Map: { A → 2, B → 3 }

    maxFreqInWindow = {3, 2} = 3      |   windowLength = 5
    numOfMinorityChar = 5 - 3 = 2
    Invalid → Enter while loop because numOfMinorityChar > k

        while : Remove str[l] = B
                 Map: { A → 2, B → 2 }
                 l = 3
                 Now → "ABBA"

                 maxFreqInWindow = {2, 2} = 2   |  windowLength = 4
                 numOfMinorityChar = 4 - 2 = 2
                 Still Invalid → Enter while again

        while : Remove str[l] = A
                 Map: { A → 1, B → 2 }
                 l = 4
                 Now → "BBA"

                 maxFreqInWindow = {1, 2} = 2   |  windowLength = 3
                 numOfMinorityChar = 3 - 2 = 1
                 Valid → Exit while

    Coming out of loop means numOfMinorityChar <= k
    ans remains 4


Final Answer = 4

The longest valid window has length 4, such as "AABA", where A appears
three times and only one B needs replacement, which is allowed by k = 1.


Important Heavy While Pattern :

R moves forward → Add current character → Calculate numOfMinorityChar
→ If invalid → Keep moving L inside while until the current window becomes valid
→ Recalculate exact maxFreqInWindow after every L movement
→ Update answer only after the window becomes valid.
*/