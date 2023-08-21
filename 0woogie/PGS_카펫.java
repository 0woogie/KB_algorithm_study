package study;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        //가로 X 세로 == 격자의 개수(total) == brown+yellow
        //yellow 개수 == (가로-2)*(세로-2)
        //brown 개수 == 격자 개수 - yellow 개수
        
        int total = brown + yellow;
        for(int se=3; se<=Math.sqrt(total); se++) {
        	if(total%se==0) { //가로 X 세로 == total을 만족시키면 -> 정답 후보
        		int ga = total/se;
        		int y = (ga-2)*(se-2);
        		int b = total - y;
        		if(y==yellow && b==brown) { //인자로 전달받은 brown, yellow와 비교
        			answer[0] = ga; //카펫 가로길이
        			answer[1] = se; //카펫 세로길이
        		}
        	}
        }
        return answer;
    }
}