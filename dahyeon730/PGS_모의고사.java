package algorithm.Team04;

import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] peroson1 = {1,2,3,4,5}; // 5개씩 반복
        int[] person2 = {2,1,2,3,2,4,2,5}; // 8개씩 반복
        int[] person3 = {3,3,1,1,2,2,4,4,5,5}; // 10개씩 반복
        int[] score = {0,0,0}; // 각 수포자들의 점수
        
        // 수포자들의 점수 계산
        for(int i=0; i<answers.length; i++) {
            if(answers[i] == peroson1[i%5]) score[0]++; //5개씩 반복되기 때문에 %5
            if(answers[i] == person2[i%8]) score[1]++; //8개씩 반복되기 때문에 %8
            if(answers[i] == person3[i%10]) score[2]++; //10개씩 반복되기 때문에 %10
        }
        
        int max = Math.max(score[0], Math.max(score[1], score[2])); //차례로 비교하면서 최대 점수 구하기
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0; i<score.length; i++) { //3번 반복
        	if(max == score[i]) // 최대 점수를 가진 수포자 리스트 생성
        		list.add(i+1);
        }
        
        //출력하기
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }

        return answer;
    }
}