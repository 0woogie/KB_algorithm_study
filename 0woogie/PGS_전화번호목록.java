package study;

import java.util.Arrays;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        for(int i=0; i<phone_book.length-1; i++) { //�ٷ� ���� ���ڿ��̶��� ���ϸ� ��
        	String a = phone_book[i];
        	String b = phone_book[i+1];
        	if(a.length()<=b.length()) { //���� ���ڿ��� ���̰� ���� ���ڿ����� ª���� ������ üũ
        		b = b.substring(0, a.length()); //���� ���ٸ� ���� ���ڿ� ���̸�ŭ�� �ڸ���(���ξ� ��)
        	}
        	if(a.equals(b))
        		return false;
        }
        return true;
    }
}
