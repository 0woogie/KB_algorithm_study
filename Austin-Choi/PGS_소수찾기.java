import java.util.*;

class Solution {
    static int N;
    static Set<Integer> ansSet;
    static char[] tmp;
    static boolean[] visited;
    //소수 판별
    public static boolean isPrime(int n){
        if(n==0 || n==1)
            return false;
        for(int i = 2; i<=Math.sqrt(n); i++){
            if(n%i == 0)
                return false;
        }
        return true;
    }
    //백트래킹
    public static void dfs(String s, int idx){
        //빈 문자열 제외하고
        if(s != ""){
            int n = Integer.parseInt(s);
            //System.out.println("현재 숫자: "+n+" idx : "+idx);
            //소수 판별
            if(isPrime(n)){
               // System.out.println("소수: "+n+" idx : "+idx);
                ansSet.add(n);
            }
        }
        //종료조건
        if(idx == N)
            return;
        else{
            for(int i = 0; i<N; i++){
                if(!visited[i]){
                    visited[i] = true; 
                    //System.out.println("현재 조합 : "+s+tmp[i]+" idx : "+idx);
                    dfs(s+tmp[i],idx+1);
                    visited[i] = false; //백트래킹
                }
            }
        }
    }
    
    public int solution(String numbers) {
        int answer = 0;
        N = numbers.length();
        ansSet = new HashSet<>();
        tmp = new char[N];
        visited = new boolean[N+1];
        
        for(int i = 0; i<N; i++){
            tmp[i] = numbers.charAt(i);
        }
        dfs("", 0);
        return ansSet.size();
    }
    
}
