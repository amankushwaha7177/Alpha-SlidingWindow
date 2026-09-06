public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

/*
Important Sliding Window Rule :
 ----------------------------
        Sliding Window is directly applicable to "At Most K" conditions because
        we can expand R and shrink L whenever the window becomes invalid.

        Example:

        At Most K
            ↓
        condition <= K
            ↓
        Expand R
            ↓
        If condition > K
            ↓
        Move L until condition <= K again
            ↓
        Count valid windows

        But "Exactly K" is not directly handled by this basic at-most window pattern
        because we need to count only windows having exactly K, while valid windows
        can exist both below K and equal to K.

        Therefore: Exactly K = AtMost(K) - AtMost(K - 1)

        This removes all windows having fewer than K and leaves only windows having
        exactly K.

        Remember:

        At Most K  → Direct Sliding Window
        Exactly K  → AtMost(K) - AtMost(K - 1)
*/