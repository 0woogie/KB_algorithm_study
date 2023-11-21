package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_14500 {
    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, board[i][j], 1);
                visited[i][j] = false;
            }
        }
        System.out.println(answer);
    }

    public static void dfs(int i, int j, int score, int cnt) {  // (i, j), 좌표에 해당하는 점수, 현재까지 블록 몇 개 이어붙였는지
        if (cnt == 4) { // 테트로미노 완성 시, 점수 최댓값으로 갱신
            answer = Math.max(answer, score);
            return;
        }

        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];

            // 'ㅡ', 'ㄹ', 'ㄴ', 'ㅁ'은 dfs로 만들 수 있는데 'ㅜ'는 안되므로 따로 처리해줘야됨
            if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                if (!visited[nx][ny]) {
                    if (cnt == 2) { // 'ㅜ' 모양 탐색을 위해
                        visited[nx][ny] = true;
                        dfs(i, j, score + board[nx][ny], cnt + 1);
                        visited[nx][ny] = false;
                    }
                    visited[nx][ny] = true;
                    dfs(nx, ny, score + board[nx][ny], cnt + 1);
                    visited[nx][ny] = false;
                }
            }
        }
    }

}