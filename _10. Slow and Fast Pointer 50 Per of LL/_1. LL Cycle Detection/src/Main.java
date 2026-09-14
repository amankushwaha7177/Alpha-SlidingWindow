public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
/*
Q. Why fast != null && fast.next != null?
A. While loop have fast = fast.next.next; internallly already
   So if this condition need to execute than

   a. fast      -> should not be null to get fast = fast.next;
   b. fast.next -> should not be null to get fast = fast.next.next;


   we are not caring about slow = slow.next; becuase fast is ruuning fast and will
   reach end first.
 */


/*
Why are slow and fast guaranteed to meet?

Non-Cyclic Linked List:

1 → 2 → 3 → 4 → 5 → null

slow moves 1 step at a time.
fast moves 2 steps at a time.

Since the linked list has an end, fast eventually reaches null
before it can meet slow.

Therefore:
fast == null → No Cycle


Cyclic Linked List:

1 → 2 → 3 → 4 → 5
        ↑       ↓
        └───────┘

Once slow and fast enter the cycle, neither pointer can reach null.

slow moves 1 step.
fast moves 2 steps.

Therefore, fast gains exactly 1 position on slow during every iteration.

Because the cycle contains a finite number of nodes, fast must eventually
catch slow at the same node.

Therefore:
slow == fast → Cycle Found


Easy Mental Model:

Straight List → Fast reaches null → No Cycle
Cycle         → Fast keeps running → Eventually catches Slow


Important:
slow == fast means both pointers reference the exact same Node object,
not merely two different nodes containing the same data value.
*/