public class Main2 {
    public static void main(String[] args) {
        int[] arr = {2, -5, 1, -3, 4};

        int l = 1;
        int r = 3;

        int sum = 0;

        /*
        Add every element from index l to index r.
        This works with positive, negative, and zero values.
        */
        for(int i = l; i <= r; i++) {
            sum += arr[i];
        }

        System.out.println("Subarray Sum = " + sum);
    }
}

/*
arr = [2, -5, 1, -3, 4]
l = 1
r = 3

We need:

arr[1...3]

        [-5, 1, -3]

Dry Run:

i = 1
sum = 0 + (-5)
    = -5

i = 2
sum = -5 + 1
    = -4

i = 3
sum = -4 + (-3)
    = -7

Answer:

Subarray = [-5, 1, -3]
Sum = -7

Time = O(r-l+1)
Space = O(1)

Important:

This direct loop works with both positive and negative numbers
because we simply add every element inside the requested range.
*/