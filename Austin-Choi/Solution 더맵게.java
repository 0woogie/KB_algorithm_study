package com.edu.algo.더맵게.test;

import java.util.*;

//25분
public class Solution {
	public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i : scoville)
            pq.add(i);
        int fst = 0;
        int snd = 0;
        while(pq.peek()<K && pq.size()>1) {
        	fst = pq.poll();
        	snd = pq.poll();
        	pq.add(fst+(2*snd));
        	answer++;
        }
        //만들수 없는 경우
        if(pq.peek()<K)
            return -1;
        return answer;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
