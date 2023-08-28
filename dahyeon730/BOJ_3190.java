package algorithm.Team07;

import java.util.*;
import java.io.*;

public class BOJ3190 {

	static int[][] map; //보드 크기
	static List<int[]> snake = new ArrayList<>();
	static int n, k, l;
	static HashMap<Integer, String> hash = new HashMap<>();
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0}; //동남서북

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine()); //보드의 크기 입력
		k = Integer.parseInt(br.readLine()); //사과의 개수 입력

		map = new int[n][n]; //보드의 크기
		for (int i = 0; i < k; i++) { //사과의 개수만큼 반복
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1; //행의 위치
			int b = Integer.parseInt(st.nextToken()) - 1; //열의 위치
			map[a][b] = 1; //보드의 초기화

		}
		
		l = Integer.parseInt(br.readLine()); //뱀의 방향 변환 횟수
		
		//뱀의 방향 변환 정보
		for (int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); //게임 시작 시간으로부터 X초
			String c = st.nextToken(); //왼쪽, 오른쪽 방향으로 90도 회전
			hash.put(x, c); //hash에 대입
		}
		
		solve();
	}
	
	public static void solve() {
		int cx = 0, cy = 0;
		int time = 0;
		int d = 0;
		snake.add(new int[] { 0, 0 });

		while (true) {
			// 1. 시간재기
			time++;

			// 2. 뱀 이동하기
			int nx = cx + dx[d];
			int ny = cy + dy[d];

			// 3. 범위를 벗어나거나, 뱀 몸통 만날 때 종료
			if (isFinish(nx, ny))
				break;

			// 4. 사과가 있을 때 없을 때 처리
			if (map[nx][ny] == 1) {
				map[nx][ny] = 0;
				snake.add(new int[] { nx, ny });

			} else {
				snake.add(new int[] { nx, ny });
				snake.remove(0);
			}

			// 5. 방향을 바꿔주는 시간을 만날 때 방향 변경
			if (hash.containsKey(time)) {
				if (hash.get(time).equals("D")) {
					d += 1;
					if (d == 4)
						d = 0;
				} else {
					d -= 1;
					if (d == -1)
						d = 3;
				}
			}

			// 6. 현재값 업데이트
			cx = nx;
			cy = ny;
		}

		System.out.println(time); //게임이 끝나는 시간(초)
	}

	public static boolean isFinish(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= n || ny >= n) { // 벽 부딪힘
			return true;
		}

		for (int i = 0; i < snake.size(); i++) {
			int[] t = snake.get(i);
			if (nx == t[0] && ny == t[1])
				return true;
		}
		return false;
	}
}