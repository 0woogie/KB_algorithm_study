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
		
		//d1 ä��� (Ȧ�� ���� ������ �縰��� ���� üũ)
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
		
		//d2 ä��� (¦�� ���� ������ �縰��� ���� üũ)
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
		
		//M���� ���� ����
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

//�ٸ� Ǯ�� - �ð� ���⵵�� �����
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
		
		// 1�ڸ��� ���
		for (int i=1; i<=N; i++) {
			dp[i][i] = true;
		}

		// 2�ڸ��� ���
		for (int i=1; i<N; i++) {
			if (arr[i] == arr[i+1]) {
				dp[i][i+1] = true;
			}
		}

		// 3�ڸ� �̻��� ���
		for (int i=2; i<N; i++) {
			for (int j=1; j+i<=N; j++) {
				if(dp[j+1][j+i-1] && arr[j]==arr[j+i]) //���� �� �� 2���� ���� ���� �� ������ ������ �縰����� ���
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