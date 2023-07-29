import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int max = nums.length / 2;    // 선택 가능한 폰켓몬 수
        
        // HashSet에 폰켓몬 번호 저장 ... 중복 허용 X
        HashSet<Integer> hash = new HashSet<Integer>();
		for(int i = 0; i < nums.length; i++)
			hash.add(nums[i]);
        
        /*
        중복 제거 했을 때, HashSet 크기가 n보다 크면
        폰켓몬 종류가 n보다 많다는 것이므로 답은 최대로 가져갈 수 있는 값인 n
        */
        if(max < hash.size())
            answer = max;
        /*
        중복 제거 했을 때, HashSet 크기가 n보다 작으면
        폰켓몬 종류가 n보다 적다는 것이므로 답은 hash 크기 
        (= 존재하는 모든 폰켓몬 하나씩 가져야 제일 다양하게 종류로 폰켓몬 가질 수 있음)
		*/
        else
            answer = hash.size();
        
		return answer;
    }
}