import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 연습용 {
    static int []dy = {-1, 0, 1, 0}; //0 : 북, 1 : 동, 2 : 남, 3 : 서
    static int []dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()); //첫번째 줄 입력

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int [][]map = new int[N][M];

        st = new StringTokenizer(br.readLine()); //두번째 줄 입력

        int y = Integer.parseInt(st.nextToken()); //청소기 x좌표
        int x = Integer.parseInt(st.nextToken()); //청소기 y좌표
        int d = Integer.parseInt(st.nextToken()); //방향

        for(int i=0;i<N;i++){ //map에 element 정의
            st =new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }


        int cnt = 0;
        while(true){
            if(map[y][x]==0){ //청소가 되지않았으면 청소하기
                map[y][x]=-1; //청소가 됐으면 -1로 된다
                cnt++;
            }

            boolean flag = false;
            for(int i=0;i<4;i++){ //먼저 4방향 청소된곳 있는지 체크
                int yy = y + dy[i];
                int xx = x + dx[i];
                if(xx<0 || yy <0 || xx>=M || yy>=N)continue;
                if(map[yy][xx]==0)flag = true; //청소할 곳이 있으면 true
            }

            if(flag){ //4방향 중에 청소할 곳이 있으면
                if(d==0)d=3;
                else d--;
                int yy = y + dy[d];
                int xx = x + dx[d];

                if(xx<0 || yy <0 || xx>=M || yy>=N)continue;
                if(map[yy][xx]==0){ //앞 방향에 있는 곳이 청소가 가능할 경우
                    y = yy;
                    x = xx;
                }
            }
            else{ //4방향 중에 청소할 곳이 없으면
                int temp = d;
                if(d >= 2)temp-=2;
                else temp+=2;

                int yy = y + dy[temp];
                int xx = x + dx[temp];

                if(xx<0 || yy <0 || xx>=M || yy>=N)continue;
                if(map[yy][xx] !=1){ //뒤로 이동했는데 벽이 없으면 이동 가능
                    y =yy;
                    x=xx;
                }
                else break; //벽있으면 그대로 종료
            }

        }
        System.out.println(cnt);
    }

}

