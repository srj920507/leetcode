import java.util.*;

public class NumberTest {

    public static void main(String[] args) {

        NumberTest numberTest = new NumberTest();

//        System.out.println(numberTest.fractionToDecimal(1,99));

        System.out.println(numberTest.isPowerOfFour(64));

        char[][] chara = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

//        numberTest.solveSudoku(chara);


    }


    /**
     * 字典序的第k小数组 todo 字节重点 有问题，溢出问题
     *
     * @param
     * @return
     */
    public int getDirectorySortK(int num, int k) {

        int res = 1;
        k = k - 1;
        while (k > 0) {
            int count = getCount(res, num);
            if (count > k) {
                res = res * 10;
                k--;
            } else {
                res++;
                k = k - count;
            }
        }

        return res;

    }


    private int getCount(int prefix, int target) {

        int pre = prefix;
        int next = pre + 1;
        int count = 0;
        while (pre <= target) {
            count += Math.min(target + 1, next) - pre;
            pre = pre * 10;
            next = next * 10;
        }
        return count;
    }


    /**
     * 两数相除
     *
     * @param
     * @return
     */
    public int divided(int num, int divideNum) {
        if (num == 0) {
            return 0;
        }
        int sign = 1;
        if ((num > 0 && divideNum < 0) || (num < 0 && divideNum > 0)) {
            sign = -1;
        }

        num = num > 0 ? num : -num;
        divideNum = divideNum > 0 ? divideNum : -divideNum;

        int res = getDividedRes(num, divideNum);

        return sign > 0 ? res : -res;

    }

    private int getDividedRes(int num, int divideNum) {
        if (num < divideNum) {
            return 0;
        }
        int res = 1;
        int newDivideNum = divideNum;
        while (num >= (newDivideNum + newDivideNum)) {
            res += res;
            newDivideNum = newDivideNum + newDivideNum;

        }

        return res + getDividedRes(num - newDivideNum, divideNum);

    }


    /**
     * 解数独 TODO 没做出来，下面错的
     *
     * @param
     * @return
     */
    public void solveSudoku(char[][] board) {

        Map<Integer, HashSet<Character>> vertical = new HashMap<>();
        Map<Integer, HashSet<Character>> historical = new HashMap<>();
        Map<Integer, HashSet<Character>> block = new HashMap<>();

        for (int i = 0; i < 9; i++) {
            vertical.put(i, new HashSet<>());
            historical.put(i, new HashSet<>());
            block.put(i, new HashSet<>());
        }


        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    vertical.get(i).add(board[i][j]);
                    historical.get(j).add(board[i][j]);
                    block.get(3 * (i / 3) + (j / 3)).add(board[i][j]);
                }
            }

        }

        recal(board, 0, 0, vertical, historical, block);


    }


    private boolean recal(char[][] board, int row, int cow, Map<Integer, HashSet<Character>> vertical, Map<Integer, HashSet<Character>> historical, Map<Integer, HashSet<Character>> block) {

        if (row == 8 && cow == 8) {
            return true;
        }

        if (row == 8) {
            row = 0;
        }

        for (int i = row; i < board.length; i++) {
            for (int j = cow; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    for (int num = 1; num <= 9; num++) {
                        char aa = (char) (num + 48);
                        if (!vertical.get(i).contains(aa) && !historical.get(j).contains(aa) &&
                                !block.get(3 * (i / 3) + (j / 3)).contains(aa)) {
                            board[i][j] = aa;
                            vertical.get(i).add(board[i][j]);
                            historical.get(j).add(board[i][j]);
                            block.get(3 * (i / 3) + (j / 3)).add(board[i][j]);
                            if (i < 8) {
                                if (recal(board, i + 1, j, vertical, historical, block)) {
                                    return true;
                                }
                            }

                            if (j < 8) {
                                if (recal(board, i, j + 1, vertical, historical, block)) {
                                    return true;
                                }
                            }


                            vertical.get(i).remove(aa);
                            historical.get(j).remove(aa);
                            block.get(3 * (i / 3) + (j / 3)).remove(aa);
                            board[i][j] = '.';

                        }


                    }
                }


            }


        }

        return false;

    }


    /**
     * 求 1+2+...+n //
     *
     * @param
     * @return
     */

    public int sum(int n) {
        int res = n;

        boolean a = n > 0 && (res = res + sum(n - 1)) > 0;

        return res;

    }


    /**
     * 格雷编码
     * 0 00  000
     * 1 01  001
     * 11  011
     * 10  010
     * 110
     * 111
     * 101
     * 100
     *
     * @param
     * @return
     */

    public List<Integer> greyEncode(int n) {

        List<Integer> res = new ArrayList<>();
        res.add(0);
        int head = 1;
        for (int i = 1; i <= n; i++) {
            int size = res.size();
            for (int j = size - 1; j >= 0; j--) {
                res.add(res.get(j) + head);
            }
            head <<= 1;

        }

        return res;

    }

    /**
     * 字符串相乘
     *
     * @param
     * @return
     */

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int[] nums = new int[num1.length() + num2.length()];

        for (int i = num1.length() - 1; i >= 0; i--) {

            for (int j = num2.length() - 1; j >= 0; j--) {
                int startPosition = num1.length() - 1 - i + num2.length() - 1 - j;
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + nums[startPosition];
                nums[startPosition] = product % 10;
                int next = product / 10;
                while (next > 0) {
                    startPosition++;
                    nums[startPosition] = (nums[startPosition] + next);
                    next = nums[startPosition] / 10;
                    nums[startPosition] = nums[startPosition] % 10;
                }
            }
        }

        StringBuilder str = new StringBuilder();

        if (nums[nums.length - 1] != 0) {
            str.append(nums[nums.length - 1]);
        }

        for (int i = nums.length - 2; i >= 0; i--) {
            str.append(nums[i]);
        }
        return str.toString();

    }


    /**
     * 剑指 Offer 62. 圆圈中最后剩下的数字 todo 感觉像个傻子。。。 还有个简单的方法
     * <p>
     * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
     * <p>
     * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入: n = 5, m = 3
     * 输出: 3
     * 示例 2：
     * <p>
     * 输入: n = 10, m = 17
     * 输出: 2
     *
     * @param
     * @return
     */

    public int lastRemaining(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        int index = 0;

        while (list.size() > 1) {

            index = (index + m - 1) % list.size();

            list.remove(index);


        }

        return list.get(0);


    }


    /**
     * 剑指 Offer 44. 数字序列中某一位的数字  todo 没考虑整型溢出
     * <p>
     * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
     * <p>
     * 请写一个函数，求任意第n位对应的数字。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 3
     * <p>
     * <p>
     * <p>
     * 输出：3
     * 示例 2：
     * <p>
     * 输入：n = 11
     * 输出：0
     *
     * @param
     * @return
     */

    public int findNthDigit(int n) {
        if (n == 0) {
            return 0;
        }

        long start = 1;
        long end = 9;
        int eachNumLength = 1;

        while (n > ((end - start + 1) * eachNumLength)) {
            n = n - (int)(end - start + 1)*eachNumLength;
            start = start * 10;
            end = end * 10 + 9;
            eachNumLength++;
        }
        //100101102
        int offset = (n - 1) / eachNumLength;

        long res = (start + offset);
        return Long.toString(res).charAt((n - 1) % eachNumLength) - '0';

    }

    public int rightFindNthDigit(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) { // 1.
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit; // 2.
        return Long.toString(num).charAt((n - 1) % digit) - '0'; // 3.
    }




    /**
     * 166. 分数到小数 todo -1 -2147483648  边界问题 "0.0000000004656612873077392578125"
     *
     * <p>
     * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以字符串形式返回小数。
     * <p>
     * 如果小数部分为循环小数，则将循环的部分括在括号内。
     * <p>
     * 示例 1:
     * <p>
     * 输入: numerator = 1, denominator = 2
     * 输出: "0.5"
     * 示例 2:
     * <p>
     * 输入: numerator = 2, denominator = 1
     * 输出: "2"
     * 示例 3:
     * <p>
     * 输入: numerator = 2, denominator = 3
     * 输出: "0.(6)"
     *
     * @param
     * @return
     */
    public String fractionToDecimal(int numerator, int denominator) {

        if (numerator == 0) {
            return String.valueOf(0);
        }

        String integer = String.valueOf(numerator / denominator);

        if (numerator % denominator == 0) {
            return integer;
        }


        StringBuilder xiaoShu = new StringBuilder();
        xiaoShu.append(".");

        Map<Integer, Integer> map = new HashMap<>();

        boolean isCircule = false;

        int index = 1;

        while (true) {

            numerator = numerator % denominator * 10;
            if(map.containsKey(numerator)){
                index = map.get(numerator);
                break;

            }

            xiaoShu.append(numerator / denominator);

            if (numerator % denominator == 0) {
                return integer + xiaoShu.toString();
            }
            map.put(numerator, index);
            index++;


        }


        String samll = xiaoShu.substring(0,index) + "("+xiaoShu.substring(index) + ")";

        return integer+samll;


    }

    /**
     * 204. 计数质数
     * 统计所有小于非负整数 n 的质数的数量。
     * <p>
     * 示例:
     * <p>
     * 输入: 10
     * 输出: 4
     * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
     *
     * @param
     * @return
     */
    public int countPrimes(int n) {
        boolean[] noPrime = new boolean[n + 1];

        for (int i = 2; i*i < n; i++) {
            if (noPrime[i]) {
                continue;
            }
            int index = 2;
            while (i * index < n) {
                noPrime[i * index] = true;
                index++;
            }
        }

        int res = 0;
        for (int i = 2; i <n; i++) {
            if (!noPrime[i]) {
                res++;
            }
        }

        return res;


    }


    /**
     * 223. 矩形面积 todo
     * <p>
     * 在二维平面上计算出两个由直线构成的矩形重叠后形成的总面积。
     * <p>
     * 每个矩形由其左下顶点和右上顶点坐标表示，如图所示。
     * <p>
     * Rectangle Area
     * <p>
     * 示例:
     * <p>
     * 输入: -3, 0, 3, 4, 0, -1, 9, 2
     * 输出: 45
     * 说明: 假设矩形面积不会超出 int 的范围。
     *
     * @param
     * @return
     */

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {

        return 0;

    }


    /**
      * 258. 各位相加 todo 感觉o(1)时间复杂度应该跟脑筋急转弯一样，得看看
     *
     * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
     *
     * 示例:
     *
     * 输入: 38
     * 输出: 2
     * 解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
     * 进阶:
     * 你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？
      *
      * @param
      * @return
      */

    public int addDigits(int num) {
        if(num/10<1){
            return num;
        }

        int newNum =0;
        while (num>0){
            newNum = newNum+num%10;
            num=num/10;
        }

        return addDigits(newNum);
    }


    /**
      * 264. 丑数 II
     *
     * 编写一个程序，找出第 n 个丑数。
     *
     * 丑数就是质因数只包含 2, 3, 5 的正整数。
     *
     * 示例:
     *
     * 输入: n = 10
     * 输出: 12
     * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
     * 说明:  
     *
     * 1 是丑数。
     * n 不超过1690。
     *
      *
      * @param
      * @return
      */

    public int nthUglyNumber(int n) {
        if(n==1){
            return 1;
        }


        int[] res = new int[n];
        res[0]=1;


        int a=0; int b=0; int c=0;

        for(int i=1;i<res.length;i++){

            int chouA = 2*res[a];
            int chouB = 3*res[b];
            int chouC = 5*res[c];

            res[i] = Math.min(chouA, Math.min(chouB,chouC));

            if(res[i]==chouA){
                a++;
            }

            if(res[i]==chouB){
                b++;
            }

            if(res[i]==chouC){
                c++;
            }

        }



        return res[n-1];
    }



    /**
      * 470. 用 Rand7() 实现 Rand10()  todo  注意里面的思想，小数模拟大数时的方法（现在写的跟背出来的一样）
     *
     * 已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。
     *
     * 不要使用系统的 Math.random() 方法。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: 1
     * 输出: [7]
     * 示例 2:
     *
     * 输入: 2
     * 输出: [8,4]
     * 示例 3:
     *
     * 输入: 3
     * 输出: [8,1,10]
     *
      *
      * @param
      * @return
      */

    /**
     * The rand7() API is already defined in the parent class SolBase.
     * public int rand7();
     * @return a random integer in the range 1 to 7
     */

    public int rand10() {


       while (true){
           int res = (rand7()-1)*7+rand7();

           if(res<=40){
               return 1+res%10;
           }

           res = (res-40-1)*7 + rand7();

           if(res<=60){
               return 1+res%10;
           }

           res  = (res-60-1)*7+ rand7();
           if(res<=20){
               return 1+res%10;
           }


       }

    }

    private int rand7(){

        return 7;

    }



    /**
      * 357. 计算各个位数不同的数字个数 todo 写了动态规划的 回溯法不想写了
     *
     * 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。
     *
     * 示例:
     *
     * 输入: 2
     * 输出: 91
     * 解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
     *
      *
      * @param
      * @return
      */

    public int countNumbersWithUniqueDigits(int n) {

       if(n==0){
           return 1;
       }

       int pre = 10;
       int next=9;
       int i=1;
       while (i<n){
           next = next*(10-i);
           pre = pre+next;
           i++;
       }

       return pre;

    }


    /**
      * 342. 4的幂 todo  错了  检查一个数是否为 2 的幂：x > 0 and x & (x - 1) == 0
     *
     * 给定一个整数 (32 位有符号整数)，请编写一个函数来判断它是否是 4 的幂次方。
     *
     * 示例 1:
     *
     * 输入: 16
     * 输出: true
     * 示例 2:
     *
     * 输入: 5
     * 输出: false
     *
      *
      * @param
      * @return
      */


    public boolean isPowerOfFour(int num) {

        if(num==1){
            return true;
        }

        int start = 4;

        if(num%start!=0){
            return false;
        }

        while (num%(start*start) == 0){

          start = start*start;
        }

        return isPowerOfFour(num/(start));


    }

    /**
      * todo 4的幂 牛逼
      *
      * @param
      * @return
      */
    public boolean rightisPowerOfFour(int num) {
        return (num > 0) && ((num & (num - 1)) == 0) && ((num & 0xaaaaaaaa) == 0);
    }





    /**
      * 137. 只出现一次的数字 II  todo
     *
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
     *
     * 说明：
     *
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * 示例 1:
     *
     * 输入: [2,2,3,2]
     * 输出: 3
     * 示例 2:
     *
     * 输入: [0,1,0,1,0,1,99]
     * 输出: 99
     *
      *
      * @param
      * @return
      */

    public int singleNumber(int[] nums) {

        return 0;

    }


    /**
      * 365. 水壶问题 todo
     * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
     *
     * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
     *
     * 你允许：
     *
     * 装满任意一个水壶
     * 清空任意一个水壶
     * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
     * 示例 1: (From the famous "Die Hard" example)
     *
     * 输入: x = 3, y = 5, z = 4
     * 输出: True
     * 示例 2:
     *
     * 输入: x = 2, y = 6, z = 5
     * 输出: False
      *
      * @param
      * @return
      */

    public boolean canMeasureWater(int x, int y, int z) {

        return true;

    }


}
