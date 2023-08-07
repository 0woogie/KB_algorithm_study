import java.util.*;

public class Solution {
	public int[] solution(int[] arr) {
		int[] answer = {};
		ArrayList<Integer> list = new ArrayList<Integer>();
		int temp = -1;	// 같은 숫자가 연속으로 있는지 확인용

		for (int i = 0; i < arr.length; i++) {
			/*
			temp와 arr[i] 같지 않다면, arr[i]로 temp 값 갱신
			arr[i+1]이 temp와 같다면, arr[i]와 arr[i+1]는 같은 값이 연속적으로 존재한다는 것이므로
			continue;
			*/
			if (arr[i] != temp) {
				list.add(arr[i]);
				temp = arr[i];
			} else
				continue;
		}

		// '연속적으로 나타나는 숫자는 하나만 남기고 전부 제거'한 결과를 answer에 저장
		answer = new int[list.size()];
		for (int i = 0; i < answer.length; i++)
			answer[i] = list.get(i);

		return answer;
	}
}