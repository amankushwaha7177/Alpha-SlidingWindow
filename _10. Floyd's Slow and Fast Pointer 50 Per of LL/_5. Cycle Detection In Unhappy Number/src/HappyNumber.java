public class HappyNumber {
    public static void main(String[] args) {
        int n = 19;

        System.out.println("Is Happy = " + isHappy(n));

        /*
        Time = O(log n)
        Reason : Each transformation processes the digits of the current number,
                 and the number quickly becomes small before entering a cycle.

        Space = O(1)
        Reason : Only slow and fast variables are used without storing visited numbers.
        */
    }

    static boolean isHappy(int n) {
        int slow = n;
        int fast = n;

        while(true) {
            slow = sumOfSquares(slow);

            fast = sumOfSquares(fast);
            fast = sumOfSquares(fast);

            if(slow == 1 || fast == 1) { // Either of pointers reaches 1 : Means n is happy
                return true;
            }

            if(slow == fast) {         // if slow == fast But they are not 1 it means it have Loop
                return false;          // So Unhappy Number.
            }
        }
    }

    static int sumOfSquares(int n) {
        int sum = 0;

        while(n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n = n / 10;
        }

        return sum;
    }
}

/*
Dry Run : n = 19

Initial:
slow = 19
fast = 19

Each transformation calculates:
sum of squares of digits.

Step 1:
slow = sumOfSquares(19)
     = 1² + 9²
     = 1 + 81
     = 82

fast = sumOfSquares(19)
     = 82

fast = sumOfSquares(82)
     = 8² + 2²
     = 64 + 4
     = 68

Now:
slow = 82
fast = 68


Step 2:
slow = sumOfSquares(82)
     = 68

fast = sumOfSquares(68)
     = 6² + 8²
     = 36 + 64
     = 100

fast = sumOfSquares(100)
     = 1² + 0² + 0²
     = 1

Now:
slow = 68
fast = 1

fast == 1
→ Happy Number
→ return true


Movement:

19 → 82 → 68 → 100 → 1
      slow
              fast

Final Answer:
19 is a Happy Number.
*/



/*
Dry Run : n = 2

Initial:
slow = 2
fast = 2

Step 1:
slow = sumOfSquares(2)
     = 2²
     = 4

fast = sumOfSquares(2)
     = 4

fast = sumOfSquares(4)
     = 4²
     = 16

Now:
slow = 4
fast = 16


Step 2:
slow = sumOfSquares(4)
     = 16

fast = sumOfSquares(16)
     = 1² + 6²
     = 1 + 36
     = 37

fast = sumOfSquares(37)
     = 3² + 7²
     = 9 + 49
     = 58

Now:
slow = 16
fast = 58


Step 3:
slow = sumOfSquares(16)
     = 37

fast = sumOfSquares(58)
     = 5² + 8²
     = 25 + 64
     = 89

fast = sumOfSquares(89)
     = 8² + 9²
     = 64 + 81
     = 145

Now:
slow = 37
fast = 145


Step 4:
slow = sumOfSquares(37)
     = 3² + 7²
     = 9 + 49
     = 58

fast = sumOfSquares(145)
     = 1² + 4² + 5²
     = 1 + 16 + 25
     = 42

fast = sumOfSquares(42)
     = 4² + 2²
     = 16 + 4
     = 20

Now:
slow = 58
fast = 20


Step 5:
slow = sumOfSquares(58)
     = 5² + 8²
     = 25 + 64
     = 89

fast = sumOfSquares(20)
     = 2² + 0²
     = 4

fast = sumOfSquares(4)
     = 4²
     = 16

Now:
slow = 89
fast = 16


Step 6:
slow = sumOfSquares(89)
     = 8² + 9²
     = 64 + 81
     = 145

fast = sumOfSquares(16)
     = 1² + 6²
     = 1 + 36
     = 37

fast = sumOfSquares(37)
     = 3² + 7²
     = 9 + 49
     = 58

Now:
slow = 145
fast = 58


Step 7:
slow = sumOfSquares(145)
     = 1² + 4² + 5²
     = 1 + 16 + 25
     = 42

fast = sumOfSquares(58)
     = 5² + 8²
     = 25 + 64
     = 89

fast = sumOfSquares(89)
     = 8² + 9²
     = 64 + 81
     = 145

Now:
slow = 42
fast = 145


Step 8:
slow = sumOfSquares(42)
     = 4² + 2²
     = 16 + 4
     = 20

fast = sumOfSquares(145)
     = 1² + 4² + 5²
     = 1 + 16 + 25
     = 42

fast = sumOfSquares(42)
     = 4² + 2²
     = 16 + 4
     = 20

Now:
slow = 20
fast = 20

slow == fast
→ Cycle Found
→ Not Happy


Cycle:

4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4

Therefore:
2 is NOT a Happy Number.
*/