package algorithm.Team03;

import java.util.PriorityQueue;

public class PGS_더맵게 {

	public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>(); //우선순위가 낮은 숫자로 큐선언
        for(int i=0; i<scoville.length; i++) { //입력받은 스코빌지수의 개수만큼 반복
        	heap.add(scoville[i]); //heap에 스코빌 지수 추가
        }
        
        while(heap.peek()<K) { //heap의 첫번째 값이 K보다 작을 동안 반복
        	if(heap.size()<2) //heap 개수가 1,0이면 -1 리턴
        		return -1;
        	
        	int f1 = heap.poll(); //heap의 첫번째값 반환+제거
        	int f2 = heap.poll(); //heap의 첫번째값 반환+제거
        	int mixK = f1 + (f2*2); //섞은 음식의 스코빌 지수 선언
        	
        	heap.add(mixK); //heap에 섞은 음식의 스코빌 지수 추가
        	answer++; //횟수 1추가
        }
        
        return answer; //정답 반환
    }
}