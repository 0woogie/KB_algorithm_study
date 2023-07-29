import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();  // 업뎃되는 스코빌 지수 자동 정렬
        
        for(int i : scoville)
            pq.add(i);
        
        while(true){
            /*
             스코빌 지수 하나 있는데,
             K보다 작을 때는 더이상 스코빌 섞는거 불가능하므로 -1 출력
             */
            if(pq.size()==1){
                if(pq.peek()<K)
                    return -1;
            }
            /*
            가장 작은 스코빌 지수가 K보다 작을 때는,
            가장 작은 값 poll() + 그 다음으로 작은 값 poll() x2 해서
            갱신된 스코빌 지수 다시 우선순위 큐에 저장
            */
            if(pq.peek() < K){
                pq.add(pq.poll() + pq.poll() * 2);
                answer++;
            }
            // 가장 작은 스코빌 지수이 K보다 크면, 모든 스코빌 지수가 다 K 이상이므로 while문 탈출
            else
                break;
        }
        return answer;
    }
}