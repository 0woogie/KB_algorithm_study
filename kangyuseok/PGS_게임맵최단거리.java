import java.util.*;
import java.io.*;
class Solution {
    int []dx = {0, 1, -1, 0};
    int []dy = {1, 0, 0, -1};
    int answer = 0;
    public int solution(int[][] maps) {
        boolean [][]visited = new boolean[maps.length][maps[0].length];
        Queue<pair>q = new LinkedList<>();
        q.add(new pair(0, 0, 1));
        int answer = 0;
        visited[0][0]=true;
        while(!q.isEmpty()){
            int x = q.peek().x;
            int y = q.peek().y;
            int z = q.peek().z;
            //System.out.println(x+" "+y+" "+z);
            q.poll(); 
            if(x ==maps.length-1 && y == maps[0].length-1){
                answer = z;
                break;
            }
            for(int i=0;i<4;i++){
                int xx = x + dx[i];
                int yy = y + dy[i];
                if(xx<0||yy<0 || xx>=maps.length||yy>=maps[0].length )continue;
                if(visited[xx][yy])continue;
                if(maps[xx][yy]==0)continue;
                visited[xx][yy]=true;
                q.add(new pair(xx, yy, z+1));
            }
        }
        if(answer == 0)answer=-1;
        return answer;
    }
}
class pair{
    int x, y, z;
    pair(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
}