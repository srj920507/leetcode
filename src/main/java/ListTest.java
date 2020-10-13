

public class ListTest {

    static class ListNode {

        public ListNode(int val) {
            this.val = val;
        }

        int val;

        ListNode next;

    }

    public static void main(String[] args) {

        int[] nums = {0, -1, -2, -1, 1, 1, 1};

        //1-2-3-4-5
        ListNode head = new ListNode(1);
        head.next = new ListNode(5);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(-1);
//        head.next.next.next.next.next = new ListNode(6);
        ListTest listTest = new ListTest();

        ListNode res = listTest.sortList(head);

        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }

    }

    /**
     * 是否回文链表
     *
     * @param
     * @return
     */
    public boolean isHuiwenList(ListNode head) {


        if (head == null || head.next == null) {
            return true;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;
        while (fast != null && fast.next != null) {
            fast = fast.next;
            fast = fast.next;
            ListNode next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;

        }

        if (fast != null) {
            slow = slow.next;
        }

        while (pre != null && slow != null) {
            if (pre.val != slow.val) {
                return false;
            }
            pre = pre.next;
            slow = slow.next;
        }

        return true;

    }


    /**
     * 是否回文链表
     *
     * @param
     * @return
     */
    public ListNode isCycleList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return null;
        }

        fast = head;

        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;

    }


    /**
     * 奇偶链表
     *
     * @param
     * @return
     */
    public ListNode oddAndEvenList(ListNode head) {

        ListNode odd = head;
        ListNode oddHead = head;

        ListNode even = head.next;
        ListNode evenHead = head.next;

        while (even != null && even.next != null) {
            odd.next = even.next;
            even.next = odd.next.next;
            odd = odd.next;
            even = even.next;

        }

        odd.next = evenHead;

        return oddHead;

    }


    /**
     * k链表排序
     *
     * @param
     * @return
     */
    public ListNode sortK(ListNode[] listNodes) {
        if (listNodes == null || listNodes.length == 0) {
            return null;
        }

        return sortK(listNodes, 0, listNodes.length - 1);

    }

    private ListNode sortK(ListNode[] listNodes, int left, int right) {
        if (left == right) {
            return listNodes[left];
        }

        int mid = left + (right - left) / 2;

        ListNode a = sortK(listNodes, left, mid);
        ListNode b = sortK(listNodes, mid + 1, right);

        return merge(a, b);

    }


    private ListNode merge(ListNode a, ListNode b) {
        ListNode dummp = new ListNode(0);
        ListNode cur = dummp;

        while (a != null && b != null) {
            if (a.val < b.val) {
                cur.next = a;
                a = a.next;

            } else {
                cur.next = b;
                b = b.next;
            }

            cur = cur.next;
        }

        if (a != null) {
            cur.next = a;
        }

        if (b != null) {
            cur.next = b;
        }

        return dummp.next;
    }


    public ListNode reSortList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dumpNode = new ListNode(0);
        dumpNode.next = head;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

        }
//        if(fast == null){
//            slow = slow.next;
//        }

        ListNode pre = null;
        while (slow != null) {
            ListNode temp = slow.next;
            slow.next = pre;
            pre = slow;
            slow = temp;
        }

        while (head != null && head.next != null && pre != null && pre.next != null) {
            ListNode temp = head.next;
            ListNode tempTail = pre.next;
            head.next = pre;
            pre.next = temp;
            head = temp;
            pre = tempTail;

        }

        return dumpNode.next;


    }


    public ListNode reverserList(ListNode head) {

        ListNode pre = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }

        return pre;


    }


    /**
     * 旋转链表
     * 1->2->3->null
     * 3-1-2
     * 2-3-1
     * 1-2-3
     * 3-1-2
     *
     * @param
     * @return
     */

    public ListNode rollList(ListNode head, int k) {

        ListNode cur = head;
        int length = 0;
        //先变成循环链表
        while (cur.next != null) {
            length++;
            cur = cur.next;
        }
        cur.next = head;
        length++;

        k = length - k % length;
        //1-2-3-4-5
        cur = head;
        while (k > 1) {
            cur = cur.next;
            k--;
        }
        head = cur.next;
        cur.next = null;

        return head;


    }


    /**
     * 82. 删除排序链表中的重复元素 II todo(自己虽然做出来了，但是有更好的双指针法可以再尝试尝试)
     * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->2->3->3->4->4->5
     * 输出: 1->2->5
     * 示例 2:
     * <p>
     * 输入: 1->1->1->2->3
     * 输出: 2->3
     *
     * @param
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode dump = new ListNode(0);
        ListNode index = null;
        dump.next = index;

        boolean canAdd = true;
        ListNode cur = head;
        head = head.next;
        while (head != null) {
            if (head.val == cur.val) {
                canAdd = false;
            } else {
                if (canAdd) {
                    index = cur;
                    index = index.next;
                }
                cur = head;
                canAdd = true;
            }
            head = head.next;
        }

        if (index != null) {
            index.next = null;
        }

        return dump.next;

    }


    /**
     * 86. 分隔链表 TODO 有点简单 想找自信时可以做这个
     * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
     * <p>
     * 你应当保留两个分区中每个节点的初始相对位置。
     * <p>
     * 示例:
     * <p>
     * 输入: head = 1->4->3->2->5->2, x = 3
     * 输出: 1->2->2->4->3->5
     *
     * @param
     * @return
     */

    public ListNode partition(ListNode head, int x) {
        return null;

    }


    /**
     * 148. 排序链表 todo 自底向上的方式待准备
     * <p>
     * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 4->2->1->3
     * 输出: 1->2->3->4
     * 示例 2:
     * <p>
     * 输入: -1->5->3->4->0
     * 输出: -1->0->3->4->5
     *
     * @param
     * @return
     */

    public ListNode sortList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        pre.next = null;

        ListNode head1 = sortList(head);
        ListNode head2 = sortList(slow);

        ListNode dump = new ListNode(0);
        ListNode newHead = dump;

        dump.next = newHead;

        while (head1 != null && head2 != null) {
            if (head1.val > head2.val) {
                newHead.next = head2;
                head2 = head2.next;
            } else {
                newHead.next = head1;
                head1 = head1.next;
            }
            newHead = newHead.next;
        }

        if (head1 != null) {
            newHead.next = head1;
        }

        if (head2 != null) {
            newHead.next = head2;
        }

        return dump.next;


    }

    /**
     * 剑指 Offer 35. 复杂链表的复制  todo deepclone
     *
     * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
     *
     *  
     *
     * 示例 1：
     *
     *
     *
     * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
     * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
     * 示例 2：
     *
     *
     *
     * 输入：head = [[1,1],[2,1]]
     * 输出：[[1,1],[2,1]]
     * 示例 3：
     *
     *
     *
     * 输入：head = [[3,null],[3,0],[3,null]]
     * 输出：[[3,null],[3,0],[3,null]]
     * 示例 4：
     *
     * 输入：head = []
     * 输出：[]
     * 解释：给定的链表为空（空指针），因此返回 null。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param
     * @return
     */


    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


    public Node copyRandomList(Node head) {

        return null;

    }

}
