import java.util.HashMap;

public class _1A_BruteForce_O_n2 {
    /*
    Interview :
    Given a string and an integer k, find the longest substring where
    we can replace at most k characters to make the entire substring
    contain only one repeating character.

    Brain :
    The simplest mental translation is to generate every possible substring
    and check how many characters must be replaced to make that substring uniform.

    Example :
    str = "AABABBA"
    k = 1

    Substring = "AABA"
    Maximum frequency = 3 because A appears three times.
    Window length = 4.
    Characters to replace = 4 - 3 = 1.
    Since replacements <= k, this substring is valid.

    Main Logic :
    Generate every substring using i and j, track character frequencies,
    calculate replacements using windowLength - maximumFrequency, and update answer.

    Idea :
    A. Normal :
       Use two loops to generate every possible substring and a HashMap
       to maintain the frequency of every character inside the current substring.

       For every new character, update its frequency and maximum frequency.
       Then calculate how many characters need replacement using:

       replacements = windowLength - maximumFrequency

       If replacements <= k, the current substring is valid and we update ans.

       T = O(n²)
       S = O(n)

    */

    public static void main(String[] args) {
        String str = "AABABBA";
        int k = 1;

        int ans = 0;
        int n = str.length();

        for(int i = 0; i < n; i++) {
            HashMap<Character, Integer> map = new HashMap<>();
            int maxFrequency = 0;

            for(int j = i; j < n; j++) {
                char current = str.charAt(j);

                map.put(current, map.getOrDefault(current, 0) + 1);

                maxFrequency = Math.max(maxFrequency, map.get(current));

                int windowLength = j - i + 1;
                int replacements = windowLength - maxFrequency;

                if(replacements <= k) {
                    ans = Math.max(ans, windowLength);
                }
            }
        }

        System.out.println("Answer = " + ans);

        /*
        Time = O(n²)
        Reason : The outer loop chooses every starting position, while the inner
                 loop extends the substring through every possible ending position.

        Space = O(n)
        Reason : The HashMap stores character frequencies for the current substring,
                 and its size can grow with the number of distinct input characters.
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

a. j = 0 → "A"  =>      Map: { A → 1 }

    maxFrequency = {0,1} = 1
    windowLength = 1
    replacements = 1 - 1 = 0
    Valid → ans = 1


b. j = 1 → "AA" =>     Map: { A → 2 }

    maxFrequency = {1,2} = 2
    windowLength = 2
    replacements = 2 - 2 = 0
    Valid → ans = 2


j = 2 → "AAB"  =>      Map: { A → 2, B → 1 }

    maxFrequency = {2, 1} = 2
    windowLength = 3
    replacements = 3 - 2 = 1
    Valid → ans = 3


j = 3 → "AABA"  =>     Map: { A → 3, B → 1 }

    maxFrequency = {2, 3} = 3
    windowLength = 4
    replacements = 4 - 3 = 1
    Valid → ans = 4


j = 4 → "AABAB" =>    Map: { A → 3, B → 2 }

    maxFrequency = {3, 2} = 3
    windowLength = 5
    replacements = 5 - 3 = 2
    Invalid → No ans change still 4


j = 5 → "AABABB" =>  Map: {A → 3, B → 3 }

    maxFrequency = {3, 3} = 3
    windowLength = 6
    replacements = 6 - 3 = 3
    Invalid → ans remains 4


j = 6 → "AABABBA" => Map: {A → 4, B → 3}

    maxFrequency = {3, 4} = 4
    windowLength = 7
    replacements = 7 - 4 = 3
    Invalid → ans remains 4


Final Answer = 4

The longest valid substring is "AABA" because A appears three times and
only one B needs replacement, which is exactly equal to the allowed k.


i = 0 → A, AA, AAB, AABA, AABAB, AABABB, AABABBA
i = 1 → A, AB, ABA, ABAB, ABABB, ABABBA
i = 2 → B, BA, BAB, BABB, BABBA
...
*/