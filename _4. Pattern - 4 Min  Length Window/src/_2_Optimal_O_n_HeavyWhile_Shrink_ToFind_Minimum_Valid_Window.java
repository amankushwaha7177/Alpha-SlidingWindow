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
        HashMap<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < t.length(); i++) {
            char current = t.charAt(i); // Get each required character from t.

            required.put(current, required.getOrDefault(current, 0) + 1); // Store required frequency.
        }

        int requiredCount = required.size(); // Store number of distinct characters required.
        int formedCount = 0; // Track how many required characters currently satisfy their frequency.

        int l = 0;
        int ans = Integer.MAX_VALUE;
        int ansL = 0;
        int ansR = 0;

        for(int r = 0; r < s.length(); r++) {
            char current = s.charAt(r); // Get the character entering the window.

            map.put(current, map.getOrDefault(current, 0) + 1); // Increase current character frequency.

            if(required.containsKey(current) &&
                    map.get(current).intValue() == required.get(current).intValue()) {
                formedCount++; // Required frequency is completely satisfied for this character.
            }

            while(formedCount == requiredCount) {
                int windowLength = r - l + 1; // Calculate current valid window length.

                if(windowLength < ans) {
                    ans = windowLength; // Store the smallest valid window length.
                    ansL = l; // Store starting index of the best window.
                    ansR = r; // Store ending index of the best window.
                }

                char left = s.charAt(l); // Get the character leaving the window.

                map.put(left, map.get(left) - 1); // Decrease frequency of the left character.

                if(required.containsKey(left) &&
                        map.get(left) < required.get(left)) {
                    formedCount--; // Window became invalid because required frequency is missing.
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