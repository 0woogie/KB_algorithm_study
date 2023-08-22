package algorithm.Team06;
/*
 카펫 사이즈의 경우의 수 구하기
 1. brown + yellow 의 약수 구하기
 2. 가로의 길이가 세로보다 길거나 같아야 함
 3. yellow 개수 = (가로-2) * (세로-2)
 4. yellow 격자 수는 1이상 -> 세로는 3이상 -> 반복문 i는 3부터 시작 
 */

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2]; //배열 크기 선언
        
        for(int i=3; i<brown+yellow; i++){ //brown + yellow 의 약수만큼 반복
            int width = (brown+yellow)/i; //가로 = (brown + yellow)/i
            if(width>=i){ //가로의 길이가 세로보다 길거나 같은 경우
                 if ((i - 2) * (width - 2) == yellow) { //yellow 개수 = (가로-2) * (세로-2)
                	 answer[0] = width; //가로
                	 answer[1] = i; //세로
                	 break;
                 }
            }
        }
        return answer;
    }
}