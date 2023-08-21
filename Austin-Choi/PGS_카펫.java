import java.util.*;
//20분

class Solution {
    public double[] solution(int b, int y) {
        double[] answer = new double[2];
        //row, col은 yellow의 row, col
        //row * col == y && row + col == (b-4)/2;
        //row = (b-4)/2 - col로 표현
        //(b-4)/2*col - col^2 = y
        //식 정리하면
        //col^2 - (b-4)/2 * col + y = 0;
        //해 구하면
        //col = 6,4 가로세로방향 상관 없으므로 
        //전체 카펫 가로세로 구하는 것이므로 
        //+2씩 해주고 
        //가로는 세로보다 크거나 같으므로 
        //큰거부터 정답배열에 삽입
        //+ 근의공식 구현
        //col^2 - ((b-4)/2) * col + y = 0;
        //a = 1, b= -1 * ((b-4)/2), c = y;
        double row = 0;
        double col = 0;
        double x1 = (((b-4)/2) + (Math.sqrt(Math.pow(((b-4)/2),2)-(4*1*y))))/2;
        double x2 = (((b-4)/2) - (Math.sqrt(Math.pow(((b-4)/2),2)-(4*1*y))))/2;
        System.out.println("x1 : "+x1+" x2 : "+x2);
        if(x1>=x2){
            answer[0] = x1 + 2;
            answer[1] = x2 + 2;
        }
        else{
            answer[0] = x1 + 2;
            answer[1] = x2 + 2;
        }
        
        return answer;
    }
}
