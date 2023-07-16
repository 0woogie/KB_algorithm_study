package study;
import java.util.Scanner;

public class S5215 {

	static int result;
	static int N, M;
	static int L;
	static int[][] arr;
	static int[][] output;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt(); //재료의 수
			L = sc.nextInt(); //제한 칼로리
			
			arr = new int[N][2];
			for(int i=0; i<N; i++) {
				arr[i][0] = sc.nextInt(); //재료 i의 점수
				arr[i][1] = sc.nextInt(); //재료 i의 칼로리
			}
			result = 0;
			for(int i=1; i<=N; i++) { //재료 N개 중에서 1개 뽑는 경우 ~ N개 뽑는 경우까지
				M = i;
				output = new int[M][2]; //뽑은 재료 M개의 점수와 칼로리 저장을 위한 배열 output
				dfs(0, 0); //depth, start
			}
			System.out.println("#"+test_case+" " + result);
		}
	}

	static void dfs(int depth, int start) {
		if(depth==M) { //종료 조건(M개를 뽑았을 때)
			//재료의 점수 합과 칼로리 합 계산
			int score = 0;
			int cal = 0;
			for(int i=0; i<M; i++) {
				score += output[i][0];
				cal += output[i][1];
			}
			//제한 칼로리 이하면서 현재 저장된 최고 점수보다 높으면 갱신
			if(cal<=L && score>result) result = score;
			return;
		}
		for(int i=start; i<N; i++) {
			output[depth][0] = arr[i][0];
			output[depth][1] = arr[i][1];
			dfs(depth+1, i+1);
		}
	}

}
