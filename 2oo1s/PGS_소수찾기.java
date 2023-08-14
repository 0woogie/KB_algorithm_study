import java.util.*;

class Solution {
	static HashSet<Integer> set = new HashSet<>(); // 중복값 제거 위해
	static char[] arr; // 종이 조각
	static boolean[] visited; // 사용여부

	public int solution(String numbers) {
		int answer = 0;

		arr = new char[numbers.length()];
		visited = new boolean[numbers.length()];

		// numbers 한글자씩 배열에 저장
		for (int i = 0; i < numbers.length(); i++)
			arr[i] = numbers.charAt(i);

		dfs("", 0);

		// set으로 중복 허용 안했으니까 set에 들어있는 소수의 개수 구하면 정답
		answer = set.size();
		return answer;
	}

	// 백트래킹으로 가능한 소수 찾기
	public void dfs(String str, int idx) {
		int temp;
		// 빈 문자열이 아니면 소수인지 확인
		if (str != "") {
			temp = Integer.parseInt(str);
			// 소수라면 set에 추가
			if (isPrime(temp))
				set.add(temp);
		}
		// 종이조각 다 확인했으면 리턴
		if (idx == arr.length)
			return;

		for (int i = 0; i < arr.length; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			dfs(str + arr[i], idx + 1);
			visited[i] = false;
		}
	}

	// 에라토스테네스의 체
	public boolean isPrime(int num) {
		// 0가 1일 때는 소수 아님
		if (num == 0 || num == 1)
			return false;
		for (int i = 2; i < (int) (Math.sqrt(num)) + 1; i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}
}