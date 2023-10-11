package algorithm.boj;

import java.io.*;
import java.util.*;

public class BOJ_21610 {
	static int N, M;
	static int[][] board;
	static boolean[][] cloud;
	// 대각은 2,4,6,8
	static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		cloud = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++)
				board[i][j] = Integer.parseInt(st.nextToken());
		}

		// 처음 비바라기 시전했을 때, 구름 위치
		cloud[N - 1][0] = cloud[N - 1][1] = cloud[N - 2][0] = cloud[N - 2][1] = true;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()); // 방향
			int s = Integer.parseInt(st.nextToken()); // 몇 칸

			cloud = command(board, cloud, d, s);
		}

		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				answer += board[i][j];
		}

		System.out.println(answer);
	}

	public static boolean[][]command(int[][] board, boolean[][] cloud, int d, int s) {
		boolean[][] cloudMove = moveCloud(board, cloud, d, s);

		waterCopyBug(board, cloudMove);
		newCloud(board, cloudMove);
		// System.out.println(Arrays.deepToString(board));

		return cloudMove;
	}

	// 조건 5에 해당하는 부분
	public static void newCloud(int[][] board, boolean[][] cloudMove) {
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {

				if (cloudMove[x][y])
					cloudMove[x][y] = false;
				else if (!cloudMove[x][y] && board[x][y] >= 2) {
					cloudMove[x][y] = true;
					board[x][y] -= 2;
				}
			}
		}

	}

	// 조건 4에 해당하는 부분
	public static void waterCopyBug(int[][] board, boolean[][] cloudMove) {
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (cloudMove[x][y]) {
					int cnt = 0;

					for (int dir = 2; dir <= 8; dir += 2) {
						int nx = x + dx[dir];
						int ny = y + dy[dir];

						if (nx >= 0 && nx < N && ny >= 0 && ny < N && board[nx][ny] > 0)
							cnt++;
					}
					// 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 물 증가
					board[x][y] += cnt;
				}
			}
		}
	}

	// 조건 1에 해당
	public static boolean[][] moveCloud(int[][] board, boolean[][] cloud, int d, int s) {
		boolean[][] cloudMove = new boolean[N][N];

		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (cloud[x][y]) { // 구름이 있는 좌표라면
					int nx = x + (dx[d] * s);
					int ny = y + (dy[d] * s);

					if (nx >= N)
						nx %= N;
					else if (nx < 0)
						nx = (nx + 1) % N + N - 1;

					if (ny >= N)
						ny %= N;
					else if (ny < 0)
						ny = (ny + 1) % N + N - 1;

					// 이동 후, 구름 위치 체크
					cloudMove[nx][ny] = true;

					// 구름이 있는 칸의 바구니에 물 +1
					board[nx][ny] += 1;
				}
			}
		}
		return cloudMove;
	}
}