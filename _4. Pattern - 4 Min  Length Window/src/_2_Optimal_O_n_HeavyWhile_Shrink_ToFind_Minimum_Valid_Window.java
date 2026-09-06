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
    requiredCount → number of distinct characters required from t
    formedCount → number of required characters currently satisfied
    */

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        HashMap<Character, Integer> required = new HashMap<>();
        HashMap<Character, Integer> ourMap = new HashMap<>();

        for(int i = 0; i < t.length(); i++) {
            char current = t.charAt(i); // Get each required character from t.

            required.put(current, required.getOrDefault(current, 0) + 1); // Store required frequency.
        }
        // required = { A → 1, B → 1, C → 1 }

        int requiredCount = required.size(); // Store number of distinct characters required.
        int formedCount = 0; // Track how many required characters currently satisfy their frequency.

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
                formedCount++; // Required frequency is completely satisfied for this character.
            }

            /* Step 2: if result is found update ans.
                       and before moving to next r.
                       lets try to shrink and minimize this window as much as possible for better result at r.
                       Else capture current window. */

            /* Way to enter this loop if formedCount == requiredCount
               Way to get from Loop -> reduce formedCount |
               Means make window invalid and Go on mission to find better window.
            */
            while(formedCount == requiredCount) {

                int windowLength = r - l + 1; // Calculate current valid window length.
                if(windowLength < ans) {
                    ans = windowLength; // Store the smallest valid window length.
                    ansL = l; // Store starting index of the best window.
                    ansR = r; // Store ending index of the best window.
                }

                char left = s.charAt(l); // Get the character leaving the window.

                ourMap.put(left, ourMap.get(left) - 1); // Decrease frequency of the left character.

                /*
                formedCount means: How many requirements are satisfied.

                Example: t = "AABC"
                Required frequencies: { A → 2, B → 1, C → 1 }
                requiredCount         = 3 ( because there are 3 different required characters: A, B, and C. )

                Suppose our current window is "AABBC":
                A → 2  ✓ required frequency reached
                B → 2  ✓ required frequency reached  ( B can go beyond its target frequency. That is completely allowed.
                                                       The important thing is that formedCount does not count the number of occurrences. It counts how many requirements are satisfied.
                                                       It only increases when first time Requirement matches for any char.
                                                       This condition helps -
                                                       Using - if( ourMap.get(current).intValue() == required.get(current).intValue()) {
                                                                        formedCount++; -> If
                                                                  }
                C → 1  ✓ required frequency reached

                Therefore:
                formedCount = 3   | requiredCount = 3 |  The window is VALID.
                                                         Update ansL, ansR.

                Now suppose L is pointing to the first A on "AABBC", and we remove that A.

                Before removal:
                ourMap A = 2
                required A = 2

                After removal:
                ourMap A = 1
                required A = 2

                Now:
                1 < 2

                This means A no longer has its required frequency.
                Therefore, one required character is no longer satisfied.

                So we decrease:
                formedCount--

                formedCount changes from 3 to 2.    || we already stored ansL, andR so no need to care formedCount after finding ans.
                                                    ||

                Now:
                formedCount = 2
                requiredCount = 3

                Because 2 != 3, the window is no longer valid.

                The condition below checks exactly this:
                "After removing left, did a required character fall below its required frequency?"
                */
                if(required.containsKey(left) &&
                        ourMap.get(left) < required.get(left)) {
                    formedCount--;
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

s = "ADOBECODEBANC"
t = "ABC"
===================

Required Map:
{ A → 1, B → 1, C → 1 }

requiredCount = 3
formedCount = 0
l = 0

a. r = 0 → "A"       => Map: { A → 1 }

    A requirement satisfied
    formedCount = 1
    formedCount != requiredCount
    Invalid → Continue R


b. r = 1 → "AD"      => Map: { A → 1, D → 1 }

    formedCount = 1
    Invalid → Continue R


c. r = 2 → "ADO"     => Map: { A → 1, D → 1, O → 1 }

    formedCount = 1
    Invalid → Continue R


d. r = 3 → "ADOB"    => Map: { A → 1, D → 1, O → 1, B → 1 }

    B requirement satisfied
    formedCount = 2
    Invalid → Continue R


e. r = 4 → "ADOBE"   => Map: { A → 1, D → 1, O → 1, B → 1, E → 1 }

    formedCount = 2
    Invalid → Continue R


f. r = 5 → "ADOBEC"   => Map: { A → 1, D → 1, O → 1, B → 1, E → 1, C → 1 }

    C requirement satisfied
    formedCount = 3
    formedCount == requiredCount
    Valid → Enter while


    while : Window = "ADOBEC"
             windowLength = 6
             ans = "ADOBEC"

             Remove s[l] = A
             Map: { A → 0, D → 1, O → 1, B → 1, E → 1, C → 1 }
             formedCount = 2
             l = 1

             Window became invalid → Exit while


g. r = 6 → "DOBECO"    => Map: { D → 1, O → 2, B → 1, E → 1, C → 1 }

    formedCount = 2
    Invalid → Continue R


h. r = 7 → "DOBECOD"   => Map: { D → 2, O → 2, B → 1, E → 1, C → 1 }

    formedCount = 2
    Invalid → Continue R


i. r = 8 → "DOBECODE"  => Map: { D → 2, O → 2, B → 1, E → 2, C → 1 }

    formedCount = 2
    Invalid → Continue R


j. r = 9 → "DOBECODEB" => Map: { D → 2, O → 2, B → 2, E → 2, C → 1 }

    formedCount = 2
    Invalid → Continue R


k. r = 10 → "DOBECODEBA" => Map: { D → 2, O → 2, B → 2, E → 2, C → 1, A → 1 }

    A requirement satisfied
    formedCount = 3
    Valid → Enter while


    while : Remove s[l] = D
             Map: { D → 1, O → 2, B → 2, E → 2, C → 1, A → 1 }
             l = 2
             Window remains valid


    while : Remove s[l] = O
             Map: { D → 1, O → 1, B → 2, E → 2, C → 1, A → 1 }
             l = 3
             Window remains valid


    while : Remove s[l] = B
             Map: { D → 1, O → 1, B → 1, E → 2, C → 1, A → 1 }
             l = 4
             Window remains valid


    while : Remove s[l] = E
             Map: { D → 1, O → 1, B → 1, E → 1, C → 1, A → 1 }
             l = 5
             Window remains valid


    while : Remove s[l] = C
             Map: { D → 1, O → 1, B → 1, E → 1, C → 0, A → 1 }
             formedCount = 2
             l = 6
             Window became invalid → Exit while


l. r = 11 → "ODEBANC" => Map contains A, B, C with required frequencies

    A requirement satisfied
    formedCount = 3
    Valid → Enter while

    Shrink L until minimum valid window becomes:

    "BANC"

    Map for "BANC":
    B → 1
    A → 1
    N → 1
    C → 1

    windowLength = 4
    ans = "BANC"


Final Answer = "BANC"
Final Length = 4

Important Pattern :

R expands → until all required characters are satisfied.
formedCount == requiredCount → Valid Window.
Valid → update minimum answer → move L inside while.
L keeps moving → until removing a character makes the window invalid.
Then R continues expanding again.
*/