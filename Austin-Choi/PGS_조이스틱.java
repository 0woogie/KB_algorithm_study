import java.util.*;
//todo : bfs 짧은 이름에서는 동작하지만 길어지면 시간초과남
//비트마스킹?

//최소 거리는 전체 상하조작 수 + 좌우조작 수 

//상하조작
//Math.min(target_char - 'A', 26 - (target_char - 'A')); 상, 하 중에 더 적은 값 구함

//좌우조작
//시작은 0번째 커서에 위치함
//오른쪽으로 A가 아닌 첫 문자까지 가기 name.length -1
//왼쪽으로 A가 아닌 첫 문자까지 가기 if (idx == -1) idx = name.length-1;
//중간에 방향 바꾸는 경우 
//다음 A가 아닌 문자 있는 곳까지 인덱스 더해줘야됨
class Solution {
    class Pairs{
        String first;
        int second;
        int third;

        Pairs(String first, int second, int third){
            this.first = first;
            this.second = second;
            this.third = third;
        }

        public void setFirst(String first){
            this.first = first;
        }

        public String getFirst(){
            return this.first;
        }

        public int getSecond(){
            return this.second;
        }

        public int getThird(){
            return this.third;
        }
    
    }
    static String target = "";
    static int answer = 0;
    static char[] charName;
    public int solution(String name) {
        target = name;
        
        charName = new char[name.length()];
        for(int i = 0; i<name.length(); i++){
            charName[i] = name.charAt(i);
        }
        
        String startStr = "";
        for(int i = 0; i<name.length(); i++){
            startStr+="A";
        }
        
        bfs(startStr, 0, 0);
        
        return answer;
    }
    
    public void bfs(String name, int idx, int moves){
        Queue<Pairs> q = new LinkedList<>();
        q.add(new Pairs(name, idx, moves));
        
        while(!q.isEmpty()){
            Pairs cur = q.poll();
            
            String curStr = cur.getFirst();
            char[] charCurStr = new char[curStr.length()];
            for(int i = 0; i<curStr.length(); i++){
                charCurStr[i] = curStr.charAt(i);
            }
            
            int curIdx = cur.getSecond();
            int curMoves = cur.getThird();
            
            //상하조작
            if(name.charAt(curIdx) != target.charAt(curIdx)){
                curMoves += Math.min(target.charAt(curIdx)-'A', 26-(target.charAt(curIdx)-'A'));
                //타겟 문자로 바꿔주고
                //valueOf(char[])로 string으로 변환해서 cur의 first값 set.
                charCurStr[curIdx] = charName[curIdx];
                curStr = String.valueOf(charCurStr);
                cur.setFirst(curStr);
            }
            //종료조건
            if(curStr.equals(target)){
                answer = curMoves;
                return;
            }
            //좌우조작
            else
            {
                int right = curIdx + 1;
                if(right > target.length() - 1)
                    right = 0;
                int left = curIdx - 1;
                if(left < 0)
                    left = target.length() - 1;
                q.add(new Pairs(curStr, right, curMoves+1));
                q.add(new Pairs(curStr, left, curMoves+1));
            }
        }
    }
}
