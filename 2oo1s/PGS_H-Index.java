import java.util.*;

class Solution {

	public int solution(int[] citations) {
		int answer = 0;

		Arrays.sort(citations);

		for (int i = 0; i < citations.length; i++) {
			// citations[i]보다 크거나 같은 논문 수
			int h = citations.length - i;
			// 논문 수보다 인용 횟수가 더 많을 때가 조건 부합하는 경우
			if (citations[i] >= h) {
				answer = h;
				break;
			}
		}
		return answer;
	}
}
