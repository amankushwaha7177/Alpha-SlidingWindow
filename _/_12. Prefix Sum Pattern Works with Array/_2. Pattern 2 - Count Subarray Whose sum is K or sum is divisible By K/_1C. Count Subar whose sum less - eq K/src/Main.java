public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
/*
Fenwick Tree helps us count how many previous prefix sums
fall within the required range in O(log n) time.

We use it because with positive and negative numbers,
sliding window is not reliable for sum <= K.

Time = O(n log n)
Space = O(n)
*/