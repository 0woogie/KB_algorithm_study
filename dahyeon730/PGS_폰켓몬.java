package algorithm.Team03;

import java.util.*;

public class PGS_폰켓몬 {

	public int solution(int[] nums) {
		int answer = 0;
		List<Integer> list = new ArrayList<Integer>();
        
		for(int i=0; i<nums.length; i++) { //폰켓몬의 개수만큼 반복
			if(!list.contains(nums[i])) //list에 nums[i]가 포함되어 있지 않다면
				list.add(nums[i]); //list에 추가
		}
		answer = (list.size() < nums.length/2) ? list.size() : nums.length/2; //list의 원소개수가 N/2보다 작으면 list의 개수를 출력하고, N/2보다 같거나 크면 N/2를 출력
		return answer;
	}
}