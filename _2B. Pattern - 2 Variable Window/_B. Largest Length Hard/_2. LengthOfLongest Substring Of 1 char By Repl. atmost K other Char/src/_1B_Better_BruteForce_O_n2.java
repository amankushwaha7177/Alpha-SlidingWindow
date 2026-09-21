import java.util.HashMap;

public class _1B_Better_BruteForce_O_n2 {
    /*
    Idea :
    A. Better Brute Force :
       Use two loops to generate every substring while continuously maintaining
       the frequency of characters and maximum frequency for the current window.

       replacements = windowLength - maximumFrequency

       replacements <= k → Valid Window
       replacements > k  → Invalid Window

       Once replacements > k for a fixed i, we break because extending j further
       can only increase or maintain the required replacements, so it cannot become valid again.

       T = O(n²)
       S = O(n)
    */

    public static void main(String[] args) {
        String str = "AABABBA";
        int k = 1;

        int ans = 0;
        int n = str.length();

        for(int i = 0; i < n; i++) {
            HashMap<Character, Integer> map = new HashMap<>(); // Stores frequency of characters in current window.
            int maxFrequency = 0; // Stores the highest frequency found in the current window.

            for(int j = i; j < n; j++) {
                char current = str.charAt(j); // Get the character added by j.

                map.put(current, map.getOrDefault(current, 0) + 1); // Increase frequency of current character.

                maxFrequency = Math.max(maxFrequency, map.get(current)); // Update maximum character frequency.

                int windowLength = j - i + 1; // Calculate current window length.
                int replacements = windowLength - maxFrequency; // Calculate characters that need replacement.

                if(replacements > k) {
                    break; // Further expansion with same i cannot become valid again.
                }

                ans = Math.max(ans, windowLength); // Update answer for the valid window.
            }
        }

        System.out.println("Answer = " + ans);

        /*
        Time = O(n²)
        Reason : The two loops can still examine every starting and ending position.

        Space = O(n)
        Reason : The HashMap stores character frequencies for the current substring.
        */

        System.out.println("Time = O(n²), Space = O(n)");
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
    Invalid → 2 > k(1)
    Break → Further j values cannot make this window valid again.


i = 1
maxFrequency = 0

a. j = 1 → "A"       => Map: { A → 1 }

    maxFrequency = {0, 1} = 1
    windowLength = 1
    replacements = 1 - 1 = 0
    Valid → ans = 4


b. j = 2 → "AB"      => Map: { A → 1, B → 1 }

    maxFrequency = {1, 1} = 1
    windowLength = 2
    replacements = 2 - 1 = 1
    Valid → ans = 4


c. j = 3 → "ABA"     => Map: { A → 2, B → 1 }

    maxFrequency = {1, 2} = 2
    windowLength = 3
    replacements = 3 - 2 = 1
    Valid → ans = 4


d. j = 4 → "ABAB"    => Map: { A → 2, B → 2 }

    maxFrequency = {2, 2} = 2
    windowLength = 4
    replacements = 4 - 2 = 2
    Invalid → 2 > k(1)
    Break


The same process continues for i = 2, i = 3, and so on.

Final Answer = 4

The important improvement over normal brute force is that we maintain the
frequency map and maximum frequency while expanding instead of recalculating them.
We also break immediately when the current starting position cannot produce
any further valid substring by extending j.
*/