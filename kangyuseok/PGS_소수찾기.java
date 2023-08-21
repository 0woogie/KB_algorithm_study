import java.util.*;
class Solution {
    int [] arr = new int[100000000];
    char[] ch;
    boolean[] visited;
    HashSet<Integer>set = new HashSet<>();
    public int solution(String numbers) {
        int answer = 0;
        prime();
        ch = new char[numbers.length()];
        visited = new boolean[numbers.length()];
        for(int i=0;i<numbers.length();i++)
            ch[i] = numbers.charAt(i); //i번째 인덱스를 char형으로변환
        dfs("", 0);
        answer = set.size();
        return answer;
    }
    public void dfs(String s, int idx){
        if(s != ""){
            if(arr[Integer.parseInt(s)]==1){
                set.add(Integer.parseInt(s));            
            }
        }
        if(idx == ch.length)return;
        for(int i=0;i<ch.length;i++){
            if(visited[i])continue;
            visited[i]=true;
            dfs(s+ch[i], idx+1);
            visited[i]=false;
        }
    }
    public void prime(){
        for(int i=0;i<100000000;i++){
            arr[i]=0;
        }
        for(int i=2;i<arr.length;i++){
            if(arr[i]==i)continue;
            arr[i]=1;
            for(int j=i+i;j<arr.length;j+=i)
                arr[j]=j;
        }
    }
}