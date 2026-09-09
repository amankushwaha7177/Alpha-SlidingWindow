import java.util.*;
class _1_Return_Index_So_Sorting_Please {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> m = new HashMap<>();

        for( int i =0; i< nums.length; i++){
            m.put(nums[i], i);
        }

        for(int i =0; i< nums.length ; i++){
            int k = target - nums[i];

            if(m.containsKey(k) && i != m.get(k)){
                return new int[]{i, m.get(k)};
            }
        }
        return  new int[]{};
    }

}

/*
Here we can not use sorting because we have to return indexes of both the element whose sum is target.
SO sorting will not help in this it will change the position of the elements.

so we need to use other ways like hashmap.

-----------------------------------------------------
cause No Sorting  ---> no 2 pointers in sum question.
becuase we can move l,r using sum < , >, = condtions
-----------------------------------------------------
 */