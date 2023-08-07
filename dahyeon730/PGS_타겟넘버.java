package algorithm.Team03;

public class PGS_타겟넘버 {
	
	int count = 0;

	public int solution(int[] numbers, int target) {
        int answer = 0;
        dfs(numbers, 0, target, 0); //dfs() 함수 실행
        answer = count;
        
        return answer;
    }

	private void dfs(int[] numbers, int depth, int target, int result) {
		if(depth == numbers.length) { //마지막 노드까지 dfs() 진행했다면
			if(target == result) //타겟변수 값과 dfs()에서 이전노드까지의 합계값이 같다면
				count++; //count+1
			return;
		}
		
		int plus = result + numbers[depth]; //양수를 더한 값
		int minus = result - numbers[depth]; //음수를 더한 값
		
		dfs(numbers, depth+1, target, plus); //해당 노드 값을 더하고 다음 값 탐색
		dfs(numbers, depth+1, target, minus); //해당 노드 값을 빼고 다음 값 탐색
		
	}
}