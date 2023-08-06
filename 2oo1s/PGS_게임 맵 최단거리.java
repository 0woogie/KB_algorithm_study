import java.util.*;

class Solution {
	// 상하좌우 확인용 좌표
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static boolean[][] visited;

	public int solution(int[][] maps) {
		int answer = 0;
		visited = new boolean[maps.length][maps[0].length];

		// 처음 캐릭터 위치부터 bfs 탐색
		bfs(0, 0, maps);

		// 상대방 진영의 값이 그대로 1이면 공격 못했다는 것이므로 -1 반환
		if (maps[maps.length - 1][maps[0].length - 1] == 1)
			return -1;
		// 그 외엔, 상대방 진영의 좌표값이 최단 거리값이므로 answer값 갱신
		else
			answer = maps[maps.length - 1][maps[0].length - 1];
		return answer;
	}

	// 알고리즘 수업 시간에 작성한 bfs 함수와 동일
	public static void bfs(int i, int j, int[][] maps) {
		Queue<Integer[]> q = new LinkedList<>();
		q.add(new Integer[] { i, j });
		visited[i][j] = true;

		while (!q.isEmpty()) {
			Integer[] t = q.poll();
			for (int k = 0; k < 4; k++) {
				int x = t[0] + dx[k];
				int y = t[1] + dy[k];
				if (x >= 0 && y >= 0 && x < visited.length && y < visited[0].length) {
					if (maps[x][y] != 0 && !visited[x][y]) {
						visited[x][y] = true;
						maps[x][y] = maps[t[0]][t[1]] + 1;
						q.add(new Integer[] { x, y });
					}
				}
			}
		}
	}
}