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