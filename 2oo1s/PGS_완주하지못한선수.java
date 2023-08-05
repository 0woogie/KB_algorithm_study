import java.util.*;

class Solution {
	public String solution(String[] participant, String[] completion) {
		String answer = "";

		// 선수 이름을 key값, participant 배열에 선수 이름이 몇번 있는지를 value값으로 
		Map<String, Integer> runner = new HashMap<>();

		for (String name : participant) {
			if (runner.get(name) == null)
				runner.put(name, 1);
			// 이름이 1번 이상 나온 선수면 value값 +1 
			else
				runner.put(name, runner.get(name) + 1);
		}

		// completion에 있는 선수를 runner Map에서 찾아서 value값 -1 
		for (String name : completion) {
			runner.put(name, runner.get(name) - 1);
		}

		// value값이 0이 아닌 선수가 완주 못한 선수이므로 answer에 저장 
		for (String name : runner.keySet()) {
			if (runner.get(name) != 0) {
				answer = name;
				break;
			}
		}

		return answer;
	}
}