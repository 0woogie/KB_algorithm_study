package algorithm.Team02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1414 {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		//입력받기
		int N = Integer.parseInt(st.nextToken()); //컴퓨터의 개수
		
		int total_length = 0; //전체 길이 입력받기
		
		for(int i=0; i<N; i++) { //컴퓨터의 개수만큼
			char[] arr = br.readLine().toCharArray(); //char형 배열로 입력받음
			for(int j=0; j<N; j++) {
				if(arr[j] == '0') //0으로 입력받으면 컴퓨터를 연결하는 랜선이 없기에 continue
					continue;
				else { //0이 아닌 문자로 입력받은 경우
					int t = toLength(arr[j]);
					total_length += t; //전체 랜선의 길이 갱신
					if(i!=j && t!=0) //i와 j가 다르고 0이 아닌 경우
						pq.add(new Edge(i, j, toLength(arr[j]))); //간선에 대한 정보를 우선순위 큐에 넣음
				}
			}
		}
		//MST(간선들의 가중치 합이 최소)
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++)
            parent[i] = i;
        int mst_length=0;
        int used=0;
        while (!pq.isEmpty()) { //비어 있지 않는 경우
            Edge now_edge = pq.poll();
            if(find(now_edge.st)!=find(now_edge.end)){ //처음과 끝이 같지 않은 경우
                union(now_edge.st, now_edge.end); //처음과 끝을 합함
                mst_length+=now_edge.w;
                used++;
            }
        }
        //출력하기
        if(used==N-1)
            System.out.println(total_length-mst_length); //랜선 길이의 최대값
        else System.out.println(-1); //모든 컴퓨터가 연결되어 있지 않으면 -1출력
    }

    static void union(int a, int b) { //union 메소드 선언
        a = find(a);
        b = find(b);
        if (a != b)
            parent[b] = a;
    }

    static int find(int a) { //find 메소드 선언
        if (a == parent[a])
            return a;
        else return parent[a] = find(parent[a]);
    }

    static int toLength(char ele) { //toLength 메소드 선언
        if (ele >= 'a' && ele <= 'z') { //소문자 a~z
            return ele - 'a' + 1;
        } else { //대문자 A~Z
            return ele - 'A' + 27;
        }
    }

    static class Edge implements Comparable<Edge> {
        int st;
        int end;
        int w;

        public Edge(int s, int e, int w) {
            this.st = s;
            this.end = e;
            this.w = w;
        }

        public int compareTo(Edge v) {
            return this.w > v.w ? 1 : -1;
        }
    }
}