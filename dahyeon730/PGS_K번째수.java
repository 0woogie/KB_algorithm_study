package algorithm.Team03;

import java.util.Arrays;

public class PGS_K번째수 {

	public int[] solution(int[] array, int[][] commands) {
        int[] answer =  new int[commands.length]; //commands의 행 개수만큼 입력
        
        for(int i=0; i<commands.length; i++) { //테스트 케이스만큼 반복
        	int start = commands[i][0]; //i
        	int end = commands[i][1]; //j
        	int k = commands[i][2];  //k
        	
        	int[] new_array = Arrays.copyOfRange(array, start-1, end); //해당 배열의 start에서부터 end직전까지 복사
        	Arrays.sort(new_array); //새로운 배열 정렬
        	answer[i] = new_array[k-1]; //k번째 수를 answer에 저장
        }
        
        return answer;
    }
}