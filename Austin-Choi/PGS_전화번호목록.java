package com.edu.algo.전화번호목록.test;

import java.util.*;

// 27분
public class PGS_전화번호목록 {
	public boolean solution(String[] phone_book) {
        Map<String, Integer> m = new HashMap<>();
        for(String s : phone_book)
            // phone_book의 스트링을 하나씩 순회하면서
            // key 값으로 해당 스트링을 넣고 value는 아무거나 동일하게 설정해줬음
            // boolean값으로 하려했는데 map은 collection이라 기초 자료형인 boolean이 들어가지 않음
            // 따라서 int의 클래스형인 Integer로 넣어 줌
            m.put(s, 1);
        
        for(String s : phone_book){
            
            // phone_book의 스트링을 하나씩 순회하면서
            for(int i = 0; i<s.length(); i++){
                
                //String.substring(시작 인덱스, 끝 인덱스)로 부분 문자열을 추출하고
                //접두어를 구하는 것이므로 시작 인덱스는 0으로 고정
                String temp = s.substring(0, i);
                
                //만약에 부분 문자열을 키값으로 하는 get함수가 동작하여
                //리턴 값이 null이 아니면 Map m에 해당 부분 문자열이 키값으로 들어있는 것.
                //(value값인 1로 == 1로 조건을 주지 못하는 이유는 
                //temp는 항상 동작하여 m.get()함수를 항상 호출하기 때문에
                //null이 리턴되서 NullPointerException이 발생할 여지가 있다) 
                //따라서 해당 부분 문자열이 접두어가 되는 것이므로
                //false를 리턴
                if(m.get(temp) != null)
                    return false;
            }
        }
        
        return true;
    }

}
