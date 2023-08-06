import java.util.*;

class Solution {
	public int[] solution(int[] answers) {
		int[] answer = {};
		// 3인방이 문제 찍는 방법 저장
		int[][] student = { { 1, 2, 3, 4, 5 }, { 2, 1, 2, 3, 2, 4, 2, 5 }, { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 } };
		// 삼인방 맞은 문제 카운트
		int[] cnt = new int[3];
		for (int i = 0; i < answers.length; i++) {
			if (answers[i] == student[0][i % student[0].length])
				cnt[0]++;
			if (answers[i] == student[1][i % student[1].length])
				cnt[1]++;
			if (answers[i] == student[2][i % student[2].length])
				cnt[2]++;
		}
		// 가장 많이 맞춘게 몇 문제인지 
		int maxAns = 0;
		for (int i : cnt) {
			if (i > maxAns)
				maxAns = i;
		}
        // 삼인방 중 최다득점인 사람 저장용 
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			if (cnt[i] == maxAns)
				list.add(i + 1);
		}

		answer = new int[list.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = list.get(i);
		}
		return answer;
	}
}