import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @description 各类区间问题
 * @author: sunrenjie
 * @date: 2019/12/05
 */
public class IntervalTest {


    public static void main(String[] args) {

        int[][] nums = {{1,5},{2,4}, {3,6}, {4,9}};
//        [10,16], [2,8], [1,6], [7,12] 1 5   2 4  3 6   4 9
        int[] newInterval = {4,8};
        IntervalTest test = new IntervalTest();

        System.out.println(test.findMinArrowShots(nums));
    }


    /**
      * 56. 合并区间  todo 里面的预发在白板编程时不太熟悉
     *
     * 给出一个区间的集合，请合并所有重叠的区间。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出: [[1,6],[8,10],[15,18]]
     * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * 示例 2:
     *
     * 输入: intervals = [[1,4],[4,5]]
     * 输出: [[1,5]]
     * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
     * 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。
     *
     *  
     *
     * 提示：
     *
     * intervals[i][0] <= intervals[i][1]
     *
      *
      * @param
      * @return
      */

    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> res = new ArrayList<>();

        int i = 0;


        while(i<intervals.length){
            int left = intervals[i][0];
            int right = intervals[i][1];

            while(i+1 < intervals.length && intervals[i+1][0]<=right){
                right= Math.max(right, intervals[i+1][1]);
                i++;
            }

            int[] temp = new int[2];
            temp[0] = left;
            temp[1] =right;
            res.add(temp);
            i++;
        }

        // int[][] resArray = new int[res.size()][2];

        // for(int j=0;i<res.size();j++){
        //     resArray[j] = res.get(j);
        // }

        return res.toArray(new int[res.size()][]);

    }

    /**
      * 57. 插入区间 todo  看看更优雅的写法，自己打了各种丑陋补丁
     *
     * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
     *
     * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
     * 输出：[[1,5],[6,9]]
     * 示例 2：
     *
     * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     * 输出：[[1,2],[3,10],[12,16]]
     * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
     *
      *
      * @param
      * @return
      */

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        boolean hasinsert = false;
        int i=0;


        while (i<intervals.length){
            int left;
            int right;

            if(newInterval[0]<=intervals[i][1]  && newInterval[1] >=intervals[i][0] && !hasinsert){

                left = Math.min(newInterval[0],intervals[i][0] );
                right = Math.max(newInterval[1], intervals[i][1]);

                //这里都是被吃掉和被归并的区间
                while (i+1<intervals.length && intervals[i+1][0]<=right){
                    right =Math.max(right, intervals[i+1][1]);
                    i++;
                }

                hasinsert = true;
            }else{
                left = intervals[i][0];
                right = intervals[i][1];
            }

            int[] temp = new int[2];
            temp[0]= left;
            temp[1]= right;
            res.add(temp);
            i++;
        }

        if(intervals.length==0 || !hasinsert){
            res.add(newInterval);
        }

        res.sort((o1,o2)->o1[0]-o2[0]);


        return res.toArray(new int[res.size()][]);

    }

    /**
      * 452. 用最少数量的箭引爆气球
     *
     * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以y坐标并不重要，因此只要知道开始和结束的x坐标就足够了。开始坐标总是小于结束坐标。平面内最多存在104个气球。
     *
     * 一支弓箭可以沿着x轴从不同点完全垂直地射出。在坐标x处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
     *
     * Example:
     *
     * 输入:
     * [[10,16], [2,8], [1,6], [7,12]]
     *
     * 1 5   2 4  3 6   4 9
     *
     * 输出:
     * 2
     *
     * 解释:
     * 对于该样例，我们可以在x = 6（射爆[2,8],[1,6]两个气球）和 x = 11（射爆另外两个气球）。
     *
      *
      * @param
      * @return
      */

    public int findMinArrowShots(int[][] points) {

       Arrays.sort(points, (o1,o2)->o1[1]-o2[1]);

        int res=0;

        int i = 0;
        while(i<points.length){
            res++;
            int left = points[i][0];
            int right = points[i][1];
            while(i+1 < points.length && points[i+1][0]<=right){
                i++;
            }
            i++;
        }

        return res;

    }

    /**
      * 上题另一写法
      *
      * @param
      * @return
      */
    public int newfindMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;

        // sort by x_end
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int arrows = 1;
        int xStart, xEnd, firstEnd = points[0][1];
        for (int[] p : points) {
            xStart = p[0];
            xEnd = p[1];
            // if the current balloon starts after the end of another one,
            // one needs one more arrow
            if (firstEnd < xStart) {
                arrows++;
                firstEnd = xEnd;
            }
        }

        return arrows;
    }



    /**
     * 228. 汇总区间
     *
     * 给定一个无重复元素的有序整数数组，返回数组区间范围的汇总。
     *
     * 示例 1:
     *
     * 输入: [0,1,2,4,5,7]
     * 输出: ["0->2","4->5","7"]
     * 解释: 0,1,2 可组成一个连续的区间; 4,5 可组成一个连续的区间。
     * 示例 2:
     *
     * 输入: [0,2,3,4,6,8,9]
     * 输出: ["0","2->4","6","8->9"]
     * 解释: 2,3,4 可组成一个连续的区间; 8,9 可组成一个连续的区间。
     *
     *
     * @param
     * @return
     */

    public List<String> summaryRanges(int[] nums) {

        List<String> res = new ArrayList<>();

        int left=0;
        int right=1;
        for(;right<nums.length;right++){
            if(nums[right]-nums[right-1]!=1){
                if(right-1 == left){
                    res.add(nums[left]+"");
                }else{
                    res.add(nums[left]+"->"+nums[right-1]);
                }
                left=right;
            }
        }


        right=right-1;
        if(right == left){
            res.add(nums[left]+"");
        }else{
            res.add(nums[left]+"->"+nums[right]);
        }


        return res;

    }
}
