package study;

import java.util.PriorityQueue;

public class Solution {
	public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        int cnt = 0; //두 개의 음식 섞는 횟수 카운트
        for(int value : scoville) q.add(value); //스코빌 지수 낮은 순으로 자동 정렬
        boolean chk = false;
        while(q.size()!=1) {
        	if(q.peek()>=K) { //모든 음식 스코빌 지수 K 이상인 경우
        		chk = true;
        		break;
        	} else {
        		q.add(q.poll()+q.poll()*2); //스코빌 낮은 두 음식 섞어서 다시 큐에 넣기
        		cnt++;
        	}
        }
        if(!chk) { //queue에 스코빌 지수가 하나밖에 없어서 반복문 나온경우 -> 모든 음식 스코빌 K이상 만들 수 없는 경우인지 확인
        	if(q.peek()<K) return -1;
        }
        return cnt;
    }
}