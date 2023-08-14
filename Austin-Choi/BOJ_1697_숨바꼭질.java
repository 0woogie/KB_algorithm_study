import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pair{
	int first;
	int second;
	public Pair(int first, int second) {
		this.first = first;
		this.second = second;
	}
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	
}
//public class BOJ_1697
//최소비용을 요구하고 각 행위를 하는데 비용이 1이므로 
//bfs로 풀이
public class Main {
	static int[] visited;
	static int N;
	static int K;
	public static void bfs(int pos) {
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(pos, 0));
		
		
		while(!q.isEmpty()) {
			int tmpPos = q.peek().getFirst();
			int tmpTime = q.peek().getSecond();
			q.poll();
			
			//수빈이가 동생의 위치 K에 도달했을때
			if(tmpPos == K) {
				System.out.println(tmpTime);
				return;
			}
      //수빈이가 할 수 있는 행동의 종류별로 탐색
			if(tmpPos - 1 >=0 && tmpPos-1<= 100000) {
				if(visited[tmpPos-1]==0) {
					q.add(new Pair(tmpPos-1,tmpTime+1));
					visited[tmpPos-1] = 1;
				}
			}
			if(tmpPos + 1 >=0 && tmpPos+1<= 100000) {
				if(visited[tmpPos+1]==0) {
					q.add(new Pair(tmpPos+1,tmpTime+1));
					visited[tmpPos+1] = 1;
				}
			}
			if(tmpPos *2 >=0 && tmpPos * 2<= 100000) {
				if(visited[tmpPos*2]==0) {
					q.add(new Pair(tmpPos * 2,tmpTime+1));
					visited[tmpPos*2] = 1;
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new int[100001];
		for(int i = 0; i<visited.length; i++) {
			visited[i] = 0;
		}
		visited[N] = 1;
		bfs(N);
	}

}
