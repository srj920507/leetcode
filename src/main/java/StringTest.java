import java.util.*;

public class StringTest {

    private static Map<Integer, String> map = new LinkedHashMap<Integer, String>() {
        {
            put(1000, "M");
            put(900,"CM");
            put(500,"D");
            put(400,"CD");
            put(100,"C");
            put(90,"XC");
            put(50,"L");
            put(40,"XL");
            put(10,"X");
            put(9,"IX");
            put(5,"V");
            put(4,"IV");
            put(1,"I");


        }
    };



    public static void main(String[] args) {

        StringTest dpTest = new StringTest();

        List<String> res = dpTest.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");

        for(String a:res){
            System.out.println(a);
        }

        System.out.println(dpTest.isIsomorphic("ab","ca"));


    }


    /**
     * 最长有效括号（这个方法纯抄的，不太懂）
     *
     * @param
     * @return
     */

    public int getValidBracketLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }

    /**
     * 质数乘积
     *
     * @param
     * @return
     */
    public String getPrimeProduct(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(num).append("=");
        int i = 2;
        while (num > 1) {
            if (num % i == 0) {
                num = num / i;
                stringBuilder.append(i).append("*");
            } else {
                i++;
            }

        }

        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();


    }


    /**
     * 翻转字符串里的单词（这个写的有缺陷和bug）
     *
     * @param
     * @return
     */
    public String reverWords(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }

        int start = 0;
        int end = str.length();
        while (str.charAt(start) == ' ') {
            start++;
        }

        while (str.charAt(end - 1) == ' ') {
            end--;
        }

        str = str.substring(start, end);

        Deque<String> deque = new LinkedList<>();

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                stringBuilder.append(str.charAt(i));
            } else {
                if (stringBuilder.length() > 0) {
                    deque.addFirst(stringBuilder.toString());
                    stringBuilder.setLength(0);
                }
            }

            if (i == str.length() - 1 && stringBuilder.length() > 0) {
                deque.addFirst(stringBuilder.toString());
                stringBuilder.setLength(0);
            }
        }

        String ans = String.join(" ", deque);

        return ans;


    }


    public boolean isHuiWenString(String str) {

        if (str == null || str.length() <= 1) {
            return true;
        }

        boolean hasDeleted = false;

        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) == str.charAt(right)) {
                left++;
                right--;
            } else {

                int tempLeft = left;
                int tempRight = right;
                if (hasDeleted) {

                    return false;
                }

                if (str.charAt(tempLeft) == str.charAt(tempRight - 1)) {
                    right--;
                    hasDeleted = true;
                    continue;
                } else if (str.charAt(tempLeft + 1) == str.charAt(tempRight)) {
                    left++;
                    hasDeleted = true;
                    continue;
                } else {
                    return false;
                }

            }

        }

        return true;

    }


//    public String Num2RomaNum(int num){
//        if(num==0){
//            return "";
//        }
//        StringBuilder stringBuilder = new StringBuilder();
//
//        while (num>0){
//
//            for()
//
//
//
//        }
//
//
//
//    }


    public List<String> recoverIp(String num){
        List<String> res = new ArrayList<>();

       recoverIpByStep(num, 0, num.length(), new ArrayList<>(),res);

       return res;
    }


    private void recoverIpByStep(String num,int start,int length, List<String> list, List<String> res){

        if(list.size() == 3 ){

           if( Long.valueOf(num.substring(start,length)) >=0  && Long.valueOf(num.substring(start,length)) <=255){

               StringBuilder stringBuilder = new StringBuilder();
               for(String ip:list){
                   stringBuilder.append(ip).append(".");
               }
               stringBuilder.append(num.substring(start,length));
               res.add(stringBuilder.toString());
           }

            return;
        }

        for(int i= start+1; i<length;i++){

            if(Long.valueOf(num.substring(start,i)) >=0  && Long.valueOf(num.substring(start,i)) <=255 ){
                list.add(num.substring(start,i));
                recoverIpByStep(num, i, length, list, res);
                list.remove(list.size()-1);
            }


        }


    }


    /**
      * 71.简化路径
      *  in: /a/../../b/../c//.//
      *  out: /c
      * @param
      * @return
      */
    public String simplifyPath(String path) {
        String[] strs = path.split("/");
        Stack<String> stack = new Stack<>();
        for(int i=0; i<strs.length; i++){
            if(strs[i].equals("") || strs[i].equals(".")){
                continue;
            }
            if(strs[i].equals("..") && !stack.isEmpty()){
                stack.pop();
            }
            if(!strs[i].equals("..")){
                stack.push(strs[i]);
            }

        }

        if(stack.isEmpty()){
            return "/";
        }
        StringBuilder strBuilder = new StringBuilder();
        for(int i=0; i<stack.size(); i++){
            strBuilder.append("/").append(stack.get(i));
        }
        return strBuilder.toString();

    }


    /**
      * 187. 重复的DNA序列 todo 看下更为高级的用法
     *
     * 所有 DNA 都由一系列缩写为 A，C，G 和 T 的核苷酸组成，例如：“ACGAATTCCG”。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
     *
     * 编写一个函数来查找目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
     *
     *  
     *
     * 示例：
     *
     * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
     * 输出：["AAAAACCCCC", "CCCCCAAAAA"]
     *
      *
      * @param
      * @return
      */

    public List<String> findRepeatedDnaSequences(String s) {

        Map<String, Integer> map = new HashMap<>();

        for(int i=0; i<s.length()-10;i++){
            String sub = s.substring(i,i+10);
            map.put(sub,map.getOrDefault(sub,0)+1);
        }

        List<String> res = new ArrayList<>();
        for(String key:map.keySet()){
            if(map.get(key)>1){
               res.add(key);
            }

        }

        return res;

    }

    /**
      * 205. 同构字符串 todo 还有种思路，可以建立第三种映射（把两个字符串都翻译成中间语言，看看翻译完是否相等）
     *
     *
     * 给定两个字符串 s 和 t，判断它们是否是同构的。
     *
     * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
     *
     * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
     *
     * 示例 1:
     *
     * 输入: s = "egg", t = "add"
     * 输出: true
     * 示例 2:
     *
     * 输入: s = "foo", t = "bar"
     * 输出: false
     * 示例 3:
     *
     * 输入: s = "paper", t = "title"
     * 输出: true

      *
      * @param
      * @return
      */

    public boolean isIsomorphic(String s, String t) {
        Map<Character, Integer> mapS = new HashMap<>();
        Map<Character, Integer> mapT = new HashMap<>();
        if(s.length() != t.length()){
            return false;
        }

        for(int i=0;i<s.length();i++){
            char schar = s.charAt(i);
            char tchar = t.charAt(i);

            //都存在，查询上一个的位置是不是一样
            if(mapS.containsKey(schar) && mapT.containsKey(tchar)){
                if(mapS.get(schar) != mapT.get(tchar)){
                    return false;
                }

            }else if(mapS.containsKey(schar) || mapT.containsKey(tchar)){
                return false;
            }


            mapS.put(schar,i);
            mapT.put(tchar,i);

        }


        return true;

    }


    /**
      * 242. 有效的字母异位词
     *
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     *
     * 示例 1:
     *
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     * 示例 2:
     *
     * 输入: s = "rat", t = "car"
     * 输出: false
     * 说明:
     * 你可以假设字符串只包含小写字母。
     *
     * 进阶:
     * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
     *
      *
      * @param
      * @return
      */

    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        char[] sChar=  s.toCharArray();
        char[] tChat = t.toCharArray();
        Arrays.sort(sChar);
        Arrays.sort(tChat);

        for(int i=0;i<sChar.length;i++){
            if(sChar[i] != tChat[i]){
                return false;
            }
        }

        return true;

    }

    /**
      * 150. 逆波兰表达式求值 todo
     * 根据 逆波兰表示法，求表达式的值。
     *
     * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
     *
     *  
     *
     * 说明：
     *
     * 整数除法只保留整数部分。
     * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
     *  
     *
     * 示例 1：
     *
     * 输入: ["2", "1", "+", "3", "*"]
     * 输出: 9
     * 解释: 该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
     * 示例 2：
     *
     * 输入: ["4", "13", "5", "/", "+"]
     * 输出: 6
     * 解释: 该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
     * 示例 3：
     *
     * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
     * 输出: 22
     * 解释:
     * 该算式转化为常见的中缀算术表达式为：
     *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
     * = ((10 * (6 / (12 * -11))) + 17) + 5
     * = ((10 * (6 / -132)) + 17) + 5
     * = ((10 * 0) + 17) + 5
     * = (0 + 17) + 5
     * = 17 + 5
     * = 22
     *  
     *
     * 逆波兰表达式：
     *
     * 逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。
     *
     * 平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 ) 。
     * 该算式的逆波兰表达式写法为 ( ( 1 2 + ) ( 3 4 + ) * ) 。
     * 逆波兰表达式主要有以下两个优点：
     *
     * 去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
     * 适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。
     *
      *
      * @param
      * @return
      */

    public int evalRPN(String[] tokens) {
        return 0;

    }
}
