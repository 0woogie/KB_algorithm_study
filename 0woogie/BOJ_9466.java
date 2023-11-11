package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9466 {
	
	static int[] arr;
	static boolean[] visited;
	static boolean[] finished;
	static int count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			int N = Integer.parseInt(br.readLine());
			arr = new int[N+1];
			visited = new boolean[N+1];
			finished = new boolean[N+1];
			count =0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=1; i<=N; i++) {
				dfs(i);
			}
			
			System.out.println(N-count);
		}
	}
	
	static void dfs(int now) {
		if(visited[now])
			return;
		
		visited[now] =true;
		int next = arr[now];
		
		if(!visited[next]) { //���� dfs Ž���� ��� �ؾ��ϴ� ��Ȳ
			dfs(next);
		} else { //������ �� ���� �̹� �湮ó�� �Ǿ� �ִ� == ��������� dfs Ž���� ����Ŭ�� ���ԵǾ� �ִ�
			if(!finished[next]) { //�̹� count ó���� ����Ŭ�� �ƴ� ���
				count++;
				while(next != now) {
					count++;
					next = arr[next];
				}
			}
		}
		//dfs Ž���� ������ == ����Ŭ�� ������ == ���� �� �ʿ� ���� ����
		finished[now] = true;
	}
}

//������ �ٽ� - "DFS Ž���� ������ ���ؼ��� ������ ����Ŭ�� �������Ѵ�"
//���� - https://bcp0109.tistory.com/32