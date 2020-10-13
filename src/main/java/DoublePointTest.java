import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class DoublePointTest {

    public static void main(String[] args) {

        int[][] nums = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        DoublePointTest arrayTest = new DoublePointTest();
        Scanner scanner = new Scanner(System.in);
        
    }
    
    
    
    /**
      * 26. 删除排序数组中的重复项 todo(待复习这种思想)

      *给定 nums = [0,0,1,1,1,2,2,3,3,4],
      *
      * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
      *
      * 你不需要考虑数组中超出新长度后面的元素。
      *
      * @param 
      * @return 
      */
    public int removeDuplicates(int[] nums) {
        int index = 0;
        for(int i=1; i<nums.length; i++){
            if(nums[i] != nums[index]){
                index++;
                nums[index] = nums[i];
            }
        }

        return index+1;

    }


    /**
      * 80. 删除排序数组中的重复项 II  (可延续到重复K)
      *
      * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
      *
      * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
      *
      * 给定 nums = [0,0,1,1,1,1,2,3,3],
      *
      * 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
      *
      * 你不需要考虑数组中超出新长度后面的元素。

      * @param
      * @return
      */
    public int removeDuplicates2(int[] nums) {

        int count =  1;
        int index = 0;


        for(int i=1;i<nums.length; i++){
            if(nums[i] == nums[i-1]){
                count ++;
            }else{
                count =1;
            }

            if(count<=2){
                index++;
                nums[index] = nums[i];
            }
        }

        return index+1;


    }
}
