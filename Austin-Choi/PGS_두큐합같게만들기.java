import java.util.*;
//22분
class Solution {
    //두개의 배열을 이어붙이고
    //두 부분합이 같게 되는 횟수 구하기..?
    //queue1 앞이 start, start++는 queue1에서 연산 발생
    //현재 합이 목표값보다 크면 start++
    //queue2 앞이 end, end++는 queue2에서 연산 발생
    //현재 합이 목표값보다 작으면 end++
    //투포인터
    
    // 0 1 2 3
    // 4 5 6 7
    
    // 3 2 7 2 14
    // 4 6 5 1 16
    // start부터 end까지 더한게 15보다 작으므로 end++
    // 3 2 7 2 4 18
    // 6 5 1 12
    // startend가 15보다 크므로 start++
    // 2 7 2 4 15
    // 6 5 1 3 15
    // 끝
    //합 구하는 iterator 돌리기보단 그때그때 원소 추가하고 빼주면서
    //각각의 합 갱신하기
    // public long sum(Deque<Long> d){
    //     Iterator<Long> i = d.iterator();
    //     long total = 0;
    //     while(!i.hasNext()){
    //         total = i.next();
    //     }
    //     return total;
    // }
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        long target = 0L;
        int size = queue1.length;
        //두개가 자리 서로 다 바꾸는 횟수가 size의 4배이므로
        int answerLimit = size * 4;
        
        Queue<Integer> q1 = new LinkedList<Integer>();
        Queue<Integer> q2 = new LinkedList<Integer>();
        long q1Total = 0L;
        long q2Total = 0L;
        
        for(int i = 0; i<size; i++){
            q1.add(queue1[i]);
            q2.add(queue2[i]);
            q1Total += queue1[i];
            q2Total += queue2[i];
        }
        // 홀수면 안됨
        if((q1Total + q2Total) % 2 == 1)
            return -1;
        target = (q1Total + q2Total) / 2;
        //두 큐의 합이 같아야되니까 둘중 하나만 따져도됨
        while(true){
            if(answer == answerLimit){
                return -1;
            }
            if(q1Total == target){
                return answer;
            }
            else {
                if(q1Total < target){
                    int temp = q2.poll();
                    q1.add(temp);
                    q1Total += temp;
                    answer++;
                }
                else{
                    int temp = q1.poll();
                    q2.add(temp);
                    q1Total -= temp;
                    answer++;
                }
            }
        }
    }
}
