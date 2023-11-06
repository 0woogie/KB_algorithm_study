package algorithm.boj;

import java.io.*;
import java.util.*;

// 82분 
public class BOJ_9466 {
	static int[] arr;
	static boolean[] visited;
	static boolean[] isCycle;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			int n = Integer.parseInt(br.readLine());
			answer = 0;
			arr = new int[n + 1];
			visited = new boolean[n + 1];
			isCycle = new boolean[n + 1]; // 싸이클을 형성했는지 안했는지 확인용 ... 팀을 이루면 싸이클이 형성

			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= n; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
				if (arr[j] == j) { // 자기자신과 팀을 이루는 경우는 바로 싸이클 형성했다고 체크하고 팀을 이룬 학생 수 증가
					isCycle[j] = true;
					answer++;
				}
			}

			for (int j = 1; j <= n; j++) {
				if (isCycle[j])
					continue;
				dfs(j);
			}
			System.out.println(n - answer);
		}
	}

	public static void dfs(int idx) {
		if (visited[idx])
			return;

		visited[idx] = true;
		int next = arr[idx];

		if (!visited[next]) // 다음 노드가 방문된 노드라면, 싸이클을 이룬다는 뜻
			dfs(next);
		else {
			if (!isCycle[next]) { // 싸이클 형성 시작
				answer++;
				while (next != idx) { // 싸이클 자기 자신 빼고 몇명 있는지 체크
					answer++;
					next = arr[next];
				}
			}
		}
		isCycle[idx] = true;
	}
}
