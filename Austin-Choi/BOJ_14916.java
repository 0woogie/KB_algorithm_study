package com.edu.algo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14916 {
	//100000개라서
	static int[] dp= new int[100001];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		dp[0] = 0;
		dp[1] = -1;
		dp[2] = 1; //5*0 + 2*1
		dp[3] = -1; 
		dp[4] = 2; //5*0 + 2*2
		dp[5] = 1;
		dp[6] = 3;
		dp[7] = 2;
		dp[8] = 4;
		dp[9] = 3;

		/*
		 * n          dp[n] dp[n-5]와의 차이
		 * 5 = 5*1        1 +1
		 * 6 = 5*0 + 2*3  3 +4
		 * 7 = 5*1 + 2*1  2 +1
		 * 8 = 5*0 + 2*4  4 +5
		 * 9 = 5*1 + 2*2  3 +1
		 * 
		 * 10 = 5*2       2 +1
		 * 11 = 5*1 + 2*3 4 +1
		 * 12 = 5*2 + 2*1 3 +1
		 * 13 = 5*1 + 2*4 5 +1
		 * 14 = 5*2 + 2*2 4 +1
		 * 
		 * n이 10 이상일때 3142 반복 n = [n-5]+1 
		 */
		for(int i = 10; i<=N; i++) {
			dp[i] = dp[i-5]+1;
		}
		System.out.println(dp[N]);
	}
}
