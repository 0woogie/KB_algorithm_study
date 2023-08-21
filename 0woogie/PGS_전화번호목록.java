package study;

import java.util.Arrays;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        for(int i=0; i<phone_book.length-1; i++) { //바로 뒤의 문자열이랑만 비교하면 됨
        	String a = phone_book[i];
        	String b = phone_book[i+1];
        	if(a.length()<=b.length()) { //뒤의 문자열의 길이가 앞의 문자열보다 짧지는 않은지 체크
        		b = b.substring(0, a.length()); //문제 없다면 앞의 문자열 길이만큼만 자르기(접두어 비교)
        	}
        	if(a.equals(b))
        		return false;
        }
        return true;
    }
}
