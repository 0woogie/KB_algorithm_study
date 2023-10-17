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
		//1. ���� ĭ�� ���� û�ҵ��� ���� ���, ���� ĭ�� û���Ѵ�.
		if(graph[x][y]==0) {
			graph[x][y] = 2; //���� ĭ�� û��
			result++;
		}
		
		for(int i=0; i<4; i++) { //2. ���� ĭ�� �ֺ� 4ĭ �� û�ҵ��� ���� �� ĭ�� �ִ��� üũ
			//2-1. �ݽð� �������� 90�� ȸ���Ѵ�.
			d = (d+3)%4;
			int nx = x + dx[d];
			int ny = y + dy[d];
			//2-2. �ٶ󺸴� ������ �������� ���� ĭ�� û�ҵ��� ���� �� ĭ�� ��� �� ĭ �����Ѵ�.
			//2-3. 1������ ���ư���.
			if(graph[nx][ny]==0) {
				DFS(nx, ny);
				return;
			}	
		}
		
		//3. ���� ĭ�� �ֺ� 4ĭ �� û�ҵ��� ���� �� ĭ�� ���� ���
		int nx = x - dx[d];
		int ny = y - dy[d];
		if(graph[nx][ny]!=1) { //3-1. �ٶ󺸴� ������ ������ ä�� �� ĭ ������ �� �ִٸ� �� ĭ �����ϰ� 1������ ���ư���.
			DFS(nx, ny);
		} else { //3-2. �ٶ󺸴� ������ ���� ĭ�� ���̶� ������ �� ���ٸ� �۵��� �����.
			return;
		}
	}
}
