import java.util.Stack;

public class DanDiaoStackTest {

    public static void main(String[] args) {

        int[] nums = {5,1,1};

        DanDiaoStackTest danDiaoStackTest = new DanDiaoStackTest();

        danDiaoStackTest.nextSequence(nums);
        for(int num:nums){
            System.out.println(num);
        }

    }


    public int maxSize4Histrom(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for(int i=0;i<nums.length;i++){
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]){
                int top = stack.pop();
                if(!stack.isEmpty()){
                    ans = Math.max(nums[top] * (i-stack.peek()-1), ans);
                }else{
                    ans = Math.max(nums[top] * i, ans);
                }
            }
            stack.push(i);
        }

        while (!stack.isEmpty()){
            int top = stack.pop();
            if(!stack.isEmpty()){
                ans = Math.max(nums[top] * (top-stack.peek()), ans);
            }else{
                ans = Math.max(nums[top] * nums.length, ans);
            }
        }

        return ans;

    }

    /**
      * 12345
     *  12543
      *
      * @param
      * @return
      */
    public int getNextGreaterNum(int num){

        String numStr = String.valueOf(num);

        char[] characters = numStr.toCharArray();

        Stack<Integer> stack = new Stack<>();

        for(int i= characters.length-1; i>=0;i--){
            int index = -1;
            while (!stack.isEmpty() && characters[i] < characters[stack.peek()]){
                index = stack.pop();
            }
            if(index>0){
                char temp = characters[index];
                characters[index] = characters[i];
                characters[i] = temp;
                int left = i+1;
                int right = characters.length-1;
                while (left<right){
                    char tempChar = characters[left];
                    characters[left] = characters[right];
                    characters[right] = tempChar;
                    left++;
                    right--;
                }

                String aa = String.valueOf(characters);
                return Integer.valueOf(aa);


            }
            stack.push(i);

        }

        return -1;


    }



    /**
      * 每日温度
      *
      * @param
      * @return
      */
    public int[] dailyTemperatures(int[] T) {

        int[] res = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<T.length;i++){
            while (!stack.isEmpty() && T[stack.peek()]<T[i]){
                int index = stack.pop();
                res[index] = i-index;
            }
            stack.push(i);

        }

        while (!stack.isEmpty()){
            res[stack.pop()] = 0;
        }

        return res;

    }


    /**
      * 下一个排列 TODO 很多边界没做好
      *  [1,2,3] -> [1,3,2]
     *   [3,2,1]->[1,2,3]
      * @param
      * @return
      */

    public void nextSequence(int[] nums){

        if(nums.length<=1){
            return;
        }

       int index = nums.length-2;

       while (nums[index]>=nums[index+1]){
           index--;
           if(index<0){
               break;
           }
       }

       if(index<0){
           reverse(nums, 0, nums.length-1);
           return;
       }

       int right = index+1;
       for(;right<nums.length;right++){
           if(nums[right]<=nums[index]){
               break;
           }
       }

       right = right-1;

       swap(nums,index, right);

       reverse(nums,index+1, nums.length-1);

    }

    private void reverse(int[] nums, int start, int end){

        while (start<end){
            swap(nums, start, end);
            start++;
            end--;
        }

    }

    private void swap(int[] nums, int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }


}
