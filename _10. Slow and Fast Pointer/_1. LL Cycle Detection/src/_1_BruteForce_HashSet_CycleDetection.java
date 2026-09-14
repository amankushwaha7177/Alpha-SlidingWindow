import java.util.HashSet;

public class _1_BruteForce_HashSet_CycleDetection {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /* Node → Have I seen this node before?
        No  → Store it → Move forward
        Yes → Cycle found */

    public static boolean hasCycle(Node head) {
        HashSet<Node> set = new HashSet<>();

        Node current = head;

        while(current != null) {
            if(set.contains(current)) {
                return true;
            }

            set.add(current);
            current = current.next;
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

        System.out.println("Cycle = " + hasCycle(n1));

        /*
        Time = O(n)
        Reason : Every node is visited at most once before detecting a repeated node or reaching null.
        Space = O(n)
        Reason : HashSet stores every visited node in the linked list.
        */
    }
}

/*
                ┌───────────┐
                ↓           │
        1 → 2 → 3 → 4 → 5 ──┘

       current = 1
       set = {}

        1 not present
        → add 1
        → current = 2


        current = 2
        set = {1}

        2 not present
        → add 2
        → current = 3


        current = 3
        set = {1,2}

        3 not present
        → add 3
        → current = 4


        current = 4
        set = {1,2,3}

        4 not present
        → add 4
        → current = 5


        current = 5
        set = {1,2,3,4}

        5 not present
        → add 5
        → current = 3


        current = 3
        set = {1,2,3,4,5}

        3 IS already present
        → Cycle found
        → return true


 */