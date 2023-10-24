package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16236 {

	static int[][] space;
	static boolean[][] visited;
	static int[][] distance;
	static int N;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static List<Fish> list;
	static int size;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		space = new int[N][N];
		int x = -1, y = -1; //아기상어 위치
		size = 2; //아기상어 크기
		int check = 0; //아기상어 먹은 양 체크
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				space[i][j] = Integer.parseInt(st.nextToken());
				if(space[i][j]==9) { //아기상어 초기 위치 파악
					x = i;
					y = j;
				}
			}
		}
		
		int time = 0;
		while(true) {
			//매번 BFS로 거리 구함 (아기상어가 먹을 수 있는 물고기들 List에 담기)
			list = new ArrayList<>();
			BFS(x, y);
			
			//우선순위에 따라 물고기들 정렬
			Collections.sort(list);
			
			if(list.size()!=0) { //먹을 수 있는 물고기가 있는 경우
				Fish fish = list.get(0); //아기상어 타겟이 된 물고기
				//아기상어 이동시키고
				space[x][y] = 0;
				x = fish.x;
				y = fish.y;
				space[x][y] = 9;
						
				//아기상어가 이동한 시간 계산
				time += fish.dist;
				
				//아기상어 사이즈 관리
				check++;
				if(check==size) {
					size++;
					check = 0;
				}
				
			} else { //먹을 수 있는 물고기가 공간에 없는 경우
				break;
			}
		}
		System.out.println(time);
	}
	
	static void BFS(int a, int b) {
		Queue<int[]> queue = new LinkedList<>();
		visited = new boolean[N][N];
		distance = new int[N][N];
		queue.add(new int[] {a,b});
		visited[a][b] = true;
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0];
			int y = now[1];
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx<0 || nx>=N || ny<0 || ny>=N) //공간 밖으로 넘어간 경우
					continue;
				if(visited[nx][ny]) //이미 방문한 공간인 경우
					continue;
				if(space[nx][ny]>size) { //아기상어보다 크기가 큰 물고기가 있는 공간
					continue;
				} else if(space[nx][ny]==size || space[nx][ny]==0) { //아기상어가 지나갈 수 있는 공간
					visited[nx][ny] = true;
					distance[nx][ny] = distance[x][y] + 1;
					queue.add(new int[] {nx,ny});
				} else { //아기상어가 먹을 수 있는 물고기
					visited[nx][ny] = true;
					list.add(new Fish(nx, ny, distance[x][y]+1));
				}
			}
		}
	}

}

class Fish implements Comparable<Fish> {
	int x;
	int y;
	int dist;
	
	public Fish(int x, int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}

	@Override
	public int compareTo(Fish o) {
		if(this.dist==o.dist) {
			if(this.x==o.x) {
				return this.y - o.y;
			}
			return this.x - o.x;
		}
		return this.dist - o.dist;
	}
}
