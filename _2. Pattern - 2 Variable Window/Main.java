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
 */