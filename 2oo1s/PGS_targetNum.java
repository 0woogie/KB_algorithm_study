class Solution {
	static int answer;
	static int N; // 배열내 원소 개수
	static int T; // 타겟 넘버

	public int solution(int[] numbers, int target) {
		answer = 0;
		T = target;
		N = numbers.length;

		dfs(numbers, 0, 0);

		return answer;
	}

	public static void dfs(int[] numbers, int n, int sum) {
		// 배열내 모든 원소 탐색했을 시
		if (n == N) {
			if (sum == T) {
				answer++;
				return;
			}
		} else {
			dfs(numbers, n + 1, sum + numbers[n]);
			dfs(numbers, n + 1, sum - numbers[n]);
		}
	}
}