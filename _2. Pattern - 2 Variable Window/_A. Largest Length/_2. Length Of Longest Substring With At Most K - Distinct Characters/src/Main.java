class Main {
    public static void main(String[] args) {

        /*
        1. Brute Force

        Time = O(n²)
        Reason : The outer loop chooses every starting position, while the inner
                 loop checks every possible ending position for that starting point.

        Space = O(n)
        Reason : The HashMap stores character frequencies for the current substring,
                 and in the worst case the substring can contain n different characters.
        */

        System.out.println("1. Brute Force");
        System.out.println("Time = O(n²), Space = O(n)");


        /*
        2. Optimal - Heavy While

        Time = O(2n) = O(n)
        Reason : R moves forward n times, and L also moves forward at most n times
                 overall, even though L moves repeatedly inside the while loop.

        Space = O(n)
        Reason : The HashMap stores character frequencies for the current window,
                 and the window can contain up to n different characters.
        */

        System.out.println("2. Optimal - Heavy While");
        System.out.println("Time = O(2n) = O(n), Space = O(n)");


        /*
        3A. More Optimal - No While

        Time = O(n)
        Reason : R moves forward exactly once through the string, while L moves
                 at most one position for each R movement using only an if condition.

        Space = O(n)
        Reason : The HashMap stores character frequencies for the current window,
                 and it can contain up to n different characters in the worst case.
        */

        System.out.println("3A. More Optimal - No While");
        System.out.println("Time = O(n), Space = O(n)");


        /*
        3B. More Optimal - Direct Jump Using Index By Map

        Time = O(n)
        Reason : R moves through the string once, while L directly jumps forward
                 using the stored character index instead of moving one by one.

        Space = O(n)
        Reason : The HashMap stores each character with its latest index, and in
                 the worst case it can contain n different characters.
        */

        System.out.println("3B. More Optimal - Direct Jump");
        System.out.println("Time = O(n), Space = O(n)");
    }
}