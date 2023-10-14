package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] cost = new int[N][3];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			cost[i][0] = Integer.parseInt(st.nextToken()); // 빨강 비용
			cost[i][1] = Integer.parseInt(st.nextToken()); // 초록 비용
			cost[i][2] = Integer.parseInt(st.nextToken()); // 파랑 비용
		}

		for (int i = 1; i < N; i++) {
			// 사용하지 않은 두 색상 중 더 저렴한 비용 더해주기
			cost[i][0] += Math.min(cost[i - 1][1], cost[i - 1][2]);
			cost[i][1] += Math.min(cost[i - 1][0], cost[i - 1][2]);
			cost[i][2] += Math.min(cost[i - 1][0], cost[i - 1][1]);
		}
		// System.out.println(Arrays.deepToString(cost));

		int answer = Math.min(cost[N - 1][0], Math.min(cost[N - 1][1], cost[N - 1][2]));

		System.out.println(answer);
	}

}
