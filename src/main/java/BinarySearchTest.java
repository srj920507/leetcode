/**
 * @author sunrenjie
 * @description 二分法相关题解
 * @date 2020-07-06
 */
public class BinarySearchTest {


    public static void main(String[] args) {

        int[] nums = {5, 6, 1, 3, 4};
        int[][] test = {{1},{3}};
        BinarySearchTest arrayTest = new BinarySearchTest();

        System.out.println(arrayTest.findTarget(test, 1));

    }

    public int findEdge(int[] nums, int target, boolean isleft) {

        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                if (isleft) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

            } else if (nums[mid] < target) {

                left = mid + 1;
            } else {

                right = mid - 1;
            }

        }


        if ((left >= nums.length - 1 || left <= 0) && nums[left] != target) {
            return -1;
        }

        if (isleft) {
            return left;
        } else {
            return right;
        }

    }


    /**
     * 搜索旋转数组
     *
     * @param
     * @return
     */
    public int binarySearch(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (target == nums[mid]) {
                return mid;
            }

            //左半边有序
            if (nums[mid] > nums[left]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

                //右半边有序
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }

            }
        }
        return -1;
    }


    /**
     * 81. 搜索旋转排序数组 II
     * <p>
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     * <p>
     * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
     * <p>
     * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [2,5,6,0,0,1,2], target = 0
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: nums = [2,5,6,0,0,1,2], target = 3
     * 输出: false
     * <p>
     * 10111 11101
     *
     * @param
     * @return
     */

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return true;
            }

            if (nums[mid] == nums[left]) {
                left++;
                continue;
            }
            //左半边有序
            if (nums[mid] > nums[left]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

                //右半边有序
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }

            }


        }

        return nums[left] == target;


    }


    /**
     * @param
     * @return
     */
    public int findMinIndexForArray(int[] nums) {

        if (nums == null || nums.length == 0) {
            return -1;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int left = 0;
        int right = nums.length - 1;

        while (true) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right--;
            }

            if (right == left) {
                return left;
            }

        }


    }


    /**
     * 找到非两个的唯一字母
     *
     * @param
     * @return
     */
    public char findOnlyChar(String str) {
        int left = 0;
        int right = str.length() - 1;


        while (left < right) {
            int mid = left + (right - left) / 2;

            char midChar = str.charAt(mid);
            if (midChar != str.charAt(mid + 1) && midChar != str.charAt(mid - 1)) {
                return str.charAt(mid);
            }
            if (midChar == str.charAt(mid - 1)) {
                if ((mid - 1 - left) % 2 == 0) {
                    left = mid + 1;
                } else {
                    right = mid - 2;
                }

            } else {
                if ((right - mid - 1) % 2 == 0) {
                    right = mid - 1;
                } else {
                    left = mid + 2;
                }
            }

        }

        return str.charAt(left);


    }

    /**
     * 搜索二维矩阵
     * 1 2 3 5
     * 8 9 11 15
     * 30 31 32 33
     *
     * @param
     * @return
     */
    public boolean findTarget(int[][] nums, int target) {
        if (nums.length == 0) {
            return false;
        }

        int start = 0;
        int end = nums.length - 1;
        int rows = nums[0].length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid][rows] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        int left = 0;
        int right = rows;

        while (left < right) {
            int middle = left + (right - left) / 2;
            if (nums[start][middle] == target) {
                return true;
            }

            if (nums[start][middle] > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }


        }
        return nums[start][left] == target;

    }


}
