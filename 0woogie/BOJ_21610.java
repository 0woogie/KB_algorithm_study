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
		
		//8���� ���⿡ ���� �迭 dx, dy
		int[] dx = {0,0,-1,-1,-1,0,1,1,1};
		int[] dy = {0,-1,-1,0,1,1,1,0,-1};
		
		int[][] arr = new int[n][n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<int[]> queue = new LinkedList<>(); //�񱸸� �����ϴ� Queue
		boolean[][] visited; //������ ����� ĭ���� ���� ����� �� �����ϴ� �迭
		
		//�񱸸� �ʱ� ��ġ
		queue.add(new int[] {n-1,0});
		queue.add(new int[] {n-1,1});
		queue.add(new int[] {n-2,0});
		queue.add(new int[] {n-2,1});
		
		for(int k=0; k<m; k++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			visited = new boolean[n][n];
			
			//�񱸸� �̵���Ű��
			int size = queue.size();
			for(int i=0; i<size; i++) {
				int[] target = queue.poll();
				//java������ ���� ��ⷯ ���� �̽�
				//int nx = (target[0] + dx[d]*s) % n;
				//int ny = (target[1] + dy[d]*s) % n;
				
				int nx = (target[0] + dx[d]*s);
				nx = Math.floorMod(nx, n);
				int ny = (target[1] + dy[d]*s);
				ny = Math.floorMod(ny, n);
				
				arr[nx][ny] += 1; //���� �ִ� ĭ ���� �� 1 ����
				queue.add(new int[] {nx,ny}); //���� �񱸸� ��ġ�� ���� ���� ���� �� ���� (�밢�� �� ���� üũ�ؾ� ��)
			}
			
			//�밢�� �� ���⿡�� �񱸸� üũ, �̶� �񱸸��� �ִ� ��ġ�� visited�� ����صα�
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

			//���� �� 2 �̻��� ��� ��ġ�� �񱸸� ����(queue�� ���� + ���� �� 2 ����)
			//�񱸸��� ����� ĭ�� ������ �񱸸��� ������� ĭ�� �ƴϾ�� ��
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(!visited[i][j] && arr[i][j]>=2) {
						arr[i][j] -= 2;
						queue.add(new int[] {i,j});
					}
				}
			}
		}
		//M���� �̵��� ��� ���� �� �ٱ��Ͽ� ����ִ� ���� ���� �� ���ϱ�
		int result = 0;
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				result += arr[i][j];
		System.out.println(result);
	}

}
