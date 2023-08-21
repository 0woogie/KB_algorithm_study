package algorithm.Team06;

import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {

        List<Integer> list = new ArrayList<>();
        int[] works = new int[progresses.length]; //작업 개수 배열 선언
        
        for(int i = 0; i < speeds.length; i++){ //작업 개수만큼 반복
            works[i]  = (100 - progresses[i]) / speeds[i]; //진도율과 스피드에 따른 작업 기간 계산식
            if ((100 - progresses[i]) % speeds[i] != 0){ //나머지가 딱 떨어지지 않으면 "작업기간+1"
                works[i]  += 1;
            }
        }
        
        int x = works[0]; 
        int count=1;
        for(int i=1;i<progresses.length;i++){

            if(x>=works[i]){ //전에 나온 x가 나중에 나온 works[i]보다 크거나 같으면 -> 첫번째 기능과 같이 배포해야 하기 때문에
                count+=1; //+1
            }
            else{ //전에 나온 x가 나중에 나온 works[i]보다 작으면 -> count를 list에 추가, count를 1로 초기화, x=works[i]로 처리
                list.add(count);
                count =1;
                x= works[i];
            }
        }
        list.add(count); //for문을 빠져나와서 마지막 처리 count를 리스트에 추가
        
        int[] answer = new int[list.size()];

        for (int i = 0; i <list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}