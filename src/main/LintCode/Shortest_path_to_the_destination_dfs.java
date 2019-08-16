import org.junit.Test;

/**
 * @author wanglong
 * @brief
 * @date 2019-08-15 22:55
 */

/**
 * 深度优先遍历
 */

public class Shortest_path_to_the_destination_dfs {
    /**
     * @param targetMap:
     * @return: nothing
     */
    public int shortestPath(int[][] targetMap) {
        // Write your code here
        if(targetMap == null || targetMap.length == 0)
            return 0;
        int[] len = new int[1];  //记录当前(i,j)到达节点2需要的步数
        if(getDes(targetMap, len,0,0))
            return len[0];
        else
            return -1;

    }

    private boolean getDes(int[][] targetMap, int[] pathLen, int i ,int j){
        if(i < 0 || i >= targetMap.length)
            return false;
        if(j < 0 || j>= targetMap[0].length)
            return false;
        if(targetMap[i][j] ==1)
            return false;
        if(targetMap[i][j] == 2){  //如果当前节点已经为2，从当前节点到当前节点的距离为0
            pathLen[0] = 0;
            return true;
        }
        targetMap[i][j] = 1; //bfs中，当前路径中已经遍历过的点 不可再继续加入该路径
        int tmpLen = 1000000000;
        if(getDes(targetMap,pathLen,i,j+1))
            tmpLen = tmpLen<pathLen[0]?tmpLen:pathLen[0];
        if(getDes(targetMap,pathLen,i,j-1))
            tmpLen = tmpLen<pathLen[0]?tmpLen:pathLen[0];
        if(getDes(targetMap,pathLen,i-1,j))
            tmpLen = tmpLen<pathLen[0]?tmpLen:pathLen[0];
        if(getDes(targetMap,pathLen,i+1,j))
            tmpLen = tmpLen<pathLen[0]?tmpLen:pathLen[0];
        if(tmpLen==1000000000){
            return false;
        }else{
            pathLen[0] = tmpLen + 1;
            targetMap[i][j] = 0;  //当前路径遍历完成后，回退
            return true;
        }
    }

    @Test
    public void test(){
        int[][] test_matrix_2 = {{0,0,0},{0,0,1},{0,0,2}};
        int[][] test_matrix_3 = {{0,0,1,0,1,0,1},{1,0,1,0,1,1,1},{1,0,0,0,1,0,0},{0,0,0,0,0,0,2}};
        System.out.println(shortestPath(test_matrix_2));
    }

}


//dfs需要不断向下递归，沿着路线A-> B-> C-> D->E 到达E之后回退到D，然后D-> F。注意深度遍历中的回退时，相应visited标识位要恢复。

//当递归深度过大时，容易发生堆栈溢出，建议bfs