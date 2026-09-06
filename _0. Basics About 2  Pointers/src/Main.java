/*
============================================================
File: _DSA_Interview_Coding_Process.java
Topic: General DSA Interview Coding Strategy

This process can be used for ANY DSA question.

Do not start coding immediately.

First understand the problem, identify the approach,
then build the code step by step.

============================================================
*/


public class Main {

    public static void main(String[] args) {


        /*
        ========================================================
        1️⃣ UNDERSTAND THE QUESTION
        ========================================================

        Before writing code, identify:

        1. What is the INPUT?
        2. What is the OUTPUT?
        3. What exactly is being asked?
        4. What are the CONSTRAINTS?
        5. Are there any special conditions?

        Example:

        "Find the maximum sum of a subarray of size K."

        Input:
        int[] arr
        int k

        Output:
        Maximum sum

        Important word:

        "subarray"
        → contiguous elements

        "size K"
        → exactly K elements


        DO NOT start coding yet.
        First understand what the problem is asking.
        */


        /*
        ========================================================
        2️⃣ TAKE A SMALL EXAMPLE
        ========================================================

        Use a small example to understand the problem.

        Example:

        arr = [2, 1, 5, 1, 3, 2]
        k = 3

        Manually identify the possible windows:

        [2, 1, 5] → 8
        [1, 5, 1] → 7
        [5, 1, 3] → 9
        [1, 3, 2] → 6

        Answer = 9


        The example helps you understand
        what your code needs to calculate.
        */


        /*
        ========================================================
        3️⃣ THINK ABOUT BRUTE FORCE
        ========================================================

        Ask:

        "What is the simplest way to solve this?"

        For the above problem:

        Generate every subarray of size K
        and calculate its sum.

        This may take:

        Time = O(N * K)

        We don't necessarily need to code the brute-force
        solution, but understanding it helps us find
        the optimization.

        During interview, explain briefly:

        "The brute-force solution would calculate
        the sum of every K-sized subarray, which
        takes O(N * K)."
        */


        /*
        ========================================================
        4️⃣ IDENTIFY THE PATTERN / DATA STRUCTURE
        ========================================================

        Now ask:

        "Can I make this faster?"

        Look for common patterns:

        HashMap
        HashSet
        Two Pointers
        Sliding Window
        Binary Search
        Stack
        Queue
        Heap
        Recursion
        Backtracking
        Tree
        Graph
        Dynamic Programming
        Prefix Sum
        Greedy
        etc.


        For our example:

        "Subarray + fixed size K"

        → Fixed-Size Sliding Window


        Pattern recognition is one of the most
        important DSA interview skills.
        */


        /*
        ========================================================
        5️⃣ EXPLAIN THE OPTIMIZED APPROACH BEFORE CODING
        ========================================================

        Do not silently start coding.

        Explain the idea to the interviewer.

        Example:

        "Instead of calculating the entire sum again
        for every window, I will maintain the current
        window sum.

        When the window moves:

        1. Remove the element leaving from the left.
        2. Add the new element entering from the right.
        3. Update the maximum."

        This shows that you understand the algorithm
        before implementing it.
        */


        /*
        ========================================================
        6️⃣ DECLARE VARIABLES
        ========================================================

        Now decide what information the algorithm needs.

        Example:

        int left = 0;
        int right = k - 1;

        int windowSum = 0;
        int finalMax = 0;


        Each variable should have a clear responsibility.

        left
        → beginning of window

        right
        → end of window

        windowSum
        → current window sum

        finalMax
        → best answer found so far
        */


        /*
        ========================================================
        7️⃣ CREATE THE BASIC CODE SKELETON
        ========================================================

        Before filling every condition,
        create the basic structure.

        Example:

        int left = 0;
        int right = k - 1;

        // Create first window


        while (...) {

            // Main logic

        }


        // Return answer


        This makes the overall algorithm structure
        visible before you fill in the details.
        */


        /*
        ========================================================
        8️⃣ FILL THE LOGIC STEP BY STEP
        ========================================================

        Now implement the algorithm.

        Do not try to write the entire solution
        in one shot.

        Build it logically:

        Initialize
             ↓
        Process
             ↓
        Check condition
             ↓
        Update answer
             ↓
        Move pointers / variables


        For example:

        windowSum -= arr[left];

        left++;

        right++;

        windowSum += arr[right];

        finalMax = Math.max(
            finalMax,
            windowSum
        );


        Each line should have a clear reason.
        */


        /*
        ========================================================
        9️⃣ DRY RUN THE CODE
        ========================================================

        After coding, take the example
        and execute your code manually.

        Example:

        arr = [2, 1, 5, 1, 3, 2]
        k = 3


        First window:

        [ [2, 1, 5], 1, 3, 2]

        sum = 8


        Next window:

        [ 2, [1, 5, 1], 3, 2]

        Remove 2:

        8 - 2 = 6

        Add 1:

        6 + 1 = 7


        Next window:

        [ 2, 1, [5, 1, 3], 2]

        Remove 1:

        7 - 1 = 6

        Add 3:

        6 + 3 = 9


        Maximum = 9


        Dry running catches many pointer,
        condition, and off-by-one errors.
        */


        /*
        ========================================================
        🔟 CHECK EDGE CASES
        ========================================================

        Before finishing, ask:

        What happens if:

        1. Array is empty?
        2. Array has one element?
        3. k = 1?
        4. k = array length?
        5. k is larger than array length?
        6. There are duplicate values?
        7. There are negative values?
        8. There is no valid answer?


        You don't need to test every possible case.

        Think about the cases that can break
        your particular algorithm.
        */


        /*
        ========================================================
        1️⃣1️⃣ CHECK TIME AND SPACE COMPLEXITY
        ========================================================

        Always be ready to explain:

        Time Complexity
        Space Complexity


        Example:

        Fixed Sliding Window:

        Time = O(N)

        Space = O(1)


        Why?

        left and right each move across
        the array at most once.
        */


        /*
        ========================================================
        1️⃣2️⃣ FINAL INTERVIEW EXPLANATION
        ========================================================

        A good interview explanation can follow this format:

        "The brute-force approach would be ________,
        which takes O(__).

        We can optimize this using ________.

        I will maintain ________.

        When ________ happens, I will ________.

        This reduces the complexity to O(__) time
        and O(__) space."


        This keeps your explanation structured
        and easy for the interviewer to follow.
        */


        /*
        ========================================================
        ⭐ COMPLETE INTERVIEW PROCESS
        ========================================================

        STEP 1
        Understand the question
              ↓
        STEP 2
        Take a small example
              ↓
        STEP 3
        Think about brute force
              ↓
        STEP 4
        Identify pattern / data structure
              ↓
        STEP 5
        Explain optimized approach
              ↓
        STEP 6
        Declare variables
              ↓
        STEP 7
        Write code skeleton
              ↓
        STEP 8
        Fill the logic
              ↓
        STEP 9
        Dry run
              ↓
        STEP 10
        Check edge cases
              ↓
        STEP 11
        Time + Space Complexity
              ↓
        STEP 12
        Final explanation


        ========================================================
        IMPORTANT RULE
        ========================================================

        DO NOT memorize:

        "Always write the loop first."

        Instead remember:

        Understand the algorithm first,
        then write the code structure
        that represents the algorithm.


        The exact order of code depends on the problem.

        For some problems you start with:

        for loop

        For others:

        while loop

        For others:

        recursion

        For others:

        DFS / BFS

        For others:

        binary search

        The interview process remains the same.
        ========================================================
        */


        /*
        ========================================================
        ⭐ QUICK VERSION TO MEMORIZE BEFORE INTERVIEW
        ========================================================

        Understand
             ↓
        Example
             ↓
        Brute Force
             ↓
        Optimize
             ↓
        Explain
             ↓
        Variables
             ↓
        Code Skeleton
             ↓
        Logic
             ↓
        Dry Run
             ↓
        Edge Cases
             ↓
        Complexity


        ONE-LINE MEMORY:

        "Understand → Think → Explain → Code → Test."
        ========================================================
        */
    }
}