public class Main {
}
/*
Q. Why do we use while(true) for Array Cycle Detection
   but while(fast != null && fast.next != null) for Linked List?

A. Linked List:

    1 → 2 → 3 → 4 → 5 → null

    A Linked List has a natural stopping point called null.

    If there is no cycle, fast will eventually reach null.

    Therefore we can safely use:

    while(fast != null && fast.next != null)

    There are only two possibilities:

    1. fast reaches null
       → No cycle

    2. slow == fast
       → Cycle found


B. Array:

    a = {1, 3, 4, 2, 2}

    Movement:

    0 → 1 → 3 → 2 → 4 → 2 → 4 → 2 → ...

    An array does not have null after the last index.
    Once we enter a cycle, the pointers can keep moving forever.

    Therefore, when a cycle is guaranteed, we can use:
    while(true)

    It means:  Keep moving the pointers until the condition we care about happens.

    The condition we care about is:
    if(slow == fast)

    Therefore:

    slow == fast
    → Cycle Found
    → break the infinite loop


Simple Mental Rule:

Linked List:
Has null
→ Use null as stopping condition.

Array:
No null
→ If cycle is guaranteed, keep moving until slow == fast.


Important:
----------
while(true) does NOT mean the algorithm should always run forever.

It means we intentionally create an infinite loop and use break
when the required stopping condition is reached.


Linked List:

while(fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;

    if(slow == fast) {
        return true;
    }
}


Array:

while(true) {
    slow = a[slow];

    fast = a[fast];
    fast = a[fast];

    if(slow == fast) {
        break;
    }
}


Main Difference:

Linked List → fast can reach null.
Array → there is no null, so we stop when slow and fast meet.
*/