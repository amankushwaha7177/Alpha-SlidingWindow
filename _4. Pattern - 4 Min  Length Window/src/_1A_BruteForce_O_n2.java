import java.util.HashMap;

public class _1A_BruteForce_O_n2 {
    /*
    Interview : Given two strings s and t, find the minimum length substring of s
                that contains every character of t with the required frequency.

    Given : We need to find the smallest substring's length possible where the
            substring contains all characters of t with their required frequency.

            Contiguous + Size is not Fixed Size For Window + SubString = Variable Window
            subString = window

    Brain : The simplest mental translation is to generate every possible substring
            and check whether the current substring contains all required characters
            from t with their required frequencies.

            Example :
            s = "ADOBECODEBANC"
            t = "ABC"

            Consider the substring = "ADOBEC"

            Required Map:
            A → 1
            B → 1
            C → 1

            Current Map:
            A → 1
            D → 1
            O → 1
            B → 1
            E → 1
            C → 1

            The substring contains all required characters, so it is valid.

            We continue checking smaller valid substrings and finally find:

            "BANC"

            B → 1
            A → 1
            N → 1
            C → 1

            Therefore, minimum window length = 4.

    Idea :
    A. Brute Force :
       Use two loops to generate every possible substring and maintain the
       frequency of every character inside the current substring.

       For every substring, check whether its character frequencies satisfy
       all the required frequencies present in string t.

       If the current substring is valid, update the minimum answer.

       T = O(n²)
       S = O(n)

    */

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        int ans = Integer.MAX_VALUE;
        int n = s.length();

        HashMap<Character, Integer> required = new HashMap<>();

        for(int i = 0; i < t.length(); i++) {
            char current = t.charAt(i); // Get each required character from t.

            required.put(current, required.getOrDefault(current, 0) + 1); // Store required frequency.
        }

        for(int i = 0; i < n; i++) {
            HashMap<Character, Integer> map = new HashMap<>(); // Stores current substring frequencies.

            for(int j = i; j < n; j++) {
                char current = s.charAt(j); // Get the character added to the substring.

                map.put(current, map.getOrDefault(current, 0) + 1); // Increase current character frequency.

                boolean valid = true; // Assume current substring is valid initially.

                for(char ch : required.keySet()) {
                    if(map.getOrDefault(ch, 0) < required.get(ch)) {
                        valid = false; // Required frequency is missing, so substring is invalid.
                        break;
                    }
                }

                if(valid) {
                    ans = Math.min(ans, j - i + 1); // Store the smallest valid substring length.
                }
            }
        }

        System.out.println("Answer = " + ans);

        /*
        Time = O(n²)
        Reason : Two loops generate every substring, while checking required characters
                 adds work proportional to the number of distinct characters in t.

        Space = O(n)
        Reason : The HashMaps store character frequencies for the required and current substring.
        */

        System.out.println("Time = O(n²), Space = O(n)");
    }
}


/*
Dry Run Example :

s = "ADOBECODEBANC"
t = "ABC"
===================

Required Map:
{ A → 1, B → 1, C → 1 }


i = 0

a. j = 0 → "A"       => Map: { A → 1 }

    Required:
    A → 1
    B → 1
    C → 1

    B and C are missing
    Invalid → No ans change


b. j = 1 → "AD"      => Map: { A → 1, D → 1 }

    B and C are missing
    Invalid → No ans change


c. j = 2 → "ADO"     => Map: { A → 1, D → 1, O → 1 }

    B and C are missing
    Invalid → No ans change


d. j = 3 → "ADOB"    => Map: { A → 1, D → 1, O → 1, B → 1 }

    C is missing
    Invalid → No ans change


e. j = 4 → "ADOBE"   => Map: { A → 1, D → 1, O → 1, B → 1, E → 1 }

    C is missing
    Invalid → No ans change


f. j = 5 → "ADOBEC"   => Map: { A → 1, D → 1, O → 1, B → 1, E → 1, C → 1 }

    A → 1 ✓
    B → 1 ✓
    C → 1 ✓

    Valid → ans = 6


As j continues, more substrings are generated and smaller valid
substrings are found until the minimum valid substring becomes:

"BANC"

Length = 4

Final Answer = 4

The minimum valid substring is "BANC" because it contains A, B, and C
with the required frequency, and no smaller substring can satisfy t.
*/