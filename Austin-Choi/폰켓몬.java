package com.edu.algo.폰켓몬.test;
import java.util.*;

//10분
class Solution {
    public int solution(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for(int i : nums)
            s.add(i);
        
        if(s.size() <= nums.length / 2)
            return s.size();
        else
            return nums.length/2;
    }
	
}
