import java.util.HashMap;
import java.util.Map;

public class TrieTree {

    private TreeNode root = new TreeNode();


    public static void main(String[] args) {

        TrieTree trieTree = new TrieTree();
        trieTree.addString("abcde");
        trieTree.addString("acdfg");

        System.out.println(trieTree.contain("abcde"));

        System.out.println(trieTree.contain("abcd"));

        System.out.println(trieTree.prefix("abcd"));

        System.out.println(trieTree.contain("acdfg"));

        System.out.println(trieTree.prefix("am"));
    }


    public void addString(String str){
        if(str == null){
            return;
        }
        TreeNode cur = root;
        for(int i=0;i<str.length();i++){
            if(cur.childs.containsKey(str.charAt(i))){
                cur = cur.childs.get(str.charAt(i));
            }else{
                TreeNode node = new TreeNode();
                cur.childs.put(str.charAt(i),node);
                cur = node;
            }

        }
        cur.leaf = true;

    }

    private boolean contain(String str){
        if(str == null || str.length() == 0){
            return  false;
        }
        TreeNode cur = root;
        for(int i= 0; i<str.length();i++){
            if(cur.childs.containsKey(str.charAt(i))){
                cur = cur.childs.get(str.charAt(i));
            }else{
                return false;
            }

        }

        return cur.leaf;

    }


    private boolean prefix(String str){
        if(str == null || str.length() == 0){
            return  false;
        }
        TreeNode cur = root;
        for(int i= 0; i<str.length();i++){
            if(cur.childs.containsKey(str.charAt(i))){
                cur = cur.childs.get(str.charAt(i));
            }else{
                return false;
            }

        }

        return true;

    }



    class TreeNode{

        private Map<Character, TreeNode> childs = new HashMap<>();
        private boolean leaf;

    }



}
