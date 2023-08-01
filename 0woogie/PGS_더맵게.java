package study;

import java.util.PriorityQueue;

public class Solution {
	public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        int cnt = 0; //�� ���� ���� ���� Ƚ�� ī��Ʈ
        for(int value : scoville) q.add(value); //���ں� ���� ���� ������ �ڵ� ����
        boolean chk = false;
        while(q.size()!=1) {
        	if(q.peek()>=K) { //��� ���� ���ں� ���� K �̻��� ���
        		chk = true;
        		break;
        	} else {
        		q.add(q.poll()+q.poll()*2); //���ں� ���� �� ���� ��� �ٽ� ť�� �ֱ�
        		cnt++;
        	}
        }
        if(!chk) { //queue�� ���ں� ������ �ϳ��ۿ� ��� �ݺ��� ���°�� -> ��� ���� ���ں� K�̻� ���� �� ���� ������� Ȯ��
        	if(q.peek()<K) return -1;
        }
        return cnt;
    }
}