package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503 {

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int result;
	static int[][] graph;
	static int d;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		result = 0;
		DFS(r,c);
		System.out.println(result);
	}

	static void DFS(int x, int y) {
		//1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
		if(graph[x][y]==0) {
			graph[x][y] = 2; //현재 칸을 청소
			result++;
		}
		
		for(int i=0; i<4; i++) { //2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는지 체크
			//2-1. 반시계 방향으로 90도 회전한다.
			d = (d+3)%4;
			int nx = x + dx[d];
			int ny = y + dy[d];
			//2-2. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
			//2-3. 1번으로 돌아간다.
			if(graph[nx][ny]==0) {
				DFS(nx, ny);
				return;
			}	
		}
		
		//3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
		int nx = x - dx[d];
		int ny = y - dy[d];
		if(graph[nx][ny]!=1) { //3-1. 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
			DFS(nx, ny);
		} else { //3-2. 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
			return;
		}
	}
}
