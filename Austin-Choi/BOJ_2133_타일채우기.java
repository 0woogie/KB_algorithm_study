import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//public class Main{
public class BOJ_2133_타일채우기 {
	public static long[] dp;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		dp = new long[31];
		//dp[0]은 없지만 dp에서 특별한 상수항 처리에 활용될 수 있다
		dp[0] = 1; //N이 짝수일때마다 나타나는 특별한 모양 2가지가 있으므로 dp[0] * 2
		dp[1] = 0;
		/*
		 * ㅡ  ㅡ  ||
		 * ||  =  ㅡ 
		 * 
		 */
		dp[2]= 3; 
		dp[3] = 0;
		/*
		 * 2칸짜리 2개로 구성하는 방법이므로 dp[2]*dp[2] + dp[0] * 2
		 * 특별한 모양 2개 = 11개
		 */
		dp[4] = dp[2] * dp[2] + 2; //11
		
		/*
		 * dp[6] 일때
		 * n=4일때로 채우고 2일때로 구성하는 방식 A. 그러면 dp[4] * dp[2]
		 * n=2일때로 채우고 n=4일때로 구성하는 방식 B 
		 * => n=4부분을 일반적인 n=4모양 11방식짜리로 채우면 A와 중복이 발생. 왜냐면 특별한 2개 구성 빼고는 dp[2]로 구성되기 때문
		 * 따라서 뒤의 n=4를 4도 n%2==0이므로 특별한 모양 2개로 채운다고 가정함. 그러면 2 * dp[2]  
		 * n%2 == 0일때 나타나는 특별한 모양 2개 C 그러면 2
		 * dp[6] 
		 * = dp[4] * dp[2] + (dp[2] * 2 + dp[0] * 2) 
		 * 
		 * 결론, N이 4보다 크고 2의 배수일 때 dp[N] = dp[N-2] * dp[2] + dp[N-4] * dp[2] + ... + [[ dp[2] * 2 + dp[0] * 2 ]]
		 * [[ ]]부분은 상수항 2를 곱한것으로 반복되므로 else안의 nested-for로 처리
		 * 
		 */
		for(int i = 5; i<=N; i++) {
			if(i%2 == 1)
				dp[i] = 0;
			else
			{
				//겹치는 모양 고려해줘야함
				//N이 4이상이고 
				//dp[i] = dp[i-2] * dp[2]*2;-- 생각없이 짠 첫번째 점화식
				dp[i] = dp[i-2] * dp[2];
				for(int j = i-4; j>=0; j-=2) {
					dp[i] += dp[j]*2; 
				}
			}
		}
		System.out.println(dp[N]);
	}

}
