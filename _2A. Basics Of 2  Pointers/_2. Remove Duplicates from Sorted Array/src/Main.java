import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr= {1,1,1,1,2,2,3};

        /*
        1. Remove Duplicate + Sorted Term => 2 pointers
        2. we have to check 2 elements one by one
           start a pointer l=0;
           we need to check further elements so move next to number using r=1
           r will find another unique number.
        3. a. Dont move l, move it only when you get unique neighbour for it.
           b. Keep moving r, when you get duplicate keep skipping them
              when you get another unique one just put it infront of l. and move l to it.
              than move r++ for next search.
         */

        int l =0;
        int r=1;

        while( r < arr.length){

            if(arr[l] == arr[r]){
                r++;
                continue;
            }
            if(arr[l] != arr[r]) {
                arr[l + 1] = arr[r];
                l++;
                r++;
            }
        }

        System.out.println(Arrays.toString(arr));
    }
}

/*
Why use else instead of another if?

Both conditions are opposite of each other:
arr[l] == arr[r] means the values are duplicate.
arr[l] != arr[r] means the values are different.

When the first condition is true, we increment r.
If we use another separate if, Java immediately checks the second
condition using the newly updated r value in the same iteration.

This can make r become arr.length and then arr[r] tries to access
an index outside the array, causing ArrayIndexOutOfBoundsException.

Using else guarantees that only one condition executes in each iteration.

    if(arr[l] == arr[r]){
            r++;
    }
    else {
            arr[l + 1] = arr[r];
            l++;
            r++;
    }


    or if you want to use if if then continue after moving r dong go below code.
    if(arr[l] == arr[r]){
                r++;
                continue;
    }



 */