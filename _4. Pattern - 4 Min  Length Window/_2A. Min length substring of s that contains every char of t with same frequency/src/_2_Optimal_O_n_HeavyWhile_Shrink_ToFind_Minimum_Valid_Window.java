import java.util.HashMap;

public class _2_Optimal_O_n_HeavyWhile_Shrink_ToFind_Minimum_Valid_Window {
    /*
    Idea :
    Expand the window using r and maintain frequency of characters inside the window.
    Track how many required characters currently have their required frequency satisfied.
    When all required characters are satisfied, the window becomes valid.
    Then keep moving l while the window remains valid to find the minimum window.

    R → keeps expanding the window
    L → keeps shrinking inside while until window becomes invalid
    requiredChars → number of distinct characters required from t
    formedChars → number of required characters currently satisfied
    */

    public static void main(String[] args) {
        String s = "ADOBCA";
        String t = "ABC";

        HashMap<Character, Integer> required = new HashMap<>();
        HashMap<Character, Integer> ourMap = new HashMap<>();

        for(int i = 0; i < t.length(); i++) {
            char current = t.charAt(i); // Get each required character from t.

            required.put(current, required.getOrDefault(current, 0) + 1); // Store required frequency.
        }
        // required = { A → 1, B → 1, C → 1 }

        int requiredChars = required.size(); // Store number of distinct characters required.
        int formedChars = 0; // Track how many required characters currently satisfy their frequency.

        int l = 0;
        int ans = Integer.MAX_VALUE;
        int ansL = 0;
        int ansR = 0;

        for(int r = 0; r < s.length(); r++) {
            char current = s.charAt(r); // Get the character entering the window.

            ourMap.put(current, ourMap.getOrDefault(current, 0) + 1); // Increase current character frequency.

            /* Step 1 : If this char at r exits in target
                        Also matches frequency then we found match
                        => so form this. */
            if(required.containsKey(current) &&
                    ourMap.get(current).intValue() == required.get(current).intValue()) {
                formedChars++; // Required frequency is completely satisfied for this character.
            }

            /* Step 2: if result is found update ans.
                       and before moving to next r.
                       lets try to shrink and minimize this window as much as possible for better result at r.
                       Else capture current window. */

            /* Way to enter this loop if formedChars == requiredChars
               Way to get from Loop -> reduce formedChars |
               Means make window invalid and Go on mission to find better window.
            */
            while(formedChars == requiredChars) {

                int windowLength = r - l + 1; // Calculate current valid window length.
                if(windowLength < ans) {
                    ans = windowLength; // Store the smallest valid window length.
                    ansL = l; // Store starting index of the best window.
                    ansR = r; // Store ending index of the best window.
                }

                char left = s.charAt(l); // Get the character leaving the window.
                ourMap.put(left, ourMap.get(left) - 1); // Decrease frequency of the left character.


                /* if removed element is not in t. --> No worry we dont care
                   But if rmeoved element is in t + its frequency is becoming less as compare to required
                   Than we need to down formedChars. Simple !
                 */
                if(required.containsKey(left) &&
                        ourMap.get(left) < required.get(left)) {
                    formedChars--;
                }

                l++; // Move left boundary forward to shrink the window.
            }
        }

        String answer = ans == Integer.MAX_VALUE ? "" : s.substring(ansL, ansR + 1);

        System.out.println("Answer = " + answer);

        /*
        Time = O(2n) = O(n)
        Reason : r moves forward n times while l also moves forward at most n times overall.
        Space = O(n)
        Reason : The HashMaps store character frequencies required by t and present in the window.
        */

        System.out.println("Time = O(n), Space = O(n)");
    }
}
/*
Dry Run Example :

s = "AAOBCC"
t = "ABCC"
===================

Required Map:
{ A → 1, B → 1, C → 2 }

requiredChars = 3
formedChars = 0
l = 0


a. r = 0 → "A"

    Add A
    ourMap = { A → 1 }

    A required frequency = 1
    A current frequency = 1
    A requirement satisfied
    formedChars = 1

    formedChars != requiredChars
    Invalid → Continue R


b. r = 1 → "AA"

    Add A
    ourMap = { A → 2 }

    A required frequency = 1
    A current frequency = 2

    A requirement was already satisfied
    formedChars = 1

    formedChars != requiredChars
    Invalid → Continue R


c. r = 2 → "AAO"

    Add O
    ourMap = { A → 2, O → 1 }

    O is not required
    formedChars = 1

    formedChars != requiredChars
    Invalid → Continue R


d. r = 3 → "AAOB"

    Add B
    ourMap = { A → 2, O → 1, B → 1 }

    B required frequency = 1
    B current frequency = 1
    B requirement satisfied
    formedChars = 2

    formedChars != requiredChars
    Invalid → Continue R


e. r = 4 → "AAOBC"

    Add C
    ourMap = { A → 2, O → 1, B → 1, C → 1 }

    C required frequency = 2
    C current frequency = 1

    C requirement is not fully satisfied yet
    formedChars = 2

    formedChars != requiredChars
    Invalid → Continue R


f. r = 5 → "AAOBCC"

    Add C
    ourMap = { A → 2, O → 1, B → 1, C → 2 }

    C required frequency = 2
    C current frequency = 2
    C requirement satisfied
    formedChars = 3

    formedChars == requiredChars
    Valid → Enter while


    while #1:

    Window = "AAOBCC"
    windowLength = 6

    ans = "AAOBCC"

    Remove s[l] = A

    Window becomes "AOBCC"
    ourMap = { A → 1, O → 1, B → 1, C → 2 }

    A is still present.
    A required frequency = 1
    A current frequency = 1

    formedChars = 3
    Window remains valid.

    l = 1


    while #2:

    Window = "AOBCC"
    windowLength = 5

    ans = "AOBCC"

    Remove s[l] = A

    Window becomes "OBCC"
    ourMap = { A → 0, O → 1, B → 1, C → 2 }

    A is required but A is now completely removed.

    A current frequency = 0
    A required frequency = 1

    0 < 1
    formedChars = 2

    l = 2

    Window became invalid → Exit while.


g. r = 5 ends because R has reached the last character.

Final Answer = "AOBCC"
Final Length = 5
*/