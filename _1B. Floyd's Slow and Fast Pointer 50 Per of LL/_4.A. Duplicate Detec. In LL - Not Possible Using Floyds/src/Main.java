import java.util.HashSet;

public class Main {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);

        System.out.println("Duplicate = " + findDuplicate(head));
    }

    static int findDuplicate(Node head) {
        HashSet<Integer> set = new HashSet<>();

        while(head != null) {
            if(set.contains(head.data)) {
                return head.data;
            }

            set.add(head.data);
            head = head.next;
        }

        return -1;
    }
}
/*
If you mean find a duplicate node/value in a linked list,
Floyd's algorithm is not for finding a duplicate value. It detects a cycle.

If the linked list contains repeated values, use a HashSet:
 */