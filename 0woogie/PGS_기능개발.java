package study;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
  public int[] solution(int[] progresses, int[] speeds) {
      Queue<Integer> queue = new LinkedList<>();
      int total = 1;
      int maxNum = getValue(progresses[0],speeds[0]);
      
      //�ڿ� �� ū ���� ���� ������ ��� �۾��� ���� ī��Ʈ
      //�ڿ� �� ū �� ������ �� �������� �۾��� ������ ť�� ����
      for(int i=1; i<progresses.length; i++) {
    	  int now = getValue(progresses[i],speeds[i]);
    	  if(now>maxNum) { //���� �� ������ �۾��� ����
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
  
  static int getValue(int p, int s) { //���� �ӵ� ����ϴ� �Լ�
	  int target = 100 - p;
	  int value = 0;
	  while(target>0) {
		  target -= s;
		  value++;
	  }
	  return value;
  }
}