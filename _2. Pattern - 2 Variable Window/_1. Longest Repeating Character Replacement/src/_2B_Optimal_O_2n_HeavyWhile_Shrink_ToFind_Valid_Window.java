import java.util.HashMap;

public class _2B_Optimal_O_2n_HeavyWhile_Shrink_ToFind_Valid_Window {
    /*
    Idea :
    Expand the window using j and maintain frequency of every character inside it.
    Track the maximum frequency and calculate how many characters need replacement.
    If replacements exceed k, keep moving i until the window becomes valid again.

    R → keeps expanding the window
    L → keeps shrinking inside while until valid
    maxFrequency → highest frequency seen so far
    replacements → windowLength - maxFrequency
    */

    /*
    High Twist :
    ============
    Why do we keep outdated maxFrequency instead of calculating the exact
    maximum frequency of the current window every time L moves?

    Suppose an earlier window had:
    "AABA"

    Map: { A → 3, B → 1 }
    maxFrequency = 3

    Later, after moving L, the current window becomes:
    "ABAB"

    Map: { A → 2, B → 2 }
    Actual maxFrequency = 2

    But we continue keeping maxFrequency = 3.

    The reason is that we only need to find the maximum possible window length,
    not perfectly validate every intermediate window after every movement of L.

    The maxFrequency = 3 was already achieved by an earlier valid window,
    so keeping this historical maximum does not create a better answer incorrectly.

    If we calculate the exact maximum after every L movement, we must traverse
    the Map repeatedly to find the current maximum frequency, which adds extra work.

    Therefore, we keep the highest frequency seen so far and avoid recalculating it.

    Current window → defined exactly by L and R.
    Map frequencies → always represent the current window.
    maxFrequency → highest frequency seen so far, intentionally not decreased.

    This historical maxFrequency is the key optimization that keeps the solution O(n).
    */

    public static void main(String[] args) {
        String str = "AABABBA";
        int k = 1;

        int ans = 0;
        int l = 0;
        int maxFrequency = 0;

        HashMap<Character, Integer> map = new HashMap<>();

        for(int r = 0; r < str.length(); r++) {
            char current = str.charAt(r); // Get the character entering the window.

            map.put(current, map.getOrDefault(current, 0) + 1); // Increase its frequency.

            maxFrequency = Math.max(maxFrequency, map.get(current)); // Update highest frequency.

            int windowLength = r - l + 1; // Calculate current window length.
            int replacements = windowLength - maxFrequency; // Calculate required replacements.

            if(replacements > k) {
                while(replacements > k) {
                    char left = str.charAt(l); // Get the character leaving the window.

                    map.put(left, map.get(left) - 1); // Decrease its frequency.

                    l++; // Move left boundary one position forward.

                    /*
                    maxFrequency = 0;                        // Reset maxFrequency before finding current maximum.
                    for(int frequency : map.values()) {       // ---> eliminated it.
                        maxFrequency = Math.max(maxFrequency, frequency); // Find exact current maximum frequency.
                    } */

                    windowLength = r - l + 1; // Recalculate window length.
                    replacements = windowLength - maxFrequency; // Recalculate replacements.
                }
            }

            ans = Math.max(ans, r - l + 1); // Update answer using the valid window.
        }

        System.out.println("Answer = " + ans);

        /*
        Time = O(2n) = O(n)
        Reason : j moves n times while i can also move at most n times overall.
        Space = O(n)
        Reason : The HashMap stores character frequencies present inside the window.
        */

        System.out.println("Time = O(n), Space = O(n)");
    }
}


/*
Dry Run Example :

str = "AABABBA"
k = 1
===================

i = 0
maxFrequency = 0

a. j = 0 → "A"       => Map: { A → 1 }

    maxFrequency = {0, 1} = 1
    windowLength = 1
    replacements = 1 - 1 = 0
    Valid → ans = 1


b. j = 1 → "AA"      => Map: { A → 2 }

    maxFrequency = {1, 2} = 2
    windowLength = 2
    replacements = 2 - 2 = 0
    Valid → ans = 2


c. j = 2 → "AAB"     => Map: { A → 2, B → 1 }

    maxFrequency = {2, 1} = 2
    windowLength = 3
    replacements = 3 - 2 = 1
    Valid → ans = 3


d. j = 3 → "AABA"    => Map: { A → 3, B → 1 }

    maxFrequency = {2, 3} = 3
    windowLength = 4
    replacements = 4 - 3 = 1
    Valid → ans = 4


e. j = 4 → "AABAB"   => Map: { A → 3, B → 2 }

    maxFrequency = {3, 2} = 3
    windowLength = 5
    replacements = 5 - 3 = 2
    Invalid → Enter while loop because replacements > k

    while :  Remove str[i] = A
             Map: { A → 2, B → 2 }
             i = 1
             Now => "ABAB"

             windowLength = 4
             replacements = 4 - 3 = 1
             Valid → Exit while


f. j = 5 → "ABABB"   => Map: { A → 2, B → 3 }

    maxFrequency = {3, 3} = 3
    windowLength = 5
    replacements = 5 - 3 = 2
    Invalid → Enter while loop because replacements > k

    while : Remove str[i] = A
             Map: { A → 1, B → 3 }
             i = 2
             windowLength = 4
             replacements = 4 - 3 = 1
             Valid → Exit while


g. j = 6 → "BABBA"   => Map: { A → 2, B → 3 }

    maxFrequency = {3, 3} = 3
    windowLength = 5
    replacements = 5 - 3 = 2
    Invalid → Enter while loop because replacements > k

    while : Remove str[i] = B
             Map: { A → 2, B → 2 }
             i = 3
             windowLength = 4
             replacements = 4 - 3 = 1
             Valid → Exit while


Final Answer = 4

The longest valid window has length 4, such as "AABA", where A appears
three times and only one B needs replacement, which is allowed by k = 1.


Important Heavy While Pattern :

R moves forward → Add current character → Calculate replacements
→ If invalid → Keep moving i inside while until the window becomes valid
→ Update answer only after the window becomes valid.
*/