package study;

import java.util.ArrayList;

class Solution {
    public int[] solution(int[] answers) {
        int[] answer;
        int[] type1 = {1,2,3,4,5};
        int[] type2 = {2,1,2,3,2,4,2,5};
        int[] type3 = {3,3,1,1,2,2,4,4,5,5};
        int[] result = new int[3];
        for(int i=0; i<answers.length; i++){ //삼인방 정답체크
            if(answers[i] == type1[i%type1.length]) result[0]++;
            if(answers[i] == type2[i%type2.length]) result[1]++;
            if(answers[i] == type3[i%type3.length]) result[2]++;
        }
        int maxNum = Math.max(Math.max(result[0],result[1]),result[2]); //가장 많이 맞힌 경우 몇 문제 맞힌건지 구하기
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<3; i++) {
            if(result[i]==maxNum) list.add(i+1); //가장 많이 맞힌 사람 가변적이기 때문에 ArrayList 이용
        }
        answer = new int[list.size()];
        int idx = 0;
        for(int value : list)
            answer[idx++] = value; //List to Array
        return answer;
    }
}