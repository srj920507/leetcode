

public class SortTest {

    public static void main(String[] args) {

        int[] nums = {4, 5, 2, 1, 0, 9, 10, 3, 55, -1, -3, 109, 3000, 201, 555};
        SortTest sortTest = new SortTest();

//    sortTest.selectSort(nums);

        //  sortTest.insetSort(nums);

        //   sortTest.xierSort(nums);

//        sortTest.quickSort(nums);
        //sortTest.mergeSort(nums);

        sortTest.heapSort(nums);

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }

    }

    public int[] insetSort(int[] nums) {
        int length = nums.length;

        for (int i = 1; i < length; i++) {
            int key = nums[i];
            int j = i - 1;
            for (; j >= 0 && key < nums[j]; j--) {
                nums[j + 1] = nums[j];
            }
            nums[j + 1] = key;
        }
        return nums;
    }


    public int[] heapSort(int[] nums) {
        for(int i = (nums.length-1)/2;i>=0;i--){
            buildHeap(nums, i, nums.length-1);
        }

        for(int i = nums.length-1; i>0; i--){
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            buildHeap(nums,0,i-1);
        }

        return nums;
    }


    private void buildHeap(int[] nums,int parent, int end){
        int parentValue = nums[parent];
        int left = 2*parent+1;
        while(left<=end){
            int right = left+1;
            if(right<=end && nums[right]>nums[left]){
                left = right;
            }

            if(nums[left]>parentValue){
                nums[parent] = nums[left];
                parent = left;
                left = 2*parent+1;
            }else{
                break;
            }

        }
        nums[parent] = parentValue;
    }


    /**
      * todo 搜索一下快排的正确写法
      *
      * @param
      * @return
      */

    public int[] quickSort(int[] nums) {

        quickSort(nums, 0, nums.length - 1);

        return nums;

    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = partition(nums, start, end);
        quickSort(nums, start, mid - 1);
        quickSort(nums, mid + 1, end);
    }

    private int partition(int[] nums, int left, int right) {
        int start = left;
        while (true) {
            while (left < nums.length && nums[left] <= nums[start]) {
                left++;
            }
            while (right >= 0 && nums[right] > nums[start]) {
                right--;
            }
            if (left > right) {
                break;
            }
            newSwap(nums, left, right);
            left++;
            right--;

        }

        newSwap(nums, start, right);
        return right;

    }


    public int[] mergeSort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;


    }

    public void mergeSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);
        merge(nums, start, mid, end);

    }

    private void merge(int[] nums, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int left = start;
        int right = mid + 1;
        int i = 0;
        while (left <= mid && right <= end) {
            if (nums[left] <= nums[right]) {
                temp[i] = nums[left];
                left++;
            } else {
                temp[i] = nums[right];
                right++;
            }
            i++;

        }

        for (; i < temp.length; i++) {
            if (left <= mid) {
                temp[i] = nums[left];
                left++;
            }

            if (right <= end) {
                temp[i] = nums[right];
                right++;
            }
        }


        for (int ii = start; ii <= end; ii++) {
            nums[ii] = temp[ii - start];
        }

    }

    public int[] xierSort(int[] nums) {
        int length = nums.length;

        for (int step = length / 2; step >= 1; step = step / 2) {
            for (int i = step; i < length; i = i + 1) {

                for (int j = i; j >= step && nums[j] < nums[j - step]; j = j - step) {
                    newSwap(nums, j, j - step);
                }
            }
        }

        return nums;

    }

    public int[] selectSort(int[] nums) {

        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int minIndex = i;
            for (int j = i; j < length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            newSwap(nums, i, minIndex);

        }

        return nums;

    }


    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    private void newSwap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        nums[i] = nums[i] + nums[j];
        nums[j] = nums[i] - nums[j];
        nums[i] = nums[i] - nums[j];
    }


    public int[] maopaoSort(int[] nums) {
        int length = nums.length;

        for (int i = length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    newSwap(nums, j, j + 1);
                }
            }
        }

        return nums;

    }


    /**
      * 324. 摆动排序 II todo        快速选择 + 3-way-partition
     *
     * 给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
     *
     * 示例 1:
     *
     * 输入: nums = [1, 5, 1, 1, 6, 4]
     * 输出: 一个可能的答案是 [1, 4, 1, 5, 1, 6]
     * 示例 2:
     *
     * 输入: nums = [1, 3, 2, 2, 3, 1]
     * 输出: 一个可能的答案是 [2, 3, 1, 3, 1, 2]
     * 说明:
     * 你可以假设所有输入都会得到有效的结果。
     *
     * 进阶:
     * 你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
     *
      *
      * @param
      * @return
      */

    public void wiggleSort(int[] nums) {

    }

}
