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
        String s = "AAOBBC";
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

            /* Step 1 : If this char at r exits in target -> we found match
                        => so form this.
              But Note :The == 1 is important because we only count a first character
              when it enters the window for the first time.
              Else every new character will increase formedCount in each increase.*/
            if(required.containsKey(current) &&
                    ourMap.get(current).intValue() == 1) {
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


                /* if removed element is not in t. --> No worry we dont care
                   But if rmeoved element is in t + its frequency is becoming 2 from 3 -> we dont care
                                                                              1 from 1 -> we dont care
                   But if its frequency becoming 0 from 1
                   Than we need to down formedCount. becasue we need atleast 1 char. Simple !
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
s = "AAOBBC"
t = "ABC"

Required Map:
{ A → 1, B → 1, C → 1 }

requiredCount = 3
formedCount = 0
l = 0

a. r = 0 → "A"

    Add A
    ourMap = { A → 1 }

    A requirement satisfied
    formedCount = 1

    formedCount != 3
    Invalid → Continue R


b. r = 1 → "AA"

    Add A
    ourMap = { A → 2 }

    A was already satisfied
    formedCount = 1

    formedCount != 3
    Invalid → Continue R


c. r = 2 → "AAO"

    Add O
    ourMap = { A → 2, O → 1 }

    O is not required
    formedCount = 1

    formedCount != 3
    Invalid → Continue R


d. r = 3 → "AAOB"

    Add B
    ourMap = { A → 2, O → 1, B → 1 }

    B requirement satisfied
    formedCount = 2

    formedCount != 3
    Invalid → Continue R


e. r = 4 → "AAOBB"

    Add B
    ourMap = { A → 2, O → 1, B → 2 }

    B was already satisfied
    formedCount = 2

    formedCount != 3
    Invalid → Continue R


f. r = 5 → "AAOBBC"

    Add C
    ourMap = { A → 2, O → 1, B → 2, C → 1 }

    C requirement satisfied
    formedCount = 3

    formedCount == requiredCount
    Valid → Enter while


    while #1: cause formedCount == requiredCount
    --------

    Window = "AAOBBC"  -->  Length = 6   |  Update answer:   ans = "AAOBBC"

    Remove A from left: "AOBBC"
    ourMap = { A → 1, O → 1, B → 2, C → 1 }

    formedCount = 3
    A is still present, so window remains valid.


    while #2: cause formedCount == requiredCount
    =========

    Window = "AOBBC"   --> Length = 5   |  Update answer:   ans = "AOBBC"

    Remove A from left: "OBBC"
    ourMap = { A → 0, O → 1, B → 2, C → 1 }

    A is now completely absent. A was required and:
    ourMap[A] < required[A]

    Therefore: formedCount = 2

    Window became invalid → Exit while.


    Final Answer = "AOBBC"
    Final Length = 5
*/