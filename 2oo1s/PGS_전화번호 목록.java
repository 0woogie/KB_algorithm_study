import java.util.*;

class Solution {

	public boolean solution(String[] phone_book) {
		boolean answer = true;
		HashSet<String> num = new HashSet<>();

		/* 
		 * 만약 A번호가 B번호의 접두어이면, 정렬하였을 때 A는 B의 앞에 위치하므로
		 * 접두어인지 확인하기 위해 정렬해줌 
		 */
		Arrays.sort(phone_book);

		for (String s : phone_book)
			num.add(s);
		
		/*
		 * 전화번호부 목록에서 가장 큰 번호는 다른 번호의 접두어가 될 수 없으므로
		 * (phone_book.length - 1)번째까지만 for문을 돌림
		 * startsWith 함수를 사용하면, (i+1)번째 번호가 i번째 번호로 시작하면 true, 아니면 false 반환
		 */
		for (int i = 0; i < phone_book.length - 1; i++) {
			if (phone_book[i + 1].startsWith(phone_book[i]))
				answer = false;
		}
		return answer;
	}
}
