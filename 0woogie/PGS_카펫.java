package study;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        //���� X ���� == ������ ����(total) == brown+yellow
        //yellow ���� == (����-2)*(����-2)
        //brown ���� == ���� ���� - yellow ����
        
        int total = brown + yellow;
        for(int se=3; se<=Math.sqrt(total); se++) {
        	if(total%se==0) { //���� X ���� == total�� ������Ű�� -> ���� �ĺ�
        		int ga = total/se;
        		int y = (ga-2)*(se-2);
        		int b = total - y;
        		if(y==yellow && b==brown) { //���ڷ� ���޹��� brown, yellow�� ��
        			answer[0] = ga; //ī�� ���α���
        			answer[1] = se; //ī�� ���α���
        		}
        	}
        }
        return answer;
    }
}