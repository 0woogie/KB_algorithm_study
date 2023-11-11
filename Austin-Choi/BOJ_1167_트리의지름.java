import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Pair{
	int vertex;
	int weight;
	Pair(int vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
	}
}

public class BOJ_1167_트리의지름 {
	public static boolean[] visited;
	public static int answer;
	public static ArrayList<Pair>[] A;
	//dfs로 다돌고 max값 갱신??
	//부모 vertex에서 호출하면, 자식 vertex중에서 가장 큰 값에다가 부모 vertex와 연결되는 weight더해서 반환
	//백트래킹
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		A = new ArrayList[V+1];
		answer = 0;
		visited = new boolean[V+1];
		
		for(int i = 1; i<=V; i++) {
			String[] input = br.readLine().split(" ");
			A[i] = new ArrayList<Pair>();
			for(int j = 1; j<input.length-1; j+=2) {
				A[i].add(new Pair(Integer.parseInt(input[j]), Integer.parseInt(input[j+1])));
			}
		}
		
		visited[1] = true;
		bt(1);
		System.out.println(answer);
	}
	public static int bt(int curVertex) {
		//이거 2개를 쓰는 이유는 1개만 연결되어 있으면 최대값 쓰면 되고
		//2개 이상이면 최대랑 2번째꺼 합치면 그것도 경로고 그게 최대 길이일 것이므로 2개씀
		//연결된 노드들부터 받은 최대 weight의 합산
		int curMaxWeight = 0;
		//연결된 노드들부터 받은 2번째로 큰 weight의 합산
		int curSecondMaxWeight = 0;
		for(Pair p : A[curVertex]) {
      if(!visited[p.vertex]){
        visited[p.vertex] = true;
			  //dfs로 구한 자식노드까지의 cost + 부모(현재bt call한 vertex)와 자식 노드 간의 cost
			  int totalWeight = bt(p.vertex) + p.weight;
			  if(totalWeight > curMaxWeight) {
				  curSecondMaxWeight = curMaxWeight;
				  curMaxWeight = totalWeight;
			  }
			  else if(totalWeight > curSecondMaxWeight)
				  curSecondMaxWeight = totalWeight;
      }
		}
		answer = Math.max(answer, curMaxWeight+curSecondMaxWeight);
		return curMaxWeight;
	}
}
