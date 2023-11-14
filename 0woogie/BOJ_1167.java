package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1167 {
	
	static ArrayList<int[]>[] list; //인접리스트
	static boolean[] visited; //방문배열
	static int result, far;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		list = new ArrayList[V+1];
		
		for(int i=1; i<=V; i++) {
			list[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		for(int i=0; i<V; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			while(true) {
				int e = Integer.parseInt(st.nextToken());
				if(e==-1)
					break;
				int x = Integer.parseInt(st.nextToken());
				list[s].add(new int[] {e, x});
			}
		}
		
		visited = new boolean[V+1];
		DFS(1, 0); //start, dist
		
		visited = new boolean[V+1];
		DFS(far, 0); //start, dist
		
		System.out.println(result);
	}
	
	static void DFS(int start, int dist) {
		visited[start] = true;
		//start와 인접한 정점 중 가보지 않은 곳 DFS 탐색
		for(int[] tmp : list[start]) {
			 int e = tmp[0];
			 int x = tmp[1];
			 if(!visited[e]) {
				 DFS(e, dist+x);
			 }
		}
		//끝에 도달했다면 result, far 갱신
		if(dist > result) {
			result = dist;
			far = start;
		}
		return;
	}
}

//트리의 지름 증명 - https://blogshine.tistory.com/111
//참고 - https://yamyam-spaghetti.tistory.com/102
//todo - DFS 재귀 호출 제한에 대해 알아보기