package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10942 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		int[] d1 = new int[N+1];
		int[] d2 = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		StringBuffer sb = new StringBuffer();
		
		//d1 채우기 (홀수 길이 수열의 펠린드롬 여부 체크)
		for(int i=1; i<=N; i++) {
			int index = 0;
			while(true) {
				if(i-index<=0 || i+index>N)
					break;
				if(arr[i-index]!=arr[i+index])
					break;
				index++;
			}
			d1[i] = 1 + (index-1)*2;
		}
		
		//d2 채우기 (짝수 길이 수열의 펠린드롬 여부 체크)
		for(int i=1; i<N; i++) {
			if(arr[i]!=arr[i+1])
				continue;
			int index = 0;
			while(true) {
				if(i-index<=0 || i+1+index>N)
					break;
				if(arr[i-index]!=arr[i+1+index])
					break;
				index++;
			}
			d2[i] = 2 + (index-1)*2;
		}
		
		//M번의 질문 수행
		int M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int mid = (S+E)/2;
			if((E-S+1)%2==1) { //d1
				if(d1[mid]>=E-S+1)
					sb.append(1);
				else
					sb.append(0);
			} else { //d2
				if(d2[mid]>=E-S+1)
					sb.append(1);
				else
					sb.append(0);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}

//다른 풀이 - 시간 복잡도는 비슷함
/*
public class BOJ_10942 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		boolean[][] dp = new boolean[N+1][N+1];

		StringBuffer sb = new StringBuffer();
		
		// 1자리일 경우
		for (int i=1; i<=N; i++) {
			dp[i][i] = true;
		}

		// 2자리일 경우
		for (int i=1; i<N; i++) {
			if (arr[i] == arr[i+1]) {
				dp[i][i+1] = true;
			}
		}

		// 3자리 이상일 경우
		for (int i=2; i<N; i++) {
			for (int j=1; j+i<=N; j++) {
				if(dp[j+1][j+i-1] && arr[j]==arr[j+i]) //수열 양 끝 2개의 수가 같고 그 사이의 수열이 펠린드롬인 경우
					dp[j][j+i] = true;
			}
		}

		int M = Integer.parseInt(br.readLine());
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			if(dp[S][E])
				sb.append(1);
			else
				sb.append(0);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
*/