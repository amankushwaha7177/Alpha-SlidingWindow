public class Main {
}
/*
                 INVALID WINDOW
                       │
           ┌───────────┴───────────┐
           │                       │
    Can I only remove          Do I know exactly
    elements one-by-one?       where L should go?
           │                       │
           ↓                       ↓
       L++ once                DIRECT JUMP
           │
           ↓
       O(n) approach


Pattern 2: HeavyWhile → while keeps shrinking until valid
Pattern 3A: NoWhile → if shrinks only one step, window may remain invalid
Pattern 3B: DirectJump → L jumps using stored index
 */