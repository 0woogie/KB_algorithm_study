package algorithm.Team03;

import java.util.*;

public class PGS_같은숫자는싫어 {
    public int[] solution(int []arr) {
        int[] answer = {}; //반환하는 출력 리스트
        ArrayList<Integer> list = new ArrayList<Integer>(); //리스트 선언
        
        for(int i=0; i<arr.length; i++){ //입력받은 개수만큼 반복
            if(i==0) //입력받은 숫자중 가장 첫번쨰(비교할 상대 없음)
                list.add(arr[i]); // 리스트에 추가
            else if(arr[i] != arr[i-1]) // 이전에 입력받은 숫자와 같지 않으면
                list.add(arr[i]); //리스트에 추가
        }
        
        answer = new int[list.size()]; //출력 리스트만들기 (앞서 추가한 숫자가 있는 리스트 사이즈만큼)
        for(int i=0; i<answer.length; i++){ //추가한 숫자의 개수만큼 반복
            answer[i] = list.get(i); //answer에 추가
        }

        return answer; //정답 출력
    }
}