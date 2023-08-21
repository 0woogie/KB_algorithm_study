import java.util.*;
class Solution {
    public Vector<Integer> solution(int[] progresses, int[] speeds) {
        int front=0;
        Vector<Integer> answer = new Vector<Integer>();
        int idx=0;
        int sum_day=0;
        boolean b=true;
        while(b){
            for(int i=front;i<progresses.length;i++){
                if(progresses[i]>=100)continue;
                progresses[i]+=speeds[i];
            }
            int day=0;
            while(front<progresses.length && progresses[front]>=100){
                day++;
                front++;
            }
            if(day!=0){
                answer.add(day);
                sum_day+=day;
            }
            if(sum_day==progresses.length)break;
        }
        return answer;
    }
}