package study;

import java.util.*;

public class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations); //논문 오름차순 정렬
        for (int i = 0; i < citations.length; i++) {
            int h = citations.length - i;
            if (citations[i] >= h) { //h번 이상 인용된 논문이 h편 이상인 경우
                answer = h;
                break;
            }
        }
        return answer;
    }
}
