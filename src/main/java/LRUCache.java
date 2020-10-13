import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    public LRUCache(long capacity) {
        this.capacity = capacity;
    }

    public static void main(String[] args) {

        int[] nums = {1, 3, 2, 3, 4, 5, 6};
//        LRUCache arrayTest = new LRUCache();

    }


    private Map<String, LRUNode> map = new HashMap<>();

    private LRUNode head;

    private LRUNode tail;

    private long capacity;


    public int get(String key) {
        LRUNode node = map.get(key);
        if (node != null) {
            remove(node, false);
            setHead(node);
            return node.value;

        } else {
            return 0;
        }

    }


    public void set(String key, int value) {

        LRUNode node = map.get(key);

        if (node != null) {
            node.value = value;
            remove(node, false);
            setHead(node);
        } else {
            if(map.size()>capacity){
                remove(tail,true);
            }
            node = new LRUNode(key,value);
            map.put(key, node);
            setHead(node);
        }

    }


    private void remove(LRUNode node, boolean flag) {
        if(node.pre != null){
            node.pre.next = node.next;
        }

        if(node.next !=null){
            node.next.pre = node.pre;
        }

        if(node == head){
           head = node.next;
        }

        if(node == tail){
            tail = node.pre;
        }

        node.next = null;
        node.pre = null;

        if(flag){
            map.remove(node.key);
        }

    }


    private void setHead(LRUNode node) {

        if(head != null){
            node.next = head;
            head.pre = node;
        }

        head = node;

        if(tail == null){
            tail = node;
        }

    }


    class LRUNode {

        public LRUNode(String key, int value) {
            this.key = key;
            this.value = value;
        }

        String key;
        int value;
        LRUNode pre;
        LRUNode next;

    }


}
