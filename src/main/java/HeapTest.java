import java.util.*;

public class HeapTest {

    public static void main(String[] args) {

        int[] nums = {1,0,0,0,1,2,3,3,3};
        HeapTest arrayTest = new HeapTest();

//        int[] res = arrayTest.calculateProduct(nums);
//
//        for (int i = 0; i < res.length; i++) {
//            System.out.print(res[i] + " ");
//        }

//        int[]  res = arrayTest.maxNumInWindownK(nums,2);
//        for (int i = 0; i < res.length; i++) {
//            System.out.print(res[i] + " ");
//        }

        System.out.println(arrayTest.getFrequenceTopK(nums,4));

    }



    /**
      * 获取出现频率前k的数字
      *
      * @param
      * @return
      */
    public  List<Integer>  getFrequenceTopK(int[] nums, int k){

        List<Integer> res = new ArrayList<>();
        if(nums.length == 0){
            return res;
        }

        Map<Integer,Integer> counter = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            counter.put(nums[i],counter.getOrDefault(nums[i],0)+1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> counter.get(o1) - counter.get(o2));

        for(Integer key: counter.keySet()){
            heap.offer(key);
            if(heap.size()>k){
                heap.poll();
            }

        }

        while (!heap.isEmpty()){
            res.add(heap.poll());
        }

        Collections.reverse(res);

        Integer[] a = new Integer[res.size()];

        return res;


    }



}
