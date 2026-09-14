public class Main {

    public static void main(String[] args) {
        int[] arr = {2, 3, -2, 4};

        int maxProduct = findMaximumProductSubarray(arr);

        System.out.println("Maximum Product Subarray = " + maxProduct);

        /*
        Time = O(n)
        Reason : We traverse the array only once while maintaining the current maximum and minimum products.

        Space = O(1)
        Reason : Only currentMax, currentMin and maxProduct variables are used without storing the subarray.
        */
    }

    static int findMaximumProductSubarray(int[] arr) {
        int currentMax = arr[0];
        int currentMin = arr[0];
        int maxProduct = arr[0];

        for(int i = 1; i < arr.length; i++) {
            int withPrevMax = currentMax * arr[i];
            int withPrevMin = currentMin * arr[i];
            int withOutPrev = arr[i];

            currentMax = Math.max(withOutPrev, Math.max(withPrevMax, withPrevMin));

            currentMin = Math.min(withOutPrev, Math.min(withPrevMax, withPrevMin));

            maxProduct = Math.max(maxProduct, currentMax);
        }

        return maxProduct;
    }
}

/*
Q. Given an integer array, find the contiguous subarray having the maximum product.

Brain:

At every element, decide whether to continue the previous subarray
or throw away the previous product and start a new subarray from current element.

For product, we maintain both maximum and minimum because
a negative number can turn the smallest product into the largest product.


withPrevMax = currentMax * arr[i];
withPrevMin = currentMin * arr[i];
withOutPrev = arr[i];


Current Maximum:

currentMax = maximum of:
            1. Current element
            2. Previous maximum × current element
            3. Previous minimum × current element


Current Minimum:

currentMin = minimum of:
            1. Current element
            2. Previous maximum × current element
            3. Previous minimum × current element


Then keep the best product found so far:

maxProduct = Math.max(maxProduct, currentMax);


Example:

arr = {2, 3, -2, 4}

Maximum Product Subarray:

[2, 3]

Product:

2 × 3 = 6

Answer = 6


Important:

For Sum:
→ Maintain current maximum.

For Product:
→ Maintain current maximum AND current minimum.

Reason:
A negative number can convert a previous minimum product
into the new maximum product after multiplication.
*/


/*
Dry Run : arr = {-2, 3, -4}

Initial:
currentMax = -2
currentMin = -2
maxProduct = -2

Step 1: arr[i] = 3

    withPrevMax = -2 × 3 = -6
    withPrevMin = -2 × 3 = -6
    withOutPrev = 3

    currentMax = max( 3, -6, -6)  =  3
    currentMin = min( 3, -6, -6,) = -6
    maxProduct = max( 3, -2)      =  3

Now:
currentMax = 3
currentMin = -6
maxProduct = 3


Step 2: arr[i] = -4

    withPrevMax =  3 × -4 = -12
    withPrevMin = -6 × -4 =  24
    withOutPrev = -4

    currentMax = max(-4, -12, 24) = 24
    currentMin = min(-4, -12, 24) = -12
    maxProduct = max(3, 24) = 24

Now:
currentMax = 24
currentMin = -12
maxProduct = 24

Maximum Product Subarray:
[-2, 3, -4]

Product:
-2 × 3 × -4 = 24

Answer = 24

Important:
-6 × -4 = 24

Previous minimum became the new maximum because
multiplying a negative number by another negative number produces a positive number.
*/