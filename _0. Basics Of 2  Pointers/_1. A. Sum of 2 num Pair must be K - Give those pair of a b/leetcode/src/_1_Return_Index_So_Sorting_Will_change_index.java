import java.util.*;
class _1_Return_Index_So_Sorting_Will_change_index {
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