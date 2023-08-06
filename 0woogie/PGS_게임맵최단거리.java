package study;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[][] maps) {
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        int n = maps.length; //���� �� �� ����
        int m = maps[0].length; //���� �� �� ����
        
        //bfs �޼��带 �������� �ʰ� solution �޼��� �ȿ��� ����
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0,0}); //ĳ���� ���� ��ġ
        while(!queue.isEmpty()){
            int[] tmp = queue.poll();
            int x = tmp[0];
            int y = tmp[1];
            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
                if(maps[nx][ny]==1){
                    maps[nx][ny] += maps[x][y]; //�ִܰŸ� ���
                    queue.add(new int[] {nx, ny});
                }
            }
        }
        if(maps[n-1][m-1]!=1) return maps[n-1][m-1]; //ĳ���Ͱ� ��� �� ���� ���������� �ִ� �Ÿ� ��ȯ
        else return -1; //�������� ���ϴ� ��� -1 ��ȯ
    }
}