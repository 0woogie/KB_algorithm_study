
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503_로봇청소기 {
	//0북 1동 2남 3서 ij
	public static int[] di = {-1, 0, 1, 0};
	public static int[] dj = {0, 1, 0, -1};
	public static int[][] board;
	public static int N;
	public static int M;
	//시작점은 항상청소함
	public static int answer = 1;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//0 청소 안됨, 1 벽, 2 청소됨
		board = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		int robotI = Integer.parseInt(st.nextToken());
		int robotJ = Integer.parseInt(st.nextToken());
		int direction = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(robotI, robotJ, direction);
		System.out.println(answer);

	}
	public static void dfs(int curI, int curJ, int direction) {
		//A 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
		board[curI][curJ] = 2;
		//0123 %4
		
		for(int i = 0; i<4; i++) {
			//C 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
			// 1 반시계 방향으로 90도 회전한다. (시계방향으로 270도 회전한다)
			direction = (direction+3)%4;
			// 2 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
			int frontI = curI + di[direction];
			int frontJ = curJ + dj[direction];

			if(frontI >= 0 && frontI < N && frontJ >= 0 && frontJ < M) {
				if(board[frontI][frontJ] == 0) {
					answer += 1;
					dfs(frontI, frontJ, direction);
					//전형적인 dfs문제는 아니라서 종료조건 따로안씀
					return;
				}
			}
		}
		
		//B 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
		// 1 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 A번으로 돌아간다.
		int reverse = (direction+2)%4;
		int backI = curI + di[reverse];
		int backJ = curJ + di[reverse];
		if(backI >= 0 && backI < N && backJ >= 0 && backJ < M && board[backI][backJ] != 1) {
			dfs(backI, backJ, direction);
		}
		
		// 2 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
		// 3 A번으로 돌아간다.	
	}
}
