import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Shark{
	int posI;
	int posJ;
	
	Shark(int posI, int posJ){
		this.posI = posI;
		this.posJ = posJ;
	}
}
//이동비용 1, 최단거리 필요 = bfs
public class BOJ_16236_아기상어 {
	public static int time = 0;
	//상하좌우
	public static int[] di = {0,0,1,-1};
	public static int[] dj = {-1,1,0,0};
	public static int N;
	public static int[][] map;
	public static int[][] dist;
	
	public static int babySize = 2;
	public static int babyPosI = 0;
	public static int babyPosJ = 0;
	public static int eatenFish = 0;
	
	//상어의 최대 이동 궤적
	public static int minDist = 401;
	public static int minI = 20;
	public static int minJ = 20;

	public static void main(String[] args) throws IOException {
		//먹는건 자기보다 작은것만
		//지나갈수 없는건 자기보다 큰 물고기
		//아기상어 초기크기 2, 1초에 상하좌우 인접 한칸만 이동
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				if(temp == 9) {
					babyPosI = i;
					babyPosJ = j;
					map[i][j] = 0;
				}
			}
		}
		
		dist = new int[N][N];
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				dist[i][j] = -1;
			}
		}
		
		while(true) {
			bfs(babyPosI, babyPosJ);
			
			//N -> 2~20
			if(minI != 20 && minJ != 20) {
				//1초에 한칸씩 움직이니까 시간 더해주고
				time += dist[minI][minJ];
				//먹은 물고기 갯수 업데이트
				eatenFish++;
				//만약 먹은 물고기 총 갯수가 아기상어 사이즈랑 같으면
				//사이즈업시키고
				//먹은 물고기 0만듬
				if(eatenFish == babySize) {
					babySize++;
					eatenFish = 0;
				}
				//아기상어 위치 먹은 물고기 위치로 옮겨주고 맵에는 0으로 표시
				map[minI][minJ] = 0;
				babyPosI = minI;
				babyPosJ = minJ;
				
				//다음 bfs를 위해 초기화
				minDist = 401;
				minI = 20;
				minJ = 20;
				for(int i = 0; i<N; i++) {
					for(int j = 0; j<N; j++) {
						dist[i][j] = -1;
					}
				}
			}
			else
				break;
		}
		
		System.out.println(time);
	}
	
	public static void bfs(int i, int j) {
		Queue<Shark> q = new LinkedList<Shark>();
		dist[i][j] = 0;
		q.add(new Shark(i, j));
		
		while(!q.isEmpty()) {
			Shark curS = q.poll();
			int curI = curS.posI;
			int curJ = curS.posJ;
			
			//4방향 이동 
			for(int z = 0; z<4; z++) {
				int ni = curI + di[z];
				int nj = curJ + dj[z];
				
				//유효한 범위 안에서
				if(ni >= 0 && ni < N && nj >= 0 && nj < N) {
					
					//아직 안 지나갔고 아기상어 사이즈보다 같거나 작은 물고기라서 지나갈수 있으면
					if(map[ni][nj] <= babySize && dist[ni][nj] == -1) {
						//거리 1 더해서 업데이트시킴
						dist[ni][nj] = dist[ni][nj] + 1;
						
						//만약 물고기가 존재하고 먹을수 있는 물고기라면
						if(map[ni][nj] != 0 && map[ni][nj] < babySize) {
							//아기상어로부터 최소거리와
							//최소 좌표값 업데이트
							if(minDist > dist[ni][nj]) {
								minI = ni;
								minJ = nj;
								minDist = dist[ni][nj];
							}
							//만약 최소 거리를 가진 다른 후보 물고기가 나타나면
							else if(minDist == dist[ni][nj]) {
								//i와 j값을 봐서 더 작은게 있으면 그걸로 업데이트함
								if(minJ > nj) 
									minJ = nj;
								if(minI > ni)
									minI = ni;
							}
						}
						
						q.add(new Shark(ni, nj));
					}
				}
			}
		}
	}

}
