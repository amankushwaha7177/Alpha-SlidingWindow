public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
/*
Interview : Given two strings s and t, find the minimum length substring of s
            that contains every character of t with the required frequency.

Given : We need to find the smallest substring's length possible where the
        substring contains all characters of t with their required frequency.

        Contiguous + Size is not Fixed Size For Window + SubString = Variable Window
        subString = window

Brain : The simplest mental translation is to expand the window using R until
        the current window contains all required characters from t, then shrink
        the window using L as much as possible while keeping it valid.

        Example :
        s = "ADOBECODEBANC"
        t = "ABC"

        Consider the window = "ADOBEC"

        Map of window:
        A → 1
        D → 1
        O → 1
        B → 1
        E → 1
        C → 1

        Required characters:
        A → 1
        B → 1
        C → 1

        All required characters are present, so the window is VALID.

        Now move L forward and try to make the window smaller while
        still containing A, B, and C.

        Eventually:

        "BANC"

        contains:
        A → 1
        B → 1
        C → 1

        Therefore, "BANC" is the minimum valid window.


Main Logic :

R → expand window until all required characters are present.
L → shrink window while the current window remains valid.
When valid → update the minimum answer.
When invalid → stop shrinking and continue moving R.
*/