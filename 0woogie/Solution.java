package study;

import java.util.HashSet;

public class Solution {
    public int solution(int[] nums) {
        int n = nums.length; //연구실 폰켓몬 수
        HashSet<Integer> s = new HashSet<>();
        for(int num : nums) s.add(num); //집합에 넣기 -> 같은 종류 폰켓몬 중복 제거
        if(n/2<s.size()) //폰켓몬 종류가 가질 수 있는 개수보다 많으면
            return n/2; //가질 수 있는 개수만큼 종류별로 하나씩 가져가기
        else //종류가 더 적으면
            return s.size(); //아쉬운대로 종류별로 쓸어가기
    }
}
