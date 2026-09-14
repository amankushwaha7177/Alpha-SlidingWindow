public class _2_Optimal_SlowFastPointer_CycleDetection {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static boolean hasCycle(Node head) {
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) {
                return true;
            }
        }

        return false;
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

        /*
                ┌───────────┐
                ↓           │
        1 → 2 → 3 → 4 → 5 ──┘

        slow → 1 step
        fast → 2 steps

        Fast is faster, so inside the cycle
        fast will eventually catch slow.
         */

        System.out.println("Cycle = " + hasCycle(n1));

        /*
        Time = O(n)
        Reason : Slow and fast pointers move through the list and eventually meet if a cycle exists.
        Space = O(1)
        Reason : Only two pointer variables are used without storing visited nodes.
        */
    }
}

/*
        Step 0
        1 → 2 → 3 → 4 → 5
                ↑       ↓
                └───────┘
        S
        F

        Step 1
        1 → 2 → 3 → 4 → 5
                ↑       ↓
                └───────┘
            S   F

        Step 2
        1 → 2 → 3 → 4 → 5
                ↑       ↓
                └───────┘
                S       F


        Step 3
        1 → 2 → 3 → 4 → 5
                ↑       ↓
                └───────┘
                   S/F ---> Pause


Core interview logic:
--------------------
slow = +1 node
fast = +2 nodes

If fast == null
    → No cycle

If slow == fast
    → Cycle exists
*/


