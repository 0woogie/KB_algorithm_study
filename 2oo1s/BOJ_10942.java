package algorithm.boj;

import java.io.*;
import java.util.*;

public class BOJ_10942 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];
		boolean[][] dp = new boolean[N + 1][N + 1];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++)
			dp[i][i] = true; // 길이가 1이면 무조건 펠린드롬

		for (int i = 1; i < N; i++) { // 길이가 2일 때, 두 숫자 같으면 팰린드롬
			if (arr[i] == arr[i + 1])
				dp[i][i + 1] = true;
		}

		for (int i = 2; i < N; i++) {
			for (int j = 1; j <= N - i; j++) { // 길이가 3 이상일 때,
				if (arr[j] == arr[j + i] && dp[j + 1][j + i - 1]) // 첫과 끝의 수가 같고, 그 사이가 팰린드롬이면 그 수도 팰린드롬
					dp[j][j + i] = true;
			}
		}

		int M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			if (dp[s][e]) // 팰린드롬일 때,
				sb.append(1).append("\n");
			else // 팰린드롬이 아닐 때,
				sb.append(0).append("\n");
		}
		System.out.println(sb);
	}
}

/*
 * 첨에 StringBuilder 안 쓰고 마지막 for문 안에서 팰린드롬인지 아닌지
 * 그때그때마다 출력되게 해줬는데 시간초과 뜸,,,
 */