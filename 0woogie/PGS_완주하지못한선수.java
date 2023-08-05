package study;

import java.util.HashMap;

class Solution {
	public String solution(String[] participant, String[] completion) {
		HashMap<String, Integer> map = new HashMap<String, Integer>(); // HashMap 선언
		
		for(String p : participant) {
			if(map.containsKey(p)) { //동명이인이 이미 저장되어 있으면
				int value = map.get(p);
				map.put(p, value+1); //해당 이름으로 인원수 1 추가
			} else {
				map.put(p, 1);
			}
		}
		
		for(String c : completion) {
			int value = map.get(c); //완주자 이름으로 인원수 확인
			if(value==1) map.remove(c); //1명밖에 없으면 완주자 이름을 HashMap에서 제거
			else map.put(c, value-1); //1명보다 많으면 인원수 1 줄이기
		}
		
		String answer = "";
		for(String string : map.keySet())
			answer = string; //이제 남은 건 완주하지 못한 선수
		return answer;
    }
}

/*
 * HashMap - key 기반으로 동작하는 get, put, remove, cotainsKey 등의 메서드는 O(1)의 시간복잡도
 * 					value 기반의 containsValue와 같은 메서드는 O(n)의 시간복잡도
 * 참고 - https://creampuffy.tistory.com/124
 */