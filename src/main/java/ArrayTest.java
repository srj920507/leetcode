import java.math.BigDecimal;
import java.util.*;

public class ArrayTest {

    public static void main(String[] args) {

        int[][] nums = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};


        int[] test = {1, 2, 3, 1};
        ArrayTest arrayTest = new ArrayTest();
        Scanner scanner = new Scanner(System.in);


//            * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
//     * 输出：[2]
//     * 示例 2：
//     * <p>
//     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//     * 输出：[9,4]

        int[] num1 = {4,9,5};
        int[] num2 = {9,4,9,8,4};

        int[] res = arrayTest.intersect(num1,num2);


        System.out.println(arrayTest.containsNearbyAlmostDuplicate(test, 3, 0));

        String a =  BigDecimal.valueOf(250000L).divide(new BigDecimal(100)).setScale(2). toString();
        System.out.println(a);


    }

    /**
     * 除自身以外数组乘积
     *
     * @param
     * @return
     */
    public int[] calculateProduct(int[] nums) {
        int length = nums.length;

        int[] result = new int[length];

        int left = 1;

        for (int i = 0; i < length; i++) {
            result[i] = left;
            left = left * nums[i];

        }

        int right = 1;
        for (int j = length - 1; j >= 0; j--) {
            result[j] = result[j] * right;
            right = right * nums[j];

        }
        return result;
    }


    //寻找重复数(这个得再熟悉熟悉)

    public int findDuplicateNum(int[] nums) {

        int start = 1;
        int end = nums.length - 1;
        while (start < end) {

            int mid = start + (end - start) / 2;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }

            if (count > mid) {
                end = mid;
            } else {
                start = mid + 1;
            }

        }

        return start;
    }


    /**
     * 缺失的第一个正数
     *
     * @param
     * @return
     */
    private int findFirstPositiveNum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int length = nums.length;

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }


    /**
     * 239.滑动窗口最大值
     *
     * @param
     * @return
     */
    public int[] maxNumInWindownK(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }

        int[] res = new int[nums.length - k + 1];

        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[deque.getLast()] < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }
        res[0] = nums[deque.peekFirst()];

        for (int i = k; i < nums.length; i++) {
            while (!deque.isEmpty() && deque.getFirst() < i - k + 1) {
                deque.removeFirst();
            }

            while (!deque.isEmpty() && nums[deque.getLast()] < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(i);

            res[i - k + 1] = nums[deque.peekFirst()];

        }

        return res;

    }


    /**
     * 乘积小于k的子数组数量（todo 这里还得在看看）
     * 2 5 10 21
     *
     * @param
     * @return
     */
    public int getProductLessTarget(int nums[], int target) {

        if (target <= 1) {
            return 0;
        }

        int res = 0;
        int preProduct = 1;
        int left = 0;
        int right = 0;

        for (; right < nums.length; right++) {

            preProduct = preProduct * nums[right];
            while (preProduct >= target) {
                preProduct = preProduct / nums[left];
                left++;
            }

            res = res + (right - left + 1);


        }

        return res;


    }


    /**
     * 和为k的子数组(可以加入hashMap进行优化)
     *
     * @param
     * @return
     */
    public int getSumEqualK(int nums[], int target) {
        int[] preSum = new int[nums.length + 1];
        preSum[0] = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
            for (int j = 0; j < i; j++) {
                if (preSum[i + 1] - preSum[j] == target) {
                    System.out.println(i + "   " + j);
                    res++;
                }
            }
        }
        return res;
    }


    /**
     * 605种花问题
     *
     * @param
     * @return
     */
    public boolean canAddFlower(int nums[], int n) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if ((i - 1 < 0 || nums[i - 1] == 0) && (i + 1 >= nums.length || nums[i + 1] == 0)) {
                    nums[i] = 1;
                    n--;
                }
            }
        }
        return n == 0;
    }


    /**
     * 寻找两个数组的中位数（todo 本来想写类似二分法方式的解法，但是边界问题太复杂，还需要再看看）
     *
     * @param
     * @return
     */
    public int findMedian(int[] nums1, int[] nums2) {

        return 0;


    }

    //1 3 5 7
    //2 4 6 8
    //1 2 3 4 5 6 7 8


    private int findMedian(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int length1 = end1 - start1 + 1;
        int length2 = end2 - start2 + 1;


        return 0;

    }

    /**
     * 矩阵翻转（原地）（todo 如果是m*n的该怎么旋转）
     * <p>
     * [1, 2, 3
     * 5, 6, 7
     * 9, 10,11]
     *
     * @param
     * @return
     */
    public void roll90(int[][] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = nums[i][j];
                nums[i][j] = nums[j][i];
                nums[j][i] = temp;
            }

        }
        int left = 0;
        int right = n - 1;
        while (left < right) {
            for (int j = 0; j < n; j++) {
                int temp = nums[left][j];
                nums[left][j] = nums[right][j];
                nums[right][j] = temp;
            }
            left++;
            right--;

        }


    }

    public int matrixScore(int[][] A) {

        int res = 0;

        for (int i = 0; i < A.length; i++) {

            if (A[i][0] == 0) {

                for (int j = 0; j < A[0].length; j++) {
                    if (A[i][j] == 0) {
                        A[i][j] = 1;
                    } else {
                        A[i][j] = 0;
                    }

                }

            }

            res = res + (1 << (A[0].length - 1));

        }


        for (int j = 1; j < A[0].length; j++) {
            int numsZero = 0;
            for (int i = 0; i < A.length; i++) {
                if (A[i][j] == 0) {
                    numsZero++;
                }
            }

            if (numsZero > A.length / 2) {

                for (int i = 0; i < A.length; i++) {
                    if (A[i][j] == 0) {
                        A[i][j] = 1;
                    } else {
                        A[i][j] = 0;
                    }
                }

            }

            if (numsZero > A.length / 2) {
                res = res + numsZero * (1 << (A[0].length - j - 1));
            } else {
                res = res + (A.length - numsZero) * (1 << (A[0].length - j - 1));
            }


        }

        return res;


    }


    /**
     * 59.螺旋矩阵
     * [1   2  3   4
     * 12 13  14  5
     * 11 16  15  6
     * 10 9   8   7 ]
     * <p>
     * 1  2  3
     * 8  9  4
     * 7  6  5
     *
     * @param
     * @return
     */


    public int[][] generateRollMatrix(int n) {
        if (n == 0) {
            return null;
        }
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;

        int[][] matrix = new int[n][n];

        int index = 1;
        int target = n * n;
        while (index <= target) {
            for (int i = left; i <= right; i++) {
                matrix[top][i] = index;
                index++;
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = index;
                index++;
            }
            right--;
            for (int i = right; i >= left; i--) {
                matrix[bottom][i] = index;
                index++;
            }
            bottom--;
            for (int i = bottom; i >= top; i--) {
                matrix[i][left] = index;
                index++;
            }
            left++;
        }


        return matrix;


    }


    /**
     * 加油站 做出来了，但是有些不熟
     *
     * @param
     * @return
     */


    // 1 2 3 4 5    //  2 3 4
    // 3 4 5 1 2    //  3 4 3
    public int getStartStation(int[] gas, int[] cost) {


        int left = 0;
        int station = 0;
        int sum = 0;
        for (int i = 0; i < gas.length; i++) {
            left = left + gas[i] - cost[i];
            sum = sum + gas[i] - cost[i];
            if (left < 0) {
                station = i + 1;
                left = 0;
            }
        }

        return sum < 0 ? -1 : station;
    }


    /**
     * 73. 矩阵置零 todo (写了一种很不严谨的ac过了，但是还有个严谨的方法要写下)
     * 输入:
     * [
     *   [1,1,1],
     *   [1,0,1],
     *   [1,1,1]
     * ]
     * 输出:
     * [
     *   [1,0,1],
     *   [0,0,0],
     *   [1,0,1]
     * ]
     *
     * @param
     * @return
     */

    public void setZeroes(int[][] matrix) {
    }

    /**
     * 667. 优美的排列 II
     * <p>
     * 给定两个整数 n 和 k，你需要实现一个数组，这个数组包含从 1 到 n 的 n 个不同整数，同时满足以下条件：
     * <p>
     * ① 如果这个数组是 [a1, a2, a3, ... , an] ，那么数组 [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] 中应该有且仅有 k 个不同整数；.
     * <p>
     * ② 如果存在多种答案，你只需实现并返回其中任意一种.
     * <p>
     * <p>
     * 输入: n = 3, k = 2
     * 输出: [1, 3, 2]
     * 解释: [1, 3, 2] 包含 3 个范围在 1-3 的不同整数， 并且 [2, 1] 中有且仅有 2 个不同整数: 1 和 2
     * <p>
     * 1 2 3 4 5 6 7 8  1 8 2 7 3 6
     *
     * @param
     * @return
     */

    public int[] constructArray(int n, int k) {

        int[] res = new int[n];
        //先构造k-1个差值，剩下的递增或递减
        int left = 1;
        int right = n;
        for (int i = 0; i < k; i++) {
            if (i % 2 == 0) {
                res[i] = left++;
            } else {
                res[i] = right--;
            }

        }

        for (int i = k; i < n; i++) {

            if (k % 2 == 1) {
                res[i] = res[i - 1] + 1;
            } else {
                res[i] = res[i - 1] - 1;
            }

        }


        return res;
    }


    /**
     * 剑指 Offer 57 - II. 和为s的连续正数序列 这居然也是简单。。
     * <p>
     * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
     * <p>
     * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
     * <p>
     * 示例 1：
     * <p>
     * 输入：target = 9
     * 输出：[[2,3,4],[4,5]]
     * 示例 2：
     * <p>
     * 输入：target = 15
     * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
     *
     * @param
     * @return
     */

    public int[][] findContinuousSequence(int target) {
        List<List<Integer>> resList = new ArrayList<>();
        int left = 1;
        int preSum = 1;
        for (int right = 2; right < target; right++) {
            preSum = preSum + right;
            while (preSum > target) {
                preSum = preSum - left;
                left++;
            }
            if (preSum == target && right - left > 0) {
                List<Integer> list = new ArrayList<>();
                for (int j = left; j <= right; j++) {
                    list.add(j);
                }
                resList.add(list);
            }

        }

        int[][] res = new int[resList.size()][];
        int m = 0;
        for (List<Integer> list : resList) {
            int[] child = new int[list.size()];
            for (int q = 0; q < list.size(); q++) {
                child[q] = list.get(q);
            }
            res[m++] = child;
        }

        return res;

    }


    /**
     * 剑指 Offer 45. 把数组排成最小的数  todo 待测试
     * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: [10,2]
     * 输出: "102"
     * 示例 2:
     * <p>
     * 输入: [3,30,34,5,9]
     * 输出: "3033459"
     *
     * @param
     * @return
     */

    public String minNumber(int[] nums) {

        if (nums.length == 0) {
            return "";
        }

        String[] temp = new String[nums.length];
        for (int j = 0; j < nums.length; j++) {
            temp[j] = nums[j] + "";
        }

        Arrays.sort(temp, (o1, o2) -> (int) (Long.valueOf(o1 + o2) - Long.valueOf(o2 + o1))
        );

//        Arrays.sort();
        int i;
        for (i = 0; i < temp.length; i++) {
            if (!temp[i].equals("0")) {
                break;
            }

        }

        StringBuilder stringBuilder = new StringBuilder();
        for (; i < temp.length; i++) {
            stringBuilder.append(temp[i]);

        }

        return stringBuilder.toString();


    }


    /**
     * 162. 寻找峰值 todo 二分查找的方法记得看下
     * 峰值元素是指其值大于左右相邻值的元素。
     * <p>
     * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
     * <p>
     * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
     * <p>
     * 你可以假设 nums[-1] = nums[n] = -∞。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [1,2,3,1]
     * 输出: 2
     * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
     * 示例 2:
     * <p>
     * 输入: nums = [1,2,1,3,5,6,4]
     * 输出: 1 或 5
     * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
     *      或者返回索引 5， 其峰值元素为 6。
     *
     * @param
     * @return
     */
    public int findPeakElement(int[] nums) {

        if (nums.length <= 1) {
            return 0;
        }

        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                return i;
            }
        }


        return nums[0] > nums[nums.length - 1] ? 0 : nums.length - 1;

    }


    /**
     * 209. 长度最小的子数组
     * <p>
     * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 输入：s = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     *
     * @param
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {

        int res = Integer.MAX_VALUE;
        int left = 0;
        int preSum = 0;

        for (int right = 0; right < nums.length; right++) {
            preSum = preSum + nums[right];

            while (preSum >= s) {
                res = Math.min(res, right - left + 1);
                preSum = preSum - nums[left];
                left++;
            }

        }

        return res == Integer.MAX_VALUE ? 0 : res;

    }


    /**
     * 220. 存在重复元素 III todo  1.桶排序思路（重点体会） 2treeSet思路(待定) 注意整型的边界溢出问题
     * <p>
     * 在整数数组 nums 中，是否存在两个下标 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值小于等于 t ，且满足 i 和 j 的差的绝对值也小于等于 ķ 。
     * <p>
     * 如果存在则返回 true，不存在返回 false。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [1,2,3,1], k = 3, t = 0
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: nums = [1,0,1,1], k = 1, t = 2
     * 输出: true
     * 示例 3:
     * <p>
     * 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
     * 输出: false
     *
     * @param
     * @return
     */

    // Get the ID of the bucket from element value x and bucket width w
    // In Java, `-3 / 5 = 0` and but we need `-3 / 5 = -1`.
    private long getID(long x, long w) {
        return x < 0 ? (x + 1) / w - 1 : x / w;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0) return false;
        Map<Long, Long> d = new HashMap<>();
        long w = (long) t + 1;
        for (int i = 0; i < nums.length; ++i) {
            long m = getID(nums[i], w);
            // check if bucket m is empty, each bucket may contain at most one element
            if (d.containsKey(m))
                return true;
            // check the nei***or buckets for almost duplicate
            if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w)
                return true;
            if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w)
                return true;
            // now bucket m is empty and no almost duplicate in nei***or buckets
            d.put(m, (long) nums[i]);
            if (i >= k) d.remove(getID(nums[i - k], w));
        }
        return false;
    }


    /**
     * 289. 生命游戏 todo 有没有空间复杂度o1的算法？？
     * <p>
     * 根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
     * <p>
     * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
     * <p>
     * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
     * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
     * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
     * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
     * 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 输入：
     * [
     *   [0,1,0],
     *   [0,0,1],
     *   [1,1,1],
     *   [0,0,0]
     * ]
     * 输出：
     * [
     *   [0,0,0],
     *   [1,0,1],
     *   [0,1,1],
     *   [0,1,0]
     * ]
     *
     * @param
     * @return
     */
    public void gameOfLife(int[][] board) {

    }


    /**
     * 274. H 指数 todo  有个排序更优的解法
     * <p>
     * <p>
     * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。
     * <p>
     * h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。（其余的 N - h 篇论文每篇被引用次数 不超过 h 次。）
     * <p>
     * 例如：某人的 h 指数是 20，这表示他已发表的论文中，每篇被引用了至少 20 次的论文总共有 20 篇。
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 输入：citations = [3,0,6,1,5]
     * 输出：3
     * 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
     *      由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。
     *  
     * <p>
     * 提示：如果 h 有多种可能的值，h 指数是其中最大的那个。
     *
     * @param
     * @return
     */

    public int hIndex(int[] citations) {
        if (citations.length == 0) {
            return 0;
        }

        int[] counts = new int[citations.length + 1];


        for (int i = 0; i < citations.length; i++) {

            for (int j = 1; j <= Math.min(citations[i], counts.length - 1); j++) {
                counts[j] = counts[j] + 1;
            }
        }

        int res = 0;
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] >= i) {
                res = i;
            }
        }

        return res;




    }


    /**
     * 164. 最大间距 todo 桶排序思想（为啥相邻桶的差值一定大于桶内差值，抽屉原理是啥）
     * <p>
     * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
     * <p>
     * 如果数组元素个数小于 2，则返回 0。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [3,6,9,1]
     * 输出: 3
     * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
     * 示例 2:
     * <p>
     * 输入: [10]
     * 输出: 0
     * 解释: 数组元素个数小于 2，因此返回 0。
     * 说明:
     * <p>
     * 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
     * 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
     *
     * @param
     * @return
     */
    public int maximumGap(int[] nums) {

        return 0;

    }


    /**
     * 349. 两个数组的交集
     * 给定两个数组，编写一个函数来计算它们的交集。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2]
     * 示例 2：
     * <p>
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[9,4]
     *
     * @param
     * @return
     */

    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0;
        int j = 0;

        List<Integer> res = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {


            while (i + 1 < nums1.length && nums1[i] == nums1[i + 1]) {
                i++;
            }

            while (j + 1 < nums2.length && nums2[j] == nums2[j + 1]) {
                j++;
            }

            if (nums1[i] == nums2[j]) {
                res.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            }else {

                i++;
            }
        }

        int[] result = new int[res.size()];

        for(int m=0;m<res.size();m++){
            result[m] = res.get(m);
        }

        return result;

    }


    /**
     * 350. 两个数组的交集 II
     * 给定两个数组，编写一个函数来计算它们的交集。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2,2]
     * 示例 2:
     * <p>
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[4,9]
     *
     * @param
     * @return
     */

    public int[] intersect(int[] nums1, int[] nums2) {

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0;
        int j = 0;

        List<Integer> res = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {


            if (nums1[i] == nums2[j]) {
                res.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            }else {

                i++;
            }
        }

        int[] result = new int[res.size()];

        for(int m=0;m<res.size();m++){
            result[m] = res.get(m);
        }

        return result;


    }

}
