class Solution {
	int answer;

	public int solution(int N, int number) {
		answer = -1; // 최솟값이 8보다 크면 -1을 return

		dfs(N, number, 0, 0);

		return answer;
	}

	public void dfs(int N, int number, int sum, int cnt) {
		if (cnt > 8)
			return;

		if (sum == number) {
			if (cnt < answer || answer == -1)
				answer = cnt;
			return;
		}

		int increN = 0;
		for (int i = 1; i < 9; i++) {
			increN = increN * 10 + N; // 5, 55, 555, 5555 등을 연산

			dfs(N, number, sum + increN, cnt + i);
			dfs(N, number, sum - increN, cnt + i);
			dfs(N, number, sum * increN, cnt + i);
			dfs(N, number, sum / increN, cnt + i);
		}
	}
}