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
			for(int c = 0; c < board.length; c++) { //2���� �迭 ���� ����
				bboard[c] = board[c].clone();
			}
			move(bboard, 0, i); //board, stage, direction
		}
		System.out.println(result);
	}

	static void move(int[][] board, int stage, int direction) {
		
		if(stage==5) { //5���� �̵��� ��� ������ ��
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(board[i][j]>result)
						result = board[i][j]; //�ִ� ����
				}
			}
			return;
		}
		
		boolean[][] visited = new boolean[N][N];
		if(direction==0) { //������ ��
			for(int j=0; j<N; j++) {
				for(int i=1; i<N; i++) {
					boolean chk = false;
					if(board[i][j]==0) //�� ĭ�� ���
						continue;
					for(int t=i-1; t>=0; t--) {
						if(board[t][j]==0) //���� ������ �� ĭ�� ���
							continue;
						if(board[i][j]!=board[t][j] || visited[t][j]) { //��ĥ �� ���� ����� ����ħ -> ������ ���� ���ܿ��⸸ �ϱ�
							int tmp = board[i][j];
							board[i][j] = 0;
							board[t+1][j] = tmp;
							chk = true;
							break;
						} else { //��ĥ �� �ִ� ����
							board[t][j] += board[i][j];
							visited[t][j] = true; //�� ���� �̵����� �̹� ������ ����� �� ������ �� ���� ������ �湮 ó��
							board[i][j] = 0;
							chk = true;
							break;
						}
					}
					if(!chk) { //���ܿ����� ��ġ���� ���� ��Ȳ (�ٽ� ���� ���� ���⿡ �ƹ� ��ϵ� ���� ����)
						board[0][j] = board[i][j];
						board[i][j] = 0;
					}
				}
			}
		} else if(direction==1) { //������ �Ʒ�
			for(int j=0; j<N; j++) {
				for(int i=N-2; i>=0; i--) {
					boolean chk = false;
					if(board[i][j]==0) //�� ĭ�� ���
						continue;
					for(int t=i+1; t<N; t++) {
						if(board[t][j]==0) //���� ������ �� ĭ�� ���
							continue;
						if(board[i][j]!=board[t][j] || visited[t][j]) { //��ĥ �� ���� ����� ����ħ -> ������ ���� ���ܿ��⸸ �ϱ�
							int tmp = board[i][j];
							board[i][j] = 0;
							board[t-1][j] = tmp;
							chk = true;
							break;
						} else { //��ĥ �� �ִ� ����
							board[t][j] += board[i][j];
							visited[t][j] = true; //�� ���� �̵����� �̹� ������ ����� �� ������ �� ���� ������ �湮 ó��
							board[i][j] = 0;
							chk = true;
							break;
						}
					}
					if(!chk) { //���ܿ����� ��ġ���� ���� ��Ȳ (�ٽ� ���� ���� ���⿡ �ƹ� ��ϵ� ���� ����)
						board[N-1][j] = board[i][j];
						board[i][j] = 0;
					}
				}
			}
		} else if(direction==2) { //������ ��
			for(int i=0; i<N; i++) {
				for(int j=1; j<N; j++) {
					boolean chk = false;
					if(board[i][j]==0) //�� ĭ�� ���
						continue;
					for(int t=j-1; t>=0; t--) {
						if(board[i][t]==0) //���� ������ �� ĭ�� ���
							continue;
						if(board[i][j]!=board[i][t] || visited[i][t]) { //��ĥ �� ���� ����� ����ħ -> ������ ���� ���ܿ��⸸ �ϱ�
							int tmp = board[i][j];
							board[i][j] = 0;
							board[i][t+1] = tmp;
							chk = true;
							break;
						} else { //��ĥ �� �ִ� ����
							board[i][t] += board[i][j];
							visited[i][t] = true; //�� ���� �̵����� �̹� ������ ����� �� ������ �� ���� ������ �湮 ó��
							board[i][j] = 0;
							chk = true;
							break;
						}
					}
					if(!chk) { //���ܿ����� ��ġ���� ���� ��Ȳ (�ٽ� ���� ���� ���⿡ �ƹ� ��ϵ� ���� ����)
						board[i][0] = board[i][j];
						board[i][j] = 0;
					}
				}
			}
		} else { //������ ��
			for(int i=0; i<N; i++) {
				for(int j=N-2; j>=0; j--) {
					boolean chk = false;
					if(board[i][j]==0) //�� ĭ�� ���
						continue;
					for(int t=j+1; t<N; t++) {
						if(board[i][t]==0) //���� ������ �� ĭ�� ���
							continue;
						if(board[i][j]!=board[i][t] || visited[i][t]) { //��ĥ �� ���� ����� ����ħ -> ������ ���� ���ܿ��⸸ �ϱ�
							int tmp = board[i][j];
							board[i][j] = 0;
							board[i][t-1] = tmp;
							chk = true;
							break;
						} else { //��ĥ �� �ִ� ����
							board[i][t] += board[i][j];
							visited[i][t] = true; //�� ���� �̵����� �̹� ������ ����� �� ������ �� ���� ������ �湮 ó��
							board[i][j] = 0;
							chk = true;
							break;
						}
					}
					if(!chk) { //���ܿ����� ��ġ���� ���� ��Ȳ (�ٽ� ���� ���� ���⿡ �ƹ� ��ϵ� ���� ����)
						board[i][N-1] = board[i][j];
						board[i][j] = 0;
					}
				}
			}
		}
		
		for(int i=0; i<4; i++) {
			int[][] bboard = new int[N][N];
			for(int c = 0; c < board.length; c++) {
				bboard[c] = board[c].clone(); //2���� �迭 ���� ����
			}
			move(bboard, stage+1, i); //board, stage, direction
		}
	}
}

//�ݷʸ��� - https://forward-gradually.tistory.com/83
//2���� �迭 ���� ���� - https://hanyeop.tistory.com/366
//�ٸ� ��� Ǯ�� - https://velog.io/@yoonuk/%EB%B0%B1%EC%A4%80-12100-2048-Easy-Java%EC%9E%90%EB%B0%94
