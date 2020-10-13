import com.sun.xml.internal.ws.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DPTest {

    public static void main(String[] args) {

        DPTest dpTest = new DPTest();

//        int res = dpTest.getValidBracketLength("axaa","babxxxaa");

//        int[][] array = new int[][]{{0,1,1},{1,1,1},{0,0,0},{1,1,1}};

        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> aa = new ArrayList<>();
        aa.add(-1);
        List<Integer> bb = new ArrayList<>();
        bb.add(-2);
        bb.add(-3);
        triangle.add(aa);
        triangle.add(bb);

        int[] test = {2,3,2};
        System.out.print(dpTest.rob(test));

    }

    /**
     * 完全平方数
     *
     * @param
     * @return
     */
    //
    //    给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
    //
    //    示例 1:
    //
    //    输入: n = 12
    //    输出: 3
    //    解释: 12 = 4 + 4 + 4.
    public int squareNum(int num) {
        int size = 1;
        for (; size * size <= num; size++) {
        }

        int[] array = new int[size - 1];
        for (int j = 1; j <= array.length; j++) {
            array[j - 1] = j * j;
        }

        int[] dp = new int[num + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= num; i++) {
            for (int m = 0; m < array.length; m++) {
                if (i >= array[m]) {
                    dp[i] = Math.min(dp[i], dp[i - array[m]] + 1);
                }


            }
        }

        return dp[num];

    }


    /**
     * 编辑距离
     *
     * @param
     * @return
     */

    public int getValidBracketLength(String word1, String word2) {
        if (word1.equals(word2)) {
            return 0;
        }

        int length1 = word1.length();
        int length2 = word2.length();
        int[][] dp = new int[length1 + 1][length2 + 1];
        for (int i = 0; i <= length1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= length2; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                }

            }
        }

        return dp[length1][length2];

    }


    public int maxRectangle(int[][] array) {
        if (array.length == 0 || array[0].length == 0) {
            return 0;
        }

        int row = array.length;
        int cow = array[0].length;

        int[][] dp = new int[row + 1][cow + 1];

        int ans = 0;

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= cow; j++) {
                if (array[i - 1][j - 1] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    ans = Math.max(ans, dp[i][j] * dp[i][j]);
                }
            }
        }

        return ans;

    }


    public int maxValidPartternLength(String str) {
        if (str == null || str.length() <= 1) {
            return 0;
        }

        int[] dp = new int[str.length() + 1];
        int ans = 0;

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == ')') {
                if (str.charAt(i - 1) == '(') {
                    dp[i + 1] = dp[i - 1] + 2;
                } else if (i - dp[i] - 1 >= 0 && str.charAt(i - dp[i] - 1) == '(') {
                    dp[i + 1] = dp[i] + 2 + dp[i - dp[i] - 1];
                }
            }
            ans = Math.max(ans, dp[i + 1]);
        }

        return ans;

    }


    /**
     * @param
     * @return
     */
    public int buyShares(int prices[], int k, int fee) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        //0不持有 1持有
        int[][][] dp = new int[prices.length + 1][k + 1][2];


        return 0;

    }


//    /**
//     * 2次的
//     *
//     * @param
//     * @return
//     */
//    public int buyShares(int prices[]){
//        if(prices== null || prices.length <=1){
//            return 0;
//        }
//
//        int[][][] dp = new int[prices.length][3][2];
//
//        dp[0][0][0]= 0;
//        dp[0][1][0] = Integer.MIN_VALUE ;
//        dp[0][2][0]= Integer.MIN_VALUE;
//
//        dp[0][0][1]= -prices[0];
//        dp[0][1][1]= Integer.MIN_VALUE;
//        dp[0][2][1] = Integer.MIN_VALUE;
//
//        for(int i=0;i<prices.length;i++){
//            dp[i][0][0] = 0;
//            dp[i][0][1]
//        }
//
//        for(int i=1; i<prices.length; i++){
//            for(int j=1;j<2;j++){
//               dp[i][j][0] = Math.max(dp[i-1][j-1])
//
//            }
//
//
//
//
//        }
//
//
//
//
//
//
//    }


    public boolean regexMatch(String s, String p) {
        if (s == null && p == null) {
            return true;
        }
        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];

        dp[0][0] = true;

        for(int i=0; i<=s.length(); i++){

            for(int j=1;j<=p.length();j++){

                if(p.charAt(j-1) == '*'){

                    if(!canMatch(s, p, i-1, j-2)){
                      dp[i][j] = dp[i][j-2];
                    }else{
                        dp[i][j] = dp[i-1][j] || dp[i][j-2];
                    }

                }else{
                    if(canMatch(s, p , i-1, j-1)){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
            }

        }

        return dp[s.length()][p.length()];

    }


    private boolean canMatch(String s, String p, int i, int j){
        if(i<0 && j<0){
            return true;
        }
        if(i<0){
            return false;
        }
        if(p.charAt(j) == '.'){
            return true;
        }
        return s.charAt(i) == p.charAt(j);

    }

    /**
      * 三角形最小路径和
      *
      * @param
      * @return
      */

    public int minimumTotal(List<List<Integer>> triangle) {
        int length = triangle.get(triangle.size()-1).size();

        int[][] dp = new int[2][length];
        for(int m=0;m<triangle.size();m++){
            int size = triangle.get(m).size();
            for(int i=0;i<size;i++){
                if(i==0 ){
                    dp[1][i] = dp[0][i]+triangle.get(m).get(i);
                }else if(i == size-1 && i>1){
                    dp[1][i] = dp[0][i-1]+triangle.get(m).get(i);
                }else{

                    dp[1][i] = Math.min(dp[0][i-1], dp[0][i])+triangle.get(m).get(i);

                }

            }

            for(int j=0;j<length;j++){
                dp[0][j]=dp[1][j];
            }
        }
        int res = Integer.MAX_VALUE;
        for(int a: dp[1]){
          res=  Math.min(res, a);
        }

        return res;

    }


    /**
      * 91. 解码方法 todo 错了一万次，给各种情况打了补丁，说明自己写的这个本身就有问题，还要再看看标准简洁的答案
     * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
     *
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
     *
     * 示例 1:
     *
     * 输入: "226"
     * 输出: 3
     * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
     *

      *
      * @param
      * @return
      */
    public int numDecodings(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        if(s.startsWith("0")){
            return 0;
        }

        char[] chars = s.toCharArray();

        int[] dp = new int[s.length()+1];
        dp[0]= 1;
        dp[1]= s.startsWith("0")?0:1;
        for(int i=1; i<chars.length;i++){
            int num= (chars[i-1]-'0')*10 + (chars[i]-'0');
            if(num==0){
                return 0;
            }
            if(chars[i]=='0'){
                if(num<=26){
                    dp[i+1]=dp[i-1];
                    continue;
                }

                return 0;

            }

            if(chars[i-1]=='0'){
                dp[i+1]=dp[i];
                continue;

            }

            if(num<=26){
                dp[i+1]= dp[i]+dp[i-1];
            }else{
                dp[i+1]=dp[i];
            }
        }

        return dp[s.length()];

    }


    /**
      * 剑指 Offer 60. n个骰子的点数 todo (发现自己现在数学感觉是真差)
     *
     * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
     *
     * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
     *
     * 示例 1:
     *
     * 输入: 1
     * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
     * 示例 2:
     *
     * 输入: 2
     * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.1388
     *

      *
      * @param
      * @return
      */

    public double[] twoSum(int n) {
        double[] pre = new double[]{1/6d,1/6d,1/6d,1/6d,1/6d,1/6d };

        for(int i=2;i<=n;i++){
            double[] temp = new double[i*5+1];

           for(int j=0;j<pre.length;j++){
               for(int m=0;m<6;m++){
                   temp[j+m] += pre[j]/6;
               }
           }
        }
        return pre;
    }


    /**
      * 剑指 Offer 47. 礼物的最大价值  todo 很简单,可以用来找自信
     *
     * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
     *
     *  
     *
     * 示例 1:
     *
     * 输入:
     * [
     *   [1,3,1],
     *   [1,5,1],
     *   [4,2,1]
     * ]
     * 输出: 12
     * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
     *

      *
      * @param
      * @return
      */
    public int maxValue(int[][] grid) {

        return 0;

    }


    /**
      * 213. 打家劫舍 II todo 待测试 看看正确写法
     *
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
     *
     * 示例 1:
     *
     * 输入: [2,3,2]
     * 输出: 3
     * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
     * 示例 2:
     *
     * 输入: [1,2,3,1]
     * 输出: 4
     * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     *
      *
      * @param
      * @return
      */

    public int rob(int[] nums) {
        if(nums.length==0){
            return 0;
        }

        if(nums.length<=1){
            return nums[0];
        }

       int[] dp1 = new int[nums.length];
       dp1[0] = nums[0];
        dp1[1]=dp1[0];

       for(int i =2;i<nums.length;i++){
           dp1[i]=Math.max(dp1[i-2]+nums[i], dp1[i-1]);
       }

        int[] dp2 = new int[nums.length];

        dp2[1] = nums[1];
        for(int i =2;i<nums.length;i++){
            dp2[i]=Math.max(dp2[i-2]+nums[i], dp2[i-1]);
        }

        return Math.max(dp1[nums.length-2],dp2[nums.length-1]);

    }

    /**
      * 剑指 Offer 14- II. 剪绳子 II todo 待提交测试 120有问题 大数越界情况下的求余问题  看下 快速幂
     * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     *
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     *
     *  
     *
     * 示例 1：
     *
     * 输入: 2
     * 输出: 1
     * 解释: 2 = 1 + 1, 1 × 1 = 1
     * 示例 2:
     *
     * 输入: 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
     *

      *
      * @param
      * @return
      */

    public int cuttingRope(int n) {

        int mod = 1000000007;
        int[] dp = new int[n+1];
        dp[0]=1;
        dp[1]=1;

        for(int i=2; i<=n;i++){

            for(int j=1; j<i;j++){
                dp[i] = Math.max(dp[i],Math.max(dp[j]*(i-j), j*(i-j)));
            }

        }

        return dp[n]%mod;

    }



    /**
     * 343. 整数拆分 todo 暂时想出个复杂度比较高的，有没有低一些的？跟上面一模一样
     * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
     *
     * 示例 1:
     *
     * 输入: 2
     * 输出: 1
     * 解释: 2 = 1 + 1, 1 × 1 = 1。
     * 示例 2:
     *
     * 输入: 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
     *
     * @param
     * @return
     */



    public int integerBreak(int n) {

        int[] dp = new int[n+1];
        dp[0]=0;
        dp[1]=1;
        for(int i=2; i<=n;i++){

            for(int j=1;j<i;j++){

                dp[i] =  Math.max(dp[i], Math.max(dp[j]*(i-j),j*(i-j)));
            }

        }

        return dp[n];



    }
}
