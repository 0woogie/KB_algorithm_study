package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14503 {

	static int N, M;
	static int answer;
	static int[][] board;

	// 북 동 남 서
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][M];

		answer = 1; // 로봇 청소기가 있는 칸은 항상 빈 칸

		st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken()); // 0 북 1 동 2 남 3 서

		// 장소의 상태 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++)
				board[i][j] = Integer.parseInt(st.nextToken());
		}

		// System.out.println(Arrays.deepToString(board));

		dfs(r, c, d);

		System.out.println(answer);
	}

	public static void dfs(int x, int y, int dir) {
		board[x][y] = -1;

		// 현재 방향 기준으로 왼쪽으로 차례대로 탐색
		for (int i = 0; i < 4; i++) {
			dir = (dir + 3) % 4; // 현재 방향 기준으로 왼쪽부터 차례대로 탐색...북-서-남-동
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			// 주변 4칸 중 청소해야되는 칸이 있는 경우
			if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
				if (board[nx][ny] == 0) {
					answer++;
					dfs(nx, ny, dir);

					return; // return 안해주면 후진해서도 청소함
				}
			}
		}

		// 주변 4칸 모두 청소 완료거나 벽일 경우
		int backDir = (dir + 2) % 4; // 현재 방향의 반대 방향으로 후진
		int bx = x + dx[backDir];
		int by = y + dy[backDir];

		if (bx >= 0 && by >= 0 && bx < N && by < M) {
			if (board[bx][by] != 1)
				dfs(bx, by, dir);
		}
	}
}