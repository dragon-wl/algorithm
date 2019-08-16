import org.junit.Test;

import java.awt.*;
import java.util.*;

/**
 * @author wanglong
 * @brief
 * @date 2019-08-16 09:46
 */
public class Shortest_path_to_the_destination_bfs {

    public int shortestPath(int[][] targetMap) {
        if(targetMap == null || targetMap.length ==0)
            return -1;
        int[][] visited = new int[targetMap.length][targetMap[0].length];
        for(int i =0; i < targetMap.length; i ++)
            for(int j = 0; j < targetMap[0].length; j ++)
                visited[i][j] = -1;
        //BFS采用队列Queue存储节点
        Queue<Point> pointList = new LinkedList<Point>();
        pointList.offer(new Point(0,0));
        int lenRes = -1; //初始步数为0
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        //按照层次去遍历矩阵中的可达点
        while(!pointList.isEmpty()){
            int size = pointList.size();
            lenRes ++ ;
            //判断步数=lenRes时，分析可达节点有哪些
            for(int i = 0; i < size; i++){
                Point tmp = pointList.poll();
                if(tmp.x >= targetMap.length || tmp.y >= targetMap[0].length || tmp.x < 0 || tmp.y < 0)
                    continue;
                if(targetMap[tmp.x][tmp.y] == 1)
                    continue;
                if(targetMap[tmp.x][tmp.y] == 2)
                    return lenRes;
                for(int k = 0; k < 4; k ++){
                    if(tmp.x + dx[k] < targetMap.length && tmp.x + dx[k] >= 0
                            && tmp.y +dy[k] < targetMap[0].length && tmp.y +dy[k] >= 0
                            && targetMap[tmp.x + dx[k]][tmp.y +dy[k]] != 1
                            && visited[tmp.x + dx[k]][tmp.y +dy[k]] == -1){
                        pointList.add(new Point(tmp.x + dx[k],tmp.y + dy[k]));
                    }
                }
                visited[tmp.x][tmp.y] = 1;
                }
        }
        return -1;
    }

    @Test
    public void test(){
        int[][] test_matrix_2 = {{0,0,0},{0,0,1},{0,0,2}};
        int[][] test_matrix_3 = {{0,1},{0,1},{0,2}};
        System.out.println(shortestPath(test_matrix_2));
    }
}

//BFS：宽度优先遍历，采用队列Queue<Point> pointList = new LinkedList<>();
//入队条件：在矩阵范围内 && 从未访问过 && 是可以访问的点

//九章答案：https://www.jiuzhang.com/solution/shortest-path-to-the-destination/
