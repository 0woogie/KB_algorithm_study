import java.util.*;

class Solution {
	public int solution(int n, int[] lost, int[] reserve) {
		int answer = 0;

		Arrays.sort(lost);
		Arrays.sort(reserve);

		// 도난 당한 학생이 여벌 체육복을 가져온 학생일 때,
		for (int i = 0; i < lost.length; i++) {
			for (int j = 0; j < reserve.length; j++) {
				if (lost[i] == reserve[j]) {
					answer++;
                    /*
                     lost[]에서 체육복을 가진 학생과
                     reserve[]에서 체육복을 빌려준 학생의 인덱스값 -1로 변경
                     :: 인덱스에 +1을 해도 if문에서 영향을 끼치지 않는 값 
                    */
					lost[i] = -1;
					reserve[j] = -1;
					break;
				}
			}
		}

		// 도난 당한 학생에게 여벌 체육복을 빌려줄 수 있는 경우
		for (int i = 0; i < lost.length; i++) {
			for (int j = 0; j < reserve.length; j++) {
				if ((lost[i] - 1 == reserve[j]) || (lost[i] + 1 == reserve[j])) {
					answer++;
					reserve[j] = -1;
					break;
				}
			}
		}
		answer += n - lost.length; // 도난 안 당한 학생 수 더해주기
		return answer;
	}
}
