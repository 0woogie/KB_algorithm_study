package algorithm.Team04;

import java.util.*;

//[1]: 테스트 케이스 6에서 오류 | participant['apple'], completion[] 인 경우에 오류가 발생하는 듯..?
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Arrays.sort(participant); //배열 정렬 -> 배열을 정렬해서 위치마다 서로 비교
        Arrays.sort(completion); 
        
        for(int i=0; i<completion.length; i++) { //배열 개수만큼 반복하는 동안
        	if(!(participant[i].equals(completion[i]))){ //만약 두 배열의 같은 위치마다 값이 같지 않다면(배열했기에 가능)
        		answer = participant[i]; //같지 않은 participant 값 출력
                break;
            }
        	else
        		answer = participant[i+1]; //completion의 마지막 자리 값까지 왔는데도 같지 않은 값이 없다면 participant의 마지막 값 출력
        }
        return answer; 
    }
}

//[2]: 그치만 int i를 for문 밖으로 빼주면 오류가 없다!...왜?
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Arrays.sort(participant);
        Arrays.sort(completion);
        int i;
        for (i = 0; i < completion.length; i++) {
            if(!participant[i].equals(completion[i])) {
                return participant[i];
            }
        }
        return participant[i];
    }
}