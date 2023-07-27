import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1414 {
    static int [] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        List<edge> l = new ArrayList<>(); //(정점, 정점, 랜선 길이)를 위해 edge라는 클래스 만듬

        int ans = 0; //전체 랜선의 길이를 저장하기 위한 변수
        String s;
        for(int i = 0;i<n;i++){
            s = br.readLine();
            for(int j = 0;j<n;j++){
                if(s.charAt(j) == '0')continue; //'0'이면 랜선이 연결 안되어 있으므로 continue
                int cost = -1;
                // a ~ z의 랜선 길이 저장
                if(s.charAt(j) >= 'a' && s.charAt(j) <='z')cost = s.charAt(j)-'a'+1;
                // A ~ Z의 랜선 길이 저장
                else if(s.charAt(j) >= 'A' && s.charAt(j) <='Z')cost = s.charAt(j)-'A' + 27;
                ans += cost;
                l.add(new edge(i+1, j+1, cost));
            }
        }

        Collections.sort(l, new Comparator<edge>() { //크루스칼 알고리즘을 활용하기 위해 랜선 길이를 기준으로 오름차순 정렬
            @Override
            public int compare(edge o1, edge o2) {
                return o1.cost - o2.cost;
            }
        });
        parent = new int[n+1];
        //최소 스패닝 트리를 활용하기 위해 parent 배열 초기화
        for(int i= 0;i<=n;i++)parent[i] = i;


        for(edge e : l){
            if(check(e.x, e.y)){ //x와 y가 서로 연결 안되 있으면
                Union(e.x, e.y); //서로 연결
                ans-=e.cost; //연결 해주니까 전체 랜선길이로부터 현재 랜선길이를 빼줘야 한다.
            }
        }
        //여기서 부터는 현재 1 ~ n 번 정점이 모두 연결되 있는지 확인한다.
        int chk = find(1);
        boolean b = true;
        for(int i=2;i<=n;i++){
            if(chk != find(i)){ //만약 연결이 되어 있지 않으면 flag인 b를 false로 만들어준다.
                b = false;
                break;
            }
        }
        if(b == true) System.out.println(ans);
        else System.out.println(-1);

    }
    static int find(int u){ //u의 최고 부모 노드를 찾는 함수
        if(parent[u] == u)return u;
        return parent[u] = find(parent[u]);
    }
    static void Union(int u, int v){ //u와 v를 합치는 함수
        u = find(u);
        v = find(v);
        if(u == v)return;
        parent[u] = v;
    }
    static boolean check(int u, int v){ //u와 v가 서로 같은 부모를 같는지 확인하는 함수
        u = find(u);
        v = find(v);
        if(u == v)return false; //서로 부모가 같으면 false
        return true; //부모가 서로 다르면 true
    }
    static class edge{
        int x;
        int y;
        int cost;
        edge(int x, int y, int cost){
            this. x = x;
            this.y = y;
            this.cost = cost;
        }
    }

}
