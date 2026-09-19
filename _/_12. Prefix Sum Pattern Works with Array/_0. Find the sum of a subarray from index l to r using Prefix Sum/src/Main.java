public class Main {
    public static void main(String[] args) {
        int[] arr = {2, 5, 1, 3, 4};

        int l = 1;
        int r = 3;

        int[] prefix = new int[arr.length];
        prefix[0] = arr[0];

        for(int i = 1; i < arr.length; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }

        int sum = prefix[r] - prefix[l - 1];

        System.out.println("Subarray Sum = " + sum);
    }
}
/*
arr = [2, 5, 1, 3, 4]
l = 1
r = 3

First create Prefix Sum:
index:     0  1  2  3  4
arr:      [2, 5, 1, 3, 4]

prefix:  [2, 7, 8, 11, 15]


-----------------------------------------
sum(l...r) = prefix[r] - prefix[l - 1]

sum(1...3)
= prefix[3] - prefix[0]
= 11 - 2
= 9               [5, 1, 3] = 9




------------------------------------------
Mental Rule:

l = 0
→ sum = prefix[r]

l > 0
→ sum = prefix[r] - prefix[l - 1]
 */