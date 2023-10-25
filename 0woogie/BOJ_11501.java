package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11501 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			int maxValue = 0;
			long result = 0;
			for(int i=N-1; i>=0; i--) { //뒤에서부터 체크하기 (현재 사는 주식의 최고점은 맨 마지막날까지 봐야 알기 때문에)
				if(arr[i]<maxValue)
					result += maxValue - arr[i]; //최고점보다 현재 주가가 낮으면 차액만큼 result에 반영
				else
					maxValue = arr[i]; //최고점 update
			}
			System.out.println(result);
		}	
	}
}
