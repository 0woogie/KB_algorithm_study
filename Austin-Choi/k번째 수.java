package com.edu.algo.k번째수.test;
import java.util.*;

class Solution {
    public ArrayList<Integer> solution(int[] array, int[][] commands) {
        ArrayList<Integer> answer = new ArrayList<>();
        int i = -1;
        int j = -1;
        int target = -2;
        ArrayList<Integer> arr;
        for(int[] command : commands){
            arr = new ArrayList<>();
            i = command[0]-1;
            j = command[1]-1;
            target = command[2];
            for(; i<=j; i++){
                arr.add(array[i]);
            }
            Collections.sort(arr);
            answer.add(arr.get(target-1));
        }
        return answer;
    }
}
