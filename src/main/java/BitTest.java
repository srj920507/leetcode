import java.util.*;

public class BitTest {

    public static void main(String[] args) {

        int[] nums = {6,5,5,9,10,9,4,10};

        String [] words= {"abcw","baz","foo","bar","xtfn","abcdef"};
        BitTest arrayTest = new BitTest();

//        int[] res = arrayTest.calculateProduct(nums);
//
//        for (int i = 0; i < res.length; i++) {
//            System.out.print(res[i] + " ");
//        }

//        int[]  res = arrayTest.maxNumInWindownK(nums,2);
//        for (int i = 0; i < res.length; i++) {
//            System.out.print(res[i] + " ");
//        }

        System.out.println(arrayTest.maxProduct(words));

        System.out.println(arrayTest.rangeBitwiseAnd(9,24));



    }



    /**
      * 只出现一次的数字[2,2,1,3,3,5] 返回[1,5]
      *
      * @param
      * @return
      */
    public List<Integer> twoNumApperanceOnce(int[] nums){
        int xor = 0;
        for(int num:nums){
            xor ^= num;
        }
        System.out.println(xor);

        int partition = 1;
        while ( (xor&partition) == 0 ){
            partition<<=1;
        }

        int a=0;
        int b=0;
        for(int num:nums){
            if((num&partition) == partition){
                a= a^num;
            }else{
                b= b^num;
            }
        }

        List<Integer> res = new ArrayList<>();
        res.add(a);
        res.add(b);
        return res;


    }


    /**
      * 318. 最大单词长度乘积
     *
     * 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
     *
     * 示例 1:
     *
     * 输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
     * 输出: 16
     * 解释: 这两个单词为 "abcw", "xtfn"。
     * 示例 2:
     *
     * 输入: ["a","ab","abc","d","cd","bcd","abcd"]
     * 输出: 4
     * 解释: 这两个单词为 "ab", "cd"。
     * 示例 3:
     *
     * 输入: ["a","aa","aaa","aaaa"]
     * 输出: 0
     * 解释: 不存在这样的两个单词。
     *
     *
      *
      * @param
      * @return
      */
    public int maxProduct(String[] words) {
        if(words.length<=1){
            return 0;
        }
        int[] bits = new int[words.length];

        for(int j=0;j<words.length;j++){
            int value = 0;
            for(int i=0;i<words[j].length(); i++){
                value = value |  1<<(words[j].charAt(i)-'a');

            }
            bits[j] = value;
        }

        int res =0;

        for(int i=0;i<words.length;i++){

            for(int j=i+1; j<words.length;j++){
                if( (bits[i]&bits[j]) == 0){
                    res = Math.max(res, words[i].length()*words[j].length());
                }
            }
        }

        return res;





    }


    /**
      * 201. 数字范围按位与 todo 这种求公共前缀的思想要在看下
     *
     * 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。
     *
     * 示例 1: 
     *
     * 输入: [5,7]  101 111 1000 1001  ->100
     * 输出: 4
     * 示例 2:
     *
     * 输入: [0,1] 0 1 -> 0
     * 输出: 0
     *
      *
      * @param
      * @return
      */
    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;
        // find the common 1-bits
        while (m < n) {
            m >>= 1;
            n >>= 1;
            ++shift;
        }
        return m << shift;
    }




    /**
      * 338. 比特位计数 todo 看提示里都dp?
     *
     * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
     *
     * 示例 1:
     *
     * 输入: 2
     * 输出: [0,1,1]
     * 示例 2:
     *
     * 输入: 5
     * 输出: [0,1,1,2,1,2]
     * 进阶:
     *
     * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
     * 要求算法的空间复杂度为O(n)。
     * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
     *
      *
      * @param
      * @return
      */

    public int[] countBits(int num) {

        return null;

    }
















}
