import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class 연습용 {
    static boolean[][] visit;
    static int [][]map;
    static int []dy = {-1, 0, 1, 0};
    static int []dx = {0, -1, 0, 1};
    static int n;
    static int level = 2;
    static boolean eat; //먹었는지 체크
    static Tuple start; // 시작점
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        start = new Tuple(); //상어 시작 위치 저장

        for(int i=0;i<n;i++){ //맵 입력
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){ //상어 위치 저장
                    start.r = i;
                    start.c = j;
                    start.cost = 0;
                    map[i][j] = 0;
                }
            }
        }
        int exp = 0; //몇마리 먹었는지 세는 변수

        int answer=0;
        boolean flag = true;
        while(flag){
            //visit 배열 초기화
            visit = new boolean[n][n];
            start = bfs(start);
            if (eat) { //1마리 먹었을 경우
                eat=false;
                exp++;
                map[start.r][start.c]=0;
                answer += start.cost;
                start.cost=0;
                if(exp == level){
                    level++;
                    exp=0;
                }
            }
            else flag = false; //1마리도 못먹었을 경우
        }

        System.out.println(answer);
    }
    static Tuple bfs(Tuple start){
        Queue<Tuple>q = new LinkedList<>();
        q.add(start);
        visit[start.r][start.c]=true;

        Tuple nextStart = new Tuple();
        while(!q.isEmpty()){
            int y = q.peek().r;
            int x = q.peek().c;
            int cost = q.peek().cost;

            if(map[y][x] >0 && map[y][x]<level && cost == nextStart.cost){ // 가장 위쪽, 왼쪽 순
                if(y < nextStart.r || (y == nextStart.r && x < nextStart.c)) {
                    nextStart.r = y;
                    nextStart.c = x;
                    continue;
                }
            }
            q.poll();
            for(int i=0;i<4;i++){
                int yy = y + dy[i];
                int xx = x + dx[i];
                if(yy<0 || xx<0 ||yy>=n || xx>=n) continue; //맵을 벗어나는 경우
                if(map[yy][xx]>level)continue; //먹이를 먹을 수 없을 경우
                if(visit[yy][xx])continue; //이미 방문한 경우


                if(map[yy][xx]>0 && map[yy][xx]<level  && eat==false){ //먹을 수 있는 경우
                    eat = true;
                    nextStart.r = yy;
                    nextStart.c = xx;
                    nextStart.cost = cost+1;
                }
                else{ //이동만 가능한 경우
                    q.add(new Tuple(yy, xx, cost+1));
                    visit[yy][xx]=true;
                }
//                if(map[yy][xx] == level || map[yy][xx]==0){ //이동만 가능할 때
//                    q.add(new Tuple(yy, xx, cost+1));
//                    visit[yy][xx]=true;
//                }
//                else if(map[yy][xx] < level && eat==false){ //물고기 먹을 수 있을 때
//                    eat = true;
//                    nextStart.r = yy;
//                    nextStart.c = xx;
//                    nextStart.cost = cost+1;
//                }
            }
        }
        return nextStart;
    }
}
class Tuple{
    int r;
    int c;
    int cost;
    Tuple(){}
    Tuple(int r, int c, int cost){
        this.r = r;
        this.c = c;
        this.cost = cost;
    }
}