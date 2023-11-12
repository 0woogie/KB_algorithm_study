import java.io.*;
import java.util.*;

// 64분
public class BOJ_1167 {
    static int V;
    static ArrayList<Node> tree[];
    static boolean[] visited;
    static int answer = 0;
    static int temp = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());
        tree = new ArrayList[V + 1];
        visited = new boolean[V + 1];

        for (int i = 0; i <= V; i++)
            tree[i] = new ArrayList<>();

        for (int i = 0; i < V; i++) {   // 트리 정보 입력
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            while (true) {
                int e = Integer.parseInt(st.nextToken());
                if (e == -1)
                    break;
                int d = Integer.parseInt(st.nextToken());
                tree[s].add(new Node(e, d));
            }
        }
        dfs(1, 0);  // dfs 탐색 시작
        visited = new boolean[V + 1];
        /*
       dfs를 또 다시 진행하는 이유는,
       만약 지름의 양 끝점이 x, y인 경우
       위에서 1부터 탐색을 진행해서 양 끝점 중 하나인 x(=temp)를 구했는데
       1에서 시작했다고 1이 y라는 건 보장할 수 없으니
       x부터 dfs를 한번 더 진행해서 트리의 지름을 구하기 위해
         */
        dfs(temp, 0);
        System.out.println(answer);
    }

    public static void dfs(int v, int len) {
        if (len > answer) { // 트리의 지름이 긴 경우로 갱신
            answer = len;
            temp = v;
        }
        visited[v] = true;

        for (Node n : tree[v]) {
            if (!visited[n.v]) {
                visited[n.v] = true;
                dfs(n.v, len + n.d);
            }
        }
    }

    static class Node {
        int v;  // 연결된 정점 번호
        int d;  // 거리

        public Node(int v, int d) {
            this.v = v;
            this.d = d;
        }
    }
}
