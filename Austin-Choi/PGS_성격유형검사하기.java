import java.util.*;
//40분
class Solution {
    public String solution(String[] survey, int[] choices) {
        StringBuilder answer = new StringBuilder();
        //chararray로 자르고
        //map에 char score로 저장하고 (-4)
        // for문 survey에 대해 한번 더 돌려서 
        // 점수 큰거 
        // rt, fc, mj, an
        Map<Character, Integer> m = new HashMap<Character, Integer>();
        String[] types = {"RT", "FC", "MJ", "AN"};
        for(int i = 0; i < types.length; i++){
            char[] tmp = types[i].toCharArray();
            m.put(tmp[0], 0);
            m.put(tmp[1], 0);
        }
        for(int i = 0; i < survey.length; i++){
            char[] tmp = survey[i].toCharArray();
            int score = choices[i] - 4;
            char key;
            if(score < 0){
                key = tmp[0];
                score = Math.abs(score);
            }
            else 
                key = tmp[1];
            m.put(key, m.get(key)+score);
        }
        for(int i = 0; i < types.length; i++){
            char[] tmp = types[i].toCharArray();
            int a = m.get(tmp[0]);
            int b = m.get(tmp[1]);
            if(a == b){
                if(tmp[0]<tmp[1])
                    answer.append(tmp[0]);
                else
                    answer.append(tmp[1]);
            }
            else{
                if(a>b){
                    answer.append(tmp[0]);
                }
                else
                    answer.append(tmp[1]);
            }
        }
        return answer.toString();
    }
}
