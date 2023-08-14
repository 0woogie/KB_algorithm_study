package study;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[][] maps) {
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        int n = maps.length; //게임 맵 행 개수
        int m = maps[0].length; //게임 맵 열 개수
        
        //bfs 메서드를 정의하지 않고 solution 메서드 안에서 구현
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0,0}); //캐릭터 시작 위치
        while(!queue.isEmpty()){
            int[] tmp = queue.poll();
            int x = tmp[0];
            int y = tmp[1];
            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
                if(maps[nx][ny]==1){
                    maps[nx][ny] += maps[x][y]; //최단거리 계산
                    queue.add(new int[] {nx, ny});
                }
            }
        }
        if(maps[n-1][m-1]!=1) return maps[n-1][m-1]; //캐릭터가 상대 팀 진영 도달했으면 최단 거리 반환
        else return -1; //도달하지 못하는 경우 -1 반환
    }
}