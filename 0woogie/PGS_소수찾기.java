package study;

import java.util.HashSet;

class Solution {
    
    static int cnt;
    static boolean[] visited;
    static char[] charArr;
    static char[] result;
    static int answer;
    static HashSet<Integer> set;
    
    public int solution(String numbers) {
        answer = 0;
        set = new HashSet<Integer>(); //소수를 담기 위한 Set
        charArr = numbers.toCharArray();
        
        for(int i=1; i<=numbers.length(); i++) {
            cnt = i; //종이조각 i개를 사용해 숫자를 만드는 경우
            result = new char[i];
            visited = new boolean[numbers.length()];
            dfs(0); //count=0부터 시작
        }
        
        /*
         순열을 사용했기 때문에 종이조각 1이 두개 있었다면 두 1을 다르게 보기 때문에
         정답에 포함시키는 소수로 11을 두 번 포함시키게 된다. => Set 이용한 중복 제거 
         */
        answer = set.size();
        return answer;
    }
    
    void dfs(int count) {
        if(count==cnt){ //종이조각을 cnt개만큼 선택한 경우
        	String value = new String(result);
            int num = Integer.parseInt(value);
            boolean chk = false;
            for(int i=2; i<num; i++){
                if(num%i==0){
                    chk = true;
                    break;
                }
            }
            if(!chk && num!=1 && num!=0) //소수가 맞다면 set에 추가
            	set.add(num);
            return;
        }
        for(int i=0; i<charArr.length; i++){
            if(!visited[i]){ //선택한 적 없는 종이조각이라면
                visited[i] = true;
                result[count++] = charArr[i]; //종이조각 선택 -> result 배열에 저장
                dfs(count); //재귀호출
                count--;
                visited[i] = false;
            }
        }
    }
}