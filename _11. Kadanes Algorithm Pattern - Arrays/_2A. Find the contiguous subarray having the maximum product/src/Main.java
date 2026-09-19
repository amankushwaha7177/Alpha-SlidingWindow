public class Main {

    public static void main(String[] args) {
        int[] arr = {2, -3, 2, -4};

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
Intuition : Every element gives us THREE arrows for Product.

Example:
arr = {2, -3, 2, -4}

For every arr[i], we ask:

                         arr[i]
                            |
             ┌──────────────┼──────────────┐
             │              │              │
             ▼              ▼              ▼

       Continue Max    Continue Min    Start Fresh

        withPrevMax     withPrevMin     withOutPrev

       currentMax×arr[i] currentMin×arr[i]    arr[i]

We choose the maximum arrow for currentMax
and the minimum arrow for currentMin.

Step 1 : i = 1
                  -3
                   |
          ┌────────┼────────┐
          ▼        ▼        ▼
      Prev Max  Prev Min  Start
       2×-3       2×-3      -3
        -6         -6       -3

Choose Maximum → -3 ✓  -> currentMax = -3
Choose Minimum → -6 ✓  -> currentMin = -6
ans = max(2, -3) = 2


Step 2 : i = 2
                   2
                   |
          ┌────────┼────────┐
          ▼        ▼        ▼
      Prev Max  Prev Min  Start
      -3×2       -6×2       2
       -6        -12        2

Choose Maximum → 2 ✓  -> currentMax = 2
Choose Minimum → -12 ✓ -> currentMin = -12
ans = max(2, 2) = 2


Step 3 : i = 3
                  -4
                   |
          ┌────────┼────────┐
          ▼        ▼        ▼
      Prev Max  Prev Min  Start
       2×-4      -12×-4      -4
        -8          48       -4

Choose Maximum → 48 ✓  -> currentMax = 48
Choose Minimum → -8 ✓  -> currentMin = -8
ans = max(2, 48) = 48


Maximum Product Subarray:

[2, -3, 2, -4]

Product:

2 × -3 × 2 × -4 = 48

Answer = 48


Important:

For Sum:
→ TWO arrows
→ Continue Previous OR Start Fresh

For Product:
→ THREE arrows
→ Continue Previous Maximum
→ Continue Previous Minimum
→ Start Fresh

Reason:
A negative number can convert the previous minimum
into the new maximum after multiplication.
*/