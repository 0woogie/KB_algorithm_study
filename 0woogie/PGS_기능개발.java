package study;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
  public int[] solution(int[] progresses, int[] speeds) {
      Queue<Integer> queue = new LinkedList<>();
      int total = 1;
      int maxNum = getValue(progresses[0],speeds[0]);
      
      //뒤에 더 큰 값이 나올 때까지 계속 작업의 개수 카운트
      //뒤에 더 큰 값 나오면 그 전까지의 작업의 개수를 큐에 저장
      for(int i=1; i<progresses.length; i++) {
    	  int now = getValue(progresses[i],speeds[i]);
    	  if(now>maxNum) { //견적 안 나오는 작업의 등장
    		  queue.add(total);
    		  maxNum = now;
    		  total = 1;
    	  } else {
    		  total++;
    	  }
      }
      queue.add(total);
      
      int[] answer = new int[queue.size()];
      for(int i=0; i<answer.length; i++) {
    	  answer[i] = queue.poll();
      }
      return answer;
  }
  
  static int getValue(int p, int s) { //개발 속도 계산하는 함수
	  int target = 100 - p;
	  int value = 0;
	  while(target>0) {
		  target -= s;
		  value++;
	  }
	  return value;
  }
}