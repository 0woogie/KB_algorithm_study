package study;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int[] solution(int []arr) {
        Queue<Integer> queue = new LinkedList<>();
        int x = -1; //숫자가 연속되는지 확인하기 위한 변수
        for(int num : arr) {
            if(num!=x) { //직전의 숫자와 다르다면 (==연속되는 수가 아니면)
                x = num;
                queue.add(num);
            }
        }
        int[] answer = new int[queue.size()];
        for(int i=0; i<answer.length; i++)
            answer[i] = queue.poll(); //큐에서 순서대로 빼서 결과 배열로 넘겨주기
        return answer;
    }
}