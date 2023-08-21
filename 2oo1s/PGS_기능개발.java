import java.util.*;

class Solution {
	public int[] solution(int[] progresses, int[] speeds) {
		int[] answer = {};
		Queue<Integer> q = new LinkedList<>();
		ArrayList<Integer> list = new ArrayList<>();

		// 각 기능별 완료되는 데 남은 일수 저장
		for (int i = 0; i < progresses.length; i++) {
			if ((100 - progresses[i]) % speeds[i] == 0)
				q.add((100 - progresses[i]) / speeds[i]);
			else
				q.add((100 - progresses[i]) / speeds[i] + 1);
		}

		int temp = q.poll();
		int cnt = 1;

		while (!q.isEmpty()) {
            // 앞에 있는 기능의 남은 일수가 뒤에 있는 기능의 남은 일수보다 많이 남았을 때,
			if (temp >= q.peek()) {
				cnt++;		// 배포 가능한 기능 추가
				q.poll();	// 뒤에 있던 기능은 빼서 더 뒤에 있는 기능과도 비교 가능하게 해주기
			} else {
				/*
				앞에 있는 기능이 뒤에 있는 기능보다 빨리 개발되면,
				뒤에 있는 기능이랑 굳이 같이 배포 안되도 되니까 혼자 배포해주기
				 */
				list.add(cnt);
				cnt = 1;
				temp = q.poll();
			}
		}
		list.add(cnt);
		
		answer = new int[list.size()];
		for (int i = 0; i < answer.length; i++)
			answer[i] = list.get(i);

		return answer;
	}
}