package algorithm.boj;

import java.io.*;
import java.util.*;

class FishInfo {
	int x;
	int y;
	int d; // 물고기까지의 거리 저장

	FishInfo(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
}

public class BOJ_16236 {
	static final int dx[] = { 1, 0, -1, 0 };
	static final int dy[] = { 0, 1, 0, -1 };
	static int[][] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Queue<FishInfo> q = new LinkedList<>();
		board = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) { // 0 빈 칸 / 1, 2, 3, 4, 5, 6 물고기 크기 / 9 아기 상어
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 9) {
					q.add(new FishInfo(i, j, 0));
					board[i][j] = 0;
				}
			}
		}

		int sharkSize = 2; // 아기 상어 크기
		int eat = 0; 	   // 아기 상어가 먹은 물고기 수
		int ans = 0; 	   // 아기 상어가 이동한 거리

		while (true) {
			ArrayList<FishInfo> eatFish = new ArrayList<>();
			int[][] d = new int[N][N];

			while (!q.isEmpty()) {
				FishInfo p = q.poll();
				int x = p.x;
				int y = p.y;

				for (int k = 0; k < 4; k++) {
					int nx = x + dx[k];
					int ny = y + dy[k];

					if (0 <= nx && ny < N && 0 <= ny && nx < N) {
						if (d[nx][ny] == 0 && board[nx][ny] <= sharkSize) { // 이동이 가능한지
							d[nx][ny] = d[x][y] + 1;
							q.add(new FishInfo(nx, ny, d[nx][ny])); // 상어의 새로운 위치 저장
							// 먹을 수 있는 물고기인지
							if (1 <= board[nx][ny] && board[nx][ny] <= 6 && board[nx][ny] < sharkSize) {
								eatFish.add(new FishInfo(nx, ny, d[nx][ny]));
							}
						}
					}
				}
			} // while

			if (eatFish.size() == 0) { // 더 이상 먹을 수 있는 물고기가 공간에 없는 경우
				System.out.println(ans);

				return;
			}

			FishInfo nowFish = eatFish.get(0);

			for (int i = 1; i < eatFish.size(); i++) {
				if (nowFish.d > eatFish.get(i).d) {
					nowFish = eatFish.get(i); // 가까운 물고기로 바꿔주기
				} else if (nowFish.d == eatFish.get(i).d) {
					if (nowFish.x > eatFish.get(i).x) { // 거리가 같으면, 위에 있는 물고기로 선택
						nowFish = eatFish.get(i);
					} else if (nowFish.x == eatFish.get(i).x) {
						if (nowFish.y > eatFish.get(i).y) { // 가장 왼쪽에 있는 물고기로 선택
							nowFish = eatFish.get(i);
						}
					}
				}
			}

			ans += nowFish.d;
			eat++;
			board[nowFish.x][nowFish.y] = 0;

			if (sharkSize == eat) { // 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가
				sharkSize++;
				eat = 0;
			}
			q.add(nowFish); // 아기 상어가 먹은 물고기 위치로 아기 상어 이동
		}
	}
}