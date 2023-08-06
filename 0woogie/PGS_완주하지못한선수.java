package study;

import java.util.HashMap;

class Solution {
	public String solution(String[] participant, String[] completion) {
		HashMap<String, Integer> map = new HashMap<String, Integer>(); // HashMap ����
		
		for(String p : participant) {
			if(map.containsKey(p)) { //���������� �̹� ����Ǿ� ������
				int value = map.get(p);
				map.put(p, value+1); //�ش� �̸����� �ο��� 1 �߰�
			} else {
				map.put(p, 1);
			}
		}
		
		for(String c : completion) {
			int value = map.get(c); //������ �̸����� �ο��� Ȯ��
			if(value==1) map.remove(c); //1��ۿ� ������ ������ �̸��� HashMap���� ����
			else map.put(c, value-1); //1���� ������ �ο��� 1 ���̱�
		}
		
		String answer = "";
		for(String string : map.keySet())
			answer = string; //���� ���� �� �������� ���� ����
		return answer;
    }
}

/*
 * HashMap - key ������� �����ϴ� get, put, remove, cotainsKey ���� �޼���� O(1)�� �ð����⵵
 * 					value ����� containsValue�� ���� �޼���� O(n)�� �ð����⵵
 * ���� - https://creampuffy.tistory.com/124
 */