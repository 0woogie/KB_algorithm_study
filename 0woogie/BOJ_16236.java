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
		int x = -1, y = -1; //�Ʊ��� ��ġ
		size = 2; //�Ʊ��� ũ��
		int check = 0; //�Ʊ��� ���� �� üũ
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				space[i][j] = Integer.parseInt(st.nextToken());
				if(space[i][j]==9) { //�Ʊ��� �ʱ� ��ġ �ľ�
					x = i;
					y = j;
				}
			}
		}
		
		int time = 0;
		while(true) {
			//�Ź� BFS�� �Ÿ� ���� (�Ʊ�� ���� �� �ִ� ������ List�� ���)
			list = new ArrayList<>();
			BFS(x, y);
			
			//�켱������ ���� ������ ����
			Collections.sort(list);
			
			if(list.size()!=0) { //���� �� �ִ� ����Ⱑ �ִ� ���
				Fish fish = list.get(0); //�Ʊ��� Ÿ���� �� �����
				//�Ʊ��� �̵���Ű��
				space[x][y] = 0;
				x = fish.x;
				y = fish.y;
				space[x][y] = 9;
						
				//�Ʊ�� �̵��� �ð� ���
				time += fish.dist;
				
				//�Ʊ��� ������ ����
				check++;
				if(check==size) {
					size++;
					check = 0;
				}
				
			} else { //���� �� �ִ� ����Ⱑ ������ ���� ���
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
				if(nx<0 || nx>=N || ny<0 || ny>=N) //���� ������ �Ѿ ���
					continue;
				if(visited[nx][ny]) //�̹� �湮�� ������ ���
					continue;
				if(space[nx][ny]>size) { //�Ʊ���� ũ�Ⱑ ū ����Ⱑ �ִ� ����
					continue;
				} else if(space[nx][ny]==size || space[nx][ny]==0) { //�Ʊ�� ������ �� �ִ� ����
					visited[nx][ny] = true;
					distance[nx][ny] = distance[x][y] + 1;
					queue.add(new int[] {nx,ny});
				} else { //�Ʊ�� ���� �� �ִ� �����
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
