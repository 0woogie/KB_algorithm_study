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
		
		if(!visited[next]) { //아직 dfs 탐색을 계속 해야하는 상황
			dfs(next);
		} else { //다음에 갈 곳이 이미 방문처리 되어 있다 == 현재까지의 dfs 탐색에 사이클이 포함되어 있다
			if(!finished[next]) { //이미 count 처리한 사이클이 아닌 경우
				count++;
				while(next != now) {
					count++;
					next = arr[next];
				}
			}
		}
		//dfs 탐색이 끝났다 == 사이클을 만났다 == 더는 볼 필요 없는 노드들
		finished[now] = true;
	}
}

//문제의 핵심 - "DFS 탐색이 끝나기 위해서는 무조건 사이클을 만나야한다"
//참고 - https://bcp0109.tistory.com/32