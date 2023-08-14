package study;

import java.util.HashSet;

class Solution {
    
    static int cnt;
    static boolean[] visited;
    static char[] charArr;
    static char[] result;
    static int answer;
    static HashSet<Integer> set;
    
    public int solution(String numbers) {
        answer = 0;
        set = new HashSet<Integer>(); //�Ҽ��� ��� ���� Set
        charArr = numbers.toCharArray();
        
        for(int i=1; i<=numbers.length(); i++) {
            cnt = i; //�������� i���� ����� ���ڸ� ����� ���
            result = new char[i];
            visited = new boolean[numbers.length()];
            dfs(0); //count=0���� ����
        }
        
        /*
         ������ ����߱� ������ �������� 1�� �ΰ� �־��ٸ� �� 1�� �ٸ��� ���� ������
         ���信 ���Խ�Ű�� �Ҽ��� 11�� �� �� ���Խ�Ű�� �ȴ�. => Set �̿��� �ߺ� ���� 
         */
        answer = set.size();
        return answer;
    }
    
    void dfs(int count) {
        if(count==cnt){ //���������� cnt����ŭ ������ ���
        	String value = new String(result);
            int num = Integer.parseInt(value);
            boolean chk = false;
            for(int i=2; i<num; i++){
                if(num%i==0){
                    chk = true;
                    break;
                }
            }
            if(!chk && num!=1 && num!=0) //�Ҽ��� �´ٸ� set�� �߰�
            	set.add(num);
            return;
        }
        for(int i=0; i<charArr.length; i++){
            if(!visited[i]){ //������ �� ���� ���������̶��
                visited[i] = true;
                result[count++] = charArr[i]; //�������� ���� -> result �迭�� ����
                dfs(count); //���ȣ��
                count--;
                visited[i] = false;
            }
        }
    }
}