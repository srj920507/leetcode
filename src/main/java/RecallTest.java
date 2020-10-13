import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @description 回溯算法在此
 * @author sunrenjie
 * @date 2020-07-07
 */
public class RecallTest {
    int res;

    public static void main(String[] args) {

        int[] nums = {1, 1 ,2 ,2,3};
        RecallTest recall = new RecallTest();

        String test = "catsandog";

        String[] words = new String[]{"cat", "cats", "and", "sand", "dog"};

//
//        List<String> res = recall.splitStrToWords(test,words);
//        for (int i = 0; i < res.size(); i++) {
//            System.out.println(res.get(i));
//        }

        System.out.println(recall.combinationSum3(1,7));

    }


    /**
      * 单词拆分II
      *
      * @param
      * @return
      */
    public List<String> splitStrToWords(String str, String[] words){

        List<String> res = new ArrayList<>();

        split(str, words, res, new ArrayList<String>());

        return res;

    }


    private void split(String str, String[] words,List<String> res, List<String> wordList ){

        if(str.length()==0){
            StringBuilder stringBuilder = new StringBuilder();
            for(String word:wordList){
               stringBuilder.append(word+" ");
            }
            res.add(stringBuilder.toString().trim());
            return;
        }

        for(String word:words){
            if(str.startsWith(word)){
                wordList.add(word);
                split(str.substring(word.length(),str.length()),words, res, wordList);
                wordList.remove(wordList.size()-1);
            }
        }


    }


    public List<List<Integer>>findContinuousSequence(int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(target <=2){
            return res;
        }

        List<Integer> nums = new ArrayList<>();

        recall(1,res,nums,target);

        return res;


    }

    private void recall( int minNum, List<List<Integer>> res , List<Integer> nums,int left){
        if(left == 0){
            res.add(new ArrayList<>(nums));
            return;
        }

        if(left<minNum){
            return;
        }

        for(int i=minNum; i<=left;i++){
            nums.add(i);
            recall(i+1, res, nums, left-i);
            nums.remove(nums.size()-1);
        }



    }


    /**
     * 47. 全排列 II  对理解回溯的减枝很有帮助  TODO
     *
     * 输入: [1,1,2]
     * 输出:
     * [
     *   [1,1,2],
     *   [1,2,1],
     *   [2,1,1]
     * ]
      *
      * @param
      * @return
      */



    /**
      * 60. 第k个排列  todo 待提交 自己做出个乱七八糟套路（再看看常规套路怎么写的）
     * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
     *
     * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
     *
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * 给定 n 和 k，返回第 k 个排列。
     *
      *
      * @param
      * @return
      */

    public String getPermutation(int n, int k) {

            boolean[] used = new boolean[n];
            int length = n-1;
            int start =1;

            StringBuilder stringBuilder = new StringBuilder();

            while (k>0){

                if(stringBuilder.length() == n){
                    break;
                }

              for(int i=start; i<=n;i++){

                  if(used[i-1]){
                      continue;
                  }
                  int count = getNjie(length);
                  if(count>=k){
                      used[i-1]=true;
                      stringBuilder.append(i);
                      length = length -1;
                      break;

                  }else{
                      k = k-count;
                  }
              }


            }

            return stringBuilder.toString();




    }


    private int getNjie(int length){

        if(length<=1){
            return 1;
        }

        int res = 1;
        for(int i=1;i<=length; i++){
            res  = res*i;
        }
        return res;
    }



    /**
      * 526. 优美的排列 todo(回溯算法做出来了，但是还有个动态规划的没看)
      *
      * @param
      * @return
      */
    public int countArrangement(int N) {

        int[] nums = new int[N];
        for(int i=0;i<nums.length;i++){
            nums[i]=i+1;
        }
        boolean[] used = new boolean[N];
        res = 0;
        dfs(nums,1,used);
        return res;


    }

    private void dfs(int[] nums, int elementNum, boolean[] used){
        if(elementNum > nums.length){
            res =res+1;
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(used[i] || (nums[i]%(elementNum)!=0 && (elementNum)%nums[i]!=0)){
                continue;
            }
            used[i]=true;
            dfs(nums,elementNum+1,used);
            used[i]=false;
        }
    }


    /**
      * 473.火柴拼正方形 TODO   [5,5,5,5,16,4,4,4,4,4,3,3,3,3,4] 超时了
      * 1 1 2 2 2 true
     *  3 3 3 3 4 false
      * @param
      * @return
      */

    public boolean makeSquare(int[] nums){
        if(nums.length<4){
            return false;
        }

        int sum = 0;
        for(int num:nums){
            sum = sum + num;
        }

        if(sum%4 != 0){
            return false;
        }
        int sideLength = sum/4;

        boolean[] uesd = new boolean[nums.length];

        return makeSquareDfs(nums,uesd, sideLength,4,0);


    }


    private boolean makeSquareDfs(int[]nums, boolean[] used, int sideLength, int leftNeed,int currentLength){
        if(leftNeed == 0){
            return true;
        }

        for(int i=0;i<nums.length; i++){

            if(used[i]){
                continue;
            }
            if(currentLength+nums[i] > sideLength){
                continue;
            }

            used[i] = true;
            if(currentLength+nums[i] == sideLength){
                if(makeSquareDfs(nums, used, sideLength, leftNeed-1, 0)){
                    return true;
                }
            }else{
                if(makeSquareDfs(nums,used,sideLength,leftNeed,currentLength+nums[i])){
                    return true;
                }
            }

            used[i] = false;

        }
        return false;

    }


    /**
      * 306. 累加数 todo  "121474836472147483648" 错误
     *
     * 累加数是一个字符串，组成它的数字可以形成累加序列。
     *
     * 一个有效的累加序列必须至少包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
     *
     * 给定一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是累加数。
     *
     * 说明: 累加序列里的数不会以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
     *
     * 示例 1:
     *
     * 输入: "112358"
     * 输出: true
     * 解释: 累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
     * 示例 2:
     *
     * 输入: "199100199"
     * 输出: true
     * 解释: 累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
     *

      *
      * @param
      * @return
      */

    public boolean isAdditiveNumber(String num) {
        if(num.length()<3){
            return false;
        }
        List<Integer> resList = new ArrayList<>();

       return   dfs4splitIntoFibonacci(num,resList,0);

    }


    /**
      * 842. 将数组拆分成斐波那契序列
     *
     * 给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。
     *
     * 形式上，斐波那契式序列是一个非负整数列表 F，且满足：
     *
     * 0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
     * F.length >= 3；
     * 对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
     * 另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
     *
     * 返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。
     *

      *
      * @param
      * @return
      */

    public List<Integer> splitIntoFibonacci(String s) {
        List<Integer> resList = new ArrayList<>();
        if(s.length()<3){
            return resList;
        }
        dfs4splitIntoFibonacci(s,resList,0);
        return resList;


    }


    private boolean dfs4splitIntoFibonacci(String s,List<Integer> res,int position){

        if(position == s.length() && res.size()>=3){
            return true;
        }

        for(int i=position; i<s.length(); i++){

            String subString = s.substring(position, i+1);
            if(subString.length()>1 && subString.startsWith("0")){
                break;
            }

            long value = Long.valueOf(subString);
            if(value > Integer.MAX_VALUE){
                break;
            }

            int newValue = (int)value;
            if(isFeiQiNaBo(res, newValue)){

                res.add(newValue);
                if(dfs4splitIntoFibonacci(s,res,i+1)){
                    return true;
                }


                res.remove(res.size()-1);



            }

        }


        return false;




    }

    private boolean isFeiQiNaBo(List<Integer> res, int value){

        if(res.size()<2){
            return true;
        }

        if(res.get(res.size()-1)+ res.get(res.size()-2) == value){
            return true;
        }

        return false;

    }


    /**
      * 216. 组合总和 III
     * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     *
     * 说明：
     *
     * 所有数字都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1:
     *
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     * 示例 2:
     *
     * 输入: k = 3, n = 9
     * 输出: [[1,2,6], [1,3,5], [2,3,4]]
     *
      *
      * @param
      * @return
      */

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs4Sum(n,k,new ArrayList<>(), res, 1);
        return res;
    }


    private void dfs4Sum(int target, int left, List<Integer> list,List<List<Integer>> res, int start){

        if(left == 0 ){

            if(target==0){
                res.add(new ArrayList<>(list));
            }

            return;
        }

        for(int i=start; i<=9;i++){

            if(target<i){
                break;
            }

            list.add(i);
            dfs4Sum(target-i, left-1, list, res, i+1);
            list.remove(list.size()-1);

        }


    }


}
