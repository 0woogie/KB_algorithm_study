import java.io.*;

public class BOJ_2133 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];

		if (N % 2 != 0) {
			System.out.println(0);
			return;
		}

		dp[0] = 1; // 크기가 0일 때,
		dp[2] = 3; // 3 * 2 채우는 경우

		// N 짝수일 때만 확인
		for (int i = 4; i <= N; i += 2) {
			dp[i] = dp[i - 2] * dp[2];
			for (int j = 0; j < i - 2; j += 2) {
				dp[i] += dp[j] * 2;
			}
		}
		System.out.println(dp[N]);
	}
}
