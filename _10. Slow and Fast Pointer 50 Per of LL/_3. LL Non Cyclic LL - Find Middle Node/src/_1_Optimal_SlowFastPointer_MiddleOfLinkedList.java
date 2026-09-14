public class _1_Optimal_SlowFastPointer_MiddleOfLinkedList {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    /*
    Middle of Linked List is the easiest Slow & Fast Pointer problem and helps build the intuition before harder questions.
     */
    public static Node findMiddle(Node head) {
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
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

        Node middle = findMiddle(n1);

        System.out.println("Middle = " + middle.data);

        /*
        Time = O(n)
        Reason : Fast pointer moves through the linked list until it reaches the end.
        Space = O(1)
        Reason : Only slow and fast pointers are used without storing additional nodes.
        */
    }
}

/*
Example: 1 → 2 → 3 → 4 → 5 → null
Answer: 3

1 → 2 → 3 → 4 → 5 → null
S
F

1 → 2 → 3 → 4 → 5 → null
    S       F

1 → 2 → 3 → 4 → 5 → null       Working fine for Odd length LL
        S           F          Loop will pause when fast == null



For even length: 1 → 2 → 3 → 4 → 5 → 6 → null
Answer: 4
         Usually, we return the second middle:

1 → 2 → 3 → 4 → 5 → 6 → null
S
F

1 → 2 → 3 → 4 → 5 → 6 → null
    S       F

1 → 2 → 3 → 4 → 5 → 6 → null
        S       F

1 → 2 → 3 → 4 → 5 → 6 → null      Working fine for Even length LL
            S       F             Loop will pause when fast.next == null
 */


/*
Q. Why fast != null && fast.next != null?
A. While loop have fast = fast.next.next; internallly already
   So if this condition need to execute than

   a. fast      -> should not be null to get fast = fast.next;
   b. fast.next -> should not be null to get fast = fast.next.next;


   we are not caring about slow = slow.next; becuase fast is ruuning fast and will
   reach end first.
 */