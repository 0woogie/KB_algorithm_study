package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1414 {

	static int[] parent;
	static PriorityQueue<Edge> queue; //랜선 길이를 기준으로 자동 정렬을 하기 위해 우선순위큐 사용
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		parent = new int[n+1];
		for(int i=1; i<=n; i++)
			parent[i] = i;
		queue = new PriorityQueue<>();
		int total = 0; //총 랜선 길이 저장
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<n; j++) {
				char c = s.charAt(j);
				if(c=='0')
					continue;
				int k = 0;
				if(c-'a'>=0) { //a~z
					k = c-'a'+1; //1~26
				} else { //A~Z
					k = c-'A'+27; //27~52
				}
				total += k; //총 랜선 길이에 현재 랜선 길이 더하기
				queue.add(new Edge(i+1,j+1,k));
			}
		}
		int edgeNum = 0; //컴퓨터 연결하는 랜선 개수
		int sum = 0; //컴퓨터 연결하는 랜선 길이 총 합
		while(!queue.isEmpty()) {
			Edge now = queue.poll();
			if(find(now.a)!=find(now.b)) { //a와 b가 현재 연결되지 않은 경우
				union(now.a, now.b);
				edgeNum++;
				sum += now.c;
			}
			if(edgeNum==n-1) break; //모든 컴퓨터가 연결되면 반복문 탈출
		}
		if(edgeNum==n-1) System.out.println(total-sum); //기부할 수 있는 랜선 = 가지고 있는 랜선 - 다솜이가 쓰는 랜선
		else System.out.println(-1); //모든 컴퓨터가 연결되어 있지 않은 경우 -1 출력
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a>b) parent[a] = b;
		else parent[b] = a;
	}

	private static int find(int x) {
		if(parent[x]!=x)
			parent[x] = find(parent[x]);
		return parent[x];
	}
}

class Edge implements Comparable<Edge> {
	int a;
	int b;
	int c;
	Edge (int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	@Override
	public int compareTo(Edge o) { //Edge 클래스의 객체를 c(랜선 길이)를 기준으로 오름차순 정렬
		return this.c - o.c;
	}
}
