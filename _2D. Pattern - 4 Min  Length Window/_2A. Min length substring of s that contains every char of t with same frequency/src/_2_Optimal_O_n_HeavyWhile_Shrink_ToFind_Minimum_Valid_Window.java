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
        String s = "AAOBCC"; // AAAAOBCC also good ex.
        String t = "ABCC";

        HashMap<Character, Integer> required = new HashMap<>();
        HashMap<Character, Integer> ourMap = new HashMap<>();

        for(int i = 0; i < t.length(); i++) {
            char current = t.charAt(i); // Get each required character from t.

            required.put(current, required.getOrDefault(current, 0) + 1); // Store required frequency.
        }
        // required = { A → 1, B → 1, C → 2}

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
Dry Run Example:

s = "AAOBCAABC"
t = "AABC"

Required = { A → 2, B → 1, C → 1 }

l = 0
formedChars = 0


r = 0 → "A"
    Map = { A → 1 }
    A current = 1, required = 2
    formedChars = 0
    Invalid → move R


r = 1 → "AA"
    Map = { A → 2 }
    A reaches required frequency.
    formedChars = 1
    Invalid → move R


r = 2 → "AAO"
    O not required.
    formedChars = 1
    Invalid → move R


r = 3 → "AAOB"
    Map = { A → 2, B → 1, O → 1 }
    B reaches required frequency.
    formedChars = 2
    Invalid → move R


r = 4 → "AAOBC"
    Map = { A → 2, B → 1, C → 1, O → 1 }
    C reaches required frequency.
    formedChars = 3

    formedChars == requiredChars
    → Valid


    while #1:
        Window = "AAOBC"
        Length = 5
        ans = "AAOBC"

        Remove A
        A frequency = 1
        1 < required A frequency 2

        formedChars = 2
        Window invalid
        Exit while


r = 5 → "AOBC A"
    A frequency = 2
    A reaches required frequency.
    formedChars = 3

    formedChars == requiredChars
    → Valid


    while #2:
        Window = "AOBCA"
        Length = 5

        Remove A
        A frequency = 1
        1 < required A frequency 2

        formedChars = 2
        Window invalid
        Exit while


r = 6 → "OBCAA"
    A frequency = 1
    formedChars = 2
    Invalid → move R


r = 7 → "OBCAA"
    A frequency = 2
    A reaches required frequency.
    formedChars = 3

    formedChars == requiredChars
    → Valid


    while #3:
        Window = "OBCAA"
        Length = 5

        Remove O
        O not required.
        formedChars = 3


        Window = "BCAA"
        Length = 4
        ans = "BCAA"

        Remove B
        B frequency = 0
        0 < required B frequency 1

        formedChars = 2
        Window invalid
        Exit while


Final Answer = "BCAA"
Final Length = 4
*/


/*
Dry Run Example:

s = "XXCAAAAB"
t = "AABC"

Required = { A → 2, B → 1, C → 1 }

l = 0
formedChars = 0


r = 0 → "X"
    X not required
    formedChars = 0
    Invalid → move R


r = 1 → "XX"
    X not required
    formedChars = 0
    Invalid → move R


r = 2 → "XXC"
    Map = { X → 2, C → 1 }
    C reaches required frequency.
    formedChars = 1
    Invalid → move R


r = 3 → "XXCA"
    Map = { X → 2, C → 1, A → 1 }
    A current = 1, required = 2
    formedChars = 1
    Invalid → move R


r = 4 → "XXCAA"
    Map = { X → 2, C → 1, A → 2 }
    A reaches required frequency.
    formedChars = 2
    Invalid → move R


r = 5 → "XXCAAA"
    A current = 3, required = 2
    formedChars = 2
    Invalid → move R


r = 6 → "XXCAAAA"
    A current = 4, required = 2
    formedChars = 2
    Invalid → move R


r = 7 → "XXCAAAAB"
    Map = { X → 2, C → 1, A → 4, B → 1 }
    B reaches required frequency.
    formedChars = 3

    formedChars == requiredChars
    → Valid


        while #1:
            Window = "XXCAAAAB"
            Length = 8
            ans = "XXCAAAAB"

            Remove X
            X frequency = 1
            X not required
            formedChars = 3

            Window = "XCAAAAB"
            Still valid


        while #2:
            Window = "XCAAAAB"
            Length = 7

            Remove X
            X frequency = 0
            X not required
            formedChars = 3

            Window = "CAAAAB"
            Still valid


        while #3:
            Window = "CAAAAB"
            Length = 6
            ans = "CAAAAB"

            Remove C
            C frequency = 0
            0 < required C frequency 1

            formedChars = 2
            Window invalid
            Exit while


Final Answer = "CAAAAB"
Final Length = 6
*/