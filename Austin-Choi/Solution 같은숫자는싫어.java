package com.edu.algo.같은숫자는싫어.test;

import java.util.*;

//15분
public class Solution {
    public ArrayList<Integer> solution(int []arr) {
        ArrayList<Integer> answer = new ArrayList<>();
        //순서대로 큐에 넣고 중복없이 answer에 저장
        Queue<Integer> q = new LinkedList<>();
        for(int i : arr){
            q.add(i);
        }
        int cur = 0;
        int prev = q.poll();
        answer.add(prev);
        
        while(!q.isEmpty()){
            cur = q.poll();
            if(cur != prev)
                answer.add(cur);
            prev = cur;
        }
        return answer;
    }
    public static void main(String[] args) {
		
	}
}