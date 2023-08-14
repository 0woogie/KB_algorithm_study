package algorithm.Team05;

import java.util.Arrays;

public class PGS_전화번호목록 {
	public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        Arrays.sort(phone_book); //번호(숫자)니까 sorting 하면 번호를 공통으로 가지고 있는 경우(접두어) 앞뒤로 정렬될 것
        
        for(int i=0; i<phone_book.length-1; i++) { //phone_book -1만큼 반복
        	if(phone_book[i+1].startsWith(phone_book[i]))  //phone_book[i+1]이 phone_book[i]로 시작한다면 
        		answer = false; //결과는 false
        }
  
        return answer;
    }
}

