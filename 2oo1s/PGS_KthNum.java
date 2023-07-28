import java.util.*;

class Solution {
	public int[] solution(int[] array, int[][] commands) {
		int[] answer = {};
		// commands의 길이로 answer 크기 설정
		answer = new int[commands.length];

		for (int i = 0; i < commands.length; i++) {
			ArrayList<Integer> temp = new ArrayList<>();	// 추출한 배열 저장용
			// array에서 i번째부터 j번째에 있는 원소 temp에 저장
			for (int j = commands[i][0] - 1; j <= commands[i][1] - 1; j++)
				temp.add(array[j]);

			Collections.sort(temp);
			/*
			 정렬한 배열에서 k번째 수 answer에 저장
			 인덱스로 접근해야되기 때문에 k값에서 -1 해줌
			*/
			answer[i] = temp.get(commands[i][2] - 1);
		}
		return answer;
	}
}