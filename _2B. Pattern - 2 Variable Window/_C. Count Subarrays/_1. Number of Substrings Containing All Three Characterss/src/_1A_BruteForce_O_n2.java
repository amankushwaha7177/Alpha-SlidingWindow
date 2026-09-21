import java.util.HashMap;

class _1A_BruteForce_O_n2 {
    /*
    Interview : There is a string which contains only a,b and c.
                Find all total number of substring which contain at least one a,
                                                                 at least one b,
                                                                 at least one c.

    Given : We need to count every substring that contains all three characters.
            The substring must be contiguous, so we cannot skip any characters.

            Contiguous + Size is Not Fixed + Contains a,b,c + SubString = Variable Window
            subString = window


    Brain : The simplest mental translation :

            Question says:
            Count the number of substrings containing at least one a, one b, and one c.

            Sliding-window language:
            Find every variable-size window that contains all three required characters.

            Ex : str = "abcabc"

                 [ [a, b, c], a, b, c ]     → contains a,b,c ✓
                 [ [a, b, c, a], b, c ]     → contains a,b,c ✓
                 [ [a, b, c, a, b], c ]     → contains a,b,c ✓
                 [ [a, b, c, a, b, c] ]     → contains a,b,c ✓

            Once a window contains all three characters, extending the window
            toward the right will never remove any of those three characters.

            Therefore, we can count multiple valid substrings together instead
            of checking every possible ending position separately.

    Bruteforce Idea :
        Generate every possible substring using two loops and check whether
        the current substring contains at least one a, one b, and one c.
        T = O(n²)
    */

    public static void main(String[] args) {

        String str = "abcabc";
        int ans = 0;


        for(int i = 0; i < str.length(); i++) {

            HashMap<Character, Integer> map = new HashMap<>();

            for(int j = i; j < str.length(); j++) {

                char current = str.charAt(j);

                map.put(current, 888);

                /*
                Once the substring contains all three characters, every longer
                substring starting from the same i will also contain all three.
                */

                if(map.size() == 3) {
                    ans++;
                }
            }
        }

        System.out.println("Answer = " + ans);

        /*
        Time = O(n²)
        Reason : The outer loop chooses every starting position, while the inner
                 loop extends the substring through every possible ending position.

        Space = O(1)
        Reason : The HashMap can contain only three possible characters, namely
                 a, b, and c, so its size never grows with the input length.
        */

        System.out.println("Time = O(n²), Space = O(1)");
    }
}

/*
Dry Run : str = "abcabc"

i = 0 "abcabc"
-----
j = 0 → "a"     → map size is not 3❌
j = 1 → "ab"    → map size is not 3❌
j = 2 → "abc"   → ✓ ans = 1
j = 3 → "abca"  → ✓ ans = 2
j = 4 → "abcab" → ✓ ans = 3
j = 5 → "abcabc"→ ✓ ans = 4

i = 1 "bcabc"
-----
"b" →  map size is not 3❌
"bc" → map size is not 3❌
"bca" → ✓ ans = 5
"bcab" → ✓ ans = 6
"bcabc" → ✓ ans = 7

i = 2 "cabc"
------
"c" → ❌
"ca" → ❌
"cab" → ✓ ans = 8
"cabc" → ✓ ans = 9

i = 3 "abc"
-----
"a" → ❌
"ab" → ❌
"abc" → ✓ ans = 10

i = 4  "bc"
-----
"b" → ❌
"bc" → ❌

i = 5  "c"
------
"c" → ❌

Final Answer = 10
*/