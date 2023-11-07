package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12100 {

	static int N;
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] board = new int[N][N];
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<4; i++) {
			int[][] bboard = new int[N][N];
			for(int c = 0; c < board.length; c++) { //2차원 배열 깊은 복사
				bboard[c] = board[c].clone();
			}
			move(bboard, 0, i); //board, stage, direction
		}
		System.out.println(result);
	}

	static void move(int[][] board, int stage, int direction) {
		
		if(stage==5) { //5번의 이동이 모두 끝났을 때
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(board[i][j]>result)
						result = board[i][j]; //최댓값 갱신
				}
			}
			return;
		}
		
		boolean[][] visited = new boolean[N][N];
		if(direction==0) { //방향은 위
			for(int j=0; j<N; j++) {
				for(int i=1; i<N; i++) {
					boolean chk = false;
					if(board[i][j]==0) //빈 칸인 경우
						continue;
					for(int t=i-1; t>=0; t--) {
						if(board[t][j]==0) //진행 방향이 빈 칸인 경우
							continue;
						if(board[i][j]!=board[t][j] || visited[t][j]) { //합칠 수 없는 블록을 마주침 -> 인접한 곳에 땡겨오기만 하기
							int tmp = board[i][j];
							board[i][j] = 0;
							board[t+1][j] = tmp;
							chk = true;
							break;
						} else { //합칠 수 있는 상태
							board[t][j] += board[i][j];
							visited[t][j] = true; //한 번의 이동에서 이미 합쳐진 블록은 또 합쳐질 수 없기 때문에 방문 처리
							board[i][j] = 0;
							chk = true;
							break;
						}
					}
					if(!chk) { //땡겨오지도 합치지도 못한 상황 (다시 말해 진행 방향에 아무 블록도 없는 상태)
						board[0][j] = board[i][j];
						board[i][j] = 0;
					}
				}
			}
		} else if(direction==1) { //방향은 아래
			for(int j=0; j<N; j++) {
				for(int i=N-2; i>=0; i--) {
					boolean chk = false;
					if(board[i][j]==0) //빈 칸인 경우
						continue;
					for(int t=i+1; t<N; t++) {
						if(board[t][j]==0) //진행 방향이 빈 칸인 경우
							continue;
						if(board[i][j]!=board[t][j] || visited[t][j]) { //합칠 수 없는 블록을 마주침 -> 인접한 곳에 땡겨오기만 하기
							int tmp = board[i][j];
							board[i][j] = 0;
							board[t-1][j] = tmp;
							chk = true;
							break;
						} else { //합칠 수 있는 상태
							board[t][j] += board[i][j];
							visited[t][j] = true; //한 번의 이동에서 이미 합쳐진 블록은 또 합쳐질 수 없기 때문에 방문 처리
							board[i][j] = 0;
							chk = true;
							break;
						}
					}
					if(!chk) { //땡겨오지도 합치지도 못한 상황 (다시 말해 진행 방향에 아무 블록도 없는 상태)
						board[N-1][j] = board[i][j];
						board[i][j] = 0;
					}
				}
			}
		} else if(direction==2) { //방향은 좌
			for(int i=0; i<N; i++) {
				for(int j=1; j<N; j++) {
					boolean chk = false;
					if(board[i][j]==0) //빈 칸인 경우
						continue;
					for(int t=j-1; t>=0; t--) {
						if(board[i][t]==0) //진행 방향이 빈 칸인 경우
							continue;
						if(board[i][j]!=board[i][t] || visited[i][t]) { //합칠 수 없는 블록을 마주침 -> 인접한 곳에 땡겨오기만 하기
							int tmp = board[i][j];
							board[i][j] = 0;
							board[i][t+1] = tmp;
							chk = true;
							break;
						} else { //합칠 수 있는 상태
							board[i][t] += board[i][j];
							visited[i][t] = true; //한 번의 이동에서 이미 합쳐진 블록은 또 합쳐질 수 없기 때문에 방문 처리
							board[i][j] = 0;
							chk = true;
							break;
						}
					}
					if(!chk) { //땡겨오지도 합치지도 못한 상황 (다시 말해 진행 방향에 아무 블록도 없는 상태)
						board[i][0] = board[i][j];
						board[i][j] = 0;
					}
				}
			}
		} else { //방향은 우
			for(int i=0; i<N; i++) {
				for(int j=N-2; j>=0; j--) {
					boolean chk = false;
					if(board[i][j]==0) //빈 칸인 경우
						continue;
					for(int t=j+1; t<N; t++) {
						if(board[i][t]==0) //진행 방향이 빈 칸인 경우
							continue;
						if(board[i][j]!=board[i][t] || visited[i][t]) { //합칠 수 없는 블록을 마주침 -> 인접한 곳에 땡겨오기만 하기
							int tmp = board[i][j];
							board[i][j] = 0;
							board[i][t-1] = tmp;
							chk = true;
							break;
						} else { //합칠 수 있는 상태
							board[i][t] += board[i][j];
							visited[i][t] = true; //한 번의 이동에서 이미 합쳐진 블록은 또 합쳐질 수 없기 때문에 방문 처리
							board[i][j] = 0;
							chk = true;
							break;
						}
					}
					if(!chk) { //땡겨오지도 합치지도 못한 상황 (다시 말해 진행 방향에 아무 블록도 없는 상태)
						board[i][N-1] = board[i][j];
						board[i][j] = 0;
					}
				}
			}
		}
		
		for(int i=0; i<4; i++) {
			int[][] bboard = new int[N][N];
			for(int c = 0; c < board.length; c++) {
				bboard[c] = board[c].clone(); //2차원 배열 깊은 복사
			}
			move(bboard, stage+1, i); //board, stage, direction
		}
	}
}

//반례모음 - https://forward-gradually.tistory.com/83
//2차원 배열 깊은 복사 - https://hanyeop.tistory.com/366
//다른 사람 풀이 - https://velog.io/@yoonuk/%EB%B0%B1%EC%A4%80-12100-2048-Easy-Java%EC%9E%90%EB%B0%94
