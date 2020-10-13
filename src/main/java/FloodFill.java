import java.util.LinkedList;
import java.util.Queue;

public class FloodFill {

    public static void main(String[] args) {

        int[][] array = new int[][]{{0,0,1},{1,1,0},{0,0,0},{1,1,1}};
        FloodFill arrayTest = new FloodFill();

//        int[] res = arrayTest.calculateProduct(nums);
//
//        for (int i = 0; i < res.length; i++) {
//            System.out.print(res[i] + " ");
//        }

//        System.out.println( arrayTest.floodFill(array));
        System.out.println(array.length);
        System.out.println(array[0].length);

    }

    /**
      * 计算岛屿数量
      *
      * @param
      * @return
      */
    public int floodFill(int[][] array) {
      int row = array.length;
      int cow = array[0].length;

      int count = 0;
      for(int i=0;i<row;i++){
          for(int j=0;j<cow;j++){
              if(array[i][j] == 1){
                 count++;
                 bfs(array,i,j);
              }
          }
      }
      return count;
    }


    /**
      * 深度优先搜索
      *
      * @param
      * @return
      */
    private void dfs(int[][] array, int i, int j){
        int row = array.length;
        int cow = array[0].length;

        if(i<0 || j<0 || i>=row || j>= cow || array[i][j] == 0){
            return;
        }
        array[i][j]=0;
        dfs(array,i-1,j);
        dfs(array,i+1,j);
        dfs(array,i,j-1);
        dfs(array,i,j+1);

    }


    /**
      * 广度优先搜索
      *
      * @param
      * @return
      */
    private void bfs(int[][] array, int i, int j){
        int rowSize = array.length;
        int cowSize = array[0].length;
        int[][] direction = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        Queue<Integer> queue = new LinkedList<>();
        //这里这个i*10+j会有问题，如果j>10的情况下，就出问题了，要用i*rowSize+j类似这样的方式
        queue.add(i*10+j);
        while (!queue.isEmpty()){
            int num = queue.poll();
            int cow = num % 10;
            int row = num/10;
            array[row][cow] = 0;
            for(int m=0;m<direction.length;m++){
                int nextRow = row+direction[m][0];
                int nextCow = cow+direction[m][1];
                if(nextCow<0 || nextCow >=cowSize || nextRow >= rowSize || nextRow<0 || array[nextRow][nextCow] == 0){
                   continue;
                }
                queue.add(nextRow*10+nextCow);
            }
        }
    }

}
