package com.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16236_아기상어 {
	static int N, shark_X, shark_Y, sharkSize = 2, time = 0, eatCount = 0;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); //공간의 크기 입력받기
        map = new int[N][N]; //공간 map

        for(int i = 0; i<N; i++){ //입력받은 N만큼 반복
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j<N; j++){ //입력받은 N만큼 반복
                map[i][j] = Integer.parseInt(st.nextToken()); //공간의 상태 입력받기
                
                if(map[i][j] == 9){ //아기 상어 위치
                    shark_X = i;
                    shark_Y = j;
                    map[i][j] = 0; //빈 칸
                }
            }
        }

        int timeSum = 0;
        
        //부모가 옮기지 않아도 되면 계속 반복하기~~
        while(sharkGo())
            timeSum += time;

        System.out.println(timeSum); //아기 상어가 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는 시간 출력
    }

    static boolean sharkGo(){
        
        if(eatCount == sharkSize){ //자신의 크기와 같은 수의 물고기를 먹으면 몸의 크기 증가
            eatCount = 0;
            sharkSize++;
        }

        boolean[][] visited = new boolean[N][N]; //방문 확인

        Queue<Node> q = new LinkedList<>(); // BFS 위한 큐 생성
        q.offer(new Node(shark_X, shark_Y, 0)); //초기 위치인 shark_X와 shark_Y를 큐에 넣고 방문 표시
        visited[shark_X][shark_Y] = true;

        //물고기 위치
        int minRow = Integer.MAX_VALUE; //시간이 동일할 때 가장 윗 줄에 있는지 확인
        int minCol = Integer.MAX_VALUE; //시간이 동일할 때 row가 같을 때 가장 왼쪽에 위치하고 있는지 확인
        int minTime = Integer.MAX_VALUE; //아기 상어에서부터 걸리는 시간이 같은지를 확인

        while(!q.isEmpty()){
            Node a = q.poll();
            
            //최소 시간으로 물고기를 먹을 수 있는 시간을 넘으면 종료
            if(a.time >= minTime)
                break;

            for(int i = 0; i<4; i++){ //아기 상어가 상하좌우 4방향으로 이동
                int dr = a.row + dx[i]; //이동한 새로운 위치
                int dc = a.col + dy[i];
                
                //다음 이동 방향으로 넘어가는 경우 3가지
                if(dr<0||dc<0||dr>=N||dc>=N) //공간을 벗어난 경우, 다음 이동 방향으로 넘어감
                    continue;
                if(visited[dr][dc]) //이미 방문한 위치인 경우, 다음 이동 방향으로 넘어감
                    continue;
                if(map[dr][dc] > sharkSize) //물고기의 크기가 아기 상어보다 큰 경우, 다음 이동 방향으로 넘어감
                    continue;

                //아기 상어가 먹을 수 있는 물고기가 있는 칸에 들어오는 경우
                if(map[dr][dc]>0 && map[dr][dc]<sharkSize){ //아기 상어가 먹을 수 있는 물고기인 경우 
                    if(dr < minRow){
                        minRow = dr;
                        minCol = dc;
                        minTime = a.time + 1; //최소 시간 업데이트
                    }
                    else if(dr == minRow){ //가장 윗 줄에 위치한 물고기인 경우
                        if(dc < minCol){
                            minCol = dc;
                            minTime = a.time + 1; //최소 시간 업데이트
                        }
                    }
                }

                q.offer(new Node(dr, dc, a.time+1)); //새로운 위치에 있는 물고기를 큐에 추가
                visited[dr][dc] = true; //방문 위치 표시
            }
        }

        if(minTime == Integer.MAX_VALUE) //-> 더 이상 먹을 수 있는 물고기가 없음
            return false;
        
        else { //아기 상어의 위치를 최소 시간의 물고기가 있는 위치로 업데이트
        	shark_X = minRow; 
        	shark_Y = minCol;
            eatCount++;
            time = minTime; //아기 상어가 물고기까지 이동하는 데 걸린 시간
            map[shark_X][shark_Y] = 0; //아기 상어가 먹은 물고기의 위치를 0으로 변경

            return true;
        }
    }
}
class Node{
    int row, col, time;
    
    //새로운 물고기의 위치와 시간을 큐에 추가
    Node(int row, int col, int time){
        this.row = row;
        this.col = col;
        this.time = time;
    }
}
