import java.util.HashMap;

public class _2B_Optimal_O_n_HeavyWhile_Shrink_ToFind_Valid_Window {
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
                char left = str.charAt(l); // Get the character leaving the window.

                map.put(left, map.get(left) - 1); // Decrease its frequency.

                l++; // Move left boundary one position forward.

                windowLength = r - l + 1; // Recalculate window length.
                replacements = windowLength - maxFrequency; // Recalculate required replacements.
            }

            if(replacements <= k) {
                ans = Math.max(ans, r - l + 1); // Update answer using the valid window.
            }
        }

        System.out.println("Answer = " + ans);

        /*
        Time = O(2n) = O(n)
        Reason : r moves n times while l can also move at most n times overall.
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

l = 0
maxFrequency = 0

a. r = 0 → "A"       => Map: { A → 1 }

    maxFrequency = {0, 1} = 1
    windowLength = 1
    replacements = 1 - 1 = 0
    Valid → ans = 1


b. r = 1 → "AA"      => Map: { A → 2 }

    maxFrequency = {1, 2} = 2
    windowLength = 2
    replacements = 2 - 2 = 0
    Valid → ans = 2


c. r = 2 → "AAB"     => Map: { A → 2, B → 1 }

    maxFrequency = {2, 1} = 2
    windowLength = 3
    replacements = 3 - 2 = 1
    Valid → ans = 3


d. r = 3 → "AABA"    => Map: { A → 3, B → 1 }

    maxFrequency = {2, 3} = 3
    windowLength = 4
    replacements = 4 - 3 = 1
    Valid → ans = 4


e. r = 4 → "AABAB"   => Map: { A → 3, B → 2 }

    maxFrequency = {3, 2} = 3
    windowLength = 5
    replacements = 5 - 3 = 2
    Invalid → Enter while loop because replacements > k

    while : Remove str[l] = A
             Map: { A → 2, B → 2 }
             l = 1
             Now → "ABAB"

             windowLength = 4
             replacements = 4 - 3 = 1
             Valid → Exit while


f. r = 5 → "ABABB"   => Map: { A → 2, B → 3 }

    maxFrequency = {3, 3} = 3
    windowLength = 5
    replacements = 5 - 3 = 2
    Invalid → Enter while loop because replacements > k

    while : Remove str[l] = A
             Map: { A → 1, B → 3 }
             l = 2
             Now → "BABB"

             windowLength = 4
             replacements = 4 - 3 = 1
             Valid → Exit while


g. r = 6 → "BABBA"   => Map: { A → 2, B → 3 }

    maxFrequency = {3, 3} = 3
    windowLength = 5
    replacements = 5 - 3 = 2
    Invalid → Enter while loop because replacements > k

    while : Remove str[l] = B
             Map: { A → 2, B → 2 }
             l = 3
             Now → "ABBA"

             windowLength = 4
             replacements = 4 - 3 = 1
             Valid → Exit while


Final Answer = 4

The longest valid window has length 4, such as "AABA", where A appears
three times and only one B needs replacement, which is allowed by k = 1.


Important Heavy While Pattern :

R moves forward → Add current character → Calculate replacements
→ If invalid → Keep moving L inside while until the window becomes valid
→ Update answer only after the window becomes valid.
*/