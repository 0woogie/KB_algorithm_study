import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10942_팰린드롬? {
	public static void main(String[] args) throws IOException {
		// 1 2 1 2 1
		// s       e
		// 1.
		// if dp[start][end] = true
		// then dp[start+1][end-1] = true
		// 2.
		// dp[i][i] = true
		// 3.
		// if arr[i] == arr[i+1] 
		// then dp[i][i+1] = true
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		boolean[][] dp = new boolean[N][N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		
		//전체 팰린드롬 구하기
		//초기화
		for(int i = 0; i<N; i++) {
			// 자기자신은 팰
			dp[i][i] = true;
		}
		for(int i = 0; i<N-1; i++) {
			// 11 22 33 이런것도 팰
			if(arr[i]==arr[i+1])
				dp[i][i+1] = true;
		}
		//구간 크기 k 0과 1은 처리해줬으니까 2부터 
		for(int k = 2; k<N; k++) {
			//0부터 N-k까지 
			for(int i=0; i<N-k; i++) {
				// i~j 까지일때 i+1에서 j-1까지 팰린드롬이고 i와 j가 같으면 i~j도 팰린드롬임
				// i + k 번째 칸
				int j = i+k;
				if((arr[i]==arr[j]) && (dp[i+1][j-1]))
					dp[i][j] = true;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			if(dp[start][end])
				sb.append("1\n");
			else
				sb.append("0\n");
		}
		System.out.println(sb);
	}

}
