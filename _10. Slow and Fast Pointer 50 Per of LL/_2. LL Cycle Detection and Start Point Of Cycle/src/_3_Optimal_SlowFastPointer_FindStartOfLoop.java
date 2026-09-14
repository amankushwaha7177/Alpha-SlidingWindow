public class _3_Optimal_SlowFastPointer_FindStartOfLoop {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /*First meeting tells us a cycle exists.
      Reset one pointer to head, then move both one step; their second meeting gives the loop start.
     */
    public static Node findLoopStart(Node head) {
        Node slow = head;
        Node fast = head;

        /* Step 1 : Find whether a cycle exists and find the meeting point. */
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) {
                break;
            }
        }

        /* No cycle exists, so there is no loop starting node to return. */
        if(fast == null || fast.next == null) {
            return null;
        }

        /* Step 2 : Reset slow to head while keeping fast at the meeting point. */
        slow = head;

        /* Both pointers now move one step until they meet at loop start. */
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n3;

        Node answer = findLoopStart(n1);

        System.out.println("Loop Start = " + answer.data);

        /*
        Time = O(n)
        Reason : Both pointer phases together traverse the linked list a linear number of times.
        Space = O(1)
        Reason : Only slow and fast pointers are used without storing visited nodes.
        */
    }
}

/*
For our example:
    1 → 2 → 3 →  4  → 5
            ↑         ↓
            └─────────┘
                s|f
The first meeting happens at:
slow = 4
fast = 4


Then:
slow = head = 1
fast = 4
    1 → 2 → 3 →  4  → 5
    s       ↑    f    ↓
            └─────────┘

Move both one step:
slow = 2     fast = 5
slow = 3     fast = 3
    1 → 2 → 3 →  4  → 5
            ↑         ↓
            └─────────┘
           s|f

They meet at 3.

3 = Loop Start
 */