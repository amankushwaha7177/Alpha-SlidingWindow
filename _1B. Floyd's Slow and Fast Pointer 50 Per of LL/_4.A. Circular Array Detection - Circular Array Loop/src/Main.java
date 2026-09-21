class Main {}
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

    Therefore, if a cycle is guaranteed by the problem,
    we can use:

    while(true)

    It means:
    Keep moving the pointers until slow and fast meet.

    if(slow == fast) {
        break;
    }

    slow == fast
    → Cycle found


C. BUT WHAT IF THE ARRAY DOES NOT HAVE A CYCLE?

    This is the important point.

    If a cycle is NOT guaranteed, then:

    while(true)

    is dangerous because there is no natural stopping condition.

    The loop could continue forever.

    Example:

    0 → 1 → 2 → 3 → ...

    If there is no cycle and no boundary check,
    there is nothing that tells while(true) to stop.

    Therefore:

    Cycle guaranteed:
    → while(true) is acceptable.

    Cycle NOT guaranteed:
    → We need an additional stopping condition.


Simple Mental Rule:

Linked List:
Has null
→ null can tell us that no cycle exists.

Array:
Has no null
→ If cycle is guaranteed, use while(true) and stop when slow == fast.

Array:
Cycle is NOT guaranteed
→ while(true) alone is NOT safe.
→ We need boundary/validity checks.


Linked List:

while(fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;

    if(slow == fast) {
        return true;
    }
}


Array when cycle is guaranteed:

while(true) {
    slow = a[slow];

    fast = a[fast];
    fast = a[fast];

    if(slow == fast) {
        break;
    }
}


Main Difference:

Linked List → fast can reach null, so null gives us a stopping condition.

Array → there is no null, so while(true) is safe only when a cycle is guaranteed.

IMPORTANT:
while(true) does NOT itself detect a cycle.

It only keeps the pointers moving.

slow == fast is what tells us that the cycle has been found.
*/