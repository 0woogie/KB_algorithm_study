package algorithm.Team04;

import java.util.*;

class Solution {
    
    int[] dx = {1, 0, -1, 0}; //4방 탐색을 위한 배열 만들기
    int[] dy = {0, 1, 0, -1};
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        int[][] visited = new int[maps.length][maps[0].length]; //방문체크를 위한 배열만들기
        visited[0][0] = 1; //시작 위치를 1로 만들어서 방문체크
        
        bfs(maps, visited); //bfs 탐색
        answer = visited[maps.length-1][maps[0].length-1]; //도착지값 넣기
        
        if(answer == 0){ //만약 0이라서 갈 수 없다면 -1 리턴
            answer = -1;
        }
        
        return answer;
    }
    
    public void bfs(int[][] maps, int[][] visited){
    	Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int cX = current[0];
            int cY = current[1];
            
            for(int i = 0; i < 4; i++){ //4방 탐색
                int nX = cX + dx[i]; //이동했을 때의 위치
                int nY = cY + dy[i];
                
                if(nX >= 0 && nX < maps.length && nY >= 0 && nY < maps[0].length && visited[nX][nY] == 0 && maps[nX][nY]==1) { //범위 벗어나는지 체크
                	visited[nX][nY] = visited[cX][cY] + 1; //방문했는지, 갈 수 있는지 체크 -> 체크해서 문제 없으면 해당 위치까지 방문한 수 +1
                    queue.add(new int[] {nX, nY}); //큐에 넣기
                }
             }
         }      
     }   
}