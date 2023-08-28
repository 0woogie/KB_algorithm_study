class Solution {
    public int[] solution(int brown, int yellow) {
        int g=-1, s=-1;
        for(int sero=1;sero<=yellow;sero++){
            int garo = yellow /sero;
            if(garo < sero)break;
            if(((garo*2) + (sero*2)) + 4 ==brown){
                g = garo;
                s= sero;
            }
        }
        int[] answer = new int [2];
        answer[0]=g+2;
        answer[1]=s+2;
        return answer;
    }
}