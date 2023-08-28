package algorith.study;

import java.util.*;
import java.io.*;

public class BOJ_3190 {
	static int N;
	static int[][] board;
	static List<int[]> snake = new ArrayList<>();
	static HashMap<Integer, String> command = new HashMap<>();
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());

		board = new int[N][N];
		// 보드판에 사과 위치 표시
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			board[x - 1][y - 1] = 1;
		}
		// 뱀의 방향 변환 정보 저장
		int L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int X = Integer.parseInt(st.nextToken());
			String C = st.nextToken();
			command.put(X, C);
		}
		move();
	}

	public static void move() {
		boolean flag = false;
		int t = 0; // 게임 시간
		int x = 0, y = 0; // 시작 위치
		int d = 0; // 게임 진행 방향

		snake.add(new int[] { 0, 0 });

		while (true) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			t += 1;

			// 종료조건 ... 벽에 부딪힐 때,
			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				break;

			// 자기 자신의 몸과 부딪힐 때,
			for (int i = 0; i < snake.size(); i++) {
				int[] temp = snake.get(i);
				if (nx == temp[0] && ny == temp[1]) {
					flag = true;
					break;
				}
			}
			if (flag)
				break;
			// 여까지 종료 조건

			// 사과 있는 자리면 사과 없애고, 머리 늘리기
			if (board[nx][ny] == 1) {
				board[nx][ny] = 0;
				snake.add(new int[] { nx, ny });

			} else {
				snake.add(new int[] { nx, ny });
				snake.remove(0); // 사과 없는 자리면 꼬리 지워주기
			}

			// 방향 바꿔줘야되는 시간이면, 방향 변경해주기
			if (command.containsKey(t)) {
				if (command.get(t).equals("L")) { // 왼쪽인 경우
					d -= 1;
					if (d == -1)
						d = 3;
				} else { // 오른쪽인 경우
					d += 1;
					if (d == 4)
						d = 0;
				}
			}
			x = nx;
			y = ny;
		}
		System.out.println(t);
	}
}
