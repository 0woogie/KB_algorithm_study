package algorithm.boj;

import java.io.*;
import java.util.*;

// 75분 
public class BOJ_1941 {
	static char[][] board = new char[5][5];
	static int[] boardX = new int[25];
	static int[] boardY = new int[25];
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int answer;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 5; i++) {
			String temp = br.readLine();
			for (int j = 0; j < 5; j++)
				board[i][j] = temp.charAt(j);
		}
		// System.out.println(Arrays.deepToString(board));

		for (int i = 0; i < 25; i++) { // 0~24까지 학생들 자리 좌표를 x, y 따로 저장해두기
			boardX[i] = i % 5;
			boardY[i] = i / 5;
		}

		comb(new int[7], 0, 0); // 7명의 학생 조합하기
		System.out.println(answer);
	}

	// 선택한 학생 담는 배열, 현재 선택된 7공주로 선택된 학생 수, 현재 7공주 후보인 학생
	public static void comb(int[] list, int idx, int check) {

		if (idx == 7) { // 7명 모두 뽑았다면,
			bfs(list); // 서로 인접하게 앉았는지 확인
			return;
		}

		if (check == 25) // 모든 학생을 다 후보로
			return;

		list[idx] = check;

		comb(list, idx + 1, check + 1); // 후보인 학생 선택한 경우
		comb(list, idx, check + 1); // 후보인 학생 선택 안 한 경우
	}

	public static void bfs(int[] list) {

		Queue<Integer> q = new LinkedList<>();
		boolean visited[] = new boolean[7];

		visited[0] = true; // list[0] 학생은 무조건 7공주 멤버로 확정
		q.add(list[0]);

		int cnt = 1; // 7공주 확정 멤버 수 = list[0]
		int dasom = 0; // 다솜파 수

		while (!q.isEmpty()) {
			int temp = q.poll();

			if (board[boardX[temp]][boardY[temp]] == 'S')
				dasom++;

			for (int i = 0; i < 4; i++) {
				for (int next = 1; next < 7; next++) { // list[0]을 기준으로 list[1]부터 인접하게 있는지 확인
					int adjX = boardX[temp] + dx[i]; // 주변 학생 x좌표
					int adjY = boardY[temp] + dy[i]; // 주변 학생 y좌표

					// temp 주변 학생의 좌표가 7공주 학생 중 한명의 좌표와 같을 때
					if (adjX == boardX[list[next]] && adjY == boardY[list[next]] && !visited[next]) {
						visited[next] = true;
						q.add(list[next]);
						cnt++;
					}
				}
			}
		}
		if (cnt == 7 && dasom >= 4) // 모두 다 인접하게 앉아있고, 다솜파가 4명 이상인 경우
			answer++;
	}
}