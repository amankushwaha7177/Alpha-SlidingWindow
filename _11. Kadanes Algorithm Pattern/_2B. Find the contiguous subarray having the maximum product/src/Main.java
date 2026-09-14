public class Main {

    public static void main(String[] args) {
        int[] arr = {-2, 3, -4};

        int minProduct = findMinimumProductSubarray(arr);

        System.out.println("Minimum Product Subarray = " + minProduct);

        /*
        Time = O(n)
        Reason : We traverse the array only once while maintaining the current maximum and minimum products.

        Space = O(1)
        Reason : Only currentMax, currentMin and minProduct variables are used without storing the subarray.
        */
    }

    static int findMinimumProductSubarray(int[] arr) {
        int currentMax = arr[0];
        int currentMin = arr[0];
        int minProduct = arr[0];

        for(int i = 1; i < arr.length; i++) {
            int withPrevMax = currentMax * arr[i];
            int withPrevMin = currentMin * arr[i];
            int withOutPrev = arr[i];

            currentMax = Math.max(withOutPrev, Math.max(withPrevMax, withPrevMin));

            currentMin = Math.min(withOutPrev, Math.min(withPrevMax, withPrevMin));

            minProduct = Math.min(minProduct, currentMin); //--> Only this line change

        }

        return minProduct;
    }
}

/*
Q. Given an integer array, find the contiguous subarray having the minimum product.

Brain:

At every element, decide whether to continue the previous subarray
or throw away the previous product and start a new subarray from current element.

For product, we maintain both maximum and minimum because
a negative number can turn the largest product into the smallest product.


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


Then keep the smallest product found so far:

minProduct = Math.min(minProduct, currentMin);


Example:

arr = {-2, 3, -4}

Minimum Product Subarray:

[3, -4]

Product:

3 × -4 = -12

Answer = -12


Important:

For Maximum Product:
→ Maintain current maximum AND current minimum.
→ Update maxProduct using currentMax.

For Minimum Product:
→ Maintain current maximum AND current minimum.
→ Update minProduct using currentMin.

Reason:
A negative number can convert a previous maximum product
into the new minimum product after multiplication.
*/


/*
Dry Run : arr = {-2, 3, -4}

Initial:
currentMax = -2
currentMin = -2
minProduct = -2

Step 1: arr[i] = 3

    withPrevMax = -2 × 3 = -6
    withPrevMin = -2 × 3 = -6
    withOutPrev = 3

    currentMax = max( 3, -6, -6)  =  3
    currentMin = min( 3, -6, -6)  = -6
    minProduct = min(-2, -6)       = -6

Now:
currentMax = 3
currentMin = -6
minProduct = -6


Step 2: arr[i] = -4

    withPrevMax =  3 × -4 = -12
    withPrevMin = -6 × -4 =  24
    withOutPrev = -4

    currentMax = max(-4, -12, 24) = 24
    currentMin = min(-4, -12, 24) = -12
    minProduct = min(-6, -12) = -12

Now:
currentMax = 24
currentMin = -12
minProduct = -12

Minimum Product Subarray:
[3, -4]

Product:
3 × -4 = -12

Answer = -12

Important:
3 × -4 = -12

Previous maximum became the new minimum because
multiplying a positive number by a negative number produces a negative number.
*/