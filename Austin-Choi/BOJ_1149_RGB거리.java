import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149_RGB거리 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//[i-1]의 최소값 + i-1인덱스를 제외한 두 값 중 최소값을 구하기
		// 이러면 인덱스값을 저장해줘야해서 어려워지므로
		// 조건에 만족하는 모든 경우를 구하고
		// 그 3가지 값 중 최소값 고르기
		//
		//현재의 색을 제외한 그 전 단계의 나머지 두가지 색 중 최소값과 현재의 색칠값을 더해주면 됨
		int N = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N+1][3];
		
		//초기화
		dp[0][0] = 0;
		dp[0][1] = 0;
		dp[0][2] = 0;
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + Integer.parseInt(st.nextToken());
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + Integer.parseInt(st.nextToken());
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + Integer.parseInt(st.nextToken());
		}
		
		System.out.println(Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2])));
	}
}
