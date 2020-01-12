package main.java.interview.chapter3;

public class Test {
    /**
     * 输入: [2,0,2,1,1,0]
        输出: [0,0,1,1,2,2]
     */
    public void sortColors(int[] nums) {
        // [0,zero] == 0;[zero + 1,i-1]==1;[two,nums.length-1]==2;
        int zero = -1;
        int two = nums.length;
        for (int i = 0; i < two;) {
            if(nums[i] == 1){
                i = i + 1;
            }else if(nums[i] == 2){
                swap(nums, i, --two);
            }else {
                swap(nums, ++zero, i++);
            }
        }
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
     }

     public int[] twoSum(int[] numbers, int target) {
        int [] res = new int[2];
        
        int l = 0;
        int r = numbers.length - 1;

        while(l != r){
            int sum = numbers[l] + numbers[r];
            if(sum == target){
                res[0] = l + 1;
                res[1] = r + 1;
                break;
            }else if(sum < target){
                l++;
            }else {
                r--;
            }
        }

        return res;
    }
    /**
     * 输入: s = 7, nums = [2,3,1,2,4,3] 输出: 2
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        //[l,r]元素之和为sum
        int i = 0;
        int j = -1;
        int sum = 0;
        int res = nums.length + 1;
        while(i < nums.length){
            if(j < nums.length - 1 && sum < s){
                sum = sum + nums[++j];
            }else{
                sum = sum - nums[i++];
            }
            if(sum >= s){
                res = Math.min(j-i+1,res);
            }
        }
        return res == nums.length + 1 ? 0 : res;
    }

    public static void main(String[] args) {
        // int [] nums = {2,0,2,1,1,0};
        // Test test = new Test();
        // test.sortColors(nums);
        // for (int i = 0; i < nums.length; i++) {
        //     System.out.print(nums[i] + " ");
        // }

        // int [] nums = {2, 7, 11, 15};
        // Test test = new Test();
        // int [] res = test.twoSum(nums, 9);
        // System.out.println(res[0] + " " + res[1]);

        int [] nums = {1,1};
        Test  test = new Test();
        int res = test.minSubArrayLen(3, nums);
        System.out.println(res);
    }
}