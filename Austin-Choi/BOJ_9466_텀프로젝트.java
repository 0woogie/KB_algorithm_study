import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_9466_텀프로젝트 {
	//방향있어서 union-find 안됨
	//그림 그려보면 방향성있는 그래프에서 사이클에 속하지 않는 노드 세는 것
	//dfs활용해서 
	//dfs가 끝나면 어느 지점에서 다른 지점으로 가는 경로가 있다는 것이고
	//dfs안 끝나면 순환한다는 것이므로..
	//각 노드에 대해서 시작하여 dfs를 해서 순환여부 저장하는 N의 크기 배열(finished)에 저장하고
	//그거 세기
	public static int[] A;
	public static boolean[] finished;
	public static boolean[] visited;
	public static int N;
	public static int cnt;
	public static int cycle;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 0; t<T; t++) {
			cycle = 0;
			cnt = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			A = new int[N+1];
			//cycle의 안에서 또 방문되었는지 확인하는 배열
			finished = new boolean[N+1];
			//dfs에서 방문했는지 체크
			visited = new boolean[N+1];
			
			st = new StringTokenizer(br.readLine());
			for(int idx = 1; idx <= N; idx++) {
				A[idx] = Integer.parseInt(st.nextToken());
			}
			
			//O(N)
			for(int idx = 1; idx<=N; idx++) {
				if(!finished[idx])
					dfs(idx);
			}
			System.out.println(N - cycle);
		}
		
	}
	public static void dfs(int i) {
		//방문한 거면 사이클 팀안에 포함된거임
		if(visited[i]) {
			finished[i] = true;
			cycle++;
		}
		else {
			//방문안했으니 방문체크해줌
			visited[i] = true;
		}
		//사이클에 포함된 사이클팀 노드는 탐색안함
		if(!finished[A[i]])
			dfs(A[i]);
		
		//dfs 끝나고 다음 노드 기준으로 잡고 탐색해줘야되니까 false로 해줌
		visited[i] = false;
		//cycle에 포함되지 않았는데(dfs가 끝남) 사이클인지 한번 검사해줬으니까 true로 해줌
		finished[i] = true;
		
	}

}
