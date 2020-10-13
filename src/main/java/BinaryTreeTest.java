import java.util.*;


/**
 * @author sunrenjie
 * @description 二叉树相关题解
 * @date 2020-07-06
 */
public class BinaryTreeTest {

    int res = 0;

    static class TreeNode {
        public TreeNode(int val) {
            this.val = val;
        }

        int val;
        TreeNode left;
        TreeNode right;

    }


    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    ;


    public static void main(String[] args) {


        int[] nums = {0, -1, -2, -1, 1, 1, 1};

        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        BinaryTreeTest arrayTest = new BinaryTreeTest();


        ListNode head = new ListNode(-10);
        head.next = new ListNode(-3);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(9);

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);


        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);


        root.right.right = new TreeNode(7);


        TreeNode res = arrayTest.sortedListToBST(head);

        System.out.println(arrayTest.diffWaysToCompute("2-1-1"));


    }


    /**
     * 不同二叉搜索树I
     *
     * @param
     * @return
     */
    public int getBinaryTreeNum(int n) {
        if (n <= 1) {
            return 1;
        }

        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum = sum + getBinaryTreeNum(i - 1) * getBinaryTreeNum(n - i);
        }
        return sum;

    }

    /**
     * 不同二叉搜索树II
     *
     * @param
     * @return
     */
    public List<TreeNode> getBinaryTreeNumII(int n) {
        return buildBinaryTree(1, n);

    }


    /**
     * 不同二叉搜索树II
     *
     * @param
     * @return
     */
    private List<TreeNode> buildBinaryTree(int start, int end) {
        List<TreeNode> res = new ArrayList<>();

        if (start > end) {
            res.add(null);
            return res;
        }

        for (int i = start; i <= end; i++) {
            TreeNode root = new TreeNode(i);
            List<TreeNode> lefts = buildBinaryTree(start, i - 1);
            List<TreeNode> rights = buildBinaryTree(i + 1, end);
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }


        }

        return res;


    }


    /**
     * 路径总和
     *
     * @param
     * @return
     */
    public boolean isEqualSumRout(TreeNode root, int target) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return root.val == target;
        }

        return isEqualSumRout(root.left, target - root.val) || isEqualSumRout(root.right, target - root.val);

    }


    /**
     * 二叉树最大宽度
     *
     * @param
     * @return
     */
    public int maxWidth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addFirst(root);

        int ans = 0;
        while (!deque.isEmpty()) {
            while (!deque.isEmpty() && deque.peekFirst() == null) {
                deque.removeFirst();
            }

            while (!deque.isEmpty() && deque.peekLast() == null) {
                deque.removeLast();
            }
            ans = Math.max(ans, deque.size());

            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.removeLast();
                if (node != null) {
                    deque.addFirst(node.left);
                    deque.addFirst(node.right);
                } else {
                    deque.addFirst(null);
                    deque.addFirst(null);
                }
            }


        }

        return ans;


    }

    /**
     * 二叉树前序遍历（迭代）
     *
     * @param
     * @return
     */
    public List<Integer> preView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return new ArrayList<>();
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }

            if (cur.left != null) {
                stack.push(cur.left);
            }
        }

        return res;
    }


    /**
     * 二叉树中序遍历
     *
     * @param
     * @return
     */
    public List<Integer> middleView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }

        return res;

    }


    /**
     * 二叉树后序遍历
     *
     * @param
     * @return
     */
    public List<Integer> postView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.peek();
            if (root.right == null || root.right == pre) {
                root = stack.pop();
                res.add(root.val);
                pre = root;
                root = null;
            } else {
                root = root.right;
            }

        }

        return res;

    }


    public List<Integer> getRightView(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean start = true;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (start) {
                    res.add(cur.val);
                    start = false;
                }

                if (cur.right != null) {
                    queue.add(cur.right);
                }

                if (cur.left != null) {
                    queue.add(cur.left);
                }
            }
        }
        return res;

    }


    /**
     * 二叉树最近公共祖先
     *
     * @param
     * @return
     */
    public TreeNode findNearestFather(TreeNode root, int child1, int child2) {

        if (root == null) {
            return null;
        }

        if (root.val == child1 || root.val == child2) {
            return root;
        }

        TreeNode left = findNearestFather(root.left, child1, child2);
        TreeNode right = findNearestFather(root.right, child1, child2);

        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }


    /**
     * 检查是否是完全二叉树(todo 待提交)
     *
     * @param
     * @return
     */
    public boolean checkFull(TreeNode root) {
        if (root == null) {
            return false;
        }

        boolean leftNullOccure = false;
        boolean rightNullOccure = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                if (rightNullOccure) {
                    return false;
                }
                queue.add(cur.left);
            } else {
                leftNullOccure = true;
            }

            if (cur.right != null) {
                if (leftNullOccure) {
                    return false;
                }
                queue.add(cur.right);
            } else {
                rightNullOccure = true;
            }
        }

        return true;

    }


    /**
     * 二叉树的堂兄弟节点
     *
     * @param
     * @return
     */
    public boolean isCousins(TreeNode root, int x, int y) {

        if (root.val == x || root.val == y) {
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {

            int size = queue.size();
            TreeNode fatherA = null;
            TreeNode fatherB = null;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.add(cur.left);
                    if (cur.left.val == x) {
                        fatherA = cur;
                    }
                    if (cur.left.val == y) {
                        fatherB = cur;
                    }
                }

                if (cur.right != null) {
                    queue.add(cur.right);
                    if (cur.right.val == x) {
                        fatherA = cur;
                    }
                    if (cur.right.val == y) {
                        fatherB = cur;
                    }
                }

                if (fatherA != null && fatherB != null && fatherA != fatherB) {
                    return true;
                }

            }

        }

        return false;
    }


    /**
     * 二叉树直径
     *
     * @param
     * @return
     */


    public int diameterOfBinaryTree(TreeNode root) {
        res = 0;
        maxDepth(root);
        return res;

    }


    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        res = Math.max(res, leftDepth + rightDepth);

        return 1 + Math.max(leftDepth, rightDepth);

    }


    /**
     * 二叉搜索树的第k大节点
     * <p>
     * 给定一棵二叉搜索树，请找出其中第k大的节点。
     * <p>
     * 示例 1:
     * <p>
     * 输入: root = [3,1,4,null,2], k = 1
     * 3
     * / \
     * 1   4
     * \
     *    2
     * 输出: 4
     * 示例 2:
     * <p>
     * 输入: root = [5,3,6,2,4,null,null,1], k = 3
     * 5
     * / \
     * 3   6
     * / \
     * 2   4
     * /
     * 1
     * 输出: 4
     *
     * @param
     * @return
     */

    int count;
    int resultNum;

    public int kthLargest(TreeNode root, int k) {

        count = k;
        dfs(root);
        return resultNum;


    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }

        dfs(root.right);
        count = count - 1;
        if (count == 0) {
            resultNum = root.val;
            return;
        }
        dfs(root.left);

    }


    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * <p>
     * 根据一棵树的中序遍历与后序遍历构造二叉树。
     * <p>
     * 注意:
     * 你可以假设树中没有重复的元素。
     * <p>
     * 例如，给出
     * <p>
     * 中序遍历 inorder = [9,3,15,20,7]
     * 后序遍历 postorder = [9,15,7,20,3]
     * reverse [3,20,7,15,9]
     * 返回如下的二叉树：
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     *
     * @param
     * @return
     */

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < postorder.length / 2; i++) {
            int temp = postorder[i];
            postorder[i] = postorder[postorder.length - 1 - i];
            postorder[postorder.length - 1 - i] = temp;
        }

        return buildChildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);

    }


    private TreeNode buildChildTree(int[] inorder, int inStart, int inEdn, int[] postorder, int pStart, int pEnd) {

        if (inStart > inEdn || pStart > pEnd) {
            return null;
        }

        int rootVal = postorder[pStart];
        TreeNode root = new TreeNode(rootVal);

        int leftLenght = 0;
        for (int i = inStart; i <= inEdn; i++) {
            if (inorder[i] == rootVal) {
                break;
            }
            leftLenght++;
        }

        TreeNode left = buildChildTree(inorder, inStart, inStart + leftLenght - 1, postorder, pEnd - leftLenght + 1, pEnd);

        TreeNode right = buildChildTree(inorder, inStart + leftLenght + 1, inEdn, postorder, pStart + 1, pEnd - leftLenght);

        root.left = left;
        root.right = right;

        return root;

    }

    /**
     * 116.填充每一个节点的右侧指针 todo 待测试
     * <p>
     * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
     * <p>
     * struct Node {
     * int val;
     * Node *left;
     * Node *right;
     * Node *next;
     * }
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     * <p>
     * 初始状态下，所有 next 指针都被设置为 NULL。
     * <p>
     *  
     *
     * @param
     * @return
     */

    public void linkRight(Node root) {

        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                //说明不是子节点
                if (cur.left != null) {
                    cur.left.next = cur.right;
                    if (cur.next != null) {
                        cur.right.next = cur.next.left;
                    }

                    queue.add(cur.left);
                    queue.add(cur.right);
                }


            }

        }


    }


    /**
     * 129. 求根到叶子节点数字之和
     * <p>
     * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
     * <p>
     * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
     * <p>
     * 计算从根到叶子节点生成的所有数字之和。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,2,3]
     * 1
     * / \
     * 2   3
     * 输出: 25
     * 解释:
     * 从根到叶子节点路径 1->2 代表数字 12.
     * 从根到叶子节点路径 1->3 代表数字 13.
     * 因此，数字总和 = 12 + 13 = 25.
     * 示例 2:
     * <p>
     * 输入: [4,9,0,5,1]
     * 4
     * / \
     * 9   0
     *  / \
     * 5   1
     * 输出: 1026
     * 解释:
     * 从根到叶子节点路径 4->9->5 代表数字 495.
     * 从根到叶子节点路径 4->9->1 代表数字 491.
     * 从根到叶子节点路径 4->0 代表数字 40.
     * 因此，数字总和 = 495 + 491 + 40 = 1026.
     *
     * @param
     * @return
     */
    int sum;

    public int sumNumbers(TreeNode root) {
        sum = 0;
        dfs4Sum(root, 0);
        return sum;
    }

    private void dfs4Sum(TreeNode root, int curSum) {

        if (root == null) {
            return;
        }

        int cur = (curSum) * 10 + root.val;

        if (root.left == null && root.right == null) {
            sum = sum + cur;
            return;
        }

        dfs4Sum(root.left, cur);
        dfs4Sum(root.right, cur);

    }


    /**
     * 222. 完全二叉树的节点个数 todo  自己写的方法压根没用到完全二叉树这个条件,看下正确答案
     * <p>
     * 给出一个完全二叉树，求出该树的节点个数。
     * <p>
     * 说明：
     * <p>
     * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
     * <p>
     * 示例:
     * <p>
     * 输入:
     * 1
     * / \
     * 2   3
     * / \  /
     * 4  5 6
     * <p>
     * 输出: 6
     *
     * @param
     * @return
     */

    public int countNodes(TreeNode root) {

        if (root == null) {
            return 0;
        }

        return 1 + countNodes(root.left) + countNodes(root.right);

    }


    /**
      * 二叉搜索树中第K小的元素 todo  如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化
     *
     * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
     *
     * 说明：
     * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
     *
     * 示例 1:
     *
     * 输入: root = [3,1,4,null,2], k = 1
     *    3
     *   / \
     *  1   4
     *   \
     *    2
     * 输出: 1
     * 示例 2:
     *
     * 输入: root = [5,3,6,2,4,null,null,1], k = 3
     *        5
     *       / \
     *      3   6
     *     / \
     *    2   4
     *   /
     *  1
     * 输出: 3
     * 进阶：
     * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
     *
      *
      * @param
      * @return
      */

    int smallCount;
    int smallRes;

    public int kthSmallest(TreeNode root, int k) {

       smallCount=k;
       smallRes=0;

       dfsForkthSmallest(root);
       return smallRes;

    }


    private void dfsForkthSmallest(TreeNode root){

        if(root==null){
            return;
        }

        dfsForkthSmallest(root.left);
        smallCount = smallCount-1;
        if(smallCount == 0){
            smallRes = root.val;
            return;
        }

        dfsForkthSmallest(root.right);

    }


    /**
      * 241. 为运算表达式设计优先级
     *
     * 给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。你需要给出所有可能的组合的结果。有效的运算符号包含 +, - 以及 * 。
     *
     * 示例 1:
     *
     * 输入: "2-1-1"
     * 输出: [0, 2]
     * 解释:
     * ((2-1)-1) = 0
     * (2-(1-1)) = 2
     * 示例 2:
     *
     * 输入: "2*3-4*5"  6 -1 20
     * 输出: [-34, -14, -10, -10, 10]
     * 解释:
     * (2*(3-(4*5))) = -34
     * ((2*3)-(4*5)) = -14
     * ((2*(3-4))*5) = -10
     * (2*((3-4)*5)) = -10
     * (((2*3)-4)*5) = 10
     *
      *
      * @param
      * @return
      */

    Map<String, List<Integer>> resultMap = new HashMap<>();
    public List<Integer> diffWaysToCompute(String input) {

        if(resultMap.containsKey(input)){
            return resultMap.get(input);
        }

       List<Integer> res = new ArrayList<>();
       if(input.length()==0){
           return res;
       }


       if(!input.contains("-") && !input.contains("+") && !input.contains("*")){
           res.add(Integer.valueOf(input));
           return res;
       }


       for(int i=0;i<input.length();i++){

           if(isOperation(input.charAt(i))){

               List<Integer> left = diffWaysToCompute(input.substring(0,i));
               List<Integer> right = diffWaysToCompute(input.substring(i+1, input.length()));

               for(Integer a: left){
                   for (Integer b: right){

                       res.add(calculate(a, b, input.charAt(i)));

                   }
               }

           }
       }
       resultMap.put(input, res);
       return res;

    }



    private boolean isOperation(char c){
        if(c=='-'){
            return true;
        }

        if(c=='+'){
            return true;
        }

        if(c=='*'){
            return true;
        }

        return false;

    }


    private int calculate(int a, int b, char c){

        if(c=='-'){
            return a-b;
        }

        if(c=='+'){
            return a+b;
        }

        if(c=='*'){
            return a*b;
        }

        return 0;

    }



    /**
      * 450. 删除二叉搜索树中的节点
     *
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     *
     * 一般来说，删除节点可分为两个步骤：
     *
     * 首先找到需要删除的节点；
     * 如果找到了，删除它。
     * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
     *
     * 示例:
     *
     * root = [5,3,6,2,4,null,7]
     * key = 3
     *
     *     5
     *    / \
     *   3   6
     *  / \   \
     * 2   4   7
     *
     * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
     *
     * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
     *
     *     5
     *    / \
     *   4   6
     *  /     \
     * 2       7
     *
     * 另一个正确答案是 [5,2,6,null,4,null,7]。
     *
     *     5
     *    / \
     *   2   6
     *    \   \
     *     4   7
     *
      *
      * @param
      * @return
      */
    public TreeNode deleteNode(TreeNode root, int key) {

        if(root==null){
            return null;
        }


        if(root.val == key){

            if(root.left!=null && root.right !=null){

                TreeNode newRoot = root.left;
                linkTree(newRoot, root.right, true);

                return newRoot;

            }

            if(root.left != null){
                return root.left;
            }

            if(root.right != null){
                return root.right;
            }

            return null;


        }

        if(root.val>key){

            root.left = deleteNode(root.left, key);
        }else{
            root.right = deleteNode(root.right,key);
        }

        return root;

    }



    private void linkTree(TreeNode root, TreeNode source, boolean isRight){

        TreeNode cur = root;
        TreeNode pre = null;
        while (cur!=null){
            pre = cur;
            if(isRight){
                cur = cur.right;
            }else{
                cur = cur.left;
            }
        }

        if(isRight){
            pre.right = source;
        }else {
            pre.left = source;
        }


    }


    /**
     * 331. 验证二叉树的前序序列化 todo
     *
     * 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
     *
     *      _9_
     *     /   \
     *    3     2
     *   / \   / \
     *  4   1  #  6
     * / \ / \   / \
     * # # # #   # #
     * 例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
     *
     * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
     *
     * 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
     *
     * 你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。
     *
     * 示例 1:
     *
     * 输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
     * 输出: true
     * 示例 2:
     *
     * 输入: "1,#"
     * 输出: false
     * 示例 3:
     *
     * 输入: "9,#,#,1"
     * 输出: false
     *
     * @param
     * @return
     */

    public boolean isValidSerialization(String preorder) {

        return true;

    }


    /**
      * 109. 有序链表转换二叉搜索树 todo 待测试看看
     *
     * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
     *
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     *
     * 示例:
     *
     * 给定的有序链表： [-10, -3, 0, 5, 9],
     *
     * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
     *
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     *
      * 1 -> 2 ->3 -> 4->5
      * @param
      * @return
      */




    static class ListNode {

        public ListNode(int val) {
            this.val = val;
        }

        int val;

        ListNode next;

    }
    public TreeNode sortedListToBST(ListNode head) {

        if(head==null ){
            return null;
        }

        if(head.next==null){
            return new TreeNode(head.val);
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;

        while (fast!=null && fast.next != null){
            pre=slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        TreeNode root = new TreeNode(slow.val);
        pre.next = null;

        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);

        return root;


    }





}
