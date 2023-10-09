package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21610 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		//8개의 방향에 대한 배열 dx, dy
		int[] dx = {0,0,-1,-1,-1,0,1,1,1};
		int[] dy = {0,-1,-1,0,1,1,1,0,-1};
		
		int[][] arr = new int[n][n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<int[]> queue = new LinkedList<>(); //비구름 저장하는 Queue
		boolean[][] visited; //구름이 사라진 칸에서 구름 생기는 것 방지하는 배열
		
		//비구름 초기 위치
		queue.add(new int[] {n-1,0});
		queue.add(new int[] {n-1,1});
		queue.add(new int[] {n-2,0});
		queue.add(new int[] {n-2,1});
		
		for(int k=0; k<m; k++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			visited = new boolean[n][n];
			
			//비구름 이동시키기
			int size = queue.size();
			for(int i=0; i<size; i++) {
				int[] target = queue.poll();
				//java에서의 음수 모듈러 연산 이슈
				//int nx = (target[0] + dx[d]*s) % n;
				//int ny = (target[1] + dy[d]*s) % n;
				
				int nx = (target[0] + dx[d]*s);
				nx = Math.floorMod(nx, n);
				int ny = (target[1] + dy[d]*s);
				ny = Math.floorMod(ny, n);
				
				arr[nx][ny] += 1; //구름 있는 칸 물의 양 1 증가
				queue.add(new int[] {nx,ny}); //아직 비구름 위치에 대한 정보 지울 수 없음 (대각선 네 방향 체크해야 됨)
			}
			
			//대각선 네 방향에서 비구름 체크, 이때 비구름이 있던 위치는 visited에 기록해두기
			while(!queue.isEmpty()) {
				int[] target = queue.poll();
				int x = target[0];
				int y = target[1];
				
				int count = 0;
				for(int t=2; t<=8; t+=2) {
					int tx = x + dx[t];
					int ty = y + dy[t];
					if(tx<0 || tx>=n || ty<0 || ty>=n) continue;
					if(arr[tx][ty]!=0) count++;
				}
				arr[x][y] += count;
				visited[x][y] = true;
			}

			//물의 양 2 이상인 모든 위치에 비구름 생성(queue에 저장 + 물의 양 2 감소)
			//비구름이 생기는 칸은 직전에 비구름이 사라졌던 칸이 아니어야 함
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(!visited[i][j] && arr[i][j]>=2) {
						arr[i][j] -= 2;
						queue.add(new int[] {i,j});
					}
				}
			}
		}
		//M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합 구하기
		int result = 0;
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				result += arr[i][j];
		System.out.println(result);
	}

}
