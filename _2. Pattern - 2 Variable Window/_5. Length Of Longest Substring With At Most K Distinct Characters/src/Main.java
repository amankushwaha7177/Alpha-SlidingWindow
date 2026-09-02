public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

/*
Important : This is the 3A NO-WHILE pattern.

            R → ENTERS one character.

            map.size() > K
            ↓
            Window becomes INVALID.

            L → moves only ONE position.

            After L moves:
            Window may STILL be INVALID.

            We do NOT use while.

            R → continues moving.

            This keeps the window size from continuously
            increasing while we search for the maximum length.

            Pattern 2 → HeavyWhile

                       R → ENTERS
                       Window → INVALID
                       L → L → L → ...
                       Window → VALID
                       Then update answer


            Pattern 3A → NoWhile

                        R → ENTERS
                        Window → INVALID
                        L → ONE step
                        Window may remain INVALID
                        R → continues


            Pattern 3B → DirectJump

                        R → ENTERS
                        Window → INVALID
                        L → jumps directly
                        using stored character/index information.
 */