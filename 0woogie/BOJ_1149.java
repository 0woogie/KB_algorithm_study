package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] A = new int[N][3];
		int[][] D = new int[N][3];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		D[0][0] = A[0][0];
		D[0][1] = A[0][1];
		D[0][2] = A[0][2];
		
		for(int i=1; i<N; i++) {
			//현재 칠하는 색의 비용 + 인접한 집이 다른 색을 칠한 경우의 비용 중 최솟값
			D[i][0] = A[i][0] + Math.min(D[i-1][1], D[i-1][2]);
			D[i][1] = A[i][1] + Math.min(D[i-1][0], D[i-1][2]);
			D[i][2] = A[i][2] + Math.min(D[i-1][0], D[i-1][1]);
		}
		
		System.out.println(Math.min(Math.min(D[N-1][0], D[N-1][1]), D[N-1][2]));
	}

}