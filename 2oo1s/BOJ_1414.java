package algorith.study;

import java.io.*;
import java.util.*;

public class BOJ_1414 {
	static int N; // 노트뷱 개수
	static int[] parent;
	static int[][] computer; // 노트북 연결 랜선 정보 저장
	static int ans = -1; // 연결 안되어 있을 시, -1 출력
	static int cable = 0; // 랜선 총 길이

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		parent = new int[N + 1];
		computer = new int[N + 1][N + 1];
		// 연결 정보 랜선 길이로 정렬
		PriorityQueue<int[]> q = new PriorityQueue<>((c1, c2) -> c1[2] - c2[2]);

		// 부모 노드 초기
		for (int i = 1; i <= N; i++)
			parent[i] = i;

		// 랜선 정보
		for (int i = 1; i <= N; i++) {
			String s = br.readLine();

			for (int j = 1; j <= N; j++) {
				char c = s.charAt(j - 1);

				if (c == '0')
					continue;
				else {
					// 소문자 : 대문자
					computer[i][j] = (c >= 97) ? c - 96 : c - 38;
					cable += computer[i][j];
				}
				// 본인과의 연결
				if (i == j)
					continue;

				q.add(new int[] { i, j, computer[i][j] });
			}
		}

		int a = 0;
		int cnt = 0;

		if (!q.isEmpty()) {
			int[] temp;

			while (!q.isEmpty()) {
				temp = q.poll();

				if (find(temp[0]) != find(temp[1])) {
					union(temp[0], temp[1]);
					cnt++;
					a += temp[2];
				}
				// 간선의 수가 N-1개가 되면 모든 컴퓨터가 연결되어있다는 것
				if (cnt == N - 1) {
					// 각 컴퓨터를 연결하고 있는 랜선의 길이는 총 랜선의 길이에서 빼주면 기부할 랜선의 길이
					ans = cable - a;
					break;
				}
			}
		}
		if (N == 1)
			ans = cable;

		System.out.println(ans);
	}

	public static void union(int n1, int n2) {
		n1 = find(n1);
		n2 = find(n2);

		if (n1 != n2)
			parent[n2] = n1;
	}

	public static int find(int n1) {
		if (n1 == parent[n1])
			return n1;
		else
			return parent[n1] = find(parent[n1]);
	}
}
