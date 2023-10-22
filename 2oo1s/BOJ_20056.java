package algorithm.boj;

import java.io.*;
import java.util.*;

public class BOJ_20056 {
	static int N, M, K;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static int[][] board;
	static ArrayList<Fireball> list;
	static int answer = 0;

	// 파이어볼 객체
	static class Fireball {
		int r, c, m, s, d;

		public Fireball(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 격자 크기
		M = Integer.parseInt(st.nextToken()); // 파이어볼 수
		K = Integer.parseInt(st.nextToken()); // 명령 횟수

		board = new int[N][N];
		list = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1; // 행
			int c = Integer.parseInt(st.nextToken()) - 1; // 열
			int m = Integer.parseInt(st.nextToken()); // 질량
			int s = Integer.parseInt(st.nextToken()); // 속력
			int d = Integer.parseInt(st.nextToken()); // 방향

			list.add(new Fireball(r, c, m, s, d));
			board[r][c]++;
		}

		for (int i = 0; i < K; i++) {
			command();
			fireballComb();
		}

		for (Fireball fb : list)
			answer += fb.m;

		System.out.println(answer);
	}

	private static void fireballComb() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] >= 2) { // 파이어볼 개수가 2개 이상일 때,
					if (divideFireball(i, j))
						board[i][j] = 4; // 다 합친 다음에 4개로 나누기 때문에, 해당 좌표 파이어볼 개수는 4개
					else
						board[i][j] = 0;
				}
			}
		}
	}

	private static boolean divideFireball(int r, int c) {
		int sumM = 0;
		int sumS = 0;
		boolean sameDir = true;
		int curDir = -1;
		int cnt = 0;
		for (int i = 0; i < list.size(); i++) {
			Fireball fb = list.get(i);

			if (fb.r == r && fb.c == c) { // 같은 좌표에 존재하는 파이어볼 더하기
				cnt++; // 같은 좌표에 존재하는 파이어볼 몇개인지 카운트
				sumM += fb.m;
				sumS += fb.s;

				if (sameDir) { // 지금까지 같은 경우
					if (curDir == -1) {
						curDir = fb.d % 2;
					} else {
						if (curDir != fb.d % 2)
							sameDir = false;
					}
				}
				list.remove(i);
				i--;
			}
		}

		int divM = sumM / 5;
		int divS = sumS / cnt;

		if (divM == 0)
			return false;
		if (sameDir) {
			for (int i = 0; i < 8; i += 2) {
				list.add(new Fireball(r, c, divM, divS, i));
			}
		} else {
			for (int i = 1; i < 8; i += 2) {
				list.add(new Fireball(r, c, divM, divS, i));
			}
		}
		return true;
	}

	private static void command() {
		for (Fireball fb : list) {
			board[fb.r][fb.c]--; // 움직이기 전 좌표에서 파이어볼 개수 하나 빼주고
			fb.r = (N + fb.r + dx[fb.d] * (fb.s % N)) % N;
			fb.c = (N + fb.c + dy[fb.d] * (fb.s % N)) % N; // 주문 방향으로 움직인 다음에,
			board[fb.r][fb.c]++; // 이동한 좌표에 파이어볼 개수 하나 더해주기
		}
	}
}