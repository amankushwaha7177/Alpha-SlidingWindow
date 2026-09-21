public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
/*
Happy Number
→ Keep replacing number with sum of squares of its digits
→ If it reaches 1 its Happy Number

Ex :
19  → 1² + 9²      → 1 + 81
82  → 8² + 2²      → 64 + 4
68  → 6² + 8²      → 36 + 64
100 → 1² + 0² + 0² → 1
Happy Number


For Unhappy Number
-----------------
2 → 4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4
    ↑_______________________________________|

Algo
-----
→ Keep replacing number with sum of squares of its digits
→ Eventually either reach 1
→ Or enter a cycle

So exactly like Linked List Cycle Detection:
-------------------------------------------
slow → moves one transformation
fast → moves two transformations

slow == fast
→ cycle detected
 */